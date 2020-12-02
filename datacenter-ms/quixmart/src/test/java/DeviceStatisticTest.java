import deepblueai.quximart.service.DeviceStatistic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeviceStatistic.class)
public class DeviceStatisticTest {

    @Autowired
    private DeviceStatistic ds;
//
//    @Test
//    public void testConnectPresto2(){
//        try {
//            ds.testpresto2();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
