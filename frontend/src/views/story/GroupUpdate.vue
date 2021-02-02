<template>
  <b-form id="box" @submit="onSubmit">
    <!-- 1. 프로필 이미지 -->
    <b-row class="pb-5"  align-h="between">
      <!-- 프로필 이미지 -->
      <b-col class="ml-3">
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
            disabled
          />
        </b-row>
        <!-- bootstrap > Form File 참고하면 나와있음 -->
        <b-row align-h="center">
          <b-form-file
            v-model="club.fileId"
            :state="Boolean(file1)"
            placeholder="첨부파일 없음"
            drop-placeholder="Drop file here..."
            style="width: 70%;"
          ></b-form-file>
          <!-- <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div> -->
        </b-row>
      </b-col>
    </b-row>
    <!-- 동네 이름 -->
    <b-row class="ml-3 mb-5">
      <h3 style="text-align: left;"><span class="font-weight-bold">{{ dong }}</span> 그룹 만들기</h3>
    </b-row>

    <!-- 그룹명 -->
    
    <div class="mb-5" style="text-align: left;">
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
            v-model="club.clubContent"
            placeholder="그룹을 소개해보세요!"
            rows="8"
          ></b-form-textarea>
      </b-col>
    </div>
    <!-- 하단 버튼 -->
    <div id="group_profile_buttons">
      <b-button class="mx-3" variant="danger" @click="deleteGroup">그룹삭제</b-button>
      <b-button class="mx-3" variant="success" @click="toMemberList">회원관리</b-button>
      <b-button class="mx-3" variant="info" @click="updateGroup">변경하기</b-button>
    </div>
    <br>
    <br>
  </b-form>
</template>

<script>
import Profile from '@/components/app/Profile'
import axios from 'axios'

export default {
  name: 'GroupUpdate',
  components: {
    Profile
  },
  // props로 가져오기
  data: function () {
    return {
      user: '',
      dong: '역삼동',
      club: {
        clubName: "다이어트",
        clubContent: "새로새로이",
        isOpen: true,
        imgURL: "",
      }
    }
  },
  methods: {
    deleteGroup: function () {
      const config = this.getToken()
      axios.delete("/club", this.club, config)
      .then(() => {
        this.$router.push({name: 'GroupPage', query: {club: this.club}})
      })
      .catch((err) => {
        console.log(err)
      })
    },
    getToken: function () {
      const token = localStorage.getItem('jwt')
      const config = {
        headers: {
          Authorization: `JWT ${token}`
        }
      }
      return config
    },
    onSubmit(evt) {
      evt.preventDefault()
      alert(JSON.stringify(this.article))
    },
    updateGroup: function () {
      const config = this.getToken()
      axios.put("/club", this.club, config)
      .then(() => {
        this.$router.push({name: 'GroupPage', query: {club: this.club}})
      })
      .catch((err) => {
        console.log(err)
      })
    },
    toMemberList: function () {
      this.$router.push({name: 'GroupMemberList', query: {club: this.club}})
    },
  },
  created: async function() {
      // 그룹 정보 가져오기!!
      // await this.getGroupInfo
  }
}
</script>

<style>

</style>