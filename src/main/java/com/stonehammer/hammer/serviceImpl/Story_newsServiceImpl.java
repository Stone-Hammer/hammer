package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.Story_news;
import com.stonehammer.hammer.repository.Story_newsRepository;
import com.stonehammer.hammer.service.Story_newsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Story_newsServiceImpl implements Story_newsService {

    @Autowired
    private Story_newsRepository story_newsRepository;

    @Override
    public Story_news addStory(Story_news story) {
        return story_newsRepository.save(story);
    }

    @Override
    public List<Story_news> getAllStory() {
//        return story_newsRepository.findAll();
        return story_newsRepository.findAllOrderByTimeDesc();
    }

    @Override
    public List<Story_news> getStoryByIndex(int start_index, int length) {
        return story_newsRepository.findAllOrderByTimeDesc(start_index, length);
    }

    @Override
    public List<Story_news> getStoryByWords(String words) {
        return story_newsRepository.findStory_newsByWords(words);
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
