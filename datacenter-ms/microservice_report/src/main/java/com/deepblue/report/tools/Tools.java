package com.deepblue.report.tools;

import java.io.*;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

public class Tools {
    public static Date stringToDate(String time,String... pattern) {
        DateFormat format = null;//日期格式
        if(pattern.length != 0)
        {
            format = new SimpleDateFormat(pattern[0]);
        }
        else
        {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String encrypt(String dataStr) {
        try {
            dataStr = dataStr;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String createJSONArray(Iterator it){
        StringBuffer stringBuffer = new StringBuffer();
        while (it.hasNext())
        {
            stringBuffer.append(it.next()+",");
        }

        return stringBuffer.insert(0,"[").deleteCharAt(stringBuffer.length() - 1).append("]").toString();
    }

    public static String getStringDate(Date currentTime,String... pattern) {
        SimpleDateFormat formatter = null;
        if(pattern.length != 0)
        {
            formatter = new SimpleDateFormat(pattern[0]);
        }
        else
        {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         }

        String dateString = formatter.format(currentTime);
        return dateString;
     }

    public static void filedownload(OutputStream os, String filepath){
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(new File(filepath)));
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

    public static String getSeparator() {
        String SEPARATOR = null;
        Properties props = System.getProperties();
        String osType = props.getProperty("os.name");
        if(osType.contains("Windows"))
        {
            SEPARATOR = "\\";
        }
        else
        {
            SEPARATOR = "/";
        }
        return SEPARATOR;
    }

    public static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static int bytesToInt(byte[] ary, int offset) {
        int value;
        value = (int) ((ary[offset]&0xFF)
                | ((ary[offset+1]<<8) & 0xFF00)
                | ((ary[offset+2]<<16)& 0xFF0000)
                | ((ary[offset+3]<<24) & 0xFF000000));
        return value;
    }

    public final static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(val));

            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
    //获取指定行内容
    public static String readFileContent(String fileName,int lineNumber) {
        File file = new File(fileName);
        BufferedReader reader = null;
        int count = 0;
        String tempStr = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((count < lineNumber) &&((tempStr = reader.readLine()) != null)) {
                count++;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        System.out.println(tempStr);
        return tempStr;
    }


}
