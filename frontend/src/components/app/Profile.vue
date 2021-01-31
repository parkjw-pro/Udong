<template>
  <div>
    <div class="profile_box" style="background: #BDBDBD;">
      <img class="profile_image" src="@/assets/story/group_sample_diet.jpg">
    </div>
  </div>
</template>

<script>
export default {
  name: 'Profile'
}
</script>

<style>
/* 프로필을 담는 박스 */
 .profile_box {
   width: 150px;
   height: 150px; 
   border-radius: 70%;
   overflow: hidden;
 }

/* 프로필 이미지 */
 .profile_image {
   width: 100%;
   height: 100%;
   object-fit: cover;
 }
</style>