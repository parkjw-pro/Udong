<template>
  <div id="box">
     <ul>
        <li v-for="(asd,idx) in user " :key=idx>
          아이디:{{ asd.userId }}  닉네임 :{{ asd.nickname }} 이메일:{{ asd.email }}
          <b-button block style="background-color: #695549" @click="toUpdate(asd)">수정하기</b-button>
        </li>
        
      </ul> 
    <!-- <b-button block style="background-color: #695549" @click="toGroupArticle">추가하기</b-button> -->
    <!-- <div v-for="unit in data"> -->
      <b-row>
        <b-col><div>{{ unit }}</div></b-col>
        
      </b-row>
     
      <!-- <h2>name: {{ $route.query }}</h2> -->
    <!-- </div> -->
    <!-- <b-table striped hover :items="items" :fields="fields"></b-table> -->
    <!-- <div v-for="asd from data">
      <div>{{ asd.userId }}</div>
    </div> -->
  </div>
</template>

<script>
// const SERVER_URL = process.env.VUE_APP_SERVER_URL
  export default {
    name: 'AdminDetail',

    data() {
      return {
        // 예시
        data: [],
        user: Array,
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
      toUpdate(res) {
        // 수정할 데이터 query로 전달하기
         this.$router.push({name: 'AdminUpdate', query: {userDetail: res}})
        // console.log(this.$route.query.users.data[5].userId)
        // console.log(this.user[0].userId )
      }
    },
    created(){
      this.user = this.$route.query.users.data
    }
  }
</script>