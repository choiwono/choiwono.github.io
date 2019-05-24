---
layout: post
title: "[EOS #1] eos 설치 및 testnet 구성"
date: 2018-12-01 12:46:32 +0900
description:
categories: Blockchain
tags: [Blockchain, EOS]
---

# Version

- ubuntu 16.04
- EOS v1.4.4
- EOSIO.CDT v1.4.1
- 2018-12-03

# EOS 설치

- Dependency 설치

```
# Install the development toolkit
$ sudo apt-get update
$ wget -O - https://apt.llvm.org/llvm-snapshot.gpg.key|sudo apt-key add -
$ sudo apt-get install clang-4.0 lldb-4.0 libclang-4.0-dev cmake make libbz2-dev libssl-dev libgmp3-dev autotools-dev build-essential libbz2-dev libicu-dev python-dev autoconf libtool git mongodb

# Install Boost 1.67
cd ~/
wget -c 'https://sourceforge.net/projects/boost/files/boost/1.67.0/boost_1_67_0.tar.bz2/download' -O boost_1.67.0.tar.bz2
tar xjf boost_1.67.0.tar.bz2
cd boost_1_67_0/
echo "export BOOST_ROOT=$HOME/boost_1_67_0" >> ~/.bash_profile
source ~/.bash_profile
./bootstrap.sh "--prefix=$BOOST_ROOT"
./b2 install
source ~/.bash_profile

# Install mongo-cxx-driver (release/v3.2)
cd ~
sudo apt-get install curl
curl -LO https://github.com/mongodb/mongo-c-driver/releases/download/1.9.3/mongo-c-driver-1.9.3.tar.gz
tar xf mongo-c-driver-1.9.3.tar.gz
cd mongo-c-driver-1.9.3
./configure --enable-static --enable-ssl=openssl --disable-automatic-init-and-cleanup --prefix=/usr/local
make -j$( nproc )
sudo make install
git clone --depth 1 -b releases/v3.2 https://github.com/mongodb/mongo-cxx-driver
cd mongo-cxx-driver/build
cmake -DBUILD_SHARED_LIBS=OFF -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX=/usr/local ..
sudo make -j$( nproc )
```

- Build EOS

```
# Build EOS
cd ~
git clone https://github.com/EOSIO/eos --recursive
cd eos
git checkout v1.4.4
git submodule update --init --recursive
./eosio_build.sh
```

# EOS TEST

- EOSIO Build Test

```
cd ~/eos/build
make test
```

- Fail이 난 테스트가 있을 시 ctest --output-on-failure 로 에러로그를 볼 수 있다.
- ctest --output-on-failure -R \<regex\> 명령어로 특정 테스트만 진행할 수 있다.
- test 완료시

```
cd ~/eos/build/
sudo make install
```

# EOSIO.CDT

- 설치

```
git clone --recursive https://github.com/eosio/eosio.cdt
cd eosio.cdt/
git checkout v1.4.1
git submodule update --recursive
./build.sh
sudo ./install.sh
```

# EOS Junglenet

- https://github.com/CryptoLions/EOS-Jungle-Testnet 에서 Jungle2.0 Testnet의 정보를 얻을 수 있다.
- http://monitor.jungletestnet.io/#register 에 접속하여 Register your EOS Node 1/2 에 정보를 기입하면 Register your EOS Node 2/2 의 wget으로 설치 스크립트를 다운받을 수 있다.

```
cd ~
mkdir jungle
cd jungle/
wget https://api.monitor.jungletestnet.io/launchers/installJungle-[Producer Account].sh && \
chmod u+x installJungle-[Producer Account].sh
sudo ./installJungle-[Producer Account].sh
```

- ~/jungle/JungleTestnet-[Producer Account]/ 디렉토리의 start.sh와 stop.sh로 nodeos 실행, 종료할 수 있다.
- cleos.sh로 cleos기능을 수행할 수 있다.
- Register your EOS Node 1/2의 HTTP server address를 0.0.0.0:8888 이 아닌 로컬 ip로 작성하였을 경우 cleos.sh의 ip를 변경해야 한다.
