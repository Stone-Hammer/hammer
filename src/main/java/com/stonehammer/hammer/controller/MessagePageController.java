package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Message;
import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user")
public class MessagePageController {

    @Autowired
    private MessageService messageService;


    public String allmessage(Model model, HttpSession httpSession){
        User user = (User)httpSession.getAttribute("user");
        model.addAttribute("user",httpSession.getAttribute("user"));
        List<Message> messagelists=messageService.getAllMessage(user.getUser_id());
        return "message";
    }
    @GetMapping("/message")
    public String messagehomepage(Model model){
        return "mymessage.html";
    }
}
