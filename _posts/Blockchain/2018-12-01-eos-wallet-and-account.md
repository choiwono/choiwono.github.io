---
layout: post
title: "[EOS #7] EOS Wallet & Account 차이"
date: 2018-12-01 12:46:32 +0900
description:
categories: Blockchain
tags: [Blockchain, EOS]
---

## EOS Wallet

- 지갑은 토큰을 저장하지 않는다.
- 지갑은, 암호화된 파일에 개인 키를 저장하고, 트랜잭션에 서명할때 사용된다.

## Wallet / Account 차이

- 둘 다 키 쌍 (암호화를위한 개인 키 및 검증을위한 공개 키)을 기반
- wallet은 블록체인과 관련이 없다.
- wallet은 클라이언트 컴퓨터에 여러 키 쌍을 안전하게 저장한다.
- account는 블록 체인에 있다
- 사람이 읽을 수 있는(readable) 계정이름(name)과 소유자키 및 활성화 키가 있다(owner key and active key).
- 각각의 키는 블록체인에서 고유한 수준의 사용권한(their own levels of permission, on the blockchain을 가진다.
- 두개의 차이는 아래의 그림을 통해 이해할 수 있다.

---

#### [EOSIO_Architecture]

![eos_node](https://user-images.githubusercontent.com/32521173/50259092-fa4b3c80-0445-11e9-9bb1-3ed20b006045.png)

## Desc

#### wallet

- 개인 키 쌍의 저장소
- 블록체인에서 수행된 작업을 서명하는데 필요
- 지갑과 그 내용은 **keosd에서 관리** / **cleos를 사용해 액세스** 가능

#### account

- nodeos는 블록체인 계정 게시 및 계정 관련 작업 관리
- nodeos의 계정 관리 기능은 cleos를 사용하여 액세스 가능
- An account can be thought of as an on-chain identifier that has access permissions associated with it (i.e., a security principal).

## Reference

- [what is the difference between wallets and accounts in eos?](https://eosio.stackexchange.com/questions/1511/what-is-the-difference-between-wallets-and-accounts-in-eos)
