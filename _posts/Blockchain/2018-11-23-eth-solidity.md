---
layout: post
title: 1.이더리움 solidity 문법 이해/정리
date: 2018-11-23 12:46:32 +0900
description:
categories: Blockchain
tags: [Blockchain, ETH]
---

- 인프런의 이더리움 강의를 수강 후 정리한 내용들 입니다.

# solidity complie version 지정

- 솔리디티 소스 파일의 확장자는 sol 이다.
- 파일 내에서 pragma solidity를 사용해 컴파일러 버전을 지정 가능

```
pragma solidity 0.4.22; //complie version

contract MyContract {
    uint count; // 상태 변수 - 클래스 멤버 변수

    constructor() public { // 생성자
    // ...
    }
            // 함수이름          매개변수    함수타입(public view) 리턴타입(returns(uint))
    function numOfStudents(address _teacher) public view returns(uint) {
        // ...
    }
}
```

# 접근 제어자 존재

| 상태변수 | 내용                                                                                                                                                                                                           |
| :------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| external | - 상태 변수에서 사용 못함. <br/>- 1. 외부 컨트랙트만 호출 가능 <br/> - 2. 상태변수는 external 사용 불가                                                                                                        |
| internal | - 1. 컨트랙 내부 호출 가능.<br/> - 2. 상속받은 컨트랙도 호출 가능. <br/> - 3. 상태변수는 디폴트로 internal 선언                                                                                                |
| public   | - 1. 컨트랙 내부 호출가능, <br/> - 2. 상속받은 컨트랙도 호출 가능. <br/> - 3. 외부 컨트랙도 호출 가능. <br/> - 가시성 명시 (명시하지 않으면 public으로 인식하지만 complie할때 경고나기때문에 public 으로 쓰기) |
| private  | - 컨트랙 내부만 호출가능                                                                                                                                                                                       |

# 함수 타입 제어자

| 함수 타입 제어자 | 설명                                                                                         |
| :--------------- | :------------------------------------------------------------------------------------------- |
| view             | 데이터 read-only                                                                             |
| pure             | 1. 데이터 읽지 않음, <br/> 2. 인자 값만 활용해서 반환 값 정함. <br/> 3. 가스 비용 없음 <br/> |
| constant         | 0.4.17 버전 이전에는 view/pure 대신 쓰임                                                     |
| payable          | 1. 함수가 에더(eth)받을 수 있게 함.<br/> 2. 가스 비용 있음.                                  |

- example code

```
function getNumbOfStudent() public view returns () {
    // 값을 읽기만 하고 수정할 수 없음.
}

// pure를 붙이면,
// 블록체인값을 리턴하는게 아니라 단순한 메서드 기능 ex) 계산
function safeMul(uint a, uint b) pure internal returns (uint) {
    uint c = a * b;
    assert(a == 0 || c / a == b);
    return c;
}

// 지금은 constant 거의 안씀. 원래 --> view 처럼 쓰이는 것.
function getNumberOfStudents() public constant returns (uint) {
    return numOfStudent;
}

// payable
- 돈을 보내고 돈을 받으려면, 함수에 payable이 붙어야 한다.
-
function buy() public payable {

}
```

# 값 타입

## boolean 형

| 리턴값     |  구문  |
| :--------- | :----: |
| ture/false | bool x |

## 정수형

- _사용할 숫자 범위를 미리 파악하고 정수형을 선언 해놓으면 코드 최적화에 도움이 된다._

| 구문 | 설명                                                                        |                 | 추가설명        |
| :--- | :-------------------------------------------------------------------------- | :-------------- | :-------------- |
| int  | int32 x = 1233; <br/>양수 음수 저장 가능.                                   | 8 bit ~ 256 bit | int == int256   |
| uint | 양수만 받을 수 있고 저장할 수 있는 값이 더 크다.<br/> uint256 x = 25675343; | 8 bit ~ 256 bit | uint == uint256 |

## 주소형

| 타입    | 값                                                              | 추가설명                            |
| :------ | :-------------------------------------------------------------- | :---------------------------------- |
| address | 20 byte 이더리움 계정 주소 (40글자) <br/> 이더리움 계정 주소 형 | 두개의 멤버 소유 : balance, tranfer |

- example

```

address x = 0x123;
function send() public {
 if (x.balance < 10) {
   x.transfer(10);
 }
}
```

## 고정된 크기의 byte 배열

| 타입  | 값               | 추가설명                                                                                              |
| :---- | :--------------- | :---------------------------------------------------------------------------------------------------- |
| bytes | 1 byte ~ 32 byte | 아래와 같이 써도 무관.동일<br/> byte == bytes1 <br/> byte에 값을 저장할때는 헥스타입으로 넣어야 한다. |

- example
- `bytes32 x = "hello world";`

## solidity는 string type에 최적화 되어 있지 않다

- solidity는 string type에 최적화 되어 있지 않다.
- **string을 쓸때는 가스비용이 더 요구되기 때문에** 내가 쓴 문자열이 32byte이상일때만 문자열을 쓰고, 32바이트 이내이면 byte를 쓴다.

## 동적인 크기의 byte 배열

| 타입         | 값   | 추가설명  |
| :----------- | :--- | :-------- |
| bytes/string | 무한 | 값 타입 x |

- example
- `bytes[] names`

## enum 타입 (열거형)(4:20)

| 타입 | 값                   | 추가설명                                             |
| :--- | :------------------- | :--------------------------------------------------- |
| enum | 이름 {value, value2} | 값을 정수형으로 리턴 <br/>\* index는 0부터 리턴한다. |

# 참조 타입

## 데이터 위치 (블록에 저장 되는건지 아닌지 지시하는 타입 명령어가 존재한다.)

- **solidity에서는 data의 위치가 중요하다.**
- 상태변수는 디폴트로 storage를 쓰고 있음.
- 매개변수로 넘겨졌을때는 memory를 쓰게 된다. 함수가 종료되면 값들은 휘발하게 된다.
- 배열은 storage로 저장

| 종류    | 설명                                                                                                                |
| :------ | :------------------------------------------------------------------------------------------------------------------ |
| storage | 1. 변수를 블록체인에 영구히 저장 (ex: 하드디스크) <br/> 2. 디폴트로 상태변수는 storage                              |
| memory  | 1. 임시 저장 변수 (ex: RAM)<br/> 2. 디폴트로 매개변수와 리턴값은 memory <br/> 3. 변수값들이 블록체인에 저장 안된다. |

## 배열

| 종류      | 설명                                |
| :-------- | :---------------------------------- |
| 정적 배열 | 사이즈 고정 uint[5] fixedArray;     |
| 동적 배열 | 사이즈 무한대 uint[] daynamicArray; |

```
//초기화
uint256[] memory a = new uint256[](5);
uint256[] memory b = new uint256[](10);

a[0] = 1; // 배열 첫번째 인덱스 숫자 1 입력.
a[1] = 2; // 배열 두번째 인덱스 숫자 2 입력.

uint8[3] memory c = [1, 2, 3] ; //함수안에서 리터럴을 통해 배열 초기화 할때 저장위치는 memory
uint8[]
```

## 구조체

| 타입   | 설명                                                      |
| :----- | :-------------------------------------------------------- |
| struct | 필요한 자료형들을 가지고 새롭게 정의하는 사용자 정의 타입 |

```
struct Student {
    string studentName;
    string gender;
}

Student[] students; // 배열로 재정의
```

## 매핑

| 문법                              | 설명                                                                                                                                                                            |
| :-------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| mapping(\_KeyType => \_ValueType) | - key & Value를 쌍으로 저장하는 자바의 map과 비슷 <br/> - keyType : 동적배열, 열거형, 구조체, 매핑 타입 제외 다른 타입들 다 가능<br/> - ValueType : 매핑 포함 다른 타입 다 가능 |

ex)

- mapping(address => uint256) //key type : 주소 , value : uint256
- mapping(address => uint256) balance; // 어떤 이더리움 주소의 양수값이 블록체인 내에 존재한다.

```
mapping(address => uint256) balance;

function learnMapping() public {
    balance[msg.sender] = 100; // key값을 입력하는 방법. []를 쓴다. msg.sender는 지금 함수를 불러오는 계정 주소를 의미한다.
    balance[msg.sender] += 100; // 주소의 양수값을 늘림 100 + 100 = 200이 됨.

    uint256 currentBalance = balance[msg.sender]; //return value type이 양수니까 uint256으로 선언.
}
```
