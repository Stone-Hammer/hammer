package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.Lives_detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Lives_detailRepository extends JpaRepository<Lives_detail,Integer> {
    @Query(value = "select * from lives_detail where lives_id=?1",nativeQuery = true)
    List<Lives_detail> findAllByLives_id(Integer lives_id);
    @Modifying
    @Transactional
    @Query("delete from Lives_detail d where d.detail_id=?1")
    int deleteByDetail_id(Integer detail_id);
}
