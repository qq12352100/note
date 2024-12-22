# -*- coding: utf-8 -*-
import pyotp

bljsecret = 'EVSCWRCPLAXUKZHBYRB4EAKZKSPT6OIX'  # 替换为你的实际秘密密钥
yaxsecret = 'FNNH2PTGW7Y55VTAPI3C3HSO6R5KEZLICTI2DEYEEKXOXJ5QXRBYKCXZAKZ6VV57'

def getkey(s):
    totp = pyotp.TOTP(s)
    # 获取当前的一次性密码
    current_otp = totp.now()
    return current_otp

from flask import Flask, jsonify, request

app = Flask(__name__)

#http://127.0.0.1:5000/getkey
@app.route('/getkey', methods=['GET'])
def hgetkey():
    return '''
    <html>
        <head>
            <title>MFA-TOTP</title>
        </head>
        <body style="text-align: center;margin: 20% 5%;">
            <h1>堡垒机：'''+getkey(bljsecret)+'''</h1>
            <h1>亚马逊：'''+getkey(yaxsecret)+'''</h1>
        </body>
    </html>
    '''

@app.route('/getkeyjson', methods=['GET'])
def getkeyjson():
    return getkey(bljsecret)
    
if __name__ == '__main__':
    app.run(debug=True)  # 在本地运行，并启用调试模式 