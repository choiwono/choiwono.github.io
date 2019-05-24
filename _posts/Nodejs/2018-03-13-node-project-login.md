---
layout: post
title:  "Passport.js 타사 로그인인증 구현(facebook)"
date : 2018-03-13 12:46:32 +0900
description: 
categories: Nodejs
tags: Nodejs passport.js
---

# Passport.js를 통해 타사 로그인 인증 구현(facebook).
- **Passport.js 패키지**를 사용해 로그인을 구현하도록 한다. passport는 한글로 여권이다. 이름처럼 자신의 웹사이트에 방문할때 여권같은 역할을 함으로서 로그인을 쉽게 할 수 있게 도와준다. 
- passport.js 를 사용하면 타사인증을 훨씬 쉽게 할 수 있다.
- 로그인 하는 사용자 정보를 직접저장하지 않고, 네이버/카카오/페북 등을 통해 확인하는 절자를 거쳐서 로그인을 한다.
- passport.js를 사용함으로서 <mark>사용자 정보를 저장할 필요가 없기 때문에 안전하고, 회원가입을 훨씬 더 간단히 처리할 수 있는 장점이있다</mark>.
- express-session은 passport로 로그인 후 유저 정보를 세션에 저장하기 위해 사용한다. 
- 나같은 경우, 타사 로그인 인증 대상으로 facebook을 선택했다.


#### 1. facebook app을 설정
- facebook developer로 접속하여 프로젝트를 생성한다.
- myapp 등록을 통해 facebook을 이용하는 일종의 응용app으로 설정된다고 한다...

#### 2. myapp에 로그인 인증을 사용하기 위한 설정을 한다.

#### 3. passport.js - facebook설정을 위해 홈페이지를 참고
- https://github.com/jaredhanson/passport-facebook#readme 에 들어가서 facebook 로그인을 설정하기 참고.
- [passportjs.org](http://www.passportjs.org/)

#### 4. 필수 npm 설치하기
`npm install passport-facebook`

#### 5. facebook strategy를 설정
- local strategy아래에(기존에 설정했던)  facebook strategy를 설정한다.

{% highlight js %}
var LocalStrategy = require('passport-local').Strategy;
var FacebookStrategy = require('passport-facebook').Strategy;
{% endhighlight %}


- facebook전략으로 동작하도록 설정.
{% highlight js %}
app.get(
   '/auth/facebook', 
   passport.authenticate( //facebook전략으로 동작
     'facebook',
     { scope: 'email' } //scope을 추가해서 페북에서 한번더 확인해야 한다.
    ),
);
{% endhighlight %}

#### facebook Strategy 설정 중.. 타사인증에 라우터 두개 필요, why??
> **1번째 과정** : 페북과 내 app이 왕복(passport가 facebook으로 redirect시켜줌.)  
> **2번째 과정** : fb에서 몇개의 정보를 붙여서 callback으로 처리. (서로 확인하는 과정이 내제되어 있음.)  

- 로그인 후, 크롬 네트워크탭에서 확인 가능하다.  
![이미지](/post_assets/2018-03-01/facebook_passport_callback.png)  
  

- 타사인증은 보안적으로 위험한일. 이 과정에서 각각의 서비스가 맞는지 코드내에서 상호간의 검증과정이 일어난다.

#### 구현 중 어려웠던 점.
- 나의 경우 /auth/facebook/callback 이렇게만 썼더니 에러들이 발생했다.

- facebook에서 새 app등록하고, 도메인 http://localhost:3003 로컬로 등록한 뒤 로그인 테스트하면 아래와 같은 에러가 떴다.

1) 
> Insecure Login Blocked: You can't get an access token or log in to this app from an insecure page. Try re-loading the page as https://
> at Strategy.authenticate

2) 
> 차단된 URL: 리디렉션 URI가 앱의 클라이언트 OAuth 설정의 화이트리스트에 없으므로 리디렉션하지 못했습니다. 클라이언트 및 웹 OAuth 로그인이 설정되었는지 확인하고 모든 앱 도메인을 유효한 OAuth 리디렉션 URI로 추가하세요.  


- 구글링해봤더니 facebook자체에서 https만 지원한다고 정책규정이 바껴서 https밖에 못쓴다. 설정바꾸는게 있다는데..도저히 못찾겠어서 포기하고 openSSL에서 Key값을 받아 설정하고 포트를 두개 설정하여 https서버를 제공해주는 방법으로 해결해보았다..ㅠ
(https://localhost:443/auth/facebook/callback 이거 아예 강제맵핑했다.)
	


> ( 참고 : https://stackoverflow.com/questions/49376862/insecure-login-blocked-try-re-loading-the-page-as-https-im-using-oneall-s
All new apps created as of March 2018 have this setting on by default and you should plan to migrate any existing apps to use only HTTPS URLs by March 2019.” Plus, Chrome is planning to mark all non-HTTPS sites as “insecure” soon (Chrome 68, will be released in July 2018), so you might want to switch to HTTPS rather sooner than later.
> )  



## 구현
{% highlight js %}
passport.use(new FacebookStrategy({
  clientID: 'clientID설정!!',
  clientSecret: 'clientSecret설정!!', //절대 노출되면 안되는 정보.
  callbackURL: "https://localhost:443/auth/facebook/callback",  

  //profile로 받을 데이터들을 명시적으로 출력해줘야 한다.
  profileFields:['id', 'email', 'gender', 'link', 'name', 
  'timezone', 'updated_time', 'verified', 'displayName' ]
  //email안던져 준다........
},

//callback받는것
//profile, done이 중요하다.
function(accessToken, refreshToken, profile, done) {
  console.log(profile); //여기서 id는 facebook식별자
  var authId = 'facebook:'+profile.Id;
  for(var i=0; i<users.length; i++){
    var user = users[i];
    if(user.authId === authId){ //facebook으로 로긴했을땐 authid가 있을 것.
      return done(null, user);
    } 
  }
  var newuser = {
    'authId':authId,
    'displayName' :profile.displayName
    /* 'email' : profile.emails[0].value 필드에서 email값을 안뿌려주나보다. Cannot read property '0' of undefined 에러떠서 주석처리함.*/
  }
  users.push(newuser);
  done(null, newuser)
  //이거 다음에 seriallizeUser
  // User.findOrCreate(..., function(err, user) {
  //   if (err) { return done(err); }
  //   done(null, user);
  // });
 }
));
{% endhighlight %}

- deserialize확인
- session은 파일에 저장되고 있음.
- 배열 - 메모리성에 저장되어있는건 사라짐.
- session은 살아있는데, 배열에 있는건 메모리상에 있어서 사라진상태.
- 이 문제를 완화시키기 위해 done('there is no user..')
session에 저장된것 처럼 어딘가 저장을 해놓고 있어야 함.
- 무한히 대기하고 있으면 session을 지워야함.
- 원하는 정보를 scope에 저장해놓고 있어야 함.


- 그러면 아래와 같이 callback data가 던져진다. 
이런식으로
{% highlight js %}
{ id: 'id',
  username: undefined,
  displayName: 'displayName~',
  name: { familyName: 'hi', givenName: 'hi', middleName: undefined },
  gender: 'female',
  profileUrl: 'https://www.facebook.com/app_scoped_user_id/~/',
  provider: 'facebook',
  _json:
   { id: 'id값',
     link: 'https://www.facebook.com/app_scoped_user_id/227712857970634/',
     timezone: 9,
     verified: true,
     name: '~' } }
serializeUser { authId: 'facebook:undefined', displayName: '~' }
{% endhighlight %}


#### think 차이점..
1. session, passport.js 로 face북 로그인 인증 --> 개인스터디
2. cookie로 로그인 구현 --> 실제어플/프로젝트
3. 이메일 확인, 비밀번호 인증.


#### 출처
- [윈도우에서 https에 필요한키 2개 생성-무중력고기블로그](http://zero-gravity.tistory.com/239)
- [이고잉-Federation Authentication(타사 인증)](https://opentutorials.org/course/2136/12144)
- [Passport로 회원가입 및 로그인하기](https://www.zerocho.com/category/NodeJS/post/57b7101ecfbef617003bf457)
- [passportjs.org](http://www.passportjs.org/)
https://github.com/jaredhanson/passport-facebook#readme