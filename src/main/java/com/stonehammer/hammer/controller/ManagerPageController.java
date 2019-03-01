package com.stonehammer.hammer.controller;

import com.stonehammer.hammer.entity.Manager;
import com.stonehammer.hammer.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerPageController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/all")
    public String getAllManager(Model model){
        List<Manager> lists=managerService.getAllManager();
        model.addAttribute("manager",lists);
        return "index";
    }

    @GetMapping("/add")
    public String add_mananger_show(Model model){
        model.addAttribute("mana",new Manager());
        model.addAttribute("cap","添加新的管理员");
        return "update";
    }

    @PostMapping("/add")
    public String add_manager_submit(Model model,@ModelAttribute Manager manager){
        Manager newManager=new Manager();
        newManager.setManager_id(manager.getManager_id());
        newManager.setName(manager.getName());
        newManager.setPassword(manager.getPassword());
        model.addAttribute("mana",newManager);
        return "result";
    }

    @GetMapping("/update/{id}")
    public String update_manager_show(Model model,@PathVariable("id") Integer manager_id){
        Manager tmp=managerService.getManagerById(manager_id);
        model.addAttribute("mana",tmp);
        model.addAttribute("cap","修改管理员信息");
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update_manager_submit(Model model,@PathVariable("id") Integer manager_id,Manager manager){
        manager.setManager_id(manager_id);
        managerService.updateManager(manager);
        model.addAttribute("mana",manager);
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteManagerById(Model model,@PathVariable("id") Integer manager_id){
        managerService.deleteManager(manager_id);
        return "delManager";
    }
}
