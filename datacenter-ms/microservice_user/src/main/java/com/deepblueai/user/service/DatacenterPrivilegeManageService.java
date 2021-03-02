package com.deepblueai.user.service;

import com.deepblueai.user.dao.DatacenterPrivilegeDao;
import com.deepblueai.user.entity.DatacenterMenu;
import com.deepblueai.user.entity.DatacenterPrivilegeManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 权限的业务类
* */
@Service
public class DatacenterPrivilegeManageService {

    @Autowired
    private DatacenterPrivilegeDao datacenterPrivilegeDao;

    /*
    * 查询所有权限
    * */
    public List<DatacenterPrivilegeManagement> findAll(){
        return  datacenterPrivilegeDao.findAll();
    }

    /*
    * 根据Id查询权限
    * */
    public DatacenterPrivilegeManagement findMenuById(Integer id){
        return datacenterPrivilegeDao.findById(id).get();
    }

    /*
     * 根据权限ID查询权限
     * */
    public DatacenterPrivilegeManagement getFindByPrivilegeId(String privilegeId) {
        return datacenterPrivilegeDao.findByPrivilegeId(privilegeId);
    }
    /*
    * 添加权限
    * */
    public void addPrivilege(DatacenterPrivilegeManagement privilegeManagement)
    {
        datacenterPrivilegeDao.save(privilegeManagement);
    }

    /*
    * 修改权限
    * */
    public void update(DatacenterPrivilegeManagement privilegeManagement){  //如果权限Id在数据库中不存在，就变成新增了
        datacenterPrivilegeDao.save(privilegeManagement);
    }

    /*
    * 删除权限
    * */
    public void delete(Integer id){
        datacenterPrivilegeDao.deleteById(id);
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
