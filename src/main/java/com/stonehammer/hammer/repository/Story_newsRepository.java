package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Story_news;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Story_newsRepository extends JpaRepository<Story_news,Integer> {
    @Query("select n from Story_news n order by story_time desc")
    List<Story_news> findAllOrderByTimeDesc();

    @Modifying
    @Query(value = "update story_news set story_title=?1,introduction=?2,tags=?3 where story_id=?4 ", nativeQuery = true)
    int updateStory_newsById(String story_title,String introduction,String tags,Integer story_id);

}
