<template>
  <div>
    <!-- <b-card-group deck> -->
    <div>
      <b-card>
        <!-- 1. 상단 부분 -->
        <b-card-text>
          <b-row align-h="justify" style="text-align: left;">
            <b-col align-self="center">
              <span style="cursor: pointer;" @click="toFeed">
                <!--그룹 게시물 - 그룹 정보-->
                <span style="">[그룹명] {{ groupName }} </span>
                <br />
                <!-- 뱃지 -->
                <b-avatar :src="require(`@/assets/app/badge/${this.badge}.jpg`)"></b-avatar>
                <!-- 닉네임 -->
                <span class="ml-1" style="">{{ post.nickname }}</span>
              </span>
            </b-col>
            <!-- 추가 버튼(신고/삭제) -->
            <b-col style="text-align: right;">
              <b-dropdown
                size="lg"
                dropup
                variant="link"
                toggle-class="text-decoration-none"
                no-caret
              >
                <template #button-content>
                  <b-icon icon="three-dots" variant="dark"></b-icon>
                </template>
                <div v-if="post.userId === userId">
                  <b-dropdown-item href="" variant="danger" v-b-modal.post-delete-modal
                    >삭제</b-dropdown-item
                  >
                  <b-modal id="post-delete-modal" @ok="deletePost">
                    <p>
                      <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%" />소중한
                      이야기를 정말 삭제하시겠습니까?
                    </p>
                  </b-modal>
                </div>
                <div v-else>
                  <b-dropdown-item href="#" variant="danger" @click="reportPost"
                    >신고</b-dropdown-item
                  >
                </div>
              </b-dropdown>
            </b-col>
          </b-row>
        </b-card-text>

        <!-- 2. 중앙 부분 -->
        <!--2.1 이미지-->
        <b-row v-if="fileId.length > 0" class="postImage" align-h="center">
          <b-carousel
            id="carousel-1"
            v-model="slide"
            controls
            indicators
            background="#ababab"
            img-width="1024"
            img-height="480"
            style="text-shadow: 1px 1px 2px #333; width: 30em; height: 15em;"
            fade
            :interval="0"
          >
            <!-- fileId 정의해주어야한다!!! -->
            <b-carousel-slide
              id="post_img"
              v-for="(item, index) in fileId"
              :key="index"
              :img-src="url + `/clubpost/download/` + item"
            ></b-carousel-slide>
          </b-carousel>
        </b-row>
        <b-row v-else class="my-5 "> </b-row>

        <!--2.2 내용-->
        <b-row class="mt-3 mx-5" align-h="center">
          <div class="my-3 mx-3" style="text-align: left;">
            <h6>{{ post.postContent }}</h6>
          </div>
        </b-row>

        <b-row class="h2 mb-2 ml-2" align-h="start">
          <!-- 좋아요 -->
          <div class="postLike mr-2" style="margin-top: 2px;">
            <div class="h4" v-if="liked">
              <b-icon icon="suit-heart-fill" variant="danger" @click="likePost()"></b-icon>
            </div>
            <div class="h4" v-else>
              <b-icon icon="suit-heart" variant="danger" @click="likePost()"></b-icon>
            </div>
          </div>
          <!-- 댓글 수 -->
          <div class="postComment" @click="showComment">
            <div class="h4" v-if="commentFlag">
              <b-icon icon="chat-fill" variant="warning"></b-icon>
              <span class="ml-1" style="color:orange"
                ><small>{{ commentCount }}</small></span
              >
            </div>
            <div class="h4" v-else>
              <b-icon icon="chat" variant="warning"></b-icon>
              <span class="ml-1" style="color:orange"
                ><small>{{ commentCount }}</small></span
              >
            </div>
          </div>
        </b-row>

        <b-row class="ml-2 mb-3">
          <div v-if="post.postLikeCount >= 0">{{ post.postLikeCount }}명이 좋아합니다</div>
        </b-row>

        <!-- 댓글 -->
        <div v-show="commentFlag">
          <div v-if="comments.length > 0" style="width: 80%; display: inline-block">
            <div v-for="(comm, i) in comments" :key="i">
              <Comment :comment="comm" type="userpost" />
            </div>
          </div>
          <div v-else>
            아직 댓글이 없어요! 처음으로 댓글을 달아보세요!
          </div>

          <b-row class="mt-3" v-if="comments.length > 0 && comments.length < commentCount">
            <b-col>
              <span style="cursor: pointer;" @click="getMoreComments">
                <!-- <b-button pill variant="light" @click="getMoreComments">+</b-button> -->
                <img alt="Vue logo" src="@/assets/udonge.png" style="width: 5%;" />더보기
              </span>
            </b-col>
          </b-row>
        </div>
        <br />

        <!--댓글 입력창-->
        <div class="container">
          <b-row align-h="justify">
            <b-col cols="8" offset="1"
              ><b-form-input
                placeholder="댓글을 달아보세요!"
                v-model="comment"
                @keypress.enter="writeComment"
              ></b-form-input
            ></b-col>
            <b-col cols="2"
              ><b-button type="submit" variant="info" @click="writeComment">확인</b-button></b-col
            >
          </b-row>
        </div>
      </b-card>
    </div>
    <div></div>
  </div>
</template>

<script>
import Comment from '@/components/story/Comment';

import { mapGetters } from 'vuex';
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'PostBlock',
  props: {
    post: Object,
    groupName: String,
  },
  components: {
    Comment,
  },
  watch: {
    post() {},
  },
  data() {
    return {
      slide: 0, //이미지 carousel 부분
      liked: false,
      comments: [],
      comment: '',
      commentCount: 0,
      commentFlag: false,
      limit: 5,
      offset: 0,
      fileId: Object,
      url: SERVER_URL,
      userId: '', // 현재 사용자의 아이디
      badge: '',
    };
  },
  computed: {
    ...mapGetters(['getUserId']),
    ...mapGetters(['getUserName']),
    cmtCount: function() {
      return this.commentCount;
    },
  },
  created() {
    axios.get(`${SERVER_URL}/clubpost/postId/${this.post.postId}`).then((res) => {
      this.fileId = res.data.fileId;
    });
    const userInfo = JSON.parse(localStorage.getItem('Login-token'));
    this.badge = userInfo.user_badge;

    this.getComments();
    // this.fileCheck();
  },
  async mounted() {
    await this.getLikeInfo();
    const userInfo = JSON.parse(localStorage.getItem('Info-token'));
    this.userId = userInfo['userId'];
  },
  methods: {
    deletePost() {
      axios
        .delete(`${SERVER_URL}/clubpost`, {
          postId: this.post['postId'],
        })
        .then((response) => {
          console.log(response);
        });
    },
    getLikeInfo() {
      axios
        .get(`${SERVER_URL}/clubpost/like`, {
          params: {
            userId: this.getUserId,
            postId: this.post['postId'],
            clubId: this.post['clubId'],
          },
        })
        .then((response) => (this.liked = response.data));
    },
    showComment() {
      this.commentFlag = !this.commentFlag;
    },
    getComments() {
      axios
        .get(`${SERVER_URL}/clubpost/comment`, {
          params: {
            postId: this.post.postId,
            limit: this.limit,
            offset: this.offset,
          },
        })
        .then((response) => {
          console.log(response.data);
          this.comments.push(...response.data.list);
          this.commentCount = response.data.count;
        });
    },
    getMoreComments() {
      if (this.commentCount <= this.comments.length) {
        return;
      }
      this.offset += this.limit;
      this.getComments();
    },
    likePost() {
      axios
        .post(`${SERVER_URL}/clubpost/like`, {
          postId: this.post['postId'],
          userId: this.getUserId,
          clubId: this.post['clubId'],
        })
        .then((response) => {
          this.liked = !response.data.includes('취소');
          if (this.liked) {
            this.post['postLikeCount'] = this.post['postLikeCount'] * 1 + 1;
          } else {
            this.post['postLikeCount'] = this.post['postLikeCount'] * 1 - 1;
          }
        });
    },
    writeComment() {
      axios
        .post(`${SERVER_URL}/clubpost/comment`, {
          postId: this.post['postId'],
          userId: this.getUserId,
          clubId: this.post['clubId'],
          commContent: this.comment,
        })
        .then((response) => {
          console.log(response);
          this.offset = 0;
          this.comments = []; //댓글 초기화
          this.getComments();
          this.post.postCommentCount = this.post.postCommentCount * 1 + 1;
          this.comment = '';
        });
    },
    reportPost() {
      //모달창으로 신고 내역 보여주기
      //content, category 입력 해야 함!
      //axios 요청
      // var content = "";
      // var category = "";
      // axios
      //   .post(`${SERVER_URL}/clubpost/report`, {
      //     userId: this.getUserId,
      //     postId: this.post['postId'],
      //     clubId: this.post['clubId'],
      //     content: content,
      //     category: category
      //   })
      //   .then((response) => {
      //     console.log(response);
      //   });
    },
    toFeed: function() {
      console.log(this.post.userId);
      this.$router.push({
        name: 'MyFeed',
        params: { userId: this.post.userId, nickname: this.post.nickname },
      });
    },
  },
};
</script>

<style>
#post_img {
  top: 0;
  left: 0;
  min-width: 30em;
  min-height: 15em;
  max-width: 30em;
  max-height: 15em;
}
</style>
