<template>
  <div class="mx-5">
      <!-- 검색창 -->
    <b-nav-form>
      <b-form-input class="mr-sm-2 ml-auto" placeholder="Search"></b-form-input>  <!-- 가운데 정렬이 잘 안된다!!!! ㅠㅠㅠ -->
      <b-button variant="outline-info" class="my-2 my-sm-0" type="submit">Search</b-button>
    </b-nav-form>

    <b-row class="my-5" align-h="between">
      <b-col cols="4"><h2 class="font-weight-bold">asdasd</h2></b-col>
      <b-col cols="2">
        <!-- v-if="user.group.status=Admin" -->
        <!-- <b-button variant="info">회원관리</b-button> -->
        <!-- v-else -->
        <b-button variant="info">fadfa</b-button>
      </b-col>
      
    </b-row>
    
    <div class="mb-5"> <!-- for문 넣기 -->
      <PostBlock />
    </div>
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <br class="my-5">
    <h3>게시물이 없어요...</h3>
    <EndBlock />
    <Button />
  </div>
</template>

<script>
import Button from '@/components/story/Button'
import EndBlock from '@/components/story/EndBlock'
import PostBlock from '@/components/story/PostBlock'
//import axios from 'axios'

export default {
  name: "GroupPage",
 
  components: {
    Button,
    EndBlock,
    PostBlock,
  },
  data() {
    return {
      
     // groups : Object,
    }
  },
  methods :{
    
  },
  created() {
   // this.groups = this.$route.query.groups
  //  console(this.groups);

  },
}
</script>

<style>

</style>