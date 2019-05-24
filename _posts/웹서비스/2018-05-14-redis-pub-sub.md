---
layout: post
title:  "redis와 pub/sub"
date : 2018-05-14 12:46:32 +0900
description: 
categories: 웹서비스
tags: Redis
---

### redis
- redis는 No SQL의 일종. 
- 대형포털들에서 static page, 또는 검색 결과 등을 **캐쉬** 하는데 많이 사용함.
1. 처리속도가 빠르다.  
- 데이터가 메모리에만 저장되기 때문에 속도가 느린 disk를 거치지 않는다.
2. 데이터가 메모리에만 저장된다.  
- 프로세스가 죽거나 장비가 shutdown되면 데이터가 사라진다.
3. 만료일을 지정하여 만료과 되면 자동으로 데이터가 사라진다.  
- cache
4. 저장소 메모리 재사용  
- 만료가 되지 않았더라도 더이상 데이터를 넣을 메모리가 없으면 LRU(Least recently used)알고리즘에 의해 데이터가 사라진다.



### pub/sub


웹소켓 --> 서버 



#### 출처
- [redis란?](http://genesis8.tistory.com/189)