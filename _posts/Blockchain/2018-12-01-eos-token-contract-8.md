---
layout: post
title: "[EOS #8] EOS token contract 테스트 과정 (from eos튜토리얼)"
date: 2018-12-01 12:46:32 +0900
description:
categories: Blockchain
tags: [Blockchain, EOS]
---

# Token Contract complie/deploy/issue/transfer

---

## 1. Contract 경로에 include, src 폴더를 만들어 컨트랙트 파일을 생성한다.

- /home/username/eosio.contracts/eosio.token(token이름은 eosio.token로 생성함)
- 디플로이할 기본 token 컨트랙트 ==> eosio.token.cpp
- [link : token contract file](https://github.com/wkimdev/eos-smartcontract-dc)

## 2. 컨트랙트를 deploy할 계정을 생성한다(계정이름은 원하는 이름으로 생성 ex : oasistokenn1)

- `cleos create account oasistokenn1 oasis.token EOS5Lq6aP8LPVEwWMRFJjtPNCnQ9C3r41SwW4fEpmRZWJo9Qt4cjJ`
- [link : account 생성하는 법](https://github.com/wkimdev/eos-smartcontract-dc/wiki/5.2-EOS-Account-%EC%83%9D%EC%84%B1%EB%B2%95)

## 3. Compile the contract

- 기본명령어 : `eosio-cpp -I include -o [파일명.wasm] [.cpp파일경로] --abigen`
- example : `eosio-cpp -I include -o oasis.token.wasm /home/skdev/eosio.contracts/eosio.token/src/eosio.token.cpp --abigen`

## 4. Deploy the Token Contract

- 기본명령어 : `cleos set contract eosio.token CONTRACTS_DIR/eosio.contracts/eosio.token --abi eosio.token.abi -p eosio.token`
- example : `cleos set contract oasistokenn1 /home/skdev/eosio.contracts/eosio.token --abi eosio.token.abi -p oasistokenn1`
- 하지만, 위의 명령어를 실행하면 Account using more than allotted RAM usage이라는 에러 메세지가 뜬다. 이를 해결하기 위해 5번 과정의 buy ram을 수행한다.

## 4-1. Buy RAM

- [link : buy ram 방법](https://github.com/wkimdev/eos-smartcontract-dc/wiki/4.-Buy-RAM)
- 에레메세지에서 부족하는 만큼의 양을 보충할 충분한 ram을 사주도록 한다.
- `cleos system buyram oasistokenn1 oasistokenn1 '10.000 EOS'`

```
Reading WASM from /home/skdev/eosio.contracts/eosio.token/eosio.token.wasm...
Publishing contract...
Error 3080001: Account using more than allotted RAM usage
Error Details:
account oasistokenn1 has insufficient ram; needs 230274 bytes has 179384 bytes
```

## 4-2. Buy RAM 이후, set contract를 실행하면 아래와 같이 결과가 잘 나온다.

- example : `$ cleos set contract oasistokenn1 /home/skdev/eosio.contracts/eosio.token --abi eosio.token.abi -p oasistokenn1`
- abi 파일 생성 이후 버전을 확인결과 : "eosio::abi/1.1"

```
Reading WASM from /home/skdev/eosio.contracts/eosio.token/eosio.token.wasm...
Publishing contract...
executed transaction: be273569dc63c7d4c7abea4f4cd36e1d3be4651ed8b5e04c120760812dbf3a8d  9312 bytes  1196 us
#         eosio <= eosio::setcode               {"account":"oasistokenn1","vmtype":0,"vmversion":0,"code":"0061736d0100000001aa011c60037f7e7f0060047...
#         eosio <= eosio::setabi                {"account":"oasistokenn1","abi":"0e656f73696f3a3a6162692f312e310008076163636f756e7400010762616c616e6...
```

## 5. Create token

- 새 토큰을 만들려면 적절한 인수를 사용하여 create (...) 액션을 호출.
- 이 액션은 1 개의 인수를받습니다. 이것은 최대 공급 float와 대문자로 된 alpha 문자로 된 symbol_name 두 개의 데이터로 구성된 symbol_name 유형.
- 발급자는 발급을 요청하거나 소유자의 동결, 회수 및 허용 목록 작성과 같은 다른 작업을 수행 할 수있는 권한을 가진 사람이됩니다
- create의 첫번째 인자는 토큰발행자, 두번째 인자는 토큰의 최대 수량.
- `cleos push action oasistokenn1 create '[ "oasistokenn1", "1000000000.0000 OAS"]' -p oasistokenn1`

```
executed transaction: e04526ad1bb3e6c8bf983bc570cf3779139572242b8213f0435b5b87ac55e7ba  120 bytes  307 us
#  oasistokenn1 <= oasistokenn1::create         {"issuer":"oasistokenn1","maximum_supply":"1000000000.0000 OAS"}
warning: transaction executed locally, but may not be confirmed by the network yet         ]

```

- 정글넷에서 해당 토큰이 생성된지 확인가능하다.
  <img width="625" alt="token_create" src="https://user-images.githubusercontent.com/32521173/50257932-c6214d00-0440-11e9-9909-f41ffeb8744c.png">

---

## 6. Issue Tokens

- 토큰을 발행하기 위해 issue action을 수행한다.
- 첫번째인자는 토큰을 발행받는 account( **컨트랙트를 디플로이한 계정과 다르다!!!** )
- `cleos push action oasistokenn1 issue '["goldengatejb", "10000.0000 OAS", "oasis token issue test"]' -p oasistokenn1`

```
$ cleos push action oasistokenn1 issue '["oasiswkimdev", "10000.0000 OAS", "oasis token issue test"]' -p oasistokenn1
executed transaction: 83dedf35587376e7775767a30e253cec2057e25935b877caa238ba9560271a61  144 bytes  525 us
#  oasistokenn1 <= oasistokenn1::issue          {"to":"goldengatejb","quantity":"10000.0000 OAS","memo":"oasis token issue test"}
#  oasistokenn1 <= oasistokenn1::transfer       {"from":"oasistokenn1","to":"goldengatejb","quantity":"10000.0000 OAS","memo":"oasis token issue tes...
#  goldengatejb <= oasistokenn1::transfer       {"from":"oasistokenn1","to":"goldengatejb","quantity":"10000.0000 OAS","memo":"oasis token issue tes...
```

- 아래 그림 처럼 확인 가능하다.  
  <img width="633" alt="token_issue" src="https://user-images.githubusercontent.com/32521173/50257968-ec46ed00-0440-11e9-8f77-11aff2329735.png">

## 7. Transfer Token

- 토큰을 발급받은 계정이 transfer를 다른 계정에게 수행할 수 있다. (-p option을 주의한다! - 아래 명령어에서 -p옵셥에 들어갈 account는 토큰을 발송할 즉, 토큰을 지급받은 계정이다)
- `cleos push action oasistokenn1 transfer '[ "oasiswkimdev", "doublechainw", "35.0000 OAS", "OAS token transfer" ]' -p oasiswkimdev`
- 아래처럼 송금가능하고 송금확인할 수 있다.

```
executed transaction: e68264415411d9b41a1edd0ae93b1b9cc6e46802417d14e639ea9678760acacd  144 bytes  322 us
#  oasistokenn1 <= oasistokenn1::transfer       {"from":"oasiswkimdev","to":"doublechainw","quantity":"35.0000 OAS","memo":"OAS token transfer"}
#  oasiswkimdev <= oasistokenn1::transfer       {"from":"oasiswkimdev","to":"doublechainw","quantity":"35.0000 OAS","memo":"OAS token transfer"}
#  doublechainw <= oasistokenn1::transfer       {"from":"oasiswkimdev","to":"doublechainw","quantity":"35.0000 OAS","memo":"OAS token transfer"}
```

---

#### token issue 수량 확인 확인

- `cleos get currency balance oasistokenn1 goldengatejb`

## Reference

- https://developers.eos.io/eosio-home/docs/token-contract
