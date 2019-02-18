package com.stonehammer.hammer.repository;

import java.util.List;
import com.stonehammer.hammer.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query("select m from Message m where m.user.user_id=?1 ")
    List<Message> findMessagesById(Integer user_id);
}
