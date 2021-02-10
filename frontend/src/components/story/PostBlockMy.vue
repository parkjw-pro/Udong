<template>
  <div>
    <!-- <b-card-group deck> -->
      <b-card>
        <!-- 1. 상단 부분 -->
        <b-card-text>
          <b-row align-h="justify" style="text-align: left;">
            <b-col align-self="center">
              <span style="cursor: pointer;" @click="toFeed">
                <!-- 뱃지 -->
                <b-avatar :src="require('@/assets/app/badge1.jpg')"></b-avatar>
                <!-- 닉네임 -->
                <span class="ml-1" style="">{{ post.nickname }}</span>
              </span>
            </b-col>
            <!-- 추가 버튼(신고/삭제) -->
            <b-col style="text-align: right;">
              <b-dropdown size="lg" dropup variant="link" toggle-class="text-decoration-none" no-caret>
              <template #button-content>
                <b-icon icon="three-dots" variant="dark"></b-icon>
              </template>
              <div v-if="post.userId === userId">
                <b-dropdown-item href="" variant="danger" v-b-modal.post-delete-modal>삭제</b-dropdown-item>
                <b-modal id="post-delete-modal" @ok="deletePost">
                  <p><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%" />소중한 리뷰를 정말 삭제하시겠습니까?</p>
                </b-modal>
              </div>
              <div v-else>
                <b-dropdown-item href="#" variant="danger" @click="reportPost">신고</b-dropdown-item>
              </div>
            </b-dropdown>
            </b-col>
          </b-row>  
        </b-card-text>

        <!-- 2. 중앙 부분 -->
        <!--2.1 이미지-->

        <!--2.2 내용-->
        <b-row class="mt-3" align-h="center">
          <div class="my-3 mx-3" style="text-align: left;">
            <h6 @click="detail(post)">{{post.postContent}}</h6>
          </div>
        </b-row>

        <b-row class="h2 mb-2 ml-2" align-h="start">
          <!-- 좋아요 -->
          <div class="postLike mr-3">
            <b-icon v-if="liked" font-scale="1" icon="suit-heart-fill" variant="danger" @click="likePost()"></b-icon>
            <b-icon v-else font-scale="1" icon="suit-heart" variant="danger" @click="likePost()"></b-icon>
          </div>
          
          <!-- 댓글 수 -->
          <div class="postComment" @click="getArticleComments">
            <b-icon v-if="comments.length > 0" font-scale="1" icon="chat-fill" variant="warning"></b-icon>
            <b-icon v-else font-scale="1" icon="chat" variant="warning"></b-icon>
          </div>
        </b-row>

        <b-row class="ml-2 mb-3">
          <div v-if="post.postLikeCount >= 0">{{post.postLikeCount}}명이 좋아합니다</div>
        </b-row>

        <!-- 댓글 -->
        <div style="width: 80%; display: inline-block">
          <div v-for="(comm, i) in comments" :key="i">
            <Comment :comment="comm" type="userpost" />
          </div>
        </div>
            
        <b-row class="mt-3" v-if="comments.length > 0 && comments.length < commentCount">
            <b-col>
              <span style="cursor: pointer;" @click="getMoreComments">
                <!-- <b-button pill variant="light" @click="getMoreComments">+</b-button> -->
                <img alt="Vue logo" src="@/assets/udonge.png" style="width: 5%;">더보기
              </span>
            </b-col>
        </b-row>

        <br>
        <!--댓글 입력창-->
        <div class="container">
          <b-row align-h="justify">
            <b-col cols="8" offset="1"><b-form-input placeholder="댓글을 달아보세요!" v-model="comment" @keypress.enter="writeComment"></b-form-input></b-col>
            <b-col cols="2"><b-button type="submit" variant="info" @click="writeComment">확인</b-button></b-col>
          </b-row>
        </div>
      </b-card>
  </div>
</template>


<script>
//import ImageSlick from '@/components/story/ImageSlick'
import Comment from '@/components/story/Comment'
import axios from 'axios';
const SERVER_URL = "http://localhost:8000";

export default {
  name: 'PostBlockMy',
  components: {
    Comment
  },
  props:{
    post: Object,    
  },
  data() {
    return {
      postDetail : Object,
      fileId: Object,
      url : SERVER_URL ,
      userId: '',
      liked: false,
      comments: [],  //글에 달린 댓글
      commentCount: 0,
      limit: 5,
      offset: 0,
      comment: '',  //작성하는 댓글
    }
  },
  created() {
    const userInfo = JSON.parse(localStorage.getItem('Info-token'))
    this.userId = userInfo["userId"]

    axios.get(`${SERVER_URL}/userpost/${this.post.postId}`)
    .then((res)=>{
      this.postDetail = res;
      this.fileId= res.data.fileId;
    });

    this.getLikeInfo();
  },
  methods: {
    toFeed: function () {
      this.$router.push({name: 'MyFeed', params: { userId: this.post.userId, nickname: this.post.nickname}})
    },
    getLikeInfo(){
      axios
        .get(`${SERVER_URL}/userpost/like`, {
          params: {
            userId: this.userId,
            postId: this.post.postId,
          }
        })
        .then(
          (response) => (
            this.liked = response.data
          )
        );
    },
    deletePost() {

    },
    reportPost() {
      
    },
    likePost() {
      axios
        .post(`${SERVER_URL}/userpost/like`, {
          postId: this.post['postId'],
          userId: this.userId
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
    getArticleComments() {
      if(this.comments.length > 0) return;
      axios
        .get(`${SERVER_URL}/userpost/comment`, {
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
          });
    },
    getMoreComments(){

    },
    writeComment() {
      axios
        .post(`${SERVER_URL}/userpost/comment`, {
          postId: this.post['postId'],
          userId: this.userId,
          commContent: this.comment
        })
        .then((response) => {
          console.log(response);
          this.getArticleComments();
          this.post.postCommentCount = this.post.postCommentCount*1 + 1;
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