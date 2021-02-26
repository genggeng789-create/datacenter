package com.deepblueai.user.service;

import com.deepblueai.user.dao.DatacenterRolePrivilegeRelationDao;
import com.deepblueai.user.entity.DatacenterRolePrivilegeRelation;
import com.deepblueai.user.entity.DatacenterUserRoleRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 关系的业务类
* */
@Service
public class DatacenterRolePrivilegeRelationService {

    @Autowired
    private DatacenterRolePrivilegeRelationDao relationService;

    /*
    * 查询所有关系
    * */
    public List<DatacenterRolePrivilegeRelation> findAll(){
        return  relationService.findAll();
    }

    /*
    * 根据Id查询关系
    * */
    public DatacenterRolePrivilegeRelation findRelationById(Integer id){
        return relationService.findById(id).get();
    }

    /*
     * 根据关系ID查询关系
     * */
    public DatacenterRolePrivilegeRelation getRolePrivilegeRelation(String projectId, String privilegeId, String roleId) {
        return relationService.findRelationById(projectId,privilegeId,roleId);
    }

    /*
    * 添加关系
    * */
    public void addRolePrivilegeRelation(DatacenterRolePrivilegeRelation relation)
    {
        relationService.save(relation);
    }

    /*
    * 修改关系
    * */
    public void update(DatacenterRolePrivilegeRelation relation){  //如果关系Id在数据库中不存在，就变成新增了
        relationService.save(relation);
    }

    /*
    * 删除关系
    * */
    public void delete(Integer id){
        relationService.deleteById(id);
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
