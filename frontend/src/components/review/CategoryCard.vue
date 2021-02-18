<template>
  <b-row id="category" align-h="center">
    <b-col class="category packery-item" v-draggabilly v-packery-item>
      <div class="category_img" :style="category_img">
        <div id="category_name" class="category_img_content" @click="toReviewList">
          <h4 style="font-family: 'Jua', sans-serif;">{{ category }}</h4>
        </div>
        <div class="category_img_cover"></div>
      </div>
    </b-col>
  </b-row>
</template>

<script>
const userInfo = JSON.parse(localStorage.getItem('Login-token'));
// import axios from 'axios';
// const SERVER_URL = process.env.VUE_APP_SERVER_URL;
//import axios from "axios"

// const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'CategoryCard',
  props: {
    category: String,
  },
  data: function() {
    return {
      urls: '',
      result: '',
      flag: false,
      bestCtgStore: {},
      storeParamDto: {
        searchWord: '',
        dongcode: userInfo['user_address'],
      },
      temp: this.category,

      // props한 이미지 가져오기
      default: "url('" + require(`@/assets/category/default.png`) + "')",
      temp2: "url('" + require(`@/assets/category/${this.category.replace(/\//g, '')}.png`) + "')",

      category_img: {
        backgroundImage: "url('" + require(`@/assets/category/default.png`) + "')",
      },
    };
  },
  methods: {
    toReviewList: function() {
      this.storeParamDto.searchWord = this.category;
      this.$router.push({
        name: 'ReviewList',
        params: {
          category: this.category,
          address: this.storeParamDto.dongcode,
          keyword: this.storeParamDto.searchWord,
        },
      });
      // ReviewList로 이동하기
      // this.$router.push({name: 'GroupPage', params: {address:  JSON.parse(localStorage.getItem('Login-token'))['user_address'], categorys : this.category }})
    },
  },
  mounted() {},
  created() {
    // console.log(require(`@/assets/category/${this.category.replace(/\//g, '')}.png`));
    // if (
    //   "url('" + require(`@/assets/category/${this.category.replace(/\//g, '')}.png`) + "')" ==
    //   null
    // ) {
    //   this.category_img.backgroundImage = this.default;
    // }

    // try {
    //   console.log(require(`@/assets/category/${this.category.replace(/\//g, '')}.png`));
    // } catch (error) {
    //   this.category_img.backgroundImage = this.default;
    // }
    console.log(this.default);
    if (this.default == undefined) {
      // console.log(this.category);
      // console.log(this.default);
      // this.category_img.backgroundImage = this.default;
    } else {
      this.category_img.backgroundImage = this.temp2;
    }
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
  /* font-size: medium; */
  color: white;
  z-index: 2;
  text-align: center;
}
</style>
