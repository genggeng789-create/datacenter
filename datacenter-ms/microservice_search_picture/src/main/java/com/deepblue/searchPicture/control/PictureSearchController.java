package com.deepblue.searchPicture.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deepblue.searchPicture.entity.PictureResource;
import com.deepblue.searchPicture.service.BoolQueryPictureService;
import com.deepblue.searchPicture.service.ExtractPictureResourceService;
import com.deepblue.searchPicture.tools.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class PictureSearchController {

    @Autowired
    BoolQueryPictureService boolQueryPictureService;
    @Autowired
    ExtractPictureResourceService extractPictureResourceService;
    //@CrossOrigin                     //解决跨域请求问题
    @GetMapping(value = {"searchRes"})
    public JSON getPictureResourceList1(PictureResource model) {
        //查询的结果集转换为JSON格式
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(boolQueryPictureService.boolQueryPictrue(model)));
        //查询的记录总数
        long total = boolQueryPictureService.getQueryCount(model);
        StringBuffer sb = new StringBuffer(JSON.toJSONString(array));
        //拼接为 {total:xx,list:{xxxxx}}格式的结果集
        sb.insert(0,"{list:").append(",total:" + total +"}");

        log.info("user name from token {}",SecurityContextUtils.getJWTToken());

        log.info("user name from token {}",SecurityContextUtils.getUsername());

        return JSONObject.parseObject(sb.toString());
    }

    @GetMapping(value = {"searchRes1"})
    public List<PictureResource> getPictureResourceList(PictureResource model) {
        return boolQueryPictureService.boolQueryPictrue(model);
    }

    @GetMapping(value = {"fillPictureResource"})
    public String pictureResourceFilled(@RequestParam("lineNumber") int lineNumber)  {
        String temp = "/data/bigdata/ftpdata/resultRecord.txt";
        return "Line"+lineNumber+": " + extractPictureResourceService.process(lineNumber,temp) +" success";
    }

}
