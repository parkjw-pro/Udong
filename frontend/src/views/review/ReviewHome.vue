<template>
  <div id="box">
    <div v-draggabilly v-packery='{itemSelector: ".packery-item", percentPosition: true}'>
      <div v-draggabilly v-packery-item class='packery-item'>
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=59" alt="Image 1"></b-img>
      </div>
      <div v-draggabilly v-packery-item class='packery-item'>    
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=54" alt="Image 2"></b-img>
      </div>
      <div v-draggabilly v-packery-item class='packery-item'>
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=58" alt="Image 3"></b-img>
      </div>
      <div v-draggabilly v-packery-item class='packery-item'>
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=23" alt="Image 3"></b-img>
      </div>
      <div v-draggabilly v-packery-item class='packery-item'>
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=35" alt="Image 3"></b-img>
      </div>
      <div v-draggabilly v-packery-item class='packery-item'>
        <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=44" alt="Image 3"></b-img>
      </div>
    </div>


    <!-- <div class="grid">
      <div class="grid-item"></div>
      <div class="grid-item grid-item--width2"></div>
      <div class="grid-item"></div>
      <div class="grid-item grid-item--height2"></div>
    </div> -->
  </div>
</template>


<script>
export default {
 name: 'ReviewHome',
}
</script>

<style>
.grid-item {
  float: left;
  width: 60px;
  height: 60px;
  background: #e6e5e4;
  border: 2px solid #b6b5b4;
}

.grid-item--width2 { width: 120px; }
.grid-item--height2 { height: 120px; }
</style>