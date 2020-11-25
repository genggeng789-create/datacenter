package com.deepblue.ossAccess.configure;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@Slf4j
public class OssAmazonS3ClientConfig {

    @Autowired
    OssProperties ossProperties;

    @Bean
    public AmazonS3 client() {
        AmazonS3 client = null;
        if (client == null) {
            log.info("开始创建OSS amazon s3 client");
            AWSCredentials credentials = new BasicAWSCredentials(ossProperties.getAccessKey(), ossProperties.getSecretKey());
            ClientConfiguration clientConfig = new ClientConfiguration();
            clientConfig.setProtocol(Protocol.HTTP);

            client = AmazonS3ClientBuilder.standard()
                    .withClientConfiguration(clientConfig)
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ossProperties.getEndpoint(), ""))
                    .withPathStyleAccessEnabled(true)
                    .build();
            log.info("创建OSS amazon s3 client success");
        }
        return client;
    }
}

