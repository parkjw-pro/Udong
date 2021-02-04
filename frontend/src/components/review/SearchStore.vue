<template>
  <div>
    <!-- <mdb-container style="width: 80%;">
      <mdb-input
        type="text"
        class="active-cyan-2 active-purple-2 mt-0 mb-3"
        placeholder="지역 혹은 가게/기관명을 입력하세요"
        v-model="keyword"
        onKeyDown="onKeyDown"
      />
    </mdb-container> -->

      <container style="width: 100%;">
      <input
        type="text"
        class="active-cyan-2 active-purple-2 mt-0 mb-3"
        placeholder="상점의 종류 및 상점명을 입력하세요"
        v-model="storeParamDto.searchWord"
        @keypress.enter="search"
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

    <table class="table table-hover">
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
        <tr
          v-for="(item, index) in getSearchStoreList"
          :key="index"
          @click="ReviewList(item)"
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
const SERVER_URL = 'http://localhost:8000';
export default {
  name: 'SearchStore',
  components: {
    // mdbInput,
    // mdbContainer,
  },
  data: function() {
    return {
      storeParamDto:{
        searchWord: "",
        dongcode: "",

      },
      key : Object,
      temp : Object,
      userId : "",

      // storeParamDto2:{
      //   searchWord: "",
      //   dongcode:  "",

      // },
      
      

      
      getSearchStoreList: {},
      getSearchareaList: [],
    };
  },
  computed: {
    before(){ 
      return this.temp;
    },

  },
  watch : {
      before(){ 
        this.storeParamDto = this.temp;
    this.search();
    },



  },
  mounted() {

    
    // this.storeParamDto.dongcode = userInfo["user_address"]
    // console.log(this.storeParamDto.dongcode);
    // this.user.nickname = userInfo["nickname"]
    // this.user.email = userInfo["email"]


  },
  created()  {

    this.key = this.$route.query.datas;
    console.log(this.key.dongcode);
    this.temp = this.key;
    console.log(this.temp)

    






  },
  methods: {
    search: function() {
      console.log('search');
      console.log(this.storeParamDto.searchWord);
      console.log(this.storeParamDto.dongcode);

      axios
      .post(`${SERVER_URL}/store/stores`, this.storeParamDto)
      .then((response) => {
        console.log(response.data);
        this.getSearchStoreList = response.data;
      })
      .catch(() => {
        console.log("fail")
      });
      
    },

    ReviewList: function(store) {
      // 리뷰 작성 페이지로 넘어가준다!!
      this.$router.push({ name: 'ReviewDetailList', query: {datas:store}});
    },
  },
};
</script>

<style>
.active-cyan-2 input[type='text']:focus:not([readonly]) {
  border-bottom: 1px solid #4dd0e1;
  box-shadow: 0 1px 0 0 #4dd0e1;
}
</style>
