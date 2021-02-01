<template>
  <div>
    <!-- for문 돌리기 -->
    <!-- <div v-for="unit in data"> -->
      <b-col>
        <b-row class="mt-2">
          <b-col sm="3">
            <!-- 여기 placeholder에 데이터명 bind로 넣어주기 -->
            <h4 class="font-weight-bold">{{ }}</h4>
          </b-col>
          <b-col sm="7">
            <!-- 여기 placeholder에 데이터 값 bind로 넣어주기 -->
            <!-- v-model도 입력해주기 -->
            <b-form-input
            class="font-weight-bold"
            :placeholder="club.clubName"
          ></b-form-input>
          </b-col>
        </b-row>
      </b-col>
      <b-button block style="background-color: #695549" @click="update">수정하기</b-button>
      <b-button block variant="danger" @click="deleteData">삭제하기</b-button>
    </div>
  <!-- </div> -->
</template>

<script>
export default {
  name: 'AdminUpdatePage',
  props: {

  },
  data: function () {
    return {

    }
  },
  methods: {
    deleteData: function () {

    },
    update: function () {

    },
  },
}
</script>

<style>

</style>