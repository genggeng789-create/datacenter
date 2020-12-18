package com.deepblue.ossAccess.service;

import com.alibaba.fastjson.JSONArray;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.deepblue.ossAccess.configure.OssProperties;
import com.deepblue.ossAccess.tools.Tools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Slf4j
@Service
public class OssAccessService {

    @Autowired
    AmazonS3 client;

    @Autowired
    OssProperties ossProperties;
    //fileNames是$$$分隔的字符串，文件名的JSONArray数组$$$文件的存储路径$$$文件的OSS路径，一般是md5格式的字符串
    public String uploadFile(String fileNames)
    {
        try {
            fileNames = java.net.URLDecoder.decode(fileNames,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String [] line = fileNames.split("\\$\\$\\$");
        String filePath = line[2];
        String filesList = line[0];
        String ftpPath_md5 = line[1];
        JSONArray jsonArray = JSONArray.parseArray(filesList);
        for(Object s : jsonArray)
        {
            System.out.println(filePath + s);
            System.out.println(ftpPath_md5);
            uploadFile2(filePath + s,ftpPath_md5);
        }
        return "upload success";
    }

    public  String uploadFile2( String fileName, String path_md5)
    {
        byte[] fileData;
        try {
            File file = new File(fileName);

            if(!file.exists()){
                log.error("文件： {} 不存在",fileName);
                return "file not exists";
            }

            Long fileSize = file.length();

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
            fileData = new byte[fileSize.intValue()] ;

            //大文件考虑断点续传
            if(fileSize > 100000000){
                log.error("文件太大，大于90MB,请考虑用断点续传");
                return "file is too large";
            }
            int len = 0 ;
            while((len=bis.read(fileData))!=-1) {
                // log.info("加载文件到内存成功，文件大小 {}",fileSize);
            }
            bis.close();
            uploadFile1(fileData,file.getName(),path_md5);
        } catch (Exception e) {
            log.error("出错 {}",e);
        }

        return "upload success";
    }


    public  String uploadFile1( byte[] fileData, String fileName, String prefix) {

        long startTime = System.currentTimeMillis();

        //如果未指定文件名，自动生成文件名
        if(StringUtils.isBlank(fileName) ){
            fileName = UUID.randomUUID().toString();
        }

        //如果指定了文件前缀，文件名加上前缀
        if(StringUtils.isNotBlank(prefix)){
            fileName = prefix + "/" + fileName;
        }

        log.info("开始上传文件 : {}",fileName);
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(fileData.length);
        PutObjectResult result = client.putObject(ossProperties.getBucketName(),fileName, new ByteArrayInputStream(fileData), meta);

//        long totalTime = System.currentTimeMillis() - startTime;
        log.info("-->上传 : {} 完成, 结果：{}" ,fileName,result.getContentMd5());
//        log.info("<---上传 : {} 完成,总执行时间 {} (ms) , 当前线程: {}" ,fileName, totalTime,Thread.currentThread().getName());


//        deleteFile(bucketName,fileName);
//        log.info("<---- 删除远端文件: {} 完成, 结果：{}" ,fileName);

        return fileName;
    }

    public void deleteFile(String fileName,String bucketName){
        client.deleteObject(bucketName,fileName);
    }


    public String Download(HttpServletResponse res, String fileName, String path) throws IOException {

        //FileResource fr = filePathRepository.findById(fileId).get();
        fileName = path + "/" + fileName;
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        filedownload(res.getOutputStream(),fileName);
        return "success download";
    }

//    public void downloadFile(String bucketName,String fileName,byte[]  bytes) {
//        long startTime = System.currentTimeMillis();
//        S3Object object;
//        if (client.doesObjectExist(bucketName, fileName)) {
//            object = client.getObject(bucketName, fileName);
//        } else {
//            log.error("bucket {} 或者文件 {} 不存在:",bucketName,fileName);
//            return;
//        }
//
//        InputStream inputStream;
//        inputStream = object.getObjectContent();
//        try {
//            bytes = IOUtils.toByteArray(inputStream);
//            inputStream.close();
//        } catch (IOException e) {
//            log.error(" 下载失败文件 {}, {}",fileName,e);
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    log.warn(e.getMessage());
//                }
//            }
//        }
//        long totalTime = System.currentTimeMillis() - startTime;
//        //countDownLatch.countDown();
//        log.info("下载文件 {} 成功，总耗时 {} (ms),当前线程 {}",fileName,totalTime,Thread.currentThread().getId());
//    }

    public void filedownload(OutputStream os, String fileName){
        String bucketName = ossProperties.getBucketName();
        S3Object object;
        if (client.doesObjectExist(bucketName, fileName)) {
            object = client.getObject(bucketName, fileName);
        } else {
            log.error("bucket {} 或者文件 {} 不存在:",bucketName,fileName);
            return;
        }

        InputStream inputStream;
        inputStream = object.getObjectContent();

        BufferedInputStream bis = null;
        try {
//            bis = new BufferedInputStream(new FileInputStream(new File(filepath)));
            bis = new BufferedInputStream(inputStream);
            byte[] buff = new byte[1024];
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, i);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(" download success");
    }

}
