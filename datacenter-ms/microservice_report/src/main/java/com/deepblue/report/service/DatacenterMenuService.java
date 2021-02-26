package com.deepblue.report.service;

import com.alibaba.fastjson.JSON;
import com.deepblue.report.dao.DatacenterMenuDao;
import com.deepblue.report.entity.DatacenterMenu;
import com.deepblue.report.entity.Menu;
import com.deepblue.report.entity.ReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/*
* 菜单的业务类
* */
@Service
public class DatacenterMenuService {

    @Autowired
    private DatacenterMenuDao datacenterMenuDao;

    @Autowired
    private DatacenterUserService userService;
    /*
    * 查询所有菜单
    * */
    public List<DatacenterMenu> findAll(){
        return  datacenterMenuDao.findAll();
    }

    /*
    * 根据Id查询菜单
    * */
    public DatacenterMenu findMenuById(Integer id){
        return datacenterMenuDao.findById(id).get();
    }

    public List<DatacenterMenu> findMenuList(String departmentId, String userId) {
        return datacenterMenuDao.findByMenuListByUserId(departmentId,userId);
    }

    public String findMenuLink(int id){
        return datacenterMenuDao.findMenuLink(id);
    }

//    public List<String> getMenuDescList(String departmentId, String userId){
//        List<String> list = new ArrayList<String>();
//        List<DatacenterMenu> menuList = findMenuList(departmentId,userId);
//        for(DatacenterMenu menu : menuList){
//            String menuDes = findMenuLink(menu.getId());
//            String linkUrl = datacenterMenuDao.findLinkUrl(menu.getId());
//            list.add("menudesc:{menu:'"+menuDes+"':"+linkUrl+"}");
//        }
//        return list;
//    }

    public String getMenuDescList(String departmentId, String userId){
        ReportResponse rr = new ReportResponse();
        List<DatacenterMenu> menuList = findMenuList(departmentId,userId);
        List<Menu> container = new ArrayList<>();
        for(DatacenterMenu menu : menuList){
            Menu m = new Menu();
            //m.setMenuDesc(findMenuLink(menu.getId()));
            m.setSrc(datacenterMenuDao.findLinkUrl(menu.getId()));
            container.add(m);
        }
        rr.setDepId(departmentId);
        rr.setDepName(userService.getUserByUserId(userId).getDepartmentName());
        rr.setUserName(userService.getUserByUserId(userId).getUsername());
        rr.setRoleName(userService.getUserRole(userId));
        rr.setReport(container);
        rr.setCode(200);
        rr.setUserId(userId);
        rr.setStatus("success");
        return JSON.toJSONString(rr);
    }

    /*
    * 添加菜单
    * */
    public void addMenu(DatacenterMenu Menu)
    {
        datacenterMenuDao.save(Menu);
    }

    /*
    * 修改菜单
    * */
    public void update(DatacenterMenu Menu){  //如果菜单Id在数据库中不存在，就变成新增了
        datacenterMenuDao.save(Menu);
    }

    /*
    * 删除菜单
    * */
    public void delete(Integer id){
        datacenterMenuDao.deleteById(id);
    }
}
