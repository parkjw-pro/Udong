<template>
  <div id="box">
    <h2 class="mb-5">어디를 리뷰할까요?</h2>
    <SearchReview />
  </div>
</template>

<script>
import SearchReview from '@/components/review/SearchReview'

export default {
  name: 'FindStore',
  components: {
    SearchReview,
  },
}
</script>

<style>

</style>