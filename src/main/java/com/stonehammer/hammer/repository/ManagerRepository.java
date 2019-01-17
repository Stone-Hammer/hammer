package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    @Query("select m from Manager m where m.manager_id=?1 and m.password=?2 ")
    Manager findByIdAndPassword(Integer manager_id, String password);
}
