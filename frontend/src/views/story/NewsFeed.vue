<template>
  <div>
    <div id="box" class="mx-5">
    <h1 id="top_title" class="mb-5">우리동네 이야기</h1>
      <!-- 검색창 -->
      <b-row align-h="center">
        <!-- <b-nav> -->
          <b-input
            class="mr-sm-2" 
            placeholder="우리동네 이야기 찾기" 
            v-model="searchWord"
            style="text-align: center; width: 30%;"
            @keypress.enter="search"
          ></b-input>
          <span><b-button style="background-color: #695549; text-align: left;" class="my-2 my-sm-0" @click="search">Search</b-button></span>
        <!-- </b-nav> -->
        <b-button class="ml-3" pill size="sm" variant="transparent" style="color: #695549;" v-b-popover.hover.top="'# 태그를 이용해 검색해보세요!'" title="검색 팁!">검색팁</b-button>
      </b-row>

      <!-- 그룹 고르는 공간 -->
      <div class="ml-4 mt-5 mb-2 font-weight-bold" style="text-align: left;">내 그룹</div>
      <b-row class="">
        <b-col v-if="groups.length > 0" md="7">
          <vue-glide
            type="carousel"
            :perView="perView"
            :gap="0"
          >
            <vue-glide-slide v-for="(group, i) in groupNames" :key="i"><b-button pill variant="outline-info" @click="selectGroup(i)">{{group}}</b-button></vue-glide-slide>
            <template slot="control">
              <b-button variant="transparent" size="sm" data-glide-dir="<">◀</b-button>
              <b-button variant="transparent" size="sm" data-glide-dir=">">▶</b-button>
            </template>
            <!-- <vue-glide-slide v-if="i != selected"><b-button  variant="secondary" @click="selectGroup(i)">{{group['clubName']}}</b-button></vue-glide-slide>
            <vue-glide-slide v-else><b-button variant="primary">{{group['clubName']}}</b-button></vue-glide-slide> -->
            
          </vue-glide>
        </b-col>
        <b-col v-else md="7" class="mt-2">
          <div>우리동네 그룹을 찾아보세요! 👉</div>
        </b-col>
        <b-col md="5">
          <b-button style="background-color: #695549;" @click="toList" end>우리동네 그룹</b-button> <!-- variant="outline-secondary" 속성 -->
        </b-col>
      </b-row>

      <div v-if="posts.length > 0">
        <div v-if="selected == 0">
          <PostBlockMy :post="post" v-for="(post , idx) in posts" :key ="idx" />
        </div>
        <div v-else>
          <PostBlock :post="post" :groupName="groupNames[selected]" class="mb-5" v-for="(post, i) in posts" :key="i" />
        </div>
      </div>
      <div v-else>
        <div class="my-2">아직 우리동네에 게시물이 없네요</div>
        <div class="my-2">역사적인 순간!</div>
        <h5>우리 동네 첫 게시글을 작성해주세요<b-icon icon="heart-fill" variant="danger"></b-icon></h5>
      </div>
      <EndBlock v-on:more="getMorePosts" />
    </div>
    <Button />
  </div>
</template>

<script>
import Button from '@/components/story/Button'
import EndBlock from '@/components/story/EndBlock'
import PostBlock from '@/components/story/PostBlock'
import PostBlockMy from '@/components/story/PostBlockMy'

import { Glide, GlideSlide } from 'vue-glide-js'

import { mapGetters } from "vuex";
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'NewsFeed',
  computed: {
    ...mapGetters(["getUserId"]),
    ...mapGetters(["getUserName"]),
    [Glide.name]: Glide,
    [GlideSlide.name]: GlideSlide,
  },
  components: {
    Button,
    EndBlock,
    PostBlock,
    PostBlockMy,
  },
  data: function () {
    return {
      perView: 3,
      groups: [], // 가입한 그룹
      groupNames: [],
      selected: 0,  //선택된 그룹
      postCount: 0,
      posts:[],
      limit: 5,  //한 페이지에 노출될 게시글의 수
      offset: 0,  //게시글 번호 오프셋
      searchWord: "",
    }
  },
  created() {
    //가입한 그룹 정보 가져오기
    axios
      .get(`${SERVER_URL}/club/user/${this.getUserId}/member`)
      .then(
        (response) => {
          this.groups.push(response.data[0]);  //groupNames와 idx 맞춰주기 위해 [0]번째에 dummy data 넣어줌
          this.groups.push(...response.data);
          this.getGroupNames(response.data);
          if(this.perView > this.groups.length) this.perView = this.groups.length;
        }
      );
    this.getPosts();
  },
  methods: {
    getGroupNames(data){
      this.groupNames.push('모두의 이야기');
      for(var g of data){
        this.groupNames.push(g.clubName);
      }
    },
    selectGroup(idx){
      this.searchWord = "";
      this.selected = idx;
      this.offset = 0;
      this.posts = [];
      this.getPosts();
    },
    getPosts(){
      if(this.selected == 0) {  //userpost 전체 조회
        this.getUserPosts();
      } else { //groupPosts
        this.getGroupPosts();
      }
    },
    getUserPosts() {
      axios
        .get(`${SERVER_URL}/userpost`, {
          params: {
            limit: this.limit,
            offset: this.offset
          }
        })
        .then((response) => {
            this.posts.push(...response.data.list);
            this.postCount = response.data.count;
        });
    },
    getGroupPosts() {
      axios
        .get(`${SERVER_URL}/clubpost/club`, {
          params: {
            clubId: this.groups[this.selected]['clubId'],
            limit: this.limit,
            offset: this.offset
          }
        })
        .then((response) => {
            this.posts.push(...response.data.list);
            this.postCount = response.data.count;
        });
    },
    getMorePosts() {
      if(this.postCount <= this.posts.length){
        return;
      }
      this.offset += this.limit;
      if(this.searchWord == ""){
        this.getPosts();
      } else {
        if(this.selected == 0){
          this.userPostSearch();
        } else {
          this.groupPostSearch();
        }
      }
    },
    search() {
      this.offset = 0;  //오프셋 초기화
      this.posts = [];  //post 리스트 초기화
      if(this.selected == 0){
        this.userPostSearch();
      } else {
        this.groupPostSearch();
      }
    },
    userPostSearch(){
      axios
        .get(`${SERVER_URL}/userpost/word`, {
          params: {
            searchWord: this.searchWord,
            limit: this.limit,
            offset: this.offset
          }
        })
        .then((response) => {
          this.postCount = response.data.count;
          this.posts.push(...response.data.list);
        });
    },
    groupPostSearch(){
      axios
        .get(`${SERVER_URL}/clubpost/club/word`, {
          params: {
            clubId: this.groups[this.selected]['clubId'],
            searchWord: this.searchWord,
            limit: this.limit,
            offset: this.offset
          }
        })
        .then((response) => {
          this.postCount = response.data.count;
          this.posts.push(...response.data.list);
        });
    },
    toList: function () {
      this.$router.push({ name: 'GroupList'})
    }
  }
}
</script>

<style>

</style>