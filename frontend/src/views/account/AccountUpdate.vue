<template>
  <div id="box">
    <div id="border" class="my-5 py-5">
      <div class="mb-5">
        <span><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%"></span>
        <span class="font-weight-bold " style="color: #695549;">회원정보</span>
      </div>
      <b-row>
        <b-col cols="8" class="pr-0 mr-0">
          <!-- 1. 아이디 -->
          <b-row id="accountBox" align-h="center">
            <b-col cols="2"><span>아이디</span></b-col>
            <b-col cols="5">
              <b-form-input
                id="input-1"
                type="email"
                :placeholder="user.userId"
                style="text-align: center;"
                disabled
              ></b-form-input>
            </b-col>
          </b-row>
        </b-col>
        <b-col>
          <!-- 2. 닉네임 -->
          <b-row id="accountBox" align-h="center">
            <b-col cols="2"><span>닉네임</span></b-col>
            <b-col cols="5">
              <b-form-input
                id="input-1"
                type="email"
                :placeholder="user.nickname"
                style="text-align: center;"
              ></b-form-input>
            </b-col>
            <!-- <small id = "error2" class="text-danger" style="float:left; margin-top:5px">{{
                  errors[0]
                }}</small> -->
          </b-row>
        </b-col>
          <!-- 3. 이메일 -->
          <b-row id="accountBox" align-h="center">
            <b-col cols="2"><span>이메일</span></b-col>
            <b-col cols="5">
              <b-form-input
                id="input-1"
                type="email"
                :placeholder="user.email"
                style="text-align: center;"
                disabled
              ></b-form-input>
            </b-col>
          </b-row>
          <!-- 4. 동네 설정 -->
          <b-row id="accountBox" align-h="center">
            <b-col cols="2"><span>우리동네</span></b-col>
            <b-col cols="5" class="font-weight-bold">
              {{ dong }}
            </b-col>
            
          </b-row>

      <b-col cols="3" class="pl-0 ml-0">
        <b-row class="my-4"></b-row>
        <b-row class="mt-5">
          <b-button
                style="margin-top:3px; background-color: #695549;"
                size="sm"
                @click="verifyNickname"
          >중복확인</b-button>
        </b-row>
        <b-row class="my-4"></b-row>
        <b-row class="my-4" align-h="left">
          <b-button variant="danger" size="sm" @click="createGroup">삭제</b-button>
        </b-row>
      </b-col>
      </b-row>
      <b-button class="mx-3 mb-5" style="background-color: #695549;" @click="createGroup">추가</b-button>

      <div>
        <b-button class="mx-3" variant="danger" @click="deleteAccount">회원탈퇴</b-button>
        <!-- 혹은 router-link 넣어주기!!! -->
        <b-button class="mx-3" style="background-color: #695549;" @click="toDetail">확인</b-button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
//const SERVER_URL = "http://localhost:8000";
export default {
  name: 'AccountUpdate',
  data: function () {
    return {
      dong: '역삼동',
      user: {
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
       if(this.user.nickname == ""|| document.getElementById("error2").innerHTML != ""){
        alert("닉네임 다시입력 바랍니다.")
        this.user.nickname = "";
      }
      else{
        axios
          .get(`/user/nickname/${this.user.nickname}`)
          .then(() => {
            alert("사용 가능한 닉네임 입니다.");
            this.checkNickname = true;
          })
          .catch(() => {
            if (this.user.nickname != "") {
              alert("현재 사용중인 닉네임 입니다.");
              this.user.nickname = "";
            }
          });
      }
    },
    deleteAccount: function () {

    },
    toDetail: function () {

    },
  },
  mounted() {
    this.user.userId = JSON.parse(localStorage.getItem('Login-token'))["user-id"]
    this.user.nickname = JSON.parse(localStorage.getItem('Login-token'))["user-name"]
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
</style>