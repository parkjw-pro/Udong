<template>
  <div id="box">
    <div class="mb-5">
      <img alt="Vue logo" src="@/assets/logo.png" style="width:40%; margin-bottom: 20px;" />
      <h2
        class="font-weight-bold"
        style="color: #695549; font-family: 'Nanum Pen Script', cursive;"
      >
        우리 동네 이야기
      </h2>
    </div>
    <div class="form-group">
      <input
        type="text"
        class="form-control"
        style="color: #695549; width:50%; text-align:center; display:block; margin: 0 auto;"
        placeholder="ID"
        id="userId"
        v-model="credentials.userId"
        @keypress.enter="login"
        autofocus
      />
    </div>
    <div class="form-group">
      <input
        type="password"
        class="form-control"
        style="color: #695549; width:50%; text-align:center; display:block; margin: 0 auto;"
        placeholder="Password"
        id="password"
        v-model="credentials.password"
        @keypress.enter="login"
      />
    </div>
    <div v-if="!error_check_login">
      <p>ID 또는 비밀번호를 다시 확인해주세요.</p>
    </div>
    <div>
      <b-button
        @click="login"
        type="submit"
        class="mb-2"
        style="width:50%; background-color: #695549;"
        >Login</b-button
      >
    </div>
    <div class="small">
      <!-- <router-link :to="{ name: 'Signup' }">회원가입</router-link>
      |
      <router-link :to="{ name: 'FindPassword' }">비밀번호찾기</router-link> -->
      <span style="color: #695549; cursor: pointer;" @click="toSignup">회원가입</span>
      |
      <span style="color: #695549; cursor: pointer;" @click="toFind">비밀번호찾기</span>
    </div>
  </div>
</template>

<script>
//import Axios from 'axios';
//const SERVER_URL = process.env.VUE_APP_SERVER_URL
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'Login',
  data: function() {
    return {
      credentials: {
        userId: '',
        password: '',
      },
      error_check_login: true,
    };
  },
  methods: {
    // login: function () {
    //   axios.post(`/user/confirm/login`, this.credentials)
    //     .then((res) => {
    //       localStorage.setItem('jwt', res.data.token)
    //       this.$emit('login')
    //       // this.$router.push({ name: 'Initial'})
    //     })
    //     .catch(() => {
    //       this.error_check_login = false
    //     })
    // }
    login: function() {
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch('LOGIN', this.credentials)
        .then(() => {
          // 나중에 getUser() 함수 사용하기!!!
          // location 정보가 있으면 Home으로 보내기!
          // const userAddress = JSON.parse(localStorage.getItem('Login-token'))["user_address"]
          // if (userAddress !== null) {
          //   location.replace('/home')
          // } else {
          //   this.$router.replace('/location')
          // }
          this.selectBadge();
          this.$router.replace('/location/first');
        })
        .catch(({ message }) => (this.msg = message));
    },
    toSignup: function() {
      this.$router.push({ name: 'Signup' });
    },
    toFind: function() {
      this.$router.push({ name: 'FindPassword' });
    },
    selectBadge() {
      console.log(this.credentials.userId);
      axios
        .get(`${SERVER_URL}/user/badge`, {
          params: {
            userId: this.credentials.userId,
          },
        })
        .then((response) => {
          console.log(response.data);
          // this.badgeList = response.data;
          // this.badgecount = response.data.length;
          const userInfo = JSON.parse(localStorage.getItem('Login-token'));
          userInfo.user_badge = response.data[0].badgeUrl;
          localStorage.setItem('Login-token', JSON.stringify(userInfo));
          console.log(userInfo.user_badge);
        });
    },
  },
  mounted() {
    if (localStorage.getItem('auth-token') != undefined) {
      this.$router.push({ name: 'Home' });
    }
  },
};
</script>

<style>
input[type='password'] {
  font: small-caption;
  font-size: 16px;
}

::placeholder {
  font-family: 'Jeju Gothic', sans-serif;
}
</style>
