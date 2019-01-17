package com.stonehammer.hammer.service;

import com.stonehammer.hammer.entity.Manager;

import java.util.List;

public interface ManagerService {

    //添加管理员
    Manager addManager(Manager manager);

    //返回所有管理员信息
    List<Manager> getAllManager();

    //修改管理员信息
    Manager updateManager(Manager manager);

    //删除管理员信息
    void deleteManager(Integer manager_id);

    //查询管理员信息
    Manager getManagerById(Integer manager_id);

    //根据管理员ID和密码查询管理员信息
    Manager getManagerByIdAndPwd(Integer manager_id,String password);
}
