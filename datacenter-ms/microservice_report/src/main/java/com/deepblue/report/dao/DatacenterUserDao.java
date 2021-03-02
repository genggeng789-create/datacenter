package com.deepblue.report.dao;

import com.deepblue.report.entity.DatacenterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
* 用户Dao
* */
public interface DatacenterUserDao extends JpaRepository<DatacenterUser, Integer> {
    DatacenterUser findByUsername(String username);
    @Query(value="select * from datacenter_user n where user_id=?1 limit 1",nativeQuery=true)
    DatacenterUser findByUserId(String userId);
    List<DatacenterUser> findByUsernameLike(String username);
    @Query(value="SELECT b.role_description FROM `datacenter_user_role_relation` a join datacenter_role b on a.department_id = b.department_id and a.role_id = b.role_id where a.user_id =?1 limit 1",nativeQuery=true)
    String findRoleNameByUserId(String userId);
}
