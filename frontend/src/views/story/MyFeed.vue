<template>
  <div id="box">
    <!-- 1. 프로필 -->
     <b-row class="mb-5">
      <b-col cols="3">
        <Profile2 />
      </b-col>
      <b-col cols="1">
        <b-icon class="h2" icon="plus"></b-icon>
      </b-col>
      <b-col cols="2"></b-col>
      <b-col cols="5">
        <b-row cols="1"><h2>{{ nickname }}<small>의 피드</small></h2></b-row>
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
import ReivewBlock from '@/components/review/ReviewBlock'
import Profile2 from '@/components/app/Profile2'
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
    Profile2,
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