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

# 判断是上交所还是深交所的代码
def gettype(stock_code):
    if stock_code.startswith(('5', '6', '9')):
        prefix = 'sh'
    else:
        prefix = 'sz'
    return prefix
    
def get_stock_info(stock_code):
    prefix = gettype(stock_code)
    
    url = f"http://qt.gtimg.cn/q={prefix}{stock_code}"
    response = requests.get(url)
    
    # 解析返回的数据
    data_list = response.text.split('~')
    
    # 创建一个字典以更友好地表示数据
    stock_info = {
        'name': data_list[1],  # 股票名称
        'code': data_list[2],  # 股票代码
        'current_price': float(data_list[3]),  # 当前价格
        'yesterday_close': float(data_list[4]),  # 昨日收盘价
        'today_open': float(data_list[5]),  # 今日开盘价
        'volume': int(data_list[6]),  # 成交量
        'turnover': float(data_list[7]),  # 成交金额
# 0: 未知 1: 名字 2: 代码 3: 当前价格 4: 昨收 5: 今开 6: 成交量（手） 7: 外盘 8: 内盘 9: 买一 10: 买一量（手） 11-18: 买二 买五 19: 卖一 20: 卖一量 21-28: 卖二 卖五 29: 最近逐笔成交 30: 时间 31: 涨跌 32: 涨跌% 33: 最高 34: 最低 35: 价格/成交量（手）/成交额 36: 成交量（手） 37: 成交额（万） 38: 换手率 39: 市盈率 40: 41: 最高 42: 最低 43: 振幅 44: 流通市值 45: 总市值 46: 市净率 47: 涨停价 48: 跌停价
    }
    '''
    s_
    stock_info = {
        'status': data_list[0],
        'name': data_list[1],  # 股票名称
        'code': data_list[2],  # 股票代码
        'current_price': float(data_list[3]),  # 当前价格
        'change_amount': float(data_list[4]),  # 涨跌额
        'change_percent': float(data_list[5]),  # 涨跌幅
        'volume': int(data_list[6]),  # 成交量 (手)
        'turnover': float(data_list[7]),  # 成交金额 (万元)
        'market_value': float(data_list[8]) if len(data_list) > 8 and data_list[8] else None,  # 总市值 (如果存在)
        'type': data_list[-1] if data_list[-1] else None  # 股票类型
    }
    '''
    return stock_info
    
#http://127.0.0.1:5000/getstock
@app.route('/getstock', methods=['GET'])
def getstock():
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
                width: 95%;
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
            <div><input id="codenum" style="width: 100%;" value="002230.159998.512710.600028.600547.601989"></input></div>
            <div id="contentArea" >
                <table id="stockTable" border="1">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>代码</th>
                            <th>当前价格</th>
                            <th>涨跌额</th>
                            <th>涨跌幅</th>
                            <th>成交量(手)</th>
                            <th>成交金额(万元)</th>
                            <th>总市值(亿元)</th>
                            <th>类型</th>
                        </tr>
                    </thead>
                    <tbody>
                    <!-- 行将在这里动态添加 -->
                    </tbody>
                </table>
            </div>
            <script>

                // 将数据填充到表格中
               function populateTableFromResponse(response) {
                    // 分割响应字符串并移除可能存在的最后一个空元素
                    var dataArray = response.split(';').filter(item => item.trim() !== '');

                    var $tbody = $('#stockTable tbody').empty(); // 清空现有内容
                    
                    dataArray.forEach(function(item) {
                        // 解析单个数据字符串
                        if (item.startsWith('v_')) {
                            item = item.split('=')[1].replace(/"/g, '').trim();
                        }

                        // 拆分字符串成列表
                        var dataList = item.split('~');

                        // 确保关键字段存在且有效
                        if (dataList.length >= 9 && dataList[1] && dataList[2]) {
                            // 根据涨跌幅设置行的颜色
                            var changePercent = parseFloat(dataList[5]);
                            var rowClass = changePercent < 0 ? 'negative' : 'positive';

                            // 直接构建并添加行到表格
                            var row = `<tr class="${rowClass}">
                                    <td>${dataList[1]}</td> <!-- 名称 -->
                                    <td>${dataList[2]}</td> <!-- 代码 -->
                                    <td>${parseFloat(dataList[3]).toFixed(2)}</td> <!-- 当前价格 -->
                                    <td>${parseFloat(dataList[4]).toFixed(2)}</td> <!-- 涨跌额 -->
                                    <td>${parseFloat(dataList[5]).toFixed(2)}%</td> <!-- 涨跌幅 -->
                                    <td>${parseInt(dataList[6]).toLocaleString()}</td> <!-- 成交量 (手) -->
                                    <td>${parseFloat(dataList[7]).toFixed(2)}</td> <!-- 成交金额 (万元) -->
                                    <td>${parseFloat(dataList[8]) ? parseFloat(dataList[8]).toFixed(2) : 'N/A'}</td> <!-- 总市值 (如果存在) -->
                                    <td>${dataList[dataList.length - 1]}</td> <!-- 类型或其他标识 -->
                                </tr>`;
                            $tbody.append(row);
                        }
                    });
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
    return flask.render_template_string(html_content)

if __name__ == "__main__":
    # stock_code = "600028"  # 中国石化的股票代码
    # info = get_stock_info(stock_code)
    # print(info)
    app.run(debug=True, port=5000)