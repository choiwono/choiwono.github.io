---
layout: post
title:  "[알고리즘] 큐"
date:   2018-12-12
excerpt: "큐,알고리즘"
algo: true
tag:
- 큐
- 알고리즘

comments: true
---

## 1. 큐

1) 선입선출의 자료구조. FIFO( FIRST IN, FIRST OUT)

   데이터가 들어온 순서대로 나가는 구조입니다. 보통 배열 형태로 이루어져있습니다.

   EX) ㅁㅁㅁㅁㅁㅁㅁ -> ㅁ(IN) ㅁㅁㅁㅁㅁ ㅁ(out)

2) 특수한 형태의 큐

우선순위 큐, 데크, 원형큐 등이 있습니다.

```java

package my.examples.javaexam;

import java.util.NoSuchElementException;

public class Queue<T> {
    public class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> first;
    private Node<T> last;

    public void add(T item) { // add 할시 null이 아닌경우 마지막은 가장 마지막에 입력한값이다.
        Node<T> t = new Node<T>(item);

        if (last != null) {
            last.next = t;
        }
        last = t;

        if (first == null) {
            first = last;
        }
    }

    public T remove() { // 가장 처음값을 출력한다.
        if (first == null) {
            throw new NoSuchElementException();
        }

        T data = first.data;
        first = first.next;

        if(first == null) {
            last = null;
        }
        return data;
    }

    public T peek() { // first값을 바로 출력해준다.
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.data;
    }

    public boolean isEmpty() { // first값이 null인지 아닌지 체크한다.
        return first == null;
    }
}

package my.examples.javaexam;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<Integer>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        System.out.println(q.remove()); // 가장 처음입력한 값 출력 : 1
        System.out.println(q.remove()); // 두번째 입력값 출력 : 2
        System.out.println(q.peek()); // 현재 값 출력 3
        System.out.println(q.remove()); // 세번째 값 출력 3
        System.out.println(q.isEmpty()); // 아직 4가 남아있기 때문에 false
        System.out.println(q.remove()); // 4번째 값인 4까지 나왔기 때문에 완료 
        System.out.println(q.isEmpty()); // 모든 큐가 비었기 때문에 true입니다.
    }
}

출력값 
1
2
3
3
false
4
true
```
