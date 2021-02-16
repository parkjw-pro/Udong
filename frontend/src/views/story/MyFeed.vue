<template>
  <div id="box">
    <!-- 1. 프로필 -->
    <b-jumbotron>  <!-- head, lead 속성 사용가능! -->
      <b-row class="mb-5">
        <b-col cols="4">
          <b-avatar size="8rem" variant="info" :src="require('@/assets/app/badge/badge1.jpg')" style="cursor: pointer; width:"></b-avatar>
        </b-col>
        <b-col cols="6" align-self="center">
          <b-row align-h="center" class="mb-4">
            <b-col>
              <h2 v-if="$route.params.userId === user.userId" style="font-family: 'Nanum Pen Script', cursive; display: inline;">{{ this.user.nickname }} </h2>
              <h2 v-else style="font-family: 'Nanum Pen Script', cursive; display: inline;">{{ $route.params.nickname }} </h2>
              <b-icon v-if="$route.params.userId === user.userId" icon="gear-fill" font-scale="1.5" style="cursor: pointer;" type="bold" @click="toAccountDetail"></b-icon>
            </b-col>
          </b-row>
          <b-row align-h="center">
            <b-col class="mx-0 px-0"><b-button disabled style="background-color: #695549;">게시물: {{ posts.length }}</b-button></b-col>
            <b-col class="mx-0 px-0"><b-button disabled style="background-color: #695549;">리뷰: {{reviews.length}}</b-button></b-col>
            <b-col class="mx-0 px-0"><b-button disabled style="background-color: #695549;">그룹: {{groups.length}}</b-button></b-col>
          </b-row>
        </b-col>
      </b-row>
    </b-jumbotron>
     
    <!-- 2. 탭 -->
    <b-tabs
      active-nav-item-class="font-weight-bold text-uppercase text-dark"
      content-class="mt-3"
      justified
    >
      <b-tab title="글" active>
        <div v-for="(post , idx) in posts" :key ="idx" >
          <PostBlockMy :post="post" />
        </div>
        <EndBlock v-on:more="getMorePosts" />
        <span v-if="this.postCount > this.posts.length">더보기</span>
      </b-tab>
      <b-tab title="리뷰">
        <div v-for="(item, index) in reviews" :key="index">
          <ReviewBlock :review="item"  />
        </div>
      </b-tab>
      <b-tab title="태그">
        <TagBox :reviews="reviews" :userposts="posts" :user="user" />
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
import ReviewBlock from '@/components/review/ReviewBlock'
import PostBlockMy from '@/components/story/PostBlockMy'
import EndBlock from '@/components/story/EndBlock'
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
    EndBlock,
    TagBox,
    ReviewBlock,
  },
  data() {
    return {
      user: {
        userId: this.$route.params.userId,
        nickname: this.$route.params.nickname
      },
      posts: [],
      postCount: 0,
      limit: 5,  //한 페이지에 노출될 게시글의 수
      offset: 0,  //게시글 번호 오프셋,
      liked: false,
      reviews: [],
      groups: [],
    };
  },
  created() {
    this.getUserPosts();
    this.getReviews();
    this.getGroups();
  },
  methods: {
    getUserPosts(){
      axios
        .get(`${SERVER_URL}/userpost/user`, {
          params: {
            userId: this.user.userId,
            limit: this.limit,
            offset: this.offset
          }
      })
        .then((response) => {
          this.posts = response.data.list;
          this.postCount = response.data.count;
        });
    },
    toAccountDetail: function () {
      this.$router.push({name: 'AccountDetail'})
    },
    getReviews: function() {
    axios
      .get(`${SERVER_URL}/review/user/${this.user.userId}`)
      .then((response) => {
        this.reviews = response.data;
      })
      .catch((response) => {
        console.log(response);
      });
    },
    getGroups() {
      axios
      .get(`${SERVER_URL}/club/user/${this.user.userId}/member`)
      .then(
        (response) => (
          this.groups = response.data
        )
      );
    },
    getMorePosts() {
      if(this.postCount <= this.posts.length){
        return;
      }
      this.offset += this.limit;
      axios
        .get(`${SERVER_URL}/userpost/user`, {
          params: {
            userId: this.user.userId,
            limit: this.limit,
            offset: this.offset
          }
        })
        .then(
          (response) => {
            this.posts.push(...response.data.list);
        });
    },
    toList: function () {
      this.$router.push({ name: 'GroupList'})
    }
  },

}
</script>


<style>

</style>