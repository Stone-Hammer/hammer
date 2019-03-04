package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Lives_detail;
import com.stonehammer.hammer.entity.Lives_news;
import com.stonehammer.hammer.service.Lives_detailService;
import com.stonehammer.hammer.service.Lives_newsService;
import com.stonehammer.hammer.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class adminLives_newsPageController {

    @Autowired
    private Lives_newsService lives_newsService;
    @Autowired
    private Lives_detailService lives_detailService;
    @Autowired
    private ManagerService  managerService;

    @GetMapping("/lives/all")
    public String getAllLives(Model model){
        List<Lives_news> lists=lives_newsService.getAllLives();
        model.addAttribute("lives",lists);
        return "picture-list";
    }

    @GetMapping("/lives/con/{id}")
    public String getAllDetails(Model model,@PathVariable("id") Integer lives_id){
        List<Lives_detail> lists=lives_detailService.findAllByLives_id(lives_id);
        model.addAttribute("lives_id",lives_id);
        model.addAttribute("details",lists);
        return "picture-con";
    }

    @GetMapping("/info/{id}")
    public String show_lives_info(Model model, HttpSession httpSession
            ,@PathVariable("id") Integer lives_id){
        model.addAttribute("user",httpSession.getAttribute("user"));
        Lives_news lives_news=lives_newsService.getLivesById(lives_id);
        model.addAttribute("lives_news",lives_news);
        return "lives_news";
    }

    @GetMapping("/lives/add")
    public String add_lives_show(Model model){
//        model.addAttribute("manager_id",manager_id);
        Lives_news lives_news=new Lives_news();
        model.addAttribute("live",lives_news);
        model.addAttribute("cap","添加新的时事新闻");
        return "picture-add";
    }

    @PostMapping("/lives/add")
    public String add_lives_submit(Model model, @ModelAttribute Lives_news live){
        Lives_news newLive=new Lives_news();
        newLive.setLives_id(live.getLives_id());
        newLive.setManager(managerService.getManagerById(1));
        newLive.setLives_title(live.getLives_title());
        newLive.setIntroduction(live.getIntroduction());
        newLive.setLives_count(live.getLives_count());
//        newLive.setLives_time(live.getLives_time());
        lives_newsService.addLives(newLive);
        return getAllLives(model);
//        model.addAttribute("live",newLive);
//        return "result";
    }

    @GetMapping("/lives/update/{id}")
    public String update_lives_show(Model model,@PathVariable("id") Integer lives_id){
        model.addAttribute("lives_id",lives_id);
        Lives_news tmp=lives_newsService.getLivesById(lives_id);
        model.addAttribute("live",tmp);
        model.addAttribute("cap","修改时事新闻");
        return "picture-edit";
    }

    @PostMapping("/lives/update/{id}")
    public String update_lives_submit(Model model,@PathVariable("id") Integer lives_id,Lives_news live){
        live.setLives_id(lives_id);
        live.setManager(managerService.getManagerById(1));
        lives_newsService.updateLives(live);
        return getAllLives(model);
//        model.addAttribute("live",live);
//        return "result";
    }

    @GetMapping("/lives/delete/{id}")
    public String deleteLivesById(Model model,@PathVariable("id") Integer lives_id){
        lives_newsService.deleteLives(lives_id);
        return getAllLives(model);
//        return "delLive";
    }

    @GetMapping("/lives/search")
    public String dosearch(Model model,String words){
        model.addAttribute("lives",lives_newsService.getLivesByWords(words));
        return "picture-list";
    }
}
