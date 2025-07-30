REM https://www.ip138.com/  # IP查询地址   http://httpbin.org/ip  # 获取真实IP
REM netstat -ano |findstr "12349"    # windos查看端口监听

ssh -NfD 127.0.0.1:12349 root@8.152.208.138 -p 22
REM      lL2#oOk-Ec^%+!mY

REM ssh -i ~/.ssh/id_rsa -NfD 12349 root@8.152.208.138 -p 22   
REM 指定私钥文件，实现免密登录。



if 0==1 (
    echo 这行不会执行
    rem 但要注意括号和特殊字符的处理
    亚马逊云
    ssh -i ~/.ssh/amx24.pem -NfD 12349 ec2-user@13.51.178.24 -p 22
    
)




