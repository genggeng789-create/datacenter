package deepblueai.quximart.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import deepblueai.quximart.entity.DashDeviceStatus;
import deepblueai.quximart.service.DeviceStatistic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;

@RestController
@RequestMapping("/device")
@Slf4j
public class DeviceStatus {

    @Autowired
    DeviceStatistic deviceStatistic;

    @GetMapping(value = {"/status"})
    public JSON getDeviceStatusJson(DashDeviceStatus model){
        StringBuffer sb = null;
        try {
            //查询的结果集转换为JSON格式
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(deviceStatistic.QueryDeviceStatus(model)));
            //查询的记录总数
            sb = new StringBuffer(JSON.toJSONString(array));
            //拼接为 {total:xx,list:{xxxxx}}格式的结果集
            sb.insert(0,"{MerchantRatesList:").append(",message: null,success:true,time:"+ Calendar.getInstance().getTimeInMillis()+"}");
//        log.info("user name from token {}",SecurityContextUtils.getJWTToken());
//        log.info("user name from token {}",SecurityContextUtils.getUsername());

            return JSONObject.parseObject(sb.toString());
        }
        catch(Exception e){
            sb = new StringBuffer("[]");
            sb.insert(0,"{DeviceStatusList:").append(",message: \""+e.getMessage()+"\",success:false,time:"+ Calendar.getInstance().getTimeInMillis()+"}");
            return JSONObject.parseObject(sb.toString());
        }
    }
}
