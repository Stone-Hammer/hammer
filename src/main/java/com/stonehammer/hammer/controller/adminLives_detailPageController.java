package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Lives_detail;
import com.stonehammer.hammer.service.Lives_detailService;
import com.stonehammer.hammer.service.Lives_newsService;
import com.stonehammer.hammer.service.Source_websiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class adminLives_detailPageController {

    @Autowired
    private Lives_detailService lives_detailService;
    @Autowired
    private Lives_newsService lives_newsService;
    @Autowired
    private Source_websiteService source_websiteService;

    @GetMapping("/details/all")
    public String getAllLives_detail(Model model){
        List<Lives_detail> lists=lives_detailService.getAllLives_detail();
        model.addAttribute("details",lists);
        return "detail-list";
    }

    @GetMapping("/details/add/{id}")
    public String add_detail_show(Model model,@PathVariable("id") Integer lives_id){
        model.addAttribute("lives_id",lives_id);
        Lives_detail detail=new Lives_detail();
        model.addAttribute("detail",detail);
        model.addAttribute("cap","添加新的时事详情");
        return "detail-add";
    }

    @PostMapping("/details/add/{id}")
    public String add_detail_submit(Model model, @ModelAttribute Lives_detail detail,@PathVariable("id") Integer lives_id){
        Lives_detail newDetail=new Lives_detail();
//        newDetail.setDetail_id(detail.getDetail_id());
        newDetail.setDetail_text(detail.getDetail_text());
//        newDetail.setIcon(detail.getIcon());
        newDetail.setLives_news(lives_newsService.getLivesById(lives_id));
//        newDetail.setName(detail.getName());
        newDetail.setSource_website(detail.getSource_website());
        newDetail.setTime(detail.getTime());
        newDetail.setTitle(detail.getTitle());
        newDetail.setUrl(detail.getUrl());
        newDetail.setWords_count(detail.getWords_count());
        lives_detailService.addLives_detail(newDetail);
        return getAllLives_detail(model);
//        model.addAttribute("detail",newDetail);
//        return "result";
    }

    @GetMapping("/details/update/{id}/{lives_id}")
    public String update_detail_show(Model model, @PathVariable("id") Integer detail_id,@PathVariable("lives_id") Integer lives_id){
        model.addAttribute("detail_id",detail_id);
        model.addAttribute("lives_id",lives_id);
        Lives_detail tmp=lives_detailService.getDetailById(detail_id);
        model.addAttribute("detail",tmp);
        model.addAttribute("cap","修改时事详情");
        return "detail-edit";
    }

    @PostMapping("/details/update/{id}/{lives_id}")
    public String update_detail_submit(Model model,@PathVariable("id") Integer detail_id,Lives_detail detail,@PathVariable("lives_id") Integer lives_id){
        detail.setDetail_id(detail_id);
        detail.setLives_news(lives_newsService.getLivesById(lives_id));
        detail.setSource_website(source_websiteService.getSource_websiteByName(detail.getSource_website().getWebsite_name()));
        lives_detailService.updateLives_detail(detail);
        return getAllLives_detail(model);
//        model.addAttribute("detail",detail);
//        return "result";
    }

    @GetMapping("/details/delete/{id}")
    public String deleteDetailById(Model model,@PathVariable("id") Integer detail_id){
        lives_detailService.deleteLives_detail(detail_id);
        return getAllLives_detail(model);
    }
}
