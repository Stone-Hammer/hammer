package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Lives_news;
import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.service.Lives_newsService;
import com.stonehammer.hammer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("hammer")
public class UserPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private Lives_newsService lives_newsService;

    @GetMapping("/")
    public String index(Model model) {
        List<Lives_news> list = lives_newsService.getAllLives();
        model.addAttribute("liveslist", list);
        return "index";
    }

}
