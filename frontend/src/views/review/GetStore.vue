<template>
  <div id="box">
    <h2 class="mb-5">우리 동네 장소를 검색해보세요!</h2>
    <SearchStore />
  </div>
</template>

<script>
import SearchStore from '@/components/review/SearchStore'

export default {
  name: 'GetStore',
  components: {
    SearchStore,
  },
}
</script>

<style>

</style>