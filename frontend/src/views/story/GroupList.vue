<template>
  <div id="box" class="mx-5">
    <!-- 1. 내 그룹 -->
    <div id="my_group" class="my-5">
        <b-row>
          <b-col md="4"><h4 id="group_list_category">내 그룹</h4></b-col>
          <b-col md="4" offset-md="4"><b-button pill variant="primary">+</b-button>(게시글 작성)</b-col>
        </b-row>
      <!-- <div>
        <h4 id="group_list_category">내 그룹</h4>
        <b-button pill variant="primary">+</b-button>
      </div> -->
        <b-card-group deck>
          <GroupCard />
          <GroupCard />
          <GroupCard />
        </b-card-group>
        <!-- <GroupCard /> -->
    </div>
    <!-- 2. 공개 그룹 -->
    <div id="public_group" class="my-5">
      <h4 id="group_list_category">공개 그룹</h4>
      <GroupCard />
    </div>
    <!-- 3. 비공개 그룹 -->
    <div id="private_group" class="my-5">
      <h4 id="group_list_category">비공개 그룹</h4>
      <GroupCard />
    </div>
  </div>
</template>

<script>
import GroupCard from '@/components/story/GroupCard'

export default {
  name: "GroupList",
  components: {
    GroupCard,
  }
}
</script>

<style>
#group_list_category {
  text-align: left;
  font-weight: bold;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>