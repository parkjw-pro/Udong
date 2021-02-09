# 0209_미팅기록

> 서울4반_4팀 2021년 2월 9일 미팅기록

---

[TOC]

---



## 공지사항

> (특별한 공지는 없습니다.)



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

- `ReviewCreate` CSS 작업
- `ReveiewDetail` CSS 작업
- 오늘은 `ReviewDetail`의 Carousel 작업
- 이후 story로 넘어가서 CSS 작업 에정

**강용욱**

- `Image` 작업함
- Group 파트에서 BE 등 전반적인 로직 수정함
- Group 계속해서 작업할 예정

**이규용**

- 커뮤니티 서비스에서 `좋아요`와 `댓글` 기능 구현 완료

**박종원**

- `FindStore`에서 리스트와 지도로 병행해서 표시
- for문으로 지도에 마커 찍기
- 프론트엔드 EC3 서버 배포해보기



### 팀미팅 피드백

- `Create`
  - 사진 삭제 버튼 만들기
  - 사진 추가시, `추가할래요`, `추가하지 않을래요`라는 메시지 변경
  - 전체적인 페이지가 비어보인다.
  - 텍스트 상자가 너무 크다.
  - 리뷰 작성 윗 부분에는 `개인 정보`를 들고 와보면 어떨까?
    - `네이버 지도 리뷰` 페이지 참고하기!
- `GetStore`
  - 지도에서 확대했을 때, 추가할 수 있도록
  - 리스트 정보에서 `시/구/동`은 별로 중요하지 않다.
  - 다른 중요한 정보들을 넣으면 좋을 것  같다.
  - `종합소매점` 검색시, `편의점`으로도 검색할 수 있도록 설정해준다.
  - 검색버튼 만들기
  - 비었을 때, 검색 추천어를 띄워줄 수 있다.
  - 지도에 커피, CU 등의 로고/아이콘이 들어가면 좋을 것 같다.
  - :ballot_box_with_check: 검색을 참고하기 위해서는 `네이버쇼핑`을 참고하면 좋다!
- 카테고리 만들기
  - 편의점
  - 카페
- 위에 페이지 탭에 `우동 아이콘`과 `우동 이름`이 표시될 수 있도록 만든다.
- 뉴스피드
  - 그룹명 [chips](chips) 참고하기
- 왼쪽 정렬을 채택해서, 오른쪽에 `핫 article`  등을 사용하면 어떨까?
- `소식`은 구현할 수 있도록!



### 이미지 크기를 고정하는 이슈

> Carousel에서 표시되는 크기가 다른 여러가지 이미지들의 크기를 일정하게 고정시킨다. (2021.02.09)

```markdown
#carousel #image #size #fix
```

- 전체 구조는 아래와 같다.

```html
<b-carousel
  id="carousel-1"
  v-model="slide"
  controls
  indicators
  background="#ababab"
  img-width="1024"
  img-height="480"
  style="text-shadow: 1px 1px 2px #333; width: 30em; height: 15em;"
  fade="true"
>
  <b-carousel-slide
    id="review_img"
    v-for="(item, index) in fileId"
    :key="index"   
    :img-src="url+`/review/download/` + item" 
  ></b-carousel-slide>
</b-carousel>
```

- 먼저 `<b-carousel>`의 스타일에서 고정하고자 하는 이미지 크기와 동일한 크기로 넓이와 높이를 설정해준다.
  - 여기서는 `width: 30em; height: 15em;`으로 설정하였다.
- 이후 이미지가 출력되는 `<img>` 태그 혹은 여기서는 `<b-carousel-slide>` 태그에 style 속성을 아래와 같이 설정한다.

```css
#review_img {
  top: 0;
  left: 0;
  min-width: 30em;
  min-height: 15em;
  max-width: 30em;
  max-height: 15em;
},
```

- `min-width`, `min-height`, `max-width`, `max-height` 값을 모두 설정하여 이미지 크기를 고정해준다.



## :hand: 다음과제

- 자세한 내용은 `jira` 참고!!



***Copyright* © 2021 Song_Artish**