package com.microservice.technicaltest.controller;

import com.microservice.technicaltest.entity.UserEntity;
import com.microservice.technicaltest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAll(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public Optional<UserEntity> getById(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }

    @PostMapping
    public void saveUpdate(@RequestBody UserEntity user){
        userService.saveOrUpdate(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") Long userId){
        userService.delete(userId);
    }
}
