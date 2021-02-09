<template>
<div>
      <div class="map_wrap">
      <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
      <div class="hAddr">
        <!-- <span class="title">지금 계신 위치가 이곳이 맞나요?</span> -->
        <span></span>
      </div>
    </div>
  <div id="box">
    <h2 class="mb-5">우리 동네 장소를 검색해보세요! </h2>
    <container style="width: 100%;">
      <input
        type="text"
        class="active-cyan-2 active-purple-2 mt-0 mb-3"
        placeholder="상점의 종류 및 상점명을 입력하세요"
        v-model="storeParamDto.searchWord"
        @keypress.enter="search"
        autofocus
      />
    </container>
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
    <table v-if="getSearchStoreList" class="table table-hover">
      <thead>
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

    <div v-else>
      <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%" />
      <br />
      <h5 class="mt-3">검색 결과가 없어요 ㅠㅠ</h5>
    </div>
  </div>
  </div>
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
  async mounted() {

    
  },
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
      var map = new kakao.maps.Map(container, options);
      var geocoder = new kakao.maps.services.Geocoder();
      console.log(this.getSearchStoreList[0]);
      for (let index = 0; index < 5; index++) {
        var temp2 = this.getSearchStoreList[index];
        geocoder.addressSearch(this.getSearchStoreList[0].storeAddr, function(result,status) {
        console.log("11111")
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          var marker = new kakao.maps.Marker({ map: map, position: coords });
          // 인포윈도우로 장소에 대한 설명을 표시합니다
          // console.log(this.getSearchStoreList[0]);
          console.log(temp2);
          var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+temp2.storeName+'</div>',
          });
          infowindow.open(map, marker); // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          map.setCenter(coords);
        }
      });
        

      }
      

    },
    addScript() {
      const script = document.createElement('script'); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${MAP_API_KEY}&libraries=services`;
      document.head.appendChild(script);
    },

    toDetail: function(store) {
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
  left: 25%;
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
