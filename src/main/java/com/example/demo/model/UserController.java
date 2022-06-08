package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAll(){
        return this.userService.allUsers();
    }

    @GetMapping({"{login}"})
    public ResponseEntity<User> getUser (@PathVariable("login") String login){
        return new ResponseEntity<User>(userService.findByLogin(login), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> getUserByLogin(@RequestBody User user) {
        var exists = false;
        try {
            exists =  userService.findByLogin(user.getLogin()) != null;
        }
        catch (Exception e){
            e.getMessage();
        }
        if (exists) {
            return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
        } else {

            return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
        }
    }

    //zmiana email
    @PutMapping("{id}")
    public ResponseEntity<User> updateEmail(@PathVariable("id") Long id, @RequestBody User user){
        return new ResponseEntity<User>(userService.updateEmail(user, id), HttpStatus.OK);
    }

    @PutMapping("/signUp/{id}")
    public ResponseEntity<User> signUpLecture (@PathVariable("id") Long lectureId, @RequestBody User user,
                                               String email, String login){
        return new ResponseEntity<User>(userService.signUp(user, lectureId, email, login), HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<User> cancel (@PathVariable("id") Long lectureId, @RequestBody User user){
        return new ResponseEntity<User>(userService.cancel(user, lectureId), HttpStatus.OK);
    }

        


}
