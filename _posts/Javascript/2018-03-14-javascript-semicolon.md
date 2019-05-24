---
layout: post
title:  Javascript 세미콜론(;) 표기법
date : 2018-03-15 12:46:32 +0900
description: 
categories: Javascript
tags: Javascript
---

# Javascript 세미콜론(;) 표기법
## 함수 선언문과 함수 표현식에서의 세미콜론(;)
- 일반적으로 코드를 작성할 때 **함수 표현식에서는 ;(세미콜론)을 붙히고 함수 선언문으로 작성할 시에는 ;(세미콜론)을 붙이지 않는다.** 
- 이것은 하나의 관습이고 코드 작성시 권장되는 방식
- 자바스크립트에서는 세미콜론 사용을 강제하지는 않는다. 그 이유는 자바스크립트 인터프리터가 자동으로 세미콜론을 삽입시켜 주기 때문.
- 하지만 세미콜론에 신경을 쓰지 않는다면 소스 압축 배포를 하거나 디버깅을 할 시에 심각한 상황에 직면할 수 도 있다.

{% highlight js %}
//함수표현식
var apple = function(){
 return 300;
};

//함수 선언문
function apple(){
 return 300;
}
{% endhighlight %}


## 필수사항: 두개 명령문이 같은 줄에 있을때
- 세미콜론은 같은 줄에 둘 이상의 명령문(statements)이있을 때만 필수입니다.
{% highlight js %}
var i = 0; i++        // <-- semicolon obligatory
                      //     (but optional before newline)
var i = 0             // <-- semicolon optional
    i++               // <-- semicolon optional
{% endhighlight %}

## 옵션사항: 명령문 이후에
- 자바스크립트의 세미콜론은 명령문을 분리하는데 사용되지만, 명령문 다음에 줄바꿈이 있으면(또는 {}에 단하나의 명령문만 있는경우) 생략할 수 있습니다.
명령문은 어떤 일을 지시하기 위해 컴퓨터에게 알려주는 코드 조각입니다. 다음은 가장 일반적인 명령문 유형들이 있습니다.
{% highlight js %}
var i;                        // variable declaration
i = 5;                        // value assignment
i = i + 1;                    // value assignment
i++;                          // same as above
var x = 9;                    // declaration & assignment
var fun = function() {...};   // var decl., assignmt, and func. defin.
alert("hi");                  // function call
{% endhighlight %}

## Avoid!
**(1)** {}가 닫힌 후에  
- `}` 이 닫힌 이후에 세미콜론을 사용하면 안됩니다. `var obj={};`와 같은 할당문은 예외입니다.
{% highlight js %}
// NO semicolons after }:
if  (...) {...} else {...}
for (...) {...}
while (...) {...}

// BUT:
do {...} while (...);

// function statement: 
function (arg) { /*do this*/ } // NO semicolon after }
{% endhighlight %}

  

**(2)** if, for, while, switch의 괄호()) 후에 명령문
- if 문의 {} 후에 세미콜론을 사용해도 상관없습니다. (무시되어지고, 불필요하다고 warning을 보게됩니다.) 그러나 세미콜론이 속하지 않는 (if, for, while, switch 문의 ()의 후와 같은)경우에는 좋은 생각이 아닙니다.
{% highlight js %}
if (0 === 1); { alert("hi") }

// equivalent to:

if (0 === 1) /*do nothing*/ ;
alert ("hi");
{% endhighlight %}

  
## 예외
- `for` 루프의 `()` 안에 세미콜론은 오로지 첫번째 이후와 두번째 명령문에만 사용하며 세번째는 사용하지 않습니다.  
` javascript for (var i=0; i < 10; i++) {/*actions*/} // correct for (var i=0; i < 10; i++;) {/*actions*/} // SyntaxError`



#### 출처
- [Javascript 세미콜론(;) 가이드](http://webframeworks.kr/tutorials/translate/javascript-semicolon/)
- [함수 선언문과 함수 표현식에서의 세미콜론(;)](http://webclub.tistory.com/16)