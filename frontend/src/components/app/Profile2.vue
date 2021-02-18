<template>
  <div @click="toBadge">
    <b-img thumbnail rounded="circle" fluid :src="require(`@/assets/app/badge/${this.badge}.png`)" alt="Image 1"></b-img>
  </div>
</template>

<script>
export default {
 name: 'Profile2',
 data: function () {
   return {
      badge: JSON.parse(localStorage.getItem('Login-token')).user_badge,
   }
 },
 methods: {
   toBadge: function () {
     this.$router.push({ name: 'Badge'})
   }
 }
}
</script>

<style>

</style>