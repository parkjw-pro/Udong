<template>
  <div id="navbar">
    <!-- 1. Navbar --> <!-- variant="faded" -->
    <b-navbar class="pl-5 mt-3" toggleable="sm" type="light" variant="faded">
      <!-- 1.1 Navbar Logo -->
      <b-navbar-brand href="#" @click="toHome" style="color: #695549;">
        <img src="@/assets/logo.png" alt="우동" style="width: 60px; height: 60px;"> 은
      </b-navbar-brand>
      <!-- 1.2 Navbar dropdowns -->
      <b-navbar-brand href="#">
        <b-navbar-nav>
          <b-nav-item-dropdown :text="user.dongName" class="px-0 mt-1 d-inline">
            <b-dropdown-item href="#" disabled>{{ user.dongName }}</b-dropdown-item>
            <!-- <b-dropdown-item href="#">신림동</b-dropdown-item> -->
            <b-dropdown-item href="#" @click="toFindLocation">다른 동네 구경하기</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-navbar-brand>
    </b-navbar>
    
    <!-- 2. 햄버거메뉴 -->
    <component :is="currentMenu" :right="side === 'right' ? true: false" >
        <b-row class="pl-0" id="option_v2" align-h="center">
          <b-col cols="4" @click="toBadge">
            <Profile2 style="width: 80%; cursor: pointer;" />
          </b-col>
          <b-col cols="6">
            <b-row><span style="color: #695549;">{{ user.nickname }}</span>님</b-row>
            <b-row><small>안녕하세요!</small></b-row>
          </b-col>
        </b-row>
        <hr>
        <b-row id="option_v1" class="pl-0" @click="toReview">
            <!-- <i class="fas fa-book-open"></i> -->
            <b-col id="option_v2"><h2>우리동네 리뷰</h2></b-col>
        </b-row>
        <b-row id="option_v1" class="pl-0" @click="toNews">
            <!-- <i class="far fa-newspaper"></i> -->
            <b-col id="option_v2"><h2>우리동네 소식</h2></b-col>
        </b-row>
        <!-- <router-link :to="{ name: 'NewsFeed' }" class="text-white"><p class="h5 mt-4" id="option_v2">뉴스피드</p></router-link> -->
        <b-row id="option_v1" class="pl-0" @click="toStory">
            <!-- <i class="fas fa-globe-americas"></i> -->
            <b-col id="option_v2"><h2>우리동네 이야기</h2></b-col>
        </b-row>
        <b-row id="option_v1" class="pl-0" @click="toMyfeed">
            <!-- <i class="fas fa-star"></i> -->
            <b-col id="option_v2"><h3>내 피드</h3></b-col>
        </b-row>
        <b-row id="option_v1" class="pl-0" @click="arrowToggle()" align-h="justify">
            <!-- <i class="fas fa-cog"></i> -->
            <b-col id="option_v2"><b-icon icon="gear"></b-icon></b-col>
            <b-col>
              <b-icon v-if="!toggle" icon="chevron-down" variant="dark"></b-icon>
              <b-icon v-else icon="chevron-up" variant="dark"></b-icon>
            </b-col>
            <!-- dropdown 표시하기 -->
        </b-row>
        <b-col v-if="toggle" class="small" id="option_v3" @click="toAccountDetail">개인정보</b-col>
        <b-col v-if="toggle" class="small" id="option_v3" @click="logout">로그아웃</b-col>
        <b-col v-if="toggle" class="small" id="option_v3" @click="toDevelopers">개발진</b-col>
        <b-col v-if="toggle && user.isManager === 1" class="small" id="option_v3" @click="toAdmin">관리자페이지</b-col>
    </component>
  </div>
</template>

<script>
import axios from 'axios'
import slide from './slide';
import Menu from '@/views/app/Menu';

import Profile2 from '@/components/app/Profile2'

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'Navbar',
  components: {
    slide,
    Menu,
    Profile2,
  },
  data: function () {
    return {
      // user 관련 정보
      user: {
        userId: '',
        nickname: '',
        address: '',
        dongName: '역삼동',
        isManager: false,
      },

      currentMenu: 'slide',
      isToggled: false,
      side: 'right',
    }
  },
  computed: {
    toggle: function () {
      return this.isToggled
    },
  },
  methods: {
    toAdmin: function () {
      this.$router.push({ name: 'AdminHome' })
    },
    toBadge: function () {
      this.$router.push({ name: 'Badge', params: { userId: this.user.userId } })
    },
    toFindLocation: function () {
      this.$router.push({ name: 'FindLocation'})
    }, 
    toHome: function () {
      // this.$router.push({name: 'Home'})
      location.replace('/home')
    },
    toReview: function () {
      this.$router.push({name: 'ReviewHome'})
    },
    toNews: function () {
      this.$router.push({name: 'NewsHome'})
    },
    toStory: function () {
      this.$router.push({name: 'NewsFeed', params: {address: this.user.address, userId: this.user.userId}})
    },
    toMyfeed: function () {
      this.$router.push({name: 'MyFeed', params: { userId: this.user.userId}})
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
    },
    searchAddr: function (dongCode) {
      axios.get(`${SERVER_URL}/area/code/${dongCode}`)
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
    }
  },
  created () {
    const user = JSON.parse(localStorage.getItem('Login-token'))
    this.user.userId = user["user-id"]
    this.user.nickname = user["nickname"]
    this.user.isManager = user["is-manager"]
    this.user.address = user["user_address"]
  },
  mounted() {
    var user_address_name = JSON.parse(localStorage.getItem('Login-token'))["user_address_name"]
    if (user_address_name) {
      this.user.dongName = user_address_name
    } else {
      this.user.dongName = '역삼동'
    }
  }
}
</script>

<style lang="less">
    #navbar {
      height: 100%;
    }

    #option_v1 {
      cursor: pointer;
      font-family: 'Nanum Pen Script', cursive;
    }

    // 햄버거 메뉴 글자
    #option_v2 {
      color: #695549;;
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
