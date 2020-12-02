package deepblueai.quximart.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import deepblueai.quximart.entity.ConsumerPurchaseRates;
import deepblueai.quximart.service.MerchantStatistic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Calendar;

@RestController
@RequestMapping("/merchant")
@Slf4j
public class PurchaseRates {
    @Autowired
    MerchantStatistic merchantStatistic;

    @GetMapping(value = {"/rates"})
    public JSON getDeviceStatusJson(ConsumerPurchaseRates model) throws SQLException {
        StringBuffer sb = null;
        try {
            //查询的结果集转换为JSON格式
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(merchantStatistic.QueryMerchantRates(model)));
            //查询的记录总数
            sb = new StringBuffer(JSON.toJSONString(array));
            //拼接为 {total:xx,list:{xxxxx}}格式的结果集
            sb.insert(0,"{DeviceStatusList:").append(",message: null,success:true,time:"+ Calendar.getInstance().getTimeInMillis()+"}");
//        log.info("user name from token {}",SecurityContextUtils.getJWTToken());
//        log.info("user name from token {}",SecurityContextUtils.getUsername());

            return JSONObject.parseObject(sb.toString());

        }catch (Exception e){
            sb = new StringBuffer("[]");
            sb.insert(0,"{DeviceStatusList:").append(",message: \""+e.getMessage()+"\",success:false,time:"+ Calendar.getInstance().getTimeInMillis()+"}");
            return JSONObject.parseObject(sb.toString());
        }
    }
}
