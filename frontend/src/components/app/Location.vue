<template>
  <div>
    <div id="title" class="my-3">
      <h3>우리동네를 찾아주세요!</h3>
    </div>
    <b-button style="background-color: #695549;" v-on:click="getLocation">클릭하여 우리 동네를 찾아보세요!</b-button>
    <br />
    <br />

    <div class="map_wrap">
      <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
      <div class="hAddr">
        <span class="title">지금 계신 위치가 이곳이 맞나요?</span>
        <span></span>
        <span id="centerAddr"></span>
        <span id="dong"></span>
      </div>
    </div>
    <div id="buttons" class="my-5">
      <b-button style="background-color: #695549;" v-on:click="createUserAddress">우리동네로 저장</b-button>
      <b-button style="background-color: #695549;" v-on:click="toHome">그냥 둘러볼게요</b-button>
    </div>
  </div>
</template>

<script>
//const MAP_API_KEY = process.env.MAP_API_KEY

export default {
  name: 'Location',
  data() {
    return {
      location: {
        lat: '37.50126268403',
        lng: '127.03955376031',
        dongcode: '11',
      },
      
    };
  },
    
  // 지도 API 가져오기
  mounted: async function() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();

    navigator.geolocation.getCurrentPosition(this.success, this.fail);
    
    // this.initMap()
    // this.addScript()

    //navigator 객체를 이용해 현재 위치를 받아온다.
    // navigator.geolocation.getCurrentPosition(this.success, this.fail);

    // await this.searchAddrFromCoords(this.location, this.displayCenterInfo);
  },
    computed: {
    before(){ 
      return this.$store.state.dongcode;
    }
  },
  watch : {
      before(){ 
    this.location.dongcode = this.$store.state.dongcode;
    }


  },
  methods: {
    success(location) {
      // 성공 시 Position 객체가 콜백함수에 전달된다.
      this.lat = location.coords.latitude; //위도
      this.lng = location.coords.longitude; //경도
      this.location.lat = this.lat;
      this.location.lng = this.lng;
      
      console.log(this.lat, this.lng);
    },
    fail(msg) {
      // 실패 시 PositionError 객체가 콜백함수에 전달된다.
      msg.code;
    },
    // searchAddrFromCoords(location, callback){
    //     this.geocoder.coord2RegionCode(location.lat, location.lng, callback);
    //   },
    // displayCenterInfo(result, status) {
    //         if (status === kakao.maps.services.Status.OK) {
    //     var infoDiv = document.getElementById('centerAddr');

    //     for(var i = 0; i < result.length; i++) {
    //         // 행정동의 region_type 값은 'H' 이므로
    //         if (result[i].region_type === 'H') {
    //             infoDiv.innerHTML = result[i].address_name;
    //             break;
    //         }
    //     }
    // }
    //  },
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
            // 행정동의 region_type 값은 'H' 이므로
            // if(i == result.length-1){
            //   this.dong =  this.dong + result[i].address_name;
            // }
            if (result[i].region_type === 'H') {

              infoDiv.innerHTML = result[i].address_name;
              dong.innerHTML = result[i].code;
              //this.asd(dong);
             // console.log(result[i].code);
             // this.$store.state.dongcode ="1111";
              // this.$store.state.dongcode = result[i].code;
              // this.location.dongcode = result[i].code;
              //this.location.dongcode = dong;
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
    asd(data){
      this.location.dongcode = data;

    },
    addScript() {
      const script = document.createElement('script'); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=40a71b1269cb975799557ecd007ad1fd&libraries=services`;
      document.head.appendChild(script);
    },
    createUserAddress: function() {
      this.location.dongcode = document.getElementById('dong').innerHTML;
      console.log(this.location.dongcode);
      this.$store
        .dispatch('createUserAddress', this.location.dongcode)
        .then(() => this.$router.replace(`/${this.nextRoute}`))
        .catch(({ message }) => (this.msg = message));
    },
    toHome: function () {
      this.$router.push({ name: 'Home' })
    }

    // addScript() {
    //   const script = document.createElement("script");
    //   /* global kakao */
    //   script.onload = () => kakao.maps.load(this.initMap);
    //   script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${MAP_API_KEY}`
    //   document.head.appendChild(script);
    // },
    // initMap() {
    //   var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
    //   var options = {
    //     //지도를 생성할 때 필요한 기본 옵션
    //     center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
    //     level: 3 //지도의 레벨(확대, 축소 정도)
    //   };

    //   var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    // }

    // 지도 표시하기
    // initMap: function () {
    //   var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    //   var options = { //지도를 생성할 때 필요한 기본 옵션
    //     center: new kakao.maps.LatLng(37.564343, 126.947613), //지도의 중심좌표.
    //     level: 3 //지도의 레벨(확대, 축소 정도)
    //   };
    //   var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

    //   //////////////////////////////////////////////////////////////////////////////////////////////////

    //   }
  },
  //     map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP); //ROADMAP HYBRID // 지도표시

  //     // 지도 타입 변경 컨트롤을 생성한다
  //     var mapTypeControl = new kakao.maps.MapTypeControl();

  //     // 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
  //     map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
  //   },

  //   addMarker(list_lat, list_lng, list_aptName) {
  //     var container = document.getElementById('map');
  //     var options = {
  //       center: new kakao.maps.LatLng(list_lat[0], list_lng[0]),
  //       level: 2,
  //     };
  //     var map = new kakao.maps.Map(container, options); // 지도생성

  //     // 마커 이미지의 이미지 주소입니다
  //     var imageSrc =
  //      // 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';
  //       'https://www.imaso.co.kr/wp-content/uploads/2019/03/IMG_9541.jpg';
  //     for (var i = 0; i < list_lat.length; i++) {
  //       // 마커 이미지의 이미지 크기 입니다
  //       var imageSize = new kakao.maps.Size(24, 35);

  //       // 마커 이미지를 생성합니다
  //       var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

  //       // 마커를 생성합니다
  //       var markerPosition = new kakao.maps.LatLng(list_lat[i], list_lng[i]);
  //       var marker = new kakao.maps.Marker({
  //         map: map, // 마커를 표시할 지도
  //         position: markerPosition, // 마커를 표시할 위치
  //         title: '', // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
  //         image: markerImage, // 마커 이미지
  //         clickable: true,
  //       });
  //       // var iwContent = <div style="padding:5px;">${list_aptName[i]}<br><a href="https://map.kakao.com/link/map/${list_aptName[i]},${list_lat[i]},${list_lng[i]}" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/${list_aptName[i]},${list_lat[i]},${list_lng[i]}" style="color:blue" target="_blank">길찾기</a></div>
  //       //   // iwRemoveable = true,// removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
  //       //   iwPosition = new kakao.maps.LatLng(list_lat[i], list_lng[i]), //인포윈도우 표시 위치입니다
  //       //   iwRemoveable = true;

  //       // 인포윈도우를 생성합니다
  //       var infowindow = new kakao.maps.InfoWindow({
  //         position: iwPosition,
  //         content: iwContent,
  //         removable : iwRemoveable
  //       });

  //       // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
  //       // 이벤트 리스너로는 클로저를 만들어 등록합니다
  //       // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
  //       kakao.maps.event.addListener(
  //         marker,
  //         'click',
  //         makeOverListener(map, marker, infowindow)
  //       );
  //     }
  //     // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
  //     function makeOverListener(map, marker, infowindow) {
  //       return function() {
  //         infowindow.open(map, marker);
  //       };
  //     }

  //     marker.setMap(map);
  //   },
  //   geocoder() {
  //     var list_lat = [];
  //     var list_lng = [];
  //     var list_aptName = [];
  //     for (
  //       let index = 0;
  //       index < this.$store.state.aptInfoList.length;
  //       index++
  //     ) {
  //       list_lat.push(this.$store.state.aptInfoList[index].lat);
  //       list_lng.push(this.$store.state.aptInfoList[index].lng);
  //       list_aptName.push(this.$store.state.aptInfoList[index].aptName);
  //       this.addMarker(list_lat, list_lng, list_aptName);
  //     }
  //   },
  //   geocoder_detail() {
  //     var list_lat = [];
  //     var list_lng = [];
  //     var detail_lat = 0.0;
  //     var detail_lng = 0.0;
  //     for (
  //       let index = 0;
  //       index < this.$store.state.aptInfoList.length;
  //       index++
  //     ) {
  //       list_lat.push(this.$store.state.aptInfoList[index].lat);
  //       list_lng.push(this.$store.state.aptInfoList[index].lng);
  //       console.log(this.$store.state.aptInfoList[index].aptName);
  //       if (
  //         this.$store.state.aptInfoList[index].aptName.indexOf(
  //           this.$store.state.detailEvent
  //         ) != -1
  //       ) {
  //         detail_lat = this.$store.state.aptInfoList[index].lat;
  //         detail_lng = this.$store.state.aptInfoList[index].lng;
  //       }
  //       console.log(detail_lat);
  //       console.log(detail_lng);
  //       this.detailMarker(list_lat, list_lng, detail_lat, detail_lng);
  //     }
  //   },
  //   detailMarker(list_lat, list_lng, detail_lat, detail_lng) {
  //     var container = document.getElementById('map');
  //     var options = {
  //       center: new kakao.maps.LatLng(detail_lat, detail_lng),
  //       level: 2,
  //     };
  //     var map = new kakao.maps.Map(container, options); // 지도생성

  //     // 마커 이미지의 이미지 주소입니다
  //     var imageSrc =
  //       // 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png';
  //       'https://www.imaso.co.kr/wp-content/uploads/2019/03/IMG_9541.jpg';
  //     for (var i = 0; i < list_lat.length; i++) {
  //       // 마커 이미지의 이미지 크기 입니다
  //       var imageSize = new kakao.maps.Size(24, 35);

  //       // 마커 이미지를 생성합니다
  //       var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

  //       // 마커를 생성합니다
  //       var markerPosition = new kakao.maps.LatLng(list_lat[i], list_lng[i]);
  //       var marker = new kakao.maps.Marker({
  //         map: map, // 마커를 표시할 지도
  //         position: markerPosition, // 마커를 표시할 위치
  //         title: '', // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
  //         image: markerImage, // 마커 이미지
  //         clickable: true,
  //       });
  //       // var iwContent = <div style="padding:5px;">${this.$store.state.detailEvent}<br><a href="https://map.kakao.com/link/map/${this.$store.state.detailEvent},${detail_lat},${detail_lng}" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/${this.$store.state.detailEvent},${detail_lat},${detail_lng}" style="color:blue" target="_blank">길찾기</a></div>, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
  //       //   // iwRemoveable = true,// removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
  //       //   iwPosition = new kakao.maps.LatLng(list_lat[i], list_lng[i]), //인포윈도우 표시 위치입니다
  //       //   iwRemoveable = true;

  //       // 인포윈도우를 생성합니다
  //       var infowindow = new kakao.maps.InfoWindow({
  //         position: iwPosition,
  //         content: iwContent,
  //         removable : iwRemoveable
  //       });

  //       // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
  //       // 이벤트 리스너로는 클로저를 만들어 등록합니다
  //       // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
  //       kakao.maps.event.addListener(
  //         marker,
  //         'click',
  //         makeOverListener(map, marker, infowindow)
  //       );
  //     }
  //     // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
  //     function makeOverListener(map, marker, infowindow) {
  //       return function() {
  //         infowindow.open(map, marker);
  //       };
  //     }

  //     marker.setMap(map);
  //     // (iwContent = <div style="padding:5px;">${this.$store.state.detailEvent}<br><a href="https://map.kakao.com/link/map/${this.$store.state.detailEvent},${detail_lat},${detail_lng}" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/${this.$store.state.detailEvent},${detail_lat},${detail_lng}" style="color:blue" target="_blank">길찾기</a></div>), // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
  //     //   //  iwRemoveable = true,// removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
  //     //   (iwPosition = new kakao.maps.LatLng(detail_lat, detail_lng)), //인포윈도우 표시 위치입니다
  //     //   (iwRemoveable = true);

  //     markerPosition = new kakao.maps.LatLng(detail_lat, detail_lng);
  //     marker = new kakao.maps.Marker({
  //       map: map, // 마커를 표시할 지도
  //       position: markerPosition, // 마커를 표시할 위치
  //       title: '', // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
  //       image: markerImage, // 마커 이미지
  //     });
  //     // 인포윈도우를 생성합니다
  //     infowindow = new kakao.maps.InfoWindow({
  //       position: iwPosition,
  //       content: iwContent,
  //       removable : iwRemoveable
  //     });

  //     // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
  //     infowindow.open(map, marker);
  // },
  // computed: {
  //   sucessdong() {
  //     console.log('sucessdong :' + this.$store.state.aptInfoList);

  //     return this.$store.state.aptInfoList;
  //   },
  //   clickDtail() {
  //     console.log('clickDtail :' + this.$store.state.detailEvent);

  //     return this.$store.state.detailEvent;
  //   },
  // },
  // watch: {
  //   sucessdong: function() {
  //     console.log(this.$store.state.aptInfoList);
  //     this.geocoder();
  //   },
  //   clickDtail: function() {
  //     console.log(this.$store.state.detailEvent);
  //     this.geocoder_detail();
  //   },
  // },
  // }
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
  left: 10px;
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
