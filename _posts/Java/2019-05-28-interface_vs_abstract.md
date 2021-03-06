---
layout: post
title: Interface, abstract의 차이
date: 2019-05-28 22:00:00 +0900
description:
categories: Java
tags: interface abstract
---

굉장히 헷갈리는 개념이라, 면접 스터디 주제로 정해서 조사해봤다.

추상 클래스와 인터페이스의 차이에 대해서 말해보세요.

### 인터페이스 vs 추상 클래스

#### 추상클래스란?

* 개념 및 특징
  * 추상클래스란 구체적이지 않은 클래스를 말한다. 추상 클래스는 클래스 내 반드시 <b>추상메소드</b>가 하나이상 포함되거나 abstract로 정의된 경우를 말합니다.

  * 추상클래스는 객체를 생성할 수 없다, 하지만 슈퍼클래스로 사용은 할 수 있으며, 추상메소드를 사용하기 위해서는 반드시 해당 메소드를 재정의 해야만 한다.

* 목적
  * 추상 클래스는 추상클래스를 상속받아서 기능을 이용하고, 확장시키는데 있다.

#### 그럼 인터페이스는?

* 개념 및 특징
  * 추상메소드들로만 이루어져 있으며, 메소드가 선언만 되어있다. 인터페이스를 사용하기 위해서는 인터페이스를 구현할 클래스에서 implements라는 키워드를 사용해야 한다.

  * 인터페이스에서는 모든 메소드가 public으로 선언되는데 이유는 어떤 상황에서든 해당 멤버들을 반드시 구현해야 하기 때문이다. 또한 인터페이스는 추상클래스나 일반 클래스와는 다르게 다중상속이 가능하다. ,를 찍어서 여러개의 인터페이스를 상속받아서 구현할 수도 있다.

* 목적
  * 인터페이스는 함수의 구현을 강제화하기 위함이고, 구현을 강제화 함으로써 구현객체의 동일한 동작을 보장할 수 있다

#### 어떤 상황에 어떤걸 쓰는게 적합할까?

* 추상클래스는 같은 종류의 행동들을 구현할 때 많이 쓴다. 상속에 대한 계층구조를 명확히 할 때 효과적이며, 일반변수와 메소드들을 같이 쓸수 있고, 굳이 구현을 할 필요가 없는 메소드는 구현하지 않아도 된다.

* <b>그러나 인터페이스를 더 많이 사용한다.</b> 이유는 무엇일까? 인터페이스는 동시에 개발이 가능하기 때문이다. 인터페이스 내부의 메소드들은 내용을 구현하지 않아도, 결과값을 미리 알 수 있기 때문에 인터페이스의 내용을 누군가 구현하고 있으면 나중에 나올값을 예상하고 다른 작업을 미리 하면 된다. 또한 여러사람이 인터페이스를 구현할 경우, 인터페이스 안의 내용물을 변경할 필요없이 해당 메소드의 구현되는 내용만 변경하면 된다.
