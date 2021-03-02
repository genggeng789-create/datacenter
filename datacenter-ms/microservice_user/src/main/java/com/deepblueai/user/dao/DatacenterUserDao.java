package com.deepblueai.user.dao;

import com.deepblueai.user.entity.DatacenterUser;
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
}
