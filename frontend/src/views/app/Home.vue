<template>
  <div id="box" class="container" style="text-align: left;">
    <div id="overview" class="review_overview">
      <h3>우리 동네 리뷰</h3>
      <p id="overview_content" @click="toReview"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">동네 리뷰 보러가기!</p>
    </div>
    <hr>
    <div id="overview" class="news_overview">
      <h3>우리 동네 소식</h3>
      <p id="overview_content" @click="toNews"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">동네 소식 보러가기!</p>
    </div>    
    <hr>
    <div id="overview" class="story_overview">
      <h3>우리 동네 이야기</h3>
      <p id="overview_content" @click="toStory"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">동네 이야기 보러가기!</p>
    </div>
    <hr>
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
      storyList: [],
    }
  },
  methods: {
    toReview: function () {
      this.$router.push({name: 'ReviewHome'})
    },
    toNews: function () {

    },
    toStory: function () {
      this.$router.push({name: 'NewsFeed'})
    },
    // getReview: function () {
    //   axios.get(`${SERVER_URL}/review`)
    //     .then((res) => {
    //       console.log(res)
    //       this.reviewList = res
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //     })
    // },
    // getStory: function () {
    //   axios.get(`${SERVER_URL}/story`)
    //     .then((res) => {
    //       console.log(res)
    //       this.storyList = res
    //     })
    //     .catch((err) => {
    //       console.log(err)
    //     })
    // },
  },
  created: async function () {
    // await this.getReview()
    // await this.getStory()
  }
}
</script>

<style>
#overview {
  margin-top: 40px;
  margin-bottom: 40px;
}

#overview_content {
  text-align: center;
  cursor: pointer;
}
</style>