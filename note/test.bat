::����@ʱ��������ʱ�����ڴ�����ʾ����������,������@, ֻ����ʾ�� echo������Ҫ��ʾ���Ķ���
@echo off 
@echo --  �������       -- 	rem ���������������
::dir c:\*.* >a.txt 			rem ��c���������ļ��������a.txt�У����û��a�ļ�������batͬһĿ¼�´���һ��
::md D:\test  					rem ��D���´���test�ļ���
::start http://www.baidu.com  	rem Ĭ�����������ҳ
::cd /d %~dp0 					rem �򿪵�ǰ·��
@echo %AllUsersProfile%			rem	C:\ProgramData
@echo %tmp%						rem	C:\Users\admin\AppData\Local\Temp
@echo %AppData%					rem	C:\Users\admin\AppData\Roaming
@echo %CommonProgramFiles%		rem	C:\Program Files\Common Files
@echo %UserProfile%				rem	C:\Users\admin
@echo %CD%						rem ����ǰĿ¼���ַ���
@echo %DATE%					rem ��ǰ����
@echo %TIME%					rem ��ǰʱ��

set /p var=����һ����:
if %var% EQU 1 (				rem EQU ���� || NEQ ������ || LSS С�� || LEQ С�ڻ���� ||  GTR ���� || GEQ ���ڻ����
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

pause

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
