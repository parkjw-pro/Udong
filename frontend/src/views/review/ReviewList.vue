<template>
  <div id="box">
    <div class="mb-5">
      <h3>카테고리명</h3>
      <b-icon id="map_btn" icon="map" font-scale="2"></b-icon>
    </div>
    <div id="dummy">
      <h5>페이지가 정상적으로 작동중입니다!!!!</h5>
      <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">
      <div>데이터 for문을 작성해주세요 개발자님!!!</div>
    </div>    
    <!-- for문 돌리기!!!! -->
    <!-- v-for="(idx, store) in stores" :key=idx -->
    <!-- <div v-for="(idx, stores) in stores" :key=idx >
      <b-card bg-variant="white" text-variant="black" :title="stores[1].storeName">
        <b-card-text>
          {{ stores }}
        </b-card-text>
        <b-button href="#" variant="primary">상세</b-button>
      </b-card>
    </div> -->
  </div>
</template>

<script>
export default {
  name: 'ReviewList',
  stores: [
    {
      storeCtg_2: '한식',
      storeDongName: '역삼동',
      storeName: '엄마김치찌개'
    },
    {
      storeCtg_2: '중식',
      storeDongName: '역삼동',
      storeName: '양자강'
    },
    {
      storeCtg_2: '일식',
      storeDongName: '역삼동',
      storeName: '코코코코코코로'
    },
  ],
   data: function() {
    return {
      storeParamDto:{
        searchWord: "",
        dongcode: "1168064000",

      },
      key : Object,
      temp : Object,

      
      getSearchStoreList: {},
      getSearchareaList: [],
    };
  },
}
</script>

<style>
#map_btn {
  position: absolute;
  top: 0;
  right: 0;
  /* text-align: right; */
  cursor: pointer;
}
</style>