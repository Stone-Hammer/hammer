package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Interest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InterestService {

    //添加关注
    Interest addInterest(Interest interest);

    //返回所有关注
    List<Interest> getAllInterest();

    //修改关注
    Interest updateInterest(Interest interest);

    //删除关注
    void deleteInterest(Integer interest_id);

    //查询关注
    Interest getInterestById(Integer interest_id);
}
