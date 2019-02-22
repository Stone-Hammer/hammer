package com.stonehammer.hammer.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Interest {

    @Id
    @GeneratedValue
    private Integer interest_id;
    @ManyToOne(cascade= CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name = "story_id")
    private Story_news story_news;
    @ManyToOne(cascade= CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getInterest_time() {
        return interest_time;
    }

    public void setInterest_time(Date interest_time) {
        this.interest_time = interest_time;
    }
}
