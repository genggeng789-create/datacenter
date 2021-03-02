package com.deepblue.report.dao;

import com.deepblue.report.entity.DatacenterMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/*
* 用户Dao
* */
public interface DatacenterMenuDao extends JpaRepository<DatacenterMenu, Integer> {
    @Query(value="select d.* from datacenter_user a " +
            "join datacenter_user_role_relation b on a.user_id = b.user_id and b.department_id = ?1  and a.user_id = ?2  " +
            "join datacenter_role_menu_relation c on b.department_id = c.department_id and b.role_id = c.role_id " +
            "join datacenter_menu d on c.menu_id = d.menu_id where a.validate = 1 and b.validate = 1 and c.validate = 1;",nativeQuery=true)
     List<DatacenterMenu> findByMenuListByUserId(@Param("in_department")String departmentId, @Param("in_user_id")String userId);

    @Query(value="select getMenuDesc(?1)", nativeQuery=true)
    String findMenuLink(int id);

    @Query(value="select link_url from datacenter_menu where id = ?1 and type=1",nativeQuery=true)
    String findLinkUrl(int id);
}
