// 중복확인 하는 법 모르겠음 // 저렇게 중복확인 함수로 두면 submit 됐을때
b-form-group확인해주는거에서 안넘어가는듯
<template>
  <div id="box"  style="padding:10px;">
    <!-- class="container" -->
    <br>
    <h2>회원가입</h2>
    <hr />
    <b-form @submit.prevent="onSubmit">
      <div class="container" style="padding:10px">
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
              style="width:60%; float:left;"
              v-model="credentials.userId"
              placeholder="아이디 입력"
              required
            ></b-form-input>
            <b-button
              style="margin-top:3px"
              size="sm"
              @click="check_user_id"
              type="submit"
              >확인</b-button
            >
            <small id = "error1" class="text-danger" style="float:left; margin-top:5px">{{
              errors[0]
            }}</small>
          </b-form-group>
        </ValidationProvider>
      </div>

      <div class="container" style="padding:10px">
        <ValidationProvider
          name="닉네임"
          rules="required|min:2"
          v-slot="{ errors }"
        >
          <b-form-group>
            <label
              style="float:left; padding-right:10px; padding-top:5px"
              for="nickname"
              >닉네임:
            </label>
            <b-form-input
              type="text"
              style="width:60%; float:left;"
              v-model="credentials.nickname"
              placeholder="닉네임 입력"
              required
            ></b-form-input>
            <b-button
              style="margin-top:3px"
              size="sm"
              @click="check_user_nickname"
              type="submit"
              >확인</b-button
            >
            <small id = "error2" class="text-danger" style="float:left; margin-top:5px">{{
              errors[0]
            }}</small>
          </b-form-group>
        </ValidationProvider>
      </div>

      <div class="container" style="padding:10px">
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
              style="width:60%; float:left;"
              v-model="credentials.email"
              placeholder="이메일 입력"
              required
            ></b-form-input>
            <b-button
              style="margin-top:3px"
              size="sm"
              @click="check_user_email"
              type="submit"
              >메일 인증</b-button
            >
            <small id = "error3"  class="text-danger" style="float:left; margin-top:5px">{{
              errors[0]
            }}</small>
          </b-form-group>
        </ValidationProvider>
      </div>

      <!-- 인증 코드  -->
      <div v-if="this.possible_email && this.credentials.email">
        <div class="container" style="padding:10px">
          <ValidationProvider
            name="인증코드"
            rules="required|min:4"
            v-slot="{ errors }"
          >
            <b-form-group>
              <label
                style="float:left; padding-right:10px; padding-top:5px"
                for="emailCode"
                >인증코드:
              </label>
              <b-form-input
                type="text"
                style="width:60%; float:left;"
                v-model="emailCode"
                placeholder="인증코드 입력"
                required
              ></b-form-input>
              <b-button
                style="margin-top:3px"
                size="sm"
                @click="check_user_emailCode"
                type="submit"
                >인증 확인</b-button
              >
              <small id = "error4" class="text-danger" style="float:left; margin-top:5px">{{
                errors[0]
              }}</small>
            </b-form-group>
          </ValidationProvider>
        </div>
      </div>

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
              style="width:60%; float:left; font-color : black;"
              v-model="credentials.password"
              placeholder="비밀번호 입력"
              required
            ></b-form-input>
            <small  id = "error5"  class="text-danger" style="float:left; margin-top:5px">{{
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
              style="width:60%; float:left;"
              v-model="password_confirmation"
              placeholder="비밀번호 확인"
              required
            ></b-form-input>
            <small id = "error6"  class="text-danger" style="float:left; margin-top:5px">{{
              errors[0]
            }}</small>
          </b-form-group>
        </ValidationProvider>
      </div>

      <b-button type="submit" class="btn btn-secondary" style="float:right;"  @click="onSubmit1()"
        >가입완료</b-button
      >
    </b-form>
    <button
      class="btn btn-secondary"
      style="float:left;"
      @click="$router.push('/account')"
    >
      뒤로가기
    </button>
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
  name: "Singup",
  data() {
    return {
      credentials: {
        userId: "",
        nickname: "",
        email: "",
        password: "",
        
      },
      password_confirmation: "",
      possible_email : false,
      emailCode : "",
      
      checkId : false,
      checkNickname : false,
      checkEmail : false,
      checkEmailCode : false,
    };
  },
  methods: {
    onSubmit1() {
        if( 
          this.checkId == false || this.checkNickname == false || this.checkEmail == false || this.checkEmailCode == false ||
          document.getElementById("error5").innerHTML != "" || document.getElementById("error6").innerHTML != "" 
          )
           {
             alert("중복체크 및 유효성 검사 확인 바랍니다.")
        }else{
          axios.post(`${SERVER_URL}/user",this.credentials`)
          .then(()=>{
           alert("회원가입 성공");
           window.location.href = "/account";
           
          })
          .catch(()=>{
            alert("서버에 문제가 생겼습니다. 다시 가입 바랍니다.");
             window.location.href = "/account";
          });
        }
      
      
    },
    check_user_id: function() { 
      if(this.credentials.userId == "" || document.getElementById("error1").innerHTML != ""){
        this.credentials.usrId = "";
        alert("아이디 다시 입력 바랍니다.")
      }
      else{
        axios
          .get(`${SERVER_URL}/user/id/${this.credentials.userId}`)
          .then(() => {
            alert("사용 가능한 아이디 입니다.");
            this.checkId = true;
          })
          .catch(() => {
            if (this.credentials.userId != "") {
              alert("현재 사용중인 아이디 입니다.");
              this.credentials.userId = "";
            }
          });
      }
    },
    check_user_nickname: function() {
       if(this.credentials.nickname == ""|| document.getElementById("error2").innerHTML != ""){
        alert("닉네임 다시입력 바랍니다.")
        this.credentials.nickname = "";
      }
      else{
        axios
          .get(`${SERVER_URL}/user/nickname/${this.credentials.nickname}`)
          .then(() => {
            alert("사용 가능한 닉네임 입니다.");
            this.checkNickname = true;
          })
          .catch(() => {
            if (this.credentials.nickname != "") {
              alert("현재 사용중인 닉네임 입니다.");
              this.credentials.nickname = "";
            }
          });
      }
    },
     check_user_email: function() {
      if(this.credentials.email == "" || document.getElementById("error3").innerHTML != ""){
        alert("이메일 다시 입력 바랍니다.")
        this.credentials.email = "";
      }
      else{
        axios
          .post(`${SERVER_URL}/user/email",this.credentials`)
          .then(() => {
            alert("사용 가능한 이메일 입니다. 인증코드 입력바랍니다.");
            this.checkEmail = true;
            this.possible_email = true;
          })
          .catch(() => {
            if (this.credentials.email != "") {
              alert("현재 사용중인 이메일 입니다.");
              this.credentials.email = "";
            }
          });
      }
    },
    check_user_emailCode: function() {
      if(this.emailCode == "" || document.getElementById("error4").innerHTML != ""){
        alert("인증코드 다시입력 바랍니다.")
      }
      else{
        axios
          .post(`${SERVER_URL}/user/email/${this.emailCode}`)
          .then(() => {
            this.checkEmailCode = true;
            alert("인증완료");
          })
          .catch(() => {
            if (this.emailCode != "") { 
              alert("인증번호가 일치하지 않습니다.");
              this.emailCode = "";
            }
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
