<template>
  <div id="navbar" class="pb-5 mb-5">
    <!-- 1. Navbar -->
    <!-- variant="faded" -->
    <b-navbar class="pl-5 mt-3" toggleable="sm" type="light" variant="faded" fixed="top">
      <!-- 1.1 Navbar Logo -->
      <b-navbar-brand href="#" @click="toHome" style="color: #695549;">
        <img src="@/assets/logo2.png" alt="우동" style="width: 60px; height: 60px;" /> 은
      </b-navbar-brand>
      <!-- 1.2 Navbar dropdowns -->
      <b-navbar-brand href="#">
        <b-navbar-nav>
          <b-nav-item-dropdown :text="user.dongName" variant="dark" class="px-0 mt-1 d-inline">
            <b-dropdown-item href="#" disabled>{{ user.dongName }}</b-dropdown-item>
            <!-- <b-dropdown-item href="#">신림동</b-dropdown-item> -->
            <b-dropdown-item href="#" @click="toFindLocation">다른 동네 구경하기</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-navbar-brand>

      <!-- Navbar 오른쪽 메뉴 -->
      <b-navbar-nav class="ml-auto mr-5 pr-4">
        <b-navbar-brand><small id="nav_content" @click="toReview">리뷰</small></b-navbar-brand>
        <b-navbar-brand><small id="nav_content" @click="toNews">소식</small></b-navbar-brand>
        <b-navbar-brand><small id="nav_content" @click="toStory">이야기</small></b-navbar-brand>
      </b-navbar-nav>

      <!-- 2. 햄버거메뉴 -->
      <component :is="currentMenu" :right="side === 'right' ? true : false">
        <b-row id="option_v2" align-h="center" class="px-0 py-0 my-0">
          <b-col @click="toBadge">
            <b-avatar
              variant="info"
              :src="require(`@/assets/app/badge/${this.badge}.jpg`)"
              style="cursor: pointer;"
            ></b-avatar>
            {{ user.nickname }}님 <small>안녕하세요!</small>
          </b-col>
        </b-row>
        <hr />
        <b-row id="option_v1" class="pl-3" @click="toReview">
          <!-- <i class="fas fa-book-open"></i> -->
          <b-col id="option_v2"><h2>우리동네 리뷰</h2></b-col>
        </b-row>
        <b-row id="option_v1" class="pl-3" @click="toNews">
          <!-- <i class="far fa-newspaper"></i> -->
          <b-col id="option_v2"><h2>우리동네 소식</h2></b-col>
        </b-row>
        <!-- <router-link :to="{ name: 'NewsFeed' }" class="text-white"><p class="h5 mt-4" id="option_v2">뉴스피드</p></router-link> -->
        <b-row id="option_v1" class="pl-3" @click="toStory">
          <!-- <i class="fas fa-globe-americas"></i> -->
          <b-col id="option_v2"><h2>우리동네 이야기</h2></b-col>
        </b-row>
        <b-row id="option_v1" class="pl-3" @click="toMyfeed">
          <!-- <i class="fas fa-star"></i> -->
          <b-col id="option_v2"><h3>내 피드</h3></b-col>
        </b-row>
        <b-row id="option_v1" class="pl-3" @click="arrowToggle()" align-h="justify">
          <!-- <i class="fas fa-cog"></i> -->
          <b-col id="option_v2"><b-icon icon="gear"></b-icon></b-col>
          <b-col class="pr-0">
            <b-icon
              v-if="!toggle"
              icon="chevron-down"
              variant="dark"
              style="text-align: right;"
            ></b-icon>
            <b-icon v-else icon="chevron-up" variant="dark" style="text-align: right;"></b-icon>
          </b-col>
          <!-- dropdown 표시하기 -->
        </b-row>
        <b-col v-if="toggle" class="small" id="option_v3" @click="toBadge">뱃지</b-col>
        <b-col v-if="toggle" class="small" id="option_v3" @click="toAccountDetail">개인정보</b-col>
        <b-col v-if="toggle" class="small" id="option_v3" @click="logout">로그아웃</b-col>
        <b-col v-if="toggle" class="small" id="option_v3" @click="toDevelopers">개발진</b-col>
        <b-col v-if="toggle && user.isManager === 1" class="small" id="option_v3" @click="toAdmin"
          >관리자페이지</b-col
        >
      </component>
    </b-navbar>
  </div>
</template>

<script>
import axios from 'axios';
import slide from './slide';
import Menu from '@/views/app/Menu';

import Profile2 from '@/components/app/Profile2';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'Navbar',
  components: {
    slide,
    Menu,
    Profile2,
  },
  data: function() {
    return {
      // user 관련 정보
      user: {
        userId: '',
        nickname: '',
        address: '',
        dongName: '역삼동',
        isManager: false,
      },
      badgeList: {},
      badge: '',

      currentMenu: 'slide',
      isToggled: false,
      side: 'right',
    };
  },
  computed: {
    toggle: function() {
      return this.isToggled;
    },
  },
  methods: {
    toAdmin: function() {
      this.$router.push({ name: 'AdminHome' });
    },
    toBadge: function() {
      this.$router.push({ name: 'Badge', params: { userId: this.user.userId } });
    },
    toFindLocation: function() {
      this.$router.push({ name: 'FindLocation' });
    },
    toHome: function() {
      // this.$router.push({name: 'Home'})
      location.replace('/home');
    },
    toReview: function() {
      // this.$router.push({name: 'ReviewHome'})
      location.replace('/review');
    },
    toNews: function() {
      this.$router.push({ name: 'NewsHome' });
      // location.reload(true)
    },
    toStory: function() {
      // location.replace(`/story/${this.user.address}/${this.user.userId}`)
      this.$router.push({
        name: 'NewsFeed',
        params: { address: this.user.address, userId: this.user.userId },
      });
      // location.reload(true)
    },
    toMyfeed: function() {
      this.$router.push({
        name: 'MyFeed',
        params: { userId: this.user.userId, nickname: this.user.nickname },
      });
      // location.reload(true)
    },
    toAccountDetail: function() {
      this.$router.push({ name: 'AccountDetail' });
      location.reload(true);
    },
    logout: function() {
      this.$store
        .dispatch('LOGOUT')
        .then(() => {
          // this.$router.push({ name: 'Home' })
          this.$router.push({ name: 'Login' });
        })
        .catch(({ message }) => (this.msg = message));
    },
    toDevelopers: function() {
      this.$router.push({ name: 'Developers' });
      location.reload(true);
    },
    arrowToggle() {
      this.isToggled = !this.isToggled;
    },
    searchAddr: function(dongCode) {
      axios
        .get(`${SERVER_URL}/area/code/${dongCode}`)
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // selectBadge() {
    //   console.log(this.user.userId);
    //   axios
    //     .get(`${SERVER_URL}/user/badge`, {
    //       params: {
    //         userId: this.user.userId,
    //       },
    //     })
    //     .then((response) => {
    //       console.log(response.data);
    //       this.badgeList = response.data;
    //       this.badgecount = response.data.length;
    //       console.log(this.badgeList[0].badgeUrl);
    //     });
    // },
  },
  created() {
    const user = JSON.parse(localStorage.getItem('Login-token'));
    this.user.userId = user['user-id'];
    this.user.nickname = user['user-name'];
    this.user.isManager = user['is-manager'];
    this.user.address = user['user_address'];
    const userInfo = JSON.parse(localStorage.getItem('Login-token'));
    this.badge = userInfo.user_badge;
  },
  mounted() {
    var user_address_name = JSON.parse(localStorage.getItem('Login-token'))['user_address_name'];
    if (user_address_name) {
      this.user.dongName = user_address_name;
    } else {
      this.user.dongName = '역삼동';
    }
  },
};
</script>

<style lang="less">
#navbar {
  height: 100%;
}

#nav_content {
  cursor: pointer;
}

#option_v1 {
  cursor: pointer;
  font-family: 'Nanum Pen Script', cursive;
}

// 햄버거 메뉴 글자
#option_v2 {
  color: #695549;
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
