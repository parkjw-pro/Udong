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
          ></b-form-input>
        </b-col>
        <b-col cols="2">
          <b-button
              style="margin-top:3px"
              size="sm"
              @click="verifyNickname"
              type="submit"
              >중복 확인</b-button
            >
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
          <b-button class="mx-3" variant="info" @click="createGroup">수정</b-button>
          <b-button class="mx-3" variant="danger" @click="createGroup">삭제</b-button>
        </b-col>
      </b-row>
      <b-col class="mb-5" cols="10"><b-button class="mx-3" variant="info" @click="createGroup">추가</b-button></b-col>
      <div>
        <b-button class="mx-3" variant="danger" @click="deleteAccount">회원탈퇴</b-button>
        <!-- 혹은 router-link 넣어주기!!! -->
        <b-button class="mx-3" variant="info" @click="toDetail">확인</b-button>
      </div>
    </b-form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'AccountUpdate',
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
    verifyNickname: function() {
       if(this.credentials.nickname == ""|| document.getElementById("error2").innerHTML != ""){
        alert("닉네임 다시입력 바랍니다.")
        this.credentials.nickname = "";
      }
      else{
        axios
          .get(`/user/nickname/${this.credentials.nickname}`)
          .then(() => {
            alert("사용 가능한 닉네임 입니다.");
            this.checkNickname = true;
          })
          .catch(() => {
            if (this.credentials.nickname != "") {
              alert("현재 사용중인 닉네임 입니다.");
              this.credentials.nickname = "";
            }
          });
      }
    },
    deleteAccount: function () {

    },
    toDetail: function () {

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