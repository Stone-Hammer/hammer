package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Lives_detail;
import com.stonehammer.hammer.entity.Story_paragraph;

import java.util.List;

public interface Story_paragraphService {

    //添加故事化段落
    Story_paragraph addParagraph(Story_paragraph story_paragraph);

    //返回所有故事化段落
    List<Story_paragraph> getAllParagraph();

    //根据外键返回相应段落
    List<Story_paragraph> findParagraphByStory_id(Integer story_id);
    //返回指定故事化新闻的所有段落
    List<Story_paragraph> getAllParagraphByStoryId(Integer story_id);

    //修改故事化段落
    Story_paragraph updateParagraph(Story_paragraph story_paragraph);

    //删除故事化段落
    void deleteParagraph(Integer paragraph_id);

    //查询故事化段落
    Story_paragraph getParagraphById(Integer paragraph_id);
}
