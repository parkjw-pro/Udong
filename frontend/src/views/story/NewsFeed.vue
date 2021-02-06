<template>
  <div id="box" class="mx-5">
    <!-- 검색창 -->
    <b-nav-form>
      <b-form-input class="mr-sm-2 ml-auto" placeholder="Search"></b-form-input>  <!-- 가운데 정렬이 잘 안된다!!!! ㅠㅠㅠ -->
      <b-button variant="outline-success" class="my-2 my-sm-0" type="submit">Search</b-button>
    </b-nav-form>

    <!-- 그룹 고르는 공간 -->
    <b-row class="my-5 ">
      <b-col md="7">
        <b-button-group>
          <b-button variant="success">그룹1</b-button>
          <b-button variant="info">그룹2</b-button>
          <b-button variant="warning">그룹2</b-button>
        </b-button-group>
      </b-col>
      <b-col md="5">
        <b-button style="background-color: #695549;" @click="toList" end>+</b-button> <!-- variant="outline-secondary" 속성 -->
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
    <EndBlock />
    <h3>게시물이 없어요...</h3>
    <Button />
  </div>
</template>

<script>
import Button from '@/components/story/Button'
import EndBlock from '@/components/story/EndBlock'
import PostBlock from '@/components/story/PostBlock'

export default {
  name: 'NewsFeed',
  components: {
    Button,
    EndBlock,
    PostBlock,
  },
  data: function () {
    return {
      user: {}
    }
  },
  methods: {
    toList: function () {
      this.$router.push({ name: 'GroupList'})
    }
  },
  created () {
    this.user = this.$store.state.user
    console.log('hello')
    console.log(this.user)
    console.log('hello')
  }
}
</script>

<style>

</style>