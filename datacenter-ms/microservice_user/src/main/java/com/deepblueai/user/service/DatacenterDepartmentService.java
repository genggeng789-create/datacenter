package com.deepblueai.user.service;

import com.deepblueai.user.dao.DatacenterDepartmentDao;
import com.deepblueai.user.entity.DatacenterDepartment;
import com.deepblueai.user.entity.DatacenterMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 部门的业务类
* */
@Service
public class DatacenterDepartmentService {

    @Autowired
    private DatacenterDepartmentDao departmentDao;

    /*
    * 查询所有部门
    * */
    public List<DatacenterDepartment> findAll(){
        return  departmentDao.findAll();
    }

    /*
    * 根据Id查询部门
    * */
    public DatacenterDepartment findDepartmentById(Integer id){
        return departmentDao.findById(id).get();
    }

    /*
     * 根据部门ID查询部门
     * */
    public DatacenterDepartment findByDepartmentId(String departmentId) {
        return departmentDao.findByDepartmentId(departmentId);
    }
    /*
    * 添加部门
    * */
    public void addDepartment(DatacenterDepartment department)
    {
        departmentDao.save(department);
    }

    /*
    * 修改部门
    * */
    public void update(DatacenterDepartment department){  //如果部门Id在数据库中不存在，就变成新增了
        departmentDao.save(department);
    }

    /*
    * 删除部门
    * */
    public void delete(Integer id){
        departmentDao.deleteById(id);
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
