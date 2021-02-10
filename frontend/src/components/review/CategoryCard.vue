<template>
  <b-col id="category" v-draggabilly v-packery-item>
    <b-img thumbnail fluid class="category_img mx-4 my-4" :style="category_img" @click="toReviewList">
      <div class="category_img_content">
        <!-- <h2>{{ this.category }}</h2> -->
        <h2>자전거</h2>
      </div>
      <div class="category_img_cover"></div>
    </b-img>
  </b-col>
</template>

<script>
//import axios from "axios"

// const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: "CategoryCard",
  props: {
    category: Object,
  },
  data: function() {
    return {
      urls: "",
      result: "",
      // props한 이미지 가져오기
      category_img: {
        // backgroundImage: `url(${SERVER_URL}/club/download/${this.category.fileId})`,
        backgroundImage: "url(https://picsum.photos/250/250/?image=9)",
      },
    };
  },
  methods: {
    toReviewList: function () {
      // this.$router.push({name: 'GroupPage', params: {address:  JSON.parse(localStorage.getItem('Login-token'))['user_address'], categorys : this.category }})
    }
  },
  mounted() {
    
  },
};
</script>

<style>
.category_img {
  position: relative;
  height: 100vh;
  background-size: cover;
  width: 300px;
  height: 300px;
}

.category_img_cover {
  position: absolute;
  height: 300px;
  width: 300px;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 1;
}

.category_img_content {
  position: absolute;
  top: 280px;
  left: 70px;
  transform: translate(-50%, -50%);
  font-size: 5rem;
  font-weight: bold;
  color: white;
  z-index: 2;
  text-align: center;
}
</style>
