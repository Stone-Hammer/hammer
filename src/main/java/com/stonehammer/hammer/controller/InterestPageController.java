package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Interest;
import com.stonehammer.hammer.entity.Story_news;
import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.service.InterestService;
import com.stonehammer.hammer.service.Story_newsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("interest")
public class InterestPageController {

    @Autowired
    private InterestService interestService;
    @Autowired
    private Story_newsService story_newsService;

    private boolean validate(User user){
        if (user==null||user.getUser_id()==null||
                user.getName()==null||user.getName().equals("")||
                user.getPassword()==null||user.getPassword().equals("")){
            return false;
        }
        return true;
    }

    @GetMapping("/user")
    public String show_interest_list(Model model, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (!validate(user)){
            model.addAttribute("message", "登录失效 请重新登录~");
            return "hammer";
        }

        model.addAttribute("user",user);
        List<Interest> interests = interestService.getAllInterest(user.getUser_id());
        if (interests.isEmpty()){
            model.addAttribute("message", "您还没有关注新闻哦~");
            return "interest";
        }
        model.addAttribute("interests", interests);
        return "interest";
    }
    @GetMapping("/all")
    public String getAllInterest(Model model){
        List<Interest> lists=interestService.getAllInterest();
        model.addAttribute("interests",lists);
        return "index";
    }

    @GetMapping("/add/{id}")
    public String addInterest(Model model, HttpSession httpSession,
                               @PathVariable("id") Integer story_id){
        User user = (User) httpSession.getAttribute("user");
        if (!validate(user)){
            model.addAttribute("message", "登录失效 请重新登录~");
            return "hammer";
        }
        model.addAttribute("user",user);
        Interest interest = new Interest();
        interest.setUser(user);
        Story_news story_news = story_newsService.getStoryById(story_id);
        if (story_news==null){
            model.addAttribute("message", "错误，关注失败！");
            model.addAttribute("story_news", story_news);
            model.addAttribute("isInterest",false);
            return "story_news";
        }
        if(interestService.getInterestById(user.getUser_id(),story_id)!=null){
            model.addAttribute("message", "您已关注该新闻！");
            model.addAttribute("story_news", story_news);
            model.addAttribute("isInterest",true);
            return "story_news";
        }
        interest.setStory_news(story_news);
        interestService.addInterest(interest);
        model.addAttribute("message", "关注成功！");
        model.addAttribute("story_news", story_news);
        model.addAttribute("isInterest",true);
        return "story_news";
    }

    @GetMapping("/delete/{id}")
    public String deleteInterest(Model model,HttpSession httpSession,
                                 @PathVariable("id") Integer story_id){
        User user = (User) httpSession.getAttribute("user");
        if (!validate(user)){
            model.addAttribute("message", "登录失效 请重新登录~");
            return "hammer";
        }
        model.addAttribute("user",user);
        Interest interest= interestService.getInterestById(user.getUser_id(),story_id);
//        System.out.println(interest+" "+interest.getInterest_id());
        interestService.deleteInterest(interest.getInterest_id());
        model.addAttribute("story_news", story_newsService.getStoryById(story_id));
        model.addAttribute("isInterest",false);
        return "story_news";
    }

    @PostMapping("/add")
    public  String add_interest_submit(Model model, @ModelAttribute Interest interest){
        Interest newInterest=new Interest();
        newInterest.setInterest_id(interest.getInterest_id());
        newInterest.setInterest_time(interest.getInterest_time());
        newInterest.setStory_news(interest.getStory_news());
        newInterest.setUser(interest.getUser());
        model.addAttribute("interest",newInterest);
        return "result";
    }

    @GetMapping("/update/{id}")
    public String update_interest_show(Model model, @PathVariable("id") Integer interest_id){
        Interest tmp=interestService.getInterestById(interest_id);
        model.addAttribute("interest",tmp);
        model.addAttribute("cap","修改关注");
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update_interest_submit(Model model, @PathVariable("id") Integer interest_id,Interest interest){
        interest.setInterest_id(interest_id);
        interestService.updateInterest(interest);
        model.addAttribute("interest",interest);
        return "result";
    }

}
