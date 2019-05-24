---
layout: post
title: ""
date: 2018-06-15 12:46:32 +0900
description:
categories: Java
tags: Java spring
---

# lombok

# @JsonInclude(Include.NON_NULL)

- Java의 json library jackson 사용법
- Springframework를 사용할때, @ResponseBodyFH json형식으로 출력 중 값이 null인 항목을 제거하기 위한 방법.
- 우리 소스에선 데이터를 response받을 때 처리할 때 사용

# @JsonInclude(Include.NON_NULL) :

@Setter(AccessLevel.NONE) : AccessLevel.NONE 설정으로 특정 필드 등에 대한 자동 메소드 생성을 막을 수 있다.

- pageurl이나, api등에 처리.
-

@ResponseBody로 json 형식으로 출력 중 값이 null인 항목 제거

- 자바 직렬화, 병렬화, 역직렬화...

- 직렬화라는 개념을 알아야 한다...
- 이걸 왜 이렇게 처리하지?? json처리를 위해 하는것 같은데, 왜 이렇게 하지?? 이렇게 하는거 처음보는것 같은데...
- json은 이기종간의 데이터를 쉽게, 빠르게 처리하게 해준다.
  - 여기서 이기종간이란 말은?
  - 데이터를 빠르고 쉽게 처리해준다는 말은?

#### 출처

- [스프링 시큐리티 로그인](http://syaku.tistory.com/278)
- [스프링 시큐리티란?](http://egloos.zum.com/springmvc/v/504862)
