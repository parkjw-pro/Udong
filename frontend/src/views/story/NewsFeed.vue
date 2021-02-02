<template>
  <div id="box" class="mx-5">
    <!-- 검색창 -->
    <b-nav-form>
      <b-form-input class="mr-sm-2 ml-auto" placeholder="Search"></b-form-input>  <!-- 가운데 정렬이 잘 안된다!!!! ㅠㅠㅠ -->
      <b-button variant="outline-success" class="my-2 my-sm-0" type="submit">Search</b-button>
    </b-nav-form>

    <!-- 그룹 고르는 공간 -->
    <b-row class="my-5 ">
      <b-col md="7">
        <b-button-group>
          <b-button variant="success">그룹1</b-button>
          <b-button variant="info">그룹2</b-button>
          <b-button variant="warning">그룹2</b-button>
        </b-button-group>
      </b-col>
      <b-col md="5">
        <b-button pill end variant="transparent" @click="toGroupList">+</b-button> <!-- variant="outline-secondary" 속성 -->
      </b-col>
    </b-row>


    <div class="mb-5"> <!-- for문 넣기 -->
      <PostBlock />
    </div>
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
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
      user: {},
      myGroup: {

      },
    }
  },
  methods: {
    getMyGroup: function () {
      // 유저 아이디 가져오기
      const userId = this.$store.getters.getUserId
      console.log(userId)
      axios.get(`${SERVER_URL}/${userId}/member`)
        .then((res) => {
          console.log(res, ': 내 그룹 가져오기 성공!')
          this.myGroup = res
        })
        .catcch((err) => {
          console.log(err, ': 내 그룹을 가져올 수 없습니다.')
        })
    },
    toGroupList: function () {
      this.$router.push({ name: 'GroupList' })
    }
  },
  created: async function () {
    await this.getMyGroup() 
  },
}
</script>

<style>

</style>