package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.dto.Message;
import uz.pdp.vazifa2.dto.UserDto;
import uz.pdp.vazifa2.entity.User;
import uz.pdp.vazifa2.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return userService.get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id){
        return userService.get(id);
    }

    @PostMapping
    public ResponseEntity<Message> add(@Valid @RequestBody UserDto dto){
        return userService.add(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> edit(@Valid @PathVariable Integer id, @RequestBody UserDto dto){
        return userService.edit(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Integer id){
        return userService.delete(id);
    }
}
