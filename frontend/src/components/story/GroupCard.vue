<template>
  <div>
    <div class="group_list_img mx-5 my-5" :style="background_image" >
      <div class="group_list_img_content">
        <h2>다이어트</h2>
      </div>
      <div class="group_list_img_cover"></div>
    </div>
  </div>
</template>

<script>

export default {
  name: "GroupCard",
  data: function () {
    return {
      // props한 이미지 가져오기
      background_image: {
        backgroundImage: `url(${require('@/assets/story/group_sample_diet.jpg')})`
      }
    }
  }
}
</script>


<style>
.group_list_img {
  position: relative;
  height: 100vh;
  background-size: cover;
  /* background-image: url("../../assets/story/group_sample_diet.jpg"); */
  width: 300px;
  height: 300px;
}

.group_list_img_cover {
  position: absolute;
  height: 300px;
  width: 300px;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 1;
}

.group_list_img_content {
  position: absolute;
  top: 280px;
  left: 70px;
  transform: translate(-50%, -50%);
  font-size: 5rem;
  font-weight: bold;
  color: white;
  z-index: 2;
  text-align: center;
}
</style>