@echo off
:: ���ô��ڴ�С����ɫ
mode con cols=80 lines=20 & color 0c

:: �л���ָ��Ŀ¼
D:
cd /A/scrcpy-win64-v3.2

REM adb tcpip 5555
REM adb connect 192.168.3.100:5555

:: ִ�����̺���-�����̨����
REM call :process_flow
call :back3time

:: �򿪶������������ͼ��
adb shell monkey -p com.alibaba.android.rimet -c android.intent.category.LAUNCHER 1
timeout /t 3 /nobreak

:: ���ǩ�򿨣�������ǩ��λ��
call :clink_t_x_y 5 752 381

:: �м�򿨣�����м��λ��
call :clink_t_x_y 1 534 1297

:: ��ͣ�ű����ȴ��û�ȷ��
REM pause

exit /b

:: ����һ��ͨ�ú���������ģ����Ļ�������
:clink_t_x_y
    set delay=%1
    set x=%2
    set y=%3
    adb shell input tap %x% %y%
    timeout /t %delay% /nobreak
exit /b

::��������
:back3time
    set count=3
    for /L %%i in (1,1,%count%) do (
        adb shell input keyevent KEYCODE_BACK
        timeout /t 1 /nobreak >nul
    )
exit /b

:: ����һ�����̺�������װ���˵��������񡢹رյȲ���
:process_flow
    :: ���˵���������˵���ť   # �ص�����Ļ
    adb shell input keyevent KEYCODE_HOME
    timeout /t 1 /nobreak

    :: �����񣺵��������ť   # �˵���
    adb shell input keyevent KEYCODE_MENU
    timeout /t 1 /nobreak

    :: �رգ��رյ�ǰӦ��
    call :tap_screen 1 542 1880
exit /b


