package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.Manager;
import com.stonehammer.hammer.repository.ManagerRepository;
import com.stonehammer.hammer.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Manager addManager(Manager manager){ return managerRepository.save(manager); }

    @Override
    public List<Manager> getAllManager(){ return managerRepository.findAll(); }

    @Override
    public Manager updateManager(Manager manager){ return managerRepository.save(manager); }

    @Override
    public void deleteManager(Integer manager_id){ managerRepository.deleteById(manager_id); }

    @Override
    public Manager getManagerById(Integer manager_id){ return managerRepository.findById(manager_id).get(); }

    @Override
    public Manager getManagerByIdAndPwd(Integer manager_id,String password){ return managerRepository.findByIdAndPassword(manager_id,password); }
}
