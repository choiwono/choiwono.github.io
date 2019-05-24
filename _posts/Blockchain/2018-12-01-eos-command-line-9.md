---
layout: post
title: "[EOS #9] EOS command line 명령어 정리"
date: 2018-12-01 12:46:32 +0900
description:
categories: Blockchain
tags: [Blockchain, EOS]
---

## node start, stop

- node 스타트 및 중지

```
/JungleTestnet-doublechain4$ sudo ./stop.sh
[sudo] password for user:
20216
Stoping Nodeos.stderr.txt
stdout.txt
Nodeos stopped.
user@user:~/jungle/JungleTestnet-doublechain4$ sudo ./start.sh
```

## log 보는 법

- `JungleTestnet-doublechain4$ tail -f stderr.txt`

## smartcontrac 조회

- `/eosio.cdt/examples$ ls`
- `cat hello.contracts.md` : contract 읽기.

## keosd &

- keosd 시작 명령어

## testnet 계정 정보 확인

- `./cleos.sh get account doublechain4`

## cleos 명령어 사용 directory는 jungle testnet을 설치한 doublechain4

- `JungleTestnet-doublechain4` : test net 에서 실행.
- 해당 경로로 들어오면 config.ini, cleos.sh, start.sh, stop.sh 등 실행

## ./cleos.sh get info

- jungleTestnet-doublechain4 경로로 들어와 위의 명령어를 실행하면 아래의 결과값을 확인할 수 있다.

```
{
  "server_version": "ea08cfd3",
  "chain_id": "e70aaab8997e1dfce58fbfac80cbbb8fecec7b99cf982a9444273cbc64c41473",
  "head_block_num": 2954573,
  "last_irreversible_block_num": 2954237,
  "last_irreversible_block_id": "002d13fd8d866046d46b30b642175ca470670243641008292f1d39b1b161ec25",
  "head_block_id": "002d154d39930c38cf0533a5a431c1d34ba7c95b7f11ef2fcbdbc323fa7b51a7",
  "head_block_time": "2018-12-10T23:36:59.500",
  "head_block_producer": "bighornsheep",
  "virtual_block_cpu_limit": 200000000,
  "virtual_block_net_limit": 1048576000,
  "block_cpu_limit": 199920,
  "block_net_limit": 1048576,
  "server_version_string": "v1.5.0"
}
```

## wallet password 확인

- `/home/username/jungle` : 해당 경로에서 wallet password를 확인할 수 있다. 원래 wallet을 생성하면 자동으로 기록이 어딘가에 되는데 그게 어딘지 아직은 잘모르겠다.
- wallet을 생성한다음 편집기 명령어로 password를 생성해서 저장하고 수시로 확인 및 편집할 수 있다.
- `nano wallet_pass.txt`

## wallet 생성 명령어

- ./cleos.sh wallet create --to-console -n kim

## 계정으로 contract 실행

- `./cleos.sh push action doublechain4 hi '["wkimdev2"]' -p doublechain4`

## keosd가 잠겼는지 확인 및 다시 연결시킬 경우

- ps -ef|grep keosd
- sudo kill -9 20229

## kill and re connection test

- (1) check `ps -ef|grep keosd`
- (2) sudo kill -9 20229
- (3) 연동이 되는지 확인 ./cleos.sh wallet list ==> 안되면 이렇게 뜰 것임.. 'Failed to connect to keosd at... '
- (4) 그러면 이 경로로 간뒤 `jungle/wallet$ ls`
- (5) `~/jungle/wallet$ sudo ./start.sh` 실행. (관리자 권한으로 실행안하면 start 안됨)
- 위의 명령어로 잘 수행되면 아래처럼 결과가 나옴

```
$ sudo ./start.sh
21465
/home/user/jungle/wallet/stop.sh: line 16: kill: (21465) - No such process
Wallet stopped.
Wallet started
```

- (6) 그 뒤 `rm: remove write-protected regular file '/home/skdev/jungle/wallet/wallet.pid'?` 가 뜨면 `no` 로 대답
- (7) 다시 확인 `~/jungle/wallet$ ps -ef|grep keosd` --> process가 다시 잘 돌아가는지 확인한다 .

## 생성한 wallet list 확인

- 해당 경로로 간다. `:~/jungle/wallet$`
- `ls -l` 명령어로 내가 생성한 wallet list를 확인할 수 있다.
- `rm -rf kim.wallet` 명령어로 wallet 을 삭제할 수 있다.

## key 생성

- public key를 생성하기 이전에 먼저 지갑을 unlock 해야 한다.
- 아래 명령어로 unlock한다 : `./cleos.sh wallet unlock -n wkimdev2`
- unlock되었는지 확인 : `./cleos.sh wallet list`

```
Wallets:
[
  "wkimdev2 *"
]
```

- key 생성 : `$ ./cleos.sh wallet create_key -n wkimdev2`
- 결과 : `Created new private key with a public key of: "EOS45d..."`

```
[
  "EOS45d..."
]
```

## key 확인

- `./cleos.sh wallet keys`
- 결과값

```
[
  "EOS45d..."
]
```

## 쌍으로 생성되는 public key와 private key 확인(private key --> 5 로 시작한다.)

- `$ ./cleos.sh wallet private_keys -n wkimdev2`
- 아래와 같이 쌍으로 붙인다.

```
password: [[
    "EOS4u.....", // public
    "5K....." // private
  ]
]
```

- 여기까지 하고 정글테스트넷에서 계정 생성한다음,
- faultset에서 eos token 받고 확인.

## ./cleos.sh wallet list

- wallet list 확인. lock들이 되어 있는 경우 아래처럼 결과가 나온다.

```
Wallets:
[]
```

## wallet 생성

- ./cleos.sh wallet create --to-console -n kim
  ./cleos.sh wallet create --to-console -n wkimdev

## list 확인

- ./cleos.sh wallet list

##

- jungle\$ ls
- 여기서 wallet 확인 : nano wallet_pass.txt
- ps -ef|grep keosd

## ./cleos.sh wallet list

## :~/jungle/wallet\$ ls -l

## cleos.sh 이 스크립트를 사용.

./cleos.sh wallet create_key -n wkimdev

- **그리고 기억! : 개발버전에서 account 생성하는 것처럼 운영에서 생성할 수 없다. 다르다.**

- `.sh 파일?` : 명령어 처리기 파일.
- `ls -l` : 파일 리스트 목록 반환
- `./`의 의미? :
- `tail -f stderr.txt` : 로그 확인
