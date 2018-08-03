package com.jcfc.springboot.controller;

import com.jcfc.springboot.entity.User;
import com.jcfc.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        Optional<User> optional = userRepository.findById(id);
        User one = userRepository.getOne(id);
        System.out.println(one);
        return optional.get();
    }

    @GetMapping("user")
    public User insertUser(User user) {
        User user1 = userRepository.save(user);
        return user1;
    }

}
