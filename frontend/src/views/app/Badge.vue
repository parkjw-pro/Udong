<template>
  <div id="box">
    <h3>{{ nickname}}<small>님의 뱃지</small></h3>
    <b-row class="mt-5">
      <b-col class="badge">
        <img alt="Vue logo" src="@/assets/app/badge1.jpg"> 
        <p class="mt-2">열정적인 노동자</p>
      </b-col>
      <b-col class="badge">
        <img class="unacquired" alt="Vue logo" src="@/assets/app/badge2.jpg"> 
        <p class="mt-2 unacquired_text">우동 영웅</p>
      </b-col>
      <b-col class="badge">
        <img class="unacquired" alt="Vue logo" src="@/assets/app/badge3.jpg"> 
        <p class="mt-2 unacquired_text">우동 귀요미</p>
      </b-col>
    </b-row>
    <b-row>
      <b-col class="badge">
        <img class="unacquired" alt="Vue logo" src="@/assets/app/badge4.jpg"> 
        <p class="mt-2 unacquired_text">코끼끼끼리리리</p>
      </b-col>
      <b-col class="badge">
        <img class="unacquired" alt="Vue logo" src="@/assets/app/badge5.jpg"> 
        <p class="mt-2 unacquired_text">창과 방패와의 싸움</p>
      </b-col>
      <b-col class="badge">
        <img class="unacquired" alt="Vue logo" src="@/assets/app/badge6.jpg"> 
        <p class="mt-2 unacquired_text">무지개떡</p>
      </b-col>
    </b-row>
    <b-row>
      <b-col class="badge">
        <img class="unacquired" alt="Vue logo" src="@/assets/app/badge7.jpg"> 
        <p class="mt-2 unacquired_text">리뷰왕!</p>
      </b-col>
      <b-col class="badge">
        <img class="unacquired" alt="Vue logo" src="@/assets/app/badge8.jpg"> 
        <p class="mt-2 unacquired_text">21번 도로의 지배자</p>
      </b-col>
      <b-col class="badge">
        <img class="unacquired" alt="Vue logo" src="@/assets/app/badge9.jpg"> 
        <p class="mt-2 unacquired_text">우동 큐피터</p>
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  name: 'Badge',
  data: function () {
    return {
      nickname: ''
    }
  },
  created () {
    this.nickname = JSON.parse(localStorage.getItem('Info-token'))["nickname"]
  }
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
    background-color: #f7f7f7;;
    cursor: pointer;
}

.unacquired {
  filter: brightness(50%);
}

.unacquired_text {
  color: #cec4ea;
}
</style>