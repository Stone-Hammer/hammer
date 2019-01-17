package com.stonehammer.hammer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Lives_news {

    @Id
    @GeneratedValue
    private Integer lives_id;
    private String lives_title;
    private String introduction;
    private Integer manager_id;
    private Integer lives_count;
    private Date lives_time;

    public Integer getLives_id() {
        return lives_id;
    }

    public void setLives_id(Integer lives_id) {
        this.lives_id = lives_id;
    }

    public String getLives_title() {
        return lives_title;
    }

    public void setLives_title(String lives_title) {
        this.lives_title = lives_title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public Integer getLives_count() {
        return lives_count;
    }

    public void setLives_count(Integer lives_count) {
        this.lives_count = lives_count;
    }

    public Date getLives_time() {
        return lives_time;
    }

    public void setLives_time(Date lives_time) {
        this.lives_time = lives_time;
    }
}
