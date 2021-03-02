package com.deepblueai.user.controller;

import com.deepblueai.user.entity.DatacenterRole;
import com.deepblueai.user.service.DatacenterRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/datacenterrole")
@RestController
public class DatacenterRoleController {

    @Autowired
    DatacenterRoleService datacenterRoleService;
    /*
    * 查询所有角色
    * */
    @GetMapping(value = {"/queryAll"})
    public List<DatacenterRole> findAll(){
        return datacenterRoleService.findAll();
    }

    /*
     * 根据roleId查询角色
     * */
    @GetMapping(value = {"/query"})
    public DatacenterRole findRoleByRoleId(@RequestParam("roleId") String roleId, @RequestParam("projectId") String projectId){
        return datacenterRoleService.getFindByRoleId(projectId,roleId);
    }

     /*
     * 添加角色
     * */
    @GetMapping(value = {"/add"})
    public String add(DatacenterRole role){
        datacenterRoleService.addRole(role);
        return "添加成功";
    }
    /*
    * 修改角色
    * */
     @GetMapping(value = {"/update"})
    public String update(DatacenterRole role){
         DatacenterRole role1 = datacenterRoleService.getFindByRoleId(role.getProjectId(),role.getRoleId());
         if(role1 ==  null)
         {
             return "修改失败，该角色不存在";
         }
         role1.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        datacenterRoleService.update(role1);
        return "修改成功";
    }

    /*
    * 根据roleId删除角色 软删除
    * */
    @GetMapping(value = "/delete")
    public String deleteById(@RequestParam("roleId") String roleId, @RequestParam("projectId") String projectId){
        DatacenterRole role = datacenterRoleService.getFindByRoleId(projectId,roleId);
        role.setValidate(false);
        role.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        System.out.println(role);
        datacenterRoleService.update(role);
        return "删除成功";
    }

}
