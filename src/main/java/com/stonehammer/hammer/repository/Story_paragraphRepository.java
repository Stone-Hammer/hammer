package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Story_figure;
import com.stonehammer.hammer.entity.Story_paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Story_paragraphRepository extends JpaRepository<Story_paragraph,Integer> {
    @Query(value = "select * from story_paragraph where story_id=?1",nativeQuery = true)
    List<Story_paragraph> findParagraphByStory_id(Integer story_id);
    @Modifying
    @Transactional
    @Query("delete from Story_paragraph p where p.paragraph_id=?1")
    int deleteByParagraph_id(Integer paragraph_id);
}
