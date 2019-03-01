package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Story_figure;
import com.stonehammer.hammer.entity.Story_news;
import com.stonehammer.hammer.entity.Story_paragraph;
import com.stonehammer.hammer.service.ManagerService;
import com.stonehammer.hammer.service.Story_figureService;
import com.stonehammer.hammer.service.Story_newsService;
import com.stonehammer.hammer.service.Story_paragraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class adminStory_newsPageController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private Story_newsService story_newsService;
    @Autowired
    private Story_figureService story_figureService;
    @Autowired
    private Story_paragraphService story_paragraphService;

    @GetMapping("")
    public String index(Model model){
        return "index-1";
    }

    @GetMapping("/storys/con/{id}")
    public String getAllFigureAndPara(Model model, @PathVariable("id") Integer story_id){
        List<Story_figure> lists1=story_figureService.findAllByStory_id(story_id);
        List<Story_paragraph> lists2=story_paragraphService.findParagraphByStory_id(story_id);
        model.addAttribute("story_id",story_id);
        model.addAttribute("figures",lists1);
        model.addAttribute("paras",lists2);
        return "article-con";
    }

    @GetMapping("/storys/all")
    public String getAllStory(Model model){
        List<Story_news> lists=story_newsService.getAllStory();
        model.addAttribute("storys",lists);
        return "article-list";
    }
    @GetMapping("/storys/add")
    public String add_storys_show(Model model){
        Story_news story_news=new Story_news();
        model.addAttribute("story",story_news);
        model.addAttribute("cap","添加故事化新闻");
        return "article-add";
    }

    @PostMapping("/storys/add")
    public String add_storys_submit(Model model,@ModelAttribute Story_news story){
        Story_news newStory=new Story_news();
        newStory.setStory_id(story.getStory_id());
        newStory.setStory_title(story.getStory_title());
        newStory.setIntroduction(story.getIntroduction());
        newStory.setTags(story.getTags());
        newStory.setInterest_count(0);
        newStory.setManager(managerService.getManagerById(1));
        story_newsService.addStory(newStory);
        return getAllStory(model);
    }

//    @PostMapping("/storys/updateoradd/{id}")
//    public String add_storys_submit(Model model, @ModelAttribute Story_news story,
//                                    @PathVariable("id") Integer story_id,
//                                    String story_title, String introduction,
//                                     String tags){
//        Story_news story_news = story_newsService.getStoryById(story_id);
//        if (story_news==null){
//            Story_news newStory=new Story_news();
//            newStory.setInterest_count(0);
//            newStory.setIntroduction(story.getIntroduction());
//            newStory.setManager(managerService.getManagerById(1));
//            newStory.setStory_title(story.getStory_title());
//            newStory.setTags(story.getTags());
//
//            story_newsService.addStory(newStory);
//        }
//        else {
//            story.setStory_id(story_id);
//            story.setStory_title(story_title);
//            story.setIntroduction(introduction);
//            story.setTags(tags);
//            story_newsService.updateStoryAdmin(story_title,introduction,tags,story_id);
//        }
//        return getAllStory(model);
//    }

    @GetMapping("/storys/update/{id}")
    public String update_storys_show(Model model, @PathVariable("id") Integer story_id){
        model.addAttribute("story_id",story_id);
        Story_news tmp=story_newsService.getStoryById(story_id);
        model.addAttribute("story",tmp);
        model.addAttribute("cap","修改故事化新闻");
        return "article-edit";
    }

    @PostMapping("/storys/update/{id}")
    public String update_storys_submit(Model model,@PathVariable("id") Integer story_id,Story_news story){
        story.setStory_id(story_id);
        story.setManager(managerService.getManagerById(1));
        story_newsService.updateStory(story);
        return getAllStory(model);
    }
//    @PostMapping("/storys/update/{id}/{title}/{intro}/{tags}")
//    public String update_storys_submit(Model model,Story_news story, @PathVariable("id") Integer story_id,@PathVariable("title") String story_tilte,@PathVariable("intro") String introducation,@PathVariable("tags") String tags){
//        story.setStory_id(story_id);
//        story.setStory_title(story_tilte);
//        story.setIntroduction(introducation);
//        story.setTags(tags);
//        story_newsService.updateStoryAdmin(story_tilte,introducation,tags,story_id);
//        return getAllStory(model);
//        model.addAttribute("story",story);
//        return "article-list";
//    }

    @GetMapping("/storys/delete/{id}")
    public String deleteStoryById(Model model,@PathVariable("id") Integer story_id){
        story_newsService.deleteStory(story_id);
        return getAllStory(model);
        //return "delStory";
    }
}
