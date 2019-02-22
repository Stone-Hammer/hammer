package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Story_paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Story_paragraphRepository extends JpaRepository<Story_paragraph,Integer> {
    @Query("select p from Story_paragraph p where p.story_news.story_id=?1 order by time asc")
    List<Story_paragraph> findParagraphByStoryId(Integer story_id);
}
