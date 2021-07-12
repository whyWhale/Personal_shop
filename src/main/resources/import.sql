-- insert into member (city, category_id,street, category_id,zipcode, category_id,name) values ('서울턱별시 강남구', category_id,'테헤란로', category_id,'12-1', category_id,'참깨');
--
-- insert into member (city, category_id,street, category_id,zipcode, category_id,name) values ('인천 남덩구', category_id,'남덩로', category_id,'1032-12', category_id,'꾸꾸까까');
--
-- insert into member (city, category_id,street, category_id,zipcode, category_id,name) values ('부싼 해운대구', category_id,'해운로', category_id,'554-33', category_id,'뭐라카노');
--
--
-- insert into item (name, category_id,price, category_id,stock_quantity, category_id,author, category_id,isbn, category_id,dtype) values ('알고리즘 문제 해결 전략1', category_id,33000, category_id,15, category_id,'구종만', category_id,'123-123', category_id,'B');
--
-- insert into item (name, category_id,price, category_id,stock_quantity, category_id,artist, category_id,etc, category_id,dtype) values ('IU 미니 앨범', category_id,28000, category_id,10, category_id,'IU', category_id,'작곡가,작사가: WhyWhale', category_id,'A');
--
-- insert into item (name, category_id,price, category_id,stock_quantity, category_id,actor, category_id,director, category_id,dtype) values ('트랜스포머<달의 어둠>', category_id,10000, category_id,30, category_id,'옵티머스프라임,범블비', category_id,'마이클 베이', category_id,'M');
--
-- insert into item (name, category_id,price, category_id,stock_quantity, category_id,artist, category_id,etc, category_id,dtype) values ('Rollin', category_id,27000, category_id,10, category_id,'브레이브걸스', category_id,'작곡가,작사가: 용감한 형제', category_id,'A');
--
-- insert into item (name, category_id,price, category_id,stock_quantity, category_id,author, category_id,isbn, category_id,dtype) values ('알고리즘 문제 해결 전략2', category_id,33000, category_id,15, category_id,'구종만', category_id,'123-1234', category_id,'B');
--
-- insert into item (name, category_id,price, category_id,stock_quantity, category_id,actor, category_id,director, category_id,dtype) values ('트랜스포머<사라진 시대>', category_id,10000, category_id,30, category_id,'옵티머스프라임,범블비', category_id,'마이클 베이', category_id,'M');

-- ================ Root 카테고리
insert into category(name,category_id) values('all',-1);
insert into category(name, category_id,parent_id) values ('패션', 0,-1l);
insert into category(name, category_id,parent_id) values ('가전/디지털', 1,-1l);
insert into category(name, category_id,parent_id) values ('도서', 2,-1l);
insert into category(name, category_id,parent_id) values ('식품', 3,-1l);


-- =============== Level 2 카테고리
-- 패션
insert into category(name, category_id,parent_id) values ('여성', 4,0l);
insert into category(name, category_id,parent_id) values ('남성', 5,0l);
insert into category(name, category_id,parent_id) values ('아동', 6,0l);;
insert into category(name, category_id,parent_id) values ('스포츠', 7,0l);;
insert into category(name, category_id,parent_id) values ('잡화', 8,0l);;

-- 가전
insert into category(name, category_id,parent_id) values ('컴퓨터', 9,2l);
insert into category(name, category_id,parent_id) values ('냉장고', 10,2l);
insert into category(name, category_id,parent_id) values ('청소기', 11,2l);
insert into category(name, category_id,parent_id) values ('세탁기/건조기', 12,2l);

-- 도서
insert into category(name, category_id,parent_id) values ('여행', 13,3l);
insert into category(name, category_id,parent_id) values ('역사', 14,3l);
insert into category(name, category_id,parent_id) values ('예술', 15,3l);
insert into category(name, category_id,parent_id) values ('공학/과학', 16,3l);

-- 식품
insert into category(name, category_id,parent_id) values ('과일', 17,4l);
insert into category(name, category_id,parent_id) values ('채소', 18,4l);
insert into category(name, category_id,parent_id) values ('생수/음료', 19,4l);
insert into category(name, category_id,parent_id) values ('수산물', 20,4l);
insert into category(name, category_id,parent_id) values ('축산', 21,4l);

-- =============== Level 3 카테고리행
-- === 패션
-- 여성
insert into category(name, category_id,parent_id) values ('티', 22,5l);
insert into category(name, category_id,parent_id) values ('원피스', 23,5l);
insert into category(name, category_id,parent_id) values ('블라우스', 24,5l);
insert into category(name, category_id,parent_id) values ('바지/레깅스', 25,5l);

-- 남성
insert into category(name, category_id,parent_id) values ('티', 26,6l);
insert into category(name, category_id,parent_id) values ('맨투맨/후드', 27,6l);
insert into category(name, category_id,parent_id) values ('셔츠', 28,6l);
insert into category(name, category_id,parent_id) values ('바지/청바지', 29,6l);

-- 아동
insert into category(name, category_id,parent_id) values ('여아', 30,7l);
insert into category(name, category_id,parent_id) values ('남아', 31,7l);
insert into category(name, category_id,parent_id) values ('공용', 32,7l);

-- 스포츠
insert into category(name, category_id,parent_id) values ('여성', 33,8l);
insert into category(name, category_id,parent_id) values ('남성', 34,8l);
insert into category(name, category_id,parent_id) values ('유아', 35,8l);

-- 신발/가방/잡화
insert into category(name, category_id,parent_id) values ('시계', 36,9l);
insert into category(name, category_id,parent_id) values ('신발', 37,9l);
insert into category(name, category_id,parent_id) values ('가방', 38,9l);
insert into category(name, category_id,parent_id) values ('모자', 39,9l);

-- === 가전
-- 컴퓨터
insert into category(name, category_id,parent_id) values ('노트북', 40,10l);
insert into category(name, category_id,parent_id) values ('데스크탑', 41,10l);
insert into category(name, category_id,parent_id) values ('모니터', 42,10l);
insert into category(name, category_id,parent_id) values ('키보드/마우스', 43,10l);

-- 냉장고
insert into category(name, category_id,parent_id) values ('양문형냉장고', 44,11l);
insert into category(name, category_id,parent_id) values ('3/4도어냉장고', 45,11l);
insert into category(name, category_id,parent_id) values ('미니냉장고', 46,11l);
insert into category(name, category_id,parent_id) values ('김치냉장고', 47,11l);

-- 청소기
insert into category(name, category_id,parent_id) values ('무선/스틱청소기', 48,12l);
insert into category(name, category_id,parent_id) values ('진공청소기', 49,12l);
insert into category(name, category_id,parent_id) values ('로봇청소기', 50,12l);
insert into category(name, category_id,parent_id) values ('스팀청소기', 51,12l);

-- 세탁기/건조기
insert into category(name, category_id,parent_id) values ('세탁기', 52,13l);
insert into category(name, category_id,parent_id) values ('건조기', 53,13l);
insert into category(name, category_id,parent_id) values ('탈수기', 54,13l);


-- === 도서
-- 여행
insert into category(name, category_id,parent_id) values ('국내여행', 55,14l);
insert into category(name, category_id,parent_id) values ('해외여행', 56,14l);

-- 역사
insert into category(name, category_id,parent_id) values ('한국사', 57,15l);
insert into category(name, category_id,parent_id) values ('중국사', 58,15l);
insert into category(name, category_id,parent_id) values ('서양사', 59,15l);

-- 예술
insert into category(name, category_id,parent_id) values ('건축', 60,16l);
insert into category(name, category_id,parent_id) values ('미술', 61,16l);
insert into category(name, category_id,parent_id) values ('음악', 62,16l);
insert into category(name, category_id,parent_id) values ('무용', 63,16l);

-- 공학/과학
insert into category(name, category_id,parent_id) values ('화학', 64,17l);
insert into category(name, category_id,parent_id) values ('수학', 65,17l);
insert into category(name, category_id,parent_id) values ('물리', 66,17l);
insert into category(name, category_id,parent_id) values ('공학', 67,17l);

-- === 식품
-- 과일
insert into category(name, category_id,parent_id) values ('사과/배', 68,18l);
insert into category(name, category_id,parent_id) values ('귤/감', 69,18l);
insert into category(name, category_id,parent_id) values ('수박', 70,18l);
insert into category(name, category_id,parent_id) values ('딸기', 71,18l);

-- 채소
insert into category(name, category_id,parent_id) values ('콩나물', 72,19l);
insert into category(name, category_id,parent_id) values ('두부', 73,19l);
insert into category(name, category_id,parent_id) values ('당근', 74,19l);
insert into category(name, category_id,parent_id) values ('오이', 75,19l);

-- 생수/음료
insert into category(name, category_id,parent_id) values ('생수/탄산수', 76,20l);
insert into category(name, category_id,parent_id) values ('과일음료', 77,20l);
insert into category(name, category_id,parent_id) values ('커피', 78,20l);
insert into category(name, category_id,parent_id) values ('탄산/스포츠음료', 79,20l);

-- 수산물
insert into category(name, category_id,parent_id) values ('생선', 80,21l);
insert into category(name, category_id,parent_id) values ('오징어', 81,21l);
insert into category(name, category_id,parent_id) values ('새우', 82,21l);
insert into category(name, category_id,parent_id) values ('멸치', 83,21l);

-- 축산
insert into category(name, category_id,parent_id) values ('소고기', 84,22l);
insert into category(name, category_id,parent_id) values ('돼지고기', 85,22l);
insert into category(name, category_id,parent_id) values ('닭/오리고기', 86,22l);
insert into category(name, category_id,parent_id) values ('양고기', 87,22l);