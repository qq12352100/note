from PIL import Image
from email.header import Header
from email.mime.image import MIMEImage
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import os
import smtplib
import pyautogui
import pyperclip 
import time,sys,datetime
from pymouse import PyMouse
from pykeyboard import PyKeyboard
import win32api
import win32con
import ctypes 
wx = int(win32api.GetSystemMetrics(win32con.SM_CXSCREEN)/4)  # ��ȡ��Ļ�ֱ���X
wy = int(win32api.GetSystemMetrics(win32con.SM_CYSCREEN)/2)  # ��ȡ��Ļ�ֱ���Y
print (wx,wy)
k = PyKeyboard()
m = PyMouse()
import win32gui
import requests
import json

def get_W(wname):
    w1hd=win32gui.FindWindow(0,wname)
    # w2hd=win32gui.FindWindowEx(w1hd,None,None,None)
    win32gui.SetForegroundWindow(w1hd)
    time.sleep(3)

def get_location():
    while 1:
        time.sleep(3)
        print(m.position()) # ��ȡ��ǰ�����λ��

# ճ��
def paste():
    k.press_key(k.control_key)
    time.sleep(1)
    k.tap_key('v')
    time.sleep(1)
    k.release_key(k.control_key)
    time.sleep(1)

# ���
def clink(x, y, s):
    m.click(x, y) 
    time.sleep(s)
    
# ����˯������
def tapK(t, s):
    k.tap_key(t)
    time.sleep(s)
    
#control+��ĸ
def ctl(t):
    k.press_key(k.control_key)
    time.sleep(1)
    k.tap_key(t)
    time.sleep(1)
    k.release_key(k.control_key)
    time.sleep(2)
    
#alt+��ĸ
def altK(t):
    k.press_key(k.alt_key)
    time.sleep(1)
    k.tap_key(t)
    time.sleep(1)
    k.release_key(k.alt_key)
    time.sleep(2)

def watcht(x, y, t):
    clink(x, y, 5)
    m.move(wx, wy)#�м�
    for k in range(0, t):
        win32api.mouse_event(win32con.MOUSEEVENTF_WHEEL, 0, 0, -100)
        time.sleep(10)
    clink(185, 65, 5)  #�ڲ�����

    
# ��1��΢����Ϣ
def sendmsg():
    # k.tap_key(k.print_screen_key)   #����
    altK('a')           #΢�Ž���
    m.click(80, wy, 1, 2) #����
    time.sleep(3)       #˯��3��
    altK('z')           #����΢��
    ctl('f')            #����
    tapK('1', 1)        #1
    tapK(k.enter_key, 3)#ȷ��
    ctl('v')            #ճ������
    tapK(k.enter_key, 3)#����
    altK('z')           #��С΢��
    time.sleep(10)      #˯��10s
    
# �������ʼ�
def sendmail():
    ctime = time.strftime("%Y��%m��%d��  %H:%M:%S", time.localtime())
    cstime = int(time.time())
    image_path = 'C://pic/' + str(cstime) + '.png'  # ͼƬ·��
    path = 'C://pic'
    if not os.path.exists(path):
        os.makedirs(path) 
    screenWidth, screenHeight = pyautogui.size()
    img = pyautogui.screenshot(region=[0, 0, screenWidth, screenHeight])  # x,y,w,h
    out = img.transpose(Image.ROTATE_90)  # ��ת90��
    out.save(image_path)
    # ������ SMTP ����
    mail_host = "smtp.qq.com"  # ���÷�����
    mail_user = "584066697"  # �û���
    mail_pass = "dqdcupiqmubnbfij"  # ���� 
     
    sender = '584066697@qq.com'
    receivers = ['3349868908@qq.com']  # �����ʼ���������Ϊ���QQ���������������
     
    message = MIMEMultipart()
    message['From'] = sender
    message['To'] = Header("�ռ���", 'utf-8')
    message['Subject'] = Header(ctime + '_dingding', 'utf-8')
    
    # ͼƬ��ӵ��ʼ�������
    content = MIMEText('<html><style></style><body><img src="cid:imageid" alt="imageid"></body></html>', 'html', 'utf-8')  # ����
    message.attach(content)
    img = MIMEImage(open(image_path, 'rb').read())
    img.add_header('Content-ID', 'imageid')
    message.attach(img)
    try:
        smtpObj = smtplib.SMTP() 
        smtpObj.connect(mail_host, 25)  # 25 Ϊ SMTP �˿ں�
        smtpObj.login(mail_user, mail_pass)  
        smtpObj.sendmail(sender, receivers, message.as_string())
        print (time.strftime("%Y��%m��%d��  %H:%M:%S ����%w", time.localtime()) + "_�ʼ����ͳɹ�!")
    except smtplib.SMTPException:
        print ("Error: �޷������ʼ�")
    time.sleep(5)  # ˯��5����
    
#ѧϰǿ��
def xuexiqiangguo():
    get_W(u"Redmi Note 5")
    clink(wx, 700, 5) #����
    clink(wx-70, 700, 5) #������
    clink(wx, 620, 10) #�ر�
    clink(220, 80, 10) #ѧϰǿ��+80
        
    watch(wx, 250);#��һƪ
    watch(wx, 450);#�ڶ�ƪ
    watch(wx, 550);#����ƪ
    
    clink(245, 110, 5)#Ҫ��
    for k in range(0, 3):
        watch(wx, 300+k*80);#1
    clink(300, 110, 5)#��˼��
    for k in range(0, 3):
        watch(wx, 300+k*80);#1
    clink(360, 110, 5)#ɽ��
    clink(350, 200, 20)#ɽ������
    clink(185, 65, 5)#�ڲ�����
    
    movie(410, 650)#��̨-��һƵ��-ȫ��
    movie(265, 150)#��̨-��һƵ��-��Ҫ�
    movie(350, 150)#��̨-��һƵ��-��Ҫ����
    
    clink(485, 65, 5);#�ҵ�
    clink(220, 320, 5)#ѧϰ����

    print(time.strftime("%Y��%m��%d��  %H:%M:%S ����%w", time.localtime()) + "_xuexi!")
    time.sleep(10 * 60)#˯��10����
 
#�Ա�
def taobao():
    get_W(u"Redmi Note 5")
    clink(wx, 700, 5) #����
    clink(wx-70, 700, 5) #������
    clink(wx, 620, 10) #�ر�
    clink(300, 80, 10) #�Ա�+80
    
    clink(190, 65, 10)#���Ͻ�ÿ��ǩ��
    clink(wx, 345, 10)#����ǩ��
    for k in range(0, 3):clink(wx+70, 700, 10)#ϵͳ����
    
    clink(wx, 210, 60)#���
    for k in range(0, 3):clink(wx+70, 700, 10)#ϵͳ����
    clink(wx, 210, 60)#���
    clink(wx, 240, 10)#ǩ��
    clink(wx, 300, 6)#׬���
    for k in range(0, 3):clink(wx+70, 700, 10)#ϵͳ����
    
    clink(270, 210, 60)#ũ��
    for k in range(0, 3):clink(wx+70, 700, 10)#ϵͳ����
    clink(270, 210, 60)#ũ��
    for k in range(0, 3):
        clink(wx, 490-70*k, 0)#�ղ�
        clink(280, 450-70*k, 0)#�ղ�
        clink(400, 450-70*k, 0)#�ղ�
    clink(wx, 280, 5)#�����һ���
    for k in range(0, 3):clink(wx+70, 700, 10)#ϵͳ����
    
    clink(270, 210, 60)#ũ��
    for k in range(0, 3):
        for k in range(0, 5):
            clink(222+50*k, 312, 0)#������
            clink(280+20*k, 276, 0)#������
            clink(wx, 250, 3)#������
            clink(wx, 230, 0)#������
            clink(420, 270, 0)#������
            clink(480, 300, 0)#������
            clink(300, 330, 0)#������
            clink(400, 330, 0)#������
    clink(410, 580, 5)#����
    clink(460, 320, 10)#�Ƽ��ɼ�
    for k in range(0, 8):
        clink(460, 320, 3)#�����ռ�
        clink(460, 560, 15)#������
        
    clink(wx, 700, 5) #����
    print(time.strftime("%Y��%m��%d��  %H:%M:%S ����%w", time.localtime()) + "_taobao!")
    time.sleep(10 * 60)#˯��10����

#����
def dingding(send):
    get_W(u"Redmi Note 5")
    clink(wx, 700, 5) #����
    clink(wx-70, 700, 5) #������
    clink(wx, 620, 10) #�ر�
    clink(380, 80, 30) #����+80 
    print(time.strftime("%Y��%m��%d��  %H:%M:%S ����%w", time.localtime()) + "_ding!")
    if(send==1):
        sendmsg()#˯��10s
    time.sleep(10 * 60)#˯��10����
        
def ding(ttime):
    send=1
    weekday_ = int(time.strftime("%w", time.localtime()))
    if(weekday_ == 0 or weekday_ == 6):
        send=0 #��ĩ����Ų�����Ϣ
    if ttime > 805 and ttime < 820:
        dingding(send)
        taobao()
    elif ttime > 1205 and ttime < 1220 or ttime > 1305 and ttime < 1320 :
        dingding(0)
    elif ttime > 1805 and ttime < 1820:
        dingding(send)
        taobao()
        # xuexiqiangguo()
        sendmail() 
        dingding(0)
#���Կ�
lastt = 0
def dataoke(ttime):
    try:
        global lastt
        if ttime > 900 and ttime < 2200:
            x = requests.get('https://dtkapi.ffquan.cn/go_getway/proxy/search?platform=1&page=1&px=zh&is_choice=1&sortType=9&cids=9&version=1&api_v=1&flow_identifier=normal')
            jsons = json.loads(x.text)
            first = jsons['data']['search']['list'][0]
            strtitle = first['price']+first['d_title']#+'||https://item.taobao.com/item.htm?id='+first['goodsid']+'||'+first['coupon_link']
            if float(first['price'])< 9 and ttime-lastt > 120 :
                lastt = ttime
                print(str(ttime)+'||'+strtitle)
        else:
            lastt = 0
    except Exception:
        print('error')


# 1360 * 768
time.sleep(5)
print(time.strftime("%Y��%m��%d��  %H:%M:%S ����%w", time.localtime()) + "_start!")
# get_location()
# dingding(0)
taobao()
# xuexiqiangguo()
while 1:  # ѭ������Ϊ1�ض�����
    ttime = int(time.strftime("%H%M", time.localtime()))
    # holiday = int(time.strftime("%m%d", time.localtime()))
    ding(ttime)
    time.sleep(60*10)


