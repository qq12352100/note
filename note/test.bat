::Ĭ�ϱ����ʽΪANSI���� GB2312 ���롣
::����@ʱ��������ʱ�����ڴ�����ʾ����������,������@, ֻ����ʾ�� echo������Ҫ��ʾ���Ķ���
mode con cols=80 lines=20&color 0c  rem  cols�� lines��  cmd�������:help color ��鿴
TITLE Tomcat                        rem  ���ô��ڱ���
cmdow @ /top /mov 500 500           rem  �ƶ����ڵ�ĳ��λ�� ���cmdow����
@echo off 
@echo --  �������       --         rem ���������������
::dir c:\*.* >a.txt                 rem ��c���������ļ��������a.txt�У����û��a�ļ�������batͬһĿ¼�´���һ��
::md D:\test                        rem ��D���´���test�ļ���
::start http://www.baidu.com        rem Ĭ�����������ҳ
::cd /d %~dp0                       rem �򿪵�ǰ·��
@echo %AllUsersProfile%             rem    C:\ProgramData
@echo %tmp%                         rem    C:\Users\admin\AppData\Local\Temp
@echo %AppData%                     rem    C:\Users\admin\AppData\Roaming
@echo %CommonProgramFiles%          rem    C:\Program Files\Common Files
@echo %UserProfile%                 rem    C:\Users\admin
@echo %CD%                          rem ����ǰĿ¼���ַ���
@echo %DATE%                        rem ��ǰ����
@echo %TIME%                        rem ��ǰʱ��

set /p var=����һ����:
if %var% EQU 1 (                    rem EQU ���� || NEQ ������ || LSS С�� || LEQ С�ڻ���� ||  GTR ���� || GEQ ���ڻ����
    goto FIRST 
) else (
    goto SECOND 
)
:FIRST
@echo I AM FIRST
:SECOND
@echo I AM SECOND
:SECOND2
@echo I AM SECOND2

set a=aa1bb1aa2bb2
@echo %a%
set b=12
@echo %b%
set /a c=39/10           
@echo %c%

for  %%I in (A,B,C) do echo %%I     rem ѭ��
for /f "skip=1 tokens=9 delims= " %a in ('ping 172.20.123.231') do @echo %a
skip=1 #���Ե�һ�У�Ĭ����ʾ������
tokens=9 #��ʾ��9�� ������awk�� ����$9
delims= #�ָ��� ���ηָ���Ϊһ���ո�
ping 172.20.123.231 #ѭ��������������
pause

:: �´������� /k ���رմ��� /c �رմ���
start cmd /k "c: && cd C:\Project\test-app-1 && npm start"
:: �´����ӳ� 5 ������ test-app-2, 
start cmd /k "timeout -nobreak 5 && c: && cd C:\Project\test-app-2 && npm start"
:: ��ȡ����ԱȨ��
runas /user:administrator cmd
:: �ж��ļ��Ƿ����
if exist %SourceFile% (
    if not exist %GenFile1% (
) else (
    //��Ҫ��������
) 


netsh -c interface ip dump
netsh interface ip show config
netsh interface ip set address "��������" static 37.136.6.210 255.255.255.0 37.136.6.254 1  rem ��������IP ���� ����
netsh interface set interface "��������" disabled rem ��������
netsh interface set interface "��������" enabled  rem ��������
ncpa.cpl
::Usage����һ������ | �ڶ������� [| ����������...]����һ������Ľ����Ϊ�ڶ�������Ĳ�����ʹ�ã��ǵ���unix�����ַ�ʽ�ܳ�����
::Usage����һ������ || �ڶ������� [|| ����������...]�����ַ�������ͬʱִ�ж������������ִ����ȷ������󽫲�ִ�к����������û�г�����ȷ��������һֱִ�����������
::Usage����һ������ & �ڶ������� [& ����������...]�����ַ�������ͬʱִ�ж�����������������Ƿ�ִ�гɹ�
::Usage����һ������ && �ڶ������� [&& ����������...]�����ַ�������ͬʱִ�ж������������ִ�г��������󽫲�ִ�к����������һֱû�г�����һֱִ�����������
--------------------------------------------------һ��һ��ping�������ʱ5�����һ��������ʱ����#����echoһ�������Ļһ��������ļ�
@echo off 
cd C:\Windows\System32
set host=172.20.123.231
set logfile=D:\ping_%host%.log
if exist %logfile% (
    del %logfile%
)
:loop
for /f "tokens=* skip=2" %%A in ('ping %host% -n 1') do (
    echo %date:~0,-3% %time:~0,-3% %%A >> %logfile%
    echo %date:~0,-3% %time:~0,-3% %%A
    timeout /t 1 /nobreak > nul
    goto loop
)
--------------------------------------------------��ӷ��� �� NSSM��װ���� https://nssm.cc/
sc create ������ binPath= "C:\win32srvDemo.exe"
sc config ������ start=AUTO   rem AUTO(�Զ�)DEMAND(�ֶ�)DISABLED(����)
sc description Redis6.2.6 "This service runs the Redis server"



