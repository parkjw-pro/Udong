# 0125_미팅기록

> 서울4반_4팀 2021년 1월 25일 미팅기록

---

[TOC]

---



## 공지사항

> :ballot_box_with_check: **금욜 팀 평가**

- 이번주 **금요일에 팀 평가**가 있습니다 :exclamation:
  - 발표 내용: 기획 위주 - 기획을 가장 잘 나타낼 수 있는 형태로 발표!
  - 발표 시간: 10분 이내
  - 발표 순서: (당일 정할 예정)
- 내일 14시 종원이 컨선턴트님과 면담
- **내일 팀미팅**
  - 서버의 DB 소개 (종원)
  - 프론트 작업 현황 소개 (송영 or 진하)



## 주제

- 파트별 개발 진행
- 뱃지 논의 (강용욱 제안!)
  - postpone
- UCC 담당자 정하기
  - 이규용, 이송영, 박종원 가능!
  - 추후 논의



## 내용

### Vue 플러그인 등록

>[vue-js-toggle-button](https://www.npmjs.com/package/vue-js-toggle-button)을 등록하고 사용하는 법을 다룬다.

**1. npm 설치**

- 먼저 Node 패키지를 설치한다.

```bash
npm install vue-js-toggle-button --save
```

**2. 플러그인 등록**

- :ballot_box_with_check: 플러그인은 `src > main.js`에서 등록해준다!!

```javascript
// main.js

import ToggleButton from 'vue-js-toggle-button' 
Vue.use(ToggleButton)
```





## 정리

**송영**

- 커뮤니티 기능 구현 중
- Group 파트 구현 예정

**진하**

- 로그인/회원가입 기능 및 UI 구성 완료

**규용**

- 그룹 CRUD 구현
- 데이터 입력하면 받아오는지
- 파일 업로드 작업중!

**종원**

- 리뷰 기능 구현 중

**용욱**

- 유저 구현 완료!
- 이메일 인증 API 구현중



## :hand: 다음과제

:star: `Jira` 참조!
