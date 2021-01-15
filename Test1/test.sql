create table member(
usernum number primary key,
id varchar2(12) not null,
pw varchar2(12) not null, 
name varchar2(15) not null,
nickname varchar2(20) not null,
tell number not null,
address varchar2(50) not null,
joined date default sysdate
)

create table login(
id varchar2(12) not null,
pw varchar2(12) not null
)

create table board(
num NUMBER(4) PRIMARY KEY,
author VARCHAR2(15) NOT NULL,
title VARCHAR2(100) NOT NULL,
content VARCHAR2(2000) NOT NULL,
writeday DATE DEFAULT SYSDATE,
readcnt NUMBER(6) DEFAULT 0,
repRoot NUMBER(4),
repStep NUMBER(4),
repIndent number(2)
)




select *from BOARD
select *from login
select *from member
commit