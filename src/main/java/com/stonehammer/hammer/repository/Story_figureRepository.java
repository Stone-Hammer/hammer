package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Story_figure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Story_figureRepository extends JpaRepository<Story_figure,Integer> {
    @Query(value = "select * from story_figure where story_id=?1",nativeQuery = true)
    List<Story_figure> findAllByStory_id(Integer story_id);
    @Modifying
    @Transactional
    @Query("delete from Story_figure f where f.figure_id=?1")
    int deleteByFigure_id(Integer figure_id);

}
