package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Source_website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Source_websiteRepository extends JpaRepository<Source_website,Integer> {
    @Query("select sw from Source_website sw where sw.website_name=?1")
    Source_website findByWebsiteName(String website_name);
}
