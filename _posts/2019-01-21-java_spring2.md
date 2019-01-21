---
layout: post
title:  "[TTL] 2019-01-21 스프링 프레임워크, IOC CONTAINER, BEAN LIFECYCLE"
date:   2019-01-21 
excerpt: "Spring, IOC container, Bean LifeCycle, xml"
java: true
tag:
- spring
- IOC Container
- Bean LifeCycle
- xml
---

## 스프링 프레임워크

### 스프링의 간단한 특징

* 원스탑숍 - 스프링프레임워크 하나로 다 해결 할 수 있음
* 원하는 부분만 가져다가 사용할 수도 있다 - 모듈화가 잘되있다
* 자동으로 트랜잭션 처리를 해준다 ( 자동으로 COMMIT, ROLLBACK을 스프링이 끼워넣는다 )

### 스프링 코어

* 인스턴스들의 생명주기를 관리한다.
* 생성된 인스터들에게 추가적인 기능을 제공한다.
* 스프링이 생성해주는 인스턴스들을 Bean이라고 한다.
* 스프링에게 인스턴스로 만들어야할 객체를 요청하면 스프링이 만들어준다.

### ApplicationContext - 스프링 빈 컨테이너

* 가장 많이 사용되는 어플리케이션 컨텍스트 구현체

* ClassPathXmlApplicationContext : 클래스패스에 위치한 xml 파일에서 컨텐스트 정의 내용을 읽어들인다.
* FileSystemxmlApplicationContext : 파일 경로로 지정된 xml 파일에서 컨텐스트 정의 내용을 읽어들인다.
* XmlWebApplicationContext : 웹 어플리케이션에 포함된 xml 파일에서 컨텐스트 정의 내용을 읽어들인다.



### XML의 특징

* 확장가능한 마크업 언어이다.
* TAG 이름이 칼럼명처럼 정해져 있지 않다( HTML 태그처럼 <H1>,<DIV> 이런식으로 정해진게 아니다 
* 이유는 데이터이기 때문에 )
* 최소 한개의 Element을 가져야한다.
* 내용이 없을 경우에는 종료 tag을 생략 할 수 있다.
* 반드시 문법을 지켜줘야한다.

* ex) 각각의 프로젝트마다 xml 스키마가 따로 있다.
* 각각의 스키마는 name space로 구분한다.

```code

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:c="http://www.springframework.org/schema/c"
  xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="bean1" class="soundsystem.MyBean"/>
    <bean id="bean2" class="soundsystem.MyBean"/>
</beans>

```

* 싱글턴 객체는 필드가없거나, 안전한 필드면 상관이 없다.
* 스프링은 기본적으로 무조건 싱글턴으로 쓴다.

### xml 파일 작성전에 가장 중요한것

1. 코드를 작성하기전에 클래스 다이어그램을 그린다.
2. 어떻게 구현할지, 작동할지, 메모리에 객체가 몇개 올라갈지에 대한 구상을 한다.
3. 어떤 인스턴스가 만들어질지, 각각의 인스턴스가 무엇을 가져야할지에 따른 구상도 끝나야함.
4. 관계와 구현설계가 끝나면, xml에 기재한다.
5. 아키텍쳐가 어느정도 정해져있다 ex) mvc 패턴

### IOC 컨테이너 ( 스프링컨테이너 ) - INVERT OF CONTAINER (제어의 역전)

#### 컨테이너란? 

* 컨테이너는 보통 인스턴스의 생명주기를 관리하며, 생성된 인스턴스들에게 추가적인 기능을 제공한다
* 다시말해서 컨테이너는 내가 작성한 코드의 처리과정을 위임받은 독립적인 존재이다.
* 컨테이너는 적절한 설정만 되어있다면, 프로그래의 소스를 참조하고 알아서 객채의 생성과 소멸을 관리해준다.

#### IOC 컨테이너

* WebApplicationContext -> ApplicationContext -> BeanFactory

#### BEAN의 생명주기

<figure>
    <img src="/assets/img/beanLifeCycle.png" />
</figure>

#### 참고 URL

https://limmmee.tistory.com/13 [심플하게 개발]
http://wiki.gurubee.net/pages/viewpage.action?pageId=26740787
https://js2prince.tistory.com/entry/Spring-IOC-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EB%9E%80