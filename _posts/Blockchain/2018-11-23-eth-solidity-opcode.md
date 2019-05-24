---
layout: post
title: 3. 이더리움 solidity 옵코드(opcodes)란?
date: 2018-11-23 12:46:32 +0900
description:
categories: Blockchain
tags: [Blockchain, ETH]
---

- 인프런의 이더리움 강의를 수강 후 정리한 내용들 입니다.

# 옵코드 (opcodes)

- 연산에 소모되는 비용 == 옵코드 (opcode)

> 1. 산술 연산

2. 로직 연산
3. memory or storage 연산
4. 등등...

- opcode를 stack에 먼저 쌓고..트랜잭션이 발생하면, 필요한 opcode를 먼저 실행한다.

# 컨트랙 최적화 1

- `솔리디티에서는 gas가 중요하다. like 빅오표기법`
- smartcontract을 개발할 때 gas 비용을 고려하여 디자인 해야 한다. 왜냐면 다 비용으로 소모되기 때문에

## 고려사항

1. 컨트랙 배포할 때의 비용
   - 주석, 변수이름, 타입 이름 --> 비용 소모 xx
   - 불필요한 코드 정리
2. 컨트랙 내의 함수를 불러올 때의 비용
   - pure, view --> 비용 xx
   - 비싼 연산을 최대한 줄이기 ex) sstore opcode
   - 반복문 관련된 패턴
   - 고정된 크기 bytes 배열 쓰기 (stirng은 동적. --> 소모 많음)
   - evm은 bytes32 에 최적화 되어 있음. string은 bytes32가 넘을 경우 사용.
   - 상태변수 storage defualt

```
1) 변경전
uint total = 0; //default storage 상태 변수
function expensive () public  {
    for(uint i = 0; i < 10 ; i ++)
        total += 2; // 엄청난 소모
}


```

```
2) 변경후
uint total = 0;
function expensive () public  {
    uint temp = 0 //local 선언
    for(uint i = 0; i < 10 ; i ++)
        temp += 2;
        total += temp; // 곧바로 상태변수를 변화시키는게 아니기 때문에 소모를 줄임.
}

```

## 정리

- 불필요한 코드 정리.
- 비산 연산 최대한 줄이기 (상태변수).
- 반복문 패턴 제대로 쓰기.
- string 대신 bytes32 쓰기.

## 컨트랙 최적화 2

- 배열 사용시 주의점
  - 무제한 크기의 배열 반복 피해야 함.
  - 경우에 맞게 map 또는 for문을 사용해야 함.
  - 솔리디티는 가스 한도가 있는 이슈가 있기 때문에 for를 돌리면 다운 될 수 있음.
  - 길이 50 이하일 경우만 사용(다량의 데이터를 interation할 수 있는 장점) 그게 아니면 맵을 써야 함.
