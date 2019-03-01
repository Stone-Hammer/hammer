package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Source_website;

import java.util.List;

public interface Source_websiteService {

    //添加新闻网
    Source_website addSource_website(Source_website source_website);

    //查询新闻网
    Source_website getSource_websiteByName(String website_name);
}
