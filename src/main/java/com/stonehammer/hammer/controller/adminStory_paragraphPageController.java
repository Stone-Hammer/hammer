package com.stonehammer.hammer.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stonehammer.hammer.entity.Source_website;
import com.stonehammer.hammer.entity.Story_figure;
import com.stonehammer.hammer.entity.Story_paragraph;
import com.stonehammer.hammer.service.Source_websiteService;
import com.stonehammer.hammer.service.Story_figureService;
import com.stonehammer.hammer.service.Story_newsService;
import com.stonehammer.hammer.service.Story_paragraphService;
import com.stonehammer.hammer.time.nlp.News;
import com.stonehammer.hammer.time.nlp.TimeNormalizer;
import com.stonehammer.hammer.time.nlp.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin")
public class adminStory_paragraphPageController {

    @Autowired
    public Story_figureService story_figureService;
    @Autowired
    private Story_paragraphService story_paragraphService;
    @Autowired
    private Story_newsService story_newsService;
    @Autowired
    private Source_websiteService source_websiteService;

    private String article_con(Model model, Integer story_id){
        List<Story_figure> lists1=story_figureService.findAllByStory_id(story_id);
        List<Story_paragraph> lists2=story_paragraphService.findParagraphByStory_id(story_id);
        model.addAttribute("story_id",story_id);
        model.addAttribute("figures",lists1);
        model.addAttribute("paras",lists2);
        return "article-con";
    }

    @PostMapping("/paras/upload/{id}")
    public String upload(@RequestParam("file_upload") MultipartFile file,
            @PathVariable("id") Integer story_id, Model model) throws Exception{
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        URL url = TimeNormalizer.class.getResource("/TimeExp.m");
        System.out.println(url.toURI().toString());
        TimeNormalizer normalizer = new TimeNormalizer(url.toURI().toString());
        normalizer.setPreferFuture(false);
//        String fileName = file.getOriginalFilename();
//        String filePath = "/Users/itinypocket/workspace/temp/";
//        File dest = new File(filePath + fileName);
//        try {
//            file.transferTo(dest); LOGGER.info("上传成功"); return "上传成功";
//        } catch (IOException e) {
//            LOGGER.error(e.toString(), e);
//        }
//        File file = new File("F:\\test.json");
//        File f = (File)file;
//        final int bufferSize = 1024;
//        final char[] buffer = new char[bufferSize];
//        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(file.getInputStream(), "UTF-8");
//        for (; ; ) {
//            int rsz = in.read(buffer, 0, buffer.length);
//            if (rsz < 0)
//                break;
//            out.append(buffer, 0, rsz);
//        }
        // 加个判断
//        BufferedReader reader = new BufferedReader(new FileReader(f));
        Gson gson = new GsonBuilder().create();
        News[] news = gson.fromJson(in, News[].class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < news.length; i++) {
            try{
                normalizer.parse(news[i].paragraph_text);// 抽取时间
                TimeUnit[] unit = normalizer.getTimeUnit();
                Date newTime = unit[0].getTime();
//            String newTime = DateUtil.formatDateDefault(unit[0].getTime()) + "-" + unit[0].getIsAllDayTime();
                Story_paragraph story_paragraph = new Story_paragraph();
                story_paragraph.setStory_news(story_newsService.getStoryById(story_id));
                story_paragraph.setSource_website(source_websiteService.getSource_websiteByName("中国新闻网"));
                story_paragraph.setParagraph_text(news[i].paragraph_text);
                story_paragraph.setTitle(news[i].title);
                story_paragraph.setUrl(news[i].url);
                if (newTime.after(sdf.parse(news[i].time)))
                    newTime = sdf.parse(news[i].time);
                story_paragraph.setTime(newTime);

                story_paragraphService.addParagraph(story_paragraph);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }

        }
        return article_con(model, story_id);
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
        return article_con(model, story_id);
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
        return article_con(model, story_id);
//        model.addAttribute("para",paragraph);
//        return "result";
    }

    @GetMapping("/paras/delete/{id}")
    public String deleteParagraphById(Model model,@PathVariable("id") Integer paragraph_id){
        Integer story_id = story_paragraphService.getParagraphById(paragraph_id).getStory_news().getStory_id();
        story_paragraphService.deleteParagraph(paragraph_id);
        return article_con(model, story_id);
    }
}
