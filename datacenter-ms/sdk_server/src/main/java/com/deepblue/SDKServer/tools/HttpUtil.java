package com.deepblue.SDKServer.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Slf4j
public class HttpUtil extends RestTemplate {

    public ResponseEntity<java.lang.String> getForEntity(String url, Object sendObject) throws RestClientException{
        Map<String, Object> uriVariables = new HashMap<>();
        Reflection reflection = new Reflection();
        Iterator iterator = reflection.getFiledsInfo(sendObject).iterator();
        url += "?";

        while(iterator.hasNext()){
            Map infoMap = (Map)iterator.next();
            if(infoMap.get("value") != null){
                uriVariables.put((String)infoMap.get("name"),infoMap.get("value"));
                url += infoMap.get("name")+"={"+infoMap.get("name")+"}&&";
            }
        }
        url = url.substring(0,url.lastIndexOf("&&"));
        log.info(url);
        ResponseEntity<String> responseEntity = super.getForEntity(url, String.class, uriVariables);

        return responseEntity;
    }
}
