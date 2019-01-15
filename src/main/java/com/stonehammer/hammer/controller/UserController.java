package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    private User addUser(User user){ return userService.addUser(user);}

    @GetMapping("/all")
    private List<User> getAllUser(){ return userService.getAllUser();}

    @GetMapping("/getUserById/{user_id}")
    private User getUserById(@PathVariable("user_id") Integer user_id){return userService.getUserById(user_id);}

    @PostMapping("/getUserByEmailAndPwd")
    private User getUserByEmailAndPwd(String email,String password){ return userService.getUserByEmailAndPwd(email, password);}

    @PostMapping("/getUserByPhoneAndPwd")
    private User getUserByPhoneAndPwd(String phone,String password){ return userService.getUserByPhoneAndPwd(phone, password);}

    @PutMapping("/updateUser")
    private User updateUser(User user){ return userService.updateUser(user);}

    @DeleteMapping("/deleteUserById/{user_id}")
    private void deleteUser(@PathVariable("user_id") Integer user_id){ userService.deleteUser(user_id);}
}
