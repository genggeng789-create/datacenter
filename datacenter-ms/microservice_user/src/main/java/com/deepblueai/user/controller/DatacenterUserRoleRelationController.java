package com.deepblueai.user.controller;

import com.deepblueai.user.entity.DatacenterMenu;
import com.deepblueai.user.entity.DatacenterUserRoleRelation;
import com.deepblueai.user.service.DatacenterMenuService;
import com.deepblueai.user.service.DatacenterUserRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/datacenteruserrolerelation")
@RestController
public class DatacenterUserRoleRelationController {

    @Autowired
    DatacenterUserRoleRelationService relationService;
    /*
    * 查询所有角色
    * */
    @GetMapping(value = {"/queryAll"})
    public List<DatacenterUserRoleRelation> findAll(){
        return relationService.findAll();
    }

    /*
     * 根据Id查询用户角色关系
     * */
    @GetMapping(value = {"/query"})
    public DatacenterUserRoleRelation findMenuByMenuId(@RequestParam("userId") String userId, @RequestParam("projectId") String projectId,@RequestParam("roleId") String roleId){
        return relationService.getUserRoleRelation(projectId,userId,roleId);
    }

     /*
     * 添加用户角色关系
     * */
    @GetMapping(value = {"/add"})
    public String add(DatacenterUserRoleRelation relation){
        relationService.addUserRoleRelation(relation);
        return "添加成功";
    }
    /*
    * 修改角色
    * */
     @GetMapping(value = {"/update"})
    public String update(DatacenterUserRoleRelation relation){
         DatacenterUserRoleRelation relation1 = relationService.getUserRoleRelation(relation.getProjectId(),relation.getUserId(),relation.getRoleId());
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
    public String deleteById(@RequestParam("userId") String userId, @RequestParam("projectId") String projectId,@RequestParam("roleId") String roleId){
        DatacenterUserRoleRelation relation = relationService.getUserRoleRelation(projectId,userId,roleId);
        relation.setValidate(false);
        relation.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        System.out.println(relation);
        relationService.update(relation);
        return "删除成功";
    }

}
