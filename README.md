# 우리동네(UDONG)

---

[TOC]

---



## Intro

> 2021년 SSAFY 2학기 공통프로젝트로 `우리동네`라는 커뮤니티를 개발하였다.
>
> 개발기간: 2021년 1월 11일 - 2021년 2얼 19일

### Service

- 동네 사람들을 연결하는 커뮤니티 기능을 제공한다.
- In this service, users can be connected with their neighbours.
- 위치 인증 방식으로 신뢰도 높은 리뷰 서비스를 제공한다.
- With location-based authentication system, users can access reviews of highly elevated reliability.
- 사용자들은 동네 이야기 및 정보를 효과적으로 공유할 수 있다.
- Users can share stories and information of town effectively with each other.

### Compatibility

- `openjdk 11.0.9.1`, `npm 6.14.10`, `vue/cli 4.5.19` 버전에서 테스트되었다.
- We tested the code using `openjdk 11.0.9.1`, `npm 6.14.10`, `vue/cli 4.5.19`.
- 데이터베이스는 `MySQL 8.0.22`에서 작성되었다.
- We created database using `MySQL 8.0.22`



### Requirements

```markdown

```



**Naming Rule**

- 파일 명은 `PascalCase`를 따른다.
- 변수 및 함수명은 `snake_case`를 따른다.



## 설치 안내

`git clone`을 한 후 아래의 작업을 진행해야 프론트 서버를 실행할 수 있다.

**1단계: npm 설치**

- `frontend` 프로젝트 폴더에서 npm을 설치한다.

```bash
npm install
```

**2단계: 환경변수 설정**

- `frontend` 폴더 내의 최상위 창에서 환경변수 `.env.local`이라는 파일을 생성한다.

- 환경변수로 관리하는 데이터는 다음과 같다.

  ```markdown
  ## .env.local
  
  VUE_APP_SERVER_URL=<서버 URL>
  VUE_APP_MAP_API_KEY=<카카오 지도 API Javascript 키>
  ```

- :ballot_box_with_check: 환경변수를 입력할 때는, 띄워쓰기를 하지 않도록 주의한다!



## File Manifest

### Frontend

- 폴더 구조는 `account`, `app`, `review`, `story`로 구분한다.

  ```markdown
  1. account
  - 회원계정 관련
  2. app
  - 위치인증, 메인페이지, 뱃지 등 나머지
  3. review
  - 리뷰 서비스 관련
  4. story
  - 커뮤니티 서비스 관련
  ```


- :ballot_box_with_check: 페이지로 구성되면 `src/views` 폴더 안에, 컴포넌트로 활용되면 `src/components` 폴더 안에 배치하는 것을 원칙으로 한다.

### Backend



## 저작권 및 사용권 정보

- 다음의 라이브러리를 사용하였다.
- Below libraries are used in this project.

```markdown
- axios
- lodash
- vue bootstrap-vue bootstrap
- vue-burger-menu
- less less-loader
- @fortawesome/fontawesome-free
- vue-js-toggle-button
- vee-validate
- vue-select
- packery
- vue-packery-draggabilly-plugin
- jquery
- mint-ui -S
- vue-star-rating
- vue-coverflow
- vue-slick-carousel
- vue-glide-js
```



## 알려진 버그



## Credits

- 이송영 [@github](https://github.com/SongArtish)
- 강용욱 [@github](https://github.com/KangYongWook)
- 박종원 [@github](https://github.com/parkjw-pro)
- 이규용 [@github](https://github.com/gyuyong290)

