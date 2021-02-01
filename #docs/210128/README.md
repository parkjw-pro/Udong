# 0128_미팅기록

> 서울4반_4팀 2021년 1월 28일 미팅기록

---

[TOC]

---



## 공지사항

> 내일 **평가** 있습니다!!

- 오늘 16시부터 취업특강이 있습니다.
- 그래서 종료미팅은 15:30에 하게 됩니다!!



## 주제

- 금요일 발표자 정하기
  - 발표 자료 준비
  - 발표 `이송영`
- backend merge 후 프론트 작업
  - 내일까지 backend 완료할 예정!!
- 페이지 구성 `web` vs `app` 논의하기 :exclamation:
  - 우선 `web`에 맞는 방향으로 구현!



## 내용

### 아침 Scrum

**박종원**

- 내일 발표를 위한 back 부분을 완성해서 넘겨준다.

**이송영**

- 디테일 수정

**우진하**

- 비밀번호찾기 front 코드 작성 완료

**강용욱**

- back 게시글
- 좋아요, 신고, join 작업 예정

**이규용**

- 지도 API, 현재위치 정보 가져오기 완료



### 피드백

- Backend URL RESTful하게 정리하기!
  - [참고사이트](https://meetup.toast.com/posts/92)
- SQL과  naming 맞추기



## 오류 수정

### CSS 오류 수정

> 페이지 최상위 컴포넌트인 `App.vue`에서 `font-family`로 글꼴을 변경하였는데, 하위컴포넌트인 `Login.vue` 등에서 `<input>` 태그 중 `type="password"`인 태그가 표시되지 않는 오류가 발생하였다.

- 아래와 같이 해당 `<input>` 태그에 추가적인 CSS 스타일링을 적용하여 해결하였다.

```css
input[type="password"] {
  font:small-caption;font-size:16px;
}
::placeholder {
  font-family: 'Jeju Gothic', sans-serif;
}
```



## :hand: 다음과제

- 발표 잘 준비하기!
- BE 마무리하기!
