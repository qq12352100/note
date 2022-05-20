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

def get_W(wname):
    w1hd=win32gui.FindWindow(0,wname)
    # w2hd=win32gui.FindWindowEx(w1hd,None,None,None)
    win32gui.SetForegroundWindow(w1hd)
    time.sleep(3)

def get_location():
    while 1:
        time.sleep(3)
        print(m.position()) # ��ȡ��ǰ�����λ��
        
def paste():
    k.press_key(k.control_key)
    time.sleep(1)
    k.tap_key('v')
    time.sleep(1)
    k.release_key(k.control_key)
    time.sleep(1)
    
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
        
def rolls(t):
    m.move(wx, wy)#�м�
    for k in range(0, t):
        win32api.mouse_event(win32con.MOUSEEVENTF_WHEEL, 0, 0, -100)
        time.sleep(1)

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
    message['From'] = Header("������", 'utf-8')
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
    for k in range(0, 3):
        watch(wx, 300+k*80);#1
    clink(300, 110, 5)#��˼��
    
    clink(220, 320, 5)#ѧϰ����

    print(time.strftime("%Y��%m��%d��  %H:%M:%S ����%w", time.localtime()) + "_xuexi!")
    time.sleep(10 * 60)#˯��10����

def ding(ttime):
    send=1
    weekday_ = int(time.strftime("%w", time.localtime()))
    if(weekday_ == 0 or weekday_ == 6):
        send=0 #��ĩ����Ų�����Ϣ
    if ttime > 705 and ttime < 720:
        taobao()
    elif ttime > 805 and ttime < 820:
        dingding(send)
    elif ttime > 1205 and ttime < 1220 or ttime > 1305 and ttime < 1320 :
        dingding(0)
    elif ttime > 1805 and ttime < 1820:
        dingding(send)
        taobao()
        xuexiqiangguo()
        sendmail() 

# 1360 * 768
time.sleep(5)
print(time.strftime("%Y��%m��%d��  %H:%M:%S ����%w", time.localtime()) + "_start!")
# get_location()
while 1:  # ѭ������Ϊ1�ض�����
    time.sleep(60*10)
    ttime = int(time.strftime("%H%M", time.localtime()))
    # holiday = int(time.strftime("%m%d", time.localtime()))
    ding(ttime)


