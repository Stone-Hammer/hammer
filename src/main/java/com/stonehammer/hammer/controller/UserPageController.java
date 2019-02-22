package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Lives_news;
import com.stonehammer.hammer.entity.Story_news;
import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.service.Lives_newsService;
import com.stonehammer.hammer.service.Story_newsService;
import com.stonehammer.hammer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("hammer")
public class UserPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private Lives_newsService lives_newsService;
    @Autowired
    private Story_newsService story_newsService;

    //首页展示的时事新闻和故事化新闻的个数
    private static final int INDEX_LIVES_NUM = 3;
    private static final int INDEX_STORY_NUM = 3;

    @GetMapping("test")
    public String test(String test,Integer age){
        System.out.println(test+"53223twe"+age);
        return null;
    }
    @GetMapping("")
    public String index(Model model, HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("user");
        if(user!=null){
            model.addAttribute("user",user);
        }
//        List<Lives_news> list = lives_newsService.getAllLives();
//        model.addAttribute("liveslist", list);
//        List<Lives_news> indexlives = new ArrayList<Lives_news>();
//        for(int i=0;i<INDEX_LIVES_NUM;i++){
//            if(!list.isEmpty() && list.size()>=INDEX_LIVES_NUM){
//                indexlives.add(list.get(i));
//            }
//        }
        List<Lives_news> indexlives = lives_newsService.getLivesByIndex(0,INDEX_LIVES_NUM);
        model.addAttribute("indexlives", indexlives);
        List<Story_news> indexstory = story_newsService.getStoryByIndex(0,INDEX_STORY_NUM);
        model.addAttribute("indexstory", indexstory);
        return "index";
//        List<Story_news> list1 = story_newsService.getAllStory();
//        model.addAttribute("storylist", list1);
//        List<Story_news> indexstory = new ArrayList<Story_news>();
//        for(int i=0;i<INDEX_STORY_NUM;i++){
//            if(!list1.isEmpty() && list1.size()>=INDEX_STORY_NUM){
//                indexstory.add(list1.get(i));
//            }
//        }
    }

    @PostMapping("/login")
    public String login(Model model, HttpSession httpSession
            , Integer user_id, String password) {
        User user = userService.getUserByIdAndPwd(user_id, password);
        if(user!=null){
            model.addAttribute("user",user);
            httpSession.setAttribute("user",user);
        }
        else{
            model.addAttribute("message","登录失败！用户名密码不正确");
        }
        return index(model, httpSession);
    }

    @GetMapping("register")
    public String register(String name, String sex,
                           String user_id, String email,
                           String phone, String password,
                           String check_password, Model model) {
        if (name==null||password==null||user_id==null){
            return "register";
        }
        if (name.equals("")||password.equals("")||user_id.equals("")){
            return "register";
        }
        if (!password.equals(check_password)){
            model.addAttribute("message","两次输入密码不一致");
            return "register";
        }

        Integer uid=Integer.parseInt(user_id);
        User u = userService.getUserById(uid);
        if (u!=null){
            model.addAttribute("message","账号已存在");
            return "register";
        }
        u = new User();
        u.setName(name);
        u.setSex(sex);
        u.setUser_id(uid);
        u.setEmail(email);
        u.setPhone(phone);
        u.setPassword(password);
        userService.addUser(u);
        model.addAttribute("message","注册成功");
        return "register";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession httpSession){
        //清除session数据
        Enumeration<String> enumeration = httpSession.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement().toString();
            httpSession.removeAttribute(key);
        }
        return index(model, httpSession);
    }
}
