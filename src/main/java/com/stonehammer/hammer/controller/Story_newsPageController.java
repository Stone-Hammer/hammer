package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Story_news;
import com.stonehammer.hammer.service.ManagerService;
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
    private ManagerService managerService;
    @Autowired
    private Story_newsService story_newsService;

    @GetMapping("")
    public String index(Model model){
        return "index-1";
    }

    @GetMapping("/all")
    public String getAllStory(Model model){
        List<Story_news> lists=story_newsService.getAllStory();
        model.addAttribute("storys",lists);
        return "article-list";
    }

    @GetMapping("/add")
    public String add_storys_show(Model model){
        model.addAttribute("story",new Story_news());
        model.addAttribute("cap","添加故事化新闻");
        return "article-add";
    }

    @PostMapping("/add")
    public String add_storys_submit(Model model, @ModelAttribute Story_news story){
        Story_news newStory=new Story_news();
        newStory.setInterest_count(0);
        newStory.setIntroduction(story.getIntroduction());
        newStory.setManager(managerService.getManagerById(1));
        newStory.setStory_title(story.getStory_title());
        newStory.setTags(story.getTags());
        story_newsService.addStory(newStory);
        return getAllStory(model);
//        model.addAttribute("story",newStory);
//        return "article-list";
    }

    @GetMapping("/update/{id}")
    public String update_storys_show(Model model, @PathVariable("id") Integer story_id){
        Story_news tmp=story_newsService.getStoryById(story_id);
        model.addAttribute("story",tmp);
        model.addAttribute("cap","修改故事化新闻");
        return "article-add";
    }

    @PostMapping("/update/{id}")
    public String update_storys_submit(Model model, @PathVariable("id") Integer story_id,Story_news story){
        story.setStory_id(story_id);
        story_newsService.updateStory(story);
        return getAllStory(model);
//        model.addAttribute("story",story);
//        return "article-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteStoryById(Model model,@PathVariable("id") Integer story_id){
        story_newsService.deleteStory(story_id);
        return getAllStory(model);
        //return "delStory";
    }
}
