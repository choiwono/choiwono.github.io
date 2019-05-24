---
layout: post
title: "Node.js, Express 개념, 장점"
date: 2018-02-01 12:46:32 +0900
categories: Nodejs
tags: Nodejs Express
---

# Node.js 란, node js 를 사용함으로서의 장점.

Node.js는 자바스크립트(javascript)로 서버 프로그래밍을 할 수 있도록 해주는 플랫폼.

## [장점]

1. **V8 Engine 위에 작동하는 이벤트 처리 I/O 프레임워크이다.**  
   구글이 javascript 코드를 동적으로 컴파일하여 기계어로 바꾸는 V8 엔진을 개발하면서 javascript 실행 성능이 크게 좋아졌다.

2. **Event-driven 방식 Node.js는 비동기 프로그래밍이다.**  
   요청이 들어오면 결과를 즉시 받아들이는 동기프로그래밍과 달리 비동기는 이벤트 요청시 바로 결과 값을 받지 않아도 된다. 때문에 다양한 요청처리가 가능하다.

3. **프론트엔드와 백엔드를 javascript언어로 관리가 가능하다.**  
   새로운언어를 습득하지 않고 기존 언어를 활용해 서버기술을 빨리 응용할 수 있게 되었다.

# Express?

Express는 노드를 만든 패키지의 일종. 웹 서버를 만들기 위한 것.  
프레임웍의 개념이다(노드를 이용해 만들어진 프레임웍). 프레임웍을 사용하게 됨으로서 노드로만 코드를 작성하는 것보다
훨씬 빠른시간에 효율적으로 서버를 개발할 수 있게 해준다는 이점이 있다.

- express자체는 템플릿 엔진 기능을 제공하지 않는다,
- 템플릿 엔진을 사용하기 위해선 따로 설치해서 express와 연결하여 사용해야 한다.[node js 에서 템플릿 엔진 사용](http://wikim.tistory.com/191?category=715189)

#### 출처

- [http://webframeworks.kr/tutorials/nodejs/api-server-by-nodejs-02/](https://cheese10yun.github.io/Node-AWS-S3-Upload/)
- [https://vinebrancho.wordpress.com/2014/03/24/node-js](https://vinebrancho.wordpress.com/2014/03/24/node-js)
