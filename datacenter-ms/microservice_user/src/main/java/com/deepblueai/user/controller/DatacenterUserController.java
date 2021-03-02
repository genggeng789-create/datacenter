package com.deepblueai.user.controller;

import com.deepblueai.user.entity.DatacenterUser;
import com.deepblueai.user.service.DatacenterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/datacenteruser")
@RestController
public class DatacenterUserController {

    @Autowired
    DatacenterUserService datacenterUserService;
    /*
    * 查询所有用户
    * */
    @GetMapping(value = {"/queryAll"})
    public List<DatacenterUser> findAll(){
        return datacenterUserService.findAll();
    }

    /*
     * 根据userId查询用户
     * */
    @GetMapping(value = "/queryById")
    public DatacenterUser findUserById(@RequestParam("userId") String userId){
        return datacenterUserService.getUserByUserId(userId);
    }

    /*
     * 根据username查询用户
     * */
    @GetMapping(value = "/queryByName")
    public DatacenterUser searchUser(@RequestParam("username") String username){
        //System.out.println(username);
        return datacenterUserService.getUserByUserName(username);
    }

     /*
     * 添加用户
     * */
    @GetMapping(value = {"/add"})
    public String add(DatacenterUser user){
        datacenterUserService.addUser(user);
        return "添加成功";
    }

    /*
    * 修改用户
    * */
    @GetMapping(value = {"/update"})
    public String update(DatacenterUser user){
        DatacenterUser user1 = datacenterUserService.getUserByUserId(user.getUser_id());
        if(user1 ==  null)
        {
            return "修改失败，该角色不存在";
        }
        user1.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        datacenterUserService.update(user);
        return "修改成功";
    }

    /*
    * 根据user_id删除用户 软删除
    * */
    @GetMapping(value = "/delete")
    public String deleteById(@RequestParam("userId") String userId){
        DatacenterUser user = datacenterUserService.getUserByUserId(userId);
        user.setValidate(false);
        user.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        //System.out.println(user);
        datacenterUserService.update(user);
        return "删除成功";
    }

}
