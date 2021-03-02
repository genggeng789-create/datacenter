package com.deepblueai.user.dao;

import com.deepblueai.user.entity.DatacenterRole;
import com.deepblueai.user.entity.DatacenterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
* 用户Dao
* */
public interface DatacenterRoleDao extends JpaRepository<DatacenterRole, Integer> {
    //DatacenterRole findByUsername(String username);
    @Query(value="select * from datacenter_role n where project_id=?1 and role_id=?2 limit 1",nativeQuery=true)
    DatacenterRole findByRoleId(String projectId,String roleId);
    //List<DatacenterRole> findByUsernameLike(String username);
}
