package com.deepblue.SDKServer.controller;

import com.deepblue.SDKServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;
    /*
     * 添加用户
     * */
    @GetMapping(value = {"/addUser"})
    public String add(@RequestParam("userName") String userName,@RequestParam("password") String password,@RequestParam("departmentId") String departmentId){
        return userService.generateKey(userName,password,departmentId);
    }

    /*
     * 获取用户public key  不做SSO验证
     * */
    @GetMapping(value = {"/selectUser/publicKey/{userName}"})
    public String getUserPublicKey(@PathVariable String userName){
        return userService.queryPublicKey(userName);
    }

    /*
     * 获取用户private key  不做SSO验证
     * */
    @GetMapping(value = {"/selectUser/privateKey/{userName}"})
    public String getUserPrivateKey(@PathVariable String userName){
        return userService.queryPrivateKey(userName);
    }
}
