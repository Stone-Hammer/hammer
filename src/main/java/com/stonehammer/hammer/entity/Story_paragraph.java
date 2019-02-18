package com.stonehammer.hammer.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Story_paragraph {

    @Id
    @GeneratedValue
    private Integer paragraph_id;
    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "story_id")
    private Story_news story_news;
    private String name;
    private String icon;
    private String url;
    private String title;
    private String paragraph_text;
    private Date time;

    public Integer getParagraph_id() {
        return paragraph_id;
    }

    public void setParagraph_id(Integer paragraph_id) {
        this.paragraph_id = paragraph_id;
    }

    public Story_news getStory_news() {
        return story_news;
    }

    public void setStory_news(Story_news story_news) {
        this.story_news = story_news;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParagraph_text() {
        return paragraph_text;
    }

    public void setParagraph_text(String paragraph_text) {
        this.paragraph_text = paragraph_text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
