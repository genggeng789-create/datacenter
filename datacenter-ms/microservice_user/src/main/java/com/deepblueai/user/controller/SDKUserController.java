package com.deepblueai.user.controller;

import com.deepblueai.user.entity.SDKUser;
import com.deepblueai.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RequestMapping("/user")
@RestController
public class SDKUserController {

    @Autowired
    UserService userService;
    /*
    * 查询所有用户
    * */
    @RequestMapping(method = RequestMethod.GET)
    public List<SDKUser> findAll(){
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
//    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
//    public SDKUser findUserById(@PathVariable Integer id){
//        System.out.println("用户微服务111111");
//        return userService.findUserById(id);
//    }

    /*
     * 根据Id查询用户
     * */
//    @RequestMapping(method = RequestMethod.GET,value = "/{userName}")
//    public SDKUser findUserByUserName(@PathVariable String userName){
//        System.out.println("用户微服务userName");
//        return userService.getUserByUserName(userName);
//    }


     /*
     * 添加用户
     * */
    @GetMapping(value = {"/addUser"})
    public String add(SDKUser user){
        try {
            user.setPrivate_key(java.net.URLDecoder.decode(user.getPrivate_key(),"UTF-8"));
            user.setPublic_key(java.net.URLDecoder.decode(user.getPublic_key(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        userService.addUser(user);
        return "添加成功";
    }
    /*
    * 修改用户
    * */
    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public String update(@RequestBody SDKUser user,@PathVariable Integer id){
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

    /*
     * 根据用户名称查询用户public key
     * */
//    @GetMapping(value = {"/selectUser"})
//    public String searchUser(SDKUser user){
//        String userName = null;
//        String public_key = null;
//        try {
//            userName = java.net.URLDecoder.decode(user.getUserName(),"UTF-8");
//            public_key = java.net.URLEncoder.encode(userService.getUserByUserName(userName).getPublic_key(),"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return public_key;
//    }
    @GetMapping(value = "/selectUser/{userName}")
    public SDKUser searchUser(@PathVariable String userName){
        System.out.println(userName);
        return userService.getUserByUserName(userName);
    }
}
