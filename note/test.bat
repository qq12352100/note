::不加@时，在运行时，会在窗口显示出这条命令,而加了@, 只会显示出 echo后面你要显示出的东西
@echo off 
@echo --  输出文字       -- 	rem 跟在输出后面会输出

::dir c:\*.* >a.txt 			rem 将c盘下所有文件名输出到a.txt中，如果没有a文件，则在bat同一目录下创建一个

::md D:\test  					rem 在D盘下创建test文件夹

::start http://www.baidu.com  	rem 默认浏览器打开网页

cd /d %~dp0 					rem 打开当前路径
@echo %CD%						rem 代表当前目录的字符串
@echo %DATE%					rem 当前日期
@echo %TIME%					rem 当前时间

set /p var=输入一个数:
if %var% EQU 1 (				rem EQU 等于 || NEQ 不等于 || LSS 小于 || LEQ 小于或等于 ||  GTR 大于 || GEQ 大于或等于
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