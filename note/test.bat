::����� Windows �ⲿ����� dir, ping, ipconfig �ȣ������ִ�Сд
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
timeout /t 10 /nobreak              rem ˯��10s   /NOBREAK        ���԰������ȴ�ָ����ʱ�䣨��ʱ�ڼ䣬�������û���������ն���ʱ����

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

:: �´������� /k ִ����ָ��������󣬲��ر�������ʾ�����ڡ� /c ִ����ָ��������󣬹ر�������ʾ�����ڡ� /b ��������������������µĴ��ڡ�
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
-------------cmdҪ�Թ���Ա����----------����ӷ��� �� NSSM��װ���� https://nssm.cc/��
sc create frp_service binPath= "D:\AAA\frp_0.57.0_windows_amd64\frpc.exe -c D:\AAA\frp_0.57.0_windows_amd64\frpc.toml"
sc config frp_service start=AUTO    rem AUTO(�Զ�)DEMAND(�ֶ�)DISABLED(����)
sc description frp_service "This service runs the frp client"

net start MyFRPService      rem ��������
sc query MyFRPService       rem �鿴�����Ƿ������ɹ�
net stop MyFRPService       rem ֹͣ����
sc delete MyFRPService      rem ɾ������



sc config MyService start= [auto| demand | disabled | boot | system] rem auto����ϵͳ����ʱ�Զ�������demand�������Ϊ�ֶ�����������Ҫ�ֶ���������disabled�����񱻽��ã������Զ�������Ҳ�޷��ֶ�������boot����ϵͳ����ʱ������system����ϵͳ��ʼ��ʱ������
sc config MyService binPath= [PathToExecutable]                      rem ָ������Ŀ�ִ���ļ���·����
sc config MyService obj= [UserOrGroup]                               rem ָ����������ʱ��ʹ�õ��û������ʻ���
sc config MyService tagBits= [TagBits]                               rem ���÷���ı�ǩλ��
sc config MyService display= [DisplayName]                           rem ���÷������ʾ���ơ�
sc config MyService error= [normal| severe| critical| ignore]        rem ���÷���ʧ��ʱ��ϵͳ��Ӧ��normal��Ĭ�϶�����severe�����ش���critical����������ignore�����Դ���
sc config MyService group= [LoadOrderGroup]                          rem ���÷���ļ���˳���顣
sc config MyService depend= [DependentServiceNames]                  rem ���÷��������������������ơ�
sc config MyService Type= [ownShare| share| interact| kernel| kernelDriver| fileSystemDriver] rem ownShare������ӵ���Լ��Ľ��̿ռ䡣share��������������������̿ռ䡣interact��������������潻����kernel���ں���������kernelDriver���ں���������fileSystemDriver���ļ�ϵͳ��������

����https://nssm.cc/����ѹ֮�����Ա�������cmd�л�����ѹĿ¼
nssm install FrpService "D:\AAA\frp_0.57.0_windows_amd64\frpc.exe" -c "D:\AAA\frp_0.57.0_windows_amd64\frpc.toml"

NSSM: The non-sucking service manager
Version 2.24 64-bit, 2014-08-31
Usage: nssm <option> [<args> ...]

To show service installation GUI:
        nssm install [<servicename>]
To install a service without confirmation:
        nssm install <servicename> <app> [<args> ...]
To show service editing GUI:
        nssm edit <servicename>
To retrieve or edit service parameters directly:
        nssm get <servicename> <parameter> [<subparameter>]
        nssm set <servicename> <parameter> [<subparameter>] <value>
        nssm reset <servicename> <parameter> [<subparameter>]
To show service removal GUI:
        nssm remove [<servicename>]
To remove a service without confirmation:
        nssm remove <servicename> confirm
To manage a service:
        nssm start <servicename>
        nssm stop <servicename>
        nssm restart <servicename>
        nssm status <servicename>
        nssm rotate <servicename>














