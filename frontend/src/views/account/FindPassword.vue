<template>
  <div id="box">
    <div id="border" class="my-5 py-5">
    <!-- 1. 제목 -->
    <div id="title" class="mb-5">
      <span><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%"></span>
      <span class="font-weight-bold " style="color: #695549;">비밀번호 찾기</span>
    </div>
    <!-- 2. 내용 -->
    <b-form @submit.prevent="onSubmit">
      <b-row id="accountBox" class="ml-5">
        <b-col cols="10">
          <ValidationProvider
            name="아이디"
            rules="required|min:5"
            v-slot="{ errors }"
          >
            <b-form-group>
              <label
                style="float:left; padding-right:10px; padding-top:5px"
                for="userId"
                >아이디:
              </label>
              <b-form-input
                type="text"
                style="width:50%; float:left;"
                v-model="credentials.userId"
                placeholder="아이디 입력"
                required
                autofocus
              ></b-form-input>
              <br>
              <br>
              <small class="text-danger" style="margin-top:5px">{{
                errors[0]
              }}</small>
            </b-form-group>
          </ValidationProvider>
        </b-col>
      </b-row>
    <b-row id="accountBox" class="ml-5">
      <b-col cols="10">
        <ValidationProvider
          name="이메일"
          rules="required|email"
          v-slot="{ errors }"
        >
          <b-form-group>
            <label
              style="float:left; padding-right:10px; padding-top:5px"
              for="email"
              >이메일:
            </label>
            <b-form-input
              type="email"
              style="width:50%; float:left;"
              v-model="credentials.email"
              placeholder="이메일 입력"
              required
              @keypress.enter="onSubmit()"
            ></b-form-input>
            <b-button type="submit" size="sm" style="background-color: #695549;">인증번호받기</b-button>
            <br>
            <br>
            <small class="text-danger" style="float:left; margin-top:5px">{{
              errors[0]
            }}</small>
          </b-form-group>
        </ValidationProvider>
      </b-col>
    </b-row>
    </b-form>
    <b-row v-if="this.possible_post">
        <b-form @submit.prevent="onSubmit2">
          <ValidationProvider
            name="인증코드"
            rules="required"
            v-slot="{ errors }"
          >
            <b-form-group>
              <label
                style="float:left; padding-right:10px; padding-top:5px"
                for="인증코드"
                >인증코드:
              </label>
              <b-form-input
                type="text"
                style="width:50%; float:left;"
                v-model="emailcode"
                placeholder="인증번호"
                required
              ></b-form-input>
              <small class="text-danger" style="float:left; margin-top:5px">{{
                errors[0]
              }}</small>
            </b-form-group>
          </ValidationProvider>
          <b-button style="background-color: #695549;" type="submit" class="btn btn-secondary">인증완료</b-button>
        </b-form>
    </b-row>
    <b-row v-if="this.possible_post && !this.possible_id">
       <small class="text-danger" style="float:left; margin-top:5px"
        >아이디 및 이메일을 확인해주세요</small>
    </b-row>


    <div v-if="this.makepassword">
      <b-form @submit.prevent="onSubmit3">
        <div class="container" style="padding:10px">
          <ValidationProvider
            name="비밀번호"
            rules="required|min:6"
            v-slot="{ errors }"
          >
            <b-form-group>
              <label
                style="float:left; padding-right:10px; padding-top:5px"
                for="password"
                >비밀번호:
              </label>
              <b-form-input
                type="password"
                style="width:50%; float:left;"
                v-model="credentials2.password"
                placeholder="비밀번호 입력"
                required
              ></b-form-input>
              <small class="text-danger" style="float:left; margin-top:5px">{{
                errors[0]
              }}</small>
            </b-form-group>
          </ValidationProvider>
        </div>

        <div class="container" style="padding:10px">
          <ValidationProvider
            name="비밀번호 확인"
            rules="required|confirmed:비밀번호"
            v-slot="{ errors }"
          >
            <b-form-group>
              <label
                style="float:left; padding-right:10px; padding-top:5px"
                for="password_confirmation"
                >비밀번호 확인:
              </label>
              <b-form-input
                type="password"
                style="width:50%; float:left;"
                v-model="credentials2.password_confirmation"
                placeholder="비밀번호 확인"
                required
              ></b-form-input>
              <small class="text-danger" style="float:left; margin-top:5px">{{
                errors[0]
              }}</small>
            </b-form-group>
          </ValidationProvider>
        </div>
        <b-button style="background-color: #695549;" type="submit" class="btn btn-secondary"
          >비밀번호 설정 완료</b-button
        >
      </b-form>
    </div>
    </div>
    <br>
    <br>
    <br>
  </div>
</template>

<script>
import axios from "axios";
import { ValidationProvider } from "vee-validate";
//const SERVER_URL = process.env.VUE_APP_SERVER_URL;
const SERVER_URL = "http://localhost:8000";
export default {
  components: {
    ValidationProvider,
    // extend,
  },
  name: "FindPassword",
  data() {
    return {
      credentials: {
        userId: "",
        email: "",
      },
      credentials2: {
        password: "",
        password_confirmation: "",
      },
      possible_post: false,
      possible_id: false,
      makepassword: false,
      emailcode: "",
    };
  },
  methods: {
    onSubmit() {
      axios
        .get(
          `${SERVER_URL}/user/password?userId=${this.credentials.userId}&email=${this.credentials.email}`
        )
        .then(() => {
          alert("인증번호  발송 되었습니다.");
          this.possible_post = true;
          this.possible_id = true;
        })
        .catch(() => {
          alert("다시 입력해주세요.");
          this.credentials.userId = "";
          this.credentials.email = "";
        });
    },
    // onSubmit2 이메일 인증코드 인풋에 넣고 post하기
    async onSubmit2() {
      axios
        .post(`${SERVER_URL}/user/email/${this.emailcode}`)
        .then(() => {
          alert("인증번호  확인 ");
          this.makepassword = true;
        })
        .catch(() => {
          alert("다시 입력해주세요.");
          this.emailcode = "";
        });
    },
    async onSubmit3() {
      if (this.credentials2.password != this.credentials2.password_confirmation) {
        alert("비밀번호가 서로 다릅니다.");
        this.credentials2.password = "";
        this.credentials2.password_confirmation = "";
      } else {
        axios
          .put(`${SERVER_URL}/user/password`, { password : this.credentials2.password, userId : this.credentials.userId})
          .then(() => {
            alert("비밀번호 재수정 완료 ");
            window.location.href = "/account";
            
          })
          .catch(() => {
            alert("비밀번호 재수정 실패 다시확인바람.");
            this.password = "";
            this.password_confirmation = "";
          });
      }
    },
  },
};
</script>

<style>
input[type="password"] {
  font: small-caption;
  font-size: 16px;
}
::placeholder {
  font-family: "Jeju Gothic", sans-serif;
}
</style>
