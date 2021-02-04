<template>
  <div id="box">
    <div id="dummy">
      <h5>페이지가 정상적으로 작동중입니다!!!!</h5>
      <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">
      <div>개발자님 환영합니다!!!</div>
    </div>    

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

    <p class="mt-4">
      Slide #: {{ slide }}<br>
      Sliding: {{ sliding }}
    </p>
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
    <div v-for="(item, index) in getStoreReviewList"
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
      key : Object,
      store : Object,
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
    this.ReviewDetail();
    }
  },
  created()  {

    this.store = this.$route.query.datas;
    console.log(this.store);
   // this.store = this.key;
   // console.log(this.store)
  },
  methods: {
    ReviewDetail: function() {
      console.log('reviewDetail');
      console.log( this.store);
      axios
      .get(`${SERVER_URL}/review/store/` + "1234")
      .then((response) => {
        console.log(response.data);
        this.getStoreReviewList = response.data;
      })
      .catch((response) => {
        console.log(response);
      });
    },
    createReview: function() {
      // 리뷰 작성 페이지로 넘어가준다!!
      console.log("보냅니다", this.store);
      this.$router.push({ name: 'ReviewCreate', query: {datas:this.store}});
    },
  },
}
</script>

<style>

</style>