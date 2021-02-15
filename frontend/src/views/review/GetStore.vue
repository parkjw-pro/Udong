<template>
  <div id="box">
    <h4 class="mb-3" style="font-family: 'Hanna', sans-serif;">우리 동네 장소를 검색해보세요!</h4>
        <input
          type="text"
          pill
          class="active-cyan-2 active-purple-2 mt-0 mb-3"
          placeholder="상점의 종류 혹은 상점명을 입력하세요"
          v-model="storeParamDto.searchWord"
          @keypress.enter="search"
          style="text-align: center; width: 65%;"
          autofocus
        >
        <b-button class="ml-3" size="sm" style="background-color: #695549;" @click="search">검색</b-button>
    <table class="table table-hover" striped hover style="background-color: #695549;">
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
        <tr
          v-for="(item, index) in getSearchStoreList"
          :key="index"
          @click="ReviewCreate(item.storeId)"
        >
          <td>{{ item.storeCtg2 }}</td>
          <td>{{ item.storeName }}</td>
          <td>{{ item.storeSidoName }}</td>
          <td>{{ item.storeGugunName }}</td>
          <td>{{ item.storeDong2Name }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';
// import { mdbInput, mdbContainer } from 'mdbvue';
const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'GetStore',
  components: {
  },
  data: function() {
    return {
      storeParamDto:{
        searchWord: "",
        dongcode: "",

      },
      key : Object,
      temp : Object,

      
      getSearchStoreList: {},
      getSearchareaList: [],
    };
  },
  computed: {
    stores: function() {
      return this.items;
    },
    before(){ 
       return this.temp;
    }
  },
  watch : {
      before(){ 
        this.storeParamDto = this.temp;
    this.search();
    },

  },
  
  methods: {
    search: function() {
      // console.log('search');
      // console.log(this.storeParamDto.dongcode);

      axios
      .post(`${SERVER_URL}/store/stores`, this.storeParamDto)
      .then((response) => {
        // console.log(response.data);
        this.getSearchStoreList = response.data;
      });
    },
    ReviewCreate: function(storeId) {
      // 리뷰 작성 페이지로 넘어가준다!!
      this.$router.push({ name: 'ReviewCreate', params: {storeId: storeId}});
    },
  },
 mounted () {
   this.storeParamDto.dongcode = this.$route.params.address
   this.search()
 }
}
</script>

<style>
.active-cyan-2 input[type='text']:focus:not([readonly]) {
  border-bottom: 1px solid #4dd0e1;
  box-shadow: 0 1px 0 0 #4dd0e1;
}
</style>