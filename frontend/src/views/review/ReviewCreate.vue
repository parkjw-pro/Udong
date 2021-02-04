<template>
  <div id="box">
    <!-- 리뷰가게명 -->
    <!-- <h3>{{ store.name }}</h3> -->


    <!-- 1. 평점 -->
    <b-row class="mx-5 mb-5" align-h="justify">
      <b-col><b-icon id="emoji_rate" icon="emoji-smile" font-scale="4" @click="rate3"></b-icon></b-col>
      <b-col><b-icon id="emoji_rate" icon="emoji-neutral" font-scale="4" @click="rate2"></b-icon></b-col>
      <b-col><b-icon id="emoji_rate" icon="emoji-frown" font-scale="4" @click="rate1"></b-icon></b-col>
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
            <b-img left thumbnail fluid src="https://picsum.photos/250/250/?image=54" alt="Image 1"></b-img>
          </b-col>
          <b-col>
            <b-img left thumbnail fluid src="https://picsum.photos/250/250/?image=58" alt="Image 2"></b-img>
          </b-col>
          <!-- <b-col>
               <b-form-file
                  v-model=""
                  :state="Boolean(file1)"
                  placeholder="Choose a file or drop it here..."
                  drop-placeholder="Drop file here..."
                ></b-form-file>
          </b-col> -->
          <!-- <b-col>
            <b-img left thumbnail fluid src="https://picsum.photos/250/250/?image=59" alt="Image 3"></b-img>
          </b-col>
          <b-col>
            <b-img left thumbnail fluid src="https://picsum.photos/250/250/?image=59" alt="Image 3"></b-img>
          </b-col> -->
        </b-row>
      </b-container>




      <!-- 4. 버튼 -->
      <b-row align-h="between" class="mx-5 mb-5">
        <b-button variant="info">취소</b-button>
        <b-button type="submit" variant="info">확인</b-button>
      </b-row>
  </div>
</template>

<script>

export default {
  name: 'ReviewCreate',
  props: {
    store: Object,
  },
  components: {
  },
  data: function () {
    return {
      review: {
        userId: '',
        storeId: '',
        reviewContent: '',
        rate: -1,
        nickName: '',
      }
    }
  },
  computed: {
    contentLength: function () {
      return this.review.reviewContent.length
    },
  },
  methods: {
    rate1: function () {
      this.review.rate = 1
    },
    rate2: function () {
      this.review.rate = 2
    },
    rate3: function () {
      this.review.rate = 3
    },
    createReview: function () {
      // 유효성 검사!!!
      // if (review.rate === -1) {
        // modal창 띄우기: "평점을 선택해주세요!"
      }
    }
}
</script>

<style>
#emoji_rate {
  cursor: pointer;
}
</style>