@echo off
:: 设置窗口大小和颜色
mode con cols=80 lines=20 & color 0c

:: 切换到指定目录
D:
cd /A/scrcpy-win64-v3.2

:: 执行流程函数-清理后台任务
call :process_flow

:: 打开钉钉：点击钉钉图标
call :tap_screen 10 667 195
:: 横标签打卡：点击横标签打卡位置
call :tap_screen 20 752 381
:: 中间打卡：点击中间打卡位置（需要补充坐标）
call :tap_screen 1 534 1297

:: 暂停脚本，等待用户确认
pause

exit /b

:: 定义一个通用函数，用于模拟屏幕点击操作
:tap_screen
    set delay=%1
    set x=%2
    set y=%3
    adb shell input tap %x% %y%
    timeout /t %delay% /nobreak
exit /b

:: 定义一个流程函数，封装主菜单、多任务、关闭等操作
:process_flow
    :: 主菜单：点击主菜单按钮
    call :tap_screen 1 540 2100

    :: 多任务：点击多任务按钮
    call :tap_screen 1 316 2090

    :: 关闭：关闭当前应用
    call :tap_screen 3 542 1880
exit /b


