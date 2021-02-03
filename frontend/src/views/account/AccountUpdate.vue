<template>
  <div id="box">
    <div id="border" class="my-5 py-5">
      <!-- 1. 제목 -->
      <div id="title" class="mb-5">
        <span><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%"></span>
        <span class="font-weight-bold " style="color: #695549;">회원정보</span>
      </div>
    
      <!-- 2. 내용 -->
      <!-- 2.1 input창 -->
      <b-col class="mx-3">
          <!-- 2.1.1 아이디 -->
        <b-row id="accountBox">
          <b-col id="label" cols="3"><span>아이디</span></b-col>
          <b-col id="label_content" cols="5">
            <b-form-input
              id="input-1"
              type="email"
              :placeholder="user.userId"
              style="text-align: center;"
              disabled
            ></b-form-input>
          </b-col>
        </b-row>
        <!-- 2.1.2 닉네임 -->
        <b-row id="accountBox">
          <b-col id="label" cols="3"><span>닉네임</span></b-col>
          <b-col id="label_content" cols="5">
            <b-form-input
              id="input-1"
              type="email"
              :placeholder="user.nickname"
              style="text-align: center;"
            ></b-form-input>
            <small id = "error2" class="text-danger"> 닉네임을 확인해주세요.</small>
          </b-col>
          <b-col cols="2">
            <b-button
              style="margin-top:3px; background-color: #695549;"
              size="sm"
              @click="verifyNickname"
            >중복확인</b-button>
          </b-col>
        </b-row>
        <!-- 2.1.3 이메일 -->
        <b-row id="accountBox">
          <b-col id="label" cols="3"><span>이메일</span></b-col>
          <b-col id="label_content" cols="5">
            <b-form-input
              id="input-1"
              type="email"
              :placeholder="user.email"
              style="text-align: center;"
              disabled
            ></b-form-input>
          </b-col>
          
        </b-row>
        <!-- 2.1.4 동네 설정 -->
        <b-row id="accountBox">
          <b-col id="label" cols="3"><span>우리동네</span></b-col>
          <b-col id="label_content" cols="5" class="font-weight-bold">
            {{ dong }}
          </b-col>
          <b-col cols="2">
            <b-button style="background-color: #695549;" size="sm" @click="addLocation">추가</b-button>
            <b-button variant="danger" size="sm" @click="deleteLocation">삭제</b-button>
          </b-col>
        </b-row>
      </b-col>
    </div>
    <div>
        <b-button class="mx-3" variant="danger" @click="deleteAccount">회원탈퇴</b-button>
        <!-- 혹은 router-link 넣어주기!!! -->
        <b-button class="mx-3" style="background-color: #695549;" @click="toDetail">확인</b-button>
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
        userId: "",
        nickname: "",
        email: "bulgen@naver.com",
      },
    }
  },
  methods: {
    addLocation: function () {
      alert("동네를 2개까지 추가할 수 있다!!?? \n서비스 준비 중입니다!")
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
    deleteLocation: function () {
      alert('한 개 이상의 동네를 설정하셔야 합니다!')
    },
    toDetail: function () {
      this.$router.push({ name: 'AccountDetail' })
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

#label {
  padding-right: 0px;
  margin-right: 0px;
}

#label_content {
  padding-left: 0px;
  margin-left: 0px;
}
</style>