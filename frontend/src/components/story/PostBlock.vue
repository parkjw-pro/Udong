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
      liked: false
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