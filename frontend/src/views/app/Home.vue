<template>
  <!-- <div id="box" class="container" style="text-align: left;">
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
  </div> -->
  <div>
    <div id="paper" class="color_yellow">
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div>
        <h1 color="black">우리 동네 공간</h1>
        <div class="pt-5"></div>
        <img src="@/assets/logo.png" alt="우동" style="width: 15%;">
        <!-- <img class="ml-5" alt="Vue logo" src="@/assets/udonge.png" style="width: 10%"> -->
      </div>
    </div>
    <!-- 1. 우리 동네 리뷰 -->
    <div id="paper">
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <b-row>
        <b-col cols="3" offset="3">
          <b-row class="mb-3">
            <h2>우리 동네 리뷰</h2>
          </b-row>
          <b-row>
            <p>우리 동네 사람들이 공유하는 리뷰와 평가</p>
          </b-row>
        </b-col>
        <b-col cols="4">
          <div class="pt-5"></div>
          <p style="cursor: pointer;" @click="toReview"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">우동 리뷰 보러가기!</p>
        </b-col>
      </b-row>
    </div>
    <!-- 2. 우리 동네 소식 -->
    <div id="paper" class="color_red">
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <b-row>
        <b-col cols="3" offset="2">
          <div class="pt-5"></div>
          <p style="cursor: pointer;" @click="toNews"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">우동 소식 보러가기!</p>
        </b-col>
        <b-col cols="4" offset="2">
          <b-row class="mb-3">
            <h2>우리 동네 소식</h2>
          </b-row>
          <b-row>
            <p>한 번에 볼 수 있는 우리 동네 소식</p>
          </b-row>
        </b-col>
      </b-row>
    </div>
    <!-- 3. 우리 동네 이야기 -->
    <div id="paper">
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <b-row>
        <b-col cols="3" offset="3">
          <b-row class="mb-3">
            <h2>우리 동네 이야기</h2>
          </b-row>
          <b-row>
            <p>우리 동네 사람들과 함께하는 이야기</p>
          </b-row>
        </b-col>
        <b-col cols="4">
          <div class="pt-5"></div>
          <p style="cursor: pointer;" @click="toStory"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">우동 이야기 보러가기!</p>
        </b-col>
      </b-row>
    </div>
    <div id="paper" class="color_green">
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <h3>소비자들에게 전하고 싶은 가치</h3>
    </div>
    <div id="paper">
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <div class="pt-5"></div>
      <b-row>
        <b-col cols="3" offset="3">
          <b-row class="mb-3">
            <h2>개발자들</h2>
          </b-row>
          <b-row>
            <p>이걸 만들기 위해 쎄빠지게 고생한 개발자들...</p>
          </b-row>
        </b-col>
        <b-col cols="4">
          <div class="pt-5"></div>
          <p style="cursor: pointer;" @click="toDevelopers"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">그 명예의 이름 확인하러 가기...!</p>
        </b-col>
      </b-row>
    </div>
    <div id="paper" class="color_purple">

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
      storyList: [],
    }
  },
  methods: {
    toDevelopers: function () {
      this.$router.push({name: 'Developers'})
    },
    toNews: function () {
      this.$router.push({name: 'News'})
    },
    toReview: function () {
      this.$router.push({name: 'ReviewHome'})
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
#paper {
  height: 600px;
}

#overview {
  margin-top: 40px;
  margin-bottom: 40px;
}

#overview_content {
  text-align: center;
  cursor: pointer;
}


/* 파스텔 컬러 */
.color_red{
  background-color: #F5D5CB;
}

.color_yellow{
  background-color: #F6F6EB;
}

.color_green {
  background-color: #D7ECD9;
}

.color_purple {
  background-color: #D5D6EA;
}
</style>