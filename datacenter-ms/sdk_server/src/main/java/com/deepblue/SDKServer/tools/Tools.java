package com.deepblue.SDKServer.tools;

import java.io.*;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class Tools {
    public static String TimestampToString(Timestamp timestamp,String pattern){
        DateFormat sdf = new SimpleDateFormat(pattern);
        String str = null;
        try {
            //方法一
            str = sdf.format(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

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

    public static String getLastDate()
    {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, -1);
        return getStringDate(ca.getTime(),"yyyy-MM-dd");
    }

}
