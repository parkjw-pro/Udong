<template>
  <div>
    <div class="mb-5">
      <img alt="Vue logo" src="@/assets/logo.png" style="width:20%; margin-bottom: 20px;">
      <h5>우리 동네 이야기</h5>
    </div>
    <div class="form-group">
      <input type="text" class="form-control" style="width:50%; text-align:center; display:block; margin: 0 auto;" placeholder="ID" id="userId" v-model="credentials.userId" @keypress.enter="login" autofocus>
    </div>
    <div class="form-group">
      <input type="password" class="form-control" style="width:50%; text-align:center; display:block; margin: 0 auto;" placeholder="Password" id="password" v-model="credentials.password" @keypress.enter="login">
    </div>
    <div v-if="!error_check_login">
      <p>ID 또는 비밀번호를 다시 확인해주세요.</p>
    </div>
    <div>
      <button @click="login" type="submit" class="btn btn-secondary mb-2" style="width:50%;">Login</button>
    </div>
    <div class="small">
      <!-- <router-link :to="{ name: 'Signup' }">회원가입</router-link>
      |
      <router-link :to="{ name: 'FindPassword' }">비밀번호찾기</router-link> -->
      <a href="" @click="toSignup">회원가입</a>
      |
      <a href="" @click="toFind">비밀번호찾기</a>
    </div>
  </div>
    
</template>

<script>
//import Axios from 'axios';
//const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'Login',
  data: function () {
    return {
      credentials: {
        userId: '',
        password: '',
      },
      error_check_login: true,
    }
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
        .dispatch("LOGIN", this.credentials)
        .then(() => {
          // this.$router.push({ name: 'Home' })
          this.$router.replace('/location')
        })
        .catch(({ message }) => (this.msg = message));
    },
    toSignup: function () {
      this.$router.push({ name: 'Signup'})
    },
    toFind: function () {
      this.$router.push({ name: 'FindPassword'})
    },
  }
}
</script>

<style>
input[type="password"] {  
  font:small-caption;font-size:16px;
}
::placeholder {
  font-family: 'Jeju Gothic', sans-serif;
}
</style>