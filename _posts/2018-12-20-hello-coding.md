---
layout: post
title:  "[알고리즘] 피보나치 수열, 최빈수 구하기, 10진수를 2진수로 변환"
date:   2018-12-04
excerpt: "알고리즘 공부, 최빈수 구하기, 10진수 2진수로 변환"
algo: true
tag:
- 알고리즘
- hello-coding
- 피보나치 수열
- 최빈수
- 10진수 2진수로 변환
---

## 1. 피보나치 수열

```java

package com.dot.noback;

public class algoExam {
    public static void main(String[] args) {

        int[] arr = new int[100];

        arr[1] = 1; // 1,2번째의 초기값은 1로 고정
        arr[2] = 1;

        for(int i=3; i<100; i++) { // i는 무조건 3부터 시작한다. 그전값은 -을 할수가없다.
            arr[i] = arr[i-1] + arr[i-2];
        }

        for(int i=1; i<100; i++) {
            System.out.println(arr[i]+" ");
        }
    }
}

```
* i는 무조건 3부터 시작
* arr[i] = arr[i-1] + arr[i-2] 
* arr[1], arr[2] 는 초기값 1로 고정!! 

## 2. 최다빈수 구하기

```java

package com.dot.noback;

import java.util.Scanner;

public class algo2Exam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] inputNum = new int[10]; // 10칸 짜리 배열생성
        for(int i=0; i<10; i++) {
            inputNum[i] = scanner.nextInt(); // scanner를 통해서 숫자값을 입력밥는다.
            System.out.println(inputNum[i]);
        }

        int[] max = new int[10]; // 최빈수를 구하기 위해 처음에 만든 배열과 같은 크기의 배열을 생성

        for(int i=0; i<10; i++) {
            max[inputNum[i]]++; 
            // max[inputNum[i]] ++ inputNum의 값이 112233335를 입력받았을경우
            // max[2] : 2, max[2] : 2, max[3] : : 4, max[5] : 1이다. 
        }

        int modeNum = 0;
        int modeCnt = 0;

        for(int i=0; i<10; i++) {
            if(modeCnt < max[i]) { // 0보다 max[i] 번째가 컸을때
                modeCnt = max[i]; // modeCnt값은 max[i] 값이다.
                modeNum = i;
            } 
        }
    }
}


```

* 최빈수를 구하기 위해서는 배열이 두개가 필요하다.
* 최빈수를 탐색하기 위한 배열 ex) [1,1,2,3,4,5,6,7,8];
* 최빈수를 찾아서 담기 위한 배열, 두가지만 기억하면 된다.

## 3. 10진수를 2진수로 변환

```java

package com.dot.noback;

public class algo3Exam {
    public static void main(String[] args) {

        int inputNum = 19;
        int bin[] = new int[100];

        int i = 0;
        int mok = inputNum;

        while(mok > 0) { // mok이 0일때 끝난다.
            bin[i] = mok % 2; // bin[0] = 1 (19 % 2), bin[1] = 1 (9%2)
            mok /= 2; // 19/2 = 9, 9/2 = 4
            i++; // 배열이 0일때, 1일때 계산
        }
        i--; // i++ 후위 연산자기 때문에 값이 1이 더 증가됐기 때문에 빼준다.

        for(; i>=0; i--) {
            System.out.println(bin[i]); // 배열 뒷자리부터 출력해야한다.
        }
    }
}

```

* 19 / 2 ---- 9 ... 1
  
  9  / 2 ---- 4 ... 1
  
  4  / 2 ---- 2 ... 0
  
  2  / 2 ---- 1 ... 0
  
  1  / 2 ---- 0 ... 1
  
  10011 => 19
        
* 가장 기본적인 10진수 -> 2 진수 변환법 기억
* mok % 2 값을 배열에 담는다, mok은 계속 2로 나눈다. ex) 19/2 = 9, 9/2 = 4
* 0이 될때까지 배열을 돌린다. 후위 연산자를 사용했을 경우 한번더 
* 배열이 돌게 되서 1이 더 증가되므로 1을 빼준다.
