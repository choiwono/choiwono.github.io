---
layout: post
title: "mac ifconfig - mac 네트워크 확인"
date: 2018-10-30 12:46:32 +0900
categories: Mac
tags: Mac ifconfig
---

## mac 네트워크 확인

- `ifconfig`
- output

```
ifconfig output in Mac OS X?
lo0 = loopback
gif0 = Software Network Interface
stf0 = 6to4 tunnel interface
en0 = Ethernet 0
fw0 = Firewire
en1 = Ethernet 1
vmnet8 = Virtual Interface
vmnet1 = Virtual Interface
```

## output 들의 의미 확인

- `-listallhardwareports` 명령어로 확인가능
- You can see the names that are used in System Preferences for some of the devices by running networksetup -listallhardwareports:

```
Hardware Port: Thunderbolt Ethernet
Device: en4
Ethernet Address: 78:7b:8a:bc:7a:4d

Hardware Port: iPhone USB
Device: en5
Ethernet Address: a2:d7:95:83:6f:e0

Hardware Port: Wi-Fi
Device: en0
Ethernet Address: 1c:36:bb:ed:a6:77

```
