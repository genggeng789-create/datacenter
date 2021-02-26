package com.deepblueai.user.dao;

import com.deepblueai.user.entity.DatacenterMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
* 用户Dao
* */
public interface DatacenterMenuDao extends JpaRepository<DatacenterMenu, Integer> {
    @Query(value="select * from datacenter_menu n where project_id=?1 and menu_id=?2 limit 1",nativeQuery=true)
    DatacenterMenu findByMenuId(String projectId, String roleId);
}
