---
layout: post
title:  "[자바] json, Exception"
date:   2018-12-18
excerpt: "java, JSON, Exception"
java: true
tag:
- java
- JSON
- Exception

comments: true
---

## 1. JSON

JSON(제이슨, JavaScript Object Notation)은 속성-값 쌍 또는 "키-값 쌍"으로 이루어진 데이터 오브젝트를 전달하기 위해 

인간이 읽을 수 있는 텍스트를 사용하는 개방형 표준 포맷이다. 비동기 브라우저/서버 통신 (AJAX)을 위해, 넓게는 XML(AJAX가 사용)

을 대체하는 주요 데이터 포맷이다. 특히, 인터넷에서 자료를 주고 받을 때 그 자료를 표현하는 방법으로 알려져 있다. 자료의 종류에 

큰 제한은 없으며, 특히 컴퓨터 프로그램의 변수값을 표현하는 데 적합하다.

JSON 자료형 형태 예시

``
{
    "이름": "홍길동",
    "나이": 25,
    "성별": "여",
    "주소": "서울특별시 양천구 목동",
    "특기": ["농구", "도술"],
    "가족관계": {"#": 2, "아버지": "홍판서", "어머니": "춘섬"},
    "회사": "경기 수원시 팔달구 우만동"
 }
 ``

## 2. java Exception 처리

```java

public class CalcExam {
    public static void main(String[] args){
        Calc calc = new Calc();
        try {
            int value = calc.divide(4, 0);

            System.out.println(value);
        }catch(Exception ae){
            System.out.println(ae.toString()); // 어떤 에러가 발생했는지 알수있다.
            System.out.println("0으로 나눴다니!!!!");
        }
    }
}

package my.examples.businesscard;

/**
 * Calc
 *
 * 계산을 위한 클래스
 */
public class Calc {
    /**
     * i를 k로 나눈 결과를 리턴한다.
     * 0 으로 나눌경우 ArithmeticException이 발생합니다.
     * @param i
     * @param k
     * @return
     * @throws ArithmeticException
     */
    public int divide(int i, int k) throws ArithmeticException{ 
        // divide 메소드를 호출 하는곳에 예외처리값을 던져준다.
        int value = 0;
        value = i / k;
        return value;
    }
}

```

API 문서를 읽어야한다. 프로그램이 안전하게 동작을 하려면 Exception 처리를 해야한다.

다만 예외처리는 UI단에서 처리해주는것이 좋다. RuntimeException일 경우 메소드에 적어주지 않아도 된다.

Exception의 종류는 크게 RuntimeException 있느냐 없느냐로 구분한다.

CheckedException = 조상중에 RuntimeException이 없는 경우

CheckedException인 경우에는 반드시 예외처리를 해주지 않으면 컴파일이 되지않는다 ( 최대한 안쓰는편이 좋다 )

되도록이면 RuntimeException을 쓰는게 좋다.

```java

package my.examples.businesscard;

import java.net.URL;

public class UrlExam {
    public static void main(String[] args){ // URL 클래스 = CheckedException!!
        try {
            URL url = new URL("http://www.naver.com");
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}

```

각각 클래스별, 메소드별 예외처리가 발생할 수 있다. 그렇기 때문에 공통 Exception 처리를 할수있다.

## 3. 금주목표

reactJS - 프로그래머스 강좌 완료!!
reactJS - SPA 페이지 만들기 ( 최종목표 )

