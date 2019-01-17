package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.User;

import java.util.List;

public interface UserService {

    //添加用户
    User addUser(User user);

    //返回所有用户信息
    List<User> getAllUser();

    //修改用户信息
    User updateUser(User user);

    //删除用户信息
    void deleteUser(Integer user_id);

    //查询用户信息
    User getUserById(Integer user_id);

    //根据邮箱和密码查询用户信息
    User getUserByEmailAndPwd(String email,String password);

    //根据手机号和密码查询用户信息
    User getUserByPhoneAndPwd(String phone,String password);

    //根据用户ID和密码查询用户信息
    User getUserByIdAndPwd(Integer user_id,String password);
}

