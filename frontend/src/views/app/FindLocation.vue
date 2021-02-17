<template>
  <div id="box">
    <h3>방문할 동네를 선택해주세요!</h3>
    <!-- <button type="button" v-on:click="getLocation">클릭하여 우리 동네를 찾아보세요!</button> -->
    <br />
    <br />

    <div class="map_wrap">
      <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
      <div class="hAddr">
        <!-- <span class="title">지금 계신 위치가 이곳이 맞나요?</span> -->
        <span></span>
        <span id="centerAddr"></span>
        <span id="dong" style="display: none;"></span>
      </div>
    </div>
    <div class="mt-1">현재위치가 아닌가요? <a href="" @click="relocation">다시찾기</a></div>
    <div class="mt-5">
      <b-button class="mr-3" style="background-color: #695549;" v-on:click="createUserAddress()"
        >여기로 갈께요!</b-button
      >
      <b-button class="ml-3" style="background-color: #695549;" v-on:click="addUserAddress()"
        >역삼동으로 갈께요!</b-button
      >
    </div>
  </div>
</template>

<script>
// if (document.location.protocol == 'http:') {
//     document.location.href = document.location.href.replace('http:', 'https:');
// }

import axios from 'axios';

const MAP_API_KEY = process.env.VUE_APP_MAP_API_KEY;
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'FindLocation',
  data() {
    return {
      location: {
        lat: '37.50126268403',
        lng: '127.03955376031',
      },
      userLocation: {
        addressCode: '11',
        addressName: '',
        userId: '',
      },
    };
  },
  // 지도 API 가져오기
  mounted: async function() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();

    navigator.geolocation.getCurrentPosition(this.success, this.fail);

    this.userLocation.userId = JSON.parse(localStorage.getItem('Login-token'))['user-id'];

    //navigator 객체를 이용해 현재 위치를 받아온다.
    // navigator.geolocation.getCurrentPosition(this.success, this.fail);

    // await this.searchAddrFromCoords(this.location, this.displayCenterInfo);
  },
  // computed: {
  //   before() {
  //     return this.$store.state.addressCode;
  //   },
  // },
  // watch: {
  //   before() {
  //     this.userLocation.addressCode = this.$store.state.addressCode;
  //   },
  // },
  methods: {
    success(location) {
      // 성공 시 Position 객체가 콜백함수에 전달된다.
      this.lat = location.coords.latitude; //위도
      this.lng = location.coords.longitude; //경도
      this.location.lat = this.lat;
      this.location.lng = this.lng;

      // console.log(this.lat, this.lng);
    },
    fail(msg) {
      // 실패 시 PositionError 객체가 콜백함수에 전달된다.
      msg.code;
    },
    initMap() {
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(this.location.lat, this.location.lng),
        level: 5,
      };
      var map = new kakao.maps.Map(container, options);
      var marker = new kakao.maps.Marker({ position: map.getCenter() });

      marker.setMap(map);
      var geocoder = new kakao.maps.services.Geocoder();
      var callback = function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          var infoDiv = document.getElementById('centerAddr');
          var dong = document.getElementById('dong');

          for (var i = 0; i < result.length; i++) {
            if (result[i].region_type === 'H') {
              const address = result[i].address_name;
              infoDiv.innerHTML = address;
              dong.innerHTML = result[i].code;

              // if (address) {
              //   this.dongName = address.split(" ",3)[2]
              // }
              break;
            }
          }
          //  this.asd(dong);
        }
      };
      var searchAddrFromCoords = function(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
      };

      searchAddrFromCoords(map.getCenter(), callback);

      // geocoder.coord2RegionCode(this.location.lat, this.location.lng, callback);
      //console.log(geocoder);
      //마커추가하려면 객체를 아래와 같이 하나 만든다.

      // console.log(map.getCenter());
    },
    // asd(data) {
    //   this.userLocation.addressCode = data;
    // },

    addScript() {
      const script = document.createElement('script'); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${MAP_API_KEY}&libraries=services`;
      document.head.appendChild(script);
    },
    addUserAddress: function() {
      const userInfo = JSON.parse(localStorage.getItem('Login-token'));
      userInfo.user_address = '1168065000';
      userInfo.user_address_name = '역삼2동';
      localStorage.setItem('Login-token', JSON.stringify(userInfo));

      this.userLocation.addressCode = '1168065000';
      this.userLocation.addressName = '역삼2동';
      axios
        .post(`${SERVER_URL}/user/address`, this.userLocation)
        .then(() => {
          // console.log(response.data);
          location.replace('/home');
          // window.location.reload(true);
          // this.$router.push({ name: 'Home'});
        })
        .catch((response) => {
          console.log(response);
        });
    },
    createUserAddress: function() {
      this.userLocation.addressCode = document.getElementById('dong').innerHTML;
      var arealist = document.getElementById('centerAddr').innerHTML.split(' ', 4);
      this.userLocation.addressName = arealist[arealist.length - 1];
      // console.log(this.userLocation.addressCode);
      // console.log(this.userLocation.userId);
      const userInfo = JSON.parse(localStorage.getItem('Login-token'));
      userInfo.user_address = this.userLocation.addressCode;
      userInfo.user_address_name = this.userLocation.addressName;
      localStorage.setItem('Login-token', JSON.stringify(userInfo));
      // localStorage.setItem("auth-token",response.data["auth-token"]);
      axios
        .post(`${SERVER_URL}/user/address`, this.userLocation)
        .then(() => {
          // console.log(response.data);
          location.replace('/home');
          // window.location.reload(true);
          // this.$router.push({ name: 'Home'});
        })
        .catch((response) => {
          console.log(response);
        });
    },
    relocation: function() {
      location.reload(true);
    },
  },
};
</script>

<style>
.map_wrap {
  position: relative;
  width: 70%;
  height: 700px;
  left: 15%;
}
.title {
  font-weight: bold;
  display: block;
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
#centerAddr {
  display: block;
  margin-top: 2px;
  font-weight: normal;
}
.bAddr {
  padding: 5px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
</style>
