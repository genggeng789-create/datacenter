package com.deepblueai.user.service;

import com.deepblueai.user.dao.DatacenterMenuDao;
import com.deepblueai.user.entity.DatacenterMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 菜单的业务类
* */
@Service
public class DatacenterMenuService {

    @Autowired
    private DatacenterMenuDao datacenterMenuDao;

    /*
    * 查询所有菜单
    * */
    public List<DatacenterMenu> findAll(){
        return  datacenterMenuDao.findAll();
    }

    /*
    * 根据Id查询菜单
    * */
    public DatacenterMenu findMenuById(Integer id){
        return datacenterMenuDao.findById(id).get();
    }

    /*
     * 根据菜单ID查询菜单
     * */
    public DatacenterMenu getFindByMenuId(String projectId,String MenuId) {
        return datacenterMenuDao.findByMenuId(projectId,MenuId);
    }
    /*
    * 添加菜单
    * */
    public void addMenu(DatacenterMenu Menu)
    {
        datacenterMenuDao.save(Menu);
    }

    /*
    * 修改菜单
    * */
    public void update(DatacenterMenu Menu){  //如果菜单Id在数据库中不存在，就变成新增了
        datacenterMenuDao.save(Menu);
    }

    /*
    * 删除菜单
    * */
    public void delete(Integer id){
        datacenterMenuDao.deleteById(id);
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
