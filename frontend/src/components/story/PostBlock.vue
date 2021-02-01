<template>
  <div>
    <!-- <b-card-group deck> -->
      <b-card header-tag="header" footer-tag="footer"> <!-- title="Title" 속성 사용 가능  -->
        <template #header>
          <b-card-text class="font-weight-bold">
            <span class="mr-5">뱃지 img</span>
            <span >용용이</span>
          </b-card-text>
        </template>
        <div> <!-- for 문 -->
          <b-card-img class="col-3 mb-5" src="https://placekitten.com/480/210" alt="Image" bottom></b-card-img>
        </div>
        <p>게시글 내용</p>
        <template #footer>
          <b-row class="h2 mb-2" align-h="between">
        <b-icon icon="suit-heart" variant="danger"></b-icon>
        <b-icon icon="suit-heart-fill" variant="danger"></b-icon>
        <b-icon icon="chat" variant="warning"></b-icon>
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
  name: 'PostBlock'
}
</script>

<style>
  
  /* 적용안됨 */
  b-card-img {
    height: 5px;
    width: 5px;
  }
</style>