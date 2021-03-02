package com.deepblueai.user.controller;

import com.deepblueai.user.entity.DatacenterRolePrivilegeRelation;
import com.deepblueai.user.entity.DatacenterUserRoleRelation;
import com.deepblueai.user.service.DatacenterRolePrivilegeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/datacenterroleprivilegerelation")
@RestController
public class DatacenterRolePrivilegeRelationController {

    @Autowired
    DatacenterRolePrivilegeRelationService relationService;
    /*
    * 查询所有角色
    * */
    @GetMapping(value = {"/queryAll"})
    public List<DatacenterRolePrivilegeRelation> findAll(){
        return relationService.findAll();
    }

    /*
     * 根据Id查询用户角色关系
     * */
    @GetMapping(value = {"/query"})
    public DatacenterRolePrivilegeRelation findMenuByMenuId(@RequestParam("privilegeId") String privilegeId, @RequestParam("projectId") String projectId, @RequestParam("roleId") String roleId){
        return relationService.getRolePrivilegeRelation(projectId,privilegeId,roleId);
    }

     /*
     * 添加用户角色关系
     * */
    @GetMapping(value = {"/add"})
    public String add(DatacenterRolePrivilegeRelation relation){
        relationService.addRolePrivilegeRelation(relation);
        return "添加成功";
    }
    /*
    * 修改角色
    * */
     @GetMapping(value = {"/update"})
    public String update(DatacenterRolePrivilegeRelation relation){
         DatacenterRolePrivilegeRelation relation1 = relationService.getRolePrivilegeRelation(relation.getProjectId(),relation.getPrivilegeId(),relation.getRoleId());
         if(relation1 ==  null)
         {
             return "修改失败，关系不存在";
         }
         relation1.setUpdatetime(new Timestamp(System.currentTimeMillis()));
         relationService.update(relation1);
        return "修改成功";
    }

    /*
    * 根据MenuId删除角色 软删除
    * */
    @GetMapping(value = "/delete")
    public String deleteById(@RequestParam("privilegeId") String privilegeId, @RequestParam("projectId") String projectId,@RequestParam("roleId") String roleId){
        DatacenterRolePrivilegeRelation relation = relationService.getRolePrivilegeRelation(projectId,privilegeId,roleId);
        relation.setValidate(false);
        relation.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        System.out.println(relation);
        relationService.update(relation);
        return "删除成功";
    }

}
