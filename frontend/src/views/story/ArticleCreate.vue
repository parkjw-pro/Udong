<template>
  <!-- 이미지 추가하는 부분을 넣어야 한다!!! -->
  <div id="article_box">
    <b-row class="mb-5" align-h="between">
      <b-col><h5 class="font-weight-bold">그룹 선택</h5></b-col>
      <b-col id="dropdown_group">
        <v-select v-model="selected" :options="options"></v-select>
      </b-col>
    </b-row>
    <b-form-textarea
      class="mb-5"
      id="textarea"
      v-model="content"
      placeholder="게시글을 입력하세요"
      rows="8"
      max-rows="10"
    ></b-form-textarea>
    <br />

    <!-- 이미지 -->
    <b-container fluid class="p-4 mb-5">
      <b-row>
        <!-- <b-col v-for="(file, index) in files" :key="index">
            <div class="image_box">
              <b-img left thumbnail fluid :src="file.name" alt="Image"></b-img>
            </div>
          </b-col> -->
        <b-col>
          <div v-for="(url, index) in imageUrl" :key="index">
            <b-img left thumbnail fluid :src="url"></b-img>
          </div>
        </b-col>
      </b-row>
      <b-row align-h="center">
        <!-- <b-form-file multiple :file-name-formatter="formatNames"></b-form-file> -->
        <b-form-file
          multiple="multiple"
          v-model="files"
          placeholder="첨부파일 없음"
          drop-placeholder="Drop file here..."
          required
          accept=".jpg, .png, .gif"
          @change="previewImage"
          style="width: 70%;"
        ></b-form-file>
        <!-- <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div> -->
      </b-row>
    </b-container>
    <b-row align-h="between" class="mx-5 mb-5"> 
      <b-button variant="info" @click="$router.push({ name: 'NewsFeed' })">취소</b-button>
      <b-button type="submit" variant="info" @click="createArticle"
        >확인</b-button
      >
    </b-row>
    <br />
    <br />
    <br />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "ArticleCreate",
  computed: {
    ...mapGetters(["getUserId"]),
    ...mapGetters(["getUserName"]),
  },
  data: function() {
    return {
      clubs: [],
      multipartFile: null,
      files: [],
      imageUrl: [],
      content: "",
      options: ["내 피드"],
      selected: "내 피드",
      isOpen: "1", //공개면 1, 비공개면 0
    };
  },
  created() {
    //가입한 그룹 정보 가져오기
    axios
      .get(`${SERVER_URL}/club/user/${this.getUserId}/member`)
      .then((response) => ((this.clubs = response.data), this.setOptions()));
  },
  methods: {
    setOptions() {
      //그룹명만 따로 저장
      for (var i in this.clubs) {
        this.options.push(this.clubs[i]["clubName"]);
      }
    },
    // formatNames(files) {
    //   this.files = files;
    //   console.log(this.files);
    //   return files.length === 1 ? files[0].name : ${files.length} files selected;
    // },
    previewImage(event) {
      console.log(this.imageUrl)
      for(var i =0; i < this.imageUrl.length;i++){ this.imageUrl[i] = ""} 
     
      for (var image of event.target.files) {
        const file = image;
        console.log(file)
        this.imageUrl.push(URL.createObjectURL(file));
      }
      console.log(this.imageUrl)
    },
  
    createArticle() {
      var formData = new FormData();
      formData.append('isOpen', this.isOpen)
      formData.append('postContent', this.content)
      formData.append('userId', this.getUserId)
      for (let i = 0; i < this.files.length; i++) {
        formData.append('file', this.files[i]);
      }
      //this.text가 빈칸일 때 처리
      if (this.content == null) {
        console.log("warn: textarea is null");
      }

      //userpost / clubpost 구분 -> 적절한 url로 axios 보내기
      if (this.selected == "내 피드") {
        //userpost`
        //file 넣어야 함

        axios
          .post(`${SERVER_URL}/userpost`,formData , { headers: { "Content-Type": `application/json; charset=UTF-8`}
        })
          .then((response) => console.log(response.data));
      } else {
        //clubpost
        var clubId = 0; //clubId 가져오기
        for (var i in this.clubs) {
          var club = this.clubs[i];
          if (this.selected == club["clubName"]) {
            clubId = club["clubId"];
            break;
          }
        }
        formData.append("clubId",clubId)
        //file 넣어야 함
        axios
          .post(`${SERVER_URL}/clubpost`,formData , { headers: { "Content-Type": `application/json; charset=UTF-8`}
        })
          .then((response) => console.log(response.data));
      }
    },
  },
};
</script>

<style>
/* ### {
  font-size: 26px;
  font-weight: 600;
  color: #2c3e5099;
  text-rendering: optimizelegibility;
  -moz-osx-font-smoothing: grayscale;
  -moz-text-size-adjust: none;
} /

#dropdown_group {
  max-width: 30em;
  / margin: 1em auto;
  margin-right: 50px;
} */

#article_box {
  width: 50%;
  height: 50%;
  position: absolute;
  left: 25%;
  margin-top: 5%;
}
</style>
