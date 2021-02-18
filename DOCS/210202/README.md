# 0202_미팅기록

> 서울4반_4팀 2021년 2월 2일 미팅기록

---

[TOC]

---



## 공지사항

> 

## 주제

- 오후 팀미팅
  - 페이지 시연 -> 용욱이가!



## 내용

### 아침 Scrum

**박종원**

- Location 작업

**이송영**

- FE 레이아웃 작업

```markdown
#### 6시 이후 추가작업한 것

- Navbar 디자인 개선
- 리뷰메인 페이지 레이아웃 작성
- 상점검색 페이지(2장) 레이아웃 작성
- 상점검색 컴포넌트 생성

#### 오늘 할 것

- 리뷰작성 페이지 레이아웃 작성
- 리뷰 카테고리 상세조회 레이아웃 작성
- 리뷰 글 상세조회 레이아웃 작성
```

**강용욱**

- URL 연결중

**이규용**

- BE 정리

```markdown
#### BE 통합본에 관하여

- 함수명 camelCase로 통일
- query.xml 파일들 PascalCase로 통일
- UserBoard -> UserPost로 변경
- swagger에 꼭 필요한 변수 정리해서 넣어둠
- url 안 맞는 부분 수정 ex) 리뷰 부분
​```java
  updateReviewReadCount 부분 url 수정
  @GetMapping("/review/store/{storeId}/plus")  ->  @PutMapping("/review/store/{storeId}")
​```
```



## :hand: 다음과제

- 자세한 내용은 `jira` 참고!!
