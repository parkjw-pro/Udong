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
        <b-card-group   >
          <div v-for="(title, idx) in myClub" :key="idx"> 
              <GroupCard :group = "title"/>
          </div>
   
        </b-card-group>
        <!-- <GroupCard /> -->
    </div>
    <!-- 2. 공개 그룹 -->
    <div id="public_group" class="my-5">
      <h4 id="group_list_category">공개 그룹</h4>
      <div v-for="(title, idx) in openClub" :key="idx"> 
      <GroupCard :group = "title"/>
       </div>
    </div>
    <!-- 3. 비공개 그룹 -->
    <div id="private_group" class="my-5">
      <h4 id="group_list_category">비공개 그룹</h4>
        <div v-for="(title, idx) in closeClub" :key="idx">   
      <GroupCard :group = "title"/>
        </div>
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
      myClub : Object,
      openClub : [],
      closeClub : [],
    }
  },
  methods: {
    groupMake(){
      this.$router.push({name:"GroupCreate"});
    }
  },
  created(){
    //내그룹에 가입한 정보들 
    axios.get(`${SERVER_URL}/club/user/${JSON.parse(localStorage.getItem('Login-token'))['user-id']}/member`)
    .then((res)=>{
     
     this.myClub = res.data
     console.log("내그룹 조회성공")
     console.log(this.myClub)
     }) 
    .catch(()=>{
       console.log("내그룹 조회 실패")
    });

    // 해당 동코드에 생성된 전체 그룹
    axios.get(`${SERVER_URL}/club/clubs/${JSON.parse(localStorage.getItem('Login-token'))['user_address']}`)
    .then((res)=>{
     console.log("전체그룹 조회성공")
     console.log(res.data)

      for(var i in res.data){
        console.log(res.data[i].isOpen)
        if(res.data[i].isOpen== "1"){
          console.log("1111")
          this.openClub.push(res.data[i])
          
        }else{
          console.log("22222")
           this.closeClub.push(res.data[i])
        }
      }
      console.log("데이터 확인")
      console.log(this.openClub)
      console.log(this.closeClub)
    }) 
    .catch(()=>{
       console.log("전체 그룹 조회 실패")
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