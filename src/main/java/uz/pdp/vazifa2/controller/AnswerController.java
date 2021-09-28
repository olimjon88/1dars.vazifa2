package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.dto.AnswerDto;
import uz.pdp.vazifa2.dto.Message;
import uz.pdp.vazifa2.entity.Answer;
import uz.pdp.vazifa2.service.AnswerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Answer>> getByTask(@PathVariable Integer taskId){
        return answerService.getByTask(taskId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Answer>> getByUser(@PathVariable Integer userId){
        return answerService.getByUser(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> get(@PathVariable Integer id){
        return answerService.get(id);
    }

    @PostMapping
    public ResponseEntity<Message> add(@Valid @RequestBody AnswerDto dto){
        return answerService.add(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> edit(@Valid @PathVariable Integer id, @RequestBody AnswerDto dto){
        return answerService.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Integer id){
        return answerService.delete(id);
    }
}
