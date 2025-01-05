#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""

http://qt.gtimg.cn/q=sh600028,sz159998
http://qt.gtimg.cn/q=s_sh600028,s_sz159998

腾讯财经提供的一个非官方支持的API，所以它的稳定性和长期可用性无法得到保证。


"""
import flask
app = flask.Flask(__name__)

import requests
# 用户提供的成本价字典
cost_prices = {
    '002230': (50.425, 200),  # 科大讯飞
    '159998': (0.90, 33000),   # 计算机ETF
    '512710': (0.620, 20000),   # 军工龙头ETF
    '600028': (6.201, 3300),   # 中国石化
    '600547': (24.316, 300),   # 山东黄金
    '601989': (4.791, 4200)    # 中国重工
}

import smtplib
from email.mime.text import MIMEText
from email.header import Header

def send_qq_email(sender, password, receivers, subject, content):
    """
    使用QQ邮箱发送邮件
    
    :param sender: 发件人邮箱地址（你的QQ邮箱）
    :param password: QQ邮箱授权码
    :param receivers: 收件人列表
    :param subject: 邮件主题
    :param content: 邮件正文内容
    :return: 成功返回True，失败返回False
    """
    try:
        # 创建MIMEText对象
        message = MIMEText(content, 'plain', 'utf-8')
        message['From'] = Header("发件人姓名", 'utf-8')  # 或者直接使用sender
        message['To'] = Header("收件人姓名", 'utf-8')   # 如果有多个收件人，可以用逗号分隔
        message['Subject'] = Header(subject, 'utf-8')

        # 连接到QQ邮箱的SMTP服务器并登录
        smtp_server = "smtp.qq.com"
        server = smtplib.SMTP_SSL(smtp_server, 465)  # QQ邮箱的SMTP服务器端口是465
        server.login(sender, password)

        # 发送邮件
        server.sendmail(sender, receivers, message.as_string())
        print("邮件发送成功")
        return True
    except smtplib.SMTPException as e:
        print(f"Error: 无法发送邮件. {e}")
        return False
    finally:
        server.quit()
        
from datetime import datetime, time

# 定义股市的开盘和闭盘时间（这里以中国A股为例）
TRADE_TIMES = [
    (time(9, 30), time(11, 30)),
    (time(13, 0), time(15, 0))
]

def is_market_open():
    now = datetime.now().time()
    return any(start <= now <= end for start, end in TRADE_TIMES)
    
def get_stock_info(stock_codes):
    # 判断是上交所还是深交所的代码
    def add_prefix(code):
        if code.startswith(('5','6','9')):
            return f"s_sh{code}"
        else:
            return f"s_sz{code}"
            
    converted_codes = [add_prefix(code) for code in stock_codes.split('.')]
    
    url = f"http://qt.gtimg.cn/q="+','.join(converted_codes)
    print(url)
    response = requests.get(url)
    
    # 解析返回的数据
    data_list = response.text.split(';')[:-1]  # 去掉最后一个元素
    for one in data_list:
        data = one.split('~')
        cost_price = cost_prices.get(data[2], 0)[0]
        cost_num = cost_prices.get(data[2], 0)[1] 
        totalG = round((float(data[3]) - cost_price) * cost_num, 2) # 总收益
        
        print(data[2],data[3],cost_price,cost_num,totalG,type(totalG),is_market_open())
        
    # send_qq_email(sender, password, receivers, subject, content) # 发送邮件
        
#0: 未知 1: 名字 2: 代码 3: 当前价格 4: 涨跌额 5: 涨跌幅 6: 成交量（手）7: 成交额（万）8: 总市值 9: 股票类型
#0: 未知 1: 名字 2: 代码 3: 当前价格 4: 昨收 5: 今开 6: 成交量（手） 7: 外盘 8: 内盘 9: 买一 10: 买一量（手） 11-18: 买二 买五 19: 卖一 20: 卖一量 21-28: 卖二 卖五 29: 最近逐笔成交 30: 时间 31: 涨跌 32: 涨跌% 33: 最高 34: 最低 35: 价格/成交量（手）/成交额 36: 成交量（手） 37: 成交额（万） 38: 换手率 39: 市盈率 40: 41: 最高 42: 最低 43: 振幅 44: 流通市值 45: 总市值 46: 市净率 47: 涨停价 48: 跌停价
    return 0
    
#http://127.0.0.1:5000/getstock
@app.route('/getstock', methods=['GET'])
def getstock():
    global cost_prices
    html_content = '''
    <!DOCTYPE html>
    <html>
        <head>
            <title>股票</title>
            <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <style>
            /* 重置默认样式 */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            html, body {
                height: 100%;
                width: 100%;
                overflow: hidden; /* 确保内容不会溢出 */
            }
            table {
                width: 100%;
            }
            .positive {
                background-color: #d4edda; /* 绿色背景 */
                color: #155724;
            }
            .negative {
                background-color: #f8d7da; /* 红色背景 */
                color: #721c24;
            }
            </style>
        </head>
        <body>
            <div><input id="codenum" style="width: 100%;"></input></div>
            <div id="contentArea" >
                <table id="stockTable">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>代码</th>
                            <th>现价</th>
                            <th>涨跌</th>
                            <th>成本价</th>
                            <th>收益</th>
                        </tr>
                    </thead>
                    <tbody>
                    <!-- 行将在这里动态添加 -->
                    </tbody>
                </table>
            </div>
            <script>
                var costPrices = {{ cost_prices|tojson|safe }}
                var keysJoinedByHash = Object.keys(costPrices).sort().join('.');
                $('#codenum').val(keysJoinedByHash);
                
                
               // 将数据填充到表格中
               function populateTableFromResponse(response) {
                    var totalG = 0;//总收益
                    var dataArray = response.split(';').filter(item => item.trim() !== '');// 分割响应字符串并移除可能存在的最后一个空元素
                    var $tbody = $('#stockTable tbody').empty(); // 清空现有内容
                    dataArray.forEach(function(item) {
                        var dataList = item.split('~');// 拆分字符串成列表
                        // 确保关键字段存在且有效
                        if (dataList.length >= 9 && dataList[1] && dataList[2]) {
                            var rowClass = parseFloat(dataList[5]) < 0 ? 'negative' : 'positive'; // 根据涨跌幅设置行的颜色
                            var costPrice = parseFloat(costPrices[dataList[2]][0]) || 0; // 获取成本价，默认为0
                            var costNum = parseFloat(costPrices[dataList[2]][1]) || 0; // 获取购买数量，默认为0
                            var currentPrice = parseFloat(dataList[3]); // 当前价格
                            var profitLoss = costPrice !== 0 ? ((currentPrice - costPrice) * costNum).toFixed(2) : 'N/A'; // 计算收益/亏损
                            totalG += Number(profitLoss);
                            // 直接构建并添加行到表格
                            var row = `<tr class="${rowClass}">
                                    <td>${dataList[1]}</td> <!-- 名称 -->
                                    <td>${dataList[2]}</td> <!-- 代码 -->
                                    <td>${parseFloat(dataList[3]).toFixed(3)}</td> <!-- 当前价格 -->
                                    <td>${parseFloat(dataList[5]).toFixed(2)}%</td> <!-- 涨跌幅 -->
                                    <td>${costPrice.toFixed(3)}</td> <!-- 成本价 -->
                                    <td>${profitLoss}</td> <!-- 收益/亏损 -->
                                </tr>`;
                            $tbody.append(row);
                        }
                    });
                    $tbody.append(`<tr><td colspan="5"></td><td style="color: #0058ff;">${totalG}</td></tr>`);
                }

                // 转换函数
                function convertCodes(codes) {
                    return codes.map(function(code) {
                        if (/^[569]/.test(code)) {
                            return 's_sh' + code;
                        } else {
                            return 's_sz' + code;
                        }
                    });
                }
                
                // 请求数据
                function getContent() {
                    var codenum = $('#codenum').val();
                    $.ajax({
                        url: 'http://qt.gtimg.cn/q='+convertCodes(codenum.split('.')),
                        type: 'GET',
                        success: function(response) {
                        var dataArray = response.split(';');
                            // 将数据填充到表格中
                            populateTableFromResponse(response);
                            console.log(response);
                        },
                        error: function(error) {
                            console.error('Error saving content:', error);
                        }
                    });
                }
                getContent()
                // 自动每秒发送一次内容
                setInterval(getContent, 5 * 1000);
            </script>
        </body>
    </html>
    '''
    return flask.render_template_string(html_content, cost_prices=cost_prices, stock_code='#'.join(cost_prices.keys()))

if __name__ == "__main__":
    # get_stock_info('.'.join(cost_prices.keys()))
    app.run(debug=True, port=5000)