package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.Source_website;
import com.stonehammer.hammer.repository.Source_websiteRepository;
import com.stonehammer.hammer.service.Source_websiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Source_websiteServiceImpl implements Source_websiteService {

    @Autowired
    private Source_websiteRepository source_websiteRepository;

    @Override
    public Source_website addSource_website(Source_website source_website) {
        return source_websiteRepository.save(source_website);
    }

    @Override
    public Source_website getSource_websiteByName(String website_name) {
        return source_websiteRepository.findByWebsiteName(website_name);
    }
}
