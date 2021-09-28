package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.dto.Message;
import uz.pdp.vazifa2.dto.TaskDto;
import uz.pdp.vazifa2.entity.Task;
import uz.pdp.vazifa2.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> get(){
        return taskService.get();
    }

    @GetMapping("/language/{languageId}/category/{categoryId}")
    public ResponseEntity<List<Task>> getByCategory(@PathVariable Integer languageId, @PathVariable Integer categoryId){
        return taskService.getByCategory(languageId,categoryId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable Integer id){
        return taskService.get(id);
    }

    @PostMapping
    public ResponseEntity<Message> add(@Valid @RequestBody TaskDto dto){
        return taskService.add(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> edit(@Valid @PathVariable Integer id, @RequestBody TaskDto dto){
        return taskService.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Integer id){
        return taskService.delete(id);
    }
}
