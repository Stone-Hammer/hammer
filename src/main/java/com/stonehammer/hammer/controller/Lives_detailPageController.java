package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Lives_detail;
import com.stonehammer.hammer.service.Lives_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("details")
public class Lives_detailPageController {

    @Autowired
    private Lives_detailService lives_detailService;

    @GetMapping("/all")
    public String getAllLives_detail(Model model){
        List<Lives_detail> lists=lives_detailService.getAllLives_detail();
        model.addAttribute("details",lists);
        return "index";
    }

    @GetMapping("/add")
    public String add_detail_show(Model model){
        model.addAttribute("detail",new Lives_detail());
        model.addAttribute("cap","添加新的时事详情");
        return "update";
    }

    @PostMapping("/add")
    public String add_detail_submit(Model model, @ModelAttribute Lives_detail detail){
        Lives_detail newDetail=new Lives_detail();
        newDetail.setDetail_id(detail.getDetail_id());
        newDetail.setDetail_text(detail.getDetail_text());
        newDetail.setIcon(detail.getIcon());
        newDetail.setLives_news(detail.getLives_news());
        newDetail.setName(detail.getName());
        newDetail.setTime(detail.getTime());
        newDetail.setTitle(detail.getTitle());
        newDetail.setUrl(detail.getUrl());
        newDetail.setWords_count(detail.getWords_count());
        model.addAttribute("detail",newDetail);
        return "result";
    }

    @GetMapping("/update/{id}")
    public String update_detail_show(Model model, @PathVariable("id") Integer detail_id){
        Lives_detail tmp=lives_detailService.getDetailById(detail_id);
        model.addAttribute("detail",tmp);
        model.addAttribute("cap","修改时事详情");
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update_detail_submit(Model model,@PathVariable("id") Integer detail_id,Lives_detail detail){
        detail.setDetail_id(detail_id);
        lives_detailService.updateLives_detail(detail);
        model.addAttribute("detail",detail);
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteDetailById(Model model,@PathVariable("id") Integer detail_id){
        lives_detailService.deleteLives_detail(detail_id);
        return "delDetail";
    }
}
