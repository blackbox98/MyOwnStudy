use happyhouse;

select * from member;
desc member;

-- 관심지역 테이블 생성
create table interest (
	no int,
    id varchar(30),
    dongCode varchar(10),
    primary key(no, id),
    foreign key(id) references member(id) on delete cascade
);

drop table interest;
-- alter table interest add foreign key(id) references member(id);

select * from member;
select * from interest;

insert into member values('wjsqudcks', '12341234', 'zxcvb@naver.com', '전병찬', 25);
insert into member values('abcde', '789456', 'zxcvb@naver.com', '홍길동', 20);

insert into interest values('1', 'wjsqudcks', '101010101');
insert into interest values('2', 'wjsqudcks', '101011111');

delete from interest where id = 'wjsqudcks';

-- 관심지역의 상가(상권) 테이블 생성
create table businessArea (
	brandName varchar(50),
    category varchar(30),
    dongCode varchar(20),
    dongName varchar(20),
    addr varchar(50),
    lng varchar(30),
    lat varchar(30),
    primary key(brandName, dongCode)
);

drop table businessArea;

select * from businessArea;

load data infile 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\businessAreaInfo_20211231_02.txt'
ignore into table businessArea character set utf8mb4
fields terminated by '\t'
lines terminated by '\r\n'
ignore 1 rows;

show create database happyhouse;

select * from housedeal;

select * from baseaddress;

select * from member;

select * from interest order by id;

delete from interest;
insert into interest values(1, 'abcde', '1111010100');
insert into interest values(2, 'abcde', '1111010200');
insert into interest values(3, 'wjsqudcks', '1111010300');
desc baseaddress;
desc interest;

select *
from baseaddress b
where b.dongCode = (
					select i.dongCode
					from interest i
                    where i.dongCode = '1111010100'
                    and i.id = 'abcde');

show create table happyhouse;