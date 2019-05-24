---
layout: post
title: "[EOS #3] EOS Smartcontract 테스트하기"
date: 2018-12-01 12:46:32 +0900
description:
categories: Blockchain
tags: [Blockchain, EOS]
---

## EOSIO.CDT (계약 개발 툴킷)

- EOSIO.CDT는 WebAssembly (WASM)를위한 도구 모음이며 EOSIO 플랫폼의 계약 작성을 용이하게하는 일련의 도구
- 범용 WebAssembly 툴 체인이 될뿐만 아니라, EOSIO 특정 계약을 통해 EOSIO 스마트 계약 구축을 지원할 수 있습니다.

## token 배포, 발행 및 전송

- contract token을 배포하기 전에 먼저 계정을 만들어야 하며, 이 계정을 eosio 개발 키를 사용 해야 한다.

## abi 파일

- 액션, 타입 등이 정의된 파일
- ABI (Application Binary Interface)는 JSON과 Binary 표현간에 사용자 작업을 변환하는 방법에 대한 JSON 기반 설명입니다.

## hello world tutorials

- account를 생성한 doublechain4 계정이 unlock된 상태로 실행되어야 한다.
- [테스트한 hello world contract file](https://github.com/EOSIO/eosio.cdt/blob/master/examples/hello/hello.hpp)

## Reference

- https://developers.eos.io/eosio-home/docs
