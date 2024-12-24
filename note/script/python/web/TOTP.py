#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""

pip install pyotp

pyotp 是一个用于实现基于时间的一次性密码（TOTP）和基于 HMAC 的一次性密码（HOTP）的 Python 库，常用于双因素认证（2FA）。

"""
import flask
import pyotp

bljsecret = 'EVSCWRCPLAXUKZHBYRB4EAKZKSPT6OIX'  # 堡垒机
yaxsecret = 'FNNH2PTGW7Y55VTAPI3C3HSO6R5KEZLICTI2DEYEEKXOXJ5QXRBYKCXZAKZ6VV57' # 亚马逊

def hgetkey(s):
    totp = pyotp.TOTP(s)
    return totp.now()

app = flask.Flask(__name__)

#http://127.0.0.1:5000/getkey
@app.route('/getkey', methods=['GET'])
def getkey():
    return '''
    <html>
        <head>
            <title>MFA-TOTP</title>
        </head>
        <body style="text-align: center;margin: 20% 5%;">
            <h1>堡垒机：'''+hgetkey(bljsecret)+'''</h1>
            <h1>亚马逊：'''+hgetkey(yaxsecret)+'''</h1>
        </body>
    </html>
    '''

# 返回json格式
@app.route('/getkeyjson', methods=['GET'])
def getkeyjson():
    return hgetkey(bljsecret)
    
if __name__ == '__main__':
    app.run(debug=True)