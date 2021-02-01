# 0121_미팅기록 - ERD

> 서울4반_4팀 2021년 1월 21일 미팅기록

---

[TOC]

---



## 공지사항

> 별도 공지 없습니다~!

- 프로젝트 이름 영명(英名)은 `Udong`으로 통일합니다!
- 1/26(화욜) 14시 박종원 프로님과 면담 예정



## 주제

- ERD 완료 후 데이터베이스 생성
- 프론트엔드/백엔드 파트 분배
- 파트별 개발 진행



## 내용

### 1. MySQL에 AWS 서버 연결하기

![mysql_new_connection](img/0121_mysql_new_connection.jpg)

- 1번에는 connection name을 사용자 정의대로 입력한다.
- 2번에는 발급받은 `host name`을 입력한다. (예시. `.io`)
- 3번에는 `ubuntu`라고 입력해주었다.
- 4번에는 발급받은 `key 파일`을 등록해준다.
- 5번에는 발급받아서 설정한 AWS 서버 아이디를 입력한다.
- 6번 `Test Connection`을 클릭하면 비밀번호를 입력해야 하는데, AWS 서버 비밀번호를 입력한다.
- 성공한 것을 확인하며 마지막으로 `OK` 버튼을 클릭한다.



### 2. GitLab , Jira, MM 연동

> 여기서 Mattermost를 MM으로 약칭한다.

#### 2.1 MM에서 Jira 구독

**2.1.1 push 채널 생성**

외부 push 메시지 수신을 위한 채널 생성

**2.1.2 Webhook 생성**

GitLab 연동을 위한 Webhook URL 생성

- MM `프로필 > 햄버거 메뉴 > 통합`을 클릭한다.
- `Incoming Webhook > 추가` 버튼을 클릭한다.
- 제목, 설명 등을 적절히 입력하고, 채널을 선택하고 `이 채널로 고정`을 체크한다.
- 생성된 URL을 복사한다. :point_left:

**2.1.3 Jira 구독**

생성한 push 채널에서 지라를 연결(connect)하고 이슈 구독(subscribe)을 설정

- MM 아무 채팅 창에서 아래의 슬래시 명령어 실행

  ```
  /jira connect
  ```

- Jira 로그인을 위한 Git 로그인 창에서 로그인을 진행한다.

- MM에서 Jira로 연결을 허용한다.

- 완료되면 창을 종료한다.

- 지라를 구독할 push 채널에서 아래의 슬래시 명령어 실행

  ```
  /jira subscribe
  ```

- `Create subscription` 버튼 클릭 후 구독 정보를 설정한다.

- 설정한 `변경사항(Events)`이 발생할 때 push 채널에 업데이트 된다.

- :white_check_mark: `/jira help` 슬래시 명령어로 할 수 있는 추가기능도 확인할 수 있다.



#### 2.2 GitLab에서 Jira/MM 연동

> GitLab과 Jira가 연동이 완료되면 GitLab의 commit 정보가 Jira 이슈에 연결 되고 MR이 닫힐 때 이슈도 같이 완료된다.

**2.2.1 GitLab에서 Jira 연동**

- `GitLab Repository > 설정 > Integrations > Project services > Jira > Active`를 체크한다.
- 상세정보를 설정한다.
  - Web URL에 JIRA 사이트 url을 입력한다.
    - :white_check_mark: Web URL을 입력할 때 url 끝에 `/(슬래시)`를 입력하지 않도록 주의한다.
  - username/password에 로그인 정보를 설정한다.
  - `Transition ID`를 입력한다. (예시: 11,21,31)
    - Transition ID는 JIRA에 사전 정의된 이슈 상태 변경 코드로 사용된다.
  - 저장하고 테스트 `Success` 메시지를 확인한다.

**2.2.2 GitLab에서 MM 연동**

- 위와 같이 MM의 Integration 설정에서도 Active를 체크한다.
- Webhook URL에 MM에서 생성한 주소를 복사하여 입력한다. :point_left:
- 저장하고 Success 메시지를 확인한다.



### 3. Vue Frameworks

#### 3.1 Vue Bootstrap

> [Vue Bootstrap 사이트](https://bootstrap-vue.org/docs)

- 먼저 패키지를 설치한다.

  ```bash
  npm install vue bootstrap bootstrap-vue
  ```

- `main.js`에 bootstrap을 등록해준다.

  ```javascript
  import Vue from 'vue'
  import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
  
  // Import Bootstrap an BootstrapVue CSS files (order is important)
  import 'bootstrap/dist/css/bootstrap.css'
  import 'bootstrap-vue/dist/bootstrap-vue.css'
  
  // Make BootstrapVue available throughout your project
  Vue.use(BootstrapVue)
  // Optionally install the BootstrapVue icon components plugin
  Vue.use(IconsPlugin)
  ```

  

#### 3.2 Vue Glide

> [Vue Glide 사이트](https://www.npmjs.com/package/vue-glide-js)

- 먼저 패키지를 설치한다.

  ```bash
  npm i vue-glide-js
  ```

- `main.js`에 bootstrap을 등록해준다.

  ```javascript
  import Vue from 'vue'
  import App from './App.vue'
  import VueGlide from 'vue-glide-js'
  import 'vue-glide-js/dist/vue-glide.css'
   
  Vue.use(VueGlide)
   
  new Vue({
    el: '#app',
    render: h => h(App)
  })
  ```




#### 3.3 Vue Burger Menu

> [Github 페이지](https://github.com/mbj36/vue-burger-menu)

- 먼저 패키지를 설치한다.

  ```bash
  npm install vue-burger-menu --save
  ```

- 기본적으로 페이지의 `<script>` 태그에서 아래와 같이 입력하면 사용할 수 있다.

  ```javascript
  import { Slide } from 'vue-burger-menu'  // import the CSS transitions you wish to use, in this case we are using `Slide`
  
  export default {
      components: {
          Slide // Register your component
      }
  }
  ```

- 위의 Github 페이지에서 다양한 사용법을 확인할 수 있다.



### 4. 프론트 component/view 작성

> 오늘은 `회원가입/로그인` 그리고 메인페이지 등 시작되는 페이지들을 위주로 컴포넌트와 뷰를 구분하고, 해당 페이지들에서 요청하거나 보내는 데이터들을 정리하였다.

- 먼저 tree는 다음과 같다.

![0121_account_and_main_tree](img/0121_account_and_main_tree.jpg)

- 데이터는 다음과 같다.

![0121_account_and_main_data](img/0121_account_and_main_data.png)



## 정리	

- 내일도 적절하게 협업 및 분업으로 프로젝트를 진행한다.



## :hand: 다음과제

- 각 파트 `JIRA` 참조
