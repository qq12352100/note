#########################################################################
# sh localhostlog.sh logname "互联网"
#########################################################################
#!/bin/bash
logname=$1
hostname=$2
#互联网
ftpAddr="http://songjian:songjian@119.3.235.61:7005/bkk/log/"
#公安网
#ftpAddr="http://songjian:songjian@10.52.223.3:36998/bkk/log/"

fname="`date +%Y%m%d`.txt"
rm -f $fname*
#wget $ftpAddr$fname
echo "------$1--------`date +%Y_%m_%d---%H_%M_%S`" >>$fname
/sbin/ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr "\n" "#"|sed -e 's/#$/\n/' >> $fname
df -h | awk 'NF>2&&+$5>50{print $0}' >> $fname
fdisk -l | grep "Disk /dev/sd" | awk -F ',' '{printf $1"\n"}' >> $fname
free -g | sed -n '2p' | awk -v a='G' '{print "内存总共：",$2a,"使用：",$3a,"剩余：",$4a ,"缓存: ",$6a }' >> $fname
echo '-----------------------------------------------------------------------------------------------------正在运行的python脚本' >> $fname
ps -ef | grep 'python' | grep 'start$' | awk '{print $8,$9,$10}' >> $fname
echo "--------------------------------------------------------------------------------------------------------------------------" >> $fname
#log
if [ $# -gt 0 ]; then
    for line in $(cat $1)
        echo "--------------------------【${i}】------------------------" >> $fname
        tail -n 10 $i >> $fname
    done
else
    echo "no log" >> $fname
fi
echo '==================================================================================================================================' >> $fname
#curl -F file=@$fname $ftpAddr