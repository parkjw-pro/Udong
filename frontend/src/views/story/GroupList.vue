<template>
  <div id="group_list_space">
    <!-- 1. 내 그룹 -->
    <div id="my_group" class="my-5">
      <b-row align-h="justify">
        <b-col align-self="center"><h4 id="group_list_category">내 그룹</h4></b-col>
        <b-col align-self="center" offset-md="4"><b-button style="background-color: #695549;" @click="groupMake()">그룹 만들기</b-button></b-col>
      </b-row>
      <hr>
      <b-row>
        <b-card-group>
          <div v-for="(title, idx) in myClub" :key="idx"> 
            <GroupCard :group ="title" />
          </div>
        </b-card-group>
      </b-row>
    </div>
    
    <br>
    <!-- 2. 공개 그룹 -->
    <div id="public_group" style="text-align: left;">
      <b-row class="ml-5 pl-5"><h4 id="group_list_category">공개 그룹</h4></b-row>
      <hr>
      <b-row>
        <b-card-group>
          <div v-for="(title, idx) in openClub" :key="idx"> 
            <GroupCard :group = "title"/>
          </div>
        </b-card-group>
      </b-row>
    </div>

    <br>
    <br>
    <br>
    <!-- 3. 비공개 그룹 -->
    <div id="private_group" style="text-align: left;">
      <b-row class="ml-5 pl-5"><h4 id="group_list_category">비공개 그룹</h4></b-row>
      <hr>
      <b-row>
        <b-card-group>
          <div v-for="(title, idx) in closeClub" :key="idx">   
            <GroupCard :group = "title"/>
          </div>
        </b-card-group>
      </b-row>
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
    
     }) 
    .catch(()=>{
       console.log("내그룹 조회 실패")
    });

    // 해당 동코드에 생성된 전체 그룹
    axios.get(`${SERVER_URL}/club/clubs/${JSON.parse(localStorage.getItem('Login-token'))['user_address']}`)
    .then((res)=>{
     console.log("전체그룹 (공개,비공개) 조회성공")
      var group = res.data
      for(var i in group){
        
        if(group[i].isOpen== "1"){
          this.openClub.push(group[i])
          
        }else{
      
           this.closeClub.push(group[i])
        }
      }
     
    }) 
    .catch(()=>{
       console.log("전체 그룹  (공개,비공개)  조회 실패")
    });
  }
}
</script>

<style>
#group_list_space {
  display: block;
  width: 60%;
  position: absolute;
  left: 20%;
  margin-top: 5%;
  padding-bottom: 7%;
}
#group_card {
  width: 20rem;
  height: 20rem;
  max-width: 20rem;
  max-height: 20rem;
}
#group_list_category {
  text-align: left;
  font-weight: bold;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>