---
layout: post
title: Angular기반 오픈소스 프로젝트를 이용한 사내 Private Explorer 구축 2
date: 2019-04-02 12:46:32 +0900
description:
categories: Angular
tags: Angular
---

- environment 에서 블록체인 설정 정보, tokeninfo로 새로 설정
- 퍼블리싱 작업 완료
- AWS에 구축한 서버에 nginx로 웹서비스 띄우기 .. 그 이후 내가 빌드한 소스코드를 배포한다.

## 빌드(build)

- angular를 개발하면 angular-cli를 사용할 수 있게 되고, 이를 이용하면 ng-build 라는 명령어를 사용해 간단한 빌드를 진행할 수 있다.
- 개발모드에서 build를 진행할 경우 `ng-build`라는 명령어를 사용하면 되지만, 운영 모드에서 빌드시엔, `ng-build --prod`라는 옵션을 사용한다.
- 단, --prod모드에서는 엄격한 문법 체계가 적용되기 때문에, 개발모드에서 발생하지 않던 문법 에러가 발생할 수 있다. 나같은 경우에도, 당장 운영에 올려야 하는데 개발모드에서 나지 않던 문법 에러가 발생하였었고, 해결을 위해 문법 적인 부분을 좀 더 찾아보고 수정해 무사히 빌드를 시켰었다.
- 최적화를 위해 좀 더 엄격히 문법을 체크하는 걸까..
- 해당 명령어가 동작하고 나면 프로젝트의 하위 폴더로 `dist`라는 폴더가 생성되고, 이 폴더 안에 있는 파일들이 app을 동작 시키는데 필요한 파일들이 된다.
- 다시 빌드할경우 기존 dist폴더에 있는 내용을 전부 삭제 후 실행한다.

## webpack에 대한 간단한 이해

- vue도 그렇고, angualr도 그렇고 프로젝트를 빌드할시 webpack으로 번들링을 할 수 있다.

#### webpack 기본 개념

- 노드 모듈중의 하나. 기본적으로 nodejs설치가 필요하다.
- 웹팩은 웹에서 사용되는 모든 자원(assets)을 번들링 해주는 도구이다.
- 번들링이란, 여러개의 파일 중에 종속성이 존재하는 파일을, 하나의 파일로 묶어 패키징을 시키는 과정을 의미한다.
- 1번 파일에서 2번 파일에 있는 함수를 호출해야 할 경우, 스크립트를 불러오는 순서를 2번 파일을 제일 상단에 올려놓고 1번 파일을 그 다음에 불러와지도록 선언 했지만, 이제는 모듈이라는 개념을 사용해 1번 파일에서 2번 파일을 불러오기만 하면 된다.

#### 사용 장점

- 번들링을 함으로써 파일은 하나로 합쳐지고 네트워킹 요청횟수는 줄어들게 된다.
  - 자바스크립트간의 종속성 뿐만 아니라 스크립트내에서 필요한 css나 img와 같은 파일(.css, .jsp)도 번들링해서 하나의 파일로 합쳐주기 때문에, 네트워크 요청을 최소할 수 있다.
- 중복된 소스코드도 최소화
- 모듈 개념을 사용하기 때문에 글로벌이 오염되지 않는다.

## 배포(deploy)

#### 주어졌던 배포 환경

- AWS서버에 블록체인 노드 구축이 되었다.
- 각 노드들에 대한 네트워크 구성이 되었고,
- 담당을 받은 네트워크에 접속하기 위한 pem파일을 받아 해당 서버에 접속하여 작업을 진행하였다.
- 서버는 `Ubuntu 18.04.2 LTS`, `Nginx`
- AWS에서 ssl설정 처리를 해주고 있었기 때문에 내가 따로 deploy이후에 ssl설정할 부분은 없었다.
- AWS Elastic load balance 에서 서비스 도메인을 port 80으로 프록시를 해주고 있음.  
  ![이미지](/post_assets/2019-04-01/elb-flow.png)

#### 사용한 배포툴

- pm2
- `sudo pm2 start server.js`
- [pm2의 ecosystem.config.js를 사용하지 못한 이유...]()

#### pm2 로 모니터링

- `pm2 list`
- `pm2 monit 0`
- `pm2 log 0`

## 기타 사용해 본 서비스 실행 방법

#### http-server

- 소스코드경로에서 `sudo http-server ./dist/ -p 80`

#### nginx

- nginx 경로 : `/etc/nginx/sites-available`
- Nginx 실행 : `sudo service nginx restart`

## 크로스 브라우징 설정

- angular의 경우, ie 브라우저 호환성 체크를 위해 polyfills.ts를 확인하면 된다.
- 하지만 작업 중, 문서와 다르게 polyfills.ts에서 ie지원을 위한 모듈들의 주석을 다 해제했으나 ie11 이하 아래 버전은 호환되지 않았었다. (그래서 결국 ie11로만 갔다)

## 실수한 점

- 업무 중 코어 네트워크가 바뀌었을때 예의 주시 했어야 했는데 미처 챙기지 못해, 데이터를 제대로 불러오지 못하여 테스트 중 이슈가 있었다.

## Reference

- https://jodu.tistory.com/38
- https://poiemaweb.com/angular-cli
- https://poiemaweb.com/angular-basics
- https://han41858.tistory.com/45
- https://steemit.com/javascript/@noreco/webpack
- https://feel5ny.github.io/2018/03/25/angular_observable/
