<template>
  <div class="mx-5 mb-3">
    <img alt="Vue logo" src="@/assets/udonge.png" style="width: 8%" @click="trigger">
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