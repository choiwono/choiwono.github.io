---
layout: post
title:  "Node AWS S3 스토리지 이용하여 파일업로드 구현"
date : 2018-03-13 12:46:32 +0900
description: 
categories: Nodejs
tags: Nodejs AWS-S3
---

# Amazon S3란??
- **Amazon S3 (Simple Storage Service)** : AWS S3 (Simple Stoage Service)는 **파일을 저장하기 위한 스토리지**이다. 일반적인 파일시스템의 개념과는 약간 다르고, 파일 이름을 대표하는 key와 파일 자체로 구분되는 Object Storage이다.
- 용량 저장할 수 있는 파일의 크기는 개당 1byte~5TB이고, 총 저장 용량에는 제한이 없다. 디렉토리와 비슷한 개념으로, **bucket**이라는 개념을 가지고 있다.
- S3에는 파일을 업로드 할때, **multipart uploading**이라는 기능을 제공한다. 이 multipart 업로드 기능을 구현은 SDK 형태로 재공되는 라이브러리를 사용하면 된다. 사용법이 매우 간단한 서비스이기는 하지만, 쉽고 안전하며, 데이터 저장소로써 필수적인 기능등은 거의 다 갖추고 있으며, 저렴한 가격에, 다른 서비스와 연계성이 높다.
- 단, S3를 이용한 시스템 설계시에는 일반 파일 시스템과 같은 형태로 접근을 하면 안된다. HTTP 형식으로 접근을 하기 때문에, 성능이 그만큼 나오지 않는다. 이 부분에 대한 고려만 충분히 하면 AWS에서 EC2 다음으로 가장 가치가 높은 서비스가 아닐까 싶다. [출처:조대협의 블로그](http://bcho.tistory.com/778)
- S3에서는 파일을 업로드 할시 PUT이라 함.


# 구현

## 작동원리
fileupload시, aws에서 사용가능한 s3스토리지에서 저장을 하게 됩니다. 
그리고 버킷에 저장하는 multipaty 모듈의 기능을 이용하여 스토리지에 버킷에 저장하게 됩니다.

## 필요한 것
- Credential 자격증명 (aws에서 인증을 받아야 AWS 인프라를 사용할 수 있다.) 

## 필수패키지 install
- formidable 파일 업로드를 위한 모듈
- async순차 실행을 위한 모듈
- aws-sdk S3 업로드를 위한 모듈, 해당 모듈은 AWS 서비스를 사용하기 위한 javascript 객체를 제공
- multiparty : 파일업로드를 위한 npm 모듈

{% highlight js %}
npm install formidable --save
npm install async --save
npm install aws-sdk --save
npm install multiparty --save
{% endhighlight %}


## S3의 스토리지 인프라에 접근하기 위한 AWS 인증 Credential 파일 설정
- 어플리케이션구동시 해당 credential 파일을 읽도록 설정한다.
`AWS.config.loadFromPath();`


## HTML Form 
{% highlight HTML %}
<div class="form-group">
    <label for="contents">표지이미지</label>
    <input  type="file" id="img_files" name="img_files" accept="image/*" class="form-control" onchange="__common.cmdSendByAjax(this.id, 'frm');">
    <input type="hidden" id="Key" name="Key" />
    <input type="hidden" id="Location" name="Location" />
</div>
{% endhighlight %}


## action을 수행하는 script파일.
{% highlight js %}
__common = {
    cmdSendByAjax : function (_obj, _form) {
    $('form').ajaxForm({
        url: '/util/FileUpload',
        enctype: 'multipart/form-data',

        beforeSubmit: function (data, form, option) {
            console.info('beforeSubmit');
            console.info(data);
            console.info(form);
            console.info('beforeSubmit');
            //validation체크
            //막기위해서는 return false를 잡아주면됨
            return true;
        },
        success: function (response, status) {
            console.info(response);
            $('#Location').val(response.Location);
        },
        error: function () {
            console.info('error');
        }
    });
    $('#' + _form).submit();
}
}
{% endhighlight %}


## router의 util.js
- aws기본 설정
{% highlight js %}
AWS.config.loadFromPath('./config/config.json');
AWS.config.region = 'ap-northeast-2'; //지역 설정
{% endhighlight %}

- AWS fileUpload 설정!!
{% highlight js %}
router.all('/FileUpload', function(req, res, next) {
var form = new multiparty.Form();

form.on('field',function(name,value){
    console.log('normal field / name = '+name+' , value = '+value);
});

//S3 버킷 설정
form.on('file',function(name, file){
    var param = {
        'Bucket':'BucketName', //s3에 설정한 버킷 이름 설정.
        'Key':file.originalFilename,
        'ACL':'public-read',
        'Body':null
    };

    var orgFileName = file.originalFilename;
    var orgFileExtension = orgFileName.lastIndexOf(".");
    
    //원본 파일명을 바꾸기 위한 코드.
    //파일명의 중복을 막기 위해 설정
    function makeid() {
        var text = "";
        var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        
        for (var i = 0; i < 10; i++)
        text += possible.charAt(Math.floor(Math.random() * possible.length));
        
        return text;
    }

    param.Key = makeid() + orgFileExtension;

    //param.Key = file.originalFilename;
    param.Body = require('fs').createReadStream(file.path);
    
    //upload
    s3.upload(param, function(err, data){
        console.log(err);
        console.log(data);

        res.status(200).send(data);
    });
    
});

form.parse(req);
});
{% endhighlight %}


 `var param = {...}` 객체는 AWS S3업로드에 대한 정보
- Bucket : S3 Bucket 이름을 지정합니다.
- Key : S3의 경로 및 파일 이름을 지정합니다.
- ACL : 파일 권한에 대한 설정입니다.
- Body : 업로드할 파일의 경로를 지정합니다.



#### 출처
- [Node AWS S3 업로드/Yun Blog](https://cheese10yun.github.io/Node-AWS-S3-Upload/)