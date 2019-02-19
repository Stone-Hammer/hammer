package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Lives_news;
import com.stonehammer.hammer.service.Lives_newsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("lives")
public class Lives_newsPageController {

    @Autowired
    private Lives_newsService lives_newsService;

    @GetMapping("/info/{id}")
    public String show_lives_info(Model model,@PathVariable("id") Integer lives_id){
        Lives_news lives_news=lives_newsService.getLivesById(lives_id);
        model.addAttribute("lives_news",lives_news);
        return "lives_news";
    }

    @GetMapping("/all")
    public String getAllLives(Model model){
        List<Lives_news> lists=lives_newsService.getAllLives();
        model.addAttribute("lives",lists);
        return "index";
    }

    @GetMapping("/add")
    public String add_lives_show(Model model){
        model.addAttribute("live",new Lives_news());
        model.addAttribute("cap","添加新的时事新闻");
        return "update";
    }

    @PostMapping("/add")
    public String add_lives_submit(Model model, @ModelAttribute Lives_news live){
        Lives_news newLive=new Lives_news();
        newLive.setLives_id(live.getLives_id());
        newLive.setManager(live.getManager());
        newLive.setLives_title(live.getLives_title());
        newLive.setIntroduction(live.getIntroduction());
        newLive.setLives_count(live.getLives_count());
        newLive.setLives_time(live.getLives_time());
        model.addAttribute("live",newLive);
        return "result";
    }

    @GetMapping("/update/{id}")
    public String update_lives_show(Model model,@PathVariable("id") Integer lives_id){
        Lives_news tmp=lives_newsService.getLivesById(lives_id);
        model.addAttribute("live",tmp);
        model.addAttribute("cap","修改时事新闻");
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update_lives_submit(Model model,@PathVariable("id") Integer lives_id,Lives_news live){
        live.setLives_id(lives_id);
        lives_newsService.updateLives(live);
        model.addAttribute("live",live);
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteLivesById(Model model,@PathVariable("id") Integer lives_id){
        lives_newsService.deleteLives(lives_id);
        return "delLive";
    }
}
