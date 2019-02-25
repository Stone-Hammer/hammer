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
@RequestMapping("message")
public class MessagePageController {

    @Autowired
    private MessageService messageService;

    private boolean validate(User user){
        if (user==null||user.getUser_id()==null||
                user.getName()==null||user.getName().equals("")||
                user.getPassword()==null||user.getPassword().equals("")){
            return false;
        }
        return true;
    }

    @GetMapping("/user")
    public String message(Model model, HttpSession httpSession){
        User user = (User)httpSession.getAttribute("user");
        if (!validate(user)){
            model.addAttribute("message", "登录失效 请重新登录~");
            return "hammer";
        }
        model.addAttribute("user",user);
        List<Message> messages=messageService.getAllMessage(user.getUser_id());
        model.addAttribute("messages",messages);
        return "user_message";
    }
//    public String messagehomepage(Model model){
//        return "mymessage.html";
//    }
}
