---
layout: post
title:  "[알고리즘] 퀵정렬"
date:   2018-12-12
excerpt: "알고리즘 공부, hello-coding 알고리즘"
algo: true
tag:
- 알고리즘
- hello-coding
- quickSort
- 퀵정렬

comments: true
---

## 1. 퀵정렬

1) 배열의 기준값을 찾는다. 가장 좋은건 중간값.

* 하지만 배열 가운데에 어떤값이 있을지 모르므로 실행속도가 빨라질수도 느려질수도 있다
* O(n log n) 평균 실행시간 / O(log n^) 최악의 실행시간

2) 배열의 가운데를 기준으로 왼쪽은 작다, 오른쪽은 크다로 나눈다.

3) 왼쪽 배열 첫번째부터 기준값으로 크다 작다를 찾는다. 오른쪽도 그대로 기준값보다 작은지 큰지 찾는다.

4) 왼쪽 배열에서 기준값보다 큰값을 발견하면 멈춘다. 오른쪽도 기준값보다 작은값을 발견하면 멈춘다.

* arr[start] < arr[mid] start++; 0번째 배열부터 값을 탐색해서 비교, 반대로 9번쨰 배열부터 ~ 중간까지 탐색.

5) swap을 통해서 배열값을 바꿔준다.

6) 다시 배열을 전진시켜서 기준값과 비교한다.

7) 반복하다보면 왼쪽 오른쪽 탐색중에 기준값에서 서로 만난다. 중지.

8) 정렬이 끝날때까지 반복!

```java

public class QuickSort {
    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length -1);
    }
    private static void quickSort(int[] arr, int start, int end) {
        int part2 = partition(arr, start, end);
        if (start < part2 -1) {
            quickSort(arr, start, part2 -1);
        }
        if (part2 < end) {
            quickSort(arr, part2, end);
        }
    }
    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[(start + end) / 2];
        while (start <= end) {
            while (arr[start] < pivot) start++;
            while (arr[end] > pivot) end--;
            if (start <= end) {
                swap(arr,start,end);
                start++;
                end--;
            }
        }
        return start;
    }
    private static void swap(int[] arr, int start, int end) {
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }

    private static void printArray(int[] arr) {
        for (int data : arr) {
            System.out.print(data + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3,9,4,7,5,0,1,6,8,2};
        printArray(arr);
        quickSort(arr);
        printArray(arr);
    }
}

// 결과값

3, 9, 4, 7, 5, 0, 1, 6, 8, 2, 
0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 

```
