<template>
  <div id="box" class="mx-5">
    <!-- 검색창 -->
    <b-nav-form>
      <b-form-input class="mr-sm-2 ml-auto" placeholder="Search"></b-form-input>  <!-- 가운데 정렬이 잘 안된다!!!! ㅠㅠㅠ -->
      <b-button variant="outline-success" class="my-2 my-sm-0" type="submit">Search</b-button>
    </b-nav-form>

    <!-- 그룹 고르는 공간 -->
    <b-row class="my-5 ">
      <b-col md="7">
        <b-button-group v-for="(groupName, i) in groupNames" :key="i">
          <b-button v-if="i != selected" variant="secondary" @click="selectGroup(i)">{{groupName}}</b-button>
          <b-button v-else variant="primary">{{groupName}}</b-button>
        </b-button-group>
      </b-col>
      <b-col md="5">
        <b-button style="background-color: #695549;" @click="toList" end>+</b-button> <!-- variant="outline-secondary" 속성 -->
      </b-col>
    </b-row>

    <div class="mb-5"> <!-- for문 넣기 -->
      <PostBlock :group="groups[selected]" />
    </div>
    <EndBlock />
    <Button />
  </div>
</template>

<script>
import Button from '@/components/story/Button'
import EndBlock from '@/components/story/EndBlock'
import PostBlock from '@/components/story/PostBlock'

import { mapGetters } from "vuex";
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'NewsFeed',
  computed: {
    ...mapGetters(["getUserId"]),
    ...mapGetters(["getUserName"])
  },
  components: {
    Button,
    EndBlock,
    PostBlock,
  },
  data: function () {
    return {
      colors: ["danger", "warning", "success", "primary"],
      groups: [],
      groupNames: [],
      selected: null,  //선택된 그룹
    }
  },
  created() {
    //가입한 그룹 정보 가져오기
    axios
        .get(`${SERVER_URL}/club/user/${this.getUserId}/member`)
        .then(
          (response) => (
            this.groups = response.data,
            this.getGroupNames()
          )
        );
  },
  methods: {
    getGroupNames(){
      //그룹명만 따로 저장
      for(var i in this.groups){
        this.groupNames.push(this.groups[i]['clubName']);
      }

      //클럽 크기가 0일 때 처리해야 함!
    },
    selectGroup(idx){
      this.selected = idx;
    },
    toList: function () {
      this.$router.push({ name: 'GroupList'})
    }
  }
}
</script>

<style>

</style>