<template>
  <!-- 이미지 추가하는 부분을 넣어야 한다!!! -->
  <div id="article_box">
    <b-row class="mb-5" align-h="between">
      <b-col><h5 class="font-weight-bold">그룹 선택</h5></b-col>
      <b-col id="dropdown_group">
         <v-select v-model="selected" :options="options"></v-select>
      </b-col>
    </b-row>
      <b-form-textarea
        class="mb-5"
        id="textarea"
        v-model="text"
        placeholder="게시글을 입력하세요"
        rows="8"
        max-rows="10"
      ></b-form-textarea>
      <br>
      <!-- 이미지 -->
      <b-container fluid class="p-4 mb-5">
        <b-row>
          <b-col>
            <b-img left thumbnail fluid src="https://picsum.photos/250/250/?image=54" alt="Image 1"></b-img>
          </b-col>
          <b-col>
            <b-img left thumbnail fluid src="https://picsum.photos/250/250/?image=58" alt="Image 2"></b-img>
          </b-col>
          <b-col>
            <b-img left thumbnail fluid src="" alt="Image 2">
               <b-form-file
                  v-model="file1"
                  :state="Boolean(file1)"
                  placeholder="Choose a file or drop it here..."
                  drop-placeholder="Drop file here..."
                ></b-form-file>
            </b-img>
          </b-col>
          <!-- <b-col>
            <b-img left thumbnail fluid src="https://picsum.photos/250/250/?image=59" alt="Image 3"></b-img>
          </b-col>
          <b-col>
            <b-img left thumbnail fluid src="https://picsum.photos/250/250/?image=59" alt="Image 3"></b-img>
          </b-col> -->
        </b-row>

      </b-container>
      <b-row align-h="between" class="mx-5 mb-5">
        <b-button variant="info">취소</b-button>
        <b-button type="submit" variant="info">확인</b-button>
      </b-row>
      <br>
      <br>
      <br>
  </div>
</template>

<script>


export default {
  name: 'ArticleCreate',
  data: function () {
    return {
      // Array로 선언
      options: [
        '내 피드',
        '다이어트',
        '피아노',
        '러닝',
      ],
      selected: '내 피드'
    }
  },
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