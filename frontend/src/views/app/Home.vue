<template>
  <div class="container" style="text-align: left;">
    <div>
      <h3>우리 동네 리뷰</h3>
      아아 테스트
      v-for 리뷰 리스트 몇가지 최신순 받아오기
      <!-- <a v-if="data.reviewList.length == 0">
        없어요
      </a> -->
    </div>    
    <hr>
    <div class="story_overview">
      <h3>우리 동네 이야기</h3>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
// const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'Home',
  data: function () {
    return {
      reviewList: [],
      storieList: [],
    }
  },
  methods: {
    // readreviewList: function () {
    //   // review, stories 데이터 axios 요청 보내기
    //   axios.get(`${SERVER_URL}/review/`)
    //     .then(response => this.reviewList = response.data)
    //     .catch(error => console.log(error))
    // },
  },
  created: function () {
    // this.readreviewList()
  }
}
</script>

<style>

</style>