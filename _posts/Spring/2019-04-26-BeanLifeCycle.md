---
layout: post
title: Bean Life Cycle이란?
date: 2019-04-26 17:46:32 +0900
description:
categories: Spring
tags: BeanLifeCycle
---

## Spring Bean Factory LifeCycle

* 생성,초기화,사용,소멸의 단계로 진행된다.

### 빈 인스턴스 화 및 DI

1. XML 파일, Java Config , 어노테이션에서 빈 정의를 스캔
2. 빈 인스턴스 생성
3. 스프링이 값과 빈의 참조값을 빈프로퍼티에 의존성을 주입한다.


### 스프링인지 여부 검사

1. 빈이 BeanNameAware를 구현하고 있을 경우 setBeanName()을 호출
2. 빈이 BeanFactoryAware를 구현하면 setBeanFactory() 메소드를 호출한다.
3. 빈이 ApplicationContextAware를 구현하면 스프링이 setApplicationContext() 메소드를 호출하고, 둘러싼 어플리케이션 컨텍스 에 대한 참조를 넘긴다.


### 빈 생성주기 콜백

1. 빈이 PostProcessor를 구현하고 있을시, 스프링은 postProcessBeforeInitialization 메소드를 호출한다.
2. 빈이 InitializingBean 인터페이스를 구현하면 스프링은 afterPropertiesSet() 메소드를 호출한다.
3. 빈이 init-method가 선언 되어있을 경우 지정한 초기화 메소드가 호출된다.

### 빈 소멸주기 콜백

1. 빈이 DisposableBean 인터페이스를 구현하면 스프링은 destory() 메소드를 호출한다.
2. 빈이 destory-method를 정의하면 지정한 메소드를 호출한다.

* 보통 많이 사용되는 경우는 초기화와 소멸 이벤트가 주로 사용된다.
* 이 초기화 혹은 소멸 시점에서 이벤트를 캐치해서 사용자가 애플리케이션에 맞게 구현할 수 있다.
* 라이프 사이클을 알아야 하는 이유는, 스프링을 좀더 깊게 파고들어야 할 경우에 사용한다.
