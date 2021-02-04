<template>
  <div @click="toBadge">
    <b-img thumbnail rounded="circle" fluid src="https://picsum.photos/200/200/?image=54" alt="Image 1"></b-img>
  </div>
</template>

<script>
export default {
 name: 'Profile2',
 methods: {
   toBadge: function () {
     this.$router.push({ name: 'Badge'})
   }
 }
}
</script>

<style>

</style>