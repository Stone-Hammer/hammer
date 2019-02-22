package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.service.Lives_newsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("search")
public class SearchPageController {

    @Autowired
    private Lives_newsService lives_newsService;

    @GetMapping("")
    public String search(Model model, HttpSession httpSession){
        model.addAttribute("user",httpSession.getAttribute("user"));
        return "search";
    }

    @GetMapping("/do")
    public String doSearch(Model model, HttpSession httpSession, String words){
        User user = (User)httpSession.getAttribute("user");
        model.addAttribute("user",httpSession.getAttribute("user"));
        return "search";
    }
}
