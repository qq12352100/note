@echo off
:: ���ñ���
set "host=10.100.255.254"  :: Ŀ������IP��ַ
set "logdir=D:\logs"       :: ��־Ŀ¼
set "logfile=%logdir%\ping_%host%_%date:~0,4%-%date:~5,2%-%date:~8,2%.log"  :: ��־�ļ���
set "max_loop=36000"        :: ���ѭ����������ֹ�������У�
set "interval=1"           :: ÿ�� ping �ļ��ʱ�䣨�룩

:: ������־Ŀ¼����������ڣ�
if not exist "%logdir%" (
    mkdir "%logdir%"
)

:: ɾ���ɵ���־�ļ���������ڣ�
if exist "%logfile%" (
    del "%logfile%"
)

:: ��ʼ��������
set /a count=0

echo 1 >> "%logfile%"
:loop
:: ����Ƿ�ﵽ���ѭ������
if %count% geq %max_loop% (
    echo [INFO] �ﵽ���ѭ���������ű��Զ��˳���
    goto end
)

for /f "tokens=* skip=2" %%A in ('ping %host% -n 1') do (
    :: ��� ping ����Ƿ�Ϊ��
    if "%%A"=="" (
        echo [ERROR] ping ���Ϊ��
    ) else (
        echo %date:~0,-3% %time:~0,-3% %%A >> %logfile%
        echo %date:~0,-3% %time:~0,-3% %%A
        timeout /t 1 /nobreak > nul
        set /a count+=1
    )
    goto loop
)

:end
echo [INFO] �ű����н�����