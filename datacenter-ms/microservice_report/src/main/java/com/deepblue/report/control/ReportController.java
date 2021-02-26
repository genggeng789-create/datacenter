package com.deepblue.report.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deepblue.report.entity.DatacenterMenu;
import com.deepblue.report.service.DatacenterMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    DatacenterMenuService service;
    //@CrossOrigin                     //解决跨域请求问题
    @GetMapping(value = {"/searchMenu"})
    public JSON getMenuList(@RequestParam("departmentId") String departmentId, @RequestParam("userId") String userId) {
        String result =  service.getMenuDescList(departmentId,userId);
        //System.out.println(result);
        return JSONObject.parseObject(result);
    }

}
