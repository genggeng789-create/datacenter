package com.deepblueai.user.controller;

import com.deepblueai.user.entity.DatacenterPictureAsset;
import com.deepblueai.user.service.DatacenterPictureAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/datacenterpictureasset")
@RestController
public class DatacenterPictureAssetController {

    @Autowired
    DatacenterPictureAssetService assetService;
    /*
    * 查询所有关系
    * */
    @GetMapping(value = {"/queryAll"})
    public List<DatacenterPictureAsset> findAll(){
        return assetService.findAll();
    }

    /*
     * 根据DepartmentId和packageId查询关系是否存在
     * */
    @GetMapping(value = {"/query"})
    public DatacenterPictureAsset findByDepartmentId(@RequestParam("departmentId") String departmentId, @RequestParam("packageId") String packageId){
        return assetService.findByDepartmentId(departmentId,packageId);
    }

     /*
     * 添加关系
     * */
    @GetMapping(value = {"/add"})
    public String add(DatacenterPictureAsset asset){
        assetService.addRelation(asset);
        return "添加成功";
    }

    /*
    * 修改关系
    * */
     @GetMapping(value = {"/update"})
    public String update(DatacenterPictureAsset asset){
         DatacenterPictureAsset asset1 = assetService.findByDepartmentId(asset.getDepartmentId(),asset.getProjectId());
         if(asset1 ==  null)
         {
             return "修改失败，该关系不存在";
         }
         asset1.setUpdatetime(new Timestamp(System.currentTimeMillis()));
         assetService.update(asset1);
        return "修改成功";
    }

    /*
    * 根据MenuId删除关系 软删除
    * */
    @GetMapping(value = "/delete")
    public String deleteById(@RequestParam("departmentId") String departmentId,@RequestParam("packageId") String packageId){
        DatacenterPictureAsset asset = assetService.findByDepartmentId(departmentId,packageId);
        asset.setValidate(false);
        asset.setUpdatetime(new Timestamp(System.currentTimeMillis()));
        System.out.println(asset);
        assetService.update(asset);
        return "删除成功";
    }

}
