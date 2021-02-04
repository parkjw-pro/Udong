<template>
  <div id="app" class="pb-5">  
    <div v-if="login">
        <Navbar/>
    </div>
    <hr>
    <router-view></router-view>   
  </div>
</template>

<script>
<<<<<<< HEAD

 import Navbar from '@/components/app/Navbar'
=======
import Navbar from '@/components/app/Navbar'
>>>>>>> 8959c25c7abe4f8e48ee0050827f07f89a960d81
import 'vue-select/dist/vue-select.css';


export default {
  name: 'App',
  components: {

<<<<<<< HEAD
     
=======
     Navbar,
>>>>>>> 8959c25c7abe4f8e48ee0050827f07f89a960d81
  },
  data: function () {
    return {
      login: false,
    }
  },
  methods: {
  },
  created(){
    if (localStorage.getItem('auth-token') != undefined) {
        this.login = true
    }
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
