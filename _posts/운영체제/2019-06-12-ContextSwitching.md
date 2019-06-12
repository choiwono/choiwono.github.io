---
layout: post
title: ContextSwitching이란?
date: 2019-06-12 14:18:00 +0900
description:
categories: 운영체제
tags: ContextSwitching
---

정확히 알기 위해서 따로 포스팅으로 정리했다.

## Context Switching 이란?

* CPU가 하나의 프로세스가 실행중인 상태에서 현재 프로세스의 상태값을 저장하고, 다른 프로세스의 상태값을 읽어 들이는 행위

## 왜 Context Switching을 사용하는가?

* 만약 어떤 일을 처리할 때, 하나의 일이 모두 끝날때까지 기다리는건 비효율적인 일입니다.
* Context Switching을 사용하지 않으면, 하나의 프로세스가 끝날때까지는 다른 프로세스를 실행할 수 없습니다.
* CPU가 CS을 통해서 여러개의 프로세스를 돌아가면서 실행하기 때문에, 사용자 입장에서는 동시에 처리하는 것과 같은 효과를 누릴 수 있습니다.

## Context Switching의 작동원리

* Context Switching은 다음과 같은 상황에서 일어납니다.
  * 입출력을 요청할 때 ( I/O 요청 )
  * CPU의 사용시간이 만료됐을 경우
  * 부모프로세스를 통해 자식 프로세스를 생성할 때 ( FORK 함수 이용 )
  * 인터럽트가 처리될때까지 기다릴때
* 해당 OS 스케쥴러의 우선 순위 알고리즘을 통해서 순서가 정해진대로 CS가 발생합니다( OS마다 다를수 있다는 얘기 )

* Context Switching은 PCB( Process Control Block )에 저장됩니다
  * 즉 스케쥴러의 우선순위에 따라서 PCB에서 정보를 읽어들여서 실행됩니다.

* PCB의 저장정보
  * 프로세스의 상태 : 생성, 준비, 수행, 대기, 중지
  * 프로그램 카운터 : 프로세스가 다음에 실행할 명령어 주소
  * 레지스터 : 스택, 색인정보 등
  * 해당 프로세스의 id

## Context Switching의 비용

* Context Switching이 자주 일어날수록, CPU 자원 소모는 커질수밖에 없습니다( 오버헤드 발생 ). 
  * Cache 초기화
  * Memory Mapping 초기화
  * Kernel이 항상 실행되어 있어야됨( 메모리가 접근해야함 )

## Process 와 Thread 중 왜 Thread를 더 많이 사용할까?

* CPU 자원소모가 적게 든다 (Context Switching 비용이 Process가 더 많이 듭니다)
  * Thread는 Stack 영역만 초기화를 하면 됩니다. 
  * 그러나 Process 같은 경우 Chache, Memory Mapping까지 모두 초기화를 해주어야합니다.
* 데이터 공유가 어렵다
  * 프로세스의 경우 데이터를 공유하려면 IPC 통신을 사용해야한다
  * 스택같은 경우는 힙 메모리를 서로 공유한다 ( 별다른 통신 필요 X )

