<template>
  <div>
    <!-- <b-card-group deck> -->
    <div>
      <b-card header-tag="header" footer-tag="footer"> <!-- title="Title" 속성 사용 가능  -->
        <template #header>
          <b-card-text class="font-weight-bold">
            <!-- <span class="mr-5">뱃지 img</span> -->
            <span >{{post.nickname}}</span>
          </b-card-text>
        </template>
        <!--이미지-->
        <div class="postImage">
             <b-card-img v-for="(item, index) in fileId"
        :key="index" class="col-4 mb-5" :src="url+`/clubpost/download/` + item" alt="Image" bottom></b-card-img>
        </div>
        <!--내용-->
        <p @click="detail(post)">{{post.postContent}}</p>
        <template #footer>
          <b-row class="h2 mb-2" align-h="between">
            <div class="postLike"> <!--좋아요 여부와 좋아요 수-->
            <b-icon icon="suit-heart-fill" variant="danger" v-if="liked" @click="likePost()"></b-icon>
            <b-icon icon="suit-heart" variant="danger" v-else @click="likePost()"></b-icon><span>{{post.postLikeCount}}</span>
            </div>
            
            <div class="postComment" @click="getArticleComments"> <!--댓글 수-->
            <b-icon v-if="comments.length > 0" icon="chat-fill" variant="warning"></b-icon>
            <b-icon v-else icon="chat" variant="warning"></b-icon>
            <span>{{post.postCommentCount}}</span>
            </div>

            <!--신고/삭제-->
            <b-dropdown size="lg" dropup variant="link" toggle-class="text-decoration-none" no-caret>
              <template #button-content>
                <b-icon icon="three-dots-vertical"></b-icon>
              </template>
              <b-dropdown-item href="#" variant="danger" v-if="post.userId == getUserId" @click="deletePost">삭제</b-dropdown-item>
              <b-dropdown-item href="#" variant="danger" v-else @click="reportPost">신고</b-dropdown-item>
            </b-dropdown>
          </b-row>

          <!-- 댓글 -->
          <div v-for="(comm, i) in comments" :key="i">
            <Comment :comment="comm" />
          </div>
          <div v-if="comments.length > 0 && comments.length < commentCount">
            <b-button @click="getMoreComments">+) 더보기</b-button>
          </div>

          <br>
          <!--댓글 입력창-->
          <div class="container">
            <div class="row">
              <b-form-input class="col-10" placeholder="댓글을 달아보세요!" v-model="comment" @keypress.enter="writeComment"></b-form-input>
              <b-button type="submit" variant="info" class="col" @click="writeComment">확인</b-button>
            </div>
          </div>
        </template>
      </b-card>
    </div>
    <!-- </b-card-group> -->
   <div>
</div>   
  </div>
</template>

<script>
import Comment from '@/components/story/Comment'

import { mapGetters } from "vuex";
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'PostBlock',
  props: {
    post: Object
  },
  components: {
    Comment
  },
  watch: {
    post(){ }
  },
  data() {
    return {
      liked: false,
      comments: [],
      comment: "",
      commentCount: 0,
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
    getLikeInfo(){
      axios
        .get(`${SERVER_URL}/clubpost/like`, {
          params: {
            userId: this.getUserId,
            postId: this.post['postId'],
            clubId: this.post['clubId'],
          }
        })
        .then(
          (response) => (
            this.liked = response.data
          )
        );
    },
    likePost() {
      axios
        .post(`${SERVER_URL}/clubpost/like`, {
          postId: this.post['postId'],
          userId: this.getUserId,
          clubId: this.post['clubId']
        })
        .then((response) => {
            this.liked = !response.data.includes("취소");
            if(this.liked) {
              this.post['postLikeCount'] = this.post['postLikeCount']*1 + 1;
            } else {
              this.post['postLikeCount'] = this.post['postLikeCount']*1 - 1;
            }
        });
    },
    deletePost() {
      axios
        .delete(`${SERVER_URL}/clubpost`, {
          postId: this.post['postId']
        })
        .then((response) => {
          console.log(response);
        });
    },
    // reportPost() {
    //   var content = "";
    //   var category = "";

    //   //모달창으로 신고 내역 보여주기
    //   //content, category 입력 해야 함!

    //   //axios 요청
    //   axios
    //     .post(`${SERVER_URL}/clubpost/report`, {
    //       userId: this.getUserId,
    //       postId: this.post['postId'],
    //       clubId: this.post['clubId'],
    //       content: content,
    //       category: category
    //     })
    //     .then((response) => {
    //       console.log(response);
    //     });
    // },
    getArticleComments(){
      if(this.comments.length > 0) return;
      axios
        .get(`${SERVER_URL}/clubpost/comment`, {
          params: {
            postId: this.post.postId,
            limit: this.limit,
            offset: this.offset
          }
        })
        .then(
          (response) => {
            this.comments = response.data.list;
            this.commentCount = response.data.count;
            console.log(this.commentCount);
          });
    },
    getMoreComments() {
      if(this.commentCount <= this.comments.length){
        return;
      }

      this.offset += this.limit;
      axios
        .get(`${SERVER_URL}/clubpost/comment`, {
          params: {
            postId: this.post.postId,
            limit: this.limit,
            offset: this.offset
          }
        })
        .then(
          (response) => {
            this.comments.push(...response.data.list);
          });
    },
    writeComment() {
      axios
        .post(`${SERVER_URL}/clubpost/comment`, {
          postId: this.post['postId'],
          userId: this.getUserId,
          clubId: this.post['clubId'],
          commContent: this.comment
        })
        .then((response) => {
          console.log(response);
          this.getArticleComments();
          this.post.postCommentCount = this.post.postCommentCount*1 + 1;
        });
    },
    detail(post) {
      this.$router.push({ name: "ArticleDetail", params: { post: post, group: this.group} });
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