package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.dto.ExampleDto;
import uz.pdp.vazifa2.dto.Message;
import uz.pdp.vazifa2.entity.Example;
import uz.pdp.vazifa2.service.ExampleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Example>> getByTask(@PathVariable Integer taskId){
        return exampleService.getByTask(taskId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Example> get(@PathVariable Integer id){
        return exampleService.get(id);
    }

    @PostMapping
    public ResponseEntity<Message> add(@Valid @RequestBody ExampleDto dto){
        return exampleService.add(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> edit(@Valid @PathVariable Integer id, @RequestBody ExampleDto dto){
        return exampleService.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Integer id){
        return exampleService.delete(id);
    }
}
