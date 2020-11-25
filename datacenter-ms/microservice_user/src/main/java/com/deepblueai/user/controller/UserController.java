package com.deepblueai.user.controller;

import com.deepblueai.user.entity.User;
import com.deepblueai.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;
    /*
    * 查询所有用户
    * */
    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll(){
//        List<User> list = new ArrayList<>();
//        list.add(new User(1,"张三","123456","男",1000.0));
//        list.add(new User(2,"李四","123456","女",2000.0));
//        list.add(new User(3,"王五","123456","男",2500.0));
//        return list;
        return userService.findAll();
    }

    /*
     * 根据Id查询用户
     * */
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public User findUserById(@PathVariable Integer id){
        System.out.println("用户微服务111111");
        return userService.findUserById(id);
    }

    /*
    * 添加用户
    * */
    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody User user){
           userService.addUser(user);
           return "添加成功";
    }

    /*
    * 修改用户
    * */
    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public String update(@RequestBody User user,@PathVariable Integer id){
        user.setId(id);
        userService.update(user);
        return "修改成功";
    }

    /*
    * 根据Id删除用户
    * */
    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public String deleteById(@PathVariable Integer id){
        userService.delete(id);
        return "删除成功";
    }
}
