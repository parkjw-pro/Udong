# 0118_미팅기록 - 기획 마무리

> 서울4반_4팀 2021년 1월 18일 미팅기록

---

[TOC]

---



## 공지사항

- **AWS 서버**는 이번주 중으로 사용가능할 수 있게 할 예정입니다.
- :star: **다음주 중간발표**
  - 주로 기획을 발표하는 자리가 될 것 같음
  - 팀장 아니어도 발표 가능! 프로젝트를 가장 잘 이해한 사람이 발표할 수 있도록!!
- **프로젝트 진행**
  - `4 - 5주차`에는 프로젝트 완성할 수 있도록!!
  - `6주차`에는 버그 수정
- :star: `1일 1commit` 꼭 할 수 있도록!



## 주제

1. 개발 환경 구축
   - [개발 환경 구축](src/Development_Enviornment_Setting.pdf)와 [Vue 설치](src/Development_Enviornment_Setting_Vue.pdf) 자료를 참고한다.
2. **커뮤니티** 와이어프레임 작성
3. 디자인 리뷰
4. DB 수정 및 페이지 넘어가는 순서 정리



## 내용

### 1. 개발환경

> 우리의 개발 환경은 다음과 같다.

```markdown
## 백엔드
openjdk 11.0.9.1 2020-11-04 LTS
sts 3.9.14
## 프론트엔드
npm 6.14.10
@vue/cli 4.5.10
## 데이터베이스
MySQL 8.0.22
## Tool
Visual Studio Code
```



### 2. 와이어프레임

- 와이어프레임 완성본은 [파일](210118 Ui 와이어프레임_ver1.pdf)를 참고한다.

- 와이어프레임 예시

  ![리뷰 작성 페이지](src/0118_review_create.png)



### 3. 데이터베이스 설계

- DB 설계는 [파일](https://docs.google.com/spreadsheets/d/1DZ8YnjyE82DVMcvgV5fPHP5Tc-EhFy-Fp7AQqUGQaqA/edit)을 참조한다.



### 4. 기타

- 리뷰 장소 API의 경우 공공데이터를 이용한다.
- 아래의 링크에서 방법을 참고한다.
  - [우리 동네 맛집 추천엔진 직접, 쉽게 만들기 (크롤링과 코사인 유사도)](https://data101.oopy.io/recommendation-engine-cosine-similarity)



## 정리

- 무사히 기획 방향에 대한 논의가 잘 마쳤다.
- 내일은 `1시반 팀미팅` 시간에 작성한 와이어프레임을 발표하고 컨설턴트/코치님께 피드백을 받을 에정이다.



## :hand: 다음과제

:star: 데이터베이스 체크

:star: 팀미팅 발표 자료(와이어프레임) 준비하기

:star: 개발파트 배분하기