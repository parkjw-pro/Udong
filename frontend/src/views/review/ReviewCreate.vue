<template>
  <div id="box">
    <!-- 리뷰가게명 -->
    <!-- <h3>{{ store.name }}</h3> -->

    <!-- 1. 평점 -->
    <b-row class="mx-5 mb-5" align-h="justify">
      <b-col
        ><b-icon id="emoji_rate" icon="emoji-smile" font-scale="4" @click="rate3"></b-icon
      ></b-col>
      <b-col
        ><b-icon id="emoji_rate" icon="emoji-neutral" font-scale="4" @click="rate2"></b-icon
      ></b-col>
      <b-col
        ><b-icon id="emoji_rate" icon="emoji-frown" font-scale="4" @click="rate1"></b-icon
      ></b-col>
    </b-row>

    <!-- 2. 리뷰 내용 -->
    <div class="mx-5 px-5">
      <b-form-textarea
        id="textarea-rows"
        v-model="review.reviewContent"
        placeholder="소중한 리뷰를 남겨주세요!"
        rows="14"
        class="text-align-center my-3"
      ></b-form-textarea>
      <p style="text-align: right;">글자 수: {{ contentLength }} / 500</p>
    </div>

    <!-- 3. 이미지 -->
    <b-container fluid class="p-4 mb-5">
      <b-row>
        <b-col>
          <b-img
            left
            thumbnail
            fluid
            src="https://picsum.photos/250/250/?image=54"
            alt="Image 1"
          ></b-img>
        </b-col>
        <b-col>
          <b-img
            left
            thumbnail
            fluid
            src="https://picsum.photos/250/250/?image=58"
            alt="Image 2"
          ></b-img>
        </b-col>
      </b-row>
    </b-container>

    <b-container fluid class="pa-0">
      <b-row align="center">
        <b-col cols="12" sm="12" md="12">
          <div class="text-center">
            <div class="my-2">
              <b-btn @click="$bvModal.show('bv-modal-example')" large color="blue" dark>
                <!-- openDialog 클릭 이벤트 -->
                파일 업로드&nbsp;
              </b-btn>
            </div>
          </div>
        </b-col>
      </b-row>
    </b-container>
    <b-modal id="bv-modal-example" persistent max-width="900px">
      <b-card>
        <b-card-title>
          <template>
            <b-icon style="margin-right:10px;" large color="#41B883">cloud_upload</b-icon>
            <span class="headline" large>파일 업로드  </span>
          </template>
          <b-spacer></b-spacer>
          <b-btn icon @click="$bvModal.hide('bv-modal-example')">
            <!-- closeDialog 클릭 이벤트 -->
            <b-icon>clear</b-icon>
          </b-btn>
        </b-card-title>
        <b-card-text>
          <b-row>
            <b-col
              cols="12"
              sm="12"
              md="12"
              style="position: relative; border:1px solid #41B883; border-style:dashed; "
            >
              <!-- 업로드 컴포넌트 -->
              <b-form-file
                multiple="multiple"
                v-model="files"
                :state="Boolean(file1)"
                placeholder="첨부파일 없음"
                drop-placeholder="Drop file here..."
                required
                accept=".jpg, .png, .gif"
                @change="previewImage"
                style="width: 70%;"
              ></b-form-file>
            </b-col>
          </b-row>
        </b-card-text>
      </b-card>
    </b-modal>
    <b-row align-h="between" class="mx-5 mb-5">
      <b-button variant="info">취소</b-button>
      <b-button @click="createReview">확인</b-button>
    </b-row>
  </div>
</template>

<script>
import axios from 'axios'
const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'ReviewCreate',
  // props: {
  //   store: Object,
  // },
  components: {},
  data: function() {
    return {
      review: {
        userId: '',
        reviewContent: '',
        rate: 1,
        nickName: '',
      },
      store: {},
      storeId: '',
      modal: false, //true : modal열림, false : modal닫힘
      files: [],
    };
  },
  mounted: async function() {
    this.storeId = this.$route.params.storeId
    await this.getStore()
    console.log(this.store);
    this.review.userId = JSON.parse(localStorage.getItem('Login-token'))['user-id'];
  },
  computed: {
    contentLength: function() {
      return this.review.reviewContent.length;
    },
  },
  methods: {
    getStore: function() {
      axios
      .get(`${SERVER_URL}/store/` + `${this.storeId}`)
      .then((response) => {
        this.store = response.data;
      })
      .catch((response) => {
        console.log(response);
      });
    },
    rate1: function() {
      this.review.rate = 1;
    },
    rate2: function() {
      this.review.rate = 2;
    },
    rate3: function() {
      this.review.rate = 3;
    },
    checkReview: function() {
      // 유효성 검사!!!
      // if (review.rate === -1) {
      // modal창 띄우기: "평점을 선택해주세요!"
      console.log('checkreview');
    },
    createReview: function() {
      // console.log(this.previewImageData[0]);
      console.log(this.files);
      console.log(this.files[0].name);
      console.log(this.review.reviewContent);
      console.log(this.review.rate);
      console.log(this.store.storeId);
      console.log(this.review.userId);
      var formData = new FormData();
      formData.append('reviewContent', this.review.reviewContent);
      formData.append('rate', this.review.rate);
      formData.append('storeId', this.store.storeId);
      formData.append('userId', this.review.userId);
      for (let i = 0; i < this.files.length; i++) {
        formData.append('file', this.files[i]);
      }
      console.log(this.files);
      // formData.append('club', this.club)
      console.log(formData);
      // if (this.verification) {
      axios
        .post(`${SERVER_URL}/review`, formData, {
          headers: { 'Content-Type': `application/json; charset=UTF-8` },
        })
        .then(() => {
          console.log('응으응')
          console.log(this.store)
          this.$router.push({ name: 'ReviewDetail', params: { storeId: this.storeId } });
        })
        .catch((err) => {
          console.log(err);
        });
      // }
      //  else {
      //    this.isVerified = false
      //  }
    },

    openmodal() {
      //Dialog 열리는 동작
      this.modal = true;
    },
    closemodal() {
      //Dialog 닫히는 동작
      this.modal = false;
    },
  },
};
</script>

<style>
#emoji_rate {
  cursor: pointer;
}
</style>
