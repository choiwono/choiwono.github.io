---
layout: post
title: javascript 기본 문법 정리
date: 2019-07-31 21:25:00 +0900
description:
categories: Java
tags: javascript
---

### 호이스팅

사전적 의미는 끌어올리다. 자바스크립트 실행시 변수선언과 함수선언을 끌어올립니다.
아래코드는 실행시 오류가 날것 같지만 실제로는 함수 a와 함수선언문이 올라가게 됩니다.
결국 var 변수, 함수 선언식을 사용한 함수의 선언부가 유효범위의 최상단으로 호이스팅이 되는 것입니다.

변수 '할당'이 함수 선언보다 우선 순위이고, 함수 선언이 변수 선언보다 우선 순위입니다.
함수 선언문과 변수 할당문이 존재할 경우, 변수 a 선언 -> 함수 a 선언 -> a에 값할당 순으로 실행됩니다.


```javascript
// 실행전
console.log(a());
console.log(b());
console.log(c());

function a(){
    return 'a';
}

var b = function bb(){
    return 'bb';
}

var c = function(){
    return 'c';
}

// 실행후, 오류가 발생.. 변수만 선언되고 안에 할당된 값이 없기 떄문에 오류가 발생한다.

function a(){
    return 'a';
}

var b;
var c;

console.log(a());
console.log(b());
console.log(c());

b = function bb(){
    return 'bb';
}

c = function(){
    return 'c';
}

// 실행순서 맞춰보기

var myName = "hi";

function myName(){
    console.log("11111등");
}

function yourName(){
    console.log("2222등");
}

var yourName = "no!";

console.log(typeof myName);
console.log(typeof yourName);

// 실행순서는.. 변수선언, 함수, 변수에 값 할당 순으로 이루어집니다
var 변수;
var 변수2;

function 함수1(){
    //실행문
}
변수 = "값할당";

```

### 함수선언문, 함수표현식의 차이

기존에는 익명함수 표현식같은 경우에 property가 할당되지 않아 디버깅을 할 수 없었습니다. 그로 인해 기명함수 표현식을 사용했는데, 현재에는 대부분의 브라우저가 property가 할당되어 익명함수도 디버깅을 할 수 있게 되었습니다.
이제는 함수선언문을 지양하고 기명함수 표현식은 거이 사용하지 않습니다.

```javascript

// 함수 선언문
function a(){
    return 'a';
}
// 기명함수 표현식
var b = function bb(){
    return 'bb';
}
// 익명함수 표현식
var c = function(){
    return 'c';
}

```

아래 코드는 실행결과가 어떻게 될까요? 맨위에 선언된 sum은 무시되고, 3만 두번 출력됩니다. 위에 선언된 sum은 정상적으로 작동하지 않습니다. 이런 문제들로 인해서 함수선언문을 사용해야 합니다. 

```javascript

function sum(a,b){
    return a+''+b;
}
sum(1,2);

function sum(a,b){
    return a+b;
}
sum(1,2);

```

### 스코프, 실행컨텍스트

스코프는 유효범위(변수) 입니다. 스코프는 함수가 정의될때 결정됩니다.
실행컨텍스트는 함수가 실행될 경우의 정보를 담고 있습니다. 실행컨텍스트에는 호이스팅, this 바인등의 정보가 담기게 됩니다. 
