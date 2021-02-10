<template>
  <div>
    <div
      class="group_list_img mx-4 my-4"
      :style="background_image"
      @click="groupDetail()"
    >
      <div class="group_list_img_content">
        <h2>{{ this.group.clubName }}</h2>
        <!-- <h2>{{ this.group.fileId }}</h2> -->
      </div>
      <div class="group_list_img_cover"></div>
    </div>
  </div>
</template>

<script>
//import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "GroupCard",
  props: {
    group: Object,
  },
  data: function() {
    return {
      urls: "",
      result: "",
      // props한 이미지 가져오기
      background_image: {
        //  backgroundImage: `url(${require('@/assets/story/group_sample_diet.jpg')})`
        // backgroundImage: `url('@/C:/SSAFY/temp/s04p13a404/backend/uploads/club/ssafy13/udong_20210204_022827abc123')`,
        backgroundImage: `url(${SERVER_URL}/club/download/${this.group.fileId})`,
      },
    };
  },
  methods: {
    groupDetail() {
      this.$router.push({
        name: "GroupPage",
        params: {
          address: JSON.parse(localStorage.getItem("Login-token"))[
            "user_address"
          ],
          groups: this.group,
        },
      });
    },
  },
  mounted() {
    // axios
    //   .get(`${SERVER_URL}/club/${this.group.clubId}`)
    //   .then((res) => {
    //     console.log("클럽이미지 성공");
    //     this.clubdto = res;
    //     console.log(this.clubdto.data.dto.userId);
    //     console.log(this.clubdto.data.fileUrl[0]);
    //     this.urls = this.clubdto.data.fileUrl[0];
    //     this.result =
    //       "/uploads/club/" +
    //       this.clubdto.data.dto.userId +
    //       "/" +
    //       this.urls.substr(
    //         14 + this.clubdto.data.dto.userId.length,
    //         this.clubdto.data.fileUrl[0].length - 1
    //       );
    //   })
    //   .catch(() => {
    //     console.log("클럽이미지 실패");
    //   });
    // if (this.group.fileId != undefined && this.group.fileId != null) {
    //     axios
    //       .get(`${SERVER_URL}/club/download/${this.group.fileId}`)
    //       .then((res) => {
    //         console.log("이미지 다운로드 성공");
    //         console.log(res);
    //       })
    //       .catch(() => {
    //         console.log("이미지 다운로드 실패");
    //       });
    //   }
  },
};
</script>

<style>
.group_list_img {
  position: relative;
  height: 100vh;
  background-size: cover;
  /* background-image: url("../../assets/story/group_sample_diet.jpg"); */
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
  top: 280px;
  left: 70px;
  transform: translate(-50%, -50%);
  font-size: 5rem;
  font-weight: bold;
  color: white;
  z-index: 2;
  text-align: center;
}
</style>
