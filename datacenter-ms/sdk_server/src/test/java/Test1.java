import com.deepblue.SDKServer.configure.CommonConfigure;
import com.deepblue.SDKServer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserService.class, RestTemplate.class, CommonConfigure.class})
@ActiveProfiles("dev")
public class Test1 {
    @Autowired
    UserService userService;

    @Test
    public void test() throws IOException {
        log.info(userService.generateKey("peter","123456","bigdata"));
    }
}
