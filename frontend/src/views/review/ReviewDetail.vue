<template>
  <div id="box">
    <!-- 1. Carousal -->
    <!-- <div id="dummy">
      <h5>페이지가 정상적으로 작동중입니다!!!!</h5>
      <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">
      <div>개발자님 환영합니다!!!</div>
    </div>     -->
    carousal  
    <div id="roadview" class="mb-5">
      <!-- 1. 이미지 -->

      <!-- <p class="mt-4">
        Slide #: {{ slide }}<br>
        Sliding: {{ sliding }}
      </p> -->
    </div>

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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${MAP_API_KEY}"></script>
<script>
const MAP_API_KEY = process.env.VUE_APP_MAP_API_KEY;
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
      store: {},
      reviews: {},
      key: Object,
      // store : Object,
      slide: '0',
      sliding: null,
      getStoreReviewList: {},
      bestReviewlist: {},
      roadviewContainer: null,
      roadview: null,
      roadviewClient: null,
      position: null,
    };
  },
  computed: {
    before3() {
      return this.store;
    },
  },
  watch: {
    before3() {
      this.getReview();
      this.selectBestReview();
    },
  },
  async created() {
    this.storeId = this.$route.params.storeId;
    await this.getReview();
    await this.getStore();
    this.roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
    this.roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
    this.roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
    this.position = new kakao.maps.LatLng(33.450701, 126.570667);
    this.insertRoadView();

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
    selectBestReview: function() {
      this.bestReviewlist = this.reviews.sort(function(a, b) {
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
    insertRoadView: function() {
      roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
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
