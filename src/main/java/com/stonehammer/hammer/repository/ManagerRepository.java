package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    @Query("select m from Manager m where m.name=?1 and m.password=?2 ")
    Manager findByNameAndPassword(String name, String password);
}
