---
layout: post
title:  "Node Express 프로젝트 POST 처리, 그리고 GET과 POST차이"
date : 2018-03-14 12:46:32 +0900
description: 
categories: Nodejs
tags: Node Express HTTP
---

## GET vs POST
- GET은 주소줄에 값이 ?뒤에 쌍으로 이어붙고 POST는 숨겨져서(body안에) 보내진다.
- GET은 URL에 이어붙기 때문에 길이제한이 있어서 많은양의 데이터는 보내기 어렵고 POST는 많은 양의 보내기에도 적합하다.(역시 용량제한은 있지만)
- 즉 http://url/bbslist.html?id=5&pagenum=2 같이 하는 것이 GET방식이고 form을 이용해서 submit을 하는 형태가 POST입니다.

## 언제 GET을 쓰고 언제 POST를 써야 하는가
- 원래의 목적에 맞게 기술을 사용하고 있는가?에 대한 고민 필요.
-  클라이언트에서 서버로 데이터를 전송하려면 GET 아니면 POST밖에 없습니다.(사실 HTTP에는 PUT, DELETE등등 몇가지 더 있다..)
- <mark>GET은 가져오는 것이고 POST는 수행하는 것입니다.</mark>

## GET
- GET은 Select적인 성향을 가지고 있습니다.
- GET은 서버에서 어떤 데이터를 가져와서 보여준다거나 하는 용도이지 서버의 값이나 상태등을 바꾸지 않습니다. 게시판의 리스트라던지 글보기 기능 같은 것이 이에 해당하죠.(방문자의 로그를 남긴다거나 글읽은 횟수를 올려준다거나 하는건 예외입니다.)
- GET을 사용해야 하는 이유 : Link문제. 기본적으로 웹에서 모든 리소스는 Link할 수 있는 URL을 가지고 있어야 합니다.(퍼머링크(permalink)1퍼머링크라면 더 좋겠지만 꼭 퍼머링크가 아니라고 하더라도) 그래야 Link를 할 수 있으니까요. 쉽게 말하면 어떤 페이지를 보고 있을때 다른 사람한테 그 주소를 주기 위해서 주소창의 URL을 복사해서 줄 수 있어야 한다는 것입니다. POST를 할 결우에는 값이 내부적으로 전달되기 때문에 URL만 전달할 수 없죠. 글을 저장하는 경우에는 URL을 제공할 필요가 없기 때문에 POST를 해도 상관이 없는 것이고요.

## POST
- POST는 서버의 값이나 상태를 바꾸기 위해서 사용합니다. 
- 글쓰기를 하면 글의 내용이 디비에 저장이 되고 수정을 하면 디비값이 수정이 되죠. 이럴 경우에 POST를 사용.


# Node Express 프로젝트 POST 처리
## POST
- HTTP 메서드. url에 담겨있는게 아님. 서버에 있는 data를 처리할 시 사용한다.
- GET은 중요한 정보가 노출될 위험이 있다.
- post요청인 경우 'req.param('email')'와 같은 형태로 받을 수 없음.
- **body parser라는 별도의 모듈이 필요함!**   
`npm install body-parser --save` (--save 를 써서 package.json에 설치하기.)  
  
	1) app.js에 모듈 export   
`var bodyParser = require('body-parser');`  
  
	2) express서버에 바디 파서를 사용한다는걸(use) 알려줘야함.  
	  
* 아래 명령은 그냥 외운다고 생각하기.
	- askii형태 데이터만 주고 받는데, 한글같은건 다른 문자로 치환해야함. 이걸 인코딩해서 처리하는걸 이렇게 표기함. ==> app.use(bodyParser.urlencoded({extend:true}))  
	- 클라이언트도 브레이스 해서 보낼 수 있는데 json으로., 이렇게 표기해서 처리하겠다. ==> app.use(bodyParser.json()) 
{% highlight js %}
app.use(bodyParser.json()) 
app.use(bodyParser.urlencoded({extend:true}))
{% endhighlight %}


- req.body --> 보통 object형태로 들어옴.  
`res.send("<h1>welcome!"+req.body.email+"</h1>")!`


#### 출처
- [GET과 POST의 차이/아웃사이더blog](https://blog.outsider.ne.kr/312)
- 인프런 강의