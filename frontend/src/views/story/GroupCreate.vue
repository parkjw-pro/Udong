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
            :value="group.isPublic"
            :width="80"
            :height="35"
            :labels="{checked: '공개', unchecked: '비공개'}"
            :color="{
              checked: '#695549', unchecked: '#a0a0a0'
            }"
            
          />
        </b-row>
        <!-- bootstrap > Form File 참고하면 나와있음 -->
        <b-row align-h="center">
          <b-form-file
            v-model="file1"
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
      <h3 style="text-align: left;"><span class="font-weight-bold">역삼동</span> 그룹 만들기</h3>
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
            v-model="group.name"
            placeholder="그룹명"
            required
          ></b-form-input>
          </b-col>
          <b-col>
            <b-button style="background-color: #695549">중복확인</b-button>
          </b-col>
        </b-row>
      </b-col>
    </div>
    <!-- 그룹소개 -->
    <div class="mb-5 pb-5" style="text-align: left;">
      <b-col>
        <h4 class="font-weight-bold mb-4">소개글</h4>
          <b-form-textarea
            v-model="group.intro"
            placeholder="그룹을 소개해보세요!"
            rows="8"
          ></b-form-textarea>
      </b-col>
    </div>
    <!-- 하단 버튼 -->
    <div>
      <!-- <b-button class="mx-3" variant="danger">삭제</b-button> -->
      <!-- <b-button class="mx-3" variant="success">회원조회</b-button> -->
      <b-button class="mx-3" type="submit" variant="info" @click="createGroup">생성하기</b-button>
    </div>
  </b-form>
</template>

<script>
import axios from 'axios'
import Profile from '@/components/app/Profile'

export default {
  name: 'GroupCreate',
  components: {
    Profile
  },
  data: function () {
    return {
      user: {
        userId: '',
      },
      group: {
        "name": "",
        "intro": "",
        "isPublic": true,
        "imgURL": "",
      }
    }
  },
  methods: {
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
    createGroup: function () {
      // const config = this.getToken()
      axios.post("/club")
      .then(() => {
        const group = this.group
        this.$router.push({name: 'GroupPage', query: {group: group}})
      })
      .catch((err) => {
        console.log(err)
      })
    },
  }
}
</script>

<style>
</style>