::����@ʱ��������ʱ�����ڴ�����ʾ����������,������@, ֻ����ʾ�� echo������Ҫ��ʾ���Ķ���
@echo off 
@echo --  �������       -- 	rem ���������������

::dir c:\*.* >a.txt 			rem ��c���������ļ��������a.txt�У����û��a�ļ�������batͬһĿ¼�´���һ��

::md D:\test  					rem ��D���´���test�ļ���

::start http://www.baidu.com  	rem Ĭ�����������ҳ

cd /d %~dp0 					rem �򿪵�ǰ·��
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