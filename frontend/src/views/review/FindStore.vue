<template>
  <b-row class="my-2 pt-3 mx-5">
    <!-- 1. 왼쪽 검색 테이블 -->
    <b-col class="mr-0 pr-0">
      <h4 class="mb-3" style="font-family: 'Hanna', sans-serif;">우리 동네 장소를 검색해보세요!</h4>
      <input
        type="text"
        pill
        class="active-cyan-2 active-purple-2 mt-0 mb-3"
        placeholder="상점명을 입력하세요!"
        v-model="storeParamDto.searchWord"
        @keypress.enter="searchWord"
        autofocus
        style="text-align: center; width: 40%;"
      />
      <b-button class="ml-3" size="sm" style="background-color: #695549;" @click="searchWord">검색</b-button>
     

      <div v-if="getSearchStoreList.length > 0" style="overflow: auto; height: 650px;">
        <table
          class="table table-hover"
          striped
          hover
         style="background-color: white; ">
          <thead style="color: black;" class="small">
            <tr>
              <th class="fixedHeader">종류</th>
              <th class="fixedHeader">이름</th>
              <th class="fixedHeader">주소</th>
            </tr>
          </thead>
          <tbody id="placesList">
            <!-- <tr v-for="(item, index) in getSearchStoreList" :key="index" @click="toDetail(item)">
            <td>{{ item.storeCtg3 }}</td>
            <td>{{ item.storeName }} {{ item.storeNameDetail }}</td>
            <td>{{ item.storeAddr }}</td>

          </tr> -->
          </tbody>
        </table>
        </div>

      <div v-else class="mt-5 pt-5">
        <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%" />
        <br />
        <h5 class="mt-3">검색 결과가 없어요 ㅠㅠ</h5>
      </div>
    </b-col>

    <!-- 2. 오른쪽 지도 -->
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
      //detail : this.toDetail(store),

      getSearchStoreList: {},
      getSearchareaList: [],
      pos: [],
      // infowindow : new kakao.maps.InfoWindow({zIndex:1}),
    };
  },
  computed: {
    storeList: function() {
      return this.getSearchStoreList;
    },

  },
  async created() {
    this.storeParamDto.dongcode = this.$route.params.address;
    if (this.storeParamDto.searchWord === '') {
      this.storeParamDto.searchWord = this.$route.params.keyword;
    }
    await this.search()
  },
  methods: {
    searchWord: function () {
      location.replace(`/store/find/${this.storeParamDto.dongcode}/${this.storeParamDto.searchWord}`)
    },
    initMap() {
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(37.501303210343146, 127.03961032748188),
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

      var listEl = document.getElementById('placesList');
      var fragment = document.createDocumentFragment();
      var displayInfowindow = function(marker, title) {
        var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

        infowindow.setContent(content);
        infowindow.open(map, marker);
      };
      var removeAllChildNods = function(el) {
        while (el.hasChildNodes()) {
          el.removeChild(el.lastChild);
        }
      };
      var rou = this.$router;
      var toDetail = function(store) {
        // 리뷰 작성 페이지로 넘어가준다!!
        rou.push({ name: 'ReviewDetail', params: { storeId: store.storeId } });
      };
      removeAllChildNods(listEl);
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
        var itemEl = this.getListItem(index, this.getSearchStoreList[index]);
        var list = this.getSearchStoreList;

        (function(marker, title) {
          kakao.maps.event.addListener(marker, 'click', function() {
            toDetail(list[index]);
          });
          kakao.maps.event.addListener(marker, 'mouseover', function() {
            displayInfowindow(marker, title);
          });
          kakao.maps.event.addListener(marker, 'mouseout', function() {
            infowindow.close();
          });
          itemEl.onclick = function() {
            toDetail(list[index]);
          };
          itemEl.onmouseover = function() {
            displayInfowindow(marker, title);
          };
          itemEl.onmouseout = function() {
            infowindow.close();
          };
        })(marker, this.getSearchStoreList[index].storeName);

        fragment.appendChild(itemEl);

        //infowindow.open(map, marker); // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        bounds.extend(coords);
      }
      listEl.appendChild(fragment);
      map.setBounds(bounds);
      // displayPagination(pagination);
    },
    addScript() {
      const script = document.createElement('script'); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${MAP_API_KEY}&libraries=services`;
      document.head.appendChild(script);
    },
    search() {
      axios
        .post(`${SERVER_URL}/store/stores`, this.storeParamDto)
        .then((response) => {
          // console.log(response.data);
          this.getSearchStoreList = response.data;
          window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
        })
        .catch(() => {
          // console.log('fail');
        });
    },
    getListItem(index, places) {
      var el = document.createElement('tr'),
        itemStr = '<td>' + places.storeCtg3 + '</td>';
      itemStr += '<td>' + places.storeName + ' ' + places.storeNameDetail + '</td>';
      itemStr += '<td>' + places.storeAddr + '</td>';

      el.innerHTML = itemStr;
      el.className = 'item';

      return el;
    },
  },
};
</script>

<style>
.active-cyan-2 input[type='text']:focus:not([readonly]) {
  border-bottom: 1px solid #4dd0e1;
  box-shadow: 0 1px 0 0 #4dd0e1;
}
.map_wrap2 {
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
.fixedHeader {
	position: sticky;
	top: 0;
  background-color: #695549;
  color: white;
}
</style>
