package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Lives_news;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Lives_newsRepository extends JpaRepository<Lives_news,Integer> {
    @Query("select n from Lives_news n order by lives_time desc")
    List<Lives_news> findAllOrderByTimeDesc();
    //取出按时间排序的一定长度的新闻
    @Query(value = "select * from (select * from lives_news order by lives_time desc) as total limit ?1,?2"
            ,nativeQuery = true)
    List<Lives_news> findAllOrderByTimeDesc(int start_index,int length);

    @Query(value = "select * from lives_news where locate(?1,lives_title)>0 order by lives_time desc",nativeQuery = true)
    List<Lives_news> findLives_newsByWords(String words);



}
