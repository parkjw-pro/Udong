<template>
  <div id="box">
    <!-- <div id="dummy">
      <h5>페이지가 정상적으로 작동중입니다!!!!</h5>
      <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">
      <div>개발자님 환영합니다!!!</div>
    </div>     -->

    <div id="review_carousel" class="mb-5">
      <!-- 1. 이미지 -->
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="4000"
        controls
        indicators
        background="#ababab"
        img-width="1024"
        img-height="480"
        style="text-shadow: 1px 1px 2px #333;"
      >
        <!-- Text slides with image -->
        <b-carousel-slide
          caption="First slide"
          text="Nulla vitae elit libero, a pharetra augue mollis interdum."
          img-src="https://picsum.photos/1024/480/?image=52"
        ></b-carousel-slide>

        <!-- Slides with custom text -->
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
          <h1>Hello world!</h1>
        </b-carousel-slide>

        <!-- Slides with image only -->
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>

        <!-- Slides with img slot -->
        <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              width="1024"
              height="480"
              src="https://picsum.photos/1024/480/?image=55"
              alt="image slot"
            >
          </template>
        </b-carousel-slide>

        <!-- Slide with blank fluid image to maintain slide aspect ratio -->
        <b-carousel-slide caption="Blank Image" img-blank img-alt="Blank image">
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eros felis, tincidunt
            a tincidunt eget, convallis vel est. Ut pellentesque ut lacus vel interdum.
          </p>
        </b-carousel-slide>
      </b-carousel>

      <!-- <p class="mt-4">
        Slide #: {{ slide }}<br>
        Sliding: {{ sliding }}
      </p> -->
    </div>

    <!-- 2. store 정보 -->
    <div style="text-align: left;">
      <div ><h3>{{store.storeName}}</h3></div>
      <hr>
      <p>주소:  {{ store.storeAddr }}</p>
      <p>카테고리: {{ store.storeCtg2 }}</p>
      <p></p>
      <p></p>
    </div>
    <!-- 여기서 for문 -->
    <div v-for="(item, index) in reviews"
          :key="index">
      <ReviewBlock :review="item"/>
    </div>
    <b-button @click="createReview">리뷰쓰기</b-button>
  </div>
</template>

<script>
import axios from 'axios';
import ReviewBlock from '@/components/review/ReviewBlock'

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'ReviewDetail',
  components: {
    ReviewBlock
  },
 data: function() {
    return {
      storeId: '',
      store: {},
      reviews: {

      },
      key : Object,
      // store : Object,
      slide : "0",
      sliding : null,
      getStoreReviewList: {},

    };
  },
  computed: {
    before3(){ 
      return this.store;
    }
  },
  watch : {
      before3(){ 
    this.getReview();
    }
  },
  async created()  {
    this.storeId = this.$route.params.storeId
    await this.getReview()
    await this.getStore()
    // console.log(this.store);
   // this.store = this.key;
   // console.log(this.store)
  },
  methods: {
    getReview: function() {
      axios
      .get(`${SERVER_URL}/review/store/` + `${this.storeId}`)
      .then((response) => {
        this.reviews = response.data;
      })
      .catch((response) => {
        console.log(response);
      });
    },
    getStore: function() {
      axios
      .get(`${SERVER_URL}/store/` + `${this.storeId}`)
      .then((response) => {
        this.store = response.data;
      })
      .catch((response) => {
        console.log(response);
      });
    },
    createReview: function() {
      // 리뷰 작성 페이지로 넘어가준다!!
      // console.log("보냅니다", this.store);
      this.$router.push({ name: 'ReviewCreate', params: {storeId: this.storeId}});
    },
  },
}
</script>

<style>

</style>