<template>
  <div id="box">
    <!-- <div id="dummy">
      <h5>페이지가 정상적으로 작동중입니다!!!!</h5>
      <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">
      <div>개발자님 환영합니다!!!</div>
    </div>     -->

    <!-- 1. 이미지 -->
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="2000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333;"
    >

      <b-carousel-slide v-for="(item, index) in fileId"
        :key="index"   
        :caption="thumbnailContent[index].nickname"
        :text="thumbnailContent[index].reviewContent"
        :img-src="url+`/review/download/` + item" 
      ></b-carousel-slide>


  
      <!-- Text slides with image -->

    </b-carousel>
    <!-- 2. store 정보 -->

    <!-- 2. store 정보 -->
    <div style="text-align: left;">
      <div>
        <h3>{{ store.storeName }}</h3>
      </div>
      <hr />
      <p>주소: {{ store.storeAddr }}</p>
      <p>카테고리: {{ store.storeCtg2 }}</p>
      <p></p>
      <p></p>
    </div>
    <!-- 여기서 for문 -->
    <div v-for="(item, index) in bestReviewlist" :key="index">
      <ReviewBlock :review="item" />
    </div>
    <div v-if="!reviews.length">
      <div id="box">
        <img class="mt-5" alt="Vue logo" src="@/assets/udonge.png" style="width: 25%" />
        <div style="font-family: 'Nanum Pen Script', cursive;">
          <h3>리뷰가 없어요 ㅠㅠ</h3>
          <h4 style="font-family: 'Hachi Maru Pop', cursive;">レビューがありません!</h4>
        </div>
      </div>
    </div>
    <b-button @click="createReview">리뷰쓰기</b-button>
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
      slide: 0,
      sliding: null,
      getStoreReviewList: {},
      bestReviewlist: {},
      url : SERVER_URL,
      fileId : [],
      thumbnailContent : [],
      temp : "",
      flag : false,
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
  async created() {
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
          this.reviews = response.data;
          this.reviews2 = response.data;
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
        var p1 = a['createdAt'];
        var p2 = b['createdAt'];

        if (o1 < o2) return -1;
        if (o1 > o2) return 1;
        if (p1 < p2) return -1;
        if (p1 > p2) return 1;
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

<style></style>
