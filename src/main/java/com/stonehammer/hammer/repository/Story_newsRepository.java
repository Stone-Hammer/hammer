package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Story_news;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Story_newsRepository extends JpaRepository<Story_news,Integer> {
    @Query("select n from Story_news n order by story_time desc")
    List<Story_news> findAllOrderByTimeDesc();
    //取出按时间排序的一定长度的新闻
    @Query(value = "select * from (select * from story_news order by story_time desc) as total limit ?1,?2"
            ,nativeQuery = true)
    List<Story_news> findAllOrderByTimeDesc(int start_index,int length);

    @Query(value = "select * from story_news where locate(?1,story_title)>0 order by story_time desc",nativeQuery = true)
    List<Story_news> findStory_newsByWords(String words);
}
