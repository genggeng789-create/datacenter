package deepblueai.quximart.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import deepblueai.quximart.entity.DashDeviceStatus;
import deepblueai.quximart.service.DeviceStatistic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/device")
@Slf4j
public class DeviceStatus {

    @Autowired
    DeviceStatistic deviceStatistic;

    @GetMapping(value = {"/status"})
    public JSON getDeviceStatusJson(DashDeviceStatus model) throws SQLException {
        //查询的结果集转换为JSON格式
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(deviceStatistic.QueryDeviceStatus(model)));
        //查询的记录总数
//        long total = boolQueryPictureService.getQueryCount(model);
        //StringBuffer sb = new StringBuffer(deviceStatistic.QueryDeviceStatus(model));
        StringBuffer sb = new StringBuffer(JSON.toJSONString(array));

        //log.info(sb.toString());
        //拼接为 {total:xx,list:{xxxxx}}格式的结果集
        sb.insert(0,"{DeviceStatusList:").append(",message: null,success:true,time:"+ Calendar.getInstance().getTimeInMillis()+"}");
//
//        log.info("user name from token {}",SecurityContextUtils.getJWTToken());
//
//        log.info("user name from token {}",SecurityContextUtils.getUsername());



        return JSONObject.parseObject(sb.toString());
    }

    @GetMapping(value = {"/status1"})
    public String getDeviceStatusJson1(DashDeviceStatus model) {
        //查询的结果集转换为JSON格式
        String test = null;
        try {
            test =  JSON.toJSONString(deviceStatistic.QueryDeviceStatus(model));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //查询的记录总数
//        long total = boolQueryPictureService.getQueryCount(model);
        //拼接为 {total:xx,list:{xxxxx}}格式的结果集
//        sb.insert(0,"{list:").append(",total:" + total +"}");
//
//        log.info("user name from token {}",SecurityContextUtils.getJWTToken());
//
//        log.info("user name from token {}",SecurityContextUtils.getUsername());


        return test;
    }

    @GetMapping(value = {"searchRes1"})
    public List<DashDeviceStatus> getPictureResourceList(DashDeviceStatus model) throws SQLException {
        return deviceStatistic.QueryDeviceStatus(model);
    }

}
