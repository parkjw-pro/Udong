<template>
  <div id="box">
    <b-row id="buttonBox">
      <b-col cols="4">유저</b-col>
      <b-col cols="8">
        <b-button block style="background-color: #695549" @click="toUser">유저 관리페이지</b-button>
      </b-col>
    </b-row>
    <b-row id="buttonBox">
      <b-col cols="4">유저게시글</b-col>
      <b-col cols="8">
        <b-button block style="background-color: #695549" @click="toUserArticle">유저게시글 관리페이지</b-button>
      </b-col>
    </b-row>
    <b-row id="buttonBox">
      <b-col cols="4">그룹</b-col>
      <b-col cols="8">
        <b-button block style="background-color: #695549" @click="toGroup">그룹 관리페이지</b-button>
      </b-col>
    </b-row>
    <b-row id="buttonBox">
      <b-col cols="4">그룹게시글</b-col>
      <b-col cols="8">
        <b-button block style="background-color: #695549" @click="toGroupArticle">그룹게시글 관리페이지</b-button>
      </b-col>
    </b-row>
    <b-row id="buttonBox">
      <b-col cols="4">리뷰</b-col>
      <b-col cols="8">
        <b-button block style="background-color: #695549" @click="toReview">리뷰 관리페이지</b-button>
      </b-col>
    </b-row>
        <b-row id="buttonBox">
      <b-col cols="4">상점</b-col>
      <b-col cols="8">
        <b-button block style="background-color: #695549" @click="toStore">상점 관리페이지</b-button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Admin',
  data: function () {
    return {
      is_manager: null,
      user: {

      }
    }
  },
  methods: {
    toUser: function () {
      // sample !!!! 이하 동일~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~!!!

      // 유저 DB 전체 가져오기
      axios.get("/user")
        .then((res) => {
          // router query로 넘겨주기
          console.log(res)
          this.$router.push({name: 'AdminDetail', query: {data: res}})
        })
        .catch((err) => {
          console.log(err)
        })
    },
    toUserArticle: function () {

    },
    toGroup: function () {

    },
    toGroupArticle: function () {

    },
    toReview: function () {

    },
    toStore: function () {

    },
  },
  created: async function() {
      // 그룹 정보 가져오기!!
      // await this.getUser()
      // if (this.is_manager) {
      //   pass
      // } else {
      //   this.$router.push({name: 'Home')
      // }
  }
}
</script>

<style>
#buttonBox {
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>