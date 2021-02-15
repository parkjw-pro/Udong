<template>
  <b-row class="my-2 pt-3 mx-5">
    <b-col class="mr-0 pr-0">

      <!-- 1. 이미지 -->
      <b-row v-if="fileId.length > 0" align-h="center">
        <b-carousel
          id="carousel-1"
          v-model="slide"
          controls
          indicators
          background="#ababab"
          img-width="1024"
          img-height="480"
          style="text-shadow: 1px 1px 2px #333; width: 50em; height: 25em;"
          :fade="true"
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
    </b-col>

    <b-col class="mt-5 pt-3 mr-3 ml-0 pl-0">
      <div class="map_wrap2" style="width: 80%;">
        <div id="map" style="width:100%; height:100%; position:relative; overflow:hidden;"></div>
        <div class="hAddr">
          <!-- <span class="title">지금 계신 위치가 이곳이 맞나요?</span> -->
          <span></span>
        </div>
      </div>
    </b-col>
  </b-row>
</template>
<script>
import axios from 'axios';
import ReviewBlock from '@/components/review/ReviewBlock';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;
const MAP_API_KEY = process.env.VUE_APP_MAP_API_KEY

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
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getStore: function() {
      axios
        .get(`${SERVER_URL}/store/` + `${this.storeId}`)
        .then((res) => {
          // 1. store 정보 가져오기
          this.store = res.data;
          // 2. 이후 지도 표시하기
          window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    selectBestReview: function() {
      
      this.bestReviewlist = this.reviews2.sort(function(a, b) {
        var o1 = b['reviewLikeCount'];
        var o2 = a['reviewLikeCount'];

        if (o1 < o2) return -1;
        if (o1 > o2) return 1;

      });

      // console.log(this.bestReviewlist);
    },
    selectAllImage: function() {
      for (let index = 0; index < this.bestReviewlist.length; index++) {
        this.temp = this.bestReviewlist[index].reviewContent;
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
          this.fileId.push(response.data.fileId[index]);
          
        }
        // for (let index = 0; index < response.data.fileId.length-1; index++) {
        //   this.thumbnailContent.push(this.thumbnailContent[this.thumbnailContent.length-1])
          
        // }
        this.flag = true;

      })
      .catch((err) => {
        console.log(err);
      });
    },
    createReview: function() {
      // 리뷰 작성 페이지로 넘어가준다!!
      // console.log("보냅니다", this.store);
      this.$router.push({ name: 'ReviewCreate', params: { storeId: this.storeId } });
    },

    // 지도

    // 맵 정보 가져오기
    addScript() {
      const script = document.createElement('script'); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${MAP_API_KEY}&libraries=services`;
      document.head.appendChild(script);
    },
    initMap() {
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(this.store.locLat, this.store.locLng),
        level: 5,
      };
      var map = new kakao.maps.Map(container, options);

      var imageSrc = require('@/assets/marker3.png'), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(24, 35), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(20, 35) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
      // var list = [];
      var bounds = new kakao.maps.LatLngBounds();
      var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

      var fragment = document.createDocumentFragment();
      var displayInfowindow = function(marker, title) {
        var content = '<div style="padding:5px; text-align: center;">' + title + '</div>';

        infowindow.setContent(content);
        infowindow.open(map, marker);
      };

      var coords = new kakao.maps.LatLng(
          this.store.locLat,
          this.store.locLng
        );
      var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: coords, // 마커를 표시할 위치locLng
        // title: this.getSearchStoreList[index].storeName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });

      (function(marker, title) {
        kakao.maps.event.addListener(marker, 'mouseover', function() {
          displayInfowindow(marker, title);
        });
        kakao.maps.event.addListener(marker, 'mouseout', function() {
          infowindow.close();
        });
        this.store.onmouseover = function() {
          displayInfowindow(marker, title);
        };
        this.store.onmouseout = function() {
          infowindow.close();
        };
      })(marker, this.store.storeName);

      fragment.appendChild(this.store);

      //infowindow.open(map, marker); // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      bounds.extend(coords);
    }
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
