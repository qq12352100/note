logname=('/var/log/hik_sdk_to_ftp4.log' \
'/var/log/yisa_put_file_to_ftp.log' \
)
echo "------视频网--------`date +%Y_%m_%d---%H_%M_%S`" >> /ftpdata/yisa_car/log.txt
/sbin/ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:" >> /ftpdata/yisa_car/log.txt
fdisk -l | grep "Disk /dev/sd" | awk -F '[ :,]+' '{printf "%.0f\n",$5/1072741824}' | awk -v total=0 '{total+=$1}END{printf "磁盘总共：%.0fG\n",total}' >>  /ftpdata/yisa_car/log.txt
df -h | grep '^/dev/' >>  /ftpdata/yisa_car/log.txt
free -g | sed -n '2p' | awk -v a='G' '{print "内存总共：",$2a,"使用：",$3a,"剩余：",$4a}' >>  /ftpdata/yisa_car/log.txt
echo '-----------------------------------------------------------------------------------------------------' >> /ftpdata/yisa_car/log.txt
ps -ef | grep 'python' | grep 'start$' | awk '{print $8,$9,$10}'| sort -k 2 | uniq | wc -l |awk '{print "正在运行的python脚本:",$1-1}' >> /ftpdata/yisa_car/log.txt
ps -ef | grep 'python' | grep 'start$' | awk '{print $8,$9,$10}'| sort -k 2 | uniq >> /ftpdata/yisa_car/log.txt
ps -ef | grep 'python' | grep ':app$' | awk '{print $13,$NF}' | uniq >> /ftpdata/yisa_car/log.txt
ps -ef | grep 'redis' | grep -v 'color' | wc -l |awk '{print "正在运行的redis程序:",$1}'  >> /ftpdata/yisa_car/log.txt
java -jar r.jar >>  /ftpdata/yisa_car/log.txt
for i in ${logname[@]}
do
echo "--------------------------【${i}】------------------------" >> /ftpdata/yisa_car/log.txt
tail -n 10 $i >> /ftpdata/yisa_car/log.txt
done
echo "===============================================================================================================================视频网===`date +%Y_%m_%d---%H_%M_%S`" >>  /ftpdata/yisa_car/log.txt