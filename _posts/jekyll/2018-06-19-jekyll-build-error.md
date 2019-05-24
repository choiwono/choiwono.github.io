---
layout: post
title:  "jekyll Page build failure:Liquid syntax error 에러 해결"
date : 2018-06-19 12:46:32 +0900
categories: jekyll
tags: jekyll
---

## jekyll Page build failure error message - Liquid Exception: Liquid syntax error
- GitHub Pages is designed to host your personal, organization, or project pages from a GitHub repository.
`Your site is having problems building: Page build failed. For more information, see https://help.github.com/articles/troubleshooting-github-pages-builds/.`

- trouble shooting 가이드를 따라가다가 이해가 안가서, 
- 집에 도착해 빌드에러가 나던 페이지를 jekyll build후 jekyll serve를 돌려보니 아레의 에러를 확인할 수 있다..
> Liquid Exception: **Liquid syntax error (line 19): 'end' is not a valid delimiter for highlight tags. use endhighlight in** C:/Ruby23-x64/bin/blog/_posts/2018-06-11-180611-bitblog/_posts/2018-06-11-180611-bitcoin-cli-1.md
jekyll 3.7.3 | Error:  Liquid syntax error (line 19): 'end' is not a valid delimiter for highlight tags. use endhighlight
PS C:\Ruby23-x64\bin\blog> jekyll serve
Configuration file: C:/Ruby23-x64/bin/blog/_config.yml
            Source: C:/Ruby23-x64/bin/blog
       Destination: C:/Ruby23-x64/bin/blog/_site
 Incremental build: disabled. Enable with --incremental
      Generating...
  Liquid Exception: Liquid syntax error (line 73): 'end' is not a valid delimiter for highlight tags. use endhighlight in C:/Ruby23-x64/bin/blog/_posts/2018-06-11-180611-bitcoin-cli-1.md

- 결국 오타가 문제였다...^^ㅎ
  
  