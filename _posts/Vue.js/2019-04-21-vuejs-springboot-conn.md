---
layout: post
title: '나의 springboot + vuejs 적용기'
date: 2019-04-21 12:46:32 +0900
description:
categories: [Vue.js]
tags: [Vue.js, Spring, SpringBoot]
---

# vuejs와 서버 프레임워크 구축 두가지의 경우

## nodejs + vuejs

- node js api proxytable에 설정
- vuejs에서 해당 api를 불러오고, 실제로 화면은 3000 포트로 접속해서 웹서비스에서 nodejs api에 접근하게 가능.

## springboot + vuejs

- 내가 읽은 문서상으론.. vuejs에서 webpack으로 프론트코드를 빌드해서 index.html에 번들링하고,
- 이 index.html을 springboot에서 읽어서, srpingboot 기본 port인 8080으로 호출시, 해당 뷰를 읽어들인다.
- 그렇다면, 이 view를 spring에서 어떻게 읽지?????

#### springboot에서 vuejs를 렌더링하는 경우 아래의 두가지 경우가 있을 수 있다.

- 컨트롤러가 html파일을 렌더링 하려면 뷰리졸버가 있어야 한다. jsp나 타임리프를 읽게 해주는.

```
1) html파일을 스프링부트가 렌더링 한다.
2) vue를 빌드해서 나온 파일을 (index.html) 스프링부트가 렌더링 하게 한다. (나의 경우)
```

## 나의 실수

#### 1. restcontroller를 controller로 바꿨어야 했음

- @Controller 의 작업은 모델 객체의 Map을 생성하고 뷰를 찾는 것이지만 @RestController는 단순히 객체를 반환하고 객체 데이터는 JSON 또는 XML로 HTTP 응답에 직접 작성됩니다.
- https://javarevisited.blogspot.com/2017/08/difference-between-restcontroller-and-controller-annotations-spring-mvc-rest.html
- @RestController 어노테이션은 @Controller 와 @ResponseBody 어노테이션 의 조합 일 뿐이다.
- 나중에 서비스가 커진다면, view만 처리하는 controller와 API만 처리하는 컨트롤러로 분리한다.

#### 2. springboot의 정적파일 읽는 경로

- 기본적으로 스프링 부트 리소스는 `src / main / resources`에 있어야합니다. 예를 들어 js 및 css 파일은 정적 폴더 `src / main / resources / static / css`에 있어야합니다. 템플릿 폴더에도 동일하게 적용됩니다.
