---
layout: post
title: Nginx란? nginx와 Apache 비교
date: 2019-04-01 12:46:32 +0900
description:
categories: 웹서비스
tags: Nginx
---

## Nginx

- apache의 C10K 문제(한 시스템에 동시접속자 수가 1만명이 넘어갈 때 효율적인 방안)를 해결하기 위해 \*\*event-driven 구조로 만든 웹서버.
- 개발한 응용 프로그램은 OSI 7 Layer 중 application Level에서 동작하며 그 아래 Level에서 Nginx 같은 웹서버가 http 통신을 제공하게 된다.

## Nginx vs Apache

- client가 http 요청을 보낼때 Apache는 MPM(Multi-Process Module)을 사용하여 처리한다.
- MPM에는 두가지의 방식이 존재한다.

```
1. PreFork 방식(다중 프로세스 처리)
2. Worker 방식(말티 프로세스-스레드 방식)
```

#### nginx는 event-driven 방식

- nginx는 event-driven 방식으로 동작한다. 한 개 또는 고정된 프로세스만 생성하고, 그 프로세스 내부에서 **비동기 방식으로 효율적으로 작업들을 처리** 한다.
- 따라서 동시 접속 요청이 많아도 Process 또는 Thread 생성 비용이 존재하지 않는다.

## Reference

- https://medium.com/sjk5766/%EB%84%8C-%EB%AD%90%EB%8B%88-nginx-9a8cae25e964
