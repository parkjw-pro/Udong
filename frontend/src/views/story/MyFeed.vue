<template>
  <div id="box">
    <!-- 1. 프로필 -->
     <b-row class="mb-5">
      <b-col cols="3">
        <Profile2 />
      </b-col>
      <b-col cols="1">
        <b-icon class="h2" icon="plus"></b-icon>
      </b-col>
      <b-col cols="2"></b-col>
      <b-col cols="5">
        <b-row cols="1"><h2>용용이</h2></b-row>
      </b-col>
    </b-row>
    <!-- 2. 탭 -->
    <b-tabs
      active-nav-item-class="font-weight-bold text-uppercase text-dark"
      content-class="mt-3"
      justified
    >
      <b-tab title="글" active>
        <PostBlockMy />
      </b-tab>
      <b-tab title="리뷰">
        <PostBlockMy />
      </b-tab>
      <b-tab title="태그">
        <TagBox />
      </b-tab>
      <b-tab title="그룹">
        <GroupBox />
      </b-tab>
    </b-tabs>

    <br>
    <br>
    <br>
  </div>
</template>

<script>
import GroupBox from '@/components/story/GroupBox'
import PostBlockMy from '@/components/story/PostBlockMy'
import Profile2 from '@/components/app/Profile2'
import TagBox from '@/components/story/TagBox'

export default {
  name: 'MyFeed',
  components: {
    GroupBox,
    PostBlockMy,
    Profile2,
    TagBox,
  },
  
}
</script>

<style>

</style>