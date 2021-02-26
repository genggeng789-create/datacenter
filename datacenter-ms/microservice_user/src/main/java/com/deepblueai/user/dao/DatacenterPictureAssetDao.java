package com.deepblueai.user.dao;

import com.deepblueai.user.entity.DatacenterPictureAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
* 用户Dao
* */
public interface DatacenterPictureAssetDao extends JpaRepository<DatacenterPictureAsset, Integer> {
    @Query(value="select * from datacenter_picture_asset n where department_id=?1 and package_id=?2 limit 1",nativeQuery=true)
    DatacenterPictureAsset findRelationById(String departmentId,String packageId);
}
