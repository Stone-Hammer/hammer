package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Lives_news;
import com.stonehammer.hammer.service.Lives_newsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Lives")
public class Lives_newsController {
    @Autowired
    private Lives_newsService lives_newsService;

    @PostMapping("/add")
    private Lives_news addLives(Lives_news lives){ return lives_newsService.addLives(lives); }

    @GetMapping("/all")
    private List<Lives_news> getAllLives(){ return lives_newsService.getAllLives(); }

    @GetMapping("/getLivesById/{lives_id}")
    private Lives_news getLivesById(@PathVariable("lives_id") Integer lives_id){ return lives_newsService.getLivesById(lives_id); }

    @PutMapping("/updateLives")
    private Lives_news updateLives(Lives_news lives){ return lives_newsService.updateLives(lives); }

    @DeleteMapping("/deleteLives/{lives_id}")
    private void deleteLives(@PathVariable("lives_id") Integer lives_id){ lives_newsService.deleteLives(lives_id);}
}
