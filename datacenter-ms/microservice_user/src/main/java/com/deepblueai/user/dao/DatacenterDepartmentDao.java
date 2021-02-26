package com.deepblueai.user.dao;

import com.deepblueai.user.entity.DatacenterDepartment;
import com.deepblueai.user.entity.DatacenterMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
* 用户Dao
* */
public interface DatacenterDepartmentDao extends JpaRepository<DatacenterDepartment, Integer> {
    @Query(value="select * from datacenter_department n where department_id=?1 limit 1",nativeQuery=true)
    DatacenterDepartment findByDepartmentId(String departmentId);
}
