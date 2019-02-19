package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Lives_news;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Lives_newsRepository extends JpaRepository<Lives_news,Integer> {
    @Query("select n from Lives_news n order by lives_time desc")
    List<Lives_news> findAllOrderByTimeDesc();

}
