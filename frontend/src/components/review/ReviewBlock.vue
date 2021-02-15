<template>
  <div>
    <!-- <b-card-group deck> -->
      <b-card
        header-tag="header"
        header-bg-variant="white"
        footer-tag="footer"
        footer-bg-variant="white"
      > 
        <!-- 1. title 부분  -->
        <template #header>
          <b-card-text>
            <b-row align-h="justify">
              <!-- <span class="mr-5">뱃지 img</span> -->
              <b-col><div class="mt-2" style="text-align: left"><span class="font-weight-bold" style="font-size: large;">{{review.nickname}}</span>님의 리뷰 {{rate}}</div></b-col>
              <b-col style="text-align: right;">
                <b-dropdown size="lg" dropup variant="link" toggle-class="text-decoration-none" no-caret>
                <template #button-content>
                  <b-icon icon="three-dots" variant="dark"></b-icon>
                </template>
                <div v-if="review.userId === userId">
                  <b-dropdown-item href="" variant="danger" v-b-modal.review-delete-modal>삭제</b-dropdown-item>
                  <b-modal id="review-delete-modal" @ok="deleteReview">
                    <p><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%" />소중한 리뷰를 정말 삭제하시겠습니까?</p>
                  </b-modal>
                </div>
                <div v-else>
                  <b-dropdown-item href="#" variant="danger">신고</b-dropdown-item>
                </div>
              </b-dropdown>
              </b-col>
            </b-row>
          </b-card-text>
        </template>

        <!-- 2. 본문 부분 -->
        <b-row v-if="fileId.length > 0" align-h="center">
          <b-carousel
            id="carousel-1"
            v-model="slide"
            controls
            indicators
            background="#ababab"
            img-width="1024"
            img-height="480"
            style="text-shadow: 1px 1px 2px #333; width: 30em; height: 15em;"
            :fade="true"
          >
            <b-carousel-slide
              id="review_img"
              v-for="(item, index) in fileId"
              :key="index"   
              :img-src="url+`/review/download/` + item" 
            ></b-carousel-slide>
          </b-carousel>
        </b-row>
        <b-row>
          <div class="my-3 mx-5" style="text-align: left;">
            <h6>{{review.reviewContent}}</h6>
          </div>
        </b-row>

        
    



        <!-- 3. footer 부분 -->
          <template #footer>
            <div style="text-align: left;">
            <span class="reviewLike mt-5"> <!--좋아요 여부와 좋아요 수-->
              <b-icon icon="suit-heart-fill" variant="danger" v-if="liked" @click="likeReview()"></b-icon>
              <b-icon icon="suit-heart" variant="danger" v-else @click="likeReview()"></b-icon>
            </span>
              <small class="ml-2">{{review.reviewLikeCount}}명이 좋아합니다.</small>
            </div>
          </template>
        </b-card>
      <div>
    </div>   
  </div>
</template>

<script>
import axios from 'axios'
import { mapGetters } from "vuex";
const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'ReviewBlock',
  components: {
  },
  props: {
    review : {},

  },
  computed: {
    ...mapGetters(["getUserId"]),
    ...mapGetters(["getUserName"])
  },
  created() {
    this.getLikeInfo();
  },
  data: function() {
    return {
      liked: false,
      rate : "",
      userId: '',
      reviewDetail : {},
      url : SERVER_URL,
      fileId : Array,
      
      slick_settings: {
        "dots": true,
        "fade": true,
        "infinite": true,
        "speed": 500,
        "slidesToShow": 1,
        "slidesToScroll": 1,
        "arrows": true,
      },

      // Carousel에 사용하는 데이터
      slide: 0,
      thumbnailContent : [],  
    }
  },
  
  methods: {
    deleteReview: function () {
      // axios.delete(`${SERVER_URL}/review` + `${this.review.reviewId}`)
      axios.delete(`${SERVER_URL}/review`, this.review)
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
    },
      GetReviewDetail: function() {
      axios
      .get(`${SERVER_URL}/review/` + `${this.review.reviewId}`)
      .then((response) => {
        this.fileId = response.data.fileId;
        for (let index = 0; index < response.data.dto.rate; index++) {
          this.rate = this.rate + "★";
          
        }

        // console.log( this.fileId);

      })
      .catch((response) => {
        console.log(response);
      });
    },
    likeReview() {
      axios
        .post(`${SERVER_URL}/review/comment/like`, {
            storeId: this.review['storeId'],
            userId: this.getUserId,
            reviewId: this.review['reviewId'],
        })
        .then((response) => {
            this.liked = !response.data.includes("취소");
            if(this.liked) {
              this.review['reviewLikeCount'] = this.review['reviewLikeCount']*1 + 1;
            } else {
              this.review['reviewLikeCount'] = this.review['reviewLikeCount']*1 - 1;
            }
        });
    },
    getLikeInfo(){
      axios
        .get(`${SERVER_URL}/review/comment/like`, {
          params: {
            userId: this.getUserId,
            reviewId: this.review['reviewId']
            
          }
        })
        .then(
          (response) => (
            this.liked = response.data
          )
        );
    },


  },
  async mounted () {
    await this.GetReviewDetail();
    const userInfo = JSON.parse(localStorage.getItem('Info-token'))
    this.userId = userInfo["userId"]
  },
};
</script>

<style>
#review_img {
  top: 0;
  left: 0;
  min-width: 30em;
  min-height: 15em;
  max-width: 30em;
  max-height: 15em;
}
b-card-img {
  height: 5px;
  width: 5px;
}
</style>
