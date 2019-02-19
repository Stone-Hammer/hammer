package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Story_news;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Story_newsService {

    //添加故事化新闻
    Story_news addStory(Story_news story);

    //返回所有故事化新闻
    List<Story_news> getAllStory();

    //修改故事化新闻
    Story_news updateStory(Story_news story);

    //删除故事化新闻
    void deleteStory(Integer story_id);

    //查询故事化新闻
    Story_news getStoryById(Integer story_id);
}
