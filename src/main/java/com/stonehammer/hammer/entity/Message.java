package com.stonehammer.hammer.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private Integer message_id;
    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private String text;
    private Date time;

    public String getFormatTime() {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
