package com.deepblue.ossAccess.configure;
/**
 * @author tuomingyao(2020-11-16)
 *
 */

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootConfiguration
@ConfigurationProperties(prefix = "oss")
@EnableConfigurationProperties(OssProperties.class)
public class OssProperties {

  private String endpoint;

  private String accessKey;

  private String  secretKey;

  private int uploadThreadNumber;

  private int downloadThreadNumber;

  String bucketName;

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public int getUploadThreadNumber() {
    return uploadThreadNumber;
  }

  public void setUploadThreadNumber(int uploadThreadNumber) {
    this.uploadThreadNumber = uploadThreadNumber;
  }

  public int getDownloadThreadNumber() {
    return downloadThreadNumber;
  }

  public void setDownloadThreadNumber(int downloadThreadNumber) {
    this.downloadThreadNumber = downloadThreadNumber;
  }

  public String getBucketName() {
    return bucketName;
  }

  public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
  }
}
