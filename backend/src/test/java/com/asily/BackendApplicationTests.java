package com.asily;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.asily.utils.AESUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private AESUtil aesUtil;

	@Test
	void contextLoads() throws Exception {
		System.out.println(SaSecureUtil.rsaGenerateKeyPair());
	}

	@Test
	void testPwd() {
		String s = aesUtil.generatePwd("123456");
		System.out.println(s);
		System.out.println(aesUtil.checkPwd("123456", s));

	}

}
