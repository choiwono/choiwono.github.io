---
layout: post
title:  "MySQL 재설치"
date : 2018-04-06 12:46:32 +0900
categories: Database
tags: MySQL Database
---

# 인강을 따라가던중 MYSQL 커넥션에 에러가 생겼다. 
1. 한글처리 수정중
2. MYSQL 서비스가 시작되지 않는 에러가 발생했다. 
3. mysql이 설치된 data파일을 백업해두지 않고 그냥 삭제해버렸고, mysql --initialize라는 명령어를 수행해도 data 폴터에 mysql 폴더(기존에 있었던)가 생성되지 않고 errr..이상한 파일들만 (?) 설치되었다.
4. 서비스가 계속 실행되지 않고 뭔가 꼬인것 같아 처음부터 재설치를 하려고 한다.

5. 인강은 41.26분까지 들음.

6. 뭐가 꼬였는지는 모르겠는데 mysql 5.7.21버전으로 경로 새로만들고 재설치 후, my.ini은 수정하지 않고
테이블생성할때만 utg8설정을 따로 했을뿐인데 한글입력 후 테이블에 데이터 로우가 잘 추가된다. mysql 서버도 잘 실행된다.
7. 오히려 my.ini에 mysqld하단에 character set을 추가하자
mysql 서비스실행이 에러와 함께 실행안되는 에러가 발생했다.(아까 해결못했던 그에러,,,)

{% highlight js %}
# 추가하려고 했던 코드 
character-set-client-handshake=TRUE
init_connect=SET collation_connection = utf8_general_ci
init_connect=SET NAMES utf8
collation-server = utf8_general_ci
default-collation = utf8_general_ci
default-character-set = utf8
{% endhighlight %}


#### 출처
- [발생에러1](http://blog.naver.com/PostView.nhn?blogId=jaebum85&logNo=110187406423&parentCategoryNo=&categoryNo=19&viewDate=&isShowPopularPosts=true&from=search)
- [JSP와 MySql 연동하기](http://egloos.zum.com/aslike/v/2529414)
- [발생에러2](https://stackoverflow.com/questions/2983248/com-mysql-jdbc-exceptions-jdbc4-communicationsexception-communications-link-fai)
- [url="jdbc:mysql://localhost:3306/kocasdb?autoReconnect=true" 여기가 문제였음.: TOMCAT에 JNDI 설정하기](http://redine07.tistory.com/149)
- [위의에러 해결하고 발생된에러2](http://m.todayhumor.co.kr/view.php?table=programmer&no=17081)
- [엔티티에 대한 참조는;구분자로 끝나야 합니다.](https://foucault.postype.com/post/730160)
- https://okky.kr/article/221844
- http://goldenraccoon.tistory.com/entry/mysql-utf8-%ED%95%9C%EA%B8%80%EA%B9%A8%EC%A7%90-%EC%B2%98%EB%A6%AC-%EB%B0%A9%EB%B2%95
- https://okky.kr/article/349207?note=1115810
- http://klero.tistory.com/entry/%EB%A6%AC%EB%88%85%EC%8A%A4%EC%97%90-MySQL-5710-Enterprise-%EB%B2%84%EC%A0%84-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0
- http://moomini.tistory.com/64
- http://tlstjscjswo.tistory.com/entry/MySQL-%EC%84%A4%EC%B9%98
- http://bombay.tistory.com/3
- http://server-talk.tistory.com/83#recentComments
- http://urajilator.tistory.com/871
