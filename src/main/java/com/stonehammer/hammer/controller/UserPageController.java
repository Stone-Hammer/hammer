package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("truthhammer")
public class UserPageController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        //List<Student> lists = studentService.getAllStudent();
        //model.addAttribute("stus", lists);
        return "index";
    }

}
