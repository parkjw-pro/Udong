<template>
  <b-form id="box" @submit="onSubmit">
    <!-- 1. 프로필 이미지 -->
    <b-row class="pb-5" align-h="between">
      <!-- 프로필 이미지 -->
      <b-col class="ml-3">
        <div>
          <div class="profile_box" style="background: #BDBDBD;">
            <img class="profile_image" :src="previewImageData" />
          </div>
        </div>
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
            :labels="{ checked: '공개', unchecked: '비공개' }"
            :color="{
              checked: '#695549',
              unchecked: '#a0a0a0',
            }"
          />
        </b-row>
        <!-- bootstrap > Form File 참고하면 나와있음 -->
        <b-row align-h="center">
          <b-form-file
            v-model="fileId"
            :state="Boolean(file1)"
            placeholder="첨부파일 없음"
            drop-placeholder="Drop file here..."
            required
            accept=".jpg, .png, .gif"
             @change="previewImage"
            style="width: 70%;"

          ></b-form-file>
          <!-- <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div> -->
        </b-row>
      </b-col>
    </b-row>
    <!-- 동네 이름 -->
    <b-row class="ml-3 mb-5">
      <h3 style="text-align: left;">
        <span class="font-weight-bold">{{ dong }}</span> 그룹 만들기
      </h3>
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
              v-model="club.clubName"
              placeholder="그룹명"
              required
            ></b-form-input>
          </b-col>
          <b-col>
            <b-button @click="verifyName" style="background-color: #695549"
              >중복확인</b-button
            >
          </b-col>
        </b-row>
      </b-col>
      <div
        v-if="verification"
        class="mt-3 small"
        style="text-align: center; color: green;"
      >
        그룹명을 사용할 수 있습니다.
      </div>
      <div
        v-if="verification == false"
        class="mt-3 small"
        style="text-align: center; color: red;"
      >
        그룹명 중복확인을 해주세요.
      </div>
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
    <div>
      <!-- <b-button class="mx-3" variant="danger">삭제</b-button> -->
      <!-- <b-button class="mx-3" variant="success">회원조회</b-button> -->
      <b-button class="mx-3" variant="info" @click="createGroup"
        >생성하기</b-button
      >
    </div>
    <br />
    <br />
  </b-form>
</template>

<script>
//import Profile from '@/components/app/Profile'
import axios from "axios";

const SERVER_URL = "http://localhost:8000";

export default {
  name: "GroupCreate",
  components: {
    //   Profile
  },
  data: function() {
    return {
      user: "",
      // props or 함수로 받아와야 한다!
      dong: "역삼동",
      club: {
        clubName: " ",
        clubContent: " ",
        isOpen: "1",
       
      },
      fileId: null,
      isVerified: null,
      previewImageData: null,
    };
  },
  computed: {
    verification: function() {
      return this.isVerified;
    },
  },
  methods: {
    previewImage(event) {
      var input = event.target;
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = (e) => {
          this.previewImageData = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      } else {
        this.previewImageData = null;
      }
    },

    // 함수: 로그인 정보를 가져오는 함수
    getToken: function() {
      const token = localStorage.getItem("jwt");
      const config = {
        headers: {
          Authorization: `JWT ${token}`,
        },
      };
      return config;
    },
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.article));
    },
    createGroup: function() {
      console.log(this.previewImageData[0]);
      console.log(this.fileId);
      console.log(this.fileId.name);
      var formData = new FormData();
      formData.append('clubName', this.club.clubName)
      formData.append('clubContent', this.club.clubContent)
      formData.append('isOpen', this.club.isOpen)
      formData.append('file', this.fileId)
      // formData.append('club', this.club)
      console.log(formData);
     // if (this.verification) {
        axios.post(`${SERVER_URL}/club`, formData, 
        { headers: { "Content-Type": `application/json; charset=UTF-8`}
        }
        
      ).then(() => {
          this.$router.push({name: 'GroupPage', query: {club: this.club}})
         })
         .catch((err) => {
           console.log(err)
         })
      // }
     //  else {
     //    this.isVerified = false
     //  }
    },
    verifyName: function() {
      // 요청한다
      axios
        .get(`${SERVER_URL}/club/${this.club.clubName}/${this.club.clubName}`)
        .then(() => {
          this.isVerified = true;
        })
        .catch((err) => {
          console.log(err);
          this.isVerified = false;
        });
    },
  },
};
</script>

<style>
/* 프로필을 담는 박스 */
.profile_box {
  width: 150px;
  height: 150px;
  border-radius: 70%;
  overflow: hidden;
}

/* 프로필 이미지 */
.profile_image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
