wget -O TOTP.py https://raw.githubusercontent.com/qq12352100/note/master/note/script/python/web/TOTP.py
wget -O online_note.py https://raw.githubusercontent.com/qq12352100/note/master/note/script/python/web/online_note.py
sleep 1
systemctl restart pyweb
sleep 2
systemctl status pyweb