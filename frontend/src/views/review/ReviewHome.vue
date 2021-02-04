<template>
  <div id="box">
    <!-- 1. 검색창 -->
    <b-row>
      <b-col class="input-group mb-5" style="width: 50%;">
        <input class="form-control my-0 py-1 amber-border" type="text" placeholder="Search" aria-label="Search" 
        v-model="storeParamDto.searchWord">
        <div class="input-group-append">
          <span class="input-group-text amber lighten-3" id="basic-text1" style="background-color: white; cursor:pointer;">
            <b-icon icon="search" variant="black" 
            @click="FindStore"/>
          </span>
        </div>
      </b-col>
      <b-col>
        <!-- 게시글 작성을 위한 버튼 -->
        <b-button pill variant="primary" @click="CreateReview">+</b-button>
      </b-col>
    </b-row>

    <!-- 2. 추천 카테고리 -->
    <!-- for문으로 출력한다!!! -->
    <div>
    <b-card-group deck>
      <b-card bg-variant="white" text-variant="black" class="text-center">
        <div id="category" v-draggabilly v-packery-item class='packery-item'>
          <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=59" content="asdasd" alt="Image 1"></b-img>
          DMZ
        </div>
      </b-card>

      <b-card bg-variant="white" text-variant="black" class="text-center">
        <div id="category" v-draggabilly v-packery-item class='packery-item'>
          <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=54" alt="Image 2"></b-img>
          산
        </div>
      </b-card>
      
      <b-card bg-variant="white" text-variant="black" class="text-center">
        <div id="category" v-draggabilly v-packery-item class='packery-item'>
          <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=58" alt="Image 3"></b-img>
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



    <!-- <div v-draggabilly v-packery='{itemSelector: ".packery-item", percentPosition: true}'>
      <div v-draggabilly v-packery-item class='packery-item'>
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=59" alt="Image 1"></b-img>
      </div>
      <div v-draggabilly v-packery-item class='packery-item'>    
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=54" alt="Image 2"></b-img>
      </div>
      <div v-draggabilly v-packery-item class='packery-item'>
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=58" alt="Image 3"></b-img>
      </div>
      <div v-draggabilly v-packery-item class='packery-item'>
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=23" alt="Image 3"></b-img>
      </div>
      <div v-draggabilly v-packery-item class='packery-item'>
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=35" alt="Image 3"></b-img>
      </div>
      <div v-draggabilly v-packery-item class='packery-item'>
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=44" alt="Image 3"></b-img>
      </div>
    </div> -->

  </div>
</template>


<script>
// const userInfo = JSON.parse(localStorage.getItem('Login-token'))
export default {
 name: 'ReviewHome',
  data: function() {
    return {
      storeParamDto:{
        searchWord: "",
        // dongcode: userInfo["user_address"],
        dongcode: "1154562000"

      },
      


    };
  },
 methods: {
   FindStore: function () {
     this.$router.push({ name: 'GetStore', query: {datas:this.storeParamDto}})
   },
    CreateReview: function () {
     this.$router.push({ name: 'FindStore', query: {datas:this.storeParamDto}})
   }
 }
}
</script>

<style>
#category {
  cursor: pointer;
}
</style>