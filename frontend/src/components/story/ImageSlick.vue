<template>
  <!-- arrow 버튼이 안 생김 !!! -->
  <!-- 가운데 정렬이 안됨!!!! -->

  <div class="my-5">
    <VueSlickCarousel v-bind="slick_settings">
      <div><img src="https://picsum.photos/300/300" /></div>
      <div><img src="https://picsum.photos/255/300" /></div>
      <div><img src="https://picsum.photos/312/300" /></div>
    </VueSlickCarousel>
  </div>
</template>

<script>
// 참고: https://github.com/gs-shop/vue-slick-carousel/blob/master/docs/API.md#props
import VueSlickCarousel from 'vue-slick-carousel'
import 'vue-slick-carousel/dist/vue-slick-carousel.css'
import 'vue-slick-carousel/dist/vue-slick-carousel-theme.css'

export default {
  name: 'ImageSlick',
  components: {
    VueSlickCarousel,
  },
  data: function () {
    return {
      slick_settings: {
        "lazyLoad": "ondemand",
        "dots": true,
        "fade": true,
        "infinite": true,
        "speed": 500,
        "slidesToShow": 1,
        "slidesToScroll": 1
      }
    }
  }
}
</script>

<style>

</style>