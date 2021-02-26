package com.deepblue.report.service;

import com.deepblue.report.configure.ElasticSearchConfig;
import com.deepblue.report.tools.Tools;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import java.io.IOException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExtractPictureResourceService.class, ElasticSearchConfig.class, Tools.class})
@ActiveProfiles("dev")
public class Test1 {
    @Autowired
    ExtractPictureResourceService epr;

    @Test
    public void test() throws IOException {
        String temp = "/data/bigdata/ftpdata/resultRecord.txt";
        epr.process(1,temp);
        log.info(epr.createMarkStartTime("/20201123标注图片资产化/K12理化试验/检测/氧气/2020-10-30-09-21"));
    }



}
