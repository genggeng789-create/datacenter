package com.deepblueai.user.controller;

import com.deepblueai.user.entity.DatacenterMenu;
import com.deepblueai.user.service.DatacenterMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/datacentermenu")
@RestController
public class DatacenterMenuController {

    @Autowired
    DatacenterMenuService datacenterMenuService;
    /*
    * 查询所有角色
    * */
    @GetMapping(value = {"/queryAll"})
    public List<DatacenterMenu> findAll(){
        return datacenterMenuService.findAll();
    }

    /*
     * 根据MenuId查询角色
     * */
    @GetMapping(value = {"/query"})
    public DatacenterMenu findMenuByMenuId(@RequestParam("menuId") String menuId, @RequestParam("projectId") String projectId){
        return datacenterMenuService.getFindByMenuId(projectId,menuId);
    }

     /*
     * 添加角色
     * */
    @GetMapping(value = {"/add"})
    public String add(DatacenterMenu menu){
        datacenterMenuService.addMenu(menu);
        return "添加成功";
    }

    /*
    * 修改角色
    * */
     @GetMapping(value = {"/update"})
    public String update(DatacenterMenu menu){
         DatacenterMenu menu1 = datacenterMenuService.getFindByMenuId(menu.getLinkUrl(),menu.getMenuId());
         if(menu1 ==  null)
         {
             return "修改失败，该角色不存在";
         }
         menu1.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        datacenterMenuService.update(menu1);
        return "修改成功";
    }

    /*
    * 根据MenuId删除角色 软删除
    * */
    @GetMapping(value = "/delete")
    public String deleteById(@RequestParam("menuId") String menuId, @RequestParam("projectId") String projectId){
        DatacenterMenu menu = datacenterMenuService.getFindByMenuId(projectId,menuId);
        menu.setValidate(false);
        menu.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        System.out.println(menu);
        datacenterMenuService.update(menu);
        return "删除成功";
    }

}
