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
      rows="11"
      maxlength="500"
      style="overflow: hidden;"
    ></b-form-textarea>
    <p style="text-align: right;">{{ contentLength }} / 500</p>
    <br />

    <!-- 3. 이미지 -->
    <b-container fluid class="my-3 pb-5" style="">
      <b-row class="py-3">
        <b-col
          cols="3"
          v-for="(url, index) in imageUrl"
          :key="index"
          left
          align-self="center"
        >
          <b-img class="px-3" id="img_thumbnail" thumbnail fluid :src="url">
          </b-img>
        </b-col>
        <b-col cols="3" class="ml-0 pl-0" align-self="center">
          <b-icon
            icon="plus"
            v-b-modal.image-modal
            font-scale="4"
            variant="dark"
            style="cursor: pointer;"
          >
            &nbsp;
          </b-icon>
        </b-col>
      </b-row>
    </b-container>

    <!-- 이미지 업로더 modal -->
    <b-modal
      id="image-modal"
      ref="image-modal"
      title="소중한 사진을 올려주세요!"
      style="font-family: 'Jeju Gothic', sans-serif;"
      hide-footer
    >
      <b-form-file
        multiple="multiple"
        v-model="files"
        placeholder="첨부파일 없음"
        drop-placeholder="Drop file here..."
        required
        accept=".jpg, .png, .gif"
        style="width: 70%;"
        @change="previewImage"
      ></b-form-file>
      <b-row class="mt-3 mx-3" align-h="end">
        <b-button class="mr-1" variant="danger" size="sm" @click="hideModal"
          >추가 안 할래요</b-button
        >
        <b-button variant="primary" size="sm" @click="hideModal"
          >추가하기!</b-button
        >
      </b-row>
    </b-modal>

    <b-row align-h="center" class="mx-5 mb-5">
      <b-col cols="3">
        <b-button size="" variant="danger" v-b-modal.article-cancel-modal
          >돌아가기</b-button
        >
      </b-col>
      <b-col cols="3">
        <b-button
          size=""
          style="background-color: #695549;"
          @click="createArticle"
          >작성</b-button
        >
      </b-col>
    </b-row>

    <!-- 돌아가기 버튼 클릭 후 나타나는 modal -->
    <b-modal
      id="article-cancel-modal"
      @ok="
        $router.push({
          name: 'NewsFeed',
          params: { userId: getUserId, nickname: getUserName },
        })
      "
    >
      리뷰 작성을 취소하시겠습니까?
    </b-modal>
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
    contentLength: function() {
      return this.content.length;
    },
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
      tag: null,
      groupCheck: 0, // 그룹에서 게시물작성 눌렀는지 체크하는 변수
      groupName: 0, // 그룹에서 게시물작성할때 넘어온 그룹내임
      areaCode: JSON.stringify(JSON.parse(localStorage.getItem('Login-token'))['user_address'])
    };
  },
  created() {
    this.areaCode = this.areaCode.substring(1,this.areaCode.length-1)
    console.log(this.areaCode)
    this.groupCheck = this.$route.params.groupcheck;
    if (this.groupCheck == 0) {
        //가입한 그룹 정보 가져오기
      axios
        .get(`${SERVER_URL}/club/user/${this.getUserId}/member`)
        .then((response) => ((this.clubs = response.data), this.setOptions()));
    } else {
      //  해당그룹에  이름가져오기
      axios
        .get(`${SERVER_URL}/club/${this.$route.params.groupId}`)
        .then((response) => {
          this.options.pop();
          this.clubs = response.data
          this.options.push(response.data.dto.clubName);
          this.selected = response.data.dto.clubName;
        });

      // this.clubs = this.$route.params.group.clubName
      // console.log("이름 "+ this.clubs)
      // this.options = (this.clubs);
      // this.selected = this.clubs
    }
  },
  methods: {
    // 글작성
    createArticle() {
      var tags = this.getTag();
      var formData = new FormData();

      formData.append("isOpen", this.isOpen);
      formData.append("postContent", this.content);
      formData.append("userId", this.getUserId);
      formData.append("postTag", tags);
  
      for (let i = 0; i < this.files.length; i++) {
        formData.append("file", this.files[i]);
      }
      //this.text가 빈칸일 때 처리2
      if (this.content == null) {
        console.log("warn: textarea is null");
      }

      //userpost / clubpost 구분 -> 적절한 url로 axios 보내기
      if (this.selected == "내 피드") {
        formData.append("areaCode", this.areaCode);
        
        //userpost`
        //file 넣어야 함
        console.log(formData);
        axios
          .post(`${SERVER_URL}/userpost`, formData, {
            headers: { "Content-Type": `application/json; charset=UTF-8` },
          })
          .then(() => {
             this.$router.push({
                name: "NewsFeed",
                params: {
                  address: JSON.parse(localStorage.getItem("Login-token"))[
                    "user_address"
                  ],
                  userId: this.getUserId,
                },
              });
          });
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
        formData.append("clubId", clubId);
        //file 넣어야 함
        axios
          .post(`${SERVER_URL}/clubpost`, formData, {
            headers: { "Content-Type": `application/json; charset=UTF-8` },
          })
          .then((response) => {
            console.log(response.data);
            if (this.groupCheck == 0) {
              this.$router.push({
                name: "NewsFeed",
                params: {
                  address: JSON.parse(localStorage.getItem("Login-token"))[
                    "user_address"
                  ],
                  userId: this.getUserId,
                },
              });
            } else {
              this.$router.push({
                name: "GroupPage",
                params: {
                  address: JSON.parse(localStorage.getItem("Login-token"))[
                    "user_address"
                  ],
                  groupId: this.$route.params.groupId,
                },
              });
            }
          })
          .catch(()=> {
            console.log("글작성 오류")
          })
      }
    },
    getTag() {
      var strs = this.content.split("#").slice(1);
      var tags = "";
      for (var i in strs) {
        var str = strs[i].replace(" ", "");
        if (str != "") tags += "#" + str;
      }
      return tags;
    },
    hideModal() {
      this.$refs["image-modal"].hide();
    },
    previewImage(event) {
      console.log(this.imageUrl);
      for (var i = 0; i < this.imageUrl.length; i++) {
        this.imageUrl[i] = "";
      }

      for (var image of event.target.files) {
        const file = image;
        console.log(file);
        this.imageUrl.push(URL.createObjectURL(file));
      }
      console.log(this.imageUrl);
    },
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
  },
};
</script>

<style>
#article_box {
  width: 50%;
  height: 50%;
  position: absolute;
  left: 25%;
  margin-top: 5%;
}

#img_thumbnail {
  width: 10rem;
  height: 12rem;
  max-width: 10rem;
  max-height: 12rem;
}
</style>
