package com.deepblueai.user.dao;

import com.deepblueai.user.entity.DatacenterMenu;
import com.deepblueai.user.entity.DatacenterPrivilegeManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
* 用户Dao
* */
public interface DatacenterPrivilegeDao extends JpaRepository<DatacenterPrivilegeManagement, Integer> {
    @Query(value="select * from datacenter_privilege_manage n where privilege_id=?1 limit 1",nativeQuery=true)
    DatacenterPrivilegeManagement findByPrivilegeId(String privilegeId);
}
