package com.asily.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Component
public class CrawlRunner {

    @Value("${crawler.command}")
    private String command;
    @Value("${crawler.path}")
    private String path;

    public String run(String... values) {
        // 构建参数列表
        String[] args = new String[values.length + 2];
        args[0] = command;
        args[1] = path + "crawlerCF.py";
        System.arraycopy(values, 0, args, 2, values.length);

        ProcessBuilder builder = new ProcessBuilder(args);

        // 设置工作目录
        builder.directory(new File(path));

        // 合并错误流和标准输出流，确保读取所有输出
        builder.redirectErrorStream(true);

        StringBuilder result = new StringBuilder();
        try {
            Process process = builder.start();

            // 读取进程输出
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            }

            // 等待进程执行完成
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                result.append("Process exited with error code ").append(exitCode).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

        return result.toString();
    }
}