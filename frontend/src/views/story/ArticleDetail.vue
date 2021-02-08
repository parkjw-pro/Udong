<template>
  <!-- 이미지 추가하는 부분을 넣어야 한다!!! -->
  <div id="article_box">
    <b-row class="mb-5" align-h="between" v-if="true">
      <b-col><h5 class="font-weight-bold">그룹</h5></b-col>
      <b-col><h5 class="font-weight-bold">다이어트</h5></b-col>
    </b-row>

     <!-- 이미지 슬라이드 -->
    <ImageSlick />

      <b-form-textarea
        class="mb-5"
        id="textarea"
        :placeholder="this.post.postContent"
        rows="8"
        disabled
      ></b-form-textarea>
      <br>

      <b-row class="h2 mb-2" align-h="between">
        <div id="like"><!--like 개수와 조회하는 사람이 like 눌렀는지-->
          <b-icon icon="suit-heart-fill" variant="danger" v-if="liked"></b-icon>
          <b-icon icon="suit-heart" variant="danger" v-else></b-icon><span>{{post.postLikeCount}}</span>
        </div>
        <div>
          <b-icon icon="chat" variant="warning"></b-icon>
          <span>{{post.postCommentCount}}</span>
        </div>
        <b-dropdown size="lg" dropup variant="link" toggle-class="text-decoration-none" no-caret>
          <template #button-content>
            <b-icon icon="three-dots-vertical"></b-icon>
          </template>
          <b-dropdown-item href="#" variant="danger" v-if="post.userId == getUserId">삭제</b-dropdown-item>
          <b-dropdown-item href="#" variant="danger" v-else>신고</b-dropdown-item>
        </b-dropdown>
      </b-row>
      
      <br>
      <br>
      <!-- 댓글 -->
      <b-list-group style="text-align: left;" v-for="comment in comments" :key="comment.commentId">  <!-- for 문 -->
        <b-list-group-item>
          <span class="font-weight-bold">{{comment.nickname}}</span>
          {{comment.commContent}}
          <span class="small">{{comment.createdAt}}</span>
        </b-list-group-item>

      </b-list-group>
     
      <b-row align-h="between" class="mx-5 mb-5">
        <b-button variant="info">취소</b-button>
        <b-button type="submit" variant="info">확인</b-button>
      </b-row>

      <br>
      <br>
      <br>

  </div>
  
</template>


<script>
import ImageSlick from '@/components/story/ImageSlick'
import { mapGetters } from "vuex";
import axios from 'axios';

export default {
  name: 'ArticleDetail',
  components: {
    ImageSlick
  },
  computed: {
    ...mapGetters(["getUserId"]),
    ...mapGetters(["getUserName"])
  },
  data() {
    return {
      post: this.$route.params.post,
      comments: [],
      limit: 5,
      offset: 0,
      group: this.$route.params.group,
      liked: false
    }
  },
  created() {
    this.getLikeInfo();
    this.getArticleComments();
  },
  methods: {
    getLikeInfo(){
      axios
        .get(`/userpost/comment`, {
          params: {
            postId: this.post.postId,
            limit: this.limit,
            offset: this.offset
          }
        })
        .then(
          (response) => (
            this.comments = response.data.list
          )
        );
    },
    getArticleComments(){
      axios
        .get(`/userpost/comment`, {
          params: {
            postId: this.post.postId,
            limit: this.limit,
            offset: this.offset
          }
        })
        .then(
          (response) => (
            this.comments = response.data.list
          )
        );
    }
  },
}




</script>


<style>


/* ### {
  font-size: 26px;
  font-weight: 600;
  color: #2c3e5099;
  text-rendering: optimizelegibility;
  -moz-osx-font-smoothing: grayscale;
  -moz-text-size-adjust: none;
} /

#dropdown_group {
  max-width: 30em;
  / margin: 1em auto; 
  margin-right: 50px;
}*/

#article_box {
  width: 50%;
  height: 50%;
  position: absolute;
  left: 25%;
  margin-top: 5%;
}


</style>

