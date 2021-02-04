<template>
  <div class="add_btn">
    <b-button @click="toCreate" pill variant="info" class="mb-3 font-weight-bold">+</b-button>
  </div>
</template>

<script>
export default {
  name: "Button",
  methods: {
    toCreate: function () {
      this.$router.push({ name: 'ArticleCreate'})
    },
  }
}
</script>

<style>
  .add_btn {
    position: sticky;
    bottom: 0;
    right: 0;
    text-align: right;
  }
</style>