<template>
  <div id="box" class="mx-5">
      <!-- 검색창 -->
    <b-row align-h="center">
      <b-nav-form>
        <b-form-input class="mr-sm-2 ml-auto" placeholder="그룹내 검색" style="text-align: center;"></b-form-input>
        <b-button variant="outline-info" class="my-2 my-sm-0" type="submit">검색</b-button>
      </b-nav-form>
    </b-row>
    <b-row class="my-5" align-h="between">
      <b-col><h2 class="font-weight-bold">{{ this.group.clubName }}</h2></b-col>
      <b-col>
        <!-- v-if="user.group.status=Admin" -->
        <!-- <b-button variant="info">회원관리</b-button> -->
        <!-- v-else -->
        <b-button variant="info">회원조회</b-button>
        <b-button style="background-color: #695549;" @click="toArticleCreate">게시글작성</b-button>
      </b-col>
      
    </b-row>
    <hr>
    <div class="mb-5"> <!-- for문 넣기 -->
      <PostBlock />
    </div>
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    아직 게시글이 없어요..!
    <EndBlock />
    <!-- <Button /> -->
  </div>
</template>

<script>
import axios from 'axios'
import EndBlock from '@/components/story/EndBlock'
import PostBlock from '@/components/story/PostBlock'

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: "GroupPage",
  components: {
    EndBlock,
    PostBlock,
  },
  data() {
    return {
      group: {

      },
      user_address: "",
      clubName : "",
    }
  },
  methods :{
    getGroup: function () {
      axios.get(`${SERVER_URL}/club/${this.$route.params.groupId}`)
        .then((res) => {
          this.group = res.data.dto
        })
        .catch((err) => {
          console.log(err)
        })
    },
    toArticleCreate: function () {
      this.$router.push({ name: 'ArticleCreate', params: {address: this.user_address, group: this.group} })
    },
  },
  created() {
    this.getGroup()
    this.user_address = JSON.parse(localStorage.getItem('Login-token'))["user_address_name"]

    // 안쓰면 삭제하기!!!!
    this.clubName = this.$route.query.club.clubName
    console(this.clubName);
    
  },
}
</script>

<style>

</style>