package com.deepblue.searchPicture.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.deepblue.searchPicture.entity.PictureResource;
import com.deepblue.searchPicture.entity.Position;
import com.deepblue.searchPicture.tools.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

@Slf4j
@Service
public class ExtractPictureResourceService extends ExcelUtil{
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    final static String SEPARATOR = Tools.getSeparator();
    private List<PictureResource> pictureResourceList;
    private Map<Position, Object> map;
    private Map<String, Position> map1;
    private Map<String,String> map2;
    private String ftpFilePath;
    private String localFilePath;
    private String osType = System.getProperties().getProperty("os.name");

    public ExtractPictureResourceService() {
        this.map2 = new HashMap<>();
        this.generateMapping();
    }

    public void generateMapping() {
//        map.put(new Position(28,2),"");
//        map.put(new Position(23,2),"");
//        map.put(new Position(16,2),"");
//        map.put(new Position(17,2),"");
//        map.put(new Position(4,2),"");
//        map.put(new Position(5,2),"");
//        map.put(new Position(6,2),"");
//        map.put(new Position(26,2),"");
//        map.put(new Position(27,2),"");
//
//        map1.put("package_name",new Position(28,2));
//        map1.put("project",new Position(23,2));
//        map1.put("mark_class",new Position(16,2));
//        map1.put("mark_type",new Position(17,2));
//        map1.put("mark_stuff_class",new Position(4,2));
//        map1.put("mark_stuff_desc",new Position(5,2));
//        map1.put("scene",new Position(6,2));
//        map1.put("batch_id",new Position(26,2));
//        map1.put("picture_number",new Position(27,2));

        map2.put("项目名称","project");
        map2.put("标注类别","mark_class");
        map2.put("标注形式","mark_type");
        map2.put("标注物类别","mark_stuff_class");
        map2.put("标注物描述","mark_stuff_desc");
        map2.put("场景描述","scene");
        map2.put("批次号","batch_id");
        map2.put("总张数","picture_number");
    }

    public String getFtpFilePath() {
        return ftpFilePath;
    }

    public void setFtpFilePath(String ftpFilePath) {
        this.ftpFilePath = ftpFilePath;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }


    /**
     * 增加文档信息
     */
    public void addDocument() {
        for(PictureResource pictureResource : this.pictureResourceList)
        {
            try {
                // 创建索引请求对象
                IndexRequest indexRequest = new IndexRequest("picture-resource", "doc");
                // 将对象转换为 byte 数组
                byte[] json = JSON.toJSONBytes(pictureResource);
                // 设置文档内容
                indexRequest.source(json, XContentType.JSON);
                // 执行增加文档
                IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
                log.info("创建状态：{}", response.status());
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    //根据路径获取标注时间 例如：/20201123标注图片资产化/K12理化试验/检测/氧气/2020-10-30-09-21
    public String createMarkStartTime(String ftpFilePath){
        File file = new File(ftpFilePath);
        String time = file.getName();
        return time.substring(0,10) + " " + time.substring(11,time.length()).replace("-",":") + ":00";
    }

    public boolean fillPictureResource() throws IOException {
        //下载目录下的文件到本地
        String manifest = downloadFile(ftpFilePath,localFilePath);
        //String manifest = "C:\\temp\\test\\2020-09-16-00-57.xlsx";
        //解析下载的本地的Excel文件
        this.map1 = readExcel1(manifest,this.map2,this.map);
        readExcel(manifest,this.map);
        for(PictureResource pictureResource : this.pictureResourceList){

            pictureResource.setPackage_md5(Tools.MD5(pictureResource.getPackage_name()).toUpperCase());
            pictureResource.setPath_md5(Tools.MD5(pictureResource.getPath()).toUpperCase());

            pictureResource.setProject((String)map.get(map1.get("project")));

            pictureResource.setMark_class((String)map.get(map1.get("mark_class")));
            pictureResource.setMark_type((String)map.get(map1.get("mark_type")));

            pictureResource.setMark_stuff_class((String)map.get(map1.get("mark_stuff_class")));
            pictureResource.setMark_stuff_desc((String)map.get(map1.get("mark_stuff_desc")));
            pictureResource.setSence((String)map.get(map1.get("scene")));

            pictureResource.setUpdateTime(Tools.getStringDate(new Date()));
            pictureResource.setCreateTime(Tools.getStringDate(new Date()));
            pictureResource.setMarkStartTime(createMarkStartTime(this.ftpFilePath));
            pictureResource.setBatch_id((String)map.get(map1.get("batch_id")));
            Float f = 0.0f;
            try {
                f = Float.valueOf((String)map.get(map1.get("picture_number")));
            }
            catch (Exception e)
            {
                System.out.println("图片数量错误");
            }

            pictureResource.setPicture_number(f.intValue());
            //将Demo文件插入OSS服务器
            uploadDemoFile(pictureResource.getDemo_photo_list().toString(),pictureResource.getPath_md5(),pictureResource.getLocal_file_path() + SEPARATOR);

            System.out.println(pictureResource);
        }
        return true;
    }

    //上传Demo图片
    private boolean uploadDemoFile(String fileNames, String prefix, String filePath){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String data = java.net.URLEncoder.encode(fileNames + "$$$" + prefix + "$$$" + filePath, "UTF-8");
            restTemplate.put("http://localhost:8087/upload?temp={fileNames}", null, data);
            System.out.println("上传Demo成功");
        }catch (HttpClientErrorException e){
            System.out.println("http客户端请求出错了！");
            //开发中可以使用统一异常处理，或者在业务逻辑的catch中作响应
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /*
    * 下载FTP路径及路径下的描述文件
    * */
     private String downloadFile(String ftpFilePath,String localFilePath) throws IOException {
         FTPUtil ftpUtil = new FTPUtil("10.16.33.159", 9021, "dbuser", "slkj8888");
         String dir = new File(ftpFilePath).getName();
         //下载ftpFilePath下的整个目录
         ftpUtil.downloadDirectory(localFilePath,ftpFilePath);
         String excelFile = localFilePath+ SEPARATOR + dir + ".xlsx";
         //下载Excel描述文件
         ftpUtil.downloadFile(excelFile,ftpFilePath + ".xlsx");
         ftpUtil.disConnection();
         getZipFile(localFilePath + SEPARATOR + dir);
         return excelFile;
     }

     //递归查询ZIP压缩文件
     private void getZipFile(String localFilePath){
         File [] files = new File(localFilePath).listFiles();
         for(File file : files){
             if(file.isDirectory())
             {
                 getZipFile(file.getAbsolutePath());
             }
             else if(file.getName().endsWith("zip")){
                 //解压并抽取demo图片，上传到OSS服务器
                 PictureResource pictureResource = new PictureResource();
                 pictureResource.setZip_file_path(file.getAbsolutePath());
                 unzip(file.getAbsolutePath(), pictureResource);
                 pictureResourceList.add(pictureResource);
             }
         }
     }

     /*
    * 解压并抽取demo图片
    * */
    private boolean unzip(String zipFileName,PictureResource pictureResource) {
        boolean flag = true;
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> arrayList1 = new ArrayList<>();
        String zipOutputFilePath = new File(zipFileName).getParent();
        UnzipUtil.unZip(zipFileName,zipOutputFilePath);

        pictureResource.setPackage_name(new File(zipFileName).getName());
        File file =  new File(this.getFtpFilePath());
        pictureResource.setPath((file.getParent()+ new File(zipFileName).getParent().replace(this.localFilePath,"")).replace("\\","/"));

        //获取解压后的文件夹路径
//        String path = zipFileName.substring(0,zipFileName.lastIndexOf("."));
        //zipOutputFilePath zip文件所在的路径
        File [] files = new File(zipOutputFilePath).listFiles();
        for (File file2 : files)
        {
            if(file2.isDirectory()){
                getDemoPictures(zipOutputFilePath,arrayList,arrayList1);
                pictureResource.setDemo_photo_list(JSONArray.parseArray(JSON.toJSONString(arrayList)));
                pictureResource.setLocal_file_path(new File(arrayList1.get(1)).getParent());
            }
        }

        return flag;
    }

     //取抽样图片
     private boolean getDemoPictures(String unzipFold,List<String> arrayList,List<String> absolutePath){
         File[] files = new File(unzipFold).listFiles();
         //图片的路径
         int count = 0;
         for(File file : files){
             if(file.isDirectory()){
                 getDemoPictures(file.getAbsolutePath(),arrayList,absolutePath);
             }
             else
             {
                 //取10张抽样图片
                 if(count < 10 && (arrayList.size() < 10))
                 {
                     if(!(file.getName().contains("txt") || file.getName().contains("json") || file.getName().contains("xml") || file.getName().contains("zip")))
                     {
                         count++;
                         arrayList.add(file.getName());
                         absolutePath.add(file.getAbsolutePath());
//                         System.out.println(file.getAbsolutePath());
                     }
                 }
                 else
                 {
                     break;
                 }
             }
         }
         return true;
     }
    /*
    * 上传压缩包到HDFS
    * */
    private void uploadData(){
        for(PictureResource pictureResource : this.pictureResourceList){
            try {
                String command = "/data/bigdata/ftpdata/uploadPackage.sh " + pictureResource.getPath_md5() + " " + pictureResource.getZip_file_path() + " " + pictureResource.getPackage_md5();
                Process process = Runtime.getRuntime().exec(command);
                InputStreamReader ir = new InputStreamReader(process.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);
                String line;
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            }
            catch (IOException e){
                System.err.println ("IOException " + e.getMessage());
            }
        }
    }

    public String process(int remoteFileLineNumber,String temp) {
        String remoteFilePath = null;
        String loacalFilePath = null;
        if(osType.contains("Windows"))
        {
            remoteFilePath = Tools.readFileContent("C:\\temp\\test\\resultRecord.txt",remoteFileLineNumber);
            loacalFilePath="C:\\temp\\test";
        }
        else
        {
            remoteFilePath = Tools.readFileContent(temp,remoteFileLineNumber);
            loacalFilePath="/data/bigdata/ftpdata";
        }

        Long start_time = System.currentTimeMillis();
        setFtpFilePath(remoteFilePath);
        setLocalFilePath(loacalFilePath);
        this.pictureResourceList = new ArrayList<>();
        this.map = new HashMap<>();
        this.map1 = new HashMap<>();

        try {
            fillPictureResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!osType.contains("Windows"))
        {
            //上传数据到HDFS
            uploadData();
        }
        //将元数据信息加入ES
        addDocument();
        Long end_time = System.currentTimeMillis();
        DecimalFormat df=new DecimalFormat("0.000");
        log.info(df.format((float)(end_time - start_time)/1000) +"m ");
        return remoteFilePath;
    }
}
