---
layout: post
title: Spring Boot 사용이유, Spring 과 차이점
date: 2019-06-16 21:25:00 +0900
description:
categories: Java
tags: java Spring SpringBoot
---

## Spring Boot를 왜 사용할까?

### 개인적으로 사용하면서 크게 느껴진 점 3가지

#### 설정의 표준화, 자동화

* 예를 들면, FreeMaker를 사용해야 한다면 ViewResolver를 Bean으로 등록하고, prefix suffix를 지정해줘야한다. 하지만 boot 같은 경우는 classpath를 기본적으로 읽어들이기 때문에 따로 지정하지 않고, classpath에 폴더를 위치시키면 된다.
* AutoConfigration으로, 많은 설정들을 기본적으로 지원해준다. 많이 쓰이고 범용적인 보일러 플레이트에 가깝다 
* 기존 spring에서 xml 설정이나 java Config로 설정 파일을 읽어들인 것처럼, @SpringBootApplication이 대신 프로젝트 패키지에 있는 애너테이션을 찾아서 명시된 대로 Bean으로 등록해준다. 기존처럼 따로 component Scan을 지정할 필요가없다. 이유는 @SpringBootApplication이 @ComponentScan과 @EnableAutoConfiguration을 포함하고 있기 때문이다
  

#### 배포가 간단하고, Tomcat 내장서버를 포함하고 있다

*  톰켓의 버전이나, 여러 설정 xml 값, maven/gradle 같은 build툴을 다시 설치해야 했지만, Boot의 경우 그럴 필요가없다.
*  내장 톰켓이 버전관리, 배포면에서 용이하다. 또한 성능관련해서도 저어언혀 떨어지지 않는다. 실제 운영서버로도 많이 사용하고 있다.
*  다만 상대적으로, 톰캣 하나에 설정파일이 많거나 컨텍스트를 여러개 생성해야 하는 경우는 외장 톰켓을 고려하는게 나을 수 있다.

#### 의존성 관리가 편리하다

* 의존성에 대한 호환성을 고려하지 않아도 된다. 맨날 maven 사이트 들어가서 몇버전까지 지원되는지 확인하고, 이 라이브러리가 호환이 어떻게 되는지 하루종일 들여다 볼 필요가 없다.
* 예를 들면, SpringBoot의 의존성 시리즈인 starter의 경우엔 사용하고 싶은 의존성이 Freemarker라면 spring-boot-starter-freemarker만 추가하면 그외에 어떤 의존성도 필요없다.


#### 잘못된 오해

* Spring 과 Spring Boot는 전혀 다른 프레임워크가 아니다. 
* Spring Boot는 Spring의 Best Practice를 제공해주고 편의성을 향상시킨것 뿐이지, Spring에서 안되던게 Spring Boot에서는 작동하는 그런 방식이 아니다.


* 참고
  * https://d2.naver.com/helloworld/5626759
  * https://jojoldu.tistory.com/43 