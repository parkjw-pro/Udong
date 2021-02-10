<template>
  <div id="box" class="mx-5">
    <!-- 검색창 -->
    <b-row align-h="center">
      <b-nav-form>
        <b-form-input
          class="mr-sm-2 ml-auto" 
          placeholder="우리동네 이야기 찾기" 
          style="text-align: center;"
        ></b-form-input>
        <b-button style="background-color: #695549;" class="my-2 my-sm-0" type="submit">Search</b-button>
      </b-nav-form>
    </b-row>

    <!-- 그룹 고르는 공간 -->
    <div class="ml-4 mt-5 mb-2 font-weight-bold" style="text-align: left;">내 그룹</div>
    <b-row class="">
      <b-col v-if="groups.length > 0" md="7">
        <vue-glide
          :type="carousel" 
          :perView="3"
          :gap="0"
        >
          <vue-glide-slide v-for="(group, i) in groups" :key="i"><b-button  variant="info" @click="selectGroup(i)">{{group['clubName']}}</b-button></vue-glide-slide>
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
      <div class="mb-5" v-for="(post, i) in posts" :key="i">
        <PostBlock :post="post" />
      </div>
    </div>
    <div v-else>
      <div class="my-2">아직 우리동네에 게시물이 없네요</div>
      <div class="my-2">역사적인 순간!</div>
      <h5>우리 동네 첫 게시글을 작성해주세요<b-icon icon="heart-fill" variant="danger"></b-icon></h5>
    </div>
    <EndBlock v-on:more="getMorePosts" />
    <Button />
  </div>
</template>

<script>
import Button from '@/components/story/Button'
import EndBlock from '@/components/story/EndBlock'
import PostBlock from '@/components/story/PostBlock'

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
  },
  data: function () {
    return {
      groups: [],
      selected: 0,  //선택된 그룹
      postCount: 0,
      posts:[],
      limit: 5,  //한 페이지에 노출될 게시글의 수
      offset: 0,  //게시글 번호 오프셋
    }
  },
  created() {
    //가입한 그룹 정보 가져오기
    axios
      .get(`${SERVER_URL}/club/user/${this.getUserId}/member`)
      .then(
        (response) => (
          this.groups = response.data,
          this.getGroupPosts()
        )
      );
  },
  methods: {
    getGroupPosts(){
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
    selectGroup(idx){
      this.selected = idx;
      this.offset = 0;
      this.posts = [];
      this.getGroupPosts();
    },
    getMorePosts() {
      console.log("want to get more??");
      if(this.postCount < this.offset + this.limit) return;

      console.log("we have more~");

      this.offset += this.limit;
      this.getGroupPosts();
      console.log("got more posts~");
    },
    toList: function () {
      this.$router.push({ name: 'GroupList'})
    }
  }
}
</script>

<style>

</style>