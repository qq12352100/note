echo $1
git status
if [ $# -gt 0 ]; then
    git pull
    git add .
    git commit -am $1
    git pm
fi
