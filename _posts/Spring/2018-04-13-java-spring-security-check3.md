---
layout: post
title: "Spring-Security 와 보안"
date: 2018-04-13 12:46:32 +0900
description:
categories: [Spring]
tags: [Java, spring-security]
---

## 보안이란?

![이미지](/post_assets/2018-04-11/authenticationAnd.jpg)

- 위의 두 단어는 스프링 시큐리티 뿐만이 아니라 일반 보안에서도 핵심 축
- **Authentication** : 인증, **Authorization** : 권한부여 (서로 다른 단어!!!!!)

## 인증의 종류

**1. 크리덴셜(Credential:자격) 기반 인증** - 웹에서 사용하는 대부분의인증 방식은 크리덴션 기반의 인증 방식. 즉 권한을 부여받는데 1차례의 인증과정이 필요하며 대개 사용자명과 비밀번호를 입력받아 입력한 비밀번호가 저장된 비밀번호와 일치하는지 확인합니다. 일반적으로 스프링 시큐리티에서는 아이디를 프린시플(principle), 비밀번호를 크리덴셜(credential)이라고 부른다.**(스프링 시큐리티를 이용해 구현해나갈 인증(Authentication))**

**2. 이중 인증(Two-factor authentication)** - 한번에 2가지 방식으로 인증을 받는 것. 예를 들어 금융, 은행 웹어플리케이션을 이용해 온라인 거래를 하실 때에는 로그인과 보안 인증서, 2가지 방법으로 인증을 받는다.

**3. 물리적인 인증** - 웹의 영역을 벗어난 것이지만 가장 효과적인 보안 수단 중에 하나. 지문인식 혹은 키 삽입 방법.

## 권한부여(Authorization)

**1. 부여된 권한(Granted Autority)** - 적절한 절차로 사용자가 인증되었다면 권한을 부여(Granted Authority)해야 한다. 회원가입 등을 통해 반영구적인 권한이 부여됬다면 우리는 이 회원에게 부여된 권한을 어딘가에 저장해야 한다. 만약 해당 사용자가 로그인을 했는데 메인 페이지로 넘어갈 수 없다면 권한부여에 문제가 있다는 것.

**2. 리소스의 권한(Intercept)** - 사용자의 권한만 있다고 보안이 제대로 동작할리는 없다. **보안이란 본래 권한이 없는 자들이 원천적으로 리소스에 접근할 수 없도록 막아내는 것**이기 때문입니다. 그런 의미에서 적절한 권한을 가진자만 해당 자원에 접근할 수 있도록 자원의 외부요청을 원천적으로 가로채는 것**(Intercept)**이 웹보안, 그 중 권한부여(Authorization)의 핵심 원칙이라 할 수 있겠습니다.

- 어떤 방식으로 권한을 부여할지, 해당 리소스에 어떻게 권한수준을 부여하고, 인증받은 정보를 어떻게 지속적으로 유지할 수 있을지에 대한 여러 이슈들을 스프링 시큐리티는 10년가까이 연구하며 발전한 보안 프레임워크이고 보안 개념이 없는 자가 보안이 필요한 쇼핑몰이나 주요 웹사이트를 설계했다고 가정했을 때 발생할 막대한 피해들을 방어할 수 있는 최선의 선택이라고 한다....

## 리소스의 권한(Intercept)

- 보안에서 리소스에 접근권한을 설정하는 것이 바로 Intercept 방식으로 작동하고 있다.
- 아무리 서버 성능이 좋고 직원이 많더라도 가지고 있는 모든 리소스에 일일이 권한을 설정할 수는 없는 노릇이다. 대신에 @MVC에서 보았듯 **DispatcherServlet**처럼 클라이언트의 요청을 가로챌 수만 있다면 간단히 문제를 해결할 수 있을 것이다.

![이미지](/post_assets/2018-04-11/intercept.jpg)

- 스프링 시큐리티는 @MVC의 DispatcherServlet이나 AOP를 이용해 프록시를 생성하지 않고 아주 오래 전부터 사용해온 고유의 DelegatingFilterProxy 클래스를 사용.
- web.xml에 DelegatingFilterProxy를 등록
  {% highlight xml %}
  <filter>
  <filter-name>springSecurityFilterChain</filter-name>
  <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
  </filter>
  <filter-mapping>
  <filter-name>springSecurityFilterChain</filter-name>
  <url-pattern>/-</url-pattern>
  </filter-mapping>
  {% endhighlight %}

- 여기서 주의할 점은 `<filter-name>`의 값이 반드시 springSecurityFilterChain이어야 한다는 점입니다. 왜 꼭 이름을 springSecurityFilterChain으로 지어야 하냐면 DelegatingFilterProxy 클래스는 setTargetBeanName(String)이라는 메서드를 갖고 있는데 이 메서드는 실제 요청을 처리할 필터를 주입받습니다. 만약 이 메서드를 통해 구현할 필터빈을 주입받지 못한다면 DelegatingFilterProxy 클래스는 기본값으로 `<filter-name>`의 값과 동일한 빈이 스프링 컨텍스트에 존재하는지를 검색하게 됩니다.

#### 출처

- [1장 스프링 시큐리티란?](http://egloos.zum.com/springmvc/v/504862)
