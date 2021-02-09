<template>
  <div class="mx-5 px-5" id="box">
    <!-- 1. 내 그룹 -->
    <div id="my_group" class="my-5">
        <b-row align-h="justify">
          <b-col align-self="center"><h4 id="group_list_category">내 그룹</h4></b-col>
          <b-col align-self="center" offset-md="4"><b-button style="background-color: #695549;" @click="groupMake()">그룹 만들기</b-button></b-col>
        </b-row>
        <hr>
        <b-row>
          <b-card-group   >
            <div id="group_card" v-for="(title, idx) in club" :key="idx"> 
                <GroupCard :group="title"/>
            </div>
          </b-card-group>
        </b-row>
    </div>
    <!-- 2. 공개 그룹 -->
    <div id="public_group" class="my-5 py-5" style="text-align: left;">
      <h4 class="mx-5" id="group_list_category">공개 그룹</h4>
      <hr>
      <GroupCard />
    </div>
    <!-- 3. 비공개 그룹 -->
    <div id="private_group" class="my-5 py-5" style="text-align: left;">
      <h4 class="mx-5" id="group_list_category">비공개 그룹</h4>
      <hr>
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
  data() {
    return {
      club : Object
    }
  },
  methods: {
    groupMake(){
      this.$router.push({name:"GroupCreate"});
    }
  },
  created(){
    axios.get(`${SERVER_URL}/club/user/${JSON.parse(localStorage.getItem('Login-token'))['user-id']}/member`)
    .then((res)=>{
     
     this.club = res.data
     console.log("조회성공")
     console.log(this.club)
     }) 
    .catch(()=>{
       console.log("조회 실패")
    });
  }
}
</script>

<style>
#group_card {
  width: 20rem;
  height: 20rem;
  max-width: 20rem;
  max-height: 20rem;
},
#group_list_category {
  text-align: left;
  font-weight: bold;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>