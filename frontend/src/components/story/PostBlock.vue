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
          <b-card-img class="col-3 mb-5" src="https://placekitten.com/480/210" alt="Image" bottom></b-card-img>
        </div>
        <!--내용-->
        <p @click="detail(post)">{{post.postContent}}</p>
        <template #footer>
          <b-row class="h2 mb-2" align-h="between">
            <div class="postLike"> <!--좋아요 여부와 좋아요 수-->
            <b-icon icon="suit-heart-fill" variant="danger" v-if="liked" @click="likePost()"></b-icon>
            <b-icon icon="suit-heart" variant="danger" v-else @click="likePost()"></b-icon><span>{{post.postLikeCount}}</span>
            </div>
            
            <div class="postComment"> <!--댓글 수-->
            <b-icon icon="chat" variant="warning"></b-icon>
            <span>{{post.postCommentCount}}</span>
            </div>

            <!--신고/삭제-->
            <b-dropdown size="lg" dropup variant="link" toggle-class="text-decoration-none" no-caret>
              <template #button-content>
                <b-icon icon="three-dots-vertical"></b-icon>
              </template>
              <b-dropdown-item href="#" variant="danger" v-if="post.userId == getUserId">삭제</b-dropdown-item>
              <b-dropdown-item href="#" variant="danger" v-else>신고</b-dropdown-item>
            </b-dropdown>
          </b-row>

          <!-- 댓글 -->
          <b-list-group style="text-align: left;" v-for="comment in comments" :key="comment.commentId">  <!-- for 문 -->
            <b-list-group-item>
              <b-row>
              <span class="font-weight-bold">{{comment.nickname}}</span>
              <span class="ml-2 mr-1">{{comment.commContent}}</span>
              <span class="small"><em>{{comment.createdAt.substr(0,10)}}</em></span>  <!--날짜까지만 표시-->
              <b-dropdown size="xs" dropup variant="link" toggle-class="text-decoration-none" no-caret>
                  <template #button-content>
                    <b-icon icon="three-dots-vertical"></b-icon>
                  </template>
                  <b-dropdown-item href="#" variant="danger" v-if="post.userId == getUserId">삭제</b-dropdown-item>
                  <b-dropdown-item href="#" variant="danger" v-else>신고</b-dropdown-item>
                </b-dropdown>
              </b-row>
            </b-list-group-item>
            
          </b-list-group>

          <br>
          <!--댓글 입력창-->
          <div class="container">
            <div class="row">
              <b-form-input class="col-10" placeholder="댓글을 달아보세요!" v-model="comment"></b-form-input>
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
import { mapGetters } from "vuex";
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'PostBlock',
  props: {
    post: Object
  },
  watch: {
    post(){ }
  },
  data() {
    return {
      liked: false,
      comments: [],
      comment: "",
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
    this.getArticleComments();
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
    getArticleComments(){
      console.log(this.post.postId);
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
            console.log(response);
            this.comments = response.data.list;
            console.log(this.comments);
            
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