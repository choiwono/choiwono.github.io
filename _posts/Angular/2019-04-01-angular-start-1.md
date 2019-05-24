---
layout: post
title: Angular기반 오픈소스 프로젝트를 이용한 사내 Private Explorer 구축 1
date: 2019-04-01 12:46:32 +0900
description:
categories: Angular
tags: Angular
---

# Intro

- 사내 프로젝트를 위해 explorer 사이트를 열게 되었다. 1차적으로 private network를 기존 EOS Tracker에 있던 EOS Blockchain 설정만 바꾸어 먼저 오픈을 하게 되었다.
- EOS Tracker는 EOSEssentials의 프로젝트 중 하나로 볼 수 있다.
- EOS Tracker는 **Real time block explorer for EOS Blockchain** 로서, Oasisbloc Explorer는 이 프로젝트의 소스코드를 clone하여 작업을 진행하였다.
- [EOSEssentials의 다른 프로젝트를 확인하려면 이곳에서](https://github.com/EOSEssentials)

# Angular 기본 서칭

angular나 typescript에 대한 충분한 지식 없이 급하게 반영하다보니 코드를 보면 답답한 마음이 많이 들었던 작업이었다. 이제라도 angular에 대한 기본적인 것을 정리해보려고 한다.

- 프로젝트는 angular 6를 사용했다.
- angular 는 Typescript를 기반으로 개발이 되고, angular 어플리케이션은 typescript를 javascript로 변환해야 한다.
  (angular는 typescript 뿐만 아니라 javascript, dart로도 작성될 수 있지만, angular 공식 문서, 커뮤니티에서 가장 많이 활용되고 있는건 typescript라고 함.)
- 또한 프로젝트가 의존하는 모듈들은 로드하는 html 파일의 script 태그를 작성해야 한다.
- Angular CLI를 이용해 프로젝트를 생성하면, 의존 모듈의 설치는 기본 패키지 매니저인 npm으로 자동화되어 진행된다.
- Angular cli의 빌드 기능은 의존성 관리를 위한 작업을 자동화 하여 진행한다.
- aunular cli 빌드 기능은 내부적으로 모듈 번들러인 webpack을 이용하여 아래 작업을 자동으로 지원한다.

```
- TypeScript에서 javascript로의 트랜스파일링
- 디버깅 용도의 source map 파일 생성
- 의존 모듈과 HTML, CSS, 자바스크립트 번들링
- 코드의 문법 체크
- 코드의 규약 준수 여부 체크
- 불필요한 코드의 삭제 및 압축
- AoT 컴파일
```

## Angular CLI란?

- angular cli는 간단한 명령어를 사용해 angular 프로젝트 스캐폴딩(scaffolding)을 생성, 실행, 빌드할 수 있다.
- angular의 다양한 구성 요소들을 선별적으로 추가할 수 있는 커맨드-라인(command line interface)이다.
- 개발용 서버를 내장하고 있어서 간단히 프로젝트를 실행시켜서 동작을 확인할 수 있다. ( `ng serve, ng serve &` )
- (심플하게 생각하면 프레임워크? angular cli는 프레임워크의 개발환경을 구축해준다~ )
- angular cli는 angular 프로젝트 스캐폴딩을 간단한 명령어로 생성해주어 개발환경 구축에 소요되는 시간을 최소화할 뿐 아니라, 표준 스타일 가이드를 제공한다.

#### angula cli가 주로 지원해주는 기능

- angular 프로젝트 생성
- angular 프로젝트의 컴포넌트, 디렉티브, 파이프, 서비스, 클래스, 인터페이스 등의 구성 요소 추가
- **liveReload를 지원하는** 내장 개발 서버를 사용한 angular 프로젝트 실행
- unit/e2e(end-to-end) 테스트 환경 지원
- 배포를 위한 angular 프로젝트 패키징

## Angular 6 특징

- 2018년 5월 출시
- angular 5와 거의 유사, 5와 호환됨

#### Angular 5 에서 도입된 기능

- HTTPClient API
- Lambda지원
- 여러 별칭을 사용해 구성 요소를 내보내어 마이그레이션 프로세스를 쉽게 수행 가능
- 표준화를 위한 새로운 파이프 도입
- 빌드 최적화 도입, build optimizer를 자동 지원
- 향상된 컴파일러 적용

## Angular 5 에서 추가된 기능

- angular-cli 명령어 업데이트 : ex) ng-update
- cdk 업데이트,
- Angular Material Updated
- 반응형 js 라이브러리인 `RxJS` 사용
- Angular Element : 앵귤러 컴포넌트를 모든 html 페이지에서 사용할 수 있는 웹 컴포넌트로 게시할 수 있다. 앵귤러 요소 패키지를 사용하면 네이티브 맞춤 요소를 쉽게 만들 수 있다.
- Tree on Service : 죽은 코드 제거 가능…

## 기타, Angular의 observable

- observable은 angular 2가 나왔을때 소개된 것.
- 옵저버블은 angular의 특정 기능이 아니고, es7 릴리스에 포함될 **비동기 데이터를 관리하기 위한** 새로운 표준이다.
- angular는 이벤트 시스템과 http서비스에서 옵저버블을 광범위하게 사용한다.

## Reference

- https://jodu.tistory.com/38
- https://poiemaweb.com/angular-cli
- https://poiemaweb.com/angular-basics
- https://han41858.tistory.com/45
- https://steemit.com/javascript/@noreco/webpack
- https://feel5ny.github.io/2018/03/25/angular_observable/
