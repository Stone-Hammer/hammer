package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.Story_news;
import com.stonehammer.hammer.repository.Story_newsRepository;
import com.stonehammer.hammer.service.Story_newsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Story_newsServiceImpl implements Story_newsService {

    @Autowired
    private Story_newsRepository story_newsRepository;

    @Override
    public Story_news addStory(Story_news story) {
        return story_newsRepository.save(story);
    }

    @Override
    public List<Story_news> getAllStory() {
        return story_newsRepository.findAll();
    }

    @Override
    public Story_news updateStory(Story_news story) {
        return story_newsRepository.save(story);
    }

    @Override
    public void deleteStory(Integer story_id) {
        story_newsRepository.deleteById(story_id);
    }

    @Override
    public Story_news getStoryById(Integer story_id) {
        return story_newsRepository.findById(story_id).get();
    }
}
