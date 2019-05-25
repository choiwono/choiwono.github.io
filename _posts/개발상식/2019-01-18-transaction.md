---
layout: post
title: 트랜잭션, 레이어드 아키텍처
date: 2019-01-15 11:46:32 +0900
description:
categories: 개발상식
tags: transaction Layered_Architecture
---

## 1. Transaction

* 여러 단계의 처리를 하나의 처리처럼 다루는 기능
* ex> 출금 transaction
* 잔액 조회 > 2. 잔액 인출 > 3. 로그 남기기
* 1-3 과정중 모든 과정은 모두 성공 하거나 혹은 하나도 적용되지 않아야됨
* Commit : Transaction 의 결과를 DB에 반영하는 것
* Roll-back : 결과를 반영하지 않고 원래 상태로 되돌리는 것
* Transaction이 끝나는 시점: Commit이나 Roll-back이 완료되는 시점 또는 커넥션이 정상적으로 종료 되는 시점 

## 2. Layerd Architecture

<figure>
    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Overview_of_a_three-tier_application_vectorVersion.svg/600px-Overview_of_a_three-tier_application_vectorVersion.svg.png" />
</figure>

* 다층 구조(Multi-tier Architecture 또는 n-tier Architecture)는 비즈니스 로직을 완전히 분리하여 데이터베이스 
시스템과 클라이언트의 사이에 배치한 클라이언트 서버 시스템의 일종이다. 예를 들어 사용자와 데이터베이스간의 데이터 
요구 서비스에 미들웨어를 이용하는 것을 들 수 있다. 일반적으로는 3층 구조가 널리 쓰인다. 3층 구조는 3개의 층으로 나뉘어져 있다.

## 프레젠테이션 계층

프레젠테이션 계층은 응용 프로그램의 최상위에 위치하고 있는데 이는 서로 다른 층에 있는 데이터 등과 커뮤니케이션을 한다.

## 애플리케이션 계층

이 계층은 비즈니스 로직 계층 또는 트랜잭션 계층이라고도 하는데, 비즈니스 로직은 워크스테이션으로부터의 클라이언트 
요청에 대해 마치 서버처럼 행동한다. 그것은 차례로 어떤 데이터가 필요한지를 결정하고, 메인프레임 컴퓨터 상에 
위치하고 있을 세 번째 계층의 프로그램에 대해서는 마치 클라이언트처럼 행동한다.

## 데이터 계층

데이터 계층은 데이터베이스와 그것에 액세스해서 읽거나 쓰는 것을 관리하는 프로그램을 포함한다. 애플리케이션의 조직은 이것보다 더욱 복잡해질 수 있지만, 3계층 관점은 대규모 프로그램에서 일부분에 관해 생각하기에 편리한 방법이다.


[출처 https://www.edwith.org/boostcourse-web/lecture/16767/](https://www.edwith.org/boostcourse-web/lecture/16767/)
[출처 https://ko.wikipedia.org/wiki/%EB%8B%A4%EC%B8%B5_%EA%B5%AC%EC%A1%B0](https://ko.wikipedia.org/wiki/%EB%8B%A4%EC%B8%B5_%EA%B5%AC%EC%A1%B0)