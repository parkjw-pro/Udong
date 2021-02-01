# 우리동네(UDONG)

> **frontend**

---

[TOC]

---

## Intro

### Node 패키지

- 본 프로젝트에서 사용하는 node 패키지는 아래와 같다.

```bash
npm install axios
npm install lodash
npm install vue bootstrap-vue bootstrap
npm install vue-burger-menu --save
npm install less less-loader --save-dev
npm install --save @fortawesome/fontawesome-free
npm install vue-js-toggle-button --save
npm install vee-validate --save
npm install vue-select
npm i vue-slick-carousel
npm install packery
npm install --save vue-packery-draggabilly-plugin
npm install jquery
npm install mint-ui -S
```

### 폴더 구조

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

### Nameing Rule

- 파일 명은 `PascalCase`를 따른다.
- 변수 및 함수명은 `snake_case`를 따른다.



## 시작하기

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
  MAP_API_KEY=<카카오 지도 API Javascript 키>
  ```

- :ballot_box_with_check: 환경변수를 입력할 때는, 띄워쓰기를 하지 않도록 주의한다!