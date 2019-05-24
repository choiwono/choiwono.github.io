---
layout: post
title: "[EOS #4] EOS Account 특징"
date: 2018-12-01 12:46:32 +0900
description:
categories: Blockchain
tags: [Blockchain, EOS]
---

## EOS Mainnet

- eos 메인넷 상에서 계정정보는 12자리(영문 소문자와 1~5까지의 숫자, 영문으로만 시작 가능)의 문자열이 대표.
- 기타 owner, active key는 해당 계정의 자격을 증명하는 일종의 암호화 키.
- 따라서 EOS 계정에서 토큰의 전송은 EOS 12자리 계정명만 알면 입출금 정보를 확인 가능
- EOS 계정 명칭으로 트랜잭션들을 쉽게 확인할 수 있다.

## EOS Account 구성

- OS 계정은 Owner Key, Active Key 2개의 키(EOS Public key) 로 구성

  > Owner Key(오너키) : 계정의 소유권에 관련된 키로 1개 또는 n 개로 등록 가능.  
  > Active Key(액티비티키): 계정의 활동에 관련된 키로 1개 또는 n개로 등록 가능.

- 일반적인 경우는 Owner Key(1개) = Active Key(1개) = EOS Public Key 와 같게 구성하며 1개 만 등록.

## 권한 (permission)

- 모든 account는 ‘owner’ 와 ‘active (사용자)’에 해당하는 permission을 필요로 한다.

```
owner : 계정의 소유권을 나타내는 권한. 해당 계정의 가장 높은 권한이다.
active : 토큰 전송, 블록 프로듀서에게 투표 등 활동에 대한 권한. owner 다음의 높은 권한이다.
account 의 owner key는 계정의 속성을 변경할때 필요한 key이므로 분실되지 않게 잘 보관해두고, contract를 배포하거나 실행 할대는 active key를 이용하여 수행하면 된다.
```

## EOS Key

- EOS Key 는 EOS Public key 와 EOS Private key 로 한 쌍으로 구성 된다
- 이 EOS Key 생성은 오프라인 상태에서 생성해야 안전하며, 검증된 도구을 (예: scatter 키쌍생성, eoskey.io ...) 이용해야 피싱으로부터 안전.
- EOS Key 는 거의 무한 으로 생성이 가능.
- 입출금과 다양한 EOS 메인넷 상에서의 트랜잭션 생성과 액션등의 실행은 기본적으로 EOS 계정과 연동된 2가지 층위의 OWNER KEY와 ACTIVE KEY 로 인증.
- 이들 키는 최초 계정 생성시 기본적으로는 동일하게 설정

```
예시) EOS 계정 정보 예시

계정명 : aaaaaaaa1234


    OWNER 키 쌍 :

    - Private Key: 5JjfWPH9x3u31u31ZWmz88RXCd62DQYGNxbV3xgX7Xhq5GGiU9u
    - Public Key: EOS8GpfengNRsBC43wfU8nTs8FponJKNUL7pEznDiVckqMse2Eeub


    ACTIVE 키 쌍 :

    - Private Key: 5JjfWPH9x3u31u31ZWmz88RXCd62DQYGNxbV3xgX7Xhq5GGiU9u
    - Public Key: EOS8GpfengNRsBC43wfU8nTs8FponJKNUL7pEznDiVckqMse2Eeub
```

## 3가지 자원

- 일반적으로 eos 메인넷 상 계정 생성을 위해서는 크게 3가지 자원이 필요하다.

  > CPU 자원  
  > Network 자원  
  > RAM 자원

- 이중에서 ram은 구매를 통해 계정에 반영해야 하며, 이때문에 EOS를 보유한 계정이 필요하다.
- EOS 메인넷 상에서 신규 계정을 발행하기 위해서는 eos를 보유한 계정이 이미 존재하고 있어야 하고 신규 계정은 EOS를 보유하고 있는 계정만 새롭게 발행할 수 있다.

## 모계정(mother account)

- EOS 신규계정을 발행할 수 있는 EOS 토큰을 보유하고 있는 계정.

## EOS 일반 계정이름 규칙

- 12 글자
- 숫자 1..5 와 알파벳 abc...xyz 의 소문자만 사용할 수 있습니다.
- 첫글자는 반드시 알파벳

## EOS 특별 계정이름 규칙

- 경매(BidName)을 통해 계정이름을 낙찰(산)받은 경우 특별 계정이름을 만들 수 있다.
- 1 ~ 12 문자로 최대 12 글자를 넘지 않으면 됩니다.
- (예: eos, 1, aa, bithumb ... )
- 숫자 1..5 와 알파벳 abc...xyz 와 "." 포인트 문자를 사용할 수 있습니다. (예: eosio.token , eosio.system ... )

## 왜 EOS coin이 아닌 EOS Token이라 부를까?(참고사항)

- 이더리움과 달리 EOS 네이티브 토큰 역시 다른 EOS 기반의 토큰들처럼 컨트랙트를 통해서 만들어진다
- 그래서 EOS 메인넷이 출시되었지만 "코인"이라는 용어 대신에 "토큰"이라는 용어를 사용
- 보다 정확히는 다른 EOS기반 토큰들과 구분짓기 위해 "EOS 네이티브 토큰"이라고 하는 것이 맞지만, 편의상 EOS 토큰이라고 부른다.

## 주의

- 이더리움 기반에서 0x52Df....B43 형식의 주소는 EOS 체계에서는 더 이상 사용하지 않으며, 또한 EOS5243...xJc 와 같은 형식의 주소도 직접 사용하지 않습니다.
- EOS의 여러분의 지갑주소는(이더리움 체계와 비교해보면) 'EOS계정'으로 해석해야 되며, 메인넷에 EOS Public key가 등록되어 사용가능한 상태이여야 합니다.
- **EOS Key 생성과 EOS 계정생성은 구분되어야 하며, EOS Key 생성이 이더리움 기반 주소생성과 비슷하여 일반적으로 EOS Key 생성이 지갑주소를 갖는 것으로 착각되는 부분.**
- [ link : 6. Wallet & Account 차이 ](https://git.doublechain.co.kr/backend/team/wikis/4.EOSIO/%EC%82%AC.-wallet-&-account-%EC%B0%A8%EC%9D%B4)

## Reference

- http://koreos.io/News/113145
- https://steemit.com/kr/@holcoin/eos-accountname
- https://steemit.com/coinkorea/@donekim/eos-ram
