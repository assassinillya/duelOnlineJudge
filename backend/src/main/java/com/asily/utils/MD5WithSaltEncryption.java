package com.asily.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MD5WithSaltEncryption {

    // 生成随机盐值
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : saltBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // 使用 MD5 和盐值加密密码
    public static String encryptWithSalt(String password, String salt) {
        // 将盐值和密码拼接，以实现加盐效果
        String saltedPassword = salt + password;
        try {
            // 获取MD5算法实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对拼接后的密码进行加密处理
            byte[] hash = md.digest(saltedPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            // 遍历加密后的字节数组，将其转换为十六进制字符串
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            // 返回加密后的字符串
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // 如果MD5算法不可用，抛出运行时异常
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String password = "123456";

        // 生成随机盐值
        String salt = generateSalt();
        System.out.println("Salt: " + salt);

        // 加盐加密
        String encryptedPassword = encryptWithSalt(password, salt);
        System.out.println("使用 Salt 加密密码：" + encryptedPassword);
    }
}
