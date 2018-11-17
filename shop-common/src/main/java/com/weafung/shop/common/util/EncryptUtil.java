package com.weafung.shop.common.util;

import java.security.MessageDigest;

/**
 * @author weifengshih
 */
public class EncryptUtil {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String sha1(String str){
        if(str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            int len = messageDigest.digest().length;
            StringBuilder buf = new StringBuilder(len * 2);
            // 把密文转换成十六进制的字符串形式
            for (int j = 0; j < len; j++) {
                buf.append(HEX_DIGITS[(messageDigest.digest()[j] >> 4) & 0x0f]);
                buf.append(HEX_DIGITS[messageDigest.digest()[j] & 0x0f]);
            }
            return buf.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
