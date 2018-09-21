package com.yfbx.demo.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Author: Edward
 * Date: 2018/1/31
 * Description:
 */

public class Md5 {
    
    /**
     * MD5加码 生成32位md5码
     */
    public static String encrypt(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes(StandardCharsets.UTF_8));
            byte md5Bytes[] = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (byte b : md5Bytes) {
                i = b;
                if (i < 0) i += 256;
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
