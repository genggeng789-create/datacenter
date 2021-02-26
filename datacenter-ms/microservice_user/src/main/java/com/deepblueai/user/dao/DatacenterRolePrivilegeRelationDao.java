package com.deepblueai.user.dao;

import com.deepblueai.user.entity.DatacenterRolePrivilegeRelation;
import com.deepblueai.user.entity.DatacenterUserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
* 用户Dao
* */
public interface DatacenterRolePrivilegeRelationDao extends JpaRepository<DatacenterRolePrivilegeRelation, Integer> {
    @Query(value="select * from datacenter_user_role_relation n where project_id=?1 and privilege_id=?2 and role_id=?3 limit 1",nativeQuery=true)
    DatacenterRolePrivilegeRelation findRelationById(String projectId, String privilegeId, String roleId);
}
