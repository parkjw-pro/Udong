<template>
  <div>
    <!-- 댓글 -->
    <b-row align-h="justify">
      <b-col cols="10" class="text-left" sytle="text-align:left">
        <span class="ml-2">{{comment.nickname}}</span>
        <span class="ml-3" style="color:gray">{{comment.commContent}}</span>
        <span class="small ml-2 mt-2" style="color:lightgray"><em>{{comment.createdAt.substr(0,10)}}</em></span>  <!--날짜까지만 표시-->
      </b-col>
      <b-col cols="2" style="text-align: right;">
      <!--좋아요 여부와 좋아요 수-->
        <b-icon icon="suit-heart-fill" variant="danger" v-if="liked" @click="likeComment"></b-icon>
        <b-icon icon="suit-heart" variant="danger" v-else @click="likeComment"></b-icon>
        <span>{{comment.commLikeCount}}</span>

        <b-dropdown size="sm" dropup variant="secondary-muted" no-caret>
          <template #button-content>
            <b-icon icon="three-dots-vertical"></b-icon>
          </template>
          <b-dropdown-item href="#" variant="danger" v-if="comment.userId == getUserId" @click="deleteComment">삭제</b-dropdown-item>
          <b-dropdown-item href="#" variant="danger" v-else @click="reportComment">신고</b-dropdown-item>
        </b-dropdown>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'Comment',
  props: {
    comment: Object,
    type: String
  },
  watch: {
    comment(){
      this.getLikeInfo();
    }
  },
  data() {
    return {
      liked: false,
      limit: 5,
      offset: 0
    }
  },
  computed: {
    ...mapGetters(["getUserId"]),
    ...mapGetters(["getUserName"])
  },
  created() {
    this.getLikeInfo();
  },
  methods: {
    getLikeInfo() {
      axios
        .get(`${SERVER_URL}/${this.type}/comment/like`, {
          params: {
            userId: this.getUserId,
            postId: this.comment['postId'],
            clubId: this.comment['clubId'],
            commentId: this.comment['commentId']
          }
        })
        .then(
          (response) => (
            this.liked = response.data
          )
        );
    },
    likeComment() {
      axios
        .post(`${SERVER_URL}/${this.type}/comment/like`, {
          postId: this.comment['postId'],
          userId: this.getUserId,
          clubId: this.comment['clubId'],
          commentId: this.comment['commentId']
        })
        .then((response) => {
            this.liked = !response.data.includes("취소");
            if(this.liked) {
              this.comment['commLikeCount'] = this.comment['commLikeCount']*1 + 1;
            } else {
              this.comment['commLikeCount'] = this.comment['commLikeCount']*1 - 1;
            }
        });
    },
    deleteComment() {
      
    },
    reportComment() {
      //모달창에서 content, category 입력
      var content = "";
      var category = "";
      //axios 요청
      axios
        .post(`${SERVER_URL}/${this.type}/comment/report`, {
          userId: this.getUserId,
          postId: this.comment['postId'],
          clubId: this.comment['clubId'],
          commentId: this.comment.commentId,
          content: content,
          category: category
        })
        .then((response) => {
          console.log(response);
        });
    }
  },
}
</script>

<style>
  
  /* 적용안됨 */
  b-card-img {
    height: 5px;
    width: 5px;
  }
</style>