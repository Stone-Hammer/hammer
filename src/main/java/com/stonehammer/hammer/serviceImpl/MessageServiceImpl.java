package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.Message;
import com.stonehammer.hammer.repository.MessageRepository;
import com.stonehammer.hammer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Override
    public Message addMessage(Message message){
        return messageRepository.save(message);
    }

    @Override
    //返回一个用户的所有消息
    public List<Message> getAllMessage(Integer user_id){
        return messageRepository.findMessagesById(user_id);
    }

    @Override
    //删除消息
    public void deleteMessage(Integer message_id){
        messageRepository.deleteById(message_id);
    }
}
