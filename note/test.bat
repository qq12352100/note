::����@ʱ��������ʱ�����ڴ�����ʾ����������,������@, ֻ����ʾ�� echo������Ҫ��ʾ���Ķ���
@echo off 
@echo --  �������       -- 	rem ���������������

:: ��c���������ļ��������a.txt�У����û��a�ļ�������batͬһĿ¼�´���һ��
rem dir c:\*.* >a.txt 

::md D:\test  					rem ��D���´���test�ļ���

set /p var=����һ����:
:: EQU ���� || NEQ ������ || LSS С�� || LEQ С�ڻ���� ||  GTR ���� || GEQ ���ڻ����
if %var% EQU 1 (
	goto FIRST 
) else (
	goto SECOND 
)


cd /d %~dp0 					rem �򿪵�ǰ·��
 
GOTO SECOND
:FIRST
@echo I AM FIRST
:SECOND
@echo I AM SECOND

set a=aa1bb1aa2bb2
@echo %a%
set b=12
@echo %b%
set /a c=39/10           
@echo %c%

@echo %CD%						rem ����ǰĿ¼���ַ���
@echo %DATE%					rem ��ǰ����
@echo %TIME%					rem ��ǰʱ��



pause