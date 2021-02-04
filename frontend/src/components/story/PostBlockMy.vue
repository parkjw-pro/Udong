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
<!-- 좋아요, 댓글 아이콘 추가해둔 상태. 와이어프레임 참고해서 상세 페이지까지 수정할 것!!!-->
            <!-- <span class="mr-5">뱃지 img</span> -->
            <span >{{post.nickname}}</span>
          </b-card-text>
        </template>
        <div @click="detail(post)"> <!-- for 문 -->
          <!-- <b-card-img class="col-3 mb-5" src="https://placekitten.com/480/210" alt="Image" bottom></b-card-img> -->
          <ImageSlick />
          <p>{{post.postContent}}</p>
        </div>
        <template #footer>
          <b-icon icon="suit-heart" variant="danger"></b-icon>
          <b-icon icon="suit-heart-fill" variant="danger"></b-icon>
          <b-icon icon="chat"></b-icon>
          <!-- <b-button class="mr-5" href="#" variant="info">상세보기</b-button> -->
          <b-button class="ml-5" href="#" variant="info">댓글</b-button>
        </template>
      </b-card>
    <!-- </b-card-group> -->
      </div>
   <div>
</div>   
  </div>
</template>

<script>
import ImageSlick from '@/components/story/ImageSlick'
<<<<<<< HEAD
import { mapGetters } from "vuex";
=======
>>>>>>> 8959c25c7abe4f8e48ee0050827f07f89a960d81
import axios from 'axios';
const SERVER_URL = "http://localhost:8000";

export default {
  name: 'PostBlockMy',
  components: {
    ImageSlick,
  },
<<<<<<< HEAD
  computed: {
    ...mapGetters(["getUserId"])
  },
  data() {
    return {
      posts:[],
      limit: 5,  //한 페이지에 노출될 게시글의 수
=======
  data() {
    return {
      posts:[],
      limit: 3,  //한 페이지에 노출될 게시글의 수
>>>>>>> 8959c25c7abe4f8e48ee0050827f07f89a960d81
      offset: 0,  //게시글 번호 오프셋
    }
  },
  mounted() {
    this.getPostInfos();
  },
  methods: {
    getPostInfos(){
      axios
<<<<<<< HEAD
        .get(`${SERVER_URL}/userpost/user`, {
          params: {
            userId: this.getUserId,
=======
        .get(`${SERVER_URL}/userpost`, {
          params: {
>>>>>>> 8959c25c7abe4f8e48ee0050827f07f89a960d81
            limit: this.limit,
            offset: this.offset
          }
      })
        .then((response) => {
<<<<<<< HEAD
          this.posts = response.data.list;
          console.log(this.posts);
        });
    },
    detail(post){
      this.$router.push({ name: "ArticleDetail", params: { postInfo: post} });
=======
          console.log(response);
        });
>>>>>>> 8959c25c7abe4f8e48ee0050827f07f89a960d81
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