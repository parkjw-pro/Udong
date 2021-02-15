# 0215_미팅기록

> 서울4반_4팀 2021년 2월 15일 미팅기록

---

[TOC]

---



## 공지사항

> (특별한 공지사항 없음!)

- 발표 준비 잘하기! UCC, 개발 열심히 하기!



## 주제

- 각자 파트별 개발 열심히 하기!

  |    이름    |             담당파트             |
  | :--------: | :------------------------------: |
  | **강용욱** |  Community 서비스 중 Group 파트  |
  | **박종원** |          Review 서비스           |
  | **이규용** | Community 서비스 중 Article 파트 |
  | **이송영** |     전체적인 CSS 및 FE 지원      |




## 내용

### 아침 Scrum

**이송영**

- favicon 만들기
- 오늘은 지난주 피드백 위주로 CSS 처리 예정

**강용욱**

- 그룹 부분 BE 데이터 연결 전체 완료
- `FormData()`가 put 요청을 받지 않는 문제
- 위의 문제 포함 그룹정보/회원정보 수정 파트(그룹 탈퇴, 그룹 삭제 등) 마무리 예정

**박종원**

- 카테고리 파트 작업 완료
- 카테고리 파트 세부 작업 마무리
- 오전은 AWS 서버 배포 (위치인증 문제 등 해결) 

**이규용**

- `MyFeed > GroupBox` 가져오기 작업
- 게시글 저장시 태그 저장까지 완료



### 지난주 화요일 피드백 사항

- `Create`
  - 사진 삭제 버튼 만들기
    - :small_red_triangle: 추가해야 하는데 어렵다 ㅠㅠㅠ
  - 사진 추가시, `추가할래요`, `추가하지 않을래요`라는 메시지 변경
    - :heavy_check_mark: 수정완료
  - 전체적인 페이지가 비어보인다.
    - :heavy_check_mark: 오른쪽에 지도 표시
  - 텍스트 상자가 너무 크다.
    - :heavy_check_mark: 보완완료
  - 리뷰 작성 윗 부분에는 `개인 정보`를 들고 와보면 어떨까?
    - `네이버 지도 리뷰` 페이지 참고하기!
    - :heavy_check_mark: 개인정보 대신 지도 표시
- `GetStore`
  - 지도에서 확대했을 때, 추가할 수 있도록
    - 개선하지 않을 예정!
  - 리스트 정보에서 `시/구/동`은 별로 중요하지 않다.
    - :heavy_check_mark: 개선완료
  - 다른 중요한 정보들을 넣으면 좋을 것  같다.
    - :heavy_check_mark: 지점명 및 주소를 넣어주었다.
  - `종합소매점` 검색시, `편의점`으로도 검색할 수 있도록 설정해준다.
    - :heavy_check_mark: 개선완료
  - 검색버튼 만들기
    - :heavy_check_mark: 만들었음
  - 비었을 때, 검색 추천어를 띄워줄 수 있다.
    - :heavy_check_mark: 편의점, 카페 등 추천어 페이지에 제시함
  - 지도에 커피, CU 등의 로고/아이콘이 들어가면 좋을 것 같다.
    - 개선하지 않을 예정!
  - 검색을 참고하기 위해서는 `네이버쇼핑`을 참고하면 좋다!
    - 참고함!
- 카테고리 만들기
  - 편의점
  - 카페
  - :heavy_check_mark: 카테고리 생성 후 연결 완료!
- 위에 페이지 탭에 `우동 아이콘`과 `우동 이름`이 표시될 수 있도록 만든다.
  - :heavy_check_mark: favicon 생성완료
- 뉴스피드
  - 그룹명 [chips](chips) 참고하기
  - :heavy_check_mark: `vue-glide`와 `b-button` 사용!
- 왼쪽 정렬을 채택해서, 오른쪽에 `핫 article`  등을 사용하면 어떨까? (이송영 생각)
- `소식`은 구현할 수 있도록!



### Window 객체

**Window 객체 계층구조**

![window 객체 내 주요 산하 객체들](TIL/JavaScript/img/window_objects.jfif)

`(출처: http://www.ktword.co.kr/test/view/view.php?nav=2&no=5991&sh=window+%EA%B0%9D%EC%B2%B4)`



#### Properties

> [w3schools 자료](https://www.w3schools.com/jsref/obj_window.asp)를 참고하였다.

|    property     |                             설명                             |
| :-------------: | :----------------------------------------------------------: |
|    `closed`     |       창이 종료되었는지 여부를 나타내는 bolean 값 반환       |
| `defaultStatus` |       창의 상태표시줄의 텍스트를 반환하거나 설정한다.        |
|    `document    |                창의 document 객체를 반환한다.                |
| `frameElement`  |         현재 창의 삽입된 `<iframe>` 요소를 반환한다.         |
|    `frames`     |         현재 창에서 모든 `<iframe>` 요소를 반환한다.         |
|    `history`    |                창의 history 객체를 반환한다.                 |
|  `innerHeight`  |       창의 콘텐츠 영역(뷰포트)의 내부 높이를 반환한다.       |
|  `innerWidth`   |       창의 콘텐츠 영역(뷰포트)의 내부 너비를 반환한다.       |
|    `length`     |         현재 창의 `<iframe>` 요소의 객수를 반환한다.         |
| `localStorage`  | 데이터를 저장하는데 사용되는 로컬 스토리지 객체에 대한 참조를 반환한다. |
|   `location`    |                창의 location 객체를 반환한다.                |
|     `name`      |               창의 이름을 반환하거나 설정한다.               |
|   `navigator`   |               창의 navigator 객체를 반환한다.                |
|    `opener`     |            창을 생성한 창에 대한 참조를 반환한다.            |
|       ...       |                             ...                              |



#### Methods

>[w3schools 자료](https://www.w3schools.com/jsref/obj_window.asp)를 참고하였다.

|      method       |                         설명                          |
| :---------------: | :---------------------------------------------------: |
|     `alert()`     |       메세지와 확인 버튼을 대화상자로 표시한다.       |
|     `atob()`      |       base-64로 인코딩 된 문자열을 디코딩한다.        |
|     `blur()`      |            현재 창에서 포커스를 제거한다.             |
|     `btoa()`      |            base-64로 문자열을 인코딩한다.             |
| `clearInterval()` |      `setInterval()`로 설정한 타이머를 제거한다.      |
| `clearTimeout()`  |     `setTimeout()`으로 설정한 타이머를 제거한다.      |
|     `close()`     |                   현재 창을 닫는다.                   |
|    `confirm()`    | 메시지 및 확인, 취소 버튼이 있는 대화상자를 표시한다. |
|     `focus()`     |                 현재 창을 포커스한다.                 |
|        ...        |                          ...                          |



#### location 객체

`location.href`

- 현재 윈도우의 url을 반환한다.

`location.href(url주소)`

- 새로운 페이지로 이동된다.
- 주소 히스토리가 기록된다.

`location.replace(url주소)`

- 기존페이지를 새로운 페이지로 변경시킨다.
- 주소 히스토리가 기록되지 않는다.





## :hand: 다음과제

- 자세한 내용은 `jira` 참고!!
