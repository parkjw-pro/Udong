<template>
  <div>
    <!-- <b-card-group deck> -->
      <div >
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
            <span >nickname : {{nickname}}</span>
          </b-card-text>
        </template>
        <div class="postImage">
           <b-card-img v-for="(item, index) in fileId"
        :key="index" class="col-4 mb-5" :src="url+`/userpost/download/` + item" alt="Image" bottom></b-card-img>
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
              <b-dropdown-item href="#" variant="danger" >삭제</b-dropdown-item>
              <!-- <b-dropdown-item href="#" variant="danger" v-else>신고</b-dropdown-item> -->
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
//import ImageSlick from '@/components/story/ImageSlick'
import axios from 'axios';
const SERVER_URL = "http://localhost:8000";

export default {
  name: 'PostBlockMy',
  components: {
 //   ImageSlick,
  },
  props:{
    post: Object,

    
  },
  data() {
    return {
      postDtail : Object,
      fileId: Object,
      url : SERVER_URL ,
    }
  },
  created() {
    console.log(this.post.postId)
    axios.get(`${SERVER_URL}/userpost/${this.post.postId}`)
    .then(( res)=>{
      this.postDtail = res;
      console.log(res)
      console.log(res.data.fileId)
      this.fileId= res.data.fileId
      
    })

  
  },  

    
  
  methods: {  
    detail(post){
      this.$router.push({ name: "ArticleDetail", params: { post: post} });
    },
 
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