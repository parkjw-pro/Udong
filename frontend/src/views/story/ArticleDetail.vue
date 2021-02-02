<template>
  <!-- 이미지 추가하는 부분을 넣어야 한다!!! -->
  <div id="article_box">
    <b-row class="mb-5" align-h="between">
      <b-col><h5 class="font-weight-bold">그룹</h5></b-col>
      <b-col><h5 class="font-weight-bold">다이어트</h5></b-col>
    </b-row>

     <!-- 이미지 슬라이드 -->
    <ImageSlick />

      <b-form-textarea
        class="mb-5"
        id="textarea"
        placeholder="하... 진짜 이거 왜 이렇게 안 되는지 모르겠네... 어떻게 해야 될까!!! 누가 좀 알려줘!!!!! 미춰버릴거같아!!!!!!!!!!!!!!!!"
        rows="8"
        disabled
      ></b-form-textarea>
      <br>

      <b-row class="h2 mb-2" align-h="between">
        <b-icon icon="suit-heart" variant="danger"></b-icon>
        <b-icon icon="suit-heart-fill" variant="danger"></b-icon>
        <!-- <b-icon icon="chat" variant="warning"></b-icon> -->
        <b-dropdown size="lg" dropup variant="link" toggle-class="text-decoration-none" no-caret>
          <template #button-content>
            <b-icon icon="list"></b-icon>
          </template>
          <b-dropdown-item href="#" variant="danger">신고</b-dropdown-item>
          <b-dropdown-item href="#" variant="danger">삭제</b-dropdown-item>
        </b-dropdown>
      </b-row>
      
      <br>
      <br>
      <!-- 댓글 -->
      <b-list-group style="text-align: left;">  <!-- for 문 -->
        <b-list-group-item><span class="font-weight-bold">용용</span>  멋있어요  <span class="small">몇 분 전</span></b-list-group-item>
        <b-list-group-item>Dapibus ac facilisis in</b-list-group-item>
        <b-list-group-item>Morbi leo risus</b-list-group-item>
        <b-list-group-item>Porta ac consectetur ac</b-list-group-item>
        <b-list-group-item>Vestibulum at eros</b-list-group-item>
      </b-list-group>
     
      <!-- <b-row align-h="between" class="mx-5 mb-5">
        <b-button variant="info">취소</b-button>
        <b-button type="submit" variant="info">확인</b-button>
      </b-row> -->



      <br>
      <br>
      <br>

  </div>
  
</template>

<script>
import ImageSlick from '@/components/story/ImageSlick'



export default {
  name: 'ArticleDetail',
  components: {
    ImageSlick
  }
}




</script>

<style>


/* ### {
  font-size: 26px;
  font-weight: 600;
  color: #2c3e5099;
  text-rendering: optimizelegibility;
  -moz-osx-font-smoothing: grayscale;
  -moz-text-size-adjust: none;
} */

#dropdown_group {
  max-width: 30em;
  /* margin: 1em auto; */
  margin-right: 50px;
}

#article_box {
  width: 50%;
  height: 50%;
  position: absolute;
  left: 25%;
  margin-top: 5%;
}


</style>