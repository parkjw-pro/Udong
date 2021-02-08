<template>
  <div>
    <!-- <b-card-group deck> -->
      <b-card header-tag="header" footer-tag="footer"> <!-- title="Title" 속성 사용 가능  -->
        <template #header>
          <b-card-text class="font-weight-bold">
            <span class="mr-5">뱃지 img</span>
            <span >{{review.nickname}}</span>
          </b-card-text>
        </template>
        <div v-for="(item, index) in fileId"
        :key="index"> <!-- for 문 -->
          <b-card-img class="col-4 mb-5" :src="url+`/review/download/` + item" alt="Image" bottom></b-card-img>
        </div>
        <p>{{review.reviewContent}}</p>
        <template #footer>
          <div style="text-align: left;">
            <b-icon icon="suit-heart" variant="danger"></b-icon>
            <b-icon icon="suit-heart-fill" variant="danger"></b-icon>
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
    review : Array


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

  },
};
</script>

<style>
  
  /* 적용안됨 */
  b-card-img {
    height: 5px;
    width: 5px;
  }
</style>