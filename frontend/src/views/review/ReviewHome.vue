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
      </b-col>
      
    </b-row>
    <!-- 게시글 작성을 위한 버튼 -->
    <b-row>
      <b-col offset="8" @click="CreateReview">
        리뷰작성<b-button pill variant="transparent" class="font-weight-bold" size="lg">+</b-button>
      </b-col>
    </b-row>
    <hr class="mb-5">
    <!-- 2. 추천 카테고리 -->
    <!-- for문으로 출력한다!!! -->
    <div>
      <b-card-group deck>
        <b-card bg-variant="white" text-variant="black" class="text-center">
          <div id="category" v-draggabilly v-packery-item class='packery-item'>
            <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=9" content="asdasd" alt="Image 1"></b-img>
            전자기기
          </div>
        </b-card>

        <b-card bg-variant="white" text-variant="black" class="text-center">
          <div id="category" v-draggabilly v-packery-item class='packery-item'>
            <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=30" alt="Image 2"></b-img>
            카페
          </div>
        </b-card>
        
        <b-card bg-variant="white" text-variant="black" class="text-center">
          <div id="category" v-draggabilly v-packery-item class='packery-item'>
            <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=118" alt="Image 3"></b-img>
            건축물
          </div>
        </b-card>
      </b-card-group>
    </div>
    <div class="mt-3">
      <b-card-group deck>
        <b-card bg-variant="white" text-variant="black" class="text-center">
          <div id="category" v-draggabilly v-packery-item class='packery-item'>
            <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=23" alt="Image 3"></b-img>
            주방용품
          </div>
        </b-card>

        <b-card bg-variant="white" text-variant="black" class="text-center">
          <div id="category" v-draggabilly v-packery-item class='packery-item'>
            <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=35" alt="Image 3"></b-img>
            과일
          </div>
        </b-card>

        <b-card bg-variant="white" text-variant="black" class="text-center">
          <div id="category" v-draggabilly v-packery-item class='packery-item'>
            <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=44" alt="Image 3"></b-img>
            바다
          </div>
        </b-card>
      </b-card-group>
    </div>
  </div>
</template>


<script>
const userInfo = JSON.parse(localStorage.getItem('Login-token'))

export default {
 name: 'ReviewHome',
  data: function() {
    return {
      storeParamDto:{
        searchWord: "",
        dongcode: userInfo["user_address"],
        //  dongcode: "1168064000",

      },
    };
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
   }
 }
}
</script>

<style>
#category {
  cursor: pointer;
}
</style>