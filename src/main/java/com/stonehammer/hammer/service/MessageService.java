package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Message;

import java.util.List;

public interface MessageService {

    //添加消息
    Message addMessage(Message message);

    //返回一个用户的所有消息
    List<Message> getAllMessage(Integer user_id);

    //删除消息
    void deleteMessage(Integer message_id);

    //查询消息
}
