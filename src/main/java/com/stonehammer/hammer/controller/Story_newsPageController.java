package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Interest;
import com.stonehammer.hammer.entity.Story_news;
import com.stonehammer.hammer.entity.Story_paragraph;
import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.service.InterestService;
import com.stonehammer.hammer.service.Story_newsService;
import com.stonehammer.hammer.service.Story_paragraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("story")
public class Story_newsPageController {

    @Autowired
    private Story_newsService story_newsService;
    @Autowired
    private Story_paragraphService story_paragraphService;
    @Autowired
    private InterestService interestService;

    private static final int STORY_PER_PAGE = 3;
    @GetMapping("")
    public String show_story_list(Model model, HttpSession httpSession
            ,Integer page){
        model.addAttribute("user",httpSession.getAttribute("user"));
        if (page==null)
            page=1;
        if (page==0){
            return "story";
        }
        List<Story_news> page_story = story_newsService.getStoryByIndex
                ((page-1)*STORY_PER_PAGE,STORY_PER_PAGE);
        if (page_story.isEmpty()){
            page_story = story_newsService.getStoryByIndex
                    ((page-2)*STORY_PER_PAGE,STORY_PER_PAGE);
            model.addAttribute("page_story", page_story);
            model.addAttribute("message", "Sorry~暂无更多新闻");
            model.addAttribute("lastpage",page-2);
            model.addAttribute("nextpage",page);
            return "story";
        }
        model.addAttribute("lastpage",page-1);
        model.addAttribute("nextpage",page+1);
        model.addAttribute("page_story", page_story);
        return "story";
    }

    @GetMapping("/info/{id}")
    public String show_story_info(Model model, HttpSession httpSession
            ,@PathVariable("id") Integer story_id){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user",user);
        Story_news story_news=story_newsService.getStoryById(story_id);
        boolean isInterest = false;
        if (user!=null){
            Interest interest = interestService.getInterestById(user.getUser_id(),story_id);
            if (interest!=null)
                isInterest = true;
        }
        model.addAttribute("isInterest",isInterest);

        List<Story_paragraph> story_paragraphs=story_paragraphService.getAllParagraphByStoryId(story_id);
        model.addAttribute("story_news",story_news);
        model.addAttribute("story_paragraphs",story_paragraphs);
        return "story_news";
    }

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
