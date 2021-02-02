<template>
  <div id="box">
     <b-form class="mt-5 pt-5" @submit="onSubmit">
      <!-- 1. 아이디 -->
      <b-row id="accountBox" align-h="left">
        <b-col cols="2"><span>아이디</span></b-col>
        <b-col cols="5">
          <b-form-input
            id="input-1"
            type="email"
            :placeholder="credentials.userId"
            style="text-align: center;"
            disabled
          ></b-form-input>
        </b-col>
      </b-row>
      <!-- 2. 닉네임 -->
      <b-row id="accountBox" align-h="left">
        <b-col cols="2"><span>닉네임</span></b-col>
        <b-col cols="5">
          <b-form-input
            id="input-1"
            type="email"
            :placeholder="credentials.nickname"
            style="text-align: center;"
            disabled
          ></b-form-input>
        </b-col>
        <!-- <small id = "error2" class="text-danger" style="float:left; margin-top:5px">{{
              errors[0]
            }}</small> -->
      </b-row>
      <!-- 3. 이메일 -->
      <b-row id="accountBox" align-h="left">
        <b-col cols="2"><span>이메일</span></b-col>
        <b-col cols="5">
          <b-form-input
            id="input-1"
            type="email"
            :placeholder="credentials.email"
            style="text-align: center;"
            disabled
          ></b-form-input>
        </b-col>
      </b-row>
      <!-- 4. 동네 설정 -->
      <b-row id="accountBox" align-h="left">
        <b-col cols="2"><span>우리동네</span></b-col>
        <b-col cols="5" class="font-weight-bold">
          {{ dong }}
        </b-col>
        <b-col cols="4" align-self="left">
          <!-- <b-button class="mx-3" variant="info" @click="createGroup">수정</b-button> -->
          <!-- <b-button class="mx-3" variant="danger" @click="createGroup">삭제</b-button> -->
        </b-col>
      </b-row>
      <!-- <b-col class="mb-5" cols="10"><b-button class="mx-3" variant="info" @click="createGroup">추가</b-button></b-col> -->
      <br>
      <br>
      <div>
        <b-button class="mx-3" variant="info" >비밀번호 변경</b-button>
        <b-button class="mx-3" variant="info" >회원정보 수정</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
//const SERVER_URL = "http://localhost:8000";
export default {
  name: 'AccountDetail',
  data: function () {
    return {
      dong: '역삼동',
      credentials: {
        userId: "ssafy10",
        nickname: "송송",
        email: "bulgen@naver.com",
      },
    }
  },
  methods: {
    getToken: function () {
      const token = localStorage.getItem('jwt')
      const config = {
        headers: {
          Authorization: `JWT ${token}`
        }
      }
      return config
    },
    onSubmit(evt) {
      evt.preventDefault()
      alert(JSON.stringify(this.article))
    },
  }
}
</script>

<style>
#accountBox {
  margin-left: 30px;
  /* margin-right: 30px; */
  margin-bottom: 30px;
}
#box {
}
</style>