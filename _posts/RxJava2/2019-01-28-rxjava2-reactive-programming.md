---
layout: post
title: "[RxJava2 #1] reactive programming 도입 배경"
date: 2019-01-28 12:46:32 +0900
description: rxjava2를 배우기전 알아야할 reactive programming 도입 배경
categories: RxJava2
tags: [RxJava2, reactive programming]
---

## Reactive Programming 발생 배경

- rxjava2를 보기에 앞서 잠깐 리액티브 프로그래밍의 발생 배경을 살펴보자면....

## 인터넷의 발전, 대용량 네트워크 트래픽 처리의 필요성 증가

- reactive programming의 패더라임과 motivation이 발생될 수 밖에 없었던 두가지 주요 트리거.

{% highlight js %}

- 하드웨어의 진화(Advancements in hardware)
- 인터넷(The internet)
  {% endhighlight %}

![이미지](/post_assets/2019-01-28/total-users-of-the-internet.png)

- 1999년도 까지만 해도 인터넷 사용자는 2억 8천만, 온라인 뱅킹은 초기단계 였다.
- 2005년도 부터 인터넷 사용자는 10억명으로 증가, Facebook 550만명, youtube는 신생아, Netflix는 아직 비디오 스트리밍을 도입하지 않은 시기(2007)
- 2014년도 인터넷 사용자 약 29억명이상 , Facebook 13억 사용자, 트위터 2억 7천만 사용자로 증가..!!!(^\_\_^)
- 이렇기 때문에 software의 scale, expectation같은 이슈사항을 직면할 수밖에 없게 되었다.

## 4 Reactive principles

{% highlight js %}

- Responsive
- Resilient
- Scalable
- Message-driven
  {% endhighlight %}

![이미지](/post_assets/2019-01-28/reactive-pattern.png)

- 현재는 인터넷 보급과 모바일 기기의 보급으로 365일 24시간 무정지의 끊김없는 빠른 서비스를 제공해야 한다.
- 이를 위해 장애(failure)에 빨리 `복구(Resilient)`되고, 서비스의 수평적 `확장(Scalable)`이 용이해야 하며,
  esilient와 scalable 바탕에는 느슨한 결합(loose coupling)의 `메세지 기반(message driven)` 아키텍처가 기반이 되어야 한다.

## message-driven

- 메시지 드리븐 아키텍처는 반응형 응용 프로그램의 기초이다.
- message driven application 프로그램은 이벤트 기반, 액터 기반 또는 둘의 조합일 수 있다.

{% highlight js %}

1. Event-driven concurrency
2. Actor-based concurrency
   {% endhighlight %}

## Event-driven concurrency

- 0개 이상의 관찰자(observers)에 의해 감시되는 이벤트를 기반으로 한다.
- 이는 응답 대기를 차단할 필요가 없기 때문에 명령형 프로그래밍과 다르다(This is different than imperative programming because the caller doesn’t need to block waiting for a response from the invoked routine).
- 이벤트는 특정 주소로 전달되는게 아닌, **watched (or listened)**를 하고 있다.

## Actor-based concurrency

- 메세지 전달 아키텍처의 확장, 메세지는 수신자에게 전달된다.
- 메시지는 스레드 경계를 넘거나 다른 물리적 서버의 다른 액터의 메일 함으로 전달 될 수 있다.

> message와 events의 주요한 차이점은, 이벤트가 발생하는 동안 메세지가 전달된다는 것이다.  
> 0개 이상의 관찰자가 이벤트를 관찰할 수 있는 반면(observed by zero or more (0-N) observers) message의 대상은 명확하다(messages have a clear destination).

## 넷플릭스의 사례

- Netflix의 tv user interface팀, 2012년 북미 인터넷 트패픽의 33%를 차지하는 엄청난 양의 데이터 처리를 해야만 했다.
- 이 엄청난 양의 네트워크 퍼포먼스를 해결하기 위해 rest service를 옵티마이징 하고 아키텍쳐를 재설계 하게 되었다(Netflix 개발자 Jafar Husain).

![이미지](/post_assets/2019-01-28/netflux-example.png)

- 아키텍쳐를 재설계 하면서 고려된 키포인트 중 하나가 reactive programming model 이었다.
- 동시성(concurrency)을 가져가면서 스레드의 안정성과 병렬처리를 개발하는것은 매우 복잡한 일이었고 그래서 이부분을 추상화시킬 프레임워크가 필요했다. 특히 기존 클라이언트 코드를 만지지 않으면서 java API를 통해 비동기 처리가 되도록 만들어야 했다. 그래서 이부분을 비동기 콜백으로 처리하기 위한 함수형 프로그래밍을 사용하는 반응형 모델을 선택하게 되었고 이 부분을 Rx Observable 모델로 가게 된 것.
- 그래서 Jafar Husain은 jvm환경에서 ReactiveExtensions 모델이 동작되는 Java버전을 개발하게 되었으며 이것이 rxJava가 되었다.

## 개발 패러다임의 변화와 맞물린 reactive extensions

- 서비스 레벨에서의 변화와 함께 web 방식의 변화도 같이 일어난다.
- 넷플릭스의 Reactive모델 적용은 단순히 포퍼먼스의 옵티마이징이 아니였다.
- Microservice architecture의 도입으로 비동기 통신에 기반하게 되었으며 이를 아주 쉽게 개발할수 있는 Reactive Programming의 대표적인 ReactiveExtensions가 주목받게 되었다.
- 웹 클라이언트에도 큰 변화가 있었고, 그중에서도 javascript가 역동적으로 변하고 있다.
- 단순 돔을 제어하고 이벤트를 처리하는 언어였지만, 이제는 컴포넌트화 되고 다양한 프레임워크들이 쏟아져 나오고 있다.
- 서버의 변화로 클라이언트도 비동기처리에 대한 필요를 느끼고 reactiveExtensions의 rxJs가 많이 사용되고 있습니다.
- 최근 angular2에서도 react가 채택된것을 확인해본다면 이러한 패러다임의 변화가 아주 빠르게 바뀌고 있음을 알수 있습니다.
- 클라이언트 변화는 모바일에서도 마찬가지 이다.
- SoundColud의 Matthias Käppler는 android에 reactiveExtensions를 적용한 rxAndroid를 내놓았으며 최근 rxSwift까지 나오면서 rxExtensions가 아주 빠르게 적용되고 바뀌어 나가고 있음을 알수있다.

## Reference

1. [RxJava2를 도입하며](https://medium.com/rainist-engineering/migrate-from-rxjava1-to-rxjava2-3aea3ff9051c)
2. [What is Reactive Programming?](https://blog.redelastic.com/what-is-reactive-programming-bc9fa7f4a7fc)
3. [[Reactive] Reactive Programming 배우는 방법](http://mobicon.tistory.com/467)
4. [reactive-history/넷플릭스](https://ahea.wordpress.com/2017/02/03/reactive-history/)
