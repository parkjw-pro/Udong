<template>
  <div class="mb-5 pb-5" style="font-family: 'Nanum Pen Script', cursive;">
    <div class="my-5">
      <b-button size="" variant="info" v-on:click="randomize" v-b-tooltip.hover title="내가 사용한 태그">Shuffle</b-button>
    </div>
    <div>
      <h3
        v-for="(tag, i) in tags" 
        :key="i"
        class="circle" 
        :style="{background: colors[Math.floor(Math.random()*colors.length)]}"
      ># {{tag}}</h3>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'TagBox',
  props:{
    user: Object
  },
  data: function () {
    return {
      userposts: [],
      userPostCount: 0,
      groupposts: [],
      groupPostCount: 0,
      limit: 100,  //태그를 가져올 게시글 순(최근순 정렬)
      offset: 0,  //게시글 번호 오프셋,
      tags: [],
      colors: ['#D5D6EA', '#F6F6EB', '#D7ECD9', '#F5D5CB', '#F6ECF5', '#F3DDF2'],
    }
  },
  async created() {
    await this.getPosts()
    this.getTags();
  },
  methods: {
    async getPosts() {
      await axios
        .get(`${SERVER_URL}/userpost/user`, {
          params: {
            userId: this.user.userId,
            limit: this.limit,
            offset: this.offset
          }
        })
        .then((response) => {
            this.userposts = response.data.list;
            this.userPostCount = response.data.count;
        });

      await axios
        .get(`${SERVER_URL}/clubpost/user`, {
          params: {
            userId: this.user.userId,
            limit: this.limit,
            offset: this.offset
          }
        })
        .then((response) => {
            this.groupposts = response.data.list;
            this.groupPostCount = response.data.count;
        });
    },
    getTags: async function() {
      for(let up of this.userposts){
        if(up.postTag != ""){

          console.log(up.postTag)
          var t1 = up.postTag;
          if(t1 != null)
            this.tags.push(...t1.split("#").slice(1));
        }
      }
      for(let gp of this.groupposts){
        if(gp.postTag != ""){
          var t2 = gp.postTag;
          if(t2 != null)
            this.tags.push(...t2.split("#").slice(1));
        }
      }
      console.log(this.tags);
    },
    randomize(){
      for (let i = this.tags.length - 1; i > 0; i--) {
        let randomIndex = Math.floor(Math.random() * i)
        let temp = this.tags[i]
        this.$set(this.tags, i, this.tags[randomIndex])
        this.$set(this.tags, randomIndex, temp)
      }
    },
    getRandom: function () {
      // for문을 돌려줘야 한다!!!!
      var circles = this.circles
      circles.circle1.left = Math.floor(Math.random() * 20)
      circles.circle1.top = Math.floor(Math.random() * 300)
      circles.circle2.left = Math.floor(Math.random() * 20)
      circles.circle2.top = Math.floor(Math.random() * 300)
      circles.circle3.left = Math.floor(Math.random() * 20)
      circles.circle3.top = Math.floor(Math.random() * 300)
      circles.circle4.left = Math.floor(Math.random() * 20)
      circles.circle4.top = Math.floor(Math.random() * 300)
      circles.circle5.left = Math.floor(Math.random() * 20)
      circles.circle5.top = Math.floor(Math.random() * 300)
      circles.circle6.left = Math.floor(Math.random() * 20)
      circles.circle6.top = Math.floor(Math.random() * 300)
    }
  },
  // created: async function() {
  //     await this.getRandom()
  // }
}
</script>

<style scoped>
.circle {
  border-radius: 50%;
  display: inline;
  position: relative;
  margin: 2%;
  margin-bottom: 30rem;
  /* box-shadow: 0.375em 0.375em 0 0 rgba(15, 28, 63, 0.125); */
}

#tags {
  width: 10rem;
  height: 10rem;
  max-width: 10rem;
  max-height: 10rem;
}
</style>