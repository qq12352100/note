#########################################################################
# 先删除本地20210325.txt，然后去FTP取20210325.txt，然后打印基本信息和logname的日志后10行到20210325.txt，然后上传到FTP
# 注意：要修改变量logname，ftpAddr
#########################################################################
#!/bin/bash
localName="政务网"
logname=('/var/log/mysql_to_ftp.log' '/var/log/mysql_to_ftp_car.log')
#互联网
ftpAddr="http://songjian:songjian@119.3.235.61:7005/bkk/log/"
#公安网
#ftpAddr="http://songjian:songjian@10.52.223.3:36998/bkk/log/"

fname="`date +%Y%m%d`.txt"
rm -f $fname*
wget $ftpAddr$fname
echo "------${localName}--------`date +%Y_%m_%d---%H_%M_%S`" >>$fname
#IP
/sbin/ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:" >> $fname
#fdisk
df -h | awk 'NF>2&&+$5>50{print $0}' >> $fname
fdisk -l | grep "Disk /dev/sd" | awk -F '[ :,]+' '{printf "%.0f\n",$5/1072741824}' | awk -v total=0 '{total+=$1}END{printf "磁盘总共：%.0fG\t",total}' >> $fname
df -k | grep -v "tmpfs" | egrep -A 1 "mapper|sd" | awk 'NF>1{print $(NF-3)}' | awk -v used=0 '{used+=$1}END{printf "使用：%.2f%\n",used/1048576}' >> $fname
#free
free -g | sed -n '2p' | awk -v a='G' '{print "内存总共：",$2a,"使用：",$3a,"剩余：",$4a}' >> $fname
echo '-----------------------------------------------------------------------------------------------------正在运行的python脚本' >> $fname
ps -ef | grep 'python' | grep 'start$' | awk '{print $8,$9,$10}' >> $fname
echo "--------------------------------------------------------------------------------------------------------------------------" >> $fname
#log
for i in ${logname[@]}
do
echo "--------------------------【${i}】------------------------" >> $fname
tail -n 10 $i >> $fname
done
echo '==================================================================================================================================' >> $fname
curl -F file=@$fname $ftpAddr