MySQL로 구현!

CREATE TABLE LIBRARY (
	NUM int primary key auto_increment, --게시판 번호
	ID varchar(20), 	--게시판 글쓴이
	PW int,				--게시판 비밀번호 (int형으로 X,해보니 String 형으로 해야함!)
	TITLE varchar(40), 	--게시판 제목
	CONTENT varchar(300), --게시판 내용
	COUNT int DEFAULT 0,	--게시판 조회수
	FNAME varchar(200), --파일 이름
	DATE datetime DEFAULT CURRENT_TIMESTAMP --게시판 작성시간
    );
    
--mysql 페이징 처리 query
SELECT * FROM (SELECT @rownum:=@rownum+1  rnum, l.*  FROM library l, (SELECT @ROWNUM := 0) R WHERE 1=1) list
WHERE rnum >= 1 AND rnum <=5 ;
    
   