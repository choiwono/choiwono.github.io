---
layout: post
title:  "Node.js firebase를 사용하여 fcm처리"
date : 2018-03-13 12:46:32 +0900
description: 
categories: Nodejs
tags: Nodejs fcm
---

## fcm이란? fcm으로 push알람 구현  
- 관리자단에서 푸시알람 구현하기 위해 firebase를 사용하여 처리하기로 했다.
(firebase Cloud Messaging)으로 푸시기능 구현

- FCM은 Firebase Cloud Messaging으로 메시지와 알림을 무료로 안정적으로 전송할 수 있는 교차 플랫폼 메시징 솔루션이다. 어플리케이션의 알림기능을 구현하기 위해선, 현재의 서버에서 클라이언트로 알림을 전달할 방법이 있어야 한다. <mark>하지만, 본연의 서버의 기능을 수행하면서 또 하나의 복잡한 알림 기능까지 포함하면 서버의 속도는 처리량이 많아 느려질 것</mark>이다.
따라서 이러한 해결책으로 <mark>알림의 기능은 다른 서버가 제공해주고 본 서버는 알림 기능을 제공하는 서버에 알림이 있는지 요청을 해서 정보를 가져오는 구조</mark>이다. 알림을 제공해주는 곳이 바로 **구글**이 된다. 구글 [firebase홈페이지](https://firebase.google.com/docs/cloud-messaging/?hl=ko)에서 FCM기능을 더 자세히 확인해볼 수 있다.

- 작동원리
![이미지](/post_assets/2018-03-01/firebase_fcm_work.jpg)

- FCM 구현에는 송수신을 위한 두 가지 주요 구성요소가 포함된다.
> 1. Firebase용 Cloud 함수 또는 앱 서버와 같이 메시지를 작성, 타겟팅, 전송할 수 있는 신뢰할 수 있는 환경.
> 2. 메시지를 수신하는 iOS, Android 또는 웹(자바스크립트) 클라이언트 앱.  
Admin SDK 또는 HTTP 및 XMPP API를 통해 메시지를 보낼 수 있습니다. 강력한 타겟팅 및 분석 기능이 기본적으로 포함된 알림 작성기를 사용하여 마케팅 또는 참여 유도 메시지를 테스트 또는 전송할 수도 있습니다.

#### 내가 해야할 관리자단 작업
- fcm-node 모듈을 통한, fcm서버로 알림전송 부분 소스 개발.
- fcm접속을 위한 서버키 확보.
- 사용자테이블에 device token값만 받으면 되는 상황.

#### 작업절차
1. firebase에 프로젝트를 설정한다. 
2. 서버key값을 받고 프로젝트에 설정한다.
3. device token값을 받는다.
4. 코드에 message를 설정한다(notification).
5. 전송 test 및 확인.

#### 필수 패키지 설치
`var FCM = require('fcm-node');`


#### 소스
**1. push를 처리하는 router**
{% highlight js %}
/**
* 푸시 발송 by pushBoardId
* @route {POST} /pushMng/pushSend
*/
router.all('/pushSend', function(req, res){
var __title = '';
var __contents = '';

pushMngDb.selectPushMngListOne(req.body, function (err, rows) {
    __title = rows[0].title;
    __contents = rows[0].contents;
    console.log('selectPushMngListOne');

    memberMngDb.selectMemberPushYn(function(err, rows){
        console.log('selectMemberPushYn');
            
        for(var i =0; i < rows.length ; i++){
            if(rows[i].pushsendYn == 'Y'){
                var _temp = new Object();
                _temp.deviceToken = rows[i].deviceToken;
                _temp.title = __title;
                _temp.contents = __contents;
                _fcm.Send(_temp);
            }
        }
    });
});
    res.send();
});
{% endhighlight %}

**2. util에서 fcm처리**
{% highlight js %}
var FCM = require('fcm-node');
var serverKey = require('ket값을 가져오는 경로');

var fcm = new FCM(serverKey);

module.exports = new function () {
    this.Send = function (query, callback) {
    /** Firebase(구글 개발자 사이트)에서 발급받은 서버키 */
    // 가급적 이 값은 별도의 설정파일로 분리하는 것이 좋다.
    
    /** var client_token = 안드로이드 단말에서 추출한 token값 */

    /** 푸시메시지 발송절차 */
    //var fcm = new FCM(serverKey);
    console.log('===========================');
    console.log(query);
    var client_token = query.deviceToken; 
    var title = query.title;
    var body = query.contents;

    //발송 후 시간 리턴(db에 timestamp 처리하기)

    /** 발송할 Push 메시지 내용 */
    var push_data = {

    // 수신대상
    to: client_token,
    // App이 실행중이지 않을 때 상태바 알림으로 등록할 내용
    notification: {
        title: title,
        body: body,
        sound: "default",
        click_action: "FCM_PLUGIN_ACTIVITY",
        icon: "fcm_push_icon"
    }
    };

    fcm.send(push_data, function(err, response) {
    if (err) {
        console.error('Push메시지 발송에 실패했습니다.');
        console.error(err);
        return;
    }

    console.log('Push메시지가 발송되었습니다.');
    console.log(response);
    console.log(response.results);
    });

    }
}
{% endhighlight %}}

#### 출처
- [fcm으로 푸시 서비스구현-꿈꾸는 개발자블로그](https://m.blog.naver.com/PostView.nhn?blogId=scw0531&logNo=220844031273&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F)