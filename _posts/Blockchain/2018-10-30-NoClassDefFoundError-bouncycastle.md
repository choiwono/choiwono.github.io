---
layout: post
title: "java.lang.NoClassDefFoundError: org/bouncycastle/crypto/Digest error"
date: 2018-10-30 12:46:32 +0900
categories: [Blockchain]
tags: [NoClassDefFoundError, bouncycastle, web3j]
---

## NoClassDefFoundError bouncycastle web3j

- 기존 프로젝트를 그대로 복붙하면서 따라하던 중
- web3j maven depengency 추가 이후 config class를 만들어 제대로 연결이 되었는지 테트를 해보는데 아래의 에러가 발생하였다..
- `java.lang.NoClassDefFoundError: org/bouncycastle/crypto/Digest`

## 시도해본방법들..

- maven clean, install, update,,
- depengency 수정
- project 다시 만들기..

## 최종해결방법

- https://www.bouncycastle.org/latest_releases.html
- 여기서 jar 파일 받아서 추가해주니까 된다... ㅠㅠ
