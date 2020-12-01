package deepblueai.quximart.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import deepblueai.quximart.entity.ConsumerPurchaseRates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
@Slf4j
public class DeviceStatus {


//    @GetMapping(value = {"searchRes"})
//    public JSON getPictureResourceList1(ConsumerPurchaseRates model) {
//        //查询的结果集转换为JSON格式
//        JSONArray array = JSONArray.parseArray(JSON.toJSONString(boolQueryPictureService.boolQueryPictrue(model)));
//        //查询的记录总数
//        long total = boolQueryPictureService.getQueryCount(model);
//        StringBuffer sb = new StringBuffer(JSON.toJSONString(array));
//        //拼接为 {total:xx,list:{xxxxx}}格式的结果集
//        sb.insert(0,"{list:").append(",total:" + total +"}");
//
//        log.info("user name from token {}",SecurityContextUtils.getJWTToken());
//
//        log.info("user name from token {}",SecurityContextUtils.getUsername());
//
//
//
//        return JSONObject.parseObject(sb.toString());
//    }

}
