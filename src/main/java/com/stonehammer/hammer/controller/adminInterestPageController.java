package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Interest;
import com.stonehammer.hammer.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class adminInterestPageController {

    @Autowired
    private InterestService interestService;

    @GetMapping("/interest/all")
    public String getAllInterest(Model model){
        List<Interest> lists=interestService.getAllInterest();
        model.addAttribute("interests",lists);
        return "interest-list";
    }

    @GetMapping("/interest/add")
    public String add_interest_show(Model model){
        model.addAttribute("interest",new Interest());
        model.addAttribute("cap","添加关注");
        return "interest-add";
    }

    @PostMapping("/interest/add")
    public  String add_interest_submit(Model model, @ModelAttribute Interest interest){
        Interest newInterest=new Interest();
        newInterest.setInterest_id(interest.getInterest_id());
        newInterest.setInterest_time(interest.getInterest_time());
        newInterest.setStory_news(interest.getStory_news());
//        newInterest.setUser_id(interest.getUser_id());
        interestService.addInterest(interest);
        return getAllInterest(model);
//        model.addAttribute("interest",newInterest);
//        return "result";
    }

    @GetMapping("/interest/update/{id}")
    public String update_interest_show(Model model, @PathVariable("id") Integer interest_id){
        Interest tmp=interestService.getInterestById(interest_id);
        model.addAttribute("interest",tmp);
        model.addAttribute("cap","修改关注");
        return "interest-add";
    }

    @PostMapping("/interest/update/{id}")
    public String update_interest_submit(Model model, @PathVariable("id") Integer interest_id,Interest interest){
        interest.setInterest_id(interest_id);
        interestService.updateInterest(interest);
        return getAllInterest(model);
//        model.addAttribute("interest",interest);
//        return "result";
    }

    @GetMapping("/interest/delete/{id}")
    public String deleteInterestById(Model model,@PathVariable("id") Integer interest_id){
        interestService.deleteInterest(interest_id);
        return getAllInterest(model);
    }
}
