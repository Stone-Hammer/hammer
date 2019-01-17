package com.stonehammer.hammer.repository;

import com.stonehammer.hammer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.email=?1 and u.password=?2 ")
    User findByEmailAndPassword(String email, String password);

    @Query("select u from User u where u.phone=?1 and u.password=?2 ")
    User findByPhoneAndPassword(String phone, String password);

    @Query("select u from User u where u.user_id=?1 and u.password=?2 ")
    User findByIdAndPassword(Integer user_id, String password);
}
