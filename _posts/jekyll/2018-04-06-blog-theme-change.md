---
layout: post
title:  "깃허브블로그 jekyll, lanyon theme 적용"
date : 2018-04-06 12:46:32 +0900
categories: jekyll
tags: jekyll
---

## Gitblog, jekyll 사용 장점
- 정적페이지의 사용으로 빠르다.
- db를 처리하는부분이 없기때문에 더 안전하다. 그렇기 때문에 유지보수가 훨씬 수월하다.
- 적은 비용. 깃허브를 통한 프리호스팅 지원.
- markdown 지원.

## 블로그테마 변경
- 포스팅 스타일의 통일성과 git과 친해지기 위해, 코딩습관을 기르기 위해 깃블로그를 시작.
- 사용하다보니, 변경사항이 깃페이지에 등록되는데 시간이 꽤 오래걸렸고, 수정즉시 확인해볼 수 있도록 환경설정을 해야함을 느꼈다.
- 윈도우로 jekyll을 설치하는 방법을 찾아봤고, 동시에 테마도 더 마음에 드는걸로 수정하였다. 

##  추후 진행할 작업
1. <del>댓글기능(disqus shortname셋팅완료)</del>
2. 구글 Analystic 적용해보기
3. mother blog 참고하기!!!! 
4. <del>한글 폰트 수정(_config.scss 에서, $font-serif: 의  serfi란 걸 지움/완료).</del>

## Github에 업로드
{% highlight js %}
    $ git init
    $ git add .
    $ git commit -m "커밋내용"
    $ git push origin master
{% endhighlight %}

## jekyll serve실행
- `$ jekyll serve` => 개발서버가 실행됩니다. http://localhost:4000/ (자동 재생성: 활성화. 비활성화하려면 `--no-watch` 를 사용하세요.)
- `$ jekyll serve --no-watch` => `jekyll serve` 와 동일하지만 변경사항을 감시하지 않습니다.
- `$ jekyll build` => 현재 폴더의 컨텐츠를 가지고 ./_site 에 사이트를 생성합니다.


##  블로그 정리, 카테고리와 태그의 차이점
- 블로그 <mark>카테고리</mark>는 게시물을 제목이나 유형으로 분류할 때 사용되며, 내 블로그에 대한 일반적인 주제나 목차로 이해할 수 있습니다. 예를 들어, 음식에 관한 블로그를 운영한다면, 아침, 점심, 저녁, 음료 등을 카테고리로 선택할 수 있습니다. 패션 블로그를 운영한다면, 여름, 겨울, 가을 등을 카테고리로 선택할 수 있습니다. 단, 독자가 혼란스러울 수 있으니 너무 많은 카테고리를 추가하지 않을 것을 권장합니다.

- <mark>태그</mark>의 목적은 내 게시물의 세부 정보를 설명하는 것이며, 태그는 해시태그와 유사한 것으로 이해할 수 있습니다. 블로그 게시물에 여러 태그를 추가할 수 있습니다. 일반적으로 블로그에 카테고리보다 태그가 많습니다. 
예를 들어, 내 블로그 게시물이 뉴욕에 위치한 일본 스시 전문점에 관한 것이라면, 내 태그는 "음식", "스시", "생선", "음식점", "뉴욕" 등이 될 수 있습니다.
블로그 태그는 태그 클라우드 요소에서 표시할 수 있습니다. 독자는 태그를 클릭해 블로그 게시물을 필터링할 수 있습니다.


## 참고
1. [콩로그.net/Jekyll 블로그에 테마 적용하기](http://my2kong.net/2016/07/07/jekyll-blogging-theme/)
2. [지킬로 깃허브에 무료 블로그 만들기](https://nolboo.kim/blog/2013/10/15/free-blog-with-github-jekyll/)
3. [Jekyll과 Lanyon을 이용한 블로깅 및 테마 편집 방법/***](https://minyoungjung.github.io/%ED%99%98%EA%B2%BD%EC%84%A4%EC%A0%95/%EB%B8%94%EB%A1%9C%EA%B7%B8/2017/05/31/lanyon-theme-customize/)
4. [Jekyll 윈도우에 설치해서 사용하기](http://tech.whatap.io/2015/09/11/install-jekyll-on-windows/)
5. [Jekyll로 GitHub에 blog 만들기](https://jamiekang.github.io/2017/04/28/blogging-on-github-with-jekyll/)
6. [Jekyll을 사용하여 GitHub Pages 만들기](http://blog.saltfactory.net/upgrade-github-pages-dependency-versions/)
7. [초보자를 위한 Jekyll Blog 시작하기](http://www.halryang.net/Jekyll-Blogging-For-Beginners/)
8. [jekyll-docs](http://jekyllrb-ko.github.io/docs/frontmatter/)
9. [디스커스 댓글 기능적용](https://blog.naver.com/skykbc/221124877511)