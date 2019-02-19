package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Story_paragraph;
import com.stonehammer.hammer.service.Story_paragraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("paras")
public class Story_paragraphPageControlller {

    @Autowired
    private Story_paragraphService story_paragraphService;

    @GetMapping("/all")
    public String getAllParagraph(Model model){
        List<Story_paragraph> lists=story_paragraphService.getAllParagraph();
        model.addAttribute("paras",lists);
        return "index";
    }

    @GetMapping("/add")
    public String add_paragraph_show(Model model){
        model.addAttribute("para",new Story_paragraph());
        model.addAttribute("cap","添加故事化段落");
        return "update";
    }

    @PostMapping("/add")
    public String add_paragraph_submit(Model model, @ModelAttribute Story_paragraph paragraph){
        Story_paragraph newParagraph=new Story_paragraph();
        newParagraph.setIcon(paragraph.getIcon());
        newParagraph.setName(paragraph.getName());
        newParagraph.setParagraph_id(paragraph.getParagraph_id());
        newParagraph.setParagraph_text(paragraph.getParagraph_text());
        newParagraph.setStory_news(paragraph.getStory_news());
        newParagraph.setTime(paragraph.getTime());
        newParagraph.setTitle(paragraph.getTitle());
        newParagraph.setUrl(paragraph.getUrl());
        model.addAttribute("para",newParagraph);
        return "result";
    }

    @GetMapping("/update/{id}")
    public String update_paragraph_show(Model model, @PathVariable("id") Integer paragraph_id){
        Story_paragraph tmp=story_paragraphService.getParagraphById(paragraph_id);
        model.addAttribute("para",tmp);
        model.addAttribute("cap","修改故事化段落");
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update_paragraph_submit(Model model, @PathVariable("id") Integer paragraph_id,Story_paragraph paragraph){
        paragraph.setParagraph_id(paragraph_id);
        story_paragraphService.updateParagraph(paragraph);
        model.addAttribute("para",paragraph);
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteParagraphById(Model model,@PathVariable("id") Integer paragraph_id){
        story_paragraphService.deleteParagraph(paragraph_id);
        return "delPara";
    }
}
