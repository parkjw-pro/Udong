<template>
  <div>
    <!-- <b-card-group deck> -->
      <b-card
        header-tag="header"
        header-bg-variant="white"
        footer-tag="footer"
        footer-bg-variant="white"
      > <!-- title="Title" 속성 사용 가능  -->
        <template #header>
          <b-card-text class="font-weight-bold" >
            <b-row align-h="justify">
              <b-col>
                <!-- <span class="mr-5">뱃지 img</span> -->
                {{review.nickname}}
              </b-col>
              <b-col>
                <b-dropdown size="lg" dropup variant="link" toggle-class="text-decoration-none" no-caret>
                  <template #button-content>
                    <b-icon icon="three-dots-vertical"></b-icon>
                  </template>
                  <b-dropdown-item href="#" variant="danger" v-if="post.userId == getUserId">삭제</b-dropdown-item>
                  <b-dropdown-item href="#" variant="danger" v-else>신고</b-dropdown-item>
                </b-dropdown>
              </b-col>
            </b-row>
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
import axios from 'axios'

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

  async mounted(){
    await this.GetReviewDetail();
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
      axios
      .get(`${SERVER_URL}/review/comment/like` + formData, {
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