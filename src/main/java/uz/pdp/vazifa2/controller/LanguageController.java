package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.dto.LanguageDto;
import uz.pdp.vazifa2.dto.Message;
import uz.pdp.vazifa2.entity.Language;
import uz.pdp.vazifa2.service.LanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages(){
        return languageService.getAllLanguages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> get(@PathVariable Integer id){
        return languageService.get(id);
    }

    @PostMapping
    public ResponseEntity<Message> add(@Valid @RequestBody LanguageDto dto){
        return languageService.add(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> edit(@Valid @PathVariable Integer id, @RequestBody LanguageDto dto){
        return languageService.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Integer id){
        return languageService.delete(id);
    }
}
