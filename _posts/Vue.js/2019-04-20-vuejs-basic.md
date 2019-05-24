---
layout: post
title: 'vuejs 기본 개념'
date: 2019-04-20 12:46:32 +0900
description:
categories: [Vue.js]
tags: [Vue.js]
---

vuejs를 끄적이면서 간단하게 정리했던 것들 입니다. 전부 vuejs 공식문서에서 발췌함.

## vuejs

- vue 생성자는 미리 정의된 옵션으로 재사용 가능한 컴포넌트 생성자를 생성하도록 확장될 수 있습니다.
- vue앱은 new Vue를 통해 만들어진 root vue instance로 구성되며, 선택적으로 중첩이 가능하고 재사용 가능한 컴포넌트 트리로 구성된다.
- 모든 vue 컴포넌트가 본질적으로 확장된 vue instance.

#### vuejs에서 server side rendering 사용하기

- vuejs에서 csr 대신 ssr을 사용하고 싶을 경우 Nuxt.js 를 사용
- https://medium.com/aha-official/%EC%95%84%ED%95%98-%ED%94%84%EB%A1%A0%ED%8A%B8-%EA%B0%9C%EB%B0%9C%EA%B8%B0-1-spa%EC%99%80-ssr%EC%9D%98-%EC%9E%A5%EB%8B%A8%EC%A0%90-%EA%B7%B8%EB%A6%AC%EA%B3%A0-nuxt-js-cafdc3ac2053

#### 속성과 메소드

- 각 vue 인스턴스는 data 객체에 있는 모든 속성을 프록시 처리 한다.

#### 부모와 자식 컴포넌트 관계

- 구조상 상-하 관계에 있는 데이터 통신
- 부모 -> 자식 : props down
- 자식 -> 부모 : events-up

# props

- 모든 컴포넌트는 각 컴포넌트 자체의 스코프를 갖는다.
  - ex) 하위 컴포넌트가 상위 컴포넌트의 값을 바로 참조할 수 없는 형식.
- 상위에서 하위로 값을 전달하려면 props 속성을 사용한다.

# non paren

- 컴포넌트간의 직접적인 통신을 불가능하도록 되어 있다. --> vue의 기본 구조.
- emit event를 통해서 컴포넌트간 데이터 통신?을 가능하게 해준다.

# parent - child

- 같은 레벨에서 다른 컴포넌트로 데이터를 전달할때 불편하다.

# event bus

- 컴포넌트간의 이벤트를 쉽게 하기 위한 것.

# \*\*\*this(중요함).

- Java : 클래스 인스턴스의 래퍼런스 변수.
- javascript : 현재 실행 문맥.(호출자가 누구인가에 따라)

## vuex

- 상태관리 개념(state management)

## webpack 라이브러리로

- one source(하나처럼 뭉쳐있음) --> webpack build후 --> .js . html이렇게 나눠서 보여줌..

## Reference

- vuejs 공식 문서
