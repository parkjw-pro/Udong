# 0127_미팅기록

> 서울4반_4팀 2021년 1월 27일 미팅기록

---

[TOC]

---



## 공지사항

> (별도 공지 없음)

- 금요일 발표 잘 준비할 수 있도록!!!
  - 기획 발표 Okay!
  - 개발 진행상황 발표도 Okay!





## 주제

- 



## 내용

### 아침 Scrum

**박종원**

- 이미지 파일 업로더와 서버 연결 완료
- 지역별 DB 처리 완료
- 파일 불러오기 처리
- 지역(area) 찾아가는 controller 생성
- backend 코드 merge 예정

**이송영**

- 커뮤니티 게시글 작성/수정, 상세 페이지 및 뉴스 피드 레이아웃 작성
- 오늘은 리뷰 서비스 페이지 레이아웃 작성 예정

**우진하**

- `비밀번호찾기` 레이아웃 및 로직 구현 중
- 위치 인증 작업 예정

**강용욱**

- POST하던 것 마무리
- front로 넘어가서 파트 처리

**이규용**

- url 정리
- front로 넘어가기



### 오늘 작업

**이송영**

- `Login`, `Navbar` 디자인
- `Story > Article`의 comment 기능 작업 중

**이규용**

- url 정리
- 카카오 지도 API 가져오기

**박종원**

- area관련 사용해야할 db를 aws DB에 업데이트
- area관련 기능 구현하기 위해 controller 및 서비스기능 작성완료
- club에 파일 업로드 기능 구현 및 확인 완료
- 내일 할 일
  - 파일 load기능 구현하기(관련 컨트롤러들 전부 리턴값을 resultDto(기존 dto + files)로 수정)
  - 용욱이 post끝나면 파일 입출력 기능 추가해서 구현하기
  - 회원가입부터 게시판까지 모든 기능 postman으로 점검해보기



## 공부한 내용

### CSS 속성

#### overflow

> 컨텐츠가 너무 커서 요소의 블록 서식 맥락에 맞출 수 없을 때의 처리법을 지정한다.
>
> - [참고사이트](https://developer.mozilla.org/ko/docs/Web/CSS/overflow)

- `visible`: Default. 넘친 컨텐츠는 상자 밖으로 보여진다.
- `hidden`: 넘친 컨텐츠는 잘려지고 보여지지 않는다.
- `scroll`:  넘친 컨텐츠는 잘리고, 스크롤바가 생겨서 스크롤해서 볼 수 있게된다. 필요하지 않더라도 가로/세로 스크롤바가 모두 생긴다.
- `auto`: 넘칠경우 스크롤바가 자동으로 생긴다. 가로/세로 필요한 부분에만 생긴다.

예시

```css
/* 키워드 값 */
overflow: visible;
overflow: hidden;
overflow: clip;
overflow: scroll;
overflow: auto;
overflow: hidden visible;

/* 전역 값 */
overflow: inherit;
overflow: initial;
overflow: unset;
```

- :white_check_mark: `overflow-x`, `overflow-y`로 overflow를 가로/세로 각각 제어할 수도 있다.

  - 예시

    ```css
    div.container1 {
        overflow-x: scroll;
        overflow-y: hidden;
    }
    
    div.container2 {
        overflow-x: auto;
        overflow-y: scroll;
    }
    ```

    

#### text-align

> 텍스트의 정렬 방향을 지정한다.

- `left`: 왼쪽 정렬
- `right`: 오른쪽 정렬
- `center`: 중앙 정렬
- `justify`: 양쪽 정렬 (자동 줄바꿈시 오른쪽 경계선 부분 정리)

사용법 예시

```css
#box1 { text-align: right; }
#box2 { text-align: left; }
#box3 { text-align: center; }
```



#### white-space

> 스페이스와 탭, 줄바꿈, 자동줄바꿈을 처리하는 속성이다.
>
> - 상속 O, 애니메이션 X
> - [참고사이트](https://developer.mozilla.org/ko/docs/Web/CSS/white-space)

- `normal`: 연속 공백을 하나로 합친다. 개행 문자도 다른 공백 문자와 동일하게 처리하며, 한 줄이 너무 길어서 넘칠 경우 자동으로 줄을 바꾼다.
- `nowrap`: 연속 공백을 하나로 합친다. 줄 바꿈은 `<br>` 요소에서만 일어난다.
- `pre`: 연속 공백을 유지한다. 줄 바굼은 개행 문자와 `<br>` 요소에서만 일어난다.
- `pre-wrap`: 연속 공백을 유지한다. 줄 바꿈은 개행 문자와 `<br>`요소에서 일어나며, 한 줄이 너무 길어서 넘칠 경우 자동으로 줄을 바꾼다.
- `pre-line`: 연속 공백을 하나로 합친다. 줄 바꿈 개행 문자와 `<br>`요소에서 일어나며, 한 줄이 너무 길어서 넘칠 경우 자동으로 줄을 바꿉니다.
- `break-spaces`: (아래 차이점을 제외하면 `pre-wrap`과 동일하다.)
  - 연속 공백이 줄의 끝에 위치하더라도 공간을 차지한다.
  - 연속 공백의 중간과 끝에서도 자동으로 줄을 바꿀 수 있다.
  - 유지한 연속 공백은 `pre-wrap`과 달리 요소 바깥으로 넘치지 않으며, 공간도 차지하므로 박스의 본질 크기(`min-content`, `max-content`)에 영향을 준다.

|                | 개행 문자 | 스페이스, 탭 | 자동 줄 바꿈 | 줄 끝의 공백 |
| :------------: | :-------: | :----------: | :----------: | :----------: |
|    `normal`    |   병합    |     병합     |      예      |     제거     |
|    `nowrap`    |   병합    |     병합     |    아니오    |     제거     |
|     `pre`      |   유지    |     유지     |    아니오    |     유지     |
|   `pre-wrap`   |   유지    |     유지     |      예      |     넘침     |
|   `pre-line`   |   유지    |     병합     |      예      |     제거     |
| `break-spaces` |   유지    |     유지     |      예      |   줄 바꿈    |





## :hand: 다음과제

- 금요일 발표자 정하기
  - 발표 자료 준비
- backend merge 후 프론트 작업
- 페이지 구성 `web` vs `app` 논의하기 :exclamation:
- 파트별 개발 진행
  - 자세한건 `jira` 참조
