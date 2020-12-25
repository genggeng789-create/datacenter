package com.deepblue.SDKServer.service;

import com.deepblue.SDKServer.entity.SDKUser;
import com.deepblue.SDKServer.tools.CreateSecrteKey;
import com.deepblue.SDKServer.tools.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class UserService {
    //上传Demo图片
    @Autowired
    RestTemplate restTemplate;

    @Value("${gateway}")
    String gateway;

    private final static String ERROR_MSG = "error,this user is not exists";
    private final static String CREATE_USER_SUCCESS = "success,create user successfully";
    private final static String USER_EXSITS = "error,this user has registered. Can not register again";
    //生成公钥的私钥，并将其存入数据库中
    public String generateKey(String userName,String password,String departmentId){
        if(queryPublicKey(userName).equals(ERROR_MSG))
        {
            //换行符
            String line_separator = System.getProperty("line.separator");
            SDKUser user = new SDKUser();
            user.setUserName(userName);
            user.setDepartment(departmentId);
            user.setPassword(password);
            Map<String, Object> keyMap;

            try {
                keyMap = CreateSecrteKey.initKey();
                String public_key = CreateSecrteKey.getPublicKey(keyMap).replace(line_separator,"");
                String private_key = CreateSecrteKey.getPrivateKey(keyMap).replace(line_separator,"");
                user.setPublic_key(java.net.URLEncoder.encode(public_key,"UTF-8"));
                user.setPrivate_key(java.net.URLEncoder.encode(private_key,"UTF-8"));
//            log.info(public_key);
//            log.info(private_key);
            } catch (Exception e) {
                return e.getMessage();
            }

            user.setCreatetime(new Timestamp(new Date().getTime()));
            user.setUpdatetime(new Timestamp(new Date().getTime()));
            user.setValid(true);

            HttpUtil httpUtil = new HttpUtil();
            ResponseEntity<String> responseEntity = httpUtil.getForEntity(gateway+"/microservice-user/user/addUser",user);
            //return responseEntity.getBody();
            return CREATE_USER_SUCCESS;
        }
        else
        {
            return USER_EXSITS;
        }
    }

    public String queryPublicKey(String userName){
        SDKUser user = restTemplate.getForObject(gateway+"/microservice-user/user/selectUser/"+userName,SDKUser.class);
        //System.out.println(user);
        return user == null ? ERROR_MSG : user.getPublic_key();
    }

    public String queryPrivateKey(String userName){
        SDKUser user = restTemplate.getForObject(gateway+"/microservice-user/user/selectUser/"+userName,SDKUser.class);
        return user == null ? ERROR_MSG : user.getPrivate_key();
    }

}
