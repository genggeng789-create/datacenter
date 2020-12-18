package com.deepblue.searchPicture.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

@Slf4j
public class HttpUtil {
    public static String getHttplienturi(URI url, Map<String, Object> params, Map<String, String> header) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        if (!CollectionUtils.isEmpty(header)) {
            // 填充header
            for (Map.Entry<String, String> entry : header.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<Map<String, Object>> entry = new HttpEntity<>(params, headers);
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
}
