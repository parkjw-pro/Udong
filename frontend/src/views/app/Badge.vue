<template>
  <div id="box">
    <h3>{{ nickname }}<small>님의 뱃지</small></h3>
    <br>
    <b-row class="mt-5">
      <b-col class="badge">
        <img
          @click="mainBadge(1)"
          v-bind:class="[badgecount > 0 ? acquired : '', unacquired]"
          alt="Badge"
          src="@/assets/app/badge/badge1.png"
          style="width: 100%;"
        />
        <p class="mt-2">첫 리뷰</p>
      </b-col>
      <b-col class="badge">
        <img
          @click="mainBadge(2)"
          v-bind:class="[badgecount > 1 ? acquired : '', unacquired]"
          alt="Badge"
          src="@/assets/app/badge/badge2.png"
          style="width: 100%;"
        />
        <p class="mt-2">첫 게시글</p>
      </b-col>
      <b-col class="badge">
        <img
          @click="mainBadge(3)"
          v-bind:class="[badgecount > 2 ? acquired : '', unacquired]"
          alt="Badge"
          src="@/assets/app/badge/badge3.png"
          style="width: 100%;"
        />
        <p class="mt-2">그룹장</p>
      </b-col>
    </b-row>
    <b-row class="my-5">
      <b-col class="badge">
        <img
          @click="mainBadge(4)"
          v-bind:class="[badgecount > 3 ? acquired : '', unacquired]"
          alt="Badge"
          src="@/assets/app/badge/badge4.png"
          style="width: 100%;"
        />
        <p class="mt-2">카페홀릭</p>
      </b-col>
      <b-col class="badge">
        <img
          @click="mainBadge(5)"
          v-bind:class="[badgecount > 4 ? acquired : '', unacquired]"
          alt="Badge"
          src="@/assets/app/badge/badge5.png"
          style="width: 100%;"
        />
        <p class="mt-2">리뷰왕</p>
      </b-col>
      <b-col class="badge" cols="4" style="display: block;">
        
      </b-col>
    </b-row>
    <b-modal v-model="modalShow" @ok="setmainBadge">
      <p>
        <img alt="Badge" src="@/assets/udonge.png" style="width: 10%" />
        메인벳지로 하시겠습니까??
      </p>
    </b-modal>
    <b-modal v-model="modalShow2">
      <p>
        <img alt="Badge" src="@/assets/udonge.png" style="width: 10%" />
        활동이 부족해요!!
      </p>
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: 'Badge',
  components: {
  },
  data: function() {
    return {
      unacquired: 'unacquired',
      acquired: 'acquired',
      nickname: '',
      userId: '',
      badgelist: {},
      badgecount: 0,
      index: '',
      modalShow: false,
      modalShow2: false,
    };
  },
  async created() {
    this.nickname = JSON.parse(localStorage.getItem('Info-token'))['nickname'];
    this.userId = JSON.parse(localStorage.getItem('Info-token'))['userId'];
    await this.selectBadge();
  },
  methods: {
    selectBadge() {
      console.log(this.userId);
      axios
        .get(`${SERVER_URL}/user/badge`, {
          params: {
            userId: this.userId,
          },
        })
        .then((response) => {
          this.badgeList = response.data;
          this.badgecount = response.data.length;
          // alert('대표뱃지 변경이 완료되었습니다! \n왼쪽 메뉴에서 확인해주세요!')
        });
    },

    mainBadge(index) {
      if (index > this.badgecount) {
        this.modalShow2 = !this.modalShow2;
      } else {
        this.index = index;
        this.modalShow = !this.modalShow;
      }
    },
    setmainBadge() {
      console.log(this.userId);
      console.log(this.index);
      axios
        .put(`${SERVER_URL}/user/badge?userId=${this.userId}&&badgeId=${this.index}`)
        .then((response) => {
          console.log(response);

          const userInfo = JSON.parse(localStorage.getItem('Login-token'));
          userInfo.user_badge = 'badge' + this.index;
          localStorage.setItem('Login-token', JSON.stringify(userInfo));
          console.log(this.badgecount);
          location.replace(true);
        });
    },
  },
};
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
  background-color: #f7f7f7;
  cursor: pointer;
}

.unacquired {
  filter: brightness(20%);
}
.acquired {
  filter: brightness(100%);
}

</style>
