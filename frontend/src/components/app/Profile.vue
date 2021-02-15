<template>
  <div>
    <b-img id="img" thumbnail rounded="circle" fluid :src="this.url+'/club/download/'+this.fileId" alt="Image 1"></b-img>
  </div>
</template>

<script>

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
 name: 'Profile',
 props:{
   fileId : String,
   filePreview : Object,
 },
 data() {
   return {
     url: SERVER_URL
   }
 },
computed:{
  chageImage(){

    return this.filePreview;
  }
},
watch:{
  chageImage(){
    document.getElementById("img").src = this.filePreview
  }
},
mounted() {
  console.log(this.url+'/club/download/'+this.fileId)
}
}
</script>

<style>

</style>