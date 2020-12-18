package com.deepblue.ossAccess.controller;

import com.deepblue.ossAccess.service.OssAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/")
public class OssFileController {

    @Autowired
    private OssAccessService ossAccessService;

    @RequestMapping("/download")
    @ResponseBody
    public String downloadFile(@RequestParam("fileName") String fileName, @RequestParam("path") String path, HttpServletResponse res) throws IOException {
        return ossAccessService.Download(res,fileName,path);
    }

//    @RequestMapping("/upload")
//    @ResponseBody
//    public String uploadFile(@RequestParam("fileNames") String fileNames,@RequestParam("path") String prefix,@RequestParam("filePath") String filePath){
//        return ossAccessService.uploadFile(filePath,fileNames,prefix);
//    }

    @RequestMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("temp") String fileNames){
        System.out.println(fileNames);
        return ossAccessService.uploadFile(fileNames);
    }

    //文件下载相关代码
    @RequestMapping(value = "/downloadImage",method = RequestMethod.GET)
    public String downloadImage(String imageName, HttpServletResponse response) {
        //String fileName = "123.JPG";
//        logger.debug("the imageName is : "+imageName);
        String uploadDir = "C:\\Users\\tuomy\\Downloads\\";
        String fileUrl = uploadDir+imageName;
        if (fileUrl != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
           /* String realPath = request.getServletContext().getRealPath(
                    "//WEB-INF//");*/
            /*File file = new File(realPath, fileName);*/
            File file = new File(fileUrl);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + imageName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}