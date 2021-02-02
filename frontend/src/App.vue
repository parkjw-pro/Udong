<template>
<div id="app" class="pb-5">
    <div v-if="login">
      <Navbar/>
      <ReviewCreate/>
    </div>
     <!-- <router-view><router-view/>    -->
  </div>
</template>

<script>
// import Home from '@/views/app/Home'
// import Login from '@/views/account/Login'
 import Navbar from '@/components/app/Navbar'

// 임시
// import Location from '@/components/app/Location'
import ReviewCreate from '@/views/review/ReviewCreate'
import 'vue-select/dist/vue-select.css';


export default {
  name: 'App',
  components: {
    // Home,
    // Login,
     Navbar,

    // Location,
    ReviewCreate,
  },
  data: function () {
    return {
      login: false,
    }
  },
  methods: {
    logout: function () {
      localStorage.removeItem('jwt')  // localStorage에서 JWT 지우기
      this.login = false
      // this.$router.push({ name: 'Login' })
    }
  },
  
  created(){
    console.log(localStorage.getItem('auth-token'));  // localStorage에서 JWT 받아오기
    console.log(this.login)
    if (localStorage.getItem('auth-token') != undefined) {
        this.login = true
    }
     console.log(this.login)
  }
}
</script>

<style>
/* 1번 폰트 - KoPub Batang */
@import url(//fonts.googleapis.com/earlyaccess/kopubbatang.css);
/* 2번 폰트 - Hanna */
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
/* 3번 폰트 - Jeju Gothic */
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);

#app {
  /* font-family: 'KoPub Batang', serif; */
  /* font-family: 'Hanna', sans-serif; */
  font-family: 'Jeju Gothic', sans-serif;
  /* 기본설정 */
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}


/* --------------------------------------------------하위 컴포넌트에서 사용-------------------------------------------------- */

/* 페이지를 담는 박스 */
#box {
  display: block;
  width: 50%;
  /* height: 50%; */
  position: absolute;
  left: 25%;
  margin-top: 5%;
  padding-bottom: 15%;
}


</style>
