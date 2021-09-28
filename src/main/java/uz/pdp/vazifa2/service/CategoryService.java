package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.dto.CategoryDto;
import uz.pdp.vazifa2.dto.Message;
import uz.pdp.vazifa2.entity.Category;
import uz.pdp.vazifa2.entity.Language;
import uz.pdp.vazifa2.helpers.Utils;
import uz.pdp.vazifa2.repo.CategoryRepository;
import uz.pdp.vazifa2.repo.LanguageRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LanguageRepository languageRepository;

    public ResponseEntity<List<Category>> get() {
        return ResponseEntity.ok(categoryRepository.findAllByStatusTrue());
    }

    public ResponseEntity<Category> get(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndStatusTrue(id);
        return optionalCategory.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<Category>> getByLanguage(Integer languageId) {
        return ResponseEntity.ok(categoryRepository.findAllByLanguage(languageId));
    }

    public ResponseEntity<Message> add(CategoryDto dto) {
        if (categoryRepository.existsByNameAndStatusTrue(dto.getName()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Set<Language> languages = getLanguages(dto.getLanguageId());
        if (Utils.isEmpty(languages))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        categoryRepository.save(Category.builder().name(dto.getName()).status(true).language(languages).description(dto.getDescription()).build());
        return ResponseEntity.ok(new Message("Category added", true));
    }

    public ResponseEntity<Message> edit(Integer id, CategoryDto dto) {
        if (categoryRepository.existsByNameAndIdNotAndStatusTrue(dto.getName(), id))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Optional<Category> optionalCategory = categoryRepository.findByIdAndStatusTrue(id);
        if (!optionalCategory.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Set<Language> languages = getLanguages(dto.getLanguageId());
        if (Utils.isEmpty(languages))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Category category = optionalCategory.get();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setLanguage(languages);
        categoryRepository.save(category);
        return ResponseEntity.ok(new Message("Category edited", true));
    }

    public ResponseEntity<Message> delete(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndStatusTrue(id);
        if (!optionalCategory.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Category category = optionalCategory.get();
        category.setStatus(false);
        categoryRepository.save(category);
        return ResponseEntity.ok(new Message("Category deleted", true));
    }

    public Set<Language> getLanguages(Set<Integer> languageIds){
        Set<Language> languages = new HashSet<>();
        for (Integer languageId : languageIds) {
            Optional<Language> optionalLanguage = languageRepository.findByIdAndStatusTrue(languageId);
            if (!optionalLanguage.isPresent())
                return null;
            languages.add(optionalLanguage.get());
        }
        return languages;
    }

}
