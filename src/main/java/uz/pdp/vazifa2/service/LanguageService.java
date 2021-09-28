package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.dto.LanguageDto;
import uz.pdp.vazifa2.dto.Message;
import uz.pdp.vazifa2.entity.Language;
import uz.pdp.vazifa2.repo.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CategoryService categoryService;

    public ResponseEntity<List<Language>> getAllLanguages() {
        return ResponseEntity.ok(languageRepository.findAllByStatusTrue());
    }

    public ResponseEntity<Language> get(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Message> add(LanguageDto dto) {
        if (languageRepository.existsByNameAndStatusTrue(dto.getName()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        languageRepository.save(Language.builder().name(dto.getName()).status(true).build());
        return ResponseEntity.ok(new Message("Language added", true));
    }

    public ResponseEntity<Message> edit(Integer id, LanguageDto dto) {
        if (languageRepository.existsByNameAndIdNotAndStatusTrue(dto.getName(), id))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Optional<Language> optionalLanguage = languageRepository.findByIdAndStatusTrue(id);
        if (!optionalLanguage.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Language language = optionalLanguage.get();
        language.setName(dto.getName());
        languageRepository.save(language);
        return ResponseEntity.ok(new Message("Language edited", true));
    }

    public ResponseEntity<Message> delete(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            languageRepository.deleteById(id);
        return ResponseEntity.ok(new Message("Language deleted",true));

    }
}
