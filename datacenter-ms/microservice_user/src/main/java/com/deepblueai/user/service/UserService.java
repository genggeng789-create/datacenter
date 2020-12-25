package com.deepblueai.user.service;

import com.deepblueai.user.dao.UserDao;
import com.deepblueai.user.dao.UserRepository;
import com.deepblueai.user.entity.SDKUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 用户的业务类
* */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    /*
    * 查询所有用户
    * */
    public List<SDKUser> findAll(){
        return  userDao.findAll();
    }

    /*
    * 根据Id查询用户
    * */
    public SDKUser findUserById(Integer id){
        return userDao.findById(id).get();
    }

    /*
    * 根据用户名查询用户
    * */
    public SDKUser getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /*
    * 添加用户
    * */
    public void addUser(SDKUser user)
    {
        userDao.save(user);
    }

    /*
    * 修改用户
    * */
    public void update(SDKUser user){  //如果用户Id在数据库中不存在，就变成新增了
        userDao.save(user);
    }

    /*
    * 删除用户
    * */
    public void delete(Integer id){
        userDao.deleteById(id);
    }

    //    @Transactional
//    public void saveAll(List<User> users) {
//        userRepository.saveAll(users);
//    }
//
//    public User getUserByUserName(String userName) {
//        return userRepository.findByUserName(userName);
//    }
//
//    public List<User> getUserByUserNameAndAge(String userName,Integer age){
//        return userRepository.findByUserNameAndAge(userName, age);
//    }
//
//    public List<User> getUserByUserNameLike(String userName){
//        return userRepository.findByUserNameLike(userName);
//    }
}
