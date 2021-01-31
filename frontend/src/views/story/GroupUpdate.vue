<template>
  <b-form @submit="on_submit" style="margin-left: 15%; margin-right: 15%;">
    <b-row class="mb-5 pb-5">
      <!-- 프로필 이미지 -->
      <b-col md="4">
        <Profile />
      </b-col>
      <!-- 공개/비공개 버튼 -->
      <b-col md="2">
        <b-row class="mb-2">
          <div>공개/비공개</div>
        </b-row>
        <b-row class="mb-3">
          <span>
            <toggle-button
              :value="form.is_public"
              :width="80"
              :height="35"
              :labels="{checked: '공개', unchecked: '비공개'}"
              :color="{
                checked: '#695549', unchecked: '#a0a0a0'
              }"
              disabled
            />
          </span>
        </b-row>
        <!-- bootstrap > Form File 참고하면 나와있음 -->
        <b-row>
          <b-form-file
            v-model="file1"
            :state="Boolean(file1)"
            placeholder="Choose a file or drop it here..."
            drop-placeholder="Drop file here..."
          ></b-form-file>
          <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div>
        </b-row>
      </b-col>
    </b-row>
    <!-- 동네 이름 -->
    <h3 class="ml-3 mb-5" style="text-align: left;"><span class="font-weight-bold">역삼동</span> 그룹</h3>

    <!-- 그룹명 -->
    
    <div id="group_profile_name" class="mb-5" style="text-align: left;">
      <b-col md="4">
        <b-row class="mt-2">
          <b-col sm="3">
            <h4 class="font-weight-bold">이름</h4>
          </b-col>
          <b-col sm="7">
            <b-form-input
            class="font-weight-bold"
            v-model="form.name"
            placeholder="다이어트"
            required
          ></b-form-input>
          </b-col>
        </b-row>
      </b-col>
    </div>
    <!-- 그룹소개 -->
    <div id="group_profile_intro" class="mb-5 pb-5" style="text-align: left;">
      <b-col md="4">
        <h4 class="font-weight-bold mb-4">소개글</h4>
          <b-form-textarea
            v-model="form.intro"
            placeholder="여러분 코로나라고 계속해서 '확 찐자'로 살 수 없습니다!! 모두 다이어트 성공합시다 ㅎㅎ"
            rows="8"
          ></b-form-textarea>
      </b-col>
    </div>
    <!-- 하단 버튼 -->
    <div id="group_profile_buttons">
      <b-button class="mx-3" variant="danger">그룹삭제</b-button>
      <b-button class="mx-3" variant="success">회원관리</b-button>
      <b-button class="mx-3" type="submit" variant="info">변경하기</b-button>
    </div>
  </b-form>
</template>

<script>
import Profile from '@/components/app/Profile'

export default {
  name: 'GroupUpdate',
  components: {
    Profile
  },
  // props로 가져오기
  data: function () {
    return {
      form: {
        "name": "",
        "intro": "",
        "is_public": true,
        "img_url": "",
      }
    }
  },
  methods: {
    on_submit: function () {
      // 제출하기
    }
  }
}
</script>

<style>

</style>