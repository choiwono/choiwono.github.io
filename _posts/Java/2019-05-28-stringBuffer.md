---
layout: post
title: StringBuffer, StringBuilder의 차이?
date: 2019-05-28 21:23:00 +0900
description:
categories: Java
tags: [StringBuilder],[StringBuffer]
---

## String vs StringBuffer, StringBuilder

* 왜 String을 사용하지 않고 이 두개의 클래스 사용을 지양해야 하는가? String은 내부의 문자열을 수정할 수 없다. 그럼? 문자열을 수정하는게 아니라 새로운 메모리 주소를 참조한다. 

* 결국 기존에 객체가 남게되고, GC가 작동하기 전까지는 힙메모리에 남아있을것이고, <b>프로그램 성능을 느리게 할 것이다</b>.

* String은 문자열 연산이 적고 조회가 적은 멀티쓰레드 환경에서 사용하자.
* String 불변객체이기 때문에 동기화 걱정을 할 필요가 없다.

## String과 어떤 부분이 다를까?

* StringBuffer 또는 StringBuilder는 <b>내부버퍼(임시로 저장할수 있는 메모리)에 문자열을 저장해두고, 그 안에서 추가, 수정, 삭제 작업을 할 수 있도록 설계되어 있다</b>. String 처럼 새로운 객체를 만들지 않고도 문자열을 조작할 수 있다.

## StringBuffer vs StringBuilder

* 이 둘의 차이는? StringBuffer는 멀티쓰레드 환경에서 <b>synchronized가 가능하므로, thread-safe하다</b>. 결론, 간단히 설명하면 멀티쓰레드 -> StringBuffer, 싱글쓰레드 혹은 쓰레드를 신경쓰지 않으면서 문자열 수정이 많을 때는 StringBuilder를 사용한다.

* 보통 알고리즘에서는 둘중에 어느 것을 사용해도 상관없으나, 보통 상용화된 시스템에서 문자열 수정이 많이 필요할 때는 StringBuffer를 많이 사용하도록 하자.