<template>
  <div id="box">
    <div id="border" class="my-5 py-5">
      <div class="mb-5">
        <span><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%"></span>
        <span class="font-weight-bold" style="color: #695549;">우리동네 주민등록증</span>
      </div>
      <!-- 0. 동네 설정 -->
      <b-row id="accountBox" align-h="center">
        <b-col cols="2"><span id="title">우리동네</span></b-col>
        <b-col cols="5" class="font-weight-bold">
          {{ dong }}
        </b-col>
      </b-row>
      <!-- 1. 아이디 -->
      <b-row id="accountBox" align-h="center">
        <b-col cols="2"><span id="title">아이디</span></b-col>
        <b-col cols="5">
          <b-form-input
            type="email"
            :placeholder="user.userId"
            style="text-align: center;"
            disabled
          ></b-form-input>
        </b-col>
      </b-row>
      <!-- 2. 닉네임 -->
      <b-row id="accountBox" align-h="center">
        <b-col cols="2"><span id="title">닉네임</span></b-col>
        <b-col cols="5">
          <b-form-input
            type="email"
            :placeholder="user.nickname"
            style="text-align: center;"
            disabled
          ></b-form-input>
        </b-col>
        <!-- <small id = "error2" class="text-danger" style="float:left; margin-top:5px">{{
              errors[0]
            }}</small> -->
      </b-row>
      <!-- 3. 이메일 -->
      <b-row id="accountBox" align-h="center">
        <b-col cols="2"><span id="title">이메일</span></b-col>
        <b-col cols="5">
          <b-form-input
            type="email"
            :placeholder="user.email"
            style="text-align: center;"
            disabled
          ></b-form-input>
        </b-col>
      </b-row>
      
      <!-- <b-col class="mb-5" cols="10"><b-button class="mx-3" variant="info" @click="createGroup">추가</b-button></b-col> -->
      <img class="my-3" alt="Vue logo" src="@/assets/account/stamp.png" style="width: 15%">
    </div>
    <b-button class="my-3" style="background-color: #695549;" @click="toUpdate">회원정보 수정</b-button>
  </div>
</template>

<script>
// import axios from 'axios'

// const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'AccountDetail',
  data: function () {
    return {
      dong: '역삼동',
      user: {
        userId: "",
        nickname: "",
        email: "",
      },
    }
  },
  methods: {
    toUpdate: function () {
      this.$router.push({name: 'AccountUpdate'})
    }
  },
  mounted() {
    const userInfo = JSON.parse(localStorage.getItem('Info-token'))
    this.user.userId = userInfo["userId"]
    this.user.nickname = userInfo["nickname"]
    this.user.email = userInfo["email"]
  }
}
</script>

<style>
#accountBox {
  margin-left: 30px;
  /* margin-right: 30px; */
  margin-bottom: 30px;
}

#border {
  border: 3px dotted #695549;
  /* opacity: 0.8; */
}

#title {
  /* font-weight: bold; */
}
</style>