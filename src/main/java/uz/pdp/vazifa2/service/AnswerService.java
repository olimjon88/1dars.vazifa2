package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.dto.AnswerDto;
import uz.pdp.vazifa2.dto.Message;
import uz.pdp.vazifa2.entity.Answer;
import uz.pdp.vazifa2.entity.Task;
import uz.pdp.vazifa2.entity.User;
import uz.pdp.vazifa2.repo.AnswerRepository;
import uz.pdp.vazifa2.repo.TaskRepository;
import uz.pdp.vazifa2.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<Answer>> getByTask(Integer taskId) {
        return ResponseEntity.ok(answerRepository.findAllByTaskId(taskId));
    }

    public ResponseEntity<List<Answer>> getByUser(Integer userId) {
        return ResponseEntity.ok(answerRepository.findAllByUserId(userId));
    }

    public ResponseEntity<Answer> get(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Message> add(AnswerDto dto) {
        Optional<Task> optionalTask = taskRepository.findByIdAndStatusTrue(dto.getTaskId());
        if (!optionalTask.isPresent())
            return new ResponseEntity<>(new Message("Task not found", false), HttpStatus.NOT_FOUND);

        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        if (!optionalUser.isPresent())
            return new ResponseEntity<>(new Message("User not found", false), HttpStatus.NOT_FOUND);

        answerRepository.save(Answer.builder()
                .task(optionalTask.get())
                .user(optionalUser.get())
                .text(dto.getText())
                .isCorrect(dto.getText().equals(optionalTask.get().getSolution())).build());
        return ResponseEntity.ok(new Message("Answer added", true));
    }

    public ResponseEntity<Message> edit(Integer id, AnswerDto dto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ResponseEntity<>(new Message("Answer not found", false), HttpStatus.NOT_FOUND);

        Optional<Task> optionalTask = taskRepository.findByIdAndStatusTrue(dto.getTaskId());
        if (!optionalTask.isPresent())
            return new ResponseEntity<>(new Message("Task not found", false), HttpStatus.NOT_FOUND);

        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        if (!optionalUser.isPresent())
            return new ResponseEntity<>(new Message("User not found", false), HttpStatus.NOT_FOUND);

        Answer answer = optionalAnswer.get();
        answer.setTask(optionalTask.get());
        answer.setText(dto.getText());
        answer.setUser(optionalUser.get());
        answer.setCorrect(dto.getText().equals(optionalTask.get().getSolution()));
        answerRepository.save(answer);
        return ResponseEntity.ok(new Message("Answer edited", true));
    }

    public ResponseEntity<Message> delete(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        answerRepository.delete(optionalAnswer.get());
        return ResponseEntity.ok(new Message("Answer deleted", true));
    }
}
