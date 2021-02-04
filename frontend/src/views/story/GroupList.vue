<template>
  <div id='box' class="mx-5">
    <!-- 1. 내 그룹 -->
    <div id="my_group" class="my-5">
        <b-row>
          <b-col cols="4"><h4 id="group_list_category">내 그룹</h4></b-col>
          <b-col cols="4" offset-md="4"><b-button variant="primary">그룹 생성</b-button></b-col>
        </b-row>
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
import axios from 'axios'
import GroupCard from '@/components/story/GroupCard'

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: "GroupList",
  components: {
    GroupCard,
  },
  data: function () {
    return {
      user: {

      },
      myGroups: [
        
        ],
      publicGroups: [

      ],
      privateGroups: [

      ],
    }
  },
  methods: {
    getUser: function () {
      // user 정보 가져오기
    },
    getGroup: function () {
      axios.get(`${SERVER_URL}/clubs`)
        .then( () => {
          // for문 돌려서
          // i) 해당 지역 일치
          // ii) 가입하지 않은 그룹
          // 그 중 public 그룹은 this.publicGroup에 저장
          // 그 중 private 그룹은 this.privateGroup에 저장
          
        })
    },
    getMyGroup: function () {
      axios.get(`${SERVER_URL}/${this.user.userId}/member`)
        .then( (res) => {
          this.myGroups = res
        })
    },
  },
  created: async function () {
    await this.getUser()
    await this.getMyGroup()
    await this.getGroup()
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