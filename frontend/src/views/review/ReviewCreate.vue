<template>
  <div id="box">
    <!-- <h3>{{ store.name }}</h3> -->
    <b-row class="mx-5 mb-5" align-h="justify">
      <b-col><b-icon id="emoji_rate" icon="emoji-smile" font-scale="4" @click="rate3"></b-icon></b-col>
      <b-col><b-icon id="emoji_rate" icon="emoji-neutral" font-scale="4" @click="rate2"></b-icon></b-col>
      <b-col><b-icon id="emoji_rate" icon="emoji-frown" font-scale="4" @click="rate1"></b-icon></b-col>
    </b-row>
    <div class="mx-5 px-5">
      <b-form-textarea
        id="textarea-rows"
        v-model="review.reviewContent"
        placeholder="소중한 리뷰를 남겨주세요!"
        rows="14"
        class="text-align-center"
    ></b-form-textarea>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ReviewCreate',
  props: {
    store: Object,
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