# ORACLE



## dbms_oracle = 관계형 데이터베이스

XE버전 설치

오라클 아이디: fox_93@naver.com // 새우

## 나 오라클 할 줄 알아의 조건

- SETUP - 설치

- CRUD - 원자
  - CREATE
  - READ
  - UPDATE
  - DELETE
- GROUP - 원자들을 묶는 것
- RUN - 실행



## 사용자와 스키마

> 오라클 최종 목표: 표에다 정보를 기록하고 기록된 정보를 읽는 것

#### 스키마

표가 많아지면 서로 연관된 표를 그루핑 하기 위한 체계가 필요함 그런 체계를 스키마라고 한다(일종의 디렉토리).

개념적으로는 어떤 표가 있고 어떤 데이터가 들어가는지 등 정의하는 것



#### 사용자

여러 컴퓨터들이 오라클 데이터베이스가 설치된 컴퓨터에 네트워크로 접속하여 작업

- 오라클은 여러 사용자를 만들 수 있고

- 각각의 사용자는 자신이 관리하는 테이블에 접속할 수 있다.
- 사용자를 생성하면 사용자에 속하는 스키마가 만들어지게 된다.



**사용자 생성시 사용자에 해당하는 스키마 생성됨.**



## 사용자 생성

1. 명령프롬프트에 들어가서 sqlplus를 실행한다
2. 사용자명에 `sys as sysdba` (system database adiministrator)입력
3. 비밀번호 입력 할 필요 없이 enter ㄱㄱ(비번 아무렇게나 쳐도 똑같음 이유는 모름)

```
Microsoft Windows [Version 10.0.18363.476]
(c) 2019 Microsoft Corporation. All rights reserved.

C:\Users\Administrator>sqlplus

SQL*Plus: Release 18.0.0.0.0 - Production on 월 4월 26 01:56:51 2021
Version 18.4.0.0.0

Copyright (c) 1982, 2018, Oracle.  All rights reserved.

사용자명 입력: sys as sysdba
비밀번호 입력:

다음에 접속됨:
Oracle Database 18c Express Edition Release 18.0.0.0.0 - Production
Version 18.4.0.0.0

SQL>
```



### 사용자 생성 방법

오라클 공식 메뉴얼 (영어 익숙시, 강사에게서 독립, 네모는 그대로 동그라미는 수정)

https://docs.oracle.com/cd/B19306_01/server.102/b14200/statements_8003.htm



`CREATE USER '유저명' IDENTIFIED BY '비밀번호'`



오류나는데 검색하면 바로 나옴



### 사용자 권한 부여

1. SYS AS SYSDBA로 SQLPLUS 접속

2. `GRANT DBA TO '권한을 받을 아이디'`
   - GRANT = 권한부여
   - DBA = 권한내용(DBA는 DATA BASE ADMINISTRATOR)



### 테이블

> 실제 데이터가 저장되는 테이블을 생성해봅시다.
>
> https://docs.oracle.com/cd/B28359_01/server.111/b28286/statements_7002.htm

#HEDGEHOG

| ID   | TITLE  | DESCRIPTION | CREATED  |
| ---- | ------ | ----------- | -------- |
| 1    | 고순이 | 소심함      | 21/04/26 |
| 2    | 두치   |             | 21/04/26 |



```sql
CREATE TABLE hedgehod(
	id NUMBER NOT NULL,
	title VARCHAR2(50) NOT NULL,--50보다 긴 글자가 들어오면 짤림, NULL 불가
	description VARCHAR2(4000) NULL, -- NULL허용(NULL안 써도 된대)
	created DATE NOT NULL
);
```

```sql
CREATE TABLE hedgehod(
	id NUMBER NOT NULL,
	title VARCHAR2(50) NOT NULL,
	description VARCHAR2(4000) NULL,
	created DATE NOT NULL
);
```





> 오늘은 2021-04-28 입니다

### 테이블에 행 추가

```sql
SQL> insert into hedgehog
  2  (code, lastmating)
  3  values
  4  ('hm000','21/04/26');

1 row created.

SQL> commit; --commit까지 해줘야 반영됨
```



### 행 읽기 및 정렬

```sql
SQL> select * from hedgehog; -- hedgehog의 모든 행 가져옴
SQL> select * from hedgehog where code='hf000'; -- hedgehog에서 code가 hf000인 것
SQL> select code from hedgehog; -- hedgehog의 모든 행의 'code' 가져옴
```



```sql
SELECT id, title, created FROM topic;
 
SELECT * FROM topic WHERE id = 1;
 
SELECT * FROM topic WHERE id > 1;
 
SELECT id, title, created FROM topic WHERE id = 1;
```



```sql
SELECT * FROM topic ORDER BY id DESC; --id를 기준으로 정렬함 desc=오름차순
 
SELECT * FROM topic 
    OFFSET 1 ROWS --인덱스 1번(0번시작)이후것 가져옴
    FETCH NEXT 2 ROWS ONLY;--2개 가져옴
```



### 행 수정 및 삭제

```sql
update hedgehog --hedgehog 테이블을 업데이트 합니다
	set 
		code = '', --code는 ''로 바꿉니다
		lastdelivery='' --lastdelivery는 ''로 바꿉니다
	where
		lastmating ='' --lastmating이 ''인 자료만 바꿉니다
```



```sql
SQL> delete from hedgehog where code ='fault000'; 
								--hegehog 테이블 중 code가 'fault000'인 row 삭제
```





### 테이블 삭제, PRIMARY KEY 설정, SEQUENCE



```sql

DROP TABLE hedgehog;

CREATE TABLE hedgehog(
	id NUMBER NOT NULL,
	sex CHAR(1) NOT NULL,
	lastmating DATE,
	lastdelivery DATE,
	CONSTRAINT PK_HEDGE PRIMARY KEY(id) -PK_HEDGE = PRIMARY KEY 이름
										-PRIMARY KEY(id) id를 primary key로
);

CREATE SEQUENCE SEQ_HEDGE; --시퀀스제작

INSERT INTO hedgehog
	(id, sex, lastmating) VALUES (SEQ_HEDGE.NEXTVAL,'M','21/04/26');

INSERT INTO hedgehog
	(id, sex, lastmating) VALUES (SEQ_HEDGE.NEXTVAL,'M','21/04/26');

INSERT INTO hedgehog
	(id, sex, lastmating) VALUES (SEQ_HEDGE.NEXTVAL,'F','21/04/25');

INSERT INTO hedgehog
	(id, sex, lastmating) VALUES (SEQ_HEDGE.NEXTVAL,'F','21/04/26');
```

