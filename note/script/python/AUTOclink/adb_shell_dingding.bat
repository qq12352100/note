@echo off
:: ���ô��ڴ�С����ɫ
mode con cols=80 lines=20 & color 0c

:: �л���ָ��Ŀ¼
D:
cd /A/scrcpy-win64-v3.2

:: ִ�����̺���-�����̨����
call :process_flow

:: �򿪶������������ͼ��
adb shell monkey -p com.alibaba.android.rimet -c android.intent.category.LAUNCHER 1
timeout /t 10 /nobreak

:: ���ǩ�򿨣�������ǩ��λ��
call :tap_screen 15 752 381

:: �м�򿨣�����м��λ�ã���Ҫ�������꣩
call :tap_screen 1 534 1297

:: ��ͣ�ű����ȴ��û�ȷ��
pause

exit /b

:: ����һ��ͨ�ú���������ģ����Ļ�������
:tap_screen
    set delay=%1
    set x=%2
    set y=%3
    adb shell input tap %x% %y%
    timeout /t %delay% /nobreak
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


