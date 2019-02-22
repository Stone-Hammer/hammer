package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestRepository extends JpaRepository<Interest,Integer> {
    @Query("select i from Interest i where i.user.user_id=?1 order by interest_time desc")
//    @Query(value = "select * from interest where user_id=?1 order by interest_time desc"
//            ,nativeQuery = true)
    List<Interest> findInterestById(Integer user_id);

    @Query("select i from Interest i where i.user.user_id=?1 and i.story_news.story_id=?2")
//    @Query(value = "select * from interest where user_id=?1 order by interest_time desc"
//            ,nativeQuery = true)
    Interest findInterestById(Integer user_id, Integer story_id);
}
