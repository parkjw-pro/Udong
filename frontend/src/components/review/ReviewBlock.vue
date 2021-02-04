<template>
  <div>
    <!-- <b-card-group deck> -->
      <b-card header-tag="header" footer-tag="footer"> <!-- title="Title" 속성 사용 가능  -->
        <template #header>
          <b-card-text class="font-weight-bold">
            <span class="mr-5">뱃지 img</span>
            <span >작성자</span>
          </b-card-text>
        </template>
        <div> <!-- for 문 -->
          <b-card-img class="col-4 mb-5" src="https://placekitten.com/480/210" alt="Image" bottom></b-card-img>
          <b-card-img class="col-4 mb-5" src="https://placekitten.com/480/210" alt="Image" bottom></b-card-img>
          <b-card-img class="col-4 mb-5" src="https://placekitten.com/480/210" alt="Image" bottom></b-card-img>
        </div>
        <p>게시글 내용</p>
        <template #footer>
          <div style="text-align: left;">
            <b-icon icon="suit-heart" variant="danger"></b-icon>
            <b-icon icon="suit-heart-fill" variant="danger"></b-icon>
            <small class="ml-2">n명이 좋아합니다.</small>
          </div>
        </template>
      </b-card>
    <!-- </b-card-group> -->
   <div>
</div>   
  </div>
</template>

<script>
export default {
  name: 'ReviewBlock'
}
</script>

<style>
  
  /* 적용안됨 */
  b-card-img {
    height: 5px;
    width: 5px;
  }
</style>