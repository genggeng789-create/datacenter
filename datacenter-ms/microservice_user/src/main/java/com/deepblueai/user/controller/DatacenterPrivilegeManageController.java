package com.deepblueai.user.controller;

import com.deepblueai.user.entity.DatacenterMenu;
import com.deepblueai.user.entity.DatacenterPrivilegeManagement;
import com.deepblueai.user.service.DatacenterPrivilegeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/datacenterprivilegemanage")
@RestController
public class DatacenterPrivilegeManageController {

    @Autowired
    DatacenterPrivilegeManageService privilegeManageService;
    /*
    * 查询所有权限
    * */
    @GetMapping(value = {"/queryAll"})
    public List<DatacenterPrivilegeManagement> findAll(){
        return privilegeManageService.findAll();
    }

    /*
     * 根据MenuId查询权限
     * */
    @GetMapping(value = {"/query"})
    public DatacenterPrivilegeManagement findByPrivilegeId(@RequestParam("privilegeId") String privilegeId){
        return privilegeManageService.getFindByPrivilegeId(privilegeId);
    }

     /*
     * 添加权限
     * */
    @GetMapping(value = {"/add"})
    public String add(DatacenterPrivilegeManagement privilegeManagement){
        privilegeManageService.addPrivilege(privilegeManagement);
        return "添加成功";
    }

    /*
    * 修改权限
    * */
     @GetMapping(value = {"/update"})
    public String update(DatacenterPrivilegeManagement privilegeManagement){
         DatacenterPrivilegeManagement privilegeManagement1 = privilegeManageService.getFindByPrivilegeId(privilegeManagement.getPrivilegeId());
         if(privilegeManagement1 ==  null)
         {
             return "修改失败，该权限不存在";
         }
         privilegeManagement1.setUpdatetime(new Timestamp(System.currentTimeMillis()));
         privilegeManageService.update(privilegeManagement1);
        return "修改成功";
    }

    /*
    * 根据privilegeId删除权限 软删除
    * */
    @GetMapping(value = "/delete")
    public String deleteById(@RequestParam("privilegeId") String privilegeId){
        DatacenterPrivilegeManagement privilegeManagement = privilegeManageService.getFindByPrivilegeId(privilegeId);
        privilegeManagement.setValidate(false);
        privilegeManagement.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        System.out.println(privilegeManagement);
        privilegeManageService.update(privilegeManagement);
        return "删除成功";
    }

}
