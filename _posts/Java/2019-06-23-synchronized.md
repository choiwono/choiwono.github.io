---
layout: post
title: synchronized란?
date: 2019-06-23 16:30:32 +0900
description:
categories: Java
tags: 동기화 멀티스레드
---

## synchronized란?

* 자바에서 말하는 synchronized란 간단히 설명하면 data를 thread-safe하게 만드는 것을 말합니다. 즉 멀티스레드 프로그래밍에서 스레드간 동기화가 되있지 않는 상태에서는 데이터의 안정성이 보장이 되지 않습니다.

* 결국 여러개의 스레드가 하나의 자원에 접근하고자 할때, 현재 데이터를 사용하고 스레드가 있으면 다른 스레드가 접근하지 못하도록 막는 것입니다. 결국 데이터의 안정성을 위해서입니다. 다만 동기화를 잘못 사용하면 프로그램 성능에 큰 저하를 일으킬 수 있고 내부적으로 block, unblock, wait 등등 의 기능을 활용해야 함으로 내부 공수가 들어갑니다.

* 적재적소에 동기화를 사용해야하고, 또한 프로그래밍적으로 많은 고려가 필요합니다.

* 동기화를 적용하는 방법은 메소드, synchronized 블록을 사용하는 경우가 있습니다. 다만 동기화를 적용해도 순서를 보장해주진 않습니다. ex) A가 method에 접근, 그뒤 B가 method에 접근 결과는 B가 먼저 나올수도 있다.
  
```java
// 두 코드는 같은 코드입니다.

public synchronized void method(){
    // 처리 코드
}

public void method(){
    synchronized(obj){
        // 처리
    }
}

```
