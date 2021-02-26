package com.deepblueai.user.service;

import com.deepblueai.user.dao.DatacenterPictureAssetDao;
import com.deepblueai.user.entity.DatacenterPictureAsset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* 资产关系的业务类
* */
@Service
public class DatacenterPictureAssetService {

    @Autowired
    private DatacenterPictureAssetDao assetDao;

    /*
    * 查询所有资产关系
    * */
    public List<DatacenterPictureAsset> findAll(){
        return  assetDao.findAll();
    }

    /*
    * 根据Id查询资产关系
    * */
    public DatacenterPictureAsset findDepartmentById(Integer id){
        return assetDao.findById(id).get();
    }

    /*
     * 根据部门和包ID查询资产关系
     * */
    public DatacenterPictureAsset findByDepartmentId(String departmentId, String packageId) {
        return assetDao.findRelationById(departmentId,packageId);
    }
    /*
    * 添加资产关系
    * */
    public void addRelation(DatacenterPictureAsset asset)
    {
        assetDao.save(asset);
    }

    /*
    * 修改资产关系
    * */
    public void update(DatacenterPictureAsset asset){  //如果资产关系Id在数据库中不存在，就变成新增了
        assetDao.save(asset);
    }

    /*
    * 删除资产关系
    * */
    public void delete(Integer id){
        assetDao.deleteById(id);
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
