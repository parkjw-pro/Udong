<template>
  <div class="mb-5 pb-5">
    <b-button variant="primary" v-on:click="get_random">SHUFFLE</b-button>
    <br>
    <br>
    <div>
      <div class="circle" id="circle1" v-bind:style="{ left: circles.circle1.left + 'px', top: circles.circle1.top + 'px' }"># 이송영집</div>
      <div class="circle" id="circle2" v-bind:style="{ left: circles.circle2.left + 'px', top: circles.circle2.top + 'px' }"># 바다</div>
      <div class="circle" id="circle3" v-bind:style="{ left: circles.circle3.left + 'px', top: circles.circle3.top + 'px' }"># 붕어빵</div>
      <div class="circle" id="circle4" v-bind:style="{ left: circles.circle4.left + 'px', top: circles.circle4.top + 'px' }"># 겨울</div>
      <div class="circle" id="circle5" v-bind:style="{ left: circles.circle5.left + 'px', top: circles.circle5.top + 'px' }"># 핫해핫해</div>
      <div class="circle" id="circle6" v-bind:style="{ left: circles.circle6.left + 'px', top: circles.circle6.top + 'px' }"># 노을</div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
  </div>
</template>

<script>
export default {
  name: 'GroupBox',
  data: function () {
    return {
      circles: {
        circle1: { left: 0, top: 0 },
        circle2: { left: 0, top: 0 },
        circle3: { left: 0, top: 0 },
        circle4: { left: 0, top: 0 },
        circle5: { left: 0, top: 0 },
        circle6: { left: 0, top: 0 },
      },
    }
  },
  methods: {
    get_random: function () {
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
  created: async function() {
      // await this.get_random()
  }
}
</script>

<style>
.circle {
  border-radius: 50%;
  display: inline;
  position: relative;
  /* box-shadow: 0.375em 0.375em 0 0 rgba(15, 28, 63, 0.125); */
}
#circle1 {
  background: #D5D6EA;
  height: 3em;
  width: 3em;
}
#circle2 {
  background: #F6F6EB;
  height: 10em;
  width: 10em;
}
#circle3 {
  background: #D7ECD9;
  height: 8em;
  width: 8em;
}
#circle4 {
  background: #F5D5CB;
  height: 15em;
  width: 15em;
}
#circle5 {
  background: #F6ECF5;
  height: 3em;
  width: 3em;
}
#circle6 {
  background: #F3DDF2;
  height: 3em;
  width: 3em;
}
</style>