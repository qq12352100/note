#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
nohup python3 app.py > log.file 2>&1 &
"""

from flask import Flask
import TOTP
import online_note

app = Flask(__name__)

# MFA 设备验证器 
app.add_url_rule('/getkey', 'TOTP.getkey', TOTP.getkey) # 主页 /getkey
app.add_url_rule('/getkeyjson', 'TOTP.getkeyjson', TOTP.getkeyjson) #返回json
# 在线笔记本
app.add_url_rule('/note/<path:subpath>', 'online_note.note', online_note.note) # 主页 /note/bkk
app.add_url_rule('/save_content', 'online_note.save_content', online_note.save_content, methods=['POST']) # 每隔10秒推送一次

if __name__ == '__main__':
    app.run()