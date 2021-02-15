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
        <img src="@/assets/logo.png" alt="우동" style="width: 60%;">
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
        <p style="cursor: pointer;" @click="toReview"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%;">우동 리뷰 보러가기!</p>
      </b-col>
    </b-row>
    <!-- 2. 우리 동네 소식 -->
    <b-row id="paper" class="color_red">
      <b-col cols="3" offset="2" align-self="center">
        <p style="cursor: pointer;" @click="toNews"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%;">우동 소식 보러가기!</p>
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
        <p style="cursor: pointer;" @click="toStory"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%;">우동 이야기 보러가기!</p>
      </b-col>
    </b-row>
    <!-- 4. 내 피드 -->
    <b-row id="paper" class="color_green">
      <b-col cols="3" offset="2" align-self="center">
        <p style="cursor: pointer;" @click="toMyFeed"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%;">내 피드 가기!</p>
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
    <b-row id="paper">
      <b-col align-self="center">
        <b-row>
          <b-col><h1 style="font-family: 'Nanum Pen Script', cursive;">소비자들에게 전하고 싶은 가치</h1></b-col>
        </b-row>
        <b-row class="mt-5">
          <!-- <b-col><b-icon icon="newspaper" font-scale="3"></b-icon></b-col> -->
        </b-row>
        <!-- newspaper` -->
      </b-col>
    </b-row>
    <b-row id="paper" class="color_purple">
      <b-col cols="3" offset="3" align-self="center">
        <b-row class="mb-3">
          <h1 style="font-family: 'Nanum Pen Script', cursive;">개발자들</h1>
        </b-row>
        <b-row>
          <p>이걸 만들기 위해 쎄빠지게 고생한 개발자들...</p>
        </b-row>
      </b-col>
      <b-col cols="4" align-self="center">
        <p style="cursor: pointer;" @click="toDevelopers"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%;">그 명예의 이름 확인하러 가기...!</p>
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
    }
  },
  methods: {
    toDevelopers: function () {
      this.$router.push({ name: 'Developers' })
    },
    toMyFeed: function () {
      this.$router.push({ name: 'MyFeed' })
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