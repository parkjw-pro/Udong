<template>
  <div class="add_btn">
    <b-button @click="toCreate" class="mb-3" style="background-color: #695549;">게시글 작성하기</b-button>
  </div>
</template>

<script>
export default {
  name: "Button",

  methods: {
    toCreate: function () {
      this.$router.push({ name: 'ArticleCreate' ,params:{address:  JSON.parse(localStorage.getItem('Login-token'))['user_address'] ,groupId : 0, groupcheck : 0}})
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