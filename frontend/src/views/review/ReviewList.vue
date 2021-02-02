<template>
  <div id="box">
    <div class="mb-5">
      <h3>카테고리명</h3>
      <b-icon id="map_btn" icon="map" font-scale="2"></b-icon>
    </div>
    <!-- v-for="(idx, store) in stores" :key=idx -->
    <div >
      <b-card bg-variant="white" text-variant="black" :title="stores[1].storeName">
        <b-card-text>
          {{ stores}}
        </b-card-text>
        <b-button href="#" variant="primary">상세</b-button>
      </b-card>
    </div>
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
  ]
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