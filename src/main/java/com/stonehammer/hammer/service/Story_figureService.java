package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Story_figure;

import java.util.List;

public interface Story_figureService {

    //添加涉事人物
    Story_figure addFigure(Story_figure story_figure);

    //返回所有涉事人物
    List<Story_figure> getAllFigure();

    //修改涉事人物
    Story_figure updateFigure(Story_figure story_figure);

    //删除涉事人物
    void deleteFigure(Integer figure_id);

    //查询涉事人物
    Story_figure getFigureById(Integer figure_id);
}
