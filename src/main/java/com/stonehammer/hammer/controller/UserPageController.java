package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Lives_news;
import com.stonehammer.hammer.entity.Story_news;
import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.service.Lives_newsService;
import com.stonehammer.hammer.service.Story_newsService;
import com.stonehammer.hammer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("hammer")
public class UserPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private Lives_newsService lives_newsService;
    @Autowired
    private Story_newsService story_newsService;

    //首页展示的时事新闻和故事化新闻的个数
    private static final int INDEX_LIVES_NUM = 3;
    private static final int INDEX_STORY_NUM = 3;

    @GetMapping("")
    public String index(Model model) {
        List<Lives_news> list = lives_newsService.getAllLives();
        model.addAttribute("liveslist", list);
        List<Lives_news> indexlives = new ArrayList<Lives_news>();
        for(int i=0;i<INDEX_LIVES_NUM;i++){
            if(!list.isEmpty() && list.size()>=INDEX_LIVES_NUM){
                indexlives.add(list.get(i));
            }
        }
        model.addAttribute("indexlives", indexlives);

        List<Story_news> list1 = story_newsService.getAllStory();
        model.addAttribute("storylist", list1);
        List<Story_news> indexstory = new ArrayList<Story_news>();
        for(int i=0;i<INDEX_STORY_NUM;i++){
            if(!list1.isEmpty() && list1.size()>=INDEX_STORY_NUM){
                indexstory.add(list1.get(i));
            }

        }
        return "index";
    }

}
