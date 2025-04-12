:: 设置一个全局变量
set globalVar=这是全局变量的值

:: 开始局部环境
setlocal
:: 修改全局变量
set globalVar=这是局部环境中的新值
echo 在局部环境中: globalVar=%globalVar%

:: 结束局部环境
endlocal

:: 检查全局变量的值是否恢复
echo 在局部环境外: globalVar=%globalVar%