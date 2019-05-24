---
layout: post
title: pm2 start --env production 실행시 Error, listen EACCES(permission denied 0.0.0.0:80)
date: 2019-04-02 12:46:32 +0900
description:
categories: Angular
tags: Angular angular-cli
---

## 배포서버에서(ubuntu) sudo pm2 start --env production 실행시 Error: listen EACCES: permission denied 0.0.0.0:80

- 로컬피씨에선 테스트 완료 후 실행.
- 권한 문제라고 ..

```
{ Error: listen EACCES: permission denied 0.0.0.0:80
    at Server.setupListenHandle [as _listen2] (net.js:1239:19)
    at listenInCluster (net.js:1304:12)
    at Server.listen (net.js:1392:7)
    at Function.listen (/home/ubuntu/eosTracker/EOSTracker/node_modules/express/lib/applic$
    at Object.<anonymous> (/home/ubuntu/eosTracker/EOSTracker/server.js:11:5)
    at Module._compile (internal/modules/cjs/loader.js:799:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:810:10)
    at Module.load (internal/modules/cjs/loader.js:666:32)
    at tryModuleLoad (internal/modules/cjs/loader.js:606:12)
    at Function.Module._load (internal/modules/cjs/loader.js:598:3)
  code: 'EACCES',
  errno: 'EACCES',
  syscall: 'listen',
  address: '0.0.0.0',
  port: 80 }
```

## 조치한 것

- `sudo iptables -t nat -A PREROUTING -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080`
- `sudo iptables -A PREROUTING -t nat -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080`
- `sudo chown ubuntu:ubuntu /home/ubuntu/.pm2/rpc.sock /home/ubuntu/.pm2/pub.sock`
  --> not working...

## solved

- 결국 `sudo pm2 start server.js` 로 실행해서 올림.
- 소스코드상 server.js를 실행하면 /dist의 경로를 보고 프로젝트를 올린다.
- pm2 ecosystem.config.js는 사용하지 못함~~

## 의문

- ng serve 실행 vs server.js 실행의 차이???
- ng serve로 실행할 경우 default port 4200,
- server.js로 실행할 경우 default port 80~

## reference

https://stackoverflow.com/questions/35105100/start-app-as-root-with-pm2
