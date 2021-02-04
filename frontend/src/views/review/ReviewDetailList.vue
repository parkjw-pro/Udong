<template>
  <div>
      <container style="width: 100%;">
            <table class="table table-hover">
      <tbody>
        <tr v-on="store">
         
          <td>{{ store.storeSidoName }} 
            {{ store.storeGugunName }} 
            {{ store.storeDong2Name }} 에 있는
            {{ store.storeName }} 의 리뷰입니다.
            </td>
        </tr>
      </tbody>
    </table>

    </container>

    <table class="table table-hover">
      <thead>
        <tr>
          <th>닉네임</th>
          <th>내용</th>
          <th>별점</th>
          <th>좋아요</th>

        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(item, index) in getStoreReviewList"
          :key="index"
          @click="ReviewDetail(item.reviewId)"
        >
          <td>{{ item.nickname }}</td>
          <td>{{ item.reviewContent }}</td>
          <td>{{ item.rate }}</td>
          <td>{{ item.reviewLikeCount }}</td>

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
  name: 'ReviewDetailList',
  components: {
    // mdbInput,
    // mdbContainer,
  },
  data: function() {
    return {
      key : Object,
      store : Object,
      

      
      getStoreReviewList: {},

    };
  },
  computed: {
    before3(){ 
      return this.store;
    }
  },
  watch : {
      before3(){ 
    this.search();
    }


  },
  created()  {

    this.store = this.$route.query.datas;
    console.log(this.store);
   // this.store = this.key;
   // console.log(this.store)
  },
  methods: {
    search: function() {
      console.log('reviewDetailList');
      console.log( this.store);
      axios
      .get(`${SERVER_URL}/review/store/` + "1234")
      .then((response) => {
        console.log(response.data);
        this.getStoreReviewList = response.data;
      })
      .catch((response) => {
        console.log(response);
      });
    },
    ReviewDetail: function(reviewId) {
      // 리뷰 작성 페이지로 넘어가준다!!
      this.$router.push({ name: 'ReviewDetail', query: {datas:reviewId}});
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
