package com.asily.utils;

public class StringUtil {
    public String[] splitString(String str) {
        // 使用正则表达式查找数字部分和字母部分（包括字母后的数字）
        String[] result = new String[2];

        // 正则表达式：匹配数字后跟字母，捕获数字部分和字母部分
        String regex = "(\\d+)([A-Za-z].*)";
        if (str.matches(regex)) {
            result[0] = str.replaceAll(regex, "$1"); // 提取数字部分
            result[1] = str.replaceAll(regex, "$2"); // 提取字母及后续部分
        } else {
            // 如果没有字母部分，直接返回原始字符串的数字部分和空字符串
            result[0] = str;
            result[1] = "";
        }

        return result;
    }
}
