echo $1
git status
git pull
if [ $# -gt 0 ]; then
    git add .
    git commit -am $1
    git push origin master
fi
