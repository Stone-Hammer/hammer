package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Story_figure;
import com.stonehammer.hammer.entity.Story_news;
import com.stonehammer.hammer.entity.Story_paragraph;
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
public class adminStory_figurePageController {

    @Autowired
    private Story_figureService story_figureService;
    @Autowired
    private Story_newsService story_newsService;
    @Autowired
    private Story_paragraphService story_paragraphService;

    private String article_con(Model model, Integer story_id){
        List<Story_figure> lists1=story_figureService.findAllByStory_id(story_id);
        List<Story_paragraph> lists2=story_paragraphService.findParagraphByStory_id(story_id);
        model.addAttribute("story_id",story_id);
        model.addAttribute("figures",lists1);
        model.addAttribute("paras",lists2);
        return "article-con";
    }

    @GetMapping("/figure/all")
    public String getAllFigures(Model model){
        List<Story_figure> lists=story_figureService.getAllFigure();
        model.addAttribute("figures",lists);
        return "figure-list";
    }

//    @GetMapping("/figure/allby/{id}")
//    public String getAllFiguresById(Model model,@PathVariable("id") Integer story_id){
//        List<Story_figure> lists=story_figureService.findAllByStory_id(story_id);
//        model.addAttribute("figures",lists);
//        return "figure-list";
//    }

    @GetMapping("/figure/add/{id}")
    public String add_figures_show(Model model, @PathVariable("id") Integer story_id){
        model.addAttribute("story_id",story_id);
        Story_figure figure = new Story_figure();
//        Story_news story = new Story_news();
//        story.setStory_id(story_id);
//        figure.setStory_news(story);
        model.addAttribute("figure",figure);
        model.addAttribute("cap","添加新的涉事人物");
        return "figure-add";
    }

    @PostMapping("/figure/add/{story_id}")
    public String add_figures_submit(Model model, @PathVariable("story_id") Integer story_id, @ModelAttribute Story_figure figure){
        Story_figure newFigure=new Story_figure();
        newFigure.setFigure_name(figure.getFigure_name());
//        newFigure.setIcon(figure.getIcon());
        newFigure.setIntroduction(figure.getIntroduction());
        newFigure.setStatus(figure.getStatus());
        newFigure.setStory_news(story_newsService.getStoryById(story_id));
        story_figureService.addFigure(newFigure);
        return article_con(model, story_id);
    }

    @GetMapping("/figure/update/{id}/{story_id}")
    public String update_figure_show(Model model, @PathVariable("id") Integer figure_id,@PathVariable("story_id") Integer story_id){
        model.addAttribute("story_id",story_id);
        model.addAttribute("figure_id",figure_id);
        Story_figure tmp=story_figureService.getFigureById(figure_id);
        model.addAttribute("figure",tmp);
        model.addAttribute("cap","修改涉事人物信息");
        return "figure-edit";
    }

    @PostMapping("/figure/update/{id}/{story_id}")
    public String update_figure_submit(Model model,@PathVariable("id") Integer figure_id, Story_figure figure,@PathVariable("story_id") Integer story_id){
        figure.setFigure_id(figure_id);
        figure.setStory_news(story_newsService.getStoryById(story_id));
        story_figureService.updateFigure(figure);
        return article_con(model, story_id);
//        model.addAttribute("figure",figure);
//        return "result";
    }

    @GetMapping("/figure/delete/{id}")
    public String deleteFigureById(Model model,@PathVariable("id") Integer figure_id){
//        story_newsService.getStoryById(story_id);
        Story_figure story_figure = story_figureService.getFigureById(figure_id);
        Integer story_id = story_figure.getStory_news().getStory_id();
        story_figureService.deleteFigure(figure_id);
        return article_con(model, story_id);
//        return "delFigure";
    }
}

