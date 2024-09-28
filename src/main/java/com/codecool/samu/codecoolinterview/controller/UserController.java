package com.codecool.samu.codecoolinterview.controller;

import com.codecool.samu.codecoolinterview.dbTarget.model.Student;
import com.codecool.samu.codecoolinterview.dbTarget.model.User;
import com.codecool.samu.codecoolinterview.dto.UserDto;
import com.codecool.samu.codecoolinterview.service.target.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("")
    public long addStudent(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/id/{id}")
    public User getStudentById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/name/{name}")
    public User getStudentByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }
}
