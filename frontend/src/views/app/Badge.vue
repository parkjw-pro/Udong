<template>
  <div>
    <h3>나의 뱃지</h3>
    <div>
      
      <div class="badge">
        <img alt="Vue logo" src="@/assets/app/badge1.jpg"> 
        <p>1뱃지</p>
      </div>
     
      <div class="badge">
        <img alt="Vue logo" src="@/assets/app/badge2.jpg"> 
        <p>2뱃지</p>
      </div>

      <div class="badge">
        <img alt="Vue logo" src="@/assets/app/badge3.jpg"> 
        <p>3뱃지</p>
      </div>

      <div class="badge">
        <img alt="Vue logo" src="@/assets/app/badge4.jpg"> 
        <p>4뱃지</p>
      </div>

      <div class="badge">
        <img alt="Vue logo" src="@/assets/app/badge5.jpg"> 
        <p>5뱃지</p>
      </div>

      <div class="badge">
        <img alt="Vue logo" src="@/assets/app/badge6.jpg"> 
        <p>6뱃지</p>
      </div>

      <div class="badge">
        <img alt="Vue logo" src="@/assets/app/badge7.jpg"> 
        <p>7뱃지</p>
      </div>

      <div class="badge">
        <img alt="Vue logo" src="@/assets/app/badge8.jpg"> 
        <p>8뱃지</p>
      </div>

      <div class="badge">
        <img alt="Vue logo" src="@/assets/app/badge9.jpg"> 
        <p>9뱃지</p>
      </div>

      <div class="badge">
        <img alt="Vue logo" src="@/assets/app/badge10.jpg"> 
        <p>10뱃지</p>
      </div>

    </div>
  </div>
</template>

<script>
export default {
    name: 'Badge',
}
</script>

<style>
.badge {
    width: 200px;
    height: 200px; 
    border-radius: 70%;
    /* overflow: hidden; */
    /* 이거 이미지 둥글게 안먹혀 */
    /* overflow hidden 주석 풀면 먹히는데 그러면 글씨 안보임;; */
    float: left;
    margin: 10px 10px 20px 0px;
    border: 1px;
    text-align: center;
}

</style>