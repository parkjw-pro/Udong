<template>
  <div class="mx-5 mt-5">
    <img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%" @click="trigger">
  </div>
</template>

<script>
export default {
  name: 'EndBlock',
  methods: {
    trigger() {
      this.$emit('more');
    }
  },
}
</script>

<style>

</style>