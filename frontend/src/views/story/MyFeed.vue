<template>
  <div id="box">
    <!-- 1. 프로필 -->
     <b-row class="mb-5">
      <b-col cols="4">
        <b-avatar size="8rem" variant="info" :src="require('@/assets/app/badge1.jpg')" style="cursor: pointer; width:"></b-avatar>
      </b-col>
      <!-- <b-col cols="1">
        <b-icon class="h2" icon="plus"></b-icon>
      </b-col> -->
      <b-col cols="6" align-self="center">
        <b-row align-h="center" class="mb-4">
          <b-col cols="3"><h2 style="font-family: 'Nanum Pen Script', cursive; display: inline;">{{ nickname }}  </h2></b-col>
          <b-col cols="2" class="mt-2"><b-icon icon="gear" font-scale="1.5" style="cursor: pointer;" type="bold" @click="toAccountDetail"></b-icon></b-col>
        </b-row>
        <b-row align-h="center">
          <b-col class="mx-0 px-0"><b-button disabled style="background-color: #695549;">게시물: {{ posts.length }}</b-button></b-col>
          <b-col class="mx-0 px-0"><b-button disabled style="background-color: #695549;">리뷰: 0</b-button></b-col>
          <b-col class="mx-0 px-0"><b-button disabled style="background-color: #695549;">그룹: 0</b-button></b-col>
        </b-row>
      </b-col>
    </b-row>
    <!-- 2. 탭 -->
    <b-tabs
      active-nav-item-class="font-weight-bold text-uppercase text-dark"
      content-class="mt-3"
      justified
    >
      <b-tab title="글" active>
        <div v-for="(post , idx) in posts" :key ="idx" ><PostBlockMy :post="post" /></div>
        
      </b-tab>
      <b-tab title="리뷰">
           <div v-for="(item, index) in reviews" :key="index">
         <ReivewBlock :review="item"  />
           </div>
      </b-tab>
      <b-tab title="태그">
        <TagBox />
      </b-tab>
      <b-tab title="그룹">
        <GroupBox />
      </b-tab>
    </b-tabs>

    <br>
    <br>
    <br>
  </div>
</template>


<script>
import GroupBox from '@/components/story/GroupBox'
import PostBlockMy from '@/components/story/PostBlockMy'
import TagBox from '@/components/story/TagBox'
import axios from "axios";
const SERVER_URL = "http://localhost:8000";

// import axios from 'axios';

// const SERVER_URL = "http://localhost:8000";

export default {
  name: 'MyFeed',
  components: {
    GroupBox,
    PostBlockMy,
    TagBox,
    ReivewBlock,
  },
  data() {
    return {
      userId: "",
      nickname: "",
      posts:[],
      limit: 5,  //한 페이지에 노출될 게시글의 수
      offset: 0,  //게시글 번호 오프셋,
      liked: false,
       reviews: Array,
    };
  },
  created() {
    this.nickname = JSON.parse(localStorage.getItem('Login-token'))['user-name']
    this.userId = JSON.parse(localStorage.getItem('Login-token'))['user-id']
    this.getUserPosts();
    this.getReview();
    
  },
  methods: {
      getUserPosts(){
      axios
        .get(`${SERVER_URL}/userpost/user`, {
          params: {
            userId: this.userId,
            limit: this.limit,
            offset: this.offset
          }
      })
        .then((response) => {
          this.posts = response.data.list;
          console.log(this.posts);
        });
    },
    toAccountDetail: function () {
      this.$router.push({name: 'AccountDetail'})
    },
    getReview: function() {
    axios
      .get(`${SERVER_URL}/review/user/` + `${this.userId}`)
      .then((response) => {
      
        this.reviews = response.data;
        console.log(this.reviews);
  
      })
      .catch((response) => {
        console.log(response);
      });
    },
  },

}
</script>


<style>

</style>