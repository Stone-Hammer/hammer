package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.User;
import com.stonehammer.hammer.repository.UserRepository;
import com.stonehammer.hammer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user){ return userRepository.save(user); }

    @Override
    public List<User> getAllUser(){ return userRepository.findAll(); }

    @Override
    public User updateUser(User user){ return userRepository.save(user); }

    @Override
    public void deleteUser(Integer user_id){ userRepository.deleteById(user_id);}

    @Override
    public User getUserById(Integer user_id){ return userRepository.findById(user_id).get();}

    @Override
    public User getUserByEmailAndPwd(String email,String password){ return userRepository.findByEmailAndPassword(email, password);}

    @Override
    public User getUserByPhoneAndPwd(String phone,String password){ return userRepository.findByPhoneAndPassword(phone, password);}

    @Override
    public User getUserByIdAndPwd(Integer manager_id,String password){ return userRepository.findByIdAndPassword(manager_id, password);}
}
