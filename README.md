# #무비로그(MOVIELOG)🎥


<h3>목차</h3>


+ [개발 배경](#%EF%B8%8F-개발-배경)
+ [개발 과정](#-개발-과정)
  + [개발 기간](#1-개발-기간)
  + [사용 언어](#2-사용-언어)
  + [프로젝트 목표](#3-프로젝트-목표)
  + [DB 설계](#4-DB-설계)
+ [개발 결과](#-개발-결과)
  + [구현한 기능](#1-구현한-기능)

 * * *
<h3>🖥️ 개발 배경</h3>

+ 영화를 사랑하는 사람들을 위한 정보 공유 & 친목 사이트입니다
+ 개발 구상 단계에서 팀원들의 공통점이 모두 영화를 좋아한다는 것을 알게 되어 떠오른 아이디어로 구상하게 되었습니다

 * * *
<h3>💡 개발 과정</h3>

<h4>1. 개발 기간</h4>

+ 2022년 8월 ~ 2022년 9월 / 총 1달
+ 설계 1주 / 개발 3주

<h4>2. 사용 언어</h4>

<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white">
  <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
<br>
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
  <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
  <img src="https://img.shields.io/badge/fontawesome-339AF0?style=for-the-badge&logo=fontawesome&logoColor=white">
</div>

<h4>3. 프로젝트 목표</h4>

1. Spring-security 방식의 회원가입, 로그인
2. 유효성 검사
3. 게시판 구현(CRUD 구현, 다중 이미지 업로드, 다중 게시판)
4. 댓글 작성(CRUD 개념 적용)
5. 검색

<h4>4. DB 설계</h4>


![_movie](https://user-images.githubusercontent.com/118376561/202316994-436932bd-4076-42b0-ab89-a6e883d95d63.png)

***
<h3>🧾 개발 결과</h3>

<h4>1. 구현한 기능</h4>

+ 로그인
<img src="https://user-images.githubusercontent.com/118376561/202320076-2bbafdab-5367-4928-b87c-5b2b304f6cc8.jpg"/>


 인터셉터(Interceptor) 활용, 세션 부여, 자동 로그인, 아이디 찾기, 비밀번호 찾기, 소셜 로그인

![제목 없는 디자인 (1)](https://user-images.githubusercontent.com/118376561/202320687-0e17e99b-f6c6-432d-94e2-4610e53cd2f9.jpg)

 비밀번호 찾기 메일 전송, 임시 비밀번호 메일, 회원가입 메일

***

+ 회원가입


![제목 없는 디자인 (2)](https://user-images.githubusercontent.com/118376561/202322471-d1e00011-b1e0-4f2d-8343-cfbb2682461f.jpg)
비동기 유효성 검사, 비밀번호 암호화 ,이메일 인증 ,인증 비밀번호 암호화, 회원 페이지

***

+ 게시판

![MOVIELOG - Chrome 2022-11-16 오후 11_53_48](https://user-images.githubusercontent.com/118376561/202322996-af62bf2c-e686-4431-a521-808ff200cd60.png)

다중 게시판, 페이징, 공지사항, 조회 수, 댓글 개수, 인터셉터 활용 - URL 기억으로 로그인, 로그아웃 시 페이지 이동 X



![제목 없는 디자인 (3)](https://user-images.githubusercontent.com/118376561/202323122-7976a183-6db5-459d-a346-c9867f0a89bc.jpg)

검색, 글 작성 시 카테고리 표시



![제목 없는 디자인 (4)](https://user-images.githubusercontent.com/118376561/202323395-bf2588a7-d22f-420b-8e82-9620b38b62f1.jpg)

다중 첨부파일, 에디터 적용, 비동기 댓글(등록, 삭제, 수정), 좋아요

***

+ 회원정보 수정
![무비로그 - Chrome 2022-11-17 오전 9_14_04](https://user-images.githubusercontent.com/118376561/202323644-1863f935-7d65-4b13-9ee5-cf38ec369d0a.png)


닉네임 변경 (Ajax 중복체크), 이메일 변경, 비밀번호 변경(Ajax 유효성 검사), 회원 탈퇴

