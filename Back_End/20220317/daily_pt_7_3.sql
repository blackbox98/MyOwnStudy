use world;

select * from city;
select * from country;
select * from countrylanguage;

-- 1. 도시명 kabul이 속한 국가의 이름은?
select Code, name
from country
where code = (select countrycode from city where name = 'kabul');

-- 2. 국가의 공식 언어 사용율이 100%인 국가의 정보를 출력하시오. 국가 이름으로 오름차순 정렬한다.(8건)
select ctr.name, language, percentage
from countrylanguage cl join country ctr
on cl.countrycode = ctr.code
where cl.isofficial = 'T'
and cl.percentage = 100
order by ctr.name asc;

-- 3. 도시명 amsterdam에서 사용되는 주요 언어와 amsterdam이 속한 국가는?
select ct.name, cl.language, ctr.name
from countrylanguage cl
join city ct
on cl.countrycode = ct.countrycode
join country ctr
on cl.countrycode = ctr.code
where cl.isofficial = 'T'
and ct.name = 'amsterdam';

-- 4. 국가명이 united로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 단, 수도 정보가 없다면 출력하지 않는다.(3건)
select ctr.name, ctr.capital, ct.name 수도, ct.population 수도인구
from country ctr join city ct
on ctr.capital = ct.id
where ctr.name like 'united%';

-- 5. 국가명이 united로 시작하는 국가의 정보와 수도의 이름, 인구를 함께 출력하시오. 단, 수도 정보가 없다면 수도없음이라고 출력한다.(4건)
select ctr.name, ctr.capital, if(ctr.capital, ct.name, '수도없음') 수도, if(ctr.capital, ct.population, '수도없음') 수도인구
from country ctr left outer join city ct
on ctr.capital = ct.id
where ctr.name like 'united%';

-- 6. 국가 코드 che의 공식 언어 중 가장 사용률이 높은 언어보다 사용률이 높은 공식언어를 사용하는 국가는 몇 곳인가?
select (
		select count(*)
        from countrylanguage cl1
        where cl1.isofficial = 'T'
        and cl1.percentage > max(cl2.percentage)
        ) 국가수
from countrylanguage cl2
where cl2.countrycode = 'che'
and cl2.isofficial = 'T'
;

-- 7. 국가명 south korea의 공식 언어는?
select cl.language
from countrylanguage cl
join country ctr
on ctr.code = cl.countrycode
where ctr.name = 'south korea'
and cl.isofficial = 'T'
;

-- 8. 국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오.(3건)
select distinct ctr.name, ctr.code, (
							select count(*)
                            from city ct1
                            where ctr.code = ct1.countrycode
                            ) 도시개수
from country ctr join city ct2
on ctr.code = ct2.countrycode
where ctr.name like 'bo%';

-- 9. 국가 이름이 bo로 시작하는 국가에 속한 도시의 개수를 출력하시오. 도시가 없을 경우는 단독이라고 표시한다.(4건)
select distinct ctr.name, ctr.code, if((
										select count(*)
										from city ct1
										where ctr.code = ct1.countrycode
										) = 0, '단독', (
														select count(*)
														from city ct1
														where ctr.code = ct1.countrycode
														)
										) 도시개수
from country ctr left outer join city ct2
on ctr.code = ct2.countrycode
where ctr.name like 'bo%';

-- 10. 인구가 가장 많은 도시는 어디인가?
select countrycode, name, population
from city
where population = (
					select max(population)
                    from city
                    );

-- 11. 가장 인구가 적은 도시의 이름, 인구수, 국가를 출력하시오.
select (
		select ctr.name
		from country ctr
        where ctr.capital = ct.id) name
        , ct.countrycode code, ct.name, ct.population
from city ct
where population = (
					select min(population)
                    from city
                    );

-- 12. KOR의 seoul보다 인구가 많은 도시들을 출력하시오.
select countrycode, name, population
from city
where population > (
					select ct2.population
					from city ct2 join country ctr
					on ct2.countrycode = ctr.code
					where ctr.code = 'KOR'
					and ct2.name = 'seoul'
                    );

-- 13. San Miguel 이라는 도시에 사는 사람들이 사용하는 공식 언어는?
select cl.countrycode, cl.language
from countrylanguage cl join city ct
on cl.countrycode = ct.countrycode
and ct.name = 'San Miguel'
where cl.isofficial = 'T';

-- 14. 국가 코드와 해당 국가의 최대 인구수를 출력하시오. 국가 코드로 정렬한다.(232건)
select ctr.code countrycode, (
								select max(ct.population)
                                from city ct
                                where ctr.code = ct.countrycode
                                ) max_pop
from country ctr
where ctr.population != 0
order by code;

-- 15. 국가별로 가장 인구가 많은 도시의 정보를 출력하시오. 국가 코드로 정렬한다.(232건)
select ct1.countrycode, ct1.name, ct1.population
from city ct1 join country ctr
on ct1.countrycode = ctr.code
where ct1.population = (
						select max(ct2.population)
						from city ct2
						where ct1.countrycode = ct2.countrycode
						) order by ct1.countrycode;

-- 16. 국가 이름과 함께 국가별로 가장 인구가 많은 도시의 정보를 출력하시오.(239건)
select ctr.code, ctr.name, ct1.name, (
										select max(ct2.population)
										from city ct2
										where ct1.countrycode = ct2.countrycode
										) population
from country ctr left outer join city ct1
on ct1.id = ctr.capital
order by ctr.code;

-- 17. 위 쿼리의 내용이 자주 사용된다. 재사용을 위해 위 쿼리의 내용을 summary라는 이름의 view로 생성하시오.
create view summary as
select ctr.code, ctr.name co_name, ct1.name ci_name, (
										select max(ct2.population)
										from city ct2
										where ct1.countrycode = ct2.countrycode
										) population
from country ctr left outer join city ct1
on ct1.id = ctr.capital
order by ctr.code;

select * from summary;

select * from city;
select * from country;
select * from countrylanguage;

-- 18. summary에서 KOR의 대표도시를 조회하시오.
select *
from summary
where code = 'KOR';