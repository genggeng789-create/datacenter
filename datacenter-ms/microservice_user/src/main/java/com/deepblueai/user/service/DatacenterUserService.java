package com.deepblueai.user.service;

import com.deepblueai.user.dao.DatacenterUserDao;
import com.deepblueai.user.entity.DatacenterUser;
import com.deepblueai.user.entity.SDKUser;
import com.deepblueai.user.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 用户的业务类
* */
@Service
public class DatacenterUserService {

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private DatacenterUserDao datacenterUserDao;

    /*
    * 查询所有用户
    * */
    public List<DatacenterUser> findAll(){
        return  datacenterUserDao.findAll();
    }

    /*
    * 根据Id查询用户
    * */
    public DatacenterUser findUserById(Integer id){
        return datacenterUserDao.findById(id).get();
    }

    /*
    * 根据用户名查询用户
    * */
    public DatacenterUser getUserByUserName(String userName) {
        return datacenterUserDao.findByUsername(userName);
    }

    /*
     * 根据用户ID查询用户
     * */
    public DatacenterUser getUserByUserId(String userId) {
        return datacenterUserDao.findByUserId(userId);
    }
    /*
    * 添加用户
    * */
    public void addUser(DatacenterUser user)
    {
        user.setPassword(Tools.MD5(user.getPassword()));
        datacenterUserDao.save(user);
    }

    /*
    * 修改用户
    * */
    public void update(DatacenterUser user){  //如果用户Id在数据库中不存在，就变成新增了
        datacenterUserDao.save(user);
    }

    /*
    * 删除用户
    * */
    public void delete(Integer id){
        datacenterUserDao.deleteById(id);
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
