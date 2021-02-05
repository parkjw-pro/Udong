<template>
  <div id ="box">
    
      <b-col>
        <b-row class="mt-2">
          <b-col sm="3">
            <!-- 여기 placeholder에 데이터명 bind로 넣어주기 -->
            <h4 class="font-weight-bold">아이디</h4>
          </b-col>
          <b-col sm="7">
            <!-- 여기 placeholder에 데이터 값 bind로 넣어주기 -->
            <!-- v-model도 입력해주기 -->
            <b-form-input
            class="font-weight-bold"
            v-model="this.$route.query.userDetail.userId"
          ></b-form-input>
          </b-col>
        </b-row>
      </b-col>

       <b-col>
        <b-row class="mt-2">
          <b-col sm="3">
            <!-- 여기 placeholder에 데이터명 bind로 넣어주기 -->
            <h4 class="font-weight-bold">닉네임</h4>
          </b-col>
          <b-col sm="7">
            <!-- 여기 placeholder에 데이터 값 bind로 넣어주기 -->
            <!-- v-model도 입력해주기 -->
            <b-form-input
            class="font-weight-bold"
            v-model="this.$route.query.userDetail.nickname"
          ></b-form-input>
          </b-col>
        </b-row>
      </b-col>

       <b-col>
        <b-row class="mt-2">
          <b-col sm="3">
            <!-- 여기 placeholder에 데이터명 bind로 넣어주기 -->
            <h4 class="font-weight-bold">비밀번호</h4>
          </b-col>
          <b-col sm="7">
            <!-- 여기 placeholder에 데이터 값 bind로 넣어주기 -->
            <!-- v-model도 입력해주기 -->
            <b-form-input
            class="font-weight-bold"
            v-model="this.$route.query.userDetail.password"
          ></b-form-input>
          </b-col>
        </b-row>
      </b-col>
      
       <b-col>
        <b-row class="mt-2">
          <b-col sm="3">
            <!-- 여기 placeholder에 데이터명 bind로 넣어주기 -->
            <h4 class="font-weight-bold">이메일</h4>
          </b-col>
          <b-col sm="7">  
            <!-- 여기 placeholder에 데이터 값 bind로 넣어주기 -->
            <!-- v-model도 입력해주기 -->
            <b-form-input
            class="font-weight-bold"
            v-model="this.$route.query.userDetail.email"
          ></b-form-input>
          </b-col>
        </b-row>
      </b-col>

      <b-button block style="background-color: #695549" @click="update">수정하기</b-button>
      <b-button block variant="danger" @click="deleteData">탈퇴하기</b-button>
    </div>

</template>

<script>
import axios from 'axios'
const SERVER_URL = "http://localhost:8000";
export default {
  name: 'AdminUpdate',
  
  data: function () {
    return {
      
        datas : Object,
        
      
    }
  },
  methods: {
    deleteData: function () {

       axios.delete(`${SERVER_URL}/user?userId=${this.datas.userId}`)
         .then(() => {
          
          console.log("탈퇴완료")
           this.$router.push("/admin")
         })
         .catch((err) => {
           console.log(err)
         })
    },
    update: function () {
       axios.put(`${SERVER_URL}/user`,this.datas)
         .then((res) => {
          // router query로 넘겨주기
          console.log(res)
          console.log("수정완료")
          this.$router.push("/admin")
         })
         .catch((err) => {
           console.log(err)
           
         })
    },
  },
  created(){
   // this.datas = this.$route.query.userDetail
    console.log(this.$route.query.userDetail )
  }
}
</script>

<style>

</style>