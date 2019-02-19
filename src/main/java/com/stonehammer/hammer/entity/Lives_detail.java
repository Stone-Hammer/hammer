package com.stonehammer.hammer.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Lives_detail {

    @Id
    @GeneratedValue
    private Integer detail_id;
//    private Integer lives_id;
    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "lives_id")
    private Lives_news lives_news;
    private String name;
    private String icon;
    private String url;
    private String title;
    private String detail_text;
    private Integer words_count;
    private Date time;

    public String getFormatTime() {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    public Integer getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(Integer detail_id) {
        this.detail_id = detail_id;
    }

    public Lives_news getLives_news() {
        return lives_news;
    }

    public void setLives_news(Lives_news lives_news) {
        this.lives_news = lives_news;
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

    public String getDetail_text() {
        return detail_text;
    }

    public void setDetail_text(String detail_text) {
        this.detail_text = detail_text;
    }

    public Integer getWords_count() {
        return words_count;
    }

    public void setWords_count(Integer words_count) {
        this.words_count = words_count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
