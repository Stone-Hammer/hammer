package com.stonehammer.hammer.entity;

import javax.persistence.*;

@Entity
@Table(name="source_website", schema = "hammer")
public class Source_website {

    @Id
    @GeneratedValue
    private Integer website_id;
    private String website_name;
    private String website_icon;

    public Integer getWebsite_id() {
        return website_id;
    }

    public void setWebsite_id(Integer website_id) {
        this.website_id = website_id;
    }

    public String getWebsite_name() {
        return website_name;
    }

    public void setWebsite_name(String website_name) {
        this.website_name = website_name;
    }

    public String getWebsite_icon() {
        return website_icon;
    }

    public void setWebsite_icon(String website_icon) {
        this.website_icon = website_icon;
    }
}
