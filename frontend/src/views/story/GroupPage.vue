<template>
  <div id="box" class="mx-5">
    <!-- 검색창 -->
    <b-row align-h="center">
      <b-nav-form>
        <b-form-input
          class="mr-sm-2 ml-auto"
          placeholder="그룹내 검색"
          style="text-align: center;"
        ></b-form-input>
        <b-button variant="outline-info" class="my-2 my-sm-0" type="submit"
          >검색</b-button
        >
      </b-nav-form>
    </b-row>
    <b-row class="my-5" align-h="between">
      <b-col
        ><h2 class="font-weight-bold">{{ this.group.clubName }}</h2></b-col
      >
      <b-col>
        <!-- v-if="user.group.status=Admin" -->
        <!-- <b-button variant="info">회원관리</b-button> -->
        <!-- v-else -->
        <b-button variant="info" @click="toGroupProfile">그룹 프로필</b-button>
        <b-button variant="info" @click="toGroupMember">회원 목록</b-button>
        <b-button style="background-color: #695549;" @click="toArticleCreate"
          >게시글작성</b-button
        >
      </b-col>
    </b-row>
    <hr />
    <div class="mb-5">
      <!-- for문 넣기 -->
      <div class="mb-5" v-for="(post, i) in posts" :key="i">
        <PostBlock :post="post" :groupName="group.clubName" />
      </div>
    </div>
    <br class="my-5" />
    <br class="my-5" />
    <br class="my-5" />
    <br class="my-5" />
    <EndBlock />
    안녕 여기는 첫지점이야!
    <!-- <Button /> -->
  </div>
</template>

<script>
import axios from "axios";
import EndBlock from "@/components/story/EndBlock";
import PostBlock from "@/components/story/PostBlock";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  name: "GroupPage",

  components: {
    EndBlock,
    PostBlock,
  },
  data() {
    return {
      group: {},
      postCount: 0,
      posts: [],
      limit: 5, //한 페이지에 노출될 게시글의 수
      offset: 0, //게시글 번호 오프셋
      user_address: JSON.parse(localStorage.getItem("Login-token"))["user_address"],
      groupcheck: "0",
    };
  },
  methods: {
    //해당 그룹에 해당하는 게시물을 가져온다
    getGroupPosts() {
      axios
        .get(`${SERVER_URL}/clubpost/club`, {
          params: {
            clubId: this.$route.params.groupId,
            limit: this.limit,
            offset: this.offset,
          },
        })
        .then((response) => {
          this.posts.push(...response.data.list);
          this.postCount = response.data.count;
        });
    },
    //해당 그룹에 대한 정보를 가져온다.
    getGroup: function() {
      axios
        .get(`${SERVER_URL}/club/${this.$route.params.groupId}`)
        .then((res) => {
          this.group = res.data.dto;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //게시물작성 버튼
    toArticleCreate: function() {
      this.groupcheck = "1"; // 그룹에서 게시물 작성한다는뜻
      this.$router.push({
        name: "ArticleCreate",
        params: {
          address: this.user_address,
          groupId: this.group.clubId,
          groupcheck: this.groupcheck,
        },
      });
    },
    //프로필 작성 버튼

    toGroupProfile: function() {
      //그룹장이랑 로그인한 아이뒤랑 같으면.
      if (
        this.group.userId ==
        JSON.parse(localStorage.getItem("Login-token"))["user-id"]
      ) {
        this.$router.push({
          name: "GroupProfile",
          params: {
            address: this.user_address,
            groupId: this.group.clubId,
            groupcheck: 2,
            group: this.group
          },
        });
      } // 디르면
      else {
        this.$router.push({
          name: "GroupProfile",
          params: {
            address: this.user_address,
            groupId: this.group.clubId,
            groupcheck: 1,
            group: this.group
          },
        });
      }
    },

    toGroupMember: function() {
      //그룹장이랑 로그인한 아이뒤랑 같으면.
      if (
        this.group.userId ==
        JSON.parse(localStorage.getItem("Login-token"))["user-id"]
      ) {
        this.$router.push({
          name: "GroupMemberList",
          params: {
            address: this.user_address,
            groupId: this.group.clubId,
            groupcheck: 1,
            group: this.group,
          },
        });
      } // 디르면
      else {
        this.$router.push({
          name: "GroupMemberList",
          params: {
            address: this.user_address,
            groupId: this.group.clubId,
            groupcheck: 0,
            group: this.group,
          },
        });
      }
    },
  },
  created() {
    this.getGroup();
    this.getGroupPosts();
  },
};
</script>

<style></style>
