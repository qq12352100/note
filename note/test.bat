::不加@时，在运行时，会在窗口显示出这条命令,而加了@, 只会显示出 echo后面你要显示出的东西
@echo off 
@echo --  输出文字       -- 	rem 跟在输出后面会输出
::dir c:\*.* >a.txt 			rem 将c盘下所有文件名输出到a.txt中，如果没有a文件，则在bat同一目录下创建一个
::md D:\test  					rem 在D盘下创建test文件夹
::start http://www.baidu.com  	rem 默认浏览器打开网页
::cd /d %~dp0 					rem 打开当前路径
@echo %AllUsersProfile%			rem	C:\ProgramData
@echo %tmp%						rem	C:\Users\admin\AppData\Local\Temp
@echo %AppData%					rem	C:\Users\admin\AppData\Roaming
@echo %CommonProgramFiles%		rem	C:\Program Files\Common Files
@echo %UserProfile%				rem	C:\Users\admin
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

netsh -c interface ip dump
netsh interface ip show config
netsh interface ip set address "本地连接" static 37.136.6.210 255.255.255.0 37.136.6.254 1  rem 设置网卡IP 掩码 网关
netsh interface set interface "本地连接" disabled rem 禁用网卡
netsh interface set interface "本地连接" enabled  rem 启用网卡
ncpa.cpl
::Usage：第一条命令 | 第二条命令 [| 第三条命令...]将第一条命令的结果作为第二条命令的参数来使用，记得在unix中这种方式很常见。
::Usage：第一条命令 || 第二条命令 [|| 第三条命令...]用这种方法可以同时执行多条命令，当碰到执行正确的命令后将不执行后面的命令，如果没有出现正确的命令则一直执行完所有命令；
::Usage：第一条命令 & 第二条命令 [& 第三条命令...]用这种方法可以同时执行多条命令，而不管命令是否执行成功
::Usage：第一条命令 && 第二条命令 [&& 第三条命令...]用这种方法可以同时执行多条命令，当碰到执行出错的命令后将不执行后面的命令，如果一直没有出错则一直执行完所有命令；
