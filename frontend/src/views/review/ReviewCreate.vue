<template>
  <div id="box">
    <div id="reviewCreateBox">
      <!-- 리뷰가게명 -->
      <div class="mt-3 pt-3">
        <h1>
          <span style="font-family: 'Hanna', sans-serif;">{{ store.storeName }} </span>
          <span class="small">리뷰하기</span>
        </h1>
        <h4 class="small">{{ store.storeAddr}}</h4>
      </div>

      <!-- 1. 평점 -->
      <b-row class="my-3" align-h="center">
        <star-rating v-model="review.rate" v-bind:star-size="40" v-bind:show-rating="true" v-bind:active-on-click="true" ></star-rating>
      </b-row>
      
      <!-- 2. 리뷰 내용 -->
      <div class="mx-5 px-5 mt-4">
        <b-form-textarea
          id="textarea-rows"
          v-model="review.reviewContent"
          placeholder="소중한 리뷰를 남겨주세요!"
          rows="11"
          class="text-align-center my-3"
          maxlength="500"
          style="overflow: hidden;"
        ></b-form-textarea>
        <p style="text-align: right;">{{ contentLength }} / 500</p>
      </div>

      <!-- 3. 이미지 -->
      <b-container fluid class="my-3 pb-5" style=""><!-- width: 70% -->
        <b-row class="py-3">
          <b-col cols="3" v-for="(url, index) in imageUrl" :key="index" left align-self="center">
            <b-img class="px-3"  id="img_thumbnail" thumbnail fluid :src="url">
            </b-img>
          </b-col>
          <b-col cols="3" class="ml-0 pl-0" align-self="center">
            <b-icon icon="plus" v-b-modal.image-modal font-scale="4" variant="dark" style="cursor: pointer;">
              &nbsp;
            </b-icon>
          </b-col>
        </b-row>
      </b-container>

      <!-- 이미지 업로더 modal -->
      <b-modal 
        id="image-modal"
        ref="image-modal"
        title="소중한 사진을 올려주세요!"
        style="font-family: 'Jeju Gothic', sans-serif;"
        hide-footer
      >
        <b-form-file
          multiple="multiple"
          v-model="files"
          placeholder="첨부파일 없음"
          drop-placeholder="Drop file here..."
          required
          accept=".jpg, .png, .gif"
          style="width: 70%;"
          @change="previewImage"
        ></b-form-file>
        <b-row class="mt-3 mx-3" align-h="end">
          <b-button class="mr-1" variant="danger" size="sm" @click="hideModal">추가 안 할래요</b-button>
          <b-button variant="primary" size="sm" @click="hideModal">추가하기!</b-button>
        </b-row >
      </b-modal>



      <b-row align-h="center" class="mb-3 pb-3">
        <b-col cols="3">
          <b-button size="lg" variant="danger" v-b-modal.review-cancel-modal>돌아가기</b-button>
        </b-col>
        <b-col cols="3">
          <b-button size="lg" style="background-color: #695549;" @click="createReview">리뷰작성</b-button>
        </b-col>
      </b-row>

      <!-- 돌아가기 버튼 클릭 후 나타나는 modal -->
      <b-modal id="review-cancel-modal" @ok="toBack">
        리뷰 작성을 취소하시겠습니까?
      </b-modal>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import StarRating from 'vue-star-rating'

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'ReviewCreate',
  // props: {
  //   store: Object,
  // },
  components: {
    StarRating,
  },
  data: function() {
    return {
      storeId: '',
      store: {},
      review: {
        userId: '',
        reviewContent: '',
        rate: 0,
        nickName: '',
      },
      imageUrl: [],
      modal: true, //true : modal열림, false : modal닫힘
      files: [],
      
      isValid: 0,
    }
  },
  computed: {
    contentLength: function() {
      return this.review.reviewContent.length;
    },
    stars: function () {
      console.log(this.review.rate)
      return this.review.rate
    },
  },
  methods: {
    // store 정보 가져오기
    getStore: function() {
      axios
      .get(`${SERVER_URL}/store/` + `${this.storeId}`)
      .then((res) => {
        // console.log(res)
        this.store = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    },
    // 작성한 리뷰 유효성 검사!!!
    checkValidity: function() {
      if (this.review.rate == 0) {
        this.isValid = 0
        alert("평점을 선택해주세요!")
      } else if (this.review.reviewContent.length < 5) {
        this.isValid = 0
        alert("리뷰를 5자 이상 작성해주세요!")
      } else {
        this.isValid = 1
      }
    },
    createReview: function() {
      this.checkValidity()
      if (this.isValid) {
        var text = document.getElementById("textarea-rows").value;
        text = text.replace(/(?:\r\n|\r|\n)/g, '<br/>')
        document.getElementById("textarea-rows").value = text
        this.review.reviewContent = text
        var formData = new FormData();
        formData.append('reviewContent', this.review.reviewContent);
        formData.append('rate', this.review.rate);
        formData.append('storeId', this.store.storeId);
        formData.append('userId', this.review.userId);
        for (let i = 0; i < this.files.length; i++) {
          formData.append('file', this.files[i]);
        }
        if(this.files == null){
          formData.append('file', "1");
        }

        // review 내용 줄바꾸기


        console.log(this.files);
        // formData.append('club', this.club)
        console.log(formData);
        // if (this.verification) {
          axios
          .post(`${SERVER_URL}/review`, formData, {
            headers: { 'Content-Type': `application/json; charset=UTF-8` },
          })
          .then(() => {
            // console.log('응으응')
            // console.log(this.store)
            this.$router.push({ name: 'ReviewDetail', params: { storeId: this.storeId } });
          })
          .catch((err) => {
            console.log(err);
          });
      }

    },
    toBack: function () {
      window.history.back()
    },
    // 이미지 preview 보여주는 함수
    previewImage(event) {
      for(var i =0; i < this.imageUrl.length;i++){ this.imageUrl[i] = ""} 
    
      for (var image of event.target.files) {
        const file = image;
        console.log(file)
        this.imageUrl.push(URL.createObjectURL(file));
      }
      this.$refs['image-modal'].hide()
    },
    hideModal() {
      this.$refs['image-modal'].hide()
    },
  },
  async mounted () {
    this.storeId = this.$route.params.storeId
    await this.getStore()
    this.review.userId = JSON.parse(localStorage.getItem('Login-token'))['user-id'];
  },
};
</script>

<style>
#img_thumbnail {
  width: 10rem;
  height: 12rem;
  max-width: 10rem;
  max-height: 12rem;
}
</style>
