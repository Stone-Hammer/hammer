package com.stonehammer.hammer.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Interest {

    @Id
    @GeneratedValue
    private Integer interest_id;
    private Integer story_id;
    private String user_id;
    private Date interest_time;

    public Integer getInterest_id() {
        return interest_id;
    }

    public void setInterest_id(Integer interest_id) {
        this.interest_id = interest_id;
    }

    public Integer getStory_id() {
        return story_id;
    }

    public void setStory_id(Integer story_id) {
        this.story_id = story_id;
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
