<template>
  <b-row id="category" align-h="center">
    <b-col class="category packery-item" v-draggabilly v-packery-item>
      <div class="category_img" :style="category_img" >
      <div id="category_name" class="category_img_content" @click="toReviewList">
          <h4 >{{category}}</h4>
          <!-- <h2>{{ this.category.fileId }}</h2> -->
      </div>
      <div class="category_img_cover"></div>
    </div>
    </b-col>
  </b-row>
</template>

<script>
const userInfo = JSON.parse(localStorage.getItem('Login-token'))
// import axios from 'axios';
// const SERVER_URL = process.env.VUE_APP_SERVER_URL;
//import axios from "axios"

// const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: "CategoryCard",
  props: {
    category: String,
  },
  data: function() {
    return {
      urls: "",
      result: "",
      flag : false,
      bestCtgStore : {},
      storeParamDto:{
        searchWord: '',
        dongcode: userInfo["user_address"],
      },
      temp : this.category,
      
      // props한 이미지 가져오기

      category_img: {
       backgroundImage: 'url(\'' + require(`@/assets/category/${this.category.replace(/\//g,"")}.png`) + '\')',
      },
    };
  },
  methods: {
    toReviewList: function () {
      this.storeParamDto.searchWord = this.category;
      this.$router.push({ name: 'ReviewList', params: {category: this.category, address: this.storeParamDto.dongcode, keyword : this.storeParamDto.searchWord}})
      // ReviewList로 이동하기
      // this.$router.push({name: 'GroupPage', params: {address:  JSON.parse(localStorage.getItem('Login-token'))['user_address'], categorys : this.category }})
    },
    
  },
  mounted() {
    
  },
};
</script>

<style>
#category_name {
  cursor: pointer;
}

.category_img {
  position: relative;
  height: 100vh;
  background-size: cover;
  width: 12rem;
  height: 12rem;
}

.category_img_cover {
  position: absolute;
  height: 12rem;
  width: 12rem;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 1;
}

.category_img_content {
  position: absolute;
  top: 95px;
  left: 95px;
  transform: translate(-50%, -50%);
  font-size: medium;
  color: white;
  z-index: 2;
  text-align: center;
}
</style>
