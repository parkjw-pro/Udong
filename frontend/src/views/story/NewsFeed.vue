<template>
  <div id="box" class="mx-5">
    <!-- 검색창 -->
    <b-nav-form>
      <b-form-input class="mr-sm-2 ml-auto" placeholder="Search"></b-form-input>  <!-- 가운데 정렬이 잘 안된다!!!! ㅠㅠㅠ -->
      <b-button variant="outline-success" class="my-2 my-sm-0" type="submit">Search</b-button>
    </b-nav-form>

    <!-- 그룹 고르는 공간 -->
    <b-row class="my-5 ">
      <b-col md="7">
        <b-button-group v-for="(group, i) in groups" :key="i">
          <b-button v-if="i != selected" variant="secondary" @click="selectGroup(i)">{{group['clubName']}}</b-button>
          <b-button v-else variant="primary">{{group['clubName']}}</b-button>
        </b-button-group>
      </b-col>
      <b-col md="5">
        <b-button style="background-color: #695549;" @click="toList" end>+</b-button> <!-- variant="outline-secondary" 속성 -->
      </b-col>
    </b-row>

    <div v-if="posts.length > 0">
      <div class="mb-5" v-for="(post, i) in posts" :key="i"> <!-- for문 넣기 -->
        <PostBlock :post="post" />
      </div>
    </div>
    <div v-else>
      <h3>게시물이 없어요...</h3>
    </div>
    <EndBlock v-on:more="getMorePosts" />
    <Button />
  </div>
</template>

<script>
import Button from '@/components/story/Button'
import EndBlock from '@/components/story/EndBlock'
import PostBlock from '@/components/story/PostBlock'

import { mapGetters } from "vuex";
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'NewsFeed',
  computed: {
    ...mapGetters(["getUserId"]),
    ...mapGetters(["getUserName"])
  },
  components: {
    Button,
    EndBlock,
    PostBlock,
  },
  data: function () {
    return {
      colors: ["danger", "warning", "success", "primary"],
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
      this.posts = {};
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