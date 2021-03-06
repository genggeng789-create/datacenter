package com.deepblueai.user.tools;

import java.security.MessageDigest;

public class Tools {
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

    public static int sum(int a, int... s) {
        for (int i : s) {
            a += i;
        }
        return a;
    }
}
