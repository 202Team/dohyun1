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


select *from member
commit