<template>
  <div>
    <br>
    <br>
    <h3>방문할 동네를 선택해주세요!</h3>
    <Location />
  </div>
</template>

<script>
import Location from '@/components/app/Location'

export default {
  name: 'FindLocation',
  components: {
    Location,
  }
}
</script>

<style>

</style>