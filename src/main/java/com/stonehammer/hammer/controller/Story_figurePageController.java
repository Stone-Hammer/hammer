package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Story_figure;
import com.stonehammer.hammer.service.Story_figureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("figures")
public class Story_figurePageController {

    @Autowired
    private Story_figureService story_figureService;

    @GetMapping("/all")
    public String getAllFigures(Model model){
        List<Story_figure> lists=story_figureService.getAllFigure();
        model.addAttribute("figures",lists);
        return "index";
    }

    @GetMapping("/add")
    public String add_figures_show(Model model){
        model.addAttribute("figure",new Story_figure());
        model.addAttribute("cap","添加新的涉事人物");
        return "update";
    }

    @PostMapping("/add")
    public String add_figures_submit(Model model, @ModelAttribute Story_figure figure){
        Story_figure newFigure=new Story_figure();
        newFigure.setFigure_id(figure.getFigure_id());
        newFigure.setFigure_name(figure.getFigure_name());
        newFigure.setIcon(figure.getIcon());
        newFigure.setIntroduction(figure.getIntroduction());
        newFigure.setStatus(figure.getStatus());
        newFigure.setStory_news(figure.getStory_news());
        model.addAttribute("figure",newFigure);
        return "result";
    }

    @GetMapping("/update/{id}")
    public String update_figure_show(Model model, @PathVariable("id") Integer figure_id){
        Story_figure tmp=story_figureService.getFigureById(figure_id);
        model.addAttribute("figure",tmp);
        model.addAttribute("cap","修改涉事人物信息");
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update_figure_submit(Model model,@PathVariable("id") Integer figure_id,Story_figure figure){
        figure.setFigure_id(figure_id);
        story_figureService.updateFigure(figure);
        model.addAttribute("figure",figure);
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteFigureById(Model model,@PathVariable("id") Integer figure_id){
        story_figureService.deleteFigure(figure_id);
        return "delFigure";
    }
}
