package com.deepblue.report.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class UnzipUtil {
    final static String SEPARATOR = Tools.getSeparator();
    public static void unZip(String zipfile, String destDir) {
        destDir = destDir.endsWith(SEPARATOR) ? destDir : destDir + SEPARATOR;
        byte b[] = new byte[1024];
        int length;
        ZipFile zipFile;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            zipFile = new ZipFile(new File(zipfile));
            Enumeration enumeration = zipFile.getEntries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements()) {
                zipEntry = (ZipEntry) enumeration.nextElement();
                File loadFile = new File(destDir + zipEntry.getName());
                if (zipEntry.isDirectory()) {
                 // 这段都可以不要，因为每次都貌似从最底层开始遍历的
                    loadFile.mkdirs();
                } else {
                    if (!loadFile.getParentFile().exists())
                        loadFile.getParentFile().mkdirs();
                    //OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(loadFile));
                    outputStream = new FileOutputStream(loadFile);
                    inputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                    while ((length = inputStream.read(b)) > 0)
                    {
                        outputStream.write(b, 0, length);
                    }
                    outputStream.flush();
                }
            }
            System.out.println("文件解压成功");
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(outputStream != null){
                try {
                    if(outputStream != null){
                        outputStream.close();
                    }
                    if(inputStream != null){
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void main(String args []){
//        unZip("C:\\temp\\test\\2020-09-16-00-57\\完成\\过滤数据20200915-end.zip","C:\\temp\\test\\2020-09-16-00-57\\完成");
//    }
}
