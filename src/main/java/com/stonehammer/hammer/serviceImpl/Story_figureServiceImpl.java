package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.Story_figure;
import com.stonehammer.hammer.repository.Story_figureRepository;
import com.stonehammer.hammer.service.Story_figureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Story_figureServiceImpl implements Story_figureService {

    @Autowired
    private Story_figureRepository story_figureRepository;

    @Override
    public Story_figure addFigure(Story_figure story_figure) {
        return story_figureRepository.save(story_figure);
    }

    @Override
    public List<Story_figure> getAllFigure() {
        return story_figureRepository.findAll();
    }

    @Override
    public Story_figure updateFigure(Story_figure story_figure) {
        return story_figureRepository.save(story_figure);
    }

    @Override
    public void deleteFigure(Integer figure_id) {
        story_figureRepository.deleteById(figure_id);
    }

    @Override
    public Story_figure getFigureById(Integer figure_id) {
        return story_figureRepository.findById(figure_id).get();
    }
}
