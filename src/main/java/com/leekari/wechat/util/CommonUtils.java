package com.leekari.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author litao
 * @date 2020/7/29 10:39
 * @description
 */
public class CommonUtils {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String md5(String plainText){
        return md5(plainText, 20);
    }

    private static String md5(String plainText, int length){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");//获取MD5实例
            md.update(plainText.getBytes());//此处传入要加密的byte类型值
            byte[] digest = md.digest();//此处得到的是md5加密后的byte类型值
            int i;
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                i = b;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append(0);
                sb.append(Integer.toHexString(i));//通过Integer.toHexString方法把值变为16进制
            }
            return sb.toString().substring(0, length);//从下标0开始，length目的是截取多少长度的值
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
