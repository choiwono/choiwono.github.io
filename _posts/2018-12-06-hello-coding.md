---
layout: post
title:  "[알고리즘] 선택정렬"
date:   2018-12-04
excerpt: "선택정렬, hello-coding 알고리즘"
algo: true
tag:
- 알고리즘
- hello-coding
- 선택정렬
- 재귀

comments: true
---

## 1. 선택정렬

선택정렬에 대하여, 컴퓨터에 음악이 많이 저장되어 있다고 가정할 경우 가수별로 몇곡이

들었는지 다음과 같이 기록하였습니다.

| 음악제목 | 연주횟수 |
|----------|----------|
| 음악1    | 156      |
| 음악2    | 146      |
| 음악3    | 140      |

이 목록을 가장 많이 들은것부터 가장 적게 들은 것 순서로 정렬하여 가장 좋아하는 가수를

알고 싶은 경우, 연주횟수가 가장 많은 음악을 음악1 ~ 음악3까지 찾는다.

| 음악제목 | 연주횟수 |
|----------|----------|
| 음악1    | 156      | 

-----------------------

| 음악제목 | 연주횟수 |
|----------|----------|
| 음악1    | 156      |
| 음악2    | 146      | 


이런식으로 반복하면 음악순위를 구할수 있다. 연주횟수가 가장 많은 사람을 찾기 위해서는

목록의 모든 항목을 점검해야 하기 때문에 O(n x n) 즉 O(n^2) 시간이 걸립니다.

```java

package my.examples.javaexam;

public class SortExam {
    public static void main(String[] args) { 
        int[] a = {254,3,213,64,75,56,4,324,65,78,9,5,76,3410,8,342,76}; // 배열
        int b; 
        for(int i = 0 ; i <  a.length -1 ; i ++) { // a배열 길이만큼 반복문
            for(int j = i+1 ; j < a.length ; j ++) { // int j는 i보다 1이크다
                if(a[i] > a[j]) { // a의 0번째 배열과, a의 1번째 배열값을 비교하여 값을 바꾼다.
                    b = a[j]; // a[j] 배열값을 보관 
                    a[j] = a[i]; // a[j] 1번째 배열에 a[i]값을 기록
                    a[i] = b; // a[i] 에는 아까 임시보관값 b를 삽입
                }
            }
        }
        // --> ex) 0번째 배열과 1번째 배열값을 계속 바꾼다.
        for(int i = 0 ; i <  a.length ; i ++) {
            System.out.println(a[i]);
        }
    }
}

```

## 2. 재귀정렬

1) 재귀에 대하여

재귀는 간단하게 설명하면 자기자신을 호출하는것입니다.