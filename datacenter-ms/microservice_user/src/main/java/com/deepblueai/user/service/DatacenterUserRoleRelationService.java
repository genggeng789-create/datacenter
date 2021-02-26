package com.deepblueai.user.service;

import com.deepblueai.user.dao.DatacenterUserRoleRelationDao;
import com.deepblueai.user.entity.DatacenterUserRoleRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 关系的业务类
* */
@Service
public class DatacenterUserRoleRelationService {

    @Autowired
    private DatacenterUserRoleRelationDao datacenterUserRoleRelationDao;

    /*
    * 查询所有关系
    * */
    public List<DatacenterUserRoleRelation> findAll(){
        return  datacenterUserRoleRelationDao.findAll();
    }

    /*
    * 根据Id查询关系
    * */
    public DatacenterUserRoleRelation findRelationById(Integer id){
        return datacenterUserRoleRelationDao.findById(id).get();
    }

    /*
     * 根据关系ID查询关系
     * */
    public DatacenterUserRoleRelation getUserRoleRelation(String projectId,String userId,String roleId) {
        return datacenterUserRoleRelationDao.findByUserRoleId(projectId,userId,roleId);
    }
    /*
    * 添加关系
    * */
    public void addUserRoleRelation(DatacenterUserRoleRelation datacenterUserRoleRelation)
    {
        datacenterUserRoleRelationDao.save(datacenterUserRoleRelation);
    }

    /*
    * 修改关系
    * */
    public void update(DatacenterUserRoleRelation relation){  //如果关系Id在数据库中不存在，就变成新增了
        datacenterUserRoleRelationDao.save(relation);
    }

    /*
    * 删除关系
    * */
    public void delete(Integer id){
        datacenterUserRoleRelationDao.deleteById(id);
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
