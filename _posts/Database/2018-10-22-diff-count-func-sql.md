---
layout: post
title:  "181022_diff_between_count(*)_count(column_name)"
categories: Database
tags: count db SQL
---

## count(*) and count(column_name) 사용의 차이 
#### 상황
- 카우치디비로 데이터를 가져오는데, count(field_name)으로 가져오는 쿼리의 처리속도가 몇초가량이 나왔다..
- 확인해보니 조건을 준 필드네임에 대해 인덱스가 걸린 것도 아니었고, count(*)로 바꾼 결과 처리속도가 몇ms로 월등이 향상되었다.

#### 그래서, 차이가 났던 이유는?
- 스택오버플로우에 많은 설명들이 있어서 가져와보았다.
- `COUNT(*)` counts all rows in the result set (or group if using GROUP BY).
-  count(*) --> row를 센다.
- `COUNT(column_name)` only counts those rows where column_name is NOT NULL. **This may be slower in some situations** even if there are no NULL values because the value has to be checked (unless the column is not nullable).
- `count(*) counts NULLs and count(column) does not`
- `COUNT(1)` is the same as COUNT(*) since 1 can never be NULL.
- 단, **인덱스가 걸린 필드**에 대해선 count(field)가 더 빠르고, 특히 해당 필드가 수치인 경우에 더 빠르다.

- 예시
```
create table #bla(id int,id2 int)
insert #bla values(null,null)
insert #bla values(1,null)
insert #bla values(null,1)
insert #bla values(1,null)
insert #bla values(null,1)
insert #bla values(1,null)
insert #bla values(null,null)

select count(*),count(id),count(id2)
from #bla

```
- 결과
`results 7 3 2`

#### 카우치디비에서의 경우...
- 아무리 그래도, 한 버킷에서 로우가 몇백개 밖에 안되는데,,, 10몇초와 몇ms의 차이는 너무 심한 것 같아 구글링을 해보았다.
- 내가 사용한 couchbase version : Community Edition 5.0.1 build 5003.
- https://forums.couchbase.com/t/n1ql-count-slow-with-where/8921/5
- 질문자의 카우치 버전과 내가 사용하는 버전차이가 있지만, count(field_name)로 했을때 느리다는 상황은 비슷한것 같다..
- 그래서 카우치베이스쪽에서 어떻게 쿼리를 쓰고, 인덱스를 어떻게 주라는 답변이 써져 있는데,, 아무튼 이런 이슈가 있었고, 나는 count(*)를 써서 해결하였다.

#### 결론
- 특정 컬럼에 대해 인덱스를 줬다면 count(column_name)을 쓰고, 
- row를 전부 카운트해도 되는 조건이라면 그렇게 사용하기. 
- 그리고, 쿼리를 작성하면 항상 처리속도를 따져보자!!!

## table 의 row수를 카운트하는법 (DBMS)
> 실시간으로 데이터를 가져와야하는 애플리케이션에서 select count(*) from tables와 같은 쿼리를 하면 데이터 건수가 적을 때에는 문제가 없지만 많아지면 많아질수록 성능상 문제가 커집니다. 그래서 일반적으로 전체 데이터 건수를 저장하기 위한 별도 테이블을 두어서 해결합니다.  예를 들어 게시판 애플리케이션에서는 게시판 목록을 가져오기 위해 게시판 건수 테이블을 만들어서 게시물을 insert할 때마다 +1을 하고, delete할 때 -1을 하는 식입니다. 그리고, 게시물 insert 쿼리와 건수 update 쿼리를 하나의 트랜잭션으로 묶어서 데이터 무결성을 보장해야 하구요.


#### 출처
- https://stackoverflow.com/questions/59294/in-sql-whats-the-difference-between-countcolumn-and-count
- https://stackoverflow.com/questions/2876909/count-and-countcolumn-name-whats-the-diff
- (table 의 row수를 카운트하는법)[https://hashcode.co.kr/questions/1828/table-%EC%9D%98-row%EC%88%98%EB%A5%BC-%EC%B9%B4%EC%9A%B4%ED%8A%B8%ED%95%98%EB%8A%94%EB%B2%95]


