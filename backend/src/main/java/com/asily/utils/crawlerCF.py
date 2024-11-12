# -*- coding: utf-8 -*-
import urllib.request
import bs4
from bs4 import BeautifulSoup
import sys
import random
import json

def cf(problemSet, problemId):
    # 常用 User-Agent 列表
    my_headers = [
        "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36",
        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/537.75.14"
    ]

    # 随机选择 User-Agent
    header = random.choice(my_headers)

    # 题目链接
    url = "https://codeforces.com/problemset/problem/{}/{}".format(problemSet, problemId)

    # 创建请求对象并设置 headers
    req = urllib.request.Request(url, headers={'User-Agent': header})

    # 存储数据的字符串变量
    result_str = ""

    try:
        # 发送请求获取网页内容
        html = urllib.request.urlopen(req).read()
        soup = BeautifulSoup(html, 'lxml')
        
        # 存储
        data_dict = {}

        # 找到主体内容
        mainContent = soup.find_all(name="div", attrs={"class": "problem-statement"})[0]
        data_dict['Title'] = "CodeForces {} ".format(problemSet) + mainContent.find_all(name="div", attrs={"class": "title"})[0].contents[-1]
        data_dict['Time Limit'] = mainContent.find_all(name="div", attrs={"class": "time-limit"})[0].contents[-1]
        data_dict['Memory Limit'] = mainContent.find_all(name="div", attrs={"class": "memory-limit"})[0].contents[-1]

        # 定义 divTextProcess 函数并处理题目描述
        def divTextProcess(div):
            strBuffer = ''
            for each in div.find_all("p"):
                for content in each.contents:
                    if strBuffer != '':
                        strBuffer += '\n\n'
                    if type(content) != bs4.element.Tag:
                        strBuffer += content.replace("       ", " ").replace("$$$", "$")
                    else:
                        strBuffer += "**" + content.contents[0].replace("       ", " ").replace("$$$", "$") + "**"
            return strBuffer

        data_dict['Problem Description'] = divTextProcess(mainContent.find_all("div")[10])

        # 处理输入和输出描述
        data_dict['Input'] = divTextProcess(mainContent.find("div", class_="input-specification"))
        data_dict['Output'] = divTextProcess(mainContent.find("div", class_="output-specification"))

        # 处理样例输入输出
        data_dict['Sample Input'] = "```cpp" + mainContent.find("div", class_="input").find("pre").text + '```'
        data_dict['Sample Output'] = "```cpp" + mainContent.find("div", class_="output").find("pre").text + '```'
        data_dict['Note'] = divTextProcess(mainContent.find("div", class_="note"))
        data_dict['Source'] = "[{}]({})".format(data_dict['Title'], url)

        # 构建字符串输出
        for each in data_dict.keys():
            result_str += '### ' + each + '\n' + data_dict[each].replace("\n\n**", "**").replace("**\n\n", "**") + '\n\n'

        # 将数据字典转换为JSON字符串
        # json_output = json.dumps(data_dict, ensure_ascii=False, indent=4)
        # result_str += "Data JSON:\n{}\n".format(json_output)

    except urllib.error.HTTPError as e:
        result_str += "HTTP error: {}\n".format(e.code)
    except urllib.error.URLError as e:
        result_str += "URL error: {}\n".format(e.reason)
    except Exception as e:
        result_str += "Error: {}\n".format(e)

    # 将结果写入文件，保存到当前路径
#     with open(f"./codeforces_problem_{problemSet}_{problemId}.md", "w", encoding="utf-8") as f:
#         f.write(result_str)
#     print(f"Output written to codeforces_problem_{problemSet}_{problemId}.md")



    return result_str



if __name__ == '__main__':
    result = cf(sys.argv[1], sys.argv[2])
    print(result)