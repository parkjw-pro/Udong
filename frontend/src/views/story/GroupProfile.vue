<template>
  <b-form id="box" @submit="onSubmit">
    <!-- 1. 프로필 이미지 -->
    <b-row class="pb-5" align-h="between">
      <!-- 프로필 이미지 -->
      <b-col class="ml-3">
        <!-- props:  :fileId="Club.fileId" -->
        <Profile :fileId="this.club.dto.fileId"/>
      </b-col>

      <!-- 공개/비공개 버튼 -->
      <b-col align-self="center">
        <!-- <b-row class="mb-2">
          <div>공개/비공개</div>
        </b-row> -->
        <b-row class="mb-3" align-h="center">
          <toggle-button
             :value="isOpen"
            :width="80"
            :height="35"
            :labels="{ checked: '공개', unchecked: '비공개' }"
            :color="{
              checked: '#695549',
              unchecked: '#a0a0a0',
            }"
            style="font-size: small; text-align: center;"
            disabled
          />
        </b-row>
      </b-col>
    </b-row>
    <!-- 동네 이름 -->
    <b-row class="ml-3 mb-5">
      <h3 style="text-align: left;">
        <span class="large">{{dong}}</span> 그룹
      </h3>
    </b-row>

    <!-- 그룹명 -->

    <div id="group_profile_name" class="mb-5" style="text-align: left;">
      <b-col>
        <b-row class="mt-2">
          <b-col sm="3">
            <h4 class="font-weight-bold">이름</h4>
          </b-col>
          <b-col sm="7">
            <b-form-input
              class="font-weight-bold"
              :placeholder="this.club.dto.clubName"
              disabled
            ></b-form-input>
          </b-col>
        </b-row>
      </b-col>
    </div>
    <!-- 그룹소개 -->
    <div class="mb-5 pb-5" style="text-align: left;">
      <b-col>
        <h4 class="font-weight-bold mb-4">소개글</h4>
        <b-form-textarea
          :placeholder="club.dto.clubContent"
          rows="8"
          disabled
        ></b-form-textarea>
      </b-col>
    </div>
    <!-- 하단 버튼 -->
    <div id="group_profile_buttons">
      <!-- <b-button class="mx-3" variant="danger">그룹삭제</b-button> -->
      <!-- 그룹 비회원 이면 -->

      <b-button
        v-if="isJoin == 0"
        class="mx-3"
        type="submit"
        variant="info"
        @click="joinGroup"
        >가입하기</b-button
      >

      <!-- 그룹 회원이면  -->
      <b-button
        v-if="isJoin == 1"
        class="mx-3"
        variant="danger"
        @click="leaveGroup"
        >그룹탈퇴</b-button
      >

      <!-- 그룹장이면 -->
      <b-button
        v-if="isJoin == 2"
        class="mx-3"
        variant="info"
        @click="updateGroup"
        >그룹관리</b-button
      >
      <b-button
        v-if="isJoin == 2"
        class="mx-3"
        variant="info"
        @click="toMemberList"
        >회원관리</b-button
      >
      <b-button
        v-if="isJoin == 2"
        class="mx-3"
        variant="info"
        @click="leaveGroup"
        >그룹탈퇴</b-button
      >
    
    </div>
    <br />
    <br />
  </b-form>
</template>

<script>
import Profile from "@/components/app/Profile";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: "GroupProfile",
  components: {
    Profile,
  },
  // clubId를 props 받아온다.
  props: {},
  data: function() {
    return {
      
      dong: JSON.parse(localStorage.getItem('Login-token'))["user_address_name"],
      club:  Object,
      isOpen: false,
      isJoined: 0,
    };
  },
  computed: {
    isJoin: function() {
      return this.isJoined;
    },
  },
  created() {
    
    this.isJoined = this.$route.params.groupcheck;
    // 그룹정보 가져오기
    axios
        .get(`${SERVER_URL}/club/${this.$route.params.groupId}`)
        .then((res) => {
          this.club = res.data;
          // 비공개, 공개여부
         
          if(this.club.dto.isOpen == 1){
            this.isOpen = true;
          }else{
            this.isOpen = false; 
          }
        })
        .catch((err) => {
          console.log(err);
        });
      
    
  },
  methods: {
    
    // 가입하기
    onSubmit(evt) {
      evt.preventDefault()
       this.$router.push({ name: 'GroupList', params: {address: this.club.dto.areaCode} })
    },
    // 가입하기
    joinGroup: function() {
      axios
        .post(`${SERVER_URL}/club/member`,{
          clubId : this.club.dto.clubId,
          userId : JSON.parse(localStorage.getItem('Login-token'))["user-id"],
          type : 1,
          contents : "ㅁㄴㅇ",
        } )
        .then((res) => {
          console.log(res)
         this.$router.push({ name: 'GroupList', params: {address: this.club.dto.areaCode} })
        })
        .catch((err) => {
          console.log(err);
         this.$router.push({ name: 'GroupList', params: {address: this.club.dto.areaCode} })
        });
    
    },
    //그룹 탈퇴 //여기부터 
    leaveGroup: function() {
        axios
        .delete(`${SERVER_URL}/club/member`,{
          clubId : this.club.dto.clubId,
          userId : JSON.parse(localStorage.getItem('Login-token'))["user-id"],
          type : 1,
          contents : "ㅁㄴㅇ",
        } )
        .then((res) => {
          console.log(res)
         this.$router.push({ name: 'GroupList', params: {address: this.club.dto.areaCode} })
        })
        .catch((err) => {
          console.log(err);
         this.$router.push({ name: 'GroupList', params: {address: this.club.dto.areaCode} })
        });
    },
    // 회원관리
    toMemberList: function() {
      this.$router.push({ name: 'GroupList', params: {address: this.club.dto.areaCode} })
    },
    // 그룹수정
    updateGroup(){
      this.$router.push({ name: 'GroupUpdate', params: {address: this.club.dto.areaCode , groupId : this.club.dto.clubId} })
    },
    // 그룹 삭제
    deleteGroup(){

    },
    

  },
};
</script>

<style></style>
