<template>
  <b-row class="my-2 pt-3 mx-5">
    <b-col cols="7" class="mr-0 pr-0">
      <div id="reviewCreateBox">
        <!-- 리뷰가게명 -->
        <div class="mt-3 pt-3">
          <h1>
            <span style="font-family: 'Hanna', sans-serif;">{{ store.storeName }} </span>
            <span class="small">리뷰하기</span>
          </h1>
          <h6 id="ReviewCreateCtg" style="text-align: center;">{{ store.storeCtg3 }}</h6>
          <b-tooltip target="ReviewCreateCtg">{{ store.storeCtg2 }}</b-tooltip>
          
        </div>

        <!-- 1. 평점 -->
        <b-row class="my-3" align-h="center">
          <star-rating
            v-model="review.rate"
            :star-size="40"
            :show-rating="false"
            :active-on-click="true"
          ></star-rating>
        </b-row>
        
        <!-- 2. 리뷰 내용 -->
        <div class="mx-5 px-5 mt-4">
          <b-form-textarea
            id="textarea-rows"
            v-model="review.reviewContent"
            placeholder="소중한 리뷰를 남겨주세요!"
            rows="11"
            class="text-align-center my-3"
            maxlength="500"
            style="overflow: hidden;"
          ></b-form-textarea>
          <p style="text-align: right;">{{ contentLength }} / 500</p>
        </div>

        <!-- 3. 이미지 -->
        <b-container fluid class="my-3 pb-5" style=""><!-- width: 70% -->
          <b-row class="py-3">
            <b-col cols="3" v-for="(url, index) in imageUrl" :key="index" left align-self="center">
              <div>
              <b-img class="px-3"  id="img_thumbnail" thumbnail fluid :src="url">
              </b-img>
               <div class="exit" @click="imageClose(index)"></div>
              </div>
            </b-col>
            <b-col cols="3" class="ml-0 pl-0" align-self="center">
              <b-icon icon="plus" v-b-modal.image-modal font-scale="4" variant="dark" style="cursor: pointer;">
                &nbsp;
              </b-icon>
            </b-col>
          </b-row>
        </b-container>

        <!-- 이미지 업로더 modal -->
        <b-modal 
          id="image-modal"
          ref="image-modal"
          title="소중한 사진을 올려주세요!"
          style="font-family: 'Jeju Gothic', sans-serif;"
          hide-footer
        >
          <b-form-file
            multiple="multiple"
            v-model="files"
            placeholder="첨부파일 없음"
            drop-placeholder="Drop file here..."
            required
            accept=".jpg, .png, .gif"
            style="width: 70%;"
            @change="previewImage"
          ></b-form-file>
          <!-- <b-row class="mt-3 mx-3" align-h="end">
            <b-button class="mr-1" size="sm" @click="hideModal" style="background-color: #695549;">뒤로가기</b-button>
            <b-button size="sm" @click="hideModal" style="background-color: #695549;">추가하기</b-button>
          </b-row > -->
        </b-modal>



        <b-row align-h="center" class="mb-3 pb-3">
          <b-col cols="3">
            <b-button size="lg" variant="danger" v-b-modal.review-cancel-modal>돌아가기</b-button>
          </b-col>
          <b-col cols="3">
            <b-button size="lg" style="background-color: #695549;" @click="createReview">리뷰작성</b-button>
          </b-col>
        </b-row>

        <!-- 돌아가기 버튼 클릭 후 나타나는 modal -->
        <b-modal id="review-cancel-modal" @ok="toBack">
          리뷰 작성을 취소하시겠습니까?
        </b-modal>
      </div>
    </b-col>

    <b-col class="mt-5 pt-2 mr-3 ml-0 pl-0">
      <div class="mb-2 small">주소: {{ store.storeAddr}}</div>
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
import axios from 'axios'
import StarRating from 'vue-star-rating'

const SERVER_URL = process.env.VUE_APP_SERVER_URL
const MAP_API_KEY = process.env.VUE_APP_MAP_API_KEY

export default {
  name: 'ReviewCreate',
  components: {
    StarRating,
  },
  data: function() {
    return {
      // store 정보
      storeId: '',
      store: {},
      // review 정보
      review: {
        userId: '',
        reviewContent: '',
        rate: 0,
        nickName: '',
      },

      // 기타
      imageUrl: [],
      files: [],
      modal: true, //true : modal열림, false : modal닫힘
      isValid: 0, // 작성한 리뷰 유효성 검사
    }
  },
  computed: {
    contentLength: function() {
      return this.review.reviewContent.length;
    },
    stars: function () {
      console.log(this.review.rate)
      return this.review.rate
    },
  },
  methods: {
    // store 정보 가져오기
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
    // 작성한 리뷰 유효성 검사!!!
    checkValidity: function() {
      if (this.review.rate == 0) {
        this.isValid = 0
        alert("평점을 선택해주세요!")
      } else if (this.review.reviewContent.length < 5) {
        this.isValid = 0
        alert("리뷰를 5자 이상 작성해주세요!")
      } else {
        this.isValid = 1
      }
    },
    createReview: function() {
      this.checkValidity()
      if (this.isValid) {
        var text = document.getElementById("textarea-rows").value;
        text = text.replace(/(?:\r\n|\r|\n)/g, '<br/>')
        document.getElementById("textarea-rows").value = text
        this.review.reviewContent = text
        var formData = new FormData();
        formData.append('reviewContent', this.review.reviewContent);
        formData.append('rate', this.review.rate);
        formData.append('storeId', this.store.storeId);
        formData.append('userId', this.review.userId);
        for (let i = 0; i < this.files.length; i++) {
          formData.append('file', this.files[i]);
        }
        if(this.files == null){
          formData.append('file', "1");
        }

        // review 내용 줄바꾸기


        console.log(this.files);
        // formData.append('club', this.club)
        console.log(formData);
        // if (this.verification) {
          axios
          .post(`${SERVER_URL}/review`, formData, {
            headers: { 'Content-Type': `application/json; charset=UTF-8` },
          })
          .then(() => {
            // console.log('응으응')
            // console.log(this.store)
            this.$router.push({ name: 'ReviewDetail', params: { storeId: this.storeId } });
          })
          .catch((err) => {
            console.log(err);
          });
      }

    },
    toBack: function () {
      window.history.back()
    },
    // 이미지 preview 보여주는 함수
    previewImage(event) {
      for(var i =0; i < this.imageUrl.length;i++){ this.imageUrl[i] = ""} 

      // 이미지 이미올라간거 초기화
      this.imageUrl.length = 0;
      // 이미지  미리보기 개수만큼 올리기
      for (var image of event.target.files) {
        const file = image;
     
        this.imageUrl.push(URL.createObjectURL(file));
      }
      this.$refs['image-modal'].hide()
    },
    imageClose(res){ // x버튼 누르면 미리보기랑 파일데이터 뺴기
      console.log(res)
      this.imageUrl.splice(res, 1); //인덱스 res부분 1개제거
      this.files.splice(res, 1); //인덱스 res부분 1개제거
    },
    hideModal() {
      this.$refs['image-modal'].hide()
    },



    // 맵 정보 가져오기
    addScript() {
      const script = document.createElement('script'); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${MAP_API_KEY}&libraries=services`;
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
  async created () {
    this.storeId = this.$route.params.storeId
    await this.getStore()
    this.review.userId = JSON.parse(localStorage.getItem('Login-token'))['user-id'];
  },
};
</script>

<style>
#img_thumbnail {
  width: 10rem;
  height: 12rem;
  max-width: 10rem;
  max-height: 12rem;
}

/* x 버튼 */
.exit {
  display: inline-block;
  position: relative;
  border: 2px solid red;
  width: 25px;
  height: 25px;
  border-radius: 50%;
  margin-top: 2em;
}

.exit:before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  margin-top: 0.15em;
  margin-left: 0.55em;
  height: 15px;
  border: solid red;
  border-width: 0 3px 0px 0;
  transform: rotate(45deg);
}

.exit:after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  margin-top: 0.15em;
  margin-left: 0.55em;
  height: 15px;
  border: solid red;
  border-width: 0 3px 0px 0;
  transform: rotate(-45deg);
}


</style>
