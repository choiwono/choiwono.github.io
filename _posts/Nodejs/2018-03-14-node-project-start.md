---
layout: post
title:  "Node Express 프로젝트 시작하기"
date : 2018-03-14 12:46:32 +0900
description: 
categories: Nodejs
tags: Node Express
---

# Node Express 프로젝트 시작하기
## Express 설치하기
**1)** node를 설치한 뒤, 어플리케이션을 보관할 디렉토리 설정 후, 그 디렉토리를 작업디렉토리로 설정.    
**2)** npm init 명령을 이용해 어플에 대한 package.json파일을 작성.    
`npm init`  
  
**3)** 이 명령을 실행하면 애플리케이션의 이름 및 버전과 같은 몇 가지 정보에 대해 프롬프트한다. 지금은 다음의 항목을 제외한 대부분의 항목에서 ENTER 키를 눌러 기본값을 수락할 수 있다.  
`entry point: (index.js)`     
   
**4)** 기본파일의 이름을 app.js로 입력하거나 자유롭게 입력. 기본파일의 이름을 index.js로 입력하기 원하는 경우에는 ENTER기를 눌러 제안된 기본 파일이름을 수락한다.  
이제 app 디렉토리에 Express를 설치한 후 종속 항목 목록에 저장한다.   
`npm install express --save`  
  
**5)** Express를 임시로 설치하고 종속 항목 목록에 추가하지 않으려면 다음과 같이 --save옵션을 생략한다.  
`npm install express`  
  
<mark>--save 옵션을 통해 설치된 Node 모듈은 package.json 파일 내의 dependencies 목록에 추가됩니다. 이후 app 디렉토리에서 npm install을 실행하면 종속 항목 목록 내의 모듈이 자동으로 설치됩니다.</mark>

## NPM 노드 패키지 모듈
- 코드 재사용을 위한 라이브러리들

## Express 
- Express는 노드를 만든 패키지의 일종. 웹 서버를 만들기 위한 것.  
- node base web server
- 프레임워크
- 사용법
{% highlight js %}
var express = require('express'); //node 모듈에 있는걸 가져옴
var app = express(); //express의 반환값이 함수이기때문에, 또다른 app에 함수정보를 담는다. //세미콜론 생략가능
{% endhighlight %}

  
- 서버 응답처리  
{% highlight js %}
app.listen(3000, function(){
	console.log("start express server port 3000!")
});
{% endhighlight %}


## node_module
- express가 필요로하는 모듈들이 모두 있는 곳.

## package.json
- 노드 프로젝트가 의존하고 있는 라이브러리를 모두 package.json이 등록하고 있다.
- 다음에 노드 프로젝트를 받았을때 쉽게 같은 환경을 가지고 설치할 수 있도록 도와준다.

## package-lock.json 이란?
- package.json이 생성될때 같이 사용 됨.
- Package-lock.json 파일에는 package.json 파일에 나열된 종속성과 설치할 특정 종속성 버전이 들어 있습니다
- <mark>npm이 node_modules 트리 또는 package.json을 수정하는 모든 작업에 대해 package-lock.json이 자동으로 생성됩니다.</mark> 생성 된 정확한 트리를 설명하므로 후속 설치가 중간 종속성 업데이트에 관계없이 동일한 트리를 생성 할 수 있습니다.
- 이 파일은 소스 저장소에 커밋되고 다양한 용도로 사용됩니다.
- 이 패키지의 이름은 패키지 잠금입니다. 이것은 package.json의 내용과 일치해야합니다.
- 팀원, 배포 및 지속적인 통합을 통해 동일한 종속성을 설치할 수 있도록하는 종속성 트리의 단일 표현을 기술할때 사용.

## node 프로젝트
#### node 프로젝트 실행 시 비동기성
- 노드는 거의 다 비동기로 실행된다.
- 아래코드 생성시, end of server code,... 먼저 콘솔로그에 찍힌다. (비동기로 실행되기 때문에!!)
{% highlight js %}
app.listen(3000, function(){
	console.log("start express server port 3000!")
});

console.log('end of server code,...'); 
{% endhighlight%}

#### nodemon, supervisor, forever,, etc
- 자동으로 파일의 변화를 감지해 노드를 스타트 시켜준다.
`npm install nodemon -g`
- -g : 글로벌 , 내 pc의 어느 디렉토리에서도 실행할 수 있다. (--save는 현재 프로젝트 내)

#### 라우팅을 통한 모든 요청 처리
{% highlight js %}
app.get('/', function(req, res){
	res.sendFile(__dirname + "/public/main.html");
})
{% endhighlight%}
- __dirname : node에서 최상의 절대 경로가 담겨있음

#### static처리
- static 한 것들. js, css, img ...자바스크립트 같은 파일들을 정적인 파일들이라고 함. 이런 파일들은 page가 요청받는 대로 자동으로 처리해주는게 좋다.
- static directory를 express에 설정하여 처리한다. 
- `app.use(express.static('public'))` 이렇게 하면 public에서 알아서 읽음.

#### 출처
- [What is package-lock.json?/stackoverflow](https://stackoverflow.com/questions/45841596/what-is-package-lock-json)
- [package-lock.json](https://docs.npmjs.com/files/package-lock.json)