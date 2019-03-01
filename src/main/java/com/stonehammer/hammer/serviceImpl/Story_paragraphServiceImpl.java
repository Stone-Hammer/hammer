package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.Story_paragraph;
import com.stonehammer.hammer.repository.Story_paragraphRepository;
import com.stonehammer.hammer.service.Story_paragraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Story_paragraphServiceImpl implements Story_paragraphService {

    @Autowired
    private Story_paragraphRepository story_paragraphRepository;

    @Override
    public Story_paragraph addParagraph(Story_paragraph story_paragraph) {
        return story_paragraphRepository.save(story_paragraph);
    }

    @Override
    public List<Story_paragraph> getAllParagraph() {
        return story_paragraphRepository.findAll();
    }

    @Override
    public List<Story_paragraph> findParagraphByStory_id(Integer story_id){return story_paragraphRepository.findParagraphByStory_id(story_id);}

    @Override
    public Story_paragraph updateParagraph(Story_paragraph story_paragraph) {
        return story_paragraphRepository.save(story_paragraph);
    }

    @Override
    public void deleteParagraph(Integer paragraph_id) {
//        story_paragraphRepository.deleteById(paragraph_id);
        story_paragraphRepository.deleteByParagraph_id(paragraph_id);
    }

    @Override
    public Story_paragraph getParagraphById(Integer paragraph_id) {
        return story_paragraphRepository.findById(paragraph_id).get();
    }
}
