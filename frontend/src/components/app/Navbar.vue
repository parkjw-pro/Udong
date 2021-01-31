<template>
  <div id="navbar">
    <!-- 1. Navbar --> <!-- variant="faded" -->
    <b-navbar class="pl-5 mt-3" toggleable="sm" type="light" variant="faded">
      <!-- 1.1 Navbar Logo -->
      <b-navbar-brand href="#">
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
        <div class="pl-0" id="menu_options">
          <span><a href=""><Profile2 /></a></span>
          <span >
            이송영님
            <br>
            안녕하세요!
          </span>
        </div>
        <br>
        <hr>
        <a href="#" class="pl-0">
            <!-- <i class="fas fa-book-open"></i> -->
            <span id="menu_options">우리동네 리뷰</span>
        </a>
        <a href="#" class="pl-0">
            <!-- <i class="far fa-newspaper"></i> -->
            <span id="menu_options" style="">우리동네 소식</span>
        </a>
        <!-- <router-link :to="{ name: 'NewsFeed' }" class="text-white"><p class="h5 mt-4" id="menu_options">뉴스피드</p></router-link> -->
        <a href="#" @click="toStory" class="pl-0">
            <!-- <i class="fas fa-globe-americas"></i> -->
            <span id="menu_options">우리동네 이야기</span>
        </a>
        <a href="#" class="pl-0">
            <!-- <i class="fas fa-star"></i> -->
            <span id="menu_options">내 피드</span>
        </a>
        <a href="#" class="pl-0">
            <!-- <i class="fas fa-cog"></i> -->
            <span id="menu_options">설정</span>
            <!-- dropdown 표시하기 -->
            <!-- <span>
              <b-nav-item-dropdown text="설정" right>
                <b-dropdown-item href="#">Account</b-dropdown-item>
                <b-dropdown-item href="#">Settings</b-dropdown-item>
              </b-nav-item-dropdown>
            </span> -->
        </a>
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
      currentMenu: 'slide'
    }
  },
  methods: {
    toStory: function () {
      this.$router.push({name: 'NewsFeed'})
    }
  },
}
</script>

<style lang="less">
    #navbar {
      height: 100%;
    }

    // 햄버거 메뉴 글자
    #menu_options {
      color: #666666;
      // overflow-x: hidden;
      white-space: pre;
    }

    // 햄버거 메뉴 항목 색상
    a {
      color: #4e4a46;
      text-decoration: none;

      &:hover,
      &:focus {
        color: #c94e50;
      }
    }

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

      a {
        color: #b8b7ad;
        text-decoration: none;
        &:hover,
        &:focus {
          // on_cursor 메뉴 색상 변화
          // color: #c94e50;
          color: black;
        }
      }
    }
</style>
