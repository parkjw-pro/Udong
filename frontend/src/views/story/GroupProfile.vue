<template>
  <b-form id="box">
    <!-- 1. 프로필 이미지 -->
    <b-row class="pb-5"  align-h="between">
      <!-- 프로필 이미지 -->
      <b-col class="ml-3">
        <!-- props:  :fileId="Club.fileId" -->
        <Profile />
      </b-col>

      <!-- 공개/비공개 버튼 -->
      <b-col align-self="center">
        <!-- <b-row class="mb-2">
          <div>공개/비공개</div>
        </b-row> -->
        <b-row class="mb-3" align-h="center">
          <toggle-button
            :value="club.isOpen"
            :width="80"
            :height="35"
            :labels="{checked: '공개', unchecked: '비공개'}"
            :color="{
              checked: '#695549', unchecked: '#a0a0a0'
            }"
            style="font-size: small; text-align: center;"
            disabled
          />
        </b-row>
      </b-col>
    </b-row>
    <!-- 동네 이름 -->
    <b-row class="ml-3 mb-5">
      <h3 style="text-align: left;"><span class="large">{{ dong }}</span> 그룹</h3>
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
            :placeholder="club.clubName"
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
            :placeholder="club.clubContent"
            rows="8"
            disabled            
          ></b-form-textarea>
      </b-col>
    </div>
    <!-- 하단 버튼 -->
    <div id="group_profile_buttons">
      <!-- <b-button class="mx-3" variant="danger">그룹삭제</b-button> -->
      <!-- 1. v-if 가입되어 있으면 -->
      <div v-if="isJoin">
        <b-button class="mx-3" variant="danger" @click="leaveGroup">그룹탈퇴</b-button>
        <b-button class="mx-3" variant="info" @click="toMemberList">회원조회</b-button>
      </div>
      <!-- 2. v-else (가입이 안 되어 있으면) -->
      <b-button v-else class="mx-3" type="submit" variant="info" @click="joinGroup">가입하기</b-button>
    </div>
    <br>
    <br>
  </b-form>
</template>

<script>
import Profile from '@/components/app/Profile'
import axios from 'axios'

export default {
  name: 'GroupProfile',
  components: {
    Profile,
  },
    // clubId를 props 받아온다.
  props: {
  },
  data: function () {
    return {
      user: '',
      dong: '역삼동',
      club: {
        clubName: "다이어트",
        clubContent: "하이하이",
        isOpen: true,
        fileId: "",
      },
      isJoined: false,
    }
  },
  computed: {
    isJoin: function () {
      return this.isJoined
    }
  },
  methods: {
    toMemberList: function () {
      // props 받아온 clubId를 뒤에 넣어준다.
      // this.$router.push({name: 'GroupMemberList', clubId })
    },
    joinGroup: function () {
      const config = this.getToken()
      // 해당 club 정보도 넘겨줘야하는거 아닌지??
      axios.post("/club/member", this.user, config)
      .then(() => {
        this.$router.push({name: 'GroupPage', query: {club: this.club}})
      })
      .catch((err) => {
        console.log(err)
      })
    },
    leaveGroup: function () {
      const config = this.getToken()
      axios.delete("/club/member", this.user, config)
      .then(() => {
        this.$router.push({name: 'NewsFeed'})
      })
      .catch((err) => {
        console.log(err)
      })
    }
  }
}
</script>

<style>
</style>