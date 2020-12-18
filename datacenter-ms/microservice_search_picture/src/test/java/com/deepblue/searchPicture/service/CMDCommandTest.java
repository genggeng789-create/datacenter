package com.deepblue.searchPicture.service;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CMDCommandTest {

    @Test
    public void run() throws IOException {
        // 创建命令集合
        List<String> commandList = new ArrayList<String>();
        commandList.add("cmd");
        commandList.add("/c");  // 执行结束后关闭
        commandList.add("echo");
        commandList.add("hello");
        commandList.add("cmd");
        // ProcessBuilder是一个用于创建操作系统进程的类，它的start()方法用于启动一个进行
        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
        // 启动进程
        Process process = processBuilder.start();
        // 解析输出
        String result = convertStreamToStr(process.getInputStream());
        System.out.println(result);
    }

    public String convertStreamToStr(InputStream is) throws IOException {
        if (is != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }
}
