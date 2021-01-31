<template>
  <div class="ml-5" style="text-align: left;">
    <div id="review_overview">
      <h3>우리 동네 리뷰</h3>
      <ul>
        <li>게시글</li>
        <li>게시글</li>
        <li>for문 돌릴 예정</li>
      </ul>
    </div>
    <hr>
    <div class="story_overview">
      <h3>우리 동네 이야기</h3>
      <ul></ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data: function () {
    return {
      reviews: [],
      stories: [],
    }
  },
  methods: {
    get_data: function () {
      // review, stories 데이터 axios 요청 보내기
    },
  },
  created: function () {

  }
}
</script>

<style>

</style>