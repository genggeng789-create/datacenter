package com.deepblueai.user.service;

import com.deepblueai.user.dao.DatacenterRoleDao;
import com.deepblueai.user.dao.DatacenterUserDao;
import com.deepblueai.user.entity.DatacenterRole;
import com.deepblueai.user.entity.DatacenterUser;
import com.deepblueai.user.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 角色的业务类
* */
@Service
public class DatacenterRoleService {

    @Autowired
    private DatacenterRoleDao datacenterRoleDao;

    /*
    * 查询所有角色
    * */
    public List<DatacenterRole> findAll(){
        return  datacenterRoleDao.findAll();
    }

    /*
    * 根据Id查询角色
    * */
    public DatacenterRole findUserById(Integer id){
        return datacenterRoleDao.findById(id).get();
    }

    /*
     * 根据角色ID查询角色
     * */
    public DatacenterRole getFindByRoleId(String projectId,String roleId) {
        return datacenterRoleDao.findByRoleId(projectId,roleId);
    }
    /*
    * 添加角色
    * */
    public void addRole(DatacenterRole role)
    {
        datacenterRoleDao.save(role);
    }

    /*
    * 修改角色
    * */
    public void update(DatacenterRole role){  //如果角色Id在数据库中不存在，就变成新增了
        datacenterRoleDao.save(role);
    }

    /*
    * 删除角色
    * */
    public void delete(Integer id){
        datacenterRoleDao.deleteById(id);
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
