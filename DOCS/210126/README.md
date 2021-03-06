# 0126_미팅기록

> 서울4반_4팀 2021년 1월 26일 미팅기록

---

[TOC]

---



## 공지사항

> 금요일 발표준비 잊지 않고 준비할 수 있도록!!

- 혹시 `Intellij` 필요한 사람?
  - `MM 공지사항 참조`
- UCC 담당자 금일내 `movavi video editor plus 2021` 설치하기
- *commit 메시지*
  - 김명석 코치님 0125자료 참고
- 내일(1/27) 11시 우진하 프로님과 면담 일정 있음!!



## 주제

- 개인별 개발 진행 현황 scrum
- 분업 중 발생하는 파트 간 조율이 필요한 사안 논의



## 내용

### 개발 진행 현황

**이송영**

- 커뮤니티 기능 계속해서 구현 중
- 오늘은 회원 조회/관리, 커뮤니티 게시물, 개인 피드 등의 페이지 작업 예정

**이규용**

- 파트 작업 완료
- 파일 업로드 작업 예정

**박종원**

- 키워드 검색 로직 구현
  - 분류/점포명 등 `or`로 다수의 키워드가 동시에 검색 가능하도록 처리하였다.
- 리뷰 수정 로직 추가
- 파일 업로드 함께 작업 예정

**우진하**

- 회원가입에서 `validation` 처리
- 회원가입 `유효성` 처리
- 비밀번호 찾기 및 user 관련 페이지 구현 예정

**강용욱**

- User에서 `user_state=1`이면 로그인 불능상태로 처리
- 회원가입시 비밀번호 해시 코드 추가
  - 로그인시 해시 코드 추가는 최종 전 단계에서 적용 예정
- 이메일 인증 작업 중



### 오늘 질문 사항

- AWS 서버에 파일 올리는 방법
  - 명석 코치님 [참고링크](https://bezkoder.com/spring-boot-upload-multiple-files/)
- (이메일 인증 backend 작업 처리)



### 팀미팅 피드백 사항

**backend**

- client -> backend로 통신
- `multipartFile` 사용하기
- domain을 기반으로 경로 작성

**frontend**

- 사이트 컨셉 따라서 전체적인 느낌을 맞추는 것이 중요
- 글씨체, img 테두리
- 모든걸 똑같이 할 필요는 없지만 중요한 부분은 맞추는 것이 좋음

**database**

- `동네리스트` 기준정보에 대한 테이블이 필요
- 기준되는 key로 연결



## :hand: 다음과제

:star: Backend

- 파일업로드/이메일 인증 처리
- 파트별 코드 merge
- POST 구현

:star: 목요일부터는 로그인/회원가입 등 기본 기능 front-back 연결할 예정!

:star: 자세한건  `Jira` 참조!
