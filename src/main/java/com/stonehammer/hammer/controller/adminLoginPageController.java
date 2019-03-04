package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Manager;
import com.stonehammer.hammer.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class adminLoginPageController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("/login")
    public String index(Model model, HttpSession httpSession){
        Manager manager=(Manager)httpSession.getAttribute("manager");
        model.addAttribute("manager",manager);
        return "login";
    }

    @PostMapping("/login/suc")
    public String login(Model model, HttpSession httpSession
            , String name, String password){
        Manager manager=managerService.getManagerByNameAndPwd(name, password);
        if(manager!=null){
            model.addAttribute("manager",manager);
            httpSession.setAttribute("manager",manager);
        }
        else{
            model.addAttribute("message","登录失败！用户名密码不正确");
        }
        return "index-1";
    }
}
