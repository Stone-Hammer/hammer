package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Lives_news;

import java.util.List;

public interface Lives_newsService {

    //添加时事新闻
    Lives_news addLives(Lives_news lives);

    //返回所有时事新闻
    List<Lives_news> getAllLives();

    //返回一定长度的时事新闻
    List<Lives_news> getLivesByIndex(int start_index,int length);

    //修改时事新闻
    Lives_news updateLives(Lives_news lives);

    //删除时事新闻
    void deleteLives(Integer lives_id);

    //查询时事新闻
    Lives_news getLivesById(Integer lives_id);
}
