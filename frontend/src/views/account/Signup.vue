<template>
  <div id="box"  style="padding:10px;">
    <div id="border" class="my-5 py-5">
      <!-- 1. 제목 -->
      <div id="title" class="mb-5">
        <span><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%"></span>
        <span class="font-weight-bold " style="color: #695549;">주민등록</span>
      </div>
      <!-- 2. 내용 -->
      <!-- 2.1 input창 -->
      <b-col class="mx-3">
          <!-- 2.1.1 아이디 -->
        <b-row id="accountBox">
          <b-col align-self="left">
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
                  class="ml-5"
                  style="width:50%; float:left;"
                  v-model="credentials.userId"
                  placeholder="아이디를 입력하세요"
                  required
                  autofocus
                  @keypress.enter="check_user_id"
                ></b-form-input>
                <b-button id="btn_signup"
                  style="margin-top:3px"
                  size="sm"
                  @click="check_user_id"
                  >확인</b-button
                >
                <br>
                <br>
                <small id = "error1" class="text-danger" style="float:center; margin-top:5px">{{
                  errors[0]
                }}</small>
              </b-form-group>
            </ValidationProvider>
          </b-col>
        </b-row>
        <!-- 2.1.2 닉네임 -->
        <b-row id="accountBox">
          <b-col align-self="left">
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
                    class="ml-5"
                    style="width:50%; float:left;"
                    v-model="credentials.nickname"
                    placeholder="개성있는 닉네임을 입력하세요"
                    required
                    @keypress.enter="check_user_nickname"
                  ></b-form-input>
                  <b-button id="btn_signup"
                    style="margin-top:3px"
                    size="sm"
                    @click="check_user_nickname"
                    >확인</b-button
                  >
                  <br>
                  <br>
                  <small id = "error2" class="text-danger" style=" margin-top:5px">{{
                    errors[0]
                  }}</small>
                </b-form-group>
              </ValidationProvider>
          </b-col>
        </b-row>
        <!-- 2.1.3 이메일 -->
        <b-row id="accountBox">
          <b-col align-self="left">
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
                  class="ml-5"
                  style="width:50%; float:left;"
                  v-model="credentials.email"
                  placeholder="이메일을 입력하세요"
                  required
                  @keypress.enter="check_user_email"
                ></b-form-input>
                <b-button id="btn_signup"
                  style="margin-top:3px"
                  size="sm"
                  @click="check_user_email"
                  >메일 인증</b-button
                >
                <br>
                <br>
                <small id = "error3"  class="text-danger" style=" margin-top:5px">{{
                  errors[0]
                }}</small>
              </b-form-group>
            </ValidationProvider>
          </b-col>
        </b-row>
        <!-- 2.1.4 이메일 인증코드 -->
        <b-row v-if="this.possible_email && this.credentials.email" id="accountBox">
          <b-col align-self="left">
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
                  class="ml-4"
                  style="width:50%; float:left;"
                  v-model="emailCode"
                  placeholder="인증코드를 입력하세요"
                  required
                  @keypress.enter="check_user_emailCode"
                ></b-form-input>
                <b-button id="btn_signup"
                  style="margin-top:3px"
                  size="sm"
                  @click="check_user_emailCode"
                  >인증 확인</b-button
                >
                <br>
                <br>
                <small id = "error4" class="text-danger" style=" margin-top:5px">{{
                  errors[0]
                }}</small>
              </b-form-group>
            </ValidationProvider>
          </b-col>
        </b-row>
        <!-- 2.1.5 비밀번호 재설정 -->
        <b-row id="accountBox">
          <b-col align-self="left">
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
                  class="mx-4"
                  style="width:50%; float:left; font-color : black;"
                  v-model="credentials.password"
                  placeholder="비밀번호를 입력하세요"
                  required
                   @keypress.enter="onSubmit()"
                ></b-form-input>
                <br>
                <br>
                <small  id = "error5"  class="text-danger" style=" margin-top:5px">{{
                  errors[0]
                }}</small>
              </b-form-group>
            </ValidationProvider>
          </b-col>
        </b-row>
        <b-row id="accountBox">
          <b-col align-self="left">
            <ValidationProvider
              name="비밀번호 확인"
              rules="required|confirmed:비밀번호"
              v-slot="{ errors }"
            >
              <b-form-group>
                <label
                  style="float:left; padding-right:10px; padding-top:5px"
                  for="password_confirmation"
                  >비밀번호확인:
                </label>
                <b-form-input
                  type="password"
                  style="width:50%; float:left;"
                  v-model="password_confirmation"
                  placeholder="비밀번호를 한 번 더 입력하세요"
                  required
                  @keypress.enter="onSubmit()"
                ></b-form-input>
                <br>
                <br>
                <small id = "error6"  class="text-danger" style=" margin-top:5px">{{
                  errors[0]
                }}</small>
              </b-form-group>
            </ValidationProvider>
          </b-col>
        </b-row>
      </b-col>
    </div>
    <!-- 버튼 -->
    <div>
      <b-button id="btn_signup" class="mx-3" @click="$router.push({ name: 'Login' })">
        로그인 페이지
      </b-button>
      <b-button id="btn_signup" class="mx-3" @click="onSubmit()">
        가입완료
      </b-button>
    </div>
    

      <!-- modal -->
      <!-- <b-button  variant="danger" v-b-modal="'deleteModal'">회원탈퇴</b-button>
      <b-modal id="deleteModal" @ok="deleteAccount"><img alt="Vue logo" src="@/assets/udonge.png" style="width: 10%">정말 탈퇴하시겠습니까?</b-modal>
      <b-button  style="background-color: #695549;" @click="toDetail">확인</b-button> -->
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
    onSubmit() {
        if( 
          this.checkId == false || this.checkNickname == false || this.checkEmail == false || this.checkEmailCode == false ||
          document.getElementById("error5").innerHTML != "" || document.getElementById("error6").innerHTML != "" 
          )
           {
             alert("중복체크 및 유효성 검사 확인 바랍니다.")
        }else{
          axios.post(`${SERVER_URL}/user`,this.credentials)
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
          .post(`${SERVER_URL}/user/email`,this.credentials)
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
#btn_signup {
  background-color: #695549;
}

input[type="password"] {
  font: small-caption;
  font-size: 16px;
}
::placeholder {
  font-family: "Jeju Gothic", sans-serif;
}
</style>
