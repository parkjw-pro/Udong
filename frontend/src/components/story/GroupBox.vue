<template>
  <div class="mb-5 pb-5" style="font-family: 'Nanum Pen Script', cursive;">
    <div class="my-5">
      <b-button size="" variant="info" v-on:click="randomize" v-b-tooltip.hover title="내 그룹">Shuffle</b-button>
    </div>
    <div>
      <h3 
        id="groups"
        v-for="(group, i) in groups" 
        :key="i"
        class="circle mx-3"
        style="cursor: pointer;"
        :style="{background: colors[Math.floor(Math.random()*colors.length)]}"
        @click="toGroupPage(group)"
        >
          {{group.clubName}} 
      </h3>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'GroupBox',
  props: {
    userId: String, 
  },
  data: function () {
    return {
      // userId: JSON.parse(localStorage.getItem('Info-token'))["userId"],
      groups: [], // 가입한 그룹 
      selected: 0,  //선택된 그룹
      colors: ['#D5D6EA', '#F6F6EB', '#D7ECD9', '#F5D5CB', '#F6ECF5', '#F3DDF2'],
    }
  },
  mounted: async function() {
    await this.init();
  },
  methods: {
    init() {
      //가입한 그룹 정보 가져오기
    axios
      .get(`${SERVER_URL}/club/user/${this.userId}/member`)
      .then(
        (response) => {
          this.groups = response.data;
          // for(var i in this.groups) {
          //   this.groups[i].top = 0;
          //   this.groups[i].left = 0;
          // }
        }
      );
    },
    randomize(){
      for (let i = this.groups.length - 1; i > 0; i--) {
        let randomIndex = Math.floor(Math.random() * i)
        let temp = this.groups[i]
        this.$set(this.groups, i, this.groups[randomIndex])
        this.$set(this.groups, randomIndex, temp)
      }
    },
    toGroupPage (group) {
      this.$router.push({ name: 'GroupPage', params: { address: group.areaCode, groupId: group.clubId}})
    }
  },
}
</script>

<style>
.circle {
  border-radius: 50%;
  display: inline;
  position: relative;
  /* box-shadow: 0.375em 0.375em 0 0 rgba(15, 28, 63, 0.125); */
}

#groups {
  width: 10rem;
  height: 10rem;
  max-width: 10rem;
  max-height: 10rem;
}
</style>