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
    <b-row id="paper" class="color_yellow">
      <b-col cols="3" offset="2" align-self="center">
        <h1 color="black" style="font-family: 'Nanum Pen Script', cursive;">우리 동네 공간</h1>
      </b-col>
      <b-col cols="5" align-self="center">
        <img src="@/assets/logo.png" alt="우동" style="width: 40%;">
      </b-col>
    </b-row>
    <!-- 1. 우리 동네 리뷰 -->
    <b-row id="paper">
      <b-col cols="3" offset="3" align-self="center">
        <b-row class="mb-3">
          <h1 style="font-family: 'Nanum Pen Script', cursive;">우리 동네 리뷰</h1>
        </b-row>
        <b-row>
          <p>우리 동네 사람들이 공유하는 리뷰와 평가</p>
        </b-row>
      </b-col>
      <b-col cols="4" align-self="center">
        <img alt="Vue logo" src="@/assets/app/home/home_review.png" style="width: 100%; cursor: pointer;" @click="toReview">
        <p class="mt-2" style="cursor: pointer;" @click="toReview"><img class="mr-2" alt="Vue logo" src="@/assets/udonge.png" style="width: 6%;">우동 리뷰 보러가기!</p>
      </b-col>
    </b-row>
    <!-- 2. 우리 동네 소식 -->
    <b-row id="paper" class="color_red">
      <b-col cols="3" offset="2" align-self="center">
        <img class="mb-3" alt="Vue logo" src="@/assets/app/home/home_news.png" style="width: 80%; cursor: pointer;" @click="toNews">
        <p style="cursor: pointer;" @click="toNews">서비스 준비중입니다</p>
      </b-col>
      <b-col cols="4" offset="2" align-self="center">
        <b-row class="mb-3">
          <h1 style="font-family: 'Nanum Pen Script', cursive;">우리 동네 소식</h1>
        </b-row>
        <b-row>
          <p>한 번에 볼 수 있는 우리 동네 소식</p>
        </b-row>
      </b-col>
    </b-row>
    <!-- 3. 우리 동네 이야기 -->
    <b-row id="paper">
      <b-col cols="3" offset="3" align-self="center">
        <b-row class="mb-3">
          <h1 style="font-family: 'Nanum Pen Script', cursive;">우리 동네 이야기</h1>
        </b-row>
        <b-row>
          <p>우리 동네 사람들과 함께하는 이야기</p>
        </b-row>
      </b-col>
      <b-col cols="4" align-self="center">
        <img alt="Vue logo" src="@/assets/app/home/home_story.png" style="width: 80%; cursor: pointer;" @click="toStory">
        <p class="mt-2" style="cursor: pointer;" @click="toStory"><img class="mr-2" alt="Vue logo" src="@/assets/udonge.png" style="width: 6%;">우동 이야기 보러가기!</p>
      </b-col>
    </b-row>
    <!-- 4. 내 피드 -->
    <b-row id="paper" class="color_green">
      <b-col cols="3" offset="2" align-self="center">
        <img class="mb-2" alt="Vue logo" src="@/assets/app/home/home_myfeed.png" style="width: 100%; cursor: pointer;" @click="toMyFeed">
        <p class="mt-2" style="cursor: pointer;" @click="toMyFeed"><img class="mr-2" alt="Vue logo" src="@/assets/udonge.png" style="width: 8%;">내 피드 가기!</p>
      </b-col>
      <b-col cols="4" offset="2" align-self="center">
        <b-row class="mb-3">
          <h1 style="font-family: 'Nanum Pen Script', cursive;">내 피드</h1>
        </b-row>
        <b-row>
          <p>나의 이야기와 추억을 담는 공간</p>
        </b-row>
      </b-col>
    </b-row>
    <b-row id="paper" style="font-family: 'Nanum Pen Script', cursive;">
      <b-col align-self="center">
        <b-row>
          <b-col><h1>Core Values of Consumers</h1></b-col>
        </b-row>
        <b-row class="mt-5" align-h="center">
          <b-col cols="2"><img src="@/assets/app/home/value_news.png" alt="" style="width: 60%;"></b-col>
          <b-col cols="2"><img src="@/assets/app/home/value_connection.png" alt="" style="width: 60%;"></b-col>
          <b-col cols="2"><img src="@/assets/app/home/value_user.jpg" alt="" style="width: 60%;"></b-col>
        </b-row>
        <b-row class="mt-5" align-h="center">
          <b-col cols="2"><h3>Sophisticated Information</h3></b-col>
          <b-col cols="2"><h3>Neighborhood Connections</h3></b-col>
          <b-col cols="2"><h3>User-Friendly</h3></b-col>
        </b-row>
      </b-col>
    </b-row>
    <b-row id="paper" class="color_purple" style="font-family: 'Nanum Pen Script', cursive;">
      <b-col cols="3" offset="3" align-self="center">
        <b-row class="mb-3">
          <h1>Developers</h1>
        </b-row>
        <b-row>
          <h4 class="ml-1">outstanding developers to be honoured</h4>
        </b-row>
      </b-col>
      <b-col cols="4" align-self="center">
        <h4 style="cursor: pointer;" @click="toDevelopers"><img class="mr-2" alt="Vue logo" src="@/assets/udonge.png" style="width: 8%;">Check it out here</h4>
      </b-col>
    </b-row>
  </div>
</template>

<script>
// import axios from 'axios'
// const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'Home',
  props: {
  },
  data: function () {
    return {
      reviewList: [],
      storyList: [],
      user: {
        userId: JSON.parse(localStorage.getItem('Login-token'))['user-id'],
        nickname: JSON.parse(localStorage.getItem('Login-token'))['user-name']
      }
    }
  },
  methods: {
    toDevelopers: function () {
      this.$router.push({ name: 'Developers' })
    },
    toMyFeed: function () {
      // this.$router.push({ name: 'MyFeed' })
      this.$router.push({ name: 'MyFeed', params: { userId: this.user.userId, nickname: this.user.nickname}})
    },
    toNews: function () {
      this.$router.push({ name: 'NewsHome' })
    },
    toReview: function () {
      this.$router.push({ name: 'ReviewHome' })
    },
    toStory: function () {
      this.$router.push({ name: 'NewsFeed' })
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
    const userInfo = JSON.parse(localStorage.getItem('Info-token'))
    if (!userInfo) {
      this.$router.push({ name: 'Login' })
    }
    // await this.getReview()
    // await this.getStory()
  }
}
</script>

<style>
#paper {
  height: 500px;
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