package com.stonehammer.hammer.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Interest {

    @Id
    @GeneratedValue
    private Integer interest_id;
    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "story_id")
    private Story_news story_news;
    private String user_id;
    private Date interest_time;

    public Integer getInterest_id() {
        return interest_id;
    }

    public void setInterest_id(Integer interest_id) {
        this.interest_id = interest_id;
    }

    public Story_news getStory_news() {
        return story_news;
    }

    public void setStory_news(Story_news story_news) {
        this.story_news = story_news;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getInterest_time() {
        return interest_time;
    }

    public void setInterest_time(Date interest_time) {
        this.interest_time = interest_time;
    }
}
