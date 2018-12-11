---
layout: post
title:  "[알고리즘] 퀵정렬, 해쉬함수"
date:   2018-12-12
excerpt: "알고리즘 공부, hello-coding 알고리즘"
algo: true
tag:
- 알고리즘
- hello-coding
- quickSort
- 퀵정렬
- 해쉬함수

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

9) <https://www.youtube.com/watch?v=7BDzle2n47c> 영상참조

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

## 2. 해쉬함수

1) 해쉬함수는 문자열을 받아서 숫자를 반환해주는 함수다.

* 같은 이름에 대해서는 항상 같은 인덱스를 반환한다.
* 해시함수는 다른 문자열에 대해서는 다른 인덱스를 할당합니다.
* 해쉬함수는 O(1)의 실행속도를 가집니다.
* 한마디로 무진장 빠릅니다.

2) 해쉬테이블의 예시

해쉬테이블은 문자열을 입력하면 인덱스 숫자를 반환해줍니다. 인덱스 숫자를 통해서

사용자는 곧바로 값을 받아올수있습니다.

* 가장 쉽게 볼수있는 예시중 하나는 웹사이트를 연결할 때 입니다.
* 컴퓨터는 사이트를 연결할 떄  http://www.sunyvale.co.kr -> 173.255.248.55 이런식으로 문자열을 받아서 숫자로 반환해줍니다.
* 그럼 컴퓨터는 저 ip로 사이트를 연결해줍니다.

3) 충돌

해쉬함수에서 가장 중요한점은 해쉬함수를 잘 만드는겁니다. 즉 해시함수를 잘못만들면

array[10] = 10개의 배열을 선언했습니다. 규칙대로 첫글자를 따라서 인덱스를 할당했습니다. ex) array[0] = apple, array[1] = banna

그렇지만 a로 시작하는 상품이 100개를 등록했다고 하면, 나머지 배열은 텅비어있습니다. 즉 활용을 못하고 첫번째 배열에만 값이 잔뜩들어가 있어서

apple이란 값을 찾을려면 array[0] 배열을 모두 탐색해야 되기때문에 결국 비효율적일수밖에 없게 됩니다.

4) 해결법

* 사용률을 낮게하고, 배열에 값을 고루 분포시키도록 해야합니다.
* 예를들어, array[4] = {4,3,1} -> 배열에 3개의 값만 있으면 사용률은 3/4입니다. 이럴경우 사용률이 0.7이 되면 배열을 리사이징합니다.
* 두배로 키우는것이죠. 4 -> 8로 그렇게 되면 사용률이 3/8이 되기때문에 0.3정도밖에 되지않습니다. 이러면 다시 해시함수의 성능이 좋아집니다.
* 다만 실제로는 많은 사람들이 이미 훌륭한 해쉬함수를 만들어놓았기 때문에 사용만 하면 됩니다. 원리파악만 잘합시다.

[해쉬함수 참고링크] : https://www.youtube.com/watch?v=Vi0hauJemxA 