---
layout: post
title: "Rest API란?"
date: 2018-02-01 12:46:32 +0900
categories: 웹서비스
tags: Node REST-API URI
---

# Rest API란?

- REST : Representational State Transfer
  > 표면적인 실체는 '스타일'또는 '패턴'이라고 할 수 있다. 많은 정보, 리소스들을 어떻게 전달할 것인가?) 기존방식보다 데이터를 주고 받을때 더 낫게 하도록...
  > 일관성 있는 웹서비스 인터페이스 설계를 위해 사용.
- 주로 비동기로 데이터를 주고받을때 API를 주고 받을 때 정해진 규칙하에 전달하는 것.
- 웹을 근간으로 하는 http protocol 기반이다.
- 리소스(자원)은 URI(Uniform Resource Identifiers)로 표현하며 말 그대로 '고유'해야 한다.
- URI는 단순하고 직관적인 구조이어야 한다.
- xml/json을 활용해서 데이터를 전송한다. (주로json- 보다 직관적)
- REST 개념은 <mark>서버와 클라가 완전히 분리된 개념이다.</mark>(일반적으로 웹개발을 할때 서버언어와 클라이언트 언어랑 같이 사용해서 이부분이 헤깔렸다.)
- REST하게 클라이언트랑 서버간에 데이터를 주고 받는 방식
- 프로젝트시 내가 한 작업은 철저히 서버단 작업이다. node로 개발하면서 클라이언트에 response해주는 인터페이스를 rest라고 보면 된다.
- 클라이언트는 앵글러나 리엑트로 작업해서 rest를 호출하여 데이터를 받는 것이다. 내가 한 작업에 빗대어 생각해보면 페이지에서 \$.ajax로 url주소로 데이터를 호출하는 부분이 있다.(jQuery로 서버데이터 호출하는 부분.) jQuery로 호출하는 url주소들을 내가 만들었던 것.

- 대충 요런식..
  {% highlight js %}
  cmdLoadByAjax : function (url, params, method) {
  var request = \$.ajax({
  type: !method ? 'POST' : method,
  url: url,
  contentType: 'application/json; charset=utf-8',
  data: params,
  dataType: 'json',
  async: true,
  cache: false
  });
  return request;
  }
  {% endhighlight %}

## 1. REST API의 탄생

- REST는 Representational State Transfer라는 용어의 약자로 2000년도에 로이 필딩 (Roy Fielding)의 박사학위 논문에서 최초로 소개. 로이 필딩은 HTTP의 주요 저자 중 한 사람으로 <mark>웹의 장점을 최대한 활용할 수 있는 아키텍처로써 REST를 발표</mark>

## 2. REST 구성

> 자원(Resource) - URI  
> 행위(Verb) - HTTP METHOD  
> 표현(Representations)

## 3. REST 의 특징

#### 1) Uniform (유니폼 인터페이스)

Uniform Interface는 URI로 지정한 리소스에 대한 조작을 통일되고 한정적인 인터페이스로 수행하는 아키텍처 스타일을 말합니다.

#### 2) Stateless (무상태성)

REST는 무상태성 성격을 갖습니다. 다시 말해 작업을 위한 상태정보를 따로 저장하고 관리하지 않습니다. <mark>세션 정보나 쿠키정보를 별도로 저장하고 관리하지 않기 때문에 API 서버는 들어오는 요청만을 단순히 처리하면 됩니다.</mark> 때문에 서비스의 자유도가 높아지고 서버에서 불필요한 정보를 관리하지 않음으로써 구현이 단순해집니다.

#### 3) Cacheable (캐시 가능)

**REST의 가장 큰 특징 중 하나는 HTTP라는 기존 웹표준을 그대로 사용하기 때문에,** 웹에서 사용하는 기존 인프라를 그대로 활용이 가능합니다. 따라서 HTTP가 가진 캐싱 기능이 적용 가능합니다. HTTP 프로토콜 표준에서 사용하는 Last-Modified태그나 E-Tag를 이용하면 캐싱 구현이 가능합니다.

#### 4) Self-descriptiveness (자체 표현 구조)

REST의 또 다른 큰 특징 중 하나는 REST API 메시지만 보고도 이를 쉽게 이해 할 수 있는 자체 표현 구조로 되어 있다는 것입니다.

#### 5) Client - Server 구조

REST 서버는 API 제공, 클라이언트는 사용자 인증이나 컨텍스트(세션, 로그인 정보)등을 직접 관리하는 구조로 각각의 역할이 확실히 구분되기 때문에 **클라이언트와 서버에서 개발해야 할 내용이 명확해지고 서로간 의존성이 줄어들게 됩니다.**

#### 6) 계층형 구조

REST 서버는 다중 계층으로 구성될 수 있으며 보안, 로드 밸런싱, 암호화 계층을 추가해 구조상의 유연성을 둘 수 있고 PROXY, 게이트웨이 같은 네트워크 기반의 중간매체를 사용할 수 있게 합니다.

## 4. REST API 디자인 가이드(중요)

- REST API 설계 시 가장 중요한 항목
  > 1. **URI**는 정보의 자원을 표시
  > 2. 자원에 대한 행위는 **HTTP Method(GET, POST, PUT, DELETE)**로 표현한다.

## 4-1. REST API 중심 규칙

1. URI는 정보의 자원을 표현해야 한다. (리소스명은 동사보다는 **명사**를 사용)  
   `GET /members/delete/1` (x)

- 위와 같은 방식은 REST를 제대로 적용하지 않은 URI입니다. URI는 자원을 표현하는데 중점을 두어야 합니다. delete와 같은 행위에 대한 표현이 들어가서는 안된다.

2. 자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE 등)로 표현  
   위의 잘못 된 URI를 HTTP Method를 통해 수정해 보면 아래와 같다.  
   `DELETE /members/1`

- 회원정보를 **가져올 때는 GET,** 회원 **추가 시의 행위를 표현하고자 할 때는 POST** METHOD를 사용하여 표현합니다.
  {% highlight js %}
  //회원정보를 가져오는 URI
  GET /members/show/1 (x)
  GET /members/1 (o)
  {% endhighlight %}

{% highlight js %}
//회원을 추가할 때
GET /members/insert/2 (x) - GET 메서드는 리소스 생성에 맞지 않다.
POST /members/2 (o)
{% endhighlight %}

## HTTP METHOD의 알맞은 역할

- POST, GET, PUT, DELETE 이 4가지의 Method를 가지고 CRUD를 할 수 있다.

| METHOD |                                             역할                                              |
| :----: | :-------------------------------------------------------------------------------------------: |
|  POST  |                      POST를 통해 해당 URI를 요청하면 리소스를 생성한다.                       |
|  GET   | GET을 통해 해당 리소스를 조회, 리소스를 조회하고 해당 도큐먼트에 대한 자세한 정보를 가져온다. |
|  PUT   |                                 PUT을 통해 해당 리소스를 수정                                 |
| DELETE |                                  DELETE를 통해 리소스를 삭제                                  |

## API Design

- **복수명사를 사용(/movies)**
- 필요하면 URL에 하위 자원을 표현. (/movies/23)
- 필터조건을 허용할 수 있음 (/movies?state=active)

- ex)

|      URL       | Methods |           설명           |
| :------------: | :-----: | :----------------------: |
|    /movies     |   GET   | 모든 영화리스트 가져오기 |
|    /movies     |  POST   |        영화 추가         |
| /movies/:title |   GET   | title 해당 영화 가져오기 |
| /movies/:title | DELETE  |   title 해당 영화 삭제   |
| /movies/:title |   PUT   | title 해당 영화 업데이트 |
| /movies?min=9  |   GET   |   상영중인 영화리스트    |

## URI, URL의 차이?!

> - **URL : Uniform Resource Locator**, 정형화 된 리소스 위치표시
> - **URI : Uniform Resource Identifier**  
>   예전에는 URL이 가리키는게 파일리소스 였는데 요즘은 Rewrite 등의 Apache , IIS, Tomcat 핸들러 때문에 자원 이라고 부른다.

- URL 은 다음과 같다.  
  http://test.com/work/sample.pdf  
  test.com 서버에서 work 폴더안의 sample.pdf 를 요청하는 URL.
- URI(통합 자원 식별자) 의 예는 다음과 같다.  
  REST 서비스 (url로 실행되는 서비스)  
  http://service.com/tv/turn/on

- URI(동물) 가 좀더 상위 개념이라서 URL(강아지), URN(다람쥐) 등의 하위 개념을 포함한다.
- 모든 URL 는 URI 이다. 가 성립힌다. (TRUE), URI = URL + URN

#### 출처

- [REST API 제대로 알고 사용하기](http://meetup.toast.com/posts/92)
- [URL과 URI의 의미와 차이점 (Difference between URL & URI)](https://blog.lael.be/post/61)
- 인프런 강의
