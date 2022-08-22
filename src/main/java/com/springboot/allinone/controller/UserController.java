package com.springboot.allinone.controller;

import com.springboot.allinone.pojo.User;
import com.springboot.allinone.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("index")
    public String index() {
        return "hello user";
    }

    @GetMapping("/queryUserById/{id:.+}")
    public User selectUserById(@PathVariable long id) {
        System.out.println("id:" + id);
        User user = userService.selectByPrimaryKey(id);
        System.out.println(user.toString());
        return user;
    }
}
