<template>
  <div>
    <!-- <b-card-group deck> -->
      <div v-for="post in posts" :key="post.postId">
      <b-card header-tag="header" footer-tag="footer"> <!-- title="Title" 속성 사용 가능  -->
      <!-- <div v-for="post in posts" v-bind:key="post.postId" @click="detail(post.postId)"> 
        <p>{{post.nickname}}</p>
        <br>
        <p>{{post.postContent}}</p>
      </div> -->
        <template>
          <b-card-text class="font-weight-bold">
            <!-- 뱃지 추가할 경우 backend에서 sql문 수정해서 뱃지 정보도 가져와야 함-->
            <!-- <span class="mr-5">뱃지 img</span> -->
            <span >{{post.nickname}}</span>
          </b-card-text>
        </template>
        <div class="postImage">
          <ImageSlick />
        </div>
        <p @click="detail(post)">{{post.postContent}}</p>
        <template #footer>
          <b-row class="h2 mb-2" align-h="between">
            <div class="postLike">
            <b-icon icon="suit-heart-fill" variant="danger" v-if="liked"></b-icon>
            <b-icon icon="suit-heart" variant="danger" v-else></b-icon><span>{{post.postLikeCount}}</span>
            </div>
            
            <div class="postComment">
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
   <div>
</div>
  </div>
</template>


<script>
import ImageSlick from '@/components/story/ImageSlick'
import { mapGetters } from "vuex";
import axios from 'axios';

export default {
  name: 'PostBlockMy',
  components: {
    ImageSlick,
  },
  computed: {
    ...mapGetters(["getUserId"])
  },
  data() {
    return {
      posts:[],
      limit: 5,  //한 페이지에 노출될 게시글의 수
      offset: 0,  //게시글 번호 오프셋,
      liked: false
    }
  },
  created() {
    this.getUserPosts();
  },
  methods: {
    getUserPosts(){
      axios
        .get(`/userpost/user`, {
          params: {
            userId: this.getUserId,
            limit: this.limit,
            offset: this.offset
          }
      })
        .then((response) => {
          this.posts = response.data.list;
          console.log(this.posts);
        });
    },
    detail(post){
      this.$router.push({ name: "ArticleDetail", params: { post: post} });
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