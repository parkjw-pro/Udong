<template>
  <div id="box">
    <!-- 1. Carousal -->
    <!-- <div id="dummy">
      <h5>페이지가 정상적으로 작동중입니다!!!!</h5>
      <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">
      <div>개발자님 환영합니다!!!</div>
    </div>     -->

    <!-- 1. 이미지 -->
    <b-row align-h="center">
      <b-carousel
        id="carousel-1"
        v-model="slide"
        controls
        indicators
        background="#ababab"
        img-width="1024"
        img-height="480"
        style="text-shadow: 1px 1px 2px #333; width: 50em; height: 25em;"
        fade="true"
      >

        <b-carousel-slide
          id="review_img_aggregated"
          v-for="(item, index) in fileId"
          :key="index"   
          
          :img-src="url+`/review/download/` + item" 
        ></b-carousel-slide>
        <!-- :caption="thumbnailContent[index].nickname" -->
          <!-- :text="thumbnailContent[index].reviewContent" -->

      </b-carousel>
    </b-row>
    <!-- 2. store 정보 -->
    <div class="my-5 py-5">
      <b-row style="text-align: justify;">
        <b-col>
          <h2 style="font-family: 'Hanna', sans-serif;">{{ store.storeName }}</h2>
        </b-col>
        <b-button style="background-color: #695549;" @click="createReview">리뷰쓰기</b-button>
      </b-row>
      <hr />
      <div style="text-align: left;">
        <p>주소: {{ store.storeAddr }}</p>
        <p>카테고리: {{ store.storeCtg2 }}</p>
      </div>
    </div>

    <!-- 여기서 for문 -->
    <div v-for="(item, index) in bestReviewlist" :key="index">
      <ReviewBlock :review="item" />
    </div>
    <div v-if="!reviews.length">
      <div id="box">
        <img class="my-5" alt="Vue logo" src="@/assets/udonge.png" style="width: 25%" />
        <h5>아직 작성된 리뷰가 없어요...</h5>
        <h5>첫 리뷰 작성자가 되어주세요 <b-icon icon="heart-fill" variant="danger"></b-icon></h5>
        <a href="" @click="createReview">리뷰 작성하러 가기!</a>
      </div>
    </div>
    
  </div>
</template>
<script>
import axios from 'axios';
import ReviewBlock from '@/components/review/ReviewBlock';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'ReviewDetail',
  components: {
    ReviewBlock,
  },
  data: function() {
    return {
      storeId: '',
      store: [],
      reviews: Array,
      reviews2: [],
      key: Object,
      // store : Object,
      sliding: null,
      getStoreReviewList: {},
      bestReviewlist: {},
      url : SERVER_URL,
      fileId : [],
      temp : "",
      flag : false,

      // Carousel에 사용하는 데이터
      slide: 0,
      thumbnailContent : [],
    };
  },
  computed: {
    before3() {
      return this.store;
      
    },
  },
  watch: {
    before3() {
      
      this.selectBestReview();
      this.selectAllImage();
    },
  },
  async mounted() {
    this.storeId = this.$route.params.storeId;
    // this.fileId = [];
    // this.thumbnailContent = [],
    // this.temp = "",
    await this.getReview();
    await this.getStore();
    

    // console.log(this.store);
    // this.store = this.key;
    // console.log(this.store)
  },

  methods: {
    getReview: function() {
      axios
        .get(`${SERVER_URL}/review/store/` + `${this.storeId}`)
        .then((response) => {
          this.reviews2 = response.data;
          this.reviews = response.data;
          console.log(this.reviews);
          console.log(this.reviews2);
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
    selectBestReview: function() {
      
      this.bestReviewlist = this.reviews2.sort(function(a, b) {
        var o1 = b['reviewLikeCount'];
        var o2 = a['reviewLikeCount'];

        if (o1 < o2) return -1;
        if (o1 > o2) return 1;

      });

      console.log(this.bestReviewlist);
    },
    selectAllImage: function() {
      for (let index = 0; index < this.bestReviewlist.length; index++) {
        console.log(this.bestReviewlist[index].reviewId)
        this.temp = this.bestReviewlist[index].reviewContent;
        console.log(this.temp);
        this.GetReviewDetail(this.bestReviewlist[index]);
       
        
        
        //console.log(this.fileId);
      }
    },
    
    GetReviewDetail: function(review) {
      axios
      .get(`${SERVER_URL}/review/` + review.reviewId)
      .then((response) => {
        for (let index = 0; index < response.data.fileId.length; index++) {
          this.thumbnailContent.push(review)
          console.log(this.temp);
          this.fileId.push(response.data.fileId[index]);
          console.log(response.data.fileId[index]);
          
        }
        // for (let index = 0; index < response.data.fileId.length-1; index++) {
        //   this.thumbnailContent.push(this.thumbnailContent[this.thumbnailContent.length-1])
          
        // }
        console.log( this.fileId);
        this.flag = true;

      })
      .catch((response) => {
        console.log(response);
      });
    },
    createReview: function() {
      // 리뷰 작성 페이지로 넘어가준다!!
      // console.log("보냅니다", this.store);
      this.$router.push({ name: 'ReviewCreate', params: { storeId: this.storeId } });
    },
  },
};
</script>

<style>
#review_img_aggregated {
  top: 0;
  left: 0;
  min-width: 50em;
  min-height: 25em;
  max-width: 50em;
  max-height: 25em;
}
</style>
