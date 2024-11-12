package com.asily;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.asily.components.AESUtil;
import com.asily.components.CrawlRunner;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private AESUtil aesUtil;

    @Autowired
    private CrawlRunner crawler;

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

    @Test
    void testPy() {
//        crawler c = new crawler();
//        c.runPythonScript();
        System.out.println(crawler.run("1353", "B"));
//        System.out.println(crawler.run("2"));
    }


    @Test
    void GetTopic() {
        String url = "https://www.luogu.com.cn/problem/CF2035G2"; // 目标 URL

        try (var httpClient = HttpClients.createDefault()) {
            // 创建 GET 请求
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // 检查响应状态
                if (response.getCode() == 200) {
                    // 获取响应内容
                    String html = EntityUtils.toString(response.getEntity());

                    // 使用 Jsoup 解析 HTML
                    Document doc = Jsoup.parse(html);
                    Element previewElement = doc.selectFirst("body"); // 获取 body 元素
                    if (previewElement != null) {
                        // 获取 body 元素中的 HTML 内容
                        String bodyHtml = previewElement.html();

                        // 使用正则表达式去掉所有HTML标签
                        String textOnly = removeHtmlTags(bodyHtml);

                        // 打印去除HTML标签后的纯文本内容
                        System.out.println("Preview Text: " + textOnly);
                    } else {
                        System.out.println("Body element not found in the HTML response.");
                    }
                } else {
                    System.out.println("GET request failed. Response Code: " + response.getCode());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 利用正则去掉HTML标签的函数
    private String removeHtmlTags(String html) {
        // 定义正则表达式，匹配所有HTML标签
        String regex = "<[^>]*>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        // 替换所有HTML标签为空字符串
        return matcher.replaceAll("");
    }

}
