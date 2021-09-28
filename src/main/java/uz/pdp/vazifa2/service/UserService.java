package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.dto.Message;
import uz.pdp.vazifa2.dto.UserDto;
import uz.pdp.vazifa2.entity.Answer;
import uz.pdp.vazifa2.entity.User;
import uz.pdp.vazifa2.helpers.Utils;
import uz.pdp.vazifa2.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerService answerService;

    public ResponseEntity<List<User>> get() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<User> get(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Message> add(UserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        userRepository.save(User.builder().email(dto.getEmail()).password(dto.getPassword()).status(true).build());
        return ResponseEntity.ok(new Message("User added", true));
    }

    public ResponseEntity<Message> edit(Integer id, UserDto dto) {
        if (userRepository.existsByEmailAndIdNot(dto.getEmail(), id))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User user = optionalUser.get();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok(new Message("User edited", true));
    }

    public ResponseEntity<Message> delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User user = optionalUser.get();
        List<Answer> answers = answerService.getByUser(user.getId()).getBody();
        if (!Utils.isEmpty(answers)){
            for (Answer answer : answers) {
                answerService.delete(answer.getId());
            }
        }
        user.setStatus(false);
        userRepository.save(user);
        return ResponseEntity.ok(new Message("User deleted",true));
    }
}
