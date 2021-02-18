# Vue 동적 라우트 매칭

> 프론트엔드 URL을 동적 라우트 매칭을 변경하였다.

2021.02.06

---

[TOC]

---



## URL 주소 변경

- URL을 아래와 같이 변경하였다.

  [URL 정리 파일](FE_URL.pdf)
  
- `router/index.js`에서도 확인 가능



## 작업 현황

- 현재까지 review 전체, 뉴스피드, 마이피드, GroupList까지 처리완료



## 처리 방법

### 1. URL 확인

- `router/index.js`에서 URL을 확인한다.

```javascript
  {
      path: '/store/find/:address/:keyword',
      name: 'FindStore',
      component: FindStore,
  },
```

### 2. params 입력

- 페이지를 변경할 곳에서 다음과 같이 router push 코드를 입력한다.

```html
<b-button @click="toFindStore">
    상점 찾기!!!
</b-buttno>
```

```javascript
methods: {  
    toFindStore: function () {
        this.$router.push({ name: 'FindStore', params: { address: this.user.address, keyword: this.searchWord}})  	}}
```

### 3. variable 받기

- 전환되는 페이지에서는 다음과 같이 이전 페이지에서 받은 변수를 사용할 수 있다.

```javascript
this.$route.params.address
this.$route.params.keyword
```