package com.asily.utils;


import cn.dev33.satoken.secure.SaSecureUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AESUtil {

    @Value("${ojKey.public}")
    private String publicKey;

    public String generatePwd(String pwd) {
        return SaSecureUtil.aesEncrypt(publicKey, pwd);
    }

    // 未加密密码   加密后密码
    public boolean checkPwd(String pwd, String pwd2) {
        return SaSecureUtil.aesEncrypt(publicKey, pwd).equals(pwd2);
    }
}
