#!/usr/bin/python
# encoding:utf-8
# 抓取YouTube博主首页视频，todu-下滑异步加载未实现抓取
import requests
from bs4 import BeautifulSoup
import socks
import socket
import re,json,sys

# 目标网页的URL
url = 'https://www.youtube.com/@DocumentaryCN/videos'
# 设置socks代理
socks.set_default_proxy(socks.SOCKS5, "127.0.0.1", 10810)
socket.socket = socks.socksocket
# 使用requests库发送GET请求，并通过代理服务器
response = requests.get(url)

# 检查请求是否成功
if response.status_code == 200:
    soup = BeautifulSoup(response.text, 'html.parser')
    text=str(soup)
    result=soup
    # print(text)

    start_index = text.index("ytInitialData") + len("ytInitialData") + 3  # 找到"to"后加1得到起始索引
    start_result = text[start_index:].strip()  # strip()移除前导和尾随的空白
    
    end_index = start_result.index(";")  # 直接找到"programming"的起始索引作为结束位置
    end_result = start_result[:end_index].strip()  # strip()移除前导和尾随的空白
    
    # with open('D://1.txt', 'w', encoding='utf-8') as f: f.write(str(end_result)) #截取网页内完整json

    data = json.loads(end_result).get('contents').get('twoColumnBrowseResultsRenderer').get('tabs')[1].get('tabRenderer').get('content').get('richGridRenderer').get('contents')
    print(type(data))
    # with open('D://1.txt', 'w', encoding='utf-8') as f:f.write(str(data[0].get('richItemRenderer')))
    # sys.exit()
    k=0
    for i in data:
        try:
            videoRenderer = json.loads(json.dumps(i)).get('richItemRenderer').get('content').get('videoRenderer')
            title = videoRenderer.get('title').get('runs')[0].get('text')
            videoId =  videoRenderer.get('videoId')
            print(videoId+'---'+title)
            k=k+1
        except AttributeError:
            print("尝试访问的对象不是一个字典")
    print('总计：'+k)
else:
    print(f"请求失败，状态码：{response.status_code}")