<template>
  <div id="box">
    <h2 class="mb-5">어디를 리뷰할까요?</h2>
    <SearchStore />
  </div>
</template>

<script>
import SearchStore from '@/components/review/SearchStore'

export default {
  name: 'FindStore',
  components: {
    SearchStore,
  },
}
</script>

<style>

</style>