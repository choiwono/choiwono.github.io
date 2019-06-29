---
layout: post
title: Spring Boot 2.0 버전의 특징
date: 2019-06-29 23:30:32 +0900
description:
categories: Spring
tags: SpringBoot Spring
---

## 스프링부트 2.0의 특징

#### 자바 8이상 버전부터 가능

* 최소 자바 8 버전이상부터 지원, 그전 버전은 jdk를 업그레이드 하지 않는 이상 사용이 불가능하다

#### Spring WebFlux 도입

* Spring WebFlux의 도입. Spring MVC와 같이 사용 가능
  * 복수개의 서비스로 이루어진 분산 시스템을 제공, MSA에 적합하다
  * 비동기, 논블록킹 방식의 리엑티브 개발가능
  * 효율적으로 동작하는 고성능 웹 어플리케이션 개발 가능
  * 서비스간 호출이 많은 MSA에 적합하다. 
* AutoConfigration을 제공, 기존에는 내장 톰캣만 지원 -> Netty 등 내장 서버 구성 지원을 해준다

#### HTTP/2 지원

* 공식적으로 HTTP/2를 지원한다. 기존의 HTTP1.1과 호환성을 유지한다.
* 특징
  * HTTP 헤더 데이터 압축
  * 서버 푸시 기술
  * 요청을 HTTP 파이프라인으로 처리
  * TCP 연결 하나로 여러 요청을 다중화 처리
  * 이런 특징으로 인해서 네트워크 지연시간을 줄이고, 웹 브라우저 렌더링 속도를 향상시킵니다.


#### Configuration Properties

* 언더스코어, 카멜 표기법등을 지원했지만 현재는 소문자와 kebab-case만 지원합니다. 하이픈으로 연결하는 방식만 지원
* ex) spring.jpa.databaseplatform=mysql

#### Data Support

* 커넥션풀 변경
  * 커넥션풀이 톰캣에서 HikariCP로 변경됨.

* 몇몇 reactive Data 자동설정을 지원해줌
  * 몽고 db, Redis 등등

#### Spring Framework 5.0

* 스프링 프레임워크 5.0기반으로 작동한다. 스프링 프레임워크 리엑티브로 인해 많은 변경점이 생겼다.

#### Json starter
* 새로운 spring-boot-starter-json가 추가 되었다. jackson-databind 뿐만아니라 java8에서 유용하게 사용할 수 있는 jackson-datatype-jdk8와 jackson-datatype-jsr310, jackson-module-parameter-names도 지원한다.


* 출처
  * https://brunch.co.kr/@springboot/55
  * http://wonwoo.ml/index.php/post/1769