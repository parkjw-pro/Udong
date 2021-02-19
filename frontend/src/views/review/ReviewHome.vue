<template>
  <div id="box">
    <!-- 1. 검색창 -->
    <b-row align-h="center">
      <b-col cols="6" class="input-group">
        <input 
          pill class="form-control my-0 py-1 amber-border"
          type="text"
          placeholder="우리동네 리뷰를 검색하세요!"
          aria-label="Search" 
          v-model="storeParamDto.searchWord"
          @keypress.enter="FindStore"
          style="text-align: center;"
          autofocus
        >
        <div class="input-group-append">
          <span class="input-group-text amber lighten-3" id="basic-text1" style="background-color: white; cursor:pointer;">
            <b-icon icon="search" variant="black" @click="FindStore"/>
          </span>
        </div>
        <b-button id="ReviewHomeTip" class="ml-3" pill size="sm" variant="transparent" style="color: #695549;">검색팁</b-button>
        <b-tooltip target="ReviewHomeTip">카테고리, 상점명 등을 검색해보세요!</b-tooltip>
      </b-col>
      
    </b-row>
    <!-- 게시글 작성을 위한 버튼 -->
    <hr class="mb-5">
    <!-- 2. 추천 카테고리 -->
    <!-- for문으로 출력한다!!! -->
    <div>
      <b-row align-h="center" >
        <b-card-group deck style="width: 75%;">
          <CategoryCard category="리뷰 많은 상점"/>
          <CategoryCard category="패스트푸드"/>
          <CategoryCard category="호프/맥주"/>
        </b-card-group>
      </b-row>
      <b-row align-h="center">
        <b-card-group deck style="width: 75%;">
          <CategoryCard category="커피전문점/카페/다방"/>
          <CategoryCard category="수퍼마켓"/>
          <CategoryCard category="후라이드/양념치킨"/>
        </b-card-group>
      </b-row>
      <b-row align-h="center">
        <b-card-group deck style="width: 75%;">
          <CategoryCard category="편의점"/>
          <CategoryCard category="라면김밥분식"/>
          <CategoryCard category="호텔/콘도"/>
        </b-card-group>
      </b-row>
    </div>
  </div>
</template>


<script>
import CategoryCard from '@/components/review/CategoryCard'
const userInfo = JSON.parse(localStorage.getItem('Login-token'))
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'ReviewHome',
  components: {
    CategoryCard,
  },
  data: function() {
    return {
      storeParamDto:{
        searchWord: "",
        dongcode: userInfo["user_address"],
        //  dongcode: "1168064000",
        category_img: {
        // backgroundImage: `url(${SERVER_URL}/club/download/${this.category.fileId})`,
        backgroundImage: "url(https://picsum.photos/250/250/?image=9)",
      },
      },
      bestStoreList : {},
      bestCtgList : [],
    };
  },
  async mounted() {
   // await this.search();
  },
  methods: {
    FindStore: function () {
      if (this.storeParamDto.searchWord === "") {
        alert("검색어를 입력하세요!")
      } else {
        this.$router.push({ name: 'FindStore', params: {address: this.storeParamDto.dongcode ,keyword : this.storeParamDto.searchWord}})
      }
    },
      CreateReview: function () {
      this.$router.push({ name: 'GetStore', params: {address : this.storeParamDto.dongcode}})
    },
    search: function() {
      console.log(this.storeParamDto.dongcode);
      axios
        .get(`${SERVER_URL}/store/beststore/` + `${this.storeParamDto.dongcode}`)
        .then((response) => {
          // console.log(response.data);
          this.bestCtgList = response.data;
          // console.log("res",this.bestCtgList);
          // if(this.getSearchStoreList!=null){
          //   window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
          // }
        })
        .catch(() => {
          console.log('fail');
        });
    },
    
  }
}
</script>

<style>
</style>