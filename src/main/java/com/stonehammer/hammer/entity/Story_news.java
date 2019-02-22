package com.stonehammer.hammer.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="story_news", schema = "hammer")
public class Story_news {

    @Id
    @GeneratedValue
    private Integer story_id;
    private String story_title;
    private String introduction;
    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private Manager manager;
    private String tags;
    private Integer interest_count;
    private Date story_time;

    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private List<Story_paragraph> paragraphs;

    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "story_id")
    private List<Story_figure> figures;

    public String getFormatTime() {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(story_time);
    }

    public List<Story_figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Story_figure> figures) {
        this.figures = figures;
    }

    public Integer getStory_id() {
        return story_id;
    }

    public void setStory_id(Integer story_id) {
        this.story_id = story_id;
    }

    public String getStory_title() {
        return story_title;
    }

    public void setStory_title(String story_title) {
        this.story_title = story_title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getInterest_count() {
        return interest_count;
    }

    public void setInterest_count(Integer interest_count) {
        this.interest_count = interest_count;
    }

    public Date getStory_time() {
        return story_time;
    }

    public void setStory_time(Date story_time) {
        this.story_time = story_time;
    }
}
