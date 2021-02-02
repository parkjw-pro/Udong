<template>
  <div id="box">
    <b-button block style="background-color: #695549" @click="toGroupArticle">추가하기</b-button>
    <!-- <div v-for="unit in data"> -->
      <b-row>
        <b-col><div>{{ unit }}</div></b-col>
        <b-button block style="background-color: #695549" @click="toUpdate">수정하기</b-button>
      </b-row>
    <!-- </div> -->
    <!-- <b-table striped hover :items="items" :fields="fields"></b-table> -->
  </div>
</template>

<script>
//const SERVER_URL = "http://localhost:8000";
  export default {
    name: 'AdminDetail',
    props: {
      // 가져온 데이터 여기 입력하기!!!
      
    },
    data() {
      return {
        // 예시
        data: []
        // Note `isActive` is left out and will not appear in the rendered table
        // fields: ['first_name', 'last_name', 'age'],
        // items: [
        //   { isActive: true, age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
        //   { isActive: false, age: 21, first_name: 'Larsen', last_name: 'Shaw' },
        //   { isActive: false, age: 89, first_name: 'Geneva', last_name: 'Wilson' },
        //   { isActive: true, age: 38, first_name: 'Jami', last_name: 'Carney' }
        // ]
      }
    },
    methods: {
      toUpdate: function () {
        // 수정할 데이터 query로 전달하기
        // this.$router.push({name: 'AdminUpdatePage', query: {club: this.club}})
      }
    }
  }
</script>