<template>
  <div>
    <!-- <b-card-group deck> -->
      <b-card header-tag="header" footer-tag="footer"> <!-- title="Title" 속성 사용 가능  -->
        <template #header>
          <b-card-text class="font-weight-bold">
            <span class="mr-5">뱃지 img</span>
            <span >{{ post.nickname }}</span>
          </b-card-text>
        </template>
        <div> <!-- for 문 -->
          여기 이미지를 가져와야 한다!
          <b-card-img class="col-3 mb-5" src="https://placekitten.com/480/210" alt="Image" bottom></b-card-img>
        </div>
        <p>post.postContent</p>
        <template #footer>
          <b-row class="h2 mb-2" align-h="between">
            여기서는 좋아요에 사용자가 포함되어 있는지를 확인해야 한다! (조건 분기)
            <b-icon icon="suit-heart" variant="danger"></b-icon>
            <b-icon icon="suit-heart-fill" variant="danger"></b-icon>
            <b-icon icon="chat" variant="warning" @click="toDetail"></b-icon>
          </b-row>
        </template>
      </b-card>
    <!-- </b-card-group> -->
   <div>
</div>   
  </div>
</template>

<script>
export default {
  name: 'PostBlock',
  props: ['post'],
  data: function () {
    return {
      user: {

      }
    }
  },
  methods: {
    getUser: function () {
      // 유저 정보 가져오기
    },
    toDetail: function () {
      this.$router.push({ name: 'ArticleDetail', query: {post: this.post}})
    }
  }
}
</script>

<style>
  
  /* 적용안됨 */
  b-card-img {
    height: 5px;
    width: 5px;
  }
</style>