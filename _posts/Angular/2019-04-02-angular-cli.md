---
layout: post
title: ng serve --prod 모드로 실행시 DON'T USE IT FOR PRODUCTION! 경고
date: 2019-04-02 12:46:32 +0900
description:
categories: Angular
tags: Angular angular-cli
---

## ng serve --prod 모드로 실행시 DON'T USE IT FOR PRODUCTION! 경고

- 서비스는 잘 뜨나 아래 워닝이 뜬다.

```
$ ng serve --prod
****************************************************************************************
This is a simple server for use in testing or debugging Angular applications locally.
It hasn't been reviewed for security issues.

DON'T USE IT FOR PRODUCTION!
****************************************************************************************
** Angular Live Development Server is listening on 0.0.0.0:4200, open your browser on http://localhost:4200/ **

Date: 2019-04-02T06:07:30.861Z
Hash: b697710c72d81aedcafd
Time: 76981ms
chunk {0} common.9bd9653f0a46706ca794.js (common) 5.56 kB  [rendered]
chunk {1} 1.b515fa1c5c91a79ac93e.js () 16.5 kB  [rendered]
chunk {2} 2.d8a9a259421a895ff913.js () 41.4 kB  [rendered]
chunk {3} 3.917a6cbee324cf942704.js () 101 kB  [rendered]
chunk {4} 4.c7641687c7c68b37042b.js () 20.1 kB  [rendered]
chunk {5} 5.307af7b5a9d5409a7724.js () 37.4 kB  [rendered]
chunk {6} 6.bf5347baf21748cc6ae1.js () 75.2 kB  [rendered]
chunk {7} 7.e11de255895ecc09981f.js () 37.6 kB  [rendered]
chunk {8} 8.37375c6aaf11f3300b0f.js () 173 kB  [rendered]
chunk {9} runtime.dea5043cd53ea7a03964.js (runtime) 2.05 kB [entry] [rendered]
chunk {10} styles.f3a27ab3aa1427652ef8.css (styles) 120 kB [initial] [rendered]
chunk {11} polyfills.431223cad454e6467d58.js (polyfills) 156 kB [initial] [rendered]
chunk {12} main.ffc57ce5ea13426644dd.js (main) 2.03 MB [initial] [rendered]
chunk {scripts} scripts.8ebcda801fb3521135d8.js (scripts) 2.04 kB  [rendered]
ℹ ｢wdm｣: Compiled successfully.
```

# angular 코드 배포방법

- https://jodu.tistory.com/38
