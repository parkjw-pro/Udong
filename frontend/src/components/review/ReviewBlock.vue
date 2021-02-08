<template>
  <div>
    <!-- <b-card-group deck> -->
      <b-card header-tag="header" footer-tag="footer"> <!-- title="Title" 속성 사용 가능  -->
        <template #header>
          <b-card-text class="font-weight-bold" >
            <span class="mr-5">뱃지 img</span>
            <span >{{review.nickname}}</span>
          </b-card-text>
        </template>
        <div > <!-- for 문 -->
          <b-card-img v-for="(item, index) in fileId"
        :key="index" class="col-4 mb-5" :src="url+`/review/download/` + item" alt="Image" bottom></b-card-img>
        </div>
        <p>{{review.reviewContent}}</p>
        <template #footer>
          <div style="text-align: left;">
            <b-icon icon="suit-heart" variant="danger"></b-icon>
            <b-icon icon="suit-heart-fill" variant="danger" @click="UpdateReviewLike"></b-icon>
            <small class="ml-2">{{review.reviewLikeCount}}명이 좋아합니다.</small>
          </div>
        </template>
      </b-card>
    <!-- </b-card-group> -->
   <div>
</div>   
  </div>
</template>

<script>
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL
// import { mdbInput, mdbContainer } from 'mdbvue';

export default {
  name: 'ReviewBlock',
  components: {
    // mdbInput,
    // mdbContainer,
  },
  props: {
    review : {},


  },
  data: function() {
    return {

      reviewDetail : {},
      url : SERVER_URL,
      fileId : Array
      

      


    };
  },

  created()  {

  },
  mounted(){
    this.GetReviewDetail();
    
  },
  methods: {
      GetReviewDetail: function() {
      axios
      .get(`${SERVER_URL}/review/` + `${this.review.reviewId}`)
      .then((response) => {
        this.fileId = response.data.fileId;

        console.log( this.fileId);

      })
      .catch((response) => {
        console.log(response);
      });
    },
    UpdateReviewLike: function() {
      var formData = new FormData();
      formData.append('reviewId', this.review.reviewId);
      formData.append('storeId', this.review.storeId);
      formData.append('userId', this.review.userId);
      // var likeDto = [
      //   reviewId = this.review.reviewId,
      //   storeId =  this.review.storeId,
      //   userId = this.review.userId
      // ]
      axios
      .post(`${SERVER_URL}/review/comment/like` + formData,{
          headers: { 'Content-Type': `application/json; charset=UTF-8` },
        })
      .then((response) => {
        this.fileId = response.data.fileId;

        console.log( this.fileId);

      })
      .catch((response) => {
        console.log(response);
      });
    },

  },
};
</script>

<style>
  /* b-card-text{
   background-color: blue;
  } */
  /* 적용안됨 */
  b-card-img {
    height: 5px;
    width: 5px;
  }
</style>