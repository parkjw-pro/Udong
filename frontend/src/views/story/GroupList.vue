<template>
  <div id="box" class="mx-5">
    <!-- 1. 내 그룹 -->
    <div id="my_group" class="my-5">
        <b-row>
          <b-col md="4"><h4 id="group_list_category">내 그룹</h4></b-col>
          <b-col md="4" offset-md="4"><b-button pill variant="primary" @click="groupMake()">+</b-button>(그룹 만들기)</b-col>
        </b-row>
      <!-- <div>
        <h4 id="group_list_category">내 그룹</h4>
        <b-button pill variant="primary">+</b-button>
      </div> -->
        <b-card-group deck>
          <div v-for="(title, idx) in club" :key="idx"> 
              <GroupCard :group = "title"/>
          </div>
   
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



const SERVER_URL = "http://localhost:8000";
 
import GroupCard from '@/components/story/GroupCard'
import axios from 'axios'

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
#group_list_category {
  text-align: left;
  font-weight: bold;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>