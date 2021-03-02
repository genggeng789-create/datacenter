package com.deepblue.report.service;

import com.deepblue.report.entity.DatacenterMenu;
import com.deepblue.report.tools.Tools;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DatacenterMenuService.class,Tools.class})
@ActiveProfiles("dev")
public class Test1 {
    @Autowired
    DatacenterMenuService service;

    @Test
    public void test() throws IOException {
//        List<String> list = service.findMenuList("datacenter","liuhb");
//        for(String menu : list){
//            System.out.println(menu);
//        }
    }



}
