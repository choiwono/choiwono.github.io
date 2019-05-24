---
layout: post
title: "[EOS #6] EOS Account 생성법"
date: 2018-12-01 12:46:32 +0900
description:
categories: Blockchain
tags: [Blockchain, EOS]
---

## 1. key를 먼저 생성(public,private)

- `$ cleos create key --to-console` 입력
- 아래처럼 결과값이 나옴

```
Private key: 5JJ6zBcmzhPptXh54Z4uYbfRt2qabudhM4c8MK9Mvg5t.....
Public key: EOS7Vf5LLigBmbsZb1Xq6aUfcEXQEEiqmyt7khbN.....
```

## 2. 생성된 key들을 잘 보관하고, Private key를 default wallet에 import 시킨다.

- `cleos wallet import` 를 입력
- 그 뒤 private key를 넣으라면 문구가 나오고 , 위에서 생성한 private key를 입력하면 된다.  
  private key 생성후 아래의 문구가 나온다.  
  `private key: imported private key for: EOS7.....`

## 3. create account 명령문 입력(issue)

`$ cleos create account doublechain4 wkimdevchain EOS7Vf5LLigBmbsZb1Xq6aUfcEXQEEiqmyt7khbNu5hyq....`

- 그러면 아직 해결하지 못한 아래 에러가 나온다.

```
Error 3080001: Account using more than allotted RAM usage
Error Details:
account wkimdevchain has insufficient ram; needs 2996 bytes has 0 bytes
```

- (\*글 업로드 이후에 해결함, ) ==> [두번째 이슈 해결방법 참고](https://git.doublechain.co.kr/backend/team/wikis/4.EOSIO/%EC%B0%A8.-%EC%A3%BC%EC%9A%94-%EB%B0%9C%EC%83%9D%ED%96%88%EB%8D%98-error-%EB%B0%8F-%ED%95%B4%EA%B2%B0)
- 이상하게도, 정글넷에서 위에서 생성된 public key를 active, owner key로 같게 생성하면 account가 잘 생성된다.
- 정글넷에서 생성하는 경우  
  <img width="704" alt="account_create" src="https://user-images.githubusercontent.com/32521173/50258361-bb67b780-0442-11e9-87fe-bf503856c4d1.png">

- 생성된 계정에 fauset으로 토큰을 보낸뒤 계정을 잘 사용하면 된다.
- 2번 3번의 순서가 바뀌어도 된다.

## 4. default wallet에 import된 private key확인하기

- `$ cleos wallet private_keys`
- 위의 명령어를 입력한뒤 지갑의 password를 입력하면 현재 default wallet에 있는 private key리스트들을 확인해 볼 수 있다.

## 또 다른 예외상황

- 만약 create 할 account의 name 길이가 12자리 미만이면 아래의 에러 메세지를 던진다.
- `$ cleos create account doublechain4 wkimdevchan EOS7Vf5LLigBmbsZb1Xq6aUfcEXQEEiqmyt7khbNu5hyqxG....`
- no active bid for name..아직 정확한 의미 파악은 못하고 지나갔다.

```
Error 3050003: eosio_assert_message assertion failure
Error Details:
assertion failure with message: no active bid for name
pending console output:
```
