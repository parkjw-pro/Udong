<template>
  <b-form id="box" @submit="onSubmit">
    <!-- 1. 프로필 이미지 -->
    <b-row class="pb-5" align-h="between">
      <!-- 프로필 이미지 -->
      <b-col class="ml-3">
        <Profile
          :fileId="this.club.dto.fileId"
          :filePreview="this.previewImageData"
        />
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
            disabled
          />
        </b-row>
        <!-- bootstrap > Form File 참고하면 나와있음 -->
        <b-row align-h="center">
          <b-form-file
            v-model="file"
            :state="Boolean(file1)"
            placeholder="첨부파일 없음"
            drop-placeholder="Drop file here..."
            style="width: 70%;"
            required
            accept=".jpg, .png, .gif"
            @change="previewImage"
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
              :placeholder="club.dto.clubName"
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
          v-model="club.dto.clubContent"
          placeholder="그룹을 소개해보세요!"
          rows="8"
        ></b-form-textarea>
      </b-col>
    </div>
    <!-- 하단 버튼 -->
    <div id="group_profile_buttons">
      <b-button class="mx-3" variant="danger" @click="deleteGroup"
        >그룹삭제</b-button
      >
      <b-button class="mx-3" variant="info" @click="updateGroup"
        >변경하기</b-button
      >
    </div>
    <br />
    <br />
  </b-form>
</template>

<script>
import Profile from "@/components/app/Profile";
import axios from "axios";
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "GroupUpdate",
  components: {
    Profile,
  },

  data: function() {
    return {
      dong: JSON.parse(localStorage.getItem("Login-token"))[
        "user_address_name"
      ],
      club: Object,
      isOpen: false,
      file: null,
      previewImageData: "https://source.unsplash.com/random",
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.article));
    },
    //그룹삭제시
    deleteGroup: function() {
      axios
        .delete(`${SERVER_URL}/club?clubId=${this.club.dto.clubId}`)
        .then(() => {
          this.$router.push({
            name: "GroupList",
            params: { address: this.club.dto.areaCode },
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateGroup: function() {
      if (this.club.dto.clubContent.length < 5) {
        alert(
          "입력하신 정보를 확인해주세요! \n소개글은 5자 이상 작성하셔야 합니다!"
        );
      } else {
        var formData = new FormData();
        formData.append("userId", this.club.dto.userId);
        formData.append("areaCode", this.club.dto.areaCode);
        formData.append("clubName", this.club.dto.clubName);
        formData.append("clubId", this.club.dto.clubId);
        formData.append("clubContent", this.club.dto.clubContent);
        formData.append("isOpen", this.club.dto.isOpen);
        if (this.file != null) {
          formData.append("file", this.file);
        }
        axios
          .put(`${SERVER_URL}/club`, formData, {
            headers: { "Content-Type": `application/json; charset=UTF-8` },
          })
          .then(() => {
            this.$router.push({
              name: "GroupProfile",
              params: {
                addrress: this.club,
                groupId: this.club.dto.clubId,
                groupcheck: "2",
              },
            });
            
          })
          .catch(() => {
            alert("서버에 문제가 발생하였습니다.")
          });
       
      }
    },
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
  },
  created: async function() {
    // 그룹 정보 가져오기!!
    // 그룹정보 가져오기
    axios
      .get(`${SERVER_URL}/club/${this.$route.params.groupId}`)
      .then((res) => {
        this.club = res.data;
        // 비공개, 공개여부

        if (this.club.dto.isOpen == 1) {
          this.isOpen = true;
        } else {
          this.isOpen = false;
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style></style>
