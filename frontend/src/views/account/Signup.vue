// 중복확인 하는 법 모르겠음
// 저렇게 중복확인 함수로 두면 submit 됐을때 b-form-group확인해주는거에서 안넘어가는듯
<template>
  <div id="box"  style="padding:10px;">
    <!-- class="container" -->
    <br>
    <h2>회원가입</h2>
    <hr>
    <b-form @submit.prevent="onSubmit">
      
      <div class="container" style="padding:10px">
        <ValidationProvider name="아이디" rules="required|min:5" v-slot="{ errors }">
          <b-form-group>
            <label style="float:left; padding-right:10px; padding-top:5px" for="user_id">아이디: </label>
            <b-form-input
              type="text"
              style="width:60%; float:left;"
              v-model="credentials.user_id"
              placeholder="아이디 입력"      
              required
            ></b-form-input>
            <b-button style= "margin-top:3px" size="sm" @click="check_user_id" type="submit">확인</b-button>
            <small class="text-danger" style="float:left; margin-top:5px">{{ errors[0] }}</small>
          </b-form-group>
          
        </ValidationProvider>
      </div>

      <div class="container" style="padding:10px">
        <ValidationProvider name="닉네임" rules="required|min:2" v-slot="{ errors }">
          <b-form-group>
            <label style="float:left; padding-right:10px; padding-top:5px" for="nickname">닉네임: </label>
            <b-form-input
              type="text"
              style="width:60%; float:left;"
              v-model="credentials.nickname"
              placeholder="닉네임 입력"
              required
            ></b-form-input>
            <b-button style= "margin-top:3px" size="sm" @click="check_nickname" type="submit">확인</b-button>
            <small class="text-danger" style="float:left; margin-top:5px">{{ errors[0] }}</small>
          </b-form-group>
        </ValidationProvider>
      </div>

      <div class="container" style="padding:10px">
        <ValidationProvider name="이메일" rules="required|email" v-slot="{ errors }">
          <b-form-group>
            <label style="float:left; padding-right:10px; padding-top:5px" for="email">이메일: </label>
            <b-form-input
              type="email"
              style="width:60%; float:left;"
              v-model="credentials.email"
              placeholder="이메일 입력"      
              required
            ></b-form-input>
            <small class="text-danger" style="float:left; margin-top:5px">{{ errors[0] }}</small>
          </b-form-group>
        </ValidationProvider>
      </div>

      <div class="container" style="padding:10px">
        <ValidationProvider name="비밀번호" rules="required|min:6" v-slot="{ errors }">
          <b-form-group>
            <label style="float:left; padding-right:10px; padding-top:5px" for="password">비밀번호: </label>
            <b-form-input
              type="password"
              style="width:60%; float:left;"
              v-model="credentials.password"
              placeholder="비밀번호 입력"      
              required
            ></b-form-input>
            <small class="text-danger" style="float:left; margin-top:5px">{{ errors[0] }}</small>
          </b-form-group>
        </ValidationProvider>
      </div>

      <div class="container" style="padding:10px">
        <ValidationProvider name="비밀번호 확인" rules="required|confirmed:비밀번호" v-slot="{ errors }">
          <b-form-group>
            <label style="float:left; padding-right:10px; padding-top:5px" for="password_confirmation">비밀번호 확인: </label>
            <b-form-input
              type="password"
              style="width:60%; float:left;"
              v-model="credentials.password_confirmation"
              placeholder="비밀번호 확인"      
              required
            ></b-form-input>
            <small class="text-danger" style="float:left; margin-top:5px">{{ errors[0] }}</small>
          </b-form-group>
        </ValidationProvider>
      </div>
      <b-button type="submit" class="btn btn-secondary" style="float:right;">가입완료</b-button>
    </b-form>
    <button class="btn btn-secondary" style="float:left;" @click="$router.push('Login')">뒤로가기</button>
  </div>
</template>


<script>
  import axios from 'axios'
  import { ValidationProvider } from 'vee-validate';
  const SERVER_URL = process.env.VUE_APP_SERVER_URL

  export default {
    components: {
      ValidationProvider,
    // extend,
    },
    name: 'Singup',
    data() {
      return {
        credentials: {
          user_id: '',
          nickname: '',
          email: '',
          password: '',
          password_confirmation: '',
        },
      }
    },
    methods: {
      async onSubmit() {
        try {
          const res = await axios.post(`${SERVER_URL}/account/signup/`, this.credentials)
          if (res.status === 201) {
            this.login()
          }
        } catch(err) {
          console.log(err)
        }
      },
      // check_user_id: function () {
      //   axios.post(`${SERVER_URL}/account/signup/`, this.credentials)
      //     .then((res) => {
      //       console.log(res)
      //       if (res.status === 201) {
      //         this.login()
      //       }
      //     })
      //     .catch((error) => {
      //       this.error_message = error.response.data
      //       console.log(error.response.data)
      //     })
      // },
      // check_nickname: function () {
      //   axios.post(`${SERVER_URL}/account/signup/`, this.credentials)
      //     .then((res) => {
      //       console.log(res)
      //       if (res.status === 201) {
      //         this.login()
      //       }
      //     })
      //     .catch((error) => {
      //       this.error_message = error.response.data
      //       console.log(error.response.data)
      //     })
      // },
      login: function () {
        axios.post(`${SERVER_URL}/account/api-token-auth/`, this.credentials)
          .then((res) => {
            localStorage.setItem('jwt', res.data.token)
            this.$emit('login')
            this.$router.push({ name: 'FindLocation' })
          })
          .catch((err) => {
            console.log(err)
          })
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