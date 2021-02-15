<template>
  <b-row class="my-2 pt-3 mx-5">

    <div id="box" v-if="!flag">
      <div class="mb-5">
        <h3>{{ storeParamDto.searchWord }}</h3>
        <b-icon id="map_btn" icon="map" font-scale="2" @click="LookMap()"></b-icon>
      </div>
      <div v-for="(item, index) in getSearchStoreList" :key="index">
        <b-card bg-variant="white" text-variant="black" :title="item.storeName">
          <b-card-text>
            {{ item.storeAddr }}
          </b-card-text>
          <b-button @click="toDetail(item)" variant="primary">상세</b-button>
        </b-card>
      </div>
    </div>


    <b-col class="mr-0 pr-0" v-if="flag">
      <div class="mb-5">
        <h3>{{ storeParamDto.searchWord }}</h3>
        <b-icon id="map_btn" icon="map" font-scale="2" @click="LookMap()"></b-icon>
      </div>
      <div v-for="(item, index) in getSearchStoreList" :key="index">
        <b-card bg-variant="white" text-variant="black" :title="item.storeName">
          <b-card-text>
            {{ item.storeAddr }}
          </b-card-text>
          <b-button @click="toDetail(item)" variant="primary">상세</b-button>
        </b-card>
      </div>
    </b-col >


      <b-col class="mt-5 pt-3 mr-3 ml-0 pl-0" v-if="flag" >
      <div class="map_wrap3" style="width: 80%;">
        <div id="map" style="width:100%; height:100%; position:relative; overflow:hidden;"></div>
        <div class="hAddr2">
          <!-- <span class="title">지금 계신 위치가 이곳이 맞나요?</span> -->
          <span></span>
        </div>
      </div>
    </b-col>
  </b-row>
</template>

<script>
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
const MAP_API_KEY = process.env.VUE_APP_MAP_API_KEY;
export default {
  name: 'ReviewList',
  data: function() {
    return {
      storeParamDto: {
        searchWord: '',
        dongcode: '',
      },
      flag: false,
      key: Object,
      temp: Object,
      makeMap : false,

      getSearchStoreList: {},
      getSearchareaList: [],
    };
  },
  created() {
    this.storeParamDto.dongcode = this.$route.params.address;
    this.storeParamDto.searchWord = this.$route.params.keyword;

    console.log(this.storeParamDto.searchWord);

    if (this.storeParamDto.searchWord != '가장 인기 많은 상점') {
      this.searchBestStore();
    } else {
      console.log('최다리뷰');
      this.searchBestStore2();
    }
  },
  mounted() {
    
  },
  methods: {
    searchBestStore: function() {
      axios
        .post(`${SERVER_URL}/store/bestctgstore`, this.storeParamDto)
        .then((response) => {
          // console.log(response.data);
          this.getSearchStoreList = response.data;
          console.log('res', this.getSearchStoreList);
        })
        .catch(() => {
          console.log('fail');
        });
      // ReviewList로 이동하기
      // this.$router.push({name: 'GroupPage', params: {address:  JSON.parse(localStorage.getItem('Login-token'))['user_address'], categorys : this.category }})
    },
    searchBestStore2: function() {
      axios
        .post(`${SERVER_URL}/store/beststore`, this.storeParamDto)
        .then((response) => {
          // console.log(response.data);
          this.getSearchStoreList = response.data;
          console.log('res', this.getSearchStoreList);
        })
        .catch(() => {
          console.log('fail');
        });
      // ReviewList로 이동하기
      // this.$router.push({name: 'GroupPage', params: {address:  JSON.parse(localStorage.getItem('Login-token'))['user_address'], categorys : this.category }})
    },
    toDetail: function(store) {
      console.log('toDetail');
      console.log(store);
      // 리뷰 작성 페이지로 넘어가준다!!
      this.$router.push({ name: 'ReviewDetail', params: { storeId: store.storeId } });
    },
    LookMap: function() {
      //기존 태그 사이즈 조정
      console.log("makeMap");
     // el.removeChild(el.lastChild);
      if(this.flag==false){
        this.flag = true;
        window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
      }else{
        this.flag = false;
      }
      if(this.makeMap==false){
        this.makeMap = true;
      //  window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
      }

        
      
      
    },
    initMap() {
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(37.501303210343146, 127.03961032748188),
        level: 3,
      };
      var map = new kakao.maps.Map(container, options);
      var imageSrc = require('@/assets/marker3.png'), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(24, 35), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(20, 35) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
      var bounds = new kakao.maps.LatLngBounds();
      var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      var displayInfowindow = function(marker, title) {
        var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';
        infowindow.setContent(content);
        infowindow.open(map, marker);
      };
      var rou = this.$router;
      var toDetail = function(store) {
        console.log('toDetail');
        console.log(store);
        // 리뷰 작성 페이지로 넘어가준다!!
        rou.push({ name: 'ReviewDetail', params: { storeId: store.storeId } });
      };
      var list = this.getSearchStoreList;
      for (let index = 0; index < this.getSearchStoreList.length; index++) {
        var coords = new kakao.maps.LatLng(
          this.getSearchStoreList[index].locLat,
          this.getSearchStoreList[index].locLng
        );
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: coords, // 마커를 표시할 위치locLng
          // title: this.getSearchStoreList[index].storeName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
        (function(marker, title) {
          kakao.maps.event.addListener(marker, 'click', function() {
            console.log('markerClick');
            console.log(list[index]);
            toDetail(list[index]);
          });
          kakao.maps.event.addListener(marker, 'mouseover', function() {
            displayInfowindow(marker, title);
          });
          kakao.maps.event.addListener(marker, 'mouseout', function() {
            infowindow.close();
          });
        })(marker, this.getSearchStoreList[index].storeName);
        //infowindow.open(map, marker); // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        bounds.extend(coords);
      }
      map.setBounds(bounds);
      // displayPagination(pagination);
    },
    addScript() {
      const script = document.createElement('script'); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${MAP_API_KEY}&libraries=services`;
      document.head.appendChild(script);
    },
  },
};
</script>

<style>
#map_btn {
  position: absolute;
  top: 0;
  right: 0;
  /* text-align: right; */
  cursor: pointer;
}
.map_wrap3 {
  position: relative;
  width: 50%;
  height: 500px;
  left: 5%;
}
.hAddr2 {
  position: absolute;
  right: 20px;
  top: 10px;
  border-radius: 2px;
  background: #fff;
  background: rgba(255, 255, 255, 0.8);
  z-index: 1;
  padding: 5px;
}
.fixedHeader {
	position: sticky;
	top: 0;
  background-color: rgb(231, 206, 173);
}
</style>
