#!/usr/bin/env bash
#提交源代码

git add .
git commit -m "$(/bin/date "+%F %T")"
git push
exit 0
