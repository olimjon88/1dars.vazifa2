package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.dto.CategoryDto;
import uz.pdp.vazifa2.dto.Message;
import uz.pdp.vazifa2.entity.Category;
import uz.pdp.vazifa2.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> get(){
        return categoryService.get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> get(@PathVariable Integer id){
        return categoryService.get(id);
    }

    @GetMapping("/language/{languageId}")
    public ResponseEntity<List<Category>> getByLanguage(@PathVariable Integer languageId){
        return categoryService.getByLanguage(languageId);
    }

    @PostMapping
    public ResponseEntity<Message> add(@Valid @RequestBody CategoryDto dto){
        return categoryService.add(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> edit(@Valid @PathVariable Integer id, @RequestBody CategoryDto dto){
        return categoryService.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Integer id){
        return categoryService.delete(id);
    }
}
