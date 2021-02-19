<template>
  <b-form id="box" @submit="onSubmit">
    <!-- 1. 프로필 이미지 -->
    <b-row class="pb-5" align-h="between">
      <!-- 프로필 이미지 -->
      <b-col class="ml-3">
        <!-- props:  :fileId="Club.fileId" -->
        <Profile :fileId="this.club.dto.fileId" />
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
        <span class="large">{{ dong }}</span> 그룹
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
    <div class="mb-5 pb-2" style="text-align: left;">
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
        v-b-modal.modal-2
        >가입하기</b-button
      >
      <b-modal id="modal-2" :title="club.dto.clubName + ' 그룹 가입하기'" hide-footer>
        <div class="container">
          <div class="col-md-12">
            <div class="well">
              <textarea
                v-model="contents"
                class="form-control"
                id="text"
                name="text"
                placeholder="가입인사를 작성해주세요!"
                rows="5"
              ></textarea>
              <h6 class="pull-right" id="count_message"></h6>
              <div style="text-align: right;">
                <b-button variant="info" size="sm" @click="joinGroup">가입 신청</b-button>
              </div>
            </div>
          </div>
        </div>
      </b-modal>

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
        @click="memberList"
        v-b-modal.modal-1
        >그룹 위임 및 탈퇴</b-button
      >

      <b-modal id="modal-1" title="그룹장 위임 및 탈퇴" hide-footer>
        <b-form-select
          v-model="selected"
          :options="options"
          size="sm"
          class="my-3"
        ></b-form-select>
        <b-button class="mx-3" variant="info" @click="leaveGroup"
          >그룹 탈퇴하기
        </b-button>
      </b-modal>
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
  name: "GroupProfile",
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
      isJoined: 0,
      selected: null,
      options: [],
      contents : "",
      group: this.$route.params.group,
    };
  },
  computed: {
    isJoin: function() {
      return this.isJoined;
    },
  },
  created() {
    this.isJoined = this.$route.params.groupcheck; // 2이면 그룹장이보는 그룹프로필  0이면 가입프로필 1이면 회원이보는 그룹프로필
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
  methods: {
    // 가입하기
    onSubmit(evt) {
      evt.preventDefault(); // 기본 form태그 이벤트를 막아줌
    },
    // 가입하기
    joinGroup: function() {
      if (this.isOpen == 1) {
        // 공개일때 바로가입
        axios
          .post(`${SERVER_URL}/club/member`, {
            clubId: this.club.dto.clubId,
            userId: JSON.parse(localStorage.getItem("Login-token"))["user-id"],
            type: 0,
            content: this.contents,
          })
          .then(() => {
           
            alert("가입완료되었습니다.");
            this.$router.push({
              name: "GroupList",
              params: { address: this.club.dto.areaCode },
            });
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        // 비공개일때
        axios
          .post(`${SERVER_URL}/club/member`, {
            clubId: this.club.dto.clubId,
            userId: JSON.parse(localStorage.getItem("Login-token"))["user-id"],
            type: 0,
            content: this.contents,
          })
          .then(() => {
           
            alert("가입신청되었습니다.");
            this.$router.push({
              name: "GroupList",
              params: { address: this.club.dto.areaCode },
            });
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    // 멤버리스트 불러오기
    memberList: function() {
      axios
        .get(`${SERVER_URL}/club/${this.club.dto.clubId}/member`)
        .then((res) => {
     
          this.options.length = 0;
          for (var i in res.data) this.options.push(res.data[i].nickname);
        })
        .catch((err) => {
          console.log(err);
          alert("서버에 오류발생하였습니다.");
        });
    },
    //그룹 탈퇴 //여기부터
    leaveGroup: function() {
      if (this.isJoined == 2) {
        // 그룹장인경우  그룹장 변경후 탈퇴
        axios
          .put(
            `${SERVER_URL}/club/manager?userId=${
              JSON.parse(localStorage.getItem("Login-token"))["user-id"]
            }&&clubId=${this.club.dto.clubId}`
          )
          .then(() => {
          

            axios
              .delete(
                `${SERVER_URL}/club/member?clubId=${
                  this.club.dto.clubId
                }&&userId=${
                  JSON.parse(localStorage.getItem("Login-token"))["user-id"]
                }&&type=1&&contents=aaa`
              )
              .then((res) => {
                console.log(res);
                this.$router.push({
                  name: "GroupList",
                  params: { address: this.club.dto.areaCode },
                });
              })
              .catch((err) => {
                console.log(err);
                alert("서버에 오류발생하였습니다.");
              });
          })
          .catch((err) => {
            console.log(err);
            alert("서버에 오류발생하였습니다.");
          });
      } else {
        // 그룹장이 아닌경우

        axios
          .delete(
            `${SERVER_URL}/club/member?clubId=${this.club.dto.clubId}&&userId=${
              JSON.parse(localStorage.getItem("Login-token"))["user-id"]
            }&&type=1&&contents=aaa`
          )
          .then((res) => {
            console.log(res);
            this.$router.push({
              name: "GroupList",
              params: { address: this.club.dto.areaCode },
            });
          })
          .catch((err) => {
            console.log(err);
            alert("서버에 오류발생하였습니다.");
          });
      }
    },
    // 회원관리
    toMemberList: function() {
      this.$router.push({
        name: "GroupMemberList",
        params: {
          address: this.club.dto.areaCode,
          groupId: this.club.dto.clubId,
          groupcheck: 1,
          group: this.group,
        },
      });
    },
    updateGroup: function() {
      this.$router.push({
        name: "GroupUpdate",
        params: {
          address: this.club.dto.areaCode,
          groupId: this.club.dto.clubId,
        },
      });
    },
  },
};
</script>

<style></style>
