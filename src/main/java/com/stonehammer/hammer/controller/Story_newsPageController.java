package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Story_news;
import com.stonehammer.hammer.service.Story_newsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("storys")
public class Story_newsPageController {

    @Autowired
    private Story_newsService story_newsService;

    @GetMapping("/all")
    public String getAllStory(Model model){
        List<Story_news> lists=story_newsService.getAllStory();
        model.addAttribute("storys",lists);
        return "index";
    }

    @GetMapping("/add")
    public String add_storys_show(Model model){
        model.addAttribute("story",new Story_news());
        model.addAttribute("cap","添加故事化新闻");
        return "update";
    }

    @PostMapping("/add")
    public String add_storys_submit(Model model, @ModelAttribute Story_news story){
        Story_news newStory=new Story_news();
        newStory.setInterest_count(story.getInterest_count());
        newStory.setIntroduction(story.getIntroduction());
        newStory.setManager(story.getManager());
        newStory.setStory_id(story.getStory_id());
        newStory.setStory_time(story.getStory_time());
        newStory.setStory_title(story.getStory_title());
        newStory.setTags(story.getTags());
        model.addAttribute("story",newStory);
        return "result";
    }

    @GetMapping("/update/{id}")
    public String update_storys_show(Model model, @PathVariable("id") Integer story_id){
        Story_news tmp=story_newsService.getStoryById(story_id);
        model.addAttribute("live",tmp);
        model.addAttribute("cap","修改故事化新闻");
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update_storys_submit(Model model, @PathVariable("id") Integer story_id,Story_news story){
        story.setStory_id(story_id);
        story_newsService.updateStory(story);
        model.addAttribute("story",story);
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteStoryById(Model model,@PathVariable("id") Integer story_id){
        story_newsService.deleteStory(story_id);
        return "delStory";
    }
}
