<template>
  <!-- 개선점!! -->
  <!-- - 닉네임 & 비밀번호 변경 로직 개선 -->

  <!-- 해결할 문제 점! -->
  <!-- 1. 닉네임(회원정보) 수정 요청을 보내서 성공하였지만, 실제로 수정이 되지 않는다! -->
  <div id="box">
    <div id="border" class="my-5 py-5">
      <!-- 1. 제목 -->
      <div id="title" class="mb-5">
        <span><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%"></span>
        <span class="font-weight-bold " style="color: #695549;">회원정보</span>
      </div>
      <!-- 2. 내용 -->
      <!-- 2.1 input창 -->
      <b-col class="mx-3">
          <!-- 2.1.1 아이디 -->
        <b-row id="accountBox">
          <b-col id="label" cols="3"><span>아이디</span></b-col>
          <b-col id="label_content" cols="5">
            <b-form-input
              type="text"
              :placeholder="user.userId"
              style="text-align: center;"
              disabled
            ></b-form-input>
          </b-col>
        </b-row>
        <!-- 2.1.2 닉네임 -->
        <b-row id="accountBox">
          <b-col id="label" cols="3"><span>닉네임</span></b-col>
          <b-col id="label_content" cols="5">
            <b-form-input
              v-model="user.nickname"
              type="text"
              :placeholder="user.nickname"
              style="text-align: center;"
              @keypress="switchCheck"
            ></b-form-input>
            <small v-if="!check" class="text-danger"> 닉네임을 확인해주세요.</small>
            <div v-if="check === true" class="mt-2">
              <small class="text-success"> 사용가능한 닉네임입니다.</small>
              <b-button class='ml-2' style="background-color: #695549;"  size="sm" @click="updateNickname">변경하기</b-button>
            </div>
          </b-col>
          <b-col cols="2">
            <b-button
              style="margin-top:3px; background-color: #695549;"
              size="sm"
              @click="checkNickname"
            >중복확인</b-button>
            <br>
          </b-col>
        </b-row>
        <!-- 2.1.3 이메일 -->
        <b-row id="accountBox">
          <b-col id="label" cols="3"><span>이메일</span></b-col>
          <b-col id="label_content" cols="5">
            <b-form-input
              type="email"
              :placeholder="user.email"
              style="text-align: center;"
              disabled
            ></b-form-input>
          </b-col>
        </b-row>
        <!-- 2.1.4 동네 설정 -->
        <b-row id="accountBox">
          <b-col id="label" cols="3"><span>우리동네</span></b-col>
          <b-col id="label_content" cols="5" class="font-weight-bold">
            {{ dong }}
          </b-col>
          <b-col cols="2">
            <b-button style="background-color: #695549;" size="sm" @click="addLocation">추가</b-button>
            <b-button variant="danger" size="sm" @click="deleteLocation">삭제</b-button>
          </b-col>
        </b-row>
        <!-- 2.1.5 비밀번호 재설정 -->
        <b-row id="accountBox">
          <b-col id="label" cols="3"><span>비밀번호</span></b-col>
          <b-col id="label_content" cols="5">
            <b-form-input
              type="password"
              v-model="password"
              placeholder="비밀번호을 변경하려면 입력하세요."
              style="text-align: center;"
            ></b-form-input>
          </b-col>
        </b-row>
        <b-row id="accountBox">
          <b-col id="label" cols="3"><span>비밀번호 확인</span></b-col>
          <b-col id="label_content" cols="5">
            <b-form-input
                type="password"
                v-model="passwordConf"
                placeholder="비밀번호을 변경하려면 입력하세요."
                style="text-align: center;"
            ></b-form-input>
            <div v-if="isCorrect !== -1">
              <small class="text-danger"> 비밀번호가 일치하지 않습니다.</small>
              <!-- <small v-else class="text-success"> 사용 가능!</small> -->
            </div>
          </b-col>
          <b-col cols="2">
            <b-button
              style="margin-top:3px; background-color: #695549;"
              size="sm"
              v-b-modal="'passwordModal'"
            >변경</b-button>
            <b-modal id="passwordModal" @ok="updatePassword"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">정말 변경하시겠습니까?</b-modal>
          </b-col>
        </b-row>
      </b-col>
    </div>
    <div>
      <!-- modal -->
      <b-button class="mx-3" variant="danger" v-b-modal="'deleteModal'">회원탈퇴</b-button>
      <b-modal id="deleteModal" @ok="deleteAccount"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">정말 떠나시나요...?</b-modal>
      <b-button class="mx-3"  style="background-color: #695549;" @click="toDetail">확인</b-button>
      </div>
  </div>
</template>

<script>
import axios from 'axios'

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: 'AccountUpdate',
  data: function () {
    return {
      dong: '역삼동',
      user: {
        userId: "",
        nickname: "",
        email: "",
      },
      // 닉네임 중복확인 했는지?
      isCheck: -1,

      // 변경할 패스워드
      password: '',
      passwordConf: '',
      // 패스워드 체크
      isCorrect: -1,
    }
  },
  computed: {
    // 닉네임 중복 확인이 되었는지 체크한다.
    check: function () {
      return this.isCheck
    },
    // 패스워드가 서로 동일한지 체크
    correct: function () {
      if (this.password === this.passwordConf) {
        return true
      } else {
        return false
      }
    },
  },
  methods: {
    addLocation: function () {
      alert("동네를 2개까지 추가할 수 있다!!?? \n서비스 준비 중입니다!")
    },
    checkNickname: function() {
      if(this.user.nickname.length < 2){
        alert("2글자 이상으로 아름다운 닉네임을 지어주세요~!")
        this.user.nickname = "";
        this.isCheck = false
      }
      else{
        axios
          .get(`${SERVER_URL}/user/nickname/${this.user.nickname}`)
          .then(() => {
            alert("사용 가능한 닉네임 입니다.");
            this.isCheck = true;
          })
          .catch(() => {
            if (this.user.nickname != "") {
              alert("현재 사용중인 닉네임 입니다.");
              this.user.nickname = "";
              this.isCheck = false
            }
          });
      }
    },
    deleteAccount: function () {
      axios
          .delete(`${SERVER_URL}/user`, this.user)
          .then(() => {
            alert("작별의 인사 드립니다. \n또 함께 할 시간을 기다리고 있겠습니다.")
            this.$router.push({ name: 'Login' })
          })
          .catch((err) => {
            alert("현재 서버가 우리의 이별을 허락하지 않습니다ㅠㅠ \n잠시후 다시 시도해주세요.")
            console.log(err)
          })
    },
    deleteLocation: function () {
      alert('한 개 이상의 동네를 설정하셔야 합니다!')
    },
    toDetail: function () {
      this.$router.push({ name: 'AccountDetail' })
    },
    switchCheck: function () {
      this.isCheck = -1
    },
    updateNickname: function () {
      console.log(this.user)
      axios.put(`${SERVER_URL}/user`, this.user)
        .then(() => {
          console.log('닉네임 변경 성공!!!!')
          alert("닉네임이 성공적으로 저장되었습니다!");
          this.isCheck = -1

          // 데이터 교체
          const userInfo1 = JSON.parse(localStorage.getItem('Login-token'))
          const userInfo2 = JSON.parse(localStorage.getItem('Info-token'))
          userInfo1.nickname = this.user.nickname
          userInfo2.nickname = this.user.nickname

          // 여기가 안된다!!!!!
          localStorage.setItem("Login-token", JSON.stringify(userInfo1));
          localStorage.setItem("Info-token", JSON.stringify(userInfo2));
          // this.$router.push({ name: 'AccountDetail' })
          // location.replace('/accuont/detail')
          

        })
        .catch((err) => {
          console.log(err)
          alert("닉네임 변경을 실패했습니다.")
        })
    },
    updatePassword: function () {
      if (this.password !== this.passwordConf) {
        this.isCorrect = false
      }
      else {
        const data = {
          userId: this.user.userId,
          password: this.password
        }
        axios.put(`${SERVER_URL}/user/password`, data)
          .then(() => {
            this.isCorrect = -1
            alert("비밀번호가 변경되었습니다!")
          })
          .catch((err) => {
            alert("오류가 발생했습니다!")
            console.log(err)
          })
      }
      this.password = ''
      this.passwordConf = ''
    },
  },
  mounted() {
    const userInfo = JSON.parse(localStorage.getItem('Info-token'))
    this.user.userId = userInfo["userId"]
    this.user.nickname = userInfo["nickname"]
    this.user.email = userInfo["email"]
  }
}
</script>

<style>
#accountBox {
  margin-left: 30px;
  /* margin-right: 30px; */
  margin-bottom: 30px;
}

#border {
  border: 3px dotted #695549;
  /* opacity: 0.8; */
}

#label {
  padding-right: 0px;
  margin-right: 0px;
}

#label_content {
  padding-left: 0px;
  margin-left: 0px;
}
</style>