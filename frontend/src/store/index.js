import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios';
Vue.use(Vuex)
const SERVER_URL =  process.env.VUE_APP_SERVER_URL

export default new Vuex.Store({
  state: {
    accessToken: null,
    userId: "",
    nickname: ""
  },
  getters: {
    getAccessToken(state) {
      if (localStorage.getItem('Login-token') != undefined) {
        //return localStorage.getItem('Login-token');
        return localStorage.getItem('auth-token');
      }
      return state.accessToken;
      //return state.accessToken = localStorage.getItem('auth-token');
    },
    getUserId(state) {
      if (localStorage.getItem('Login-token') != undefined) {
        return JSON.parse(localStorage.getItem('Login-token'))['user-id'];
      }
      return state.userId;
    },
    getUserName(state) {
      if (localStorage.getItem('Login-token') != undefined) {
        return JSON.parse(localStorage.getItem('Login-token'))['user-name'];
      }
      return state.nickname;
    }
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload["auth-token"];
      state.userId = payload["user-id"];
      state.nickname = payload["user-name"];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userId = "";
      state.nickname = "";
    }
  },

  actions: {
    LOGIN(context, user) {
      localStorage.clear();
      return axios
        .post(`${SERVER_URL}/user/login`, user)
        .then((response) => {
          console.log("axios login");
          context.commit("LOGIN", response.data);
          localStorage.setItem("auth-token",response.data["auth-token"]);
          //axios.defaults.headers.common["auth0-token"] = ${response.data["auth-token"]};
          //localStorage.setItem("auth-token",${response.data["auth-token"]});
          axios.defaults.headers.common["auth-token"] = localStorage.getItem('auth-token');

      if (localStorage.getItem('Login-token') == undefined) {
        localStorage.setItem('Login-token', JSON.stringify(response.data));
      }

      axios
        .get(`${SERVER_URL}/user`)
        .then((response) => {
          console.log("axios login info");
          localStorage.setItem('Info-token', JSON.stringify(response.data.user));
          // window.location.href = "/location/first";
        })
        .catch(() => {
          localStorage.clear();
          window.location.href = "/account";
          alert("로그인 실패 아이디및 비밀번호 확인 부탁드립니다.");
        });

    })
    .catch(() => {
      localStorage.clear();
      window.location.href = "/account";
      alert("로그인 실패 아이디및 비밀번호 확인 부탁드립니다.");
    });
},
LOGOUT(context) {
  context.commit("LOGOUT");
  axios.defaults.headers.common["auth-token"] = undefined;
  localStorage.clear();
  //window.location.reload();
  window.location.href = "/account";
}
  },
})