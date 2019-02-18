package com.stonehammer.hammer.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Story_figure {

    @Id
    @GeneratedValue
    private Integer figure_id;
    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "story_id")
    private Story_news story_news;
    private String figure_name;
    private String icon;
    private String introduction;
    private String status;

    public Integer getFigure_id() {
        return figure_id;
    }

    public void setFigure_id(Integer figure_id) {
        this.figure_id = figure_id;
    }

    public Story_news getStory_news() {
        return story_news;
    }

    public void setStory_news(Story_news story_news) {
        this.story_news = story_news;
    }

    public String getFigure_name() {
        return figure_name;
    }

    public void setFigure_name(String figure_name) {
        this.figure_name = figure_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
