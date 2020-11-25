package com.deepblueai.user.service;

import com.deepblueai.user.dao.UserDao;
import com.deepblueai.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 用户的业务类
* */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /*
    * 查询所有用户
    * */
    public List<User> findAll(){
        return  userDao.findAll();
    }

    /*
    * 根据Id查询用户
    * */
    public User findUserById(Integer id){
        return userDao.findById(id).get();
    }

    /*
    * 添加用户
    * */
    public void addUser(User user)
    {
        userDao.save(user);
    }

    /*
    * 修改用户
    * */
    public void update(User user){  //如果用户Id在数据库中不存在，就变成新增了
        userDao.save(user);
    }

    /*
    * 删除用户
    * */
    public void delete(Integer id){
        userDao.deleteById(id);
    }
}
