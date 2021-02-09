<template>
  <b-row class="my-2 pt-3 mx-5">
    <!-- 1. 왼쪽 검색 테이블 -->
    <b-col class="mr-0 pr-0">
      <h4 class="mb-3" style="font-family: 'Hanna', sans-serif;">우리 동네 장소를 검색해보세요!</h4>
        <input
          type="text"
          class="active-cyan-2 active-purple-2 mt-0 mb-3"
          placeholder="상점명을 입력하세요!"
          v-model="storeParamDto.searchWord"
          @keypress.enter="search"
          autofocus
          style="text-align: center; width: 40%;"
        />
      <!-- 
      <div class="mt-5">
        <div v-if="stores">
          <ul :v-for="(store, idx) in stores">
            <li @click="createArticle(store)">{{ store }}</li>
            <hr>
          </ul>
        </div>
        <div v-else>
          <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">
          <br>
          <h5 class="mt-3">검색 결과가 없어요 ㅠㅠ</h5>
        </div>
      </div> -->
      <table v-if="getSearchStoreList.length > 0" class="table table-hover"  striped hover style="background-color: #695549;">
        <thead style="color: white;" class="small">
          <tr>
            <th>종류</th>
            <th>이름</th>
            <th>시</th>
            <th>구</th>
            <th>동</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in getSearchStoreList" :key="index" @click="toDetail(item)">
            <td>{{ item.storeCtg2 }}</td>
            <td>{{ item.storeName }}</td>
            <td>{{ item.storeSidoName }}</td>
            <td>{{ item.storeGugunName }}</td>
            <td>{{ item.storeDong2Name }}</td>
          </tr>
        </tbody>
      </table>

      <div v-else class="mt-5 pt-5">
        <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%" />
        <br />
        <h5 class="mt-3">검색 결과가 없어요 ㅠㅠ</h5>
      </div>
    </b-col>

    <!-- 2. 오른쪽 지도 -->
    <b-col class="mt-5 pt-3 mr-3 ml-0 pl-0">
      <div class="map_wrap" style="width: 80%;">
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
// import { mdbInput, mdbContainer } from 'mdbvue';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
const MAP_API_KEY = process.env.VUE_APP_MAP_API_KEY;
export default {
  name: 'FindStore',
  components: {
    // mdbInput,
    // mdbContainer,
  },
  data: function() {
    return {
      storeParamDto: {
        searchWord: '',
        dongcode: '',
      },
      key: Object,
      temp: Object,
      userId: '',

      getSearchStoreList: {},
      getSearchareaList: [],
      pos : [],
    };
  },
  computed: {
    storeList: function() {
      return this.getSearchStoreList;
    },
    before() {
      return this.temp;
    },
  },
  watch: {
    before() {
      this.storeParamDto = this.temp;

      this.search();
    },
  },
  async mounted() {},
  created() {
    this.storeParamDto.dongcode = this.$route.params.address;
    if (this.storeParamDto.searchWord === '') {
      this.storeParamDto.searchWord = this.$route.params.keyword;
    }
    this.search();
  },
  methods: {
    search: function() {
      // console.log('search');
      // console.log(this.storeParamDto.searchWord);
      // console.log(this.storeParamDto.dongcode);
      axios
        .post(`${SERVER_URL}/store/stores`, this.storeParamDto)
        .then((response) => {
          console.log(response.data);
          this.getSearchStoreList = response.data;
          window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
        })
        .catch(() => {
          console.log('fail');
        });
    },
    initMap() {
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      //       var clusterer = new kakao.maps.MarkerClusterer({
      //     map: map,
      //     markers: markers,
      //     gridSize: 35,
      //     averageCenter: true,
      //     minLevel: 6,
      //     disableClickZoom: true,
      //     styles: [{
      //         width : '53px', height : '52px',
      //         background: 'url(cluster.png) no-repeat',
      //         color: '#fff',
      //         textAlign: 'center',
      //         lineHeight: '54px'
      //     }]
      // });
      var map = new kakao.maps.Map(container, options);
      var geocoder = new kakao.maps.services.Geocoder();

      var imageSrc = require('@/assets/udonge.png'), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(24, 35), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(20, 35) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
      var list = [];


      for (let index = 0; index < this.getSearchStoreList.length; index++) {
        var temp = this.getSearchStoreList[index];
        console.log(temp);
        list.push(temp)
        geocoder.addressSearch(temp.storeAddr, function(result, status) {
         // var title = temp.storeName;
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

           var marker = new kakao.maps.Marker({
              map: map, // 마커를 표시할 지도
              position: coords, // 마커를 표시할 위치
              title: "상점", // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
              image: markerImage, // 마커 이미지
            });
            //marker.setMap(map);
            console.log(marker);
            // var marker = new kakao.maps.Marker({ map: map, position: coords });
            // 인포윈도우로 장소에 대한 설명을 표시합니다
            // console.log(this.getSearchStoreList[0]);
              //  console.log(temp);
              console.log(list[index]);
               var infowindow = new kakao.maps.InfoWindow({
                 content: '<div @click="toDetail('+list[index]+')" style="width:100px;text-align:center;padding:4px 0; cursor: pointer;">'+list[index].storeName+'</div>',
               });
              infowindow.open(map, marker); // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              console.log(marker);
              map.setCenter(coords);
            
          }
        });
      }
      // for (let index = 0; index < this.getSearchStoreList.length; index++) {
      //   console.log(list[index]);

        
      // }
      

    },
    addScript() {
      const script = document.createElement('script'); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${MAP_API_KEY}&libraries=services`;
      document.head.appendChild(script);
    },

    toDetail(store) {
      console.log("toDetail");
      // 리뷰 작성 페이지로 넘어가준다!!
      this.$router.push({ name: 'ReviewDetail', params: { storeId: store.storeId } });
    },
  },
};
</script>

<style>
.active-cyan-2 input[type='text']:focus:not([readonly]) {
  border-bottom: 1px solid #4dd0e1;
  box-shadow: 0 1px 0 0 #4dd0e1;
}
.map_wrap {
  position: relative;
  width: 50%;
  height: 500px;
  left: 5%;
}
.hAddr {
  position: absolute;
  right: 20px;
  top: 10px;
  border-radius: 2px;
  background: #fff;
  background: rgba(255, 255, 255, 0.8);
  z-index: 1;
  padding: 5px;
}
</style>
