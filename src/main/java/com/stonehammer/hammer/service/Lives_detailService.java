package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Lives_detail;

import java.util.List;

public interface Lives_detailService {

    //添加时事详情
    Lives_detail addLives_detail(Lives_detail lives_detail);

    //返回所有时事详情
    List<Lives_detail> getAllLives_detail();

    //返回标题中有words的时事详情新闻
    List<Lives_detail> getLives_detailByWords(String words);

    //修改时事详情
    Lives_detail updateLives_detail(Lives_detail lives_detail);

    //删除时事详情
    void deleteLives_detail(Integer detail_id);

    //查询时事详情
    Lives_detail getDetailById(Integer detail_id);
}
