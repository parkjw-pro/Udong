<template>
  <div>
    <!-- <b-card-group deck> -->
      <b-card v-for="(post, i) in posts" :key="i" header-tag="header" footer-tag="footer"> <!-- title="Title" 속성 사용 가능  -->
        <template #header>
          <b-card-text class="font-weight-bold">
            <!-- <span class="mr-5">뱃지 img</span> -->
            <span >{{post.nickname}}</span>
          </b-card-text>
        </template>
        <div class="postImage">
          <b-card-img class="col-3 mb-5" src="https://placekitten.com/480/210" alt="Image" bottom></b-card-img>
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
    <!-- </b-card-group> -->
   <div>
</div>   
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from 'axios';

export default {
  name: 'PostBlock',
  data() {
    return {
      groupInfo: null,
      posts:[],
      limit: 5,  //한 페이지에 노출될 게시글의 수
      offset: 0,  //게시글 번호 오프셋
      liked: false
    }
  },
  computed: {
    ...mapGetters(["getUserId"]),
    ...mapGetters(["getUserName"])
  },
  created() {
    //view로부터 가져온 clubId 인자로 넣기
    this.getGroupInfo(139);  
    this.getGroupPosts(139);  
  },
  methods: {
    getGroupInfo(){
      
    },
    getGroupPosts(clubId){  //seleted에 해당하는 group의 게시글 조회
      axios
        .get(`/clubpost/club`, {
          params: {
            clubId: clubId,
            limit: this.limit,
            offset: this.offset
          }
        })
        .then(
          (response) => (
            this.posts = response.data.list
          )
        );

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