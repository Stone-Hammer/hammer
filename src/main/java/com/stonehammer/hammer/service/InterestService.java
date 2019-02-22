package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Interest;

import java.util.List;

public interface InterestService {

    //添加关注
    Interest addInterest(Interest interest);

    //返回所有关注
    List<Interest> getAllInterest();

    //返回指定用户的所有关注
    List<Interest> getAllInterest(Integer user_id);

    //返回指定用户和新闻的关注数据
    Interest getInterestById(Integer user_id,Integer story_id);

    //修改关注
    Interest updateInterest(Interest interest);

    //删除关注
    void deleteInterest(Integer interest_id);

    //查询关注
    Interest getInterestById(Integer interest_id);
}
