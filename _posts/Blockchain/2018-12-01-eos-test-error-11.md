---
layout: post
title: "[EOS #11] EOS 주요 발생했던 ERROR 및 해결"
date: 2018-12-01 12:46:32 +0900
description:
categories: Blockchain
tags: [Blockchain, EOS]
---

## Error 3090003: Provided keys, permissions, and delays do not satisfy declared authorizations

```
Error 3090003: Provided keys, permissions, and delays do not satisfy declared authorizations
Ensure that you have the related private keys inside your wallet and your wallet is unlocked.
Error Details:
transaction declares authority '{"actor":"addressbook2","permission":"active"}', but does not have signatures for it.
```

#### resolved

- 해당 에러는 생성한 어카운트에 대한 private key가 default wallet에 import 되어 있지 않았을때 발생했다(즉, 권한이 없는경우 발생..)
- account 생성시, private key를 반드시 default wallet에 넣어줘야 함

## Error 3080001: Account using more than allotted RAM usage

```
Error 3080001: Account using more than allotted RAM usage
Error Details:
account addressbook2 has insufficient ram; needs 2996 bytes has 0 bytes
```

#### resolved

- eosio라는 계정의 contract 탭을 보면 여러 액션들이 존재한다.
- abi를 보면 newaccount에 대한 action arqument값들을 볼 수 있다.
- 아래처럼 실행하면, 계정이 생성된다!!!!!

`cleos system newaccount --stake-net "0.1000 EOS" --stake-cpu "0.1000 EOS" --buy-ram-kbytes 8 doublechain4 doubletestab EOS592NLgZJAaUAR2FYK8f959bYVQguULpfK4ZsPBa3KrTLMEVUAU`

## assertion failure with message: no active bid for name

- EOS에서, 12자리 이하 길이의 account명은 투표를 통해 생성한다.
- 12자리의 계정name으로 생성해야 한다.
