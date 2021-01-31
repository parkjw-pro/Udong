<template>
  <div>
    <div class="mb-5">
      <img alt="Vue logo" src="@/assets/logo.png" style="width:20%; margin-bottom: 20px;">
      <h5>우리 동네 이야기</h5>
    </div>
    <div class="form-group">
      <input type="text" class="form-control" style="width:20%; text-align:center; display:block; margin: 0 auto;" placeholder="ID" id="user_id" v-model="credentials.user_id" @keypress.enter="login">
    </div>
    <div class="form-group">
      <input type="password" class="form-control" style="width:20%; text-align:center; display:block; margin: 0 auto;" placeholder="Password" id="password" v-model="credentials.password" @keypress.enter="login">
    </div>
    <div v-if="!error_check_login">
      <p class="text-danger small">ID 또는 비밀번호를 다시 확인해주세요.</p>
    </div>
    <div>
      <button @click="login" type="submit" class="btn btn-secondary mb-2" style="width:20%;">Login</button>
    </div>
    <div class="text-decoration: none;">
      <!-- class="small" -->
      <router-link :to="{ name: 'Signup' }" style="color: black; textDecoration: 'none'">회원가입</router-link>
      |
      <router-link :to="{ name: 'FindPassword' }" style="color: black; textDecoration: 'none'">비밀번호찾기</router-link>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'Login',
  data: function () {
    return {
      credentials: {
      user_id: '',
      password: '',
      },
      error_check_login: true,
    }
  },
  methods: {
    login: function () {
      axios.post(`${SERVER_URL}/account/api-token-auth/`, this.credentials)
        .then((res) => {
          localStorage.setItem('jwt', res.data.token)
          this.$emit('login')
          // this.$router.push({ name: 'Initial'})
        })
        .catch(() => {
          this.error_check_login = false
        })
    }
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