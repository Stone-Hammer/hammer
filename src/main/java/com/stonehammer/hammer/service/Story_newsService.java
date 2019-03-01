package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Story_news;

import java.util.List;

public interface Story_newsService {

    //添加故事化新闻
    Story_news addStory(Story_news story);

    //返回所有故事化新闻
    List<Story_news> getAllStory();

    //返回一定长度的时事新闻
    List<Story_news> getStoryByIndex(int start_index,int length);

    //返回标题中有words的故事化新闻
    List<Story_news> getStoryByWords(String words);

    //修改故事化新闻
    void updateStoryAdmin(String story_title,String introduction,String tags,Integer story_id);

    //修改故事化新闻
    Story_news updateStory(Story_news story);

    //删除故事化新闻
    void deleteStory(Integer story_id);

    //查询故事化新闻
    Story_news getStoryById(Integer story_id);
}
