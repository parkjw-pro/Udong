<template>
  <div id="box" class="mx-5">
    <!-- 1. 검색바 -->
    <b-row align-h="center">
      <!-- 1.1 검색창 -->
      <b-col cols="6" class="pr-0 mr-0"><b-form-input v-model="text" placeholder="검색하기" @keypress.enter="search"></b-form-input></b-col>
      <!-- 1.2 검색버튼 -->
      <!-- <b-col cols="2" class="pl-0 ml-0"><b-icon icon="search" font-scale="2" variant="info"></b-icon></b-col> -->
    </b-row>

    <!-- 그룹 고르는 공간 -->
    <b-row class="my-5 ">
      <b-col md="7">
        <b-button-group v-for="(idx, group) in myGroups" :key="idx">
          <b-button variant="info" @click="toGroup(group)">{{ group.clubName }}</b-button>
        </b-button-group>
      </b-col>
      <b-col md="5">
        <b-button pill end variant="transparent" @click="toGroupList">더보기 +</b-button> <!-- variant="outline-secondary" 속성 -->
      </b-col>
    </b-row>


    <div class="mb-5" v-for="(idx, post) in posts" :key="idx"> <!-- for문 넣기 -->
      <PostBlock :post="post" />
    </div>
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <EndBlock />
    <h3>게시물이 없어요...</h3>
    <Button />
  </div>
</template>

<script>
import axios from 'axios'
import Button from '@/components/story/Button'
import EndBlock from '@/components/story/EndBlock'
import PostBlock from '@/components/story/PostBlock'

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'NewsFeed',
  components: {
    Button,
    EndBlock,
    PostBlock,
  },
  data: function () {
    return {
      text: '',
      user: {
      },
      myGroups: {

      },
      posts: {

      },
    }
  },
  methods: {
    getUser: function () {

    },
    getMyGroup: function () {
      // 유저 아이디 가져오기
      const userId = this.$store.getters.getUserId
      // axios 요청보내기
      axios.get(`${SERVER_URL}/${userId}/member`)
        .then((res) => {
          console.log(res, ': 내 그룹 가져오기 성공!')
          this.myGroups = res
        })
        .catcch((err) => {
          console.log(err, ': 내 그룹을 가져올 수 없습니다.')
        })
    },
    search: function () {
      // 검색기능 어떻게 할까?
      this.text
    },
    toGroup: function (group) {
      this.$router.push({ name: 'GroupPage', query: {group: group}})
    },
    toGroupList: function () {
      this.$router.push({ name: 'GroupList' })
    },
  },
  created: async function () {
    await this.getUser()
    await this.getMyGroup() 
  },
}
</script>

<style>

</style>