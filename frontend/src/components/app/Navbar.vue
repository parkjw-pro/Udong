<template>
  <div id="navbar">
    <!-- 1. Navbar --> <!-- variant="faded" -->
    <b-navbar class="pl-5 mt-3" toggleable="sm" type="light" variant="faded">
      <!-- 1.1 Navbar Logo -->
      <b-navbar-brand href="#" @click="toHome">
        <img src="@/assets/logo.png" alt="우동" style="width: 60px; height: 60px;"> 은
      </b-navbar-brand>
      <!-- 1.2 Navbar dropdowns -->
      <b-navbar-brand href="#">
        <b-navbar-nav>
          <b-nav-item-dropdown text="역삼동" class="px-0 mt-1 d-inline">
            <b-dropdown-item href="#" disabled>역삼동</b-dropdown-item>
            <b-dropdown-item href="#">신림동</b-dropdown-item>
            <b-dropdown-item href="#">다른 동네 구경하기</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-navbar-brand>
    </b-navbar>
    
    
    <!-- 2. 햄버거메뉴 -->
    <component :is="currentMenu" :right="side === 'right' ? true: false" >
        <b-row class="pl-0" id="option_v2">
          <b-col><a href=""><Profile2 style="width: 60%;" /></a></b-col>
          <b-col>
            <b-row>이송영님</b-row>
            <b-row>안녕하세요!</b-row>
          </b-col>
        </b-row>
        <hr>
        <b-row id="option_v1" class="pl-0" @click="toReview">
            <!-- <i class="fas fa-book-open"></i> -->
            <b-col id="option_v2">우리동네 리뷰</b-col>
        </b-row>
        <b-row id="option_v1" class="pl-0" @click="toNews">
            <!-- <i class="far fa-newspaper"></i> -->
            <b-col id="option_v2" style="">우리동네 소식</b-col>
        </b-row>
        <!-- <router-link :to="{ name: 'NewsFeed' }" class="text-white"><p class="h5 mt-4" id="option_v2">뉴스피드</p></router-link> -->
        <b-row id="option_v1" class="pl-0" @click="toStory">
            <!-- <i class="fas fa-globe-americas"></i> -->
            <b-col id="option_v2">우리동네 이야기</b-col>
        </b-row>
        <b-row id="option_v1" class="pl-0" @click="toMyfeed">
            <!-- <i class="fas fa-star"></i> -->
            <b-col id="option_v2">내 피드</b-col>
        </b-row>
        <b-row id="option_v1" class="pl-0" @click="arrowToggle()" align-h="justify">
            <!-- <i class="fas fa-cog"></i> -->
            <b-col id="option_v2">설정</b-col>
            <b-col>
              <b-icon v-if="!toggle" icon="chevron-down" variant="dark"></b-icon>
              <b-icon v-else icon="chevron-up" variant="dark"></b-icon>
            </b-col>
            <!-- dropdown 표시하기 -->
        </b-row>
        <b-col v-if="toggle" class="small" id="option_v3" @click="toAccountDetail">개인정보</b-col>
        <b-col v-if="toggle" class="small" id="option_v3" @click="logout">로그아웃</b-col>
        <b-col v-if="toggle" class="small" id="option_v3" @click="toDevelopers">개발진</b-col>
    </component>
  </div>
</template>

<script>
import slide from './slide';
import Menu from '@/views/app/Menu';

import Profile2 from '@/components/app/Profile2'


export default {
  name: 'Navbar',
  components: {
    slide,
    Menu,
    Profile2,
  },
  data: function () {
    return {
      side: 'right',
      currentMenu: 'slide',
      isToggled: false,
    }
  },
  computed: {
    toggle: function () {
      return this.isToggled
    }
  },
  methods: {
    toHome: function () {
      this.$router.push({name: 'Home'})
    },
    toReview: function () {
      this.$router.push({name: 'ReviewHome'})
    },
    toNews: function () {
      this.$router.push({name: 'NewsHome'})
    },
    toStory: function () {
      this.$router.push({name: 'NewsFeed'})
    },
    toMyfeed: function () {
      this.$router.push({name: 'MyFeed'})
    },
    toAccountDetail: function () {
      this.$router.push({name: 'AccountDetail'})
    },
    logout: function () {
      this.$store
        .dispatch("LOGOUT")
        .then(() => {
          // this.$router.push({ name: 'Home' })
          this.$router.push({ name: 'Login'})
        })
        .catch(({ message }) => (this.msg = message));
    },
    toDevelopers: function () {
      this.$router.push({name: 'Developers'})
    },
    arrowToggle() {
      this.isToggled = !this.isToggled;
    }
  },
}
</script>

<style lang="less">
    #navbar {
      height: 100%;
    }

    #option_v1 {
      cursor: pointer;
    }

    // 햄버거 메뉴 글자
    #option_v2 {
      color: #666666;
      // overflow-x: hidden;
      white-space: pre;
      font-weight: bold;
      text-align: left;
    }

    #option_v3 {
      text-align: left;
      color: #666666;
      cursor: pointer;
    }

    // 햄버거 메뉴 항목 색상
    // a {
    //   color: #4e4a46;
    //   text-decoration: none;

    //   &:hover,
    //   &:focus {
    //     color: #c94e50;
    //   }
    // }

    //
    // Burger menu custom styles
    //
    .bm-burger-button {
      position: absolute;
      width: 36px;
      height: 30px;
      right: 36px;
      top: 36px;
    }

    .right .bm-burger-button {
      left: initial;
      right: 36px;
    }

    .bm-burger-bars {
      background: #373a47;
    }

    .bm-morph-shape {
      fill: #373a47;
    }

    // 햄버거 메뉴 배경 색상
    .bm-menu {
      background: #ffffff;

      // a {
      //   color: #b8b7ad;
      //   text-decoration: none;
      //   &:hover,
      //   &:focus {
      //     // on_cursor 메뉴 색상 변화
      //     // color: #c94e50;
      //     color: black;
      //   }
      // }
    }
</style>
