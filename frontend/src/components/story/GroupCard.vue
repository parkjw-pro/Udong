<template>
  <div
    class="group_list_img"
    :style="background_image"
    @click="groupDetail()"
  >
    <div class="group_list_img_content">
      <h2>{{ this.group.clubName }}</h2>
    </div>
    <div class="group_list_img_cover"></div>
  </div>
</template>

<script>
import axios from "axios"

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: "GroupCard",
  props: {
    group: Object,
  },
  data: function() {
    return {
      urls: "",
      result: "",
      myClub: Object,
      checkMygroup: "0",
      // props한 이미지 가져오기
      background_image: {
        backgroundImage: `url(${SERVER_URL}/club/download/${this.group.fileId})`,
      },
    };
  },
  methods: {
    groupDetail() {
      // 내그룹 내역 가져오기
      axios
        .get(
          `${SERVER_URL}/club/user/${
            JSON.parse(localStorage.getItem("Login-token"))["user-id"]
          }/member`
        )
        .then((res) => {
          this.myClub = res.data;
          console.log("내그룹 조회성공");

          for (var i in this.myClub) {
            console.log(this.myClub[i].clubId);
            if (this.group.clubId == this.myClub[i].clubId) {
              // 내그룹에 있으면 체크변수 1로
              this.checkMygroup = "1";
            }
          }

          if (this.checkMygroup == "1") { // 내그룹에 있을떄  
            this.$router.push({
              name: "GroupPage",
              params: {
                address: JSON.parse(localStorage.getItem("Login-token"))[
                  "user_address"
                ],
                groupId: this.group.clubId,
              },
            });
          } else { // 내그룹에 없을때  가입창으로 
            this.$router.push({
              name: "GroupProfile",
              params: {
                address: JSON.parse(localStorage.getItem("Login-token"))[
                  "user_address"
                ],
                groupId: this.group.clubId,
                groupcheck: 0, // 가입자
              },
            });
          }
        })
        .catch(() => {
          console.log("내그룹 조회 실패");
        });
    },
  },
};
</script>

<style>
.group_list_img {
  position: relative;
  height: 100vh;
  background-size: cover;
  width: 300px;
  height: 300px;
}

.group_list_img_cover {
  position: absolute;
  height: 300px;
  width: 300px;
  background-color: rgba(0, 0, 0, 0.4);
  z-index: 1;
}

.group_list_img_content {
  position: absolute;
  top: 15rem;
  padding-left: 1rem;
  /* transform: translate(-25%, -50%); */
  font-size: 5rem;
  font-weight: bold;
  color: white;
  z-index: 2;
  text-align: center;
}
</style>
