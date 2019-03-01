package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class adminUserPageController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/all")
    public String getAllUser(Model model){
        List<User> lists=userService.getAllUser();
        model.addAttribute("users",lists);
        return "member-list";
    }

    @GetMapping("/user/add")
    public String add_user_show(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("cap","添加新的用户");
        return "member-add";
    }

    @PostMapping("/user/add")
    public String add_manager_submit(Model model,@ModelAttribute User user){
        User newUser=new User();
        newUser.setUser_id(user.getUser_id());
        newUser.setName(user.getName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        newUser.setSex(user.getSex());
        newUser.setIcon(user.getIcon());
        userService.addUser(newUser);
        return getAllUser(model);
//        model.addAttribute("user",newUser);
//        return "result";
    }

    @GetMapping("/user/update/{id}")
    public String update_user_show(Model model,@PathVariable("id") Integer user_id){
        User tmp=userService.getUserById(user_id);
        model.addAttribute("user",tmp);
        model.addAttribute("cap","修改用户信息");
        return "member-add";
    }

    @PostMapping("/user/update/{id}")
    public String update_manager_submit(Model model,@PathVariable("id") Integer user_id,User user){
        user.setUser_id(user_id);
        userService.updateUser(user);
        return getAllUser(model);
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUserById(Model model,@PathVariable("id") Integer user_id){
        userService.deleteUser(user_id);
        return getAllUser(model);
    }
}
