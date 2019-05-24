---
layout: post
title:  "error when git master merge/fatal: Could not read from remote repository"
date : 2018-10-30 12:46:32 +0900
categories: etc
tags: git merge
---

## git master merge 안된다.  
```
fatal: 'date-query-update-dev' does not appear to be a git repository
fatal: Could not read from remote repository.
Please make sure you have the correct access rights
```


## 해결방법 
- https://stackoverflow.com/questions/47072807/how-to-merge-branch-to-master

```
1)  
git pull origin master를 사용하여 로컬 마스터를 원격 마스터로 업데이트하십시오.**
2)  
git merge br-1을 사용하여 br-1을 로컬 마스터에 병합하십시오. 이렇게하면 갈등을 해소하고 더 ​​나아 가기 전에 커밋 된 변경 사항을 제공 할 수 있습니다.
3)  
로컬에서 마스터에 br-1의 병합이 커밋되면 git push origin master를 사용하여 로컬 마스터를 원격 마스터에 푸시합니다.
로컬 마스터에도 병합을 시키고, push 시킨다..

```
