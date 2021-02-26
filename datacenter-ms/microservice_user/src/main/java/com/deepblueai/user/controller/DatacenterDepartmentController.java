package com.deepblueai.user.controller;

import com.deepblueai.user.entity.DatacenterDepartment;
import com.deepblueai.user.entity.DatacenterMenu;
import com.deepblueai.user.service.DatacenterDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/datacenterdepartment")
@RestController
public class DatacenterDepartmentController {

    @Autowired
    DatacenterDepartmentService departmentService;
    /*
    * 查询所有角色
    * */
    @GetMapping(value = {"/queryAll"})
    public List<DatacenterDepartment> findAll(){
        return departmentService.findAll();
    }

    /*
     * 根据DepartmentId查询角色
     * */
    @GetMapping(value = {"/query"})
    public DatacenterDepartment findByDepartmentId(@RequestParam("departmentId") String departmentId){
        return departmentService.findByDepartmentId(departmentId);
    }

     /*
     * 添加角色
     * */
    @GetMapping(value = {"/add"})
    public String add(DatacenterDepartment department){
        departmentService.addDepartment(department);
        return "添加成功";
    }

    /*
    * 修改角色
    * */
     @GetMapping(value = {"/update"})
    public String update(DatacenterDepartment department){
         DatacenterDepartment department1 = departmentService.findByDepartmentId(department.getDepartmentId());
         if(department1 ==  null)
         {
             return "修改失败，该角色不存在";
         }
         department1.setUpdatetime(new Timestamp(System.currentTimeMillis()));
         departmentService.update(department1);
        return "修改成功";
    }

    /*
    * 根据MenuId删除角色 软删除
    * */
    @GetMapping(value = "/delete")
    public String deleteById(@RequestParam("departmentId") String departmentId){
        DatacenterDepartment department = departmentService.findByDepartmentId(departmentId);
        department.setValidate(false);
        department.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        System.out.println(department);
        departmentService.update(department);
        return "删除成功";
    }

}
