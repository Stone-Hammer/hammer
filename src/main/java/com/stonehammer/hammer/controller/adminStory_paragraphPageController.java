package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Source_website;
import com.stonehammer.hammer.entity.Story_paragraph;
import com.stonehammer.hammer.service.Source_websiteService;
import com.stonehammer.hammer.service.Story_newsService;
import com.stonehammer.hammer.service.Story_paragraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("admin")
public class adminStory_paragraphPageController {

    @Autowired
    private Story_paragraphService story_paragraphService;
    @Autowired
    private Story_newsService story_newsService;
    @Autowired
    private Source_websiteService source_websiteService;

    @PostMapping("/paras/upload")
    @ResponseBody
    public String upload(@RequestParam("file_upload") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
//        String fileName = file.getOriginalFilename();
//        String filePath = "/Users/itinypocket/workspace/temp/";
//        File dest = new File(filePath + fileName);
//        try {
//            file.transferTo(dest); LOGGER.info("上传成功"); return "上传成功";
//        } catch (IOException e) {
//            LOGGER.error(e.toString(), e);
//        }
        return "上传失败！";
    }

    @GetMapping("/paras/all")
    public String getAllParagraph(Model model){
        List<Story_paragraph> lists=story_paragraphService.getAllParagraph();
        model.addAttribute("paras",lists);
        return "para-list";
    }

    @GetMapping("/paras/add/{story_id}")
    public String add_paragraph_show(Model model,@PathVariable("story_id") Integer story_id){
        model.addAttribute("story_id",story_id);
        Story_paragraph para=new Story_paragraph();
        model.addAttribute("para",para);
        model.addAttribute("cap","添加故事化段落");
        return "para-add";
    }

    @PostMapping("/paras/add/{story_id}")
    public String add_paragraph_submit(Model model, @ModelAttribute Story_paragraph paragraph,@PathVariable("story_id") Integer story_id){
        Story_paragraph newParagraph=new Story_paragraph();
//        newParagraph.setIcon(paragraph.getIcon());
//        newParagraph.setName(paragraph.getName());
        newParagraph.setSource_website(paragraph.getSource_website());
        newParagraph.setParagraph_id(paragraph.getParagraph_id());
        newParagraph.setParagraph_text(paragraph.getParagraph_text());
        newParagraph.setStory_news(story_newsService.getStoryById(story_id));
//        newParagraph.setTime(paragraph.getTime());
        newParagraph.setTitle(paragraph.getTitle());
        newParagraph.setUrl(paragraph.getUrl());
        story_paragraphService.addParagraph(newParagraph);
        return getAllParagraph(model);
//        model.addAttribute("para",newParagraph);
//        return "result";
    }

    @GetMapping("/paras/update/{id}/{story_id}")
    public String update_paragraph_show(Model model, @PathVariable("id") Integer paragraph_id,@PathVariable("story_id") Integer story_id){
        model.addAttribute("story_id",story_id);
        model.addAttribute("paragraph_id",paragraph_id);
        Story_paragraph tmp=story_paragraphService.getParagraphById(paragraph_id);
        model.addAttribute("para",tmp);
        model.addAttribute("cap","修改故事化段落");
        return "para-edit";
    }

    @PostMapping("/paras/update/{id}/{story_id}")
    public String update_paragraph_submit(Model model, @PathVariable("id") Integer paragraph_id,Story_paragraph paragraph,@PathVariable("story_id") Integer story_id){
        paragraph.setParagraph_id(paragraph_id);
        paragraph.setStory_news(story_newsService.getStoryById(story_id));
        paragraph.setSource_website(source_websiteService.getSource_websiteByName(paragraph.getSource_website().getWebsite_name()));
        story_paragraphService.updateParagraph(paragraph);
        return getAllParagraph(model);
//        model.addAttribute("para",paragraph);
//        return "result";
    }

    @GetMapping("/paras/delete/{id}")
    public String deleteParagraphById(Model model,@PathVariable("id") Integer paragraph_id){
        story_paragraphService.deleteParagraph(paragraph_id);
        return getAllParagraph(model);
    }
}
