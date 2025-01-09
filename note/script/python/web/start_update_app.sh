wget -O app.py https://raw.githubusercontent.com/qq12352100/note/master/note/script/python/web/app.py
wget -O TOTP.py https://raw.githubusercontent.com/qq12352100/note/master/note/script/python/web/TOTP.py
wget -O online_note.py https://raw.githubusercontent.com/qq12352100/note/master/note/script/python/web/online_note.py
wget -O stock.py https://raw.githubusercontent.com/qq12352100/note/master/note/script/python/web/stock.py
sleep 1
systemctl restart pyweb
sleep 2
systemctl status pyweb
sleep 1
tail -30f log.file