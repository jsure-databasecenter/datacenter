package com.jsure.datacenter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author: wuxiaobiao
 * @Description: MD5工具类
 * @Date: Created in 2018/4/11
 * @Time: 10:06
 * I am a Code Man -_-!
 */
public class MD5Util {

    private static final String SIGNATURE = "1f32a83588848d341c89f102dde11d4f";

    private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

    public static void main(String[] args) {
        try {
            String a = MD5("你好啊啊");

            String b = MD5Encode(a, "UTF-8");
            System.out.println(b);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String MD5(String str) {
        try {
            byte[] bs = str.getBytes("UTF-8");
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(bs);
            String hex = new BigInteger(1, digest.digest()).toString(16);
            // 补齐BigInteger省略的前置0
            return new String(new char[32 - hex.length()]).replace("\0", "0") + hex;
        } catch (Exception e) {
            logger.info("MD5加密失败：" + e);
            return null;
        }

    }

    public static boolean checkSignature(String token, String userId) {
        String checkToken = MD5Util.MD5(userId + MD5Util.MD5(SIGNATURE));
        logger.info("验签 token:" + token + " <==>生成：" + checkToken);
        return checkToken.equals(token);

    }

    public static String MD5Encrypt(String userId) {
        String token = MD5Util.MD5(userId + MD5Util.MD5(SIGNATURE));
        logger.info("userId加密 :" + token);
        return token;

    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}