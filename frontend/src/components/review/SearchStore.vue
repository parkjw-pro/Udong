<template>
  <div>
    <mdb-container style="width: 80%;">
      <mdb-input
        type="text"
        class="active-cyan-2 active-purple-2 mt-0 mb-3"
        placeholder = "지역 혹은 가게/기관명을 입력하세요"
        v-model="keyword" 
        @keypress.enter="search"
      />
    </mdb-container>
    <div class="mt-5">
      <div v-if="stores">
        <ul :v-for="(store, idx) in stores">
          <!-- for문 출력이 현재 안되는데 작업해보자!! -->
          <li @click="createArticle(store)">{{ store }}</li>
          <hr>
        </ul>
      </div>
      <div v-else>
        <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">
        <br>
        <h5 class="mt-3">검색 결과가 없어요 ㅠㅠ</h5>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { mdbInput, mdbContainer } from 'mdbvue';
  
export default {
  name: 'SearchStore',
  components: {
    mdbInput,
    mdbContainer,
  },
  data: function () {
    return {
      keyword: '',
      items: [
        '샘플',
        '홍콩반점',
        '새마을식당',
      ],
    }
  },
  computed: {
    stores: function () {
      return this.items
    }
  },
  methods: {
    search: function () {
      // 상점 조회 axios 요청 보내기!!!!
      axios.get()
    },
    createArticle: function(store) {
      // 리뷰 작성 페이지로 넘어가준다!!
      this.$router.push({ name: 'ArticleCreate', query: store})
    }
  }
}
</script>

<style>
  
  .active-cyan-2 input[type=text]:focus:not([readonly]) {
    border-bottom: 1px solid #4dd0e1;
    box-shadow: 0 1px 0 0 #4dd0e1;
  }
  
</style>