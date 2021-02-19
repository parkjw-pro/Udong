<template>
  <div class="my-5 py-5">
    <!-- 참고 사이트: https://www.bootdey.com/snippets/view/User-list-page#preview -->
    <link
      href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
      rel="stylesheet"
    />
    <h3>승인대기 목록</h3>
    <div class="container mb-5">
      <div class="row">
        <div class="col-lg-12">
          <div class="main-box clearfix">
            <div class="table-responsive">
              <table class="table user-list">
                <thead>
                  <tr>
                    <th><span>가입신청 대기자</span></th>
                    <th><span>가입신청 일시</span></th>
                    <th class="text-center"><span>가입인사</span></th>
                    <th>&nbsp;</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(memberWating, idx) in membersWating" :key="idx">
                    <td>
                      <img
                        src="https://bootdey.com/img/Content/avatar/avatar1.png"
                        alt=""
                      />
                      <a href="#" class="user-link">{{ memberWating.nickname }}</a>
                      <!-- <span class="user-subhead">Admin</span> -->
                    </td>
                    <td>
                      {{ memberWating.requestedAt }}
                    </td>
                    <td class="text-center">
                      <span class="label label-default">{{
                        memberWating.content
                      }}</span>
                    </td>

                    <td style="width: 20%;">
                      <!-- <a href="#" class="table-link">
                        <span class="fa-stack">
                          <i class="fa fa-square fa-stack-2x"></i>
                          <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                        </span>
                      </a>
                     -->
                      <div v-if="check">
                        <a
                          href="#"
                          class="table-link danger"
                          @click="deleteMember(member.userId)"
                        >
                          <span class="fa-stack">
                            <i class="fa fa-square fa-stack-2x"></i>
                            <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                          </span>
                        </a>
                          <a href="#" class="table-link" @click="createMember(memberWating)"> 
                        <span class="fa-stack">
                          <i class="fa fa-square fa-stack-2x"></i>
                          <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                        </span>
                      </a>
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <ul class="pagination pull-right">
              <li>
                <a href="#"><i class="fa fa-chevron-left"></i></a>
              </li>
              <li><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li>
                <a href="#"><i class="fa fa-chevron-right"></i></a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <h3 class="mt-5 pt-5">그룹원 관리</h3>
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <div class="main-box clearfix">
            <div class="table-responsive">
              <table class="table user-list">
                <thead>
                  <tr>
                    <th><span>그룹원</span></th>
                    <th><span>가입일시</span></th>
                    <th class="text-center"><span>직책</span></th>

                    <th>&nbsp;</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(member, idx) in members" :key="idx">
                    <td>
                      <img
                        src="https://bootdey.com/img/Content/avatar/avatar1.png"
                        alt=""
                      />
                      <a href="#" class="user-link">{{ member.nickname }}</a>
                      <!-- <span class="user-subhead">Admin</span> -->
                    </td>
                    <td>
                      {{ member.createdAt }}
                    </td>
                    <td class="text-center">
                      <span class="label label-default">{{
                        member.type == 1 ? "매니저" : "멤버"
                      }}</span>
                    </td>

                    <td style="width: 20%;">
                      <!-- <a href="#" class="table-link">
                        <span class="fa-stack">
                          <i class="fa fa-square fa-stack-2x"></i>
                          <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                        </span>
                      </a>
                      <a href="#" class="table-link">
                        <span class="fa-stack">
                          <i class="fa fa-square fa-stack-2x"></i>
                          <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                        </span>
                      </a> -->
                      <div v-if="check">
                        <a
                          href="#"
                          class="table-link danger"
                          @click="deleteMember(member.userId)"
                        >
                          <span class="fa-stack">
                            <i class="fa fa-square fa-stack-2x"></i>
                            <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                          </span>
                        </a>
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <ul class="pagination pull-right">
              <li>
                <a href="#"><i class="fa fa-chevron-left"></i></a>
              </li>
              <li><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li>
                <a href="#"><i class="fa fa-chevron-right"></i></a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios"

const SERVER_URL = process.env.VUE_APP_SERVER_URL

export default {
  name: "GroupMemberList",
  data() {
    return {
      userId: JSON.parse(localStorage.getItem('Login-token'))['user-id'],
      members: Object,
      membersWating: Object,
      check: false,
      // group 정보를 params로 받아왔다 -> 새로고침하면 안됨!!
      group: this.$route.params.group,
    };
  },
  created() {
    this.memberList();
    this.memberWatingList();
    if (this.$route.params.groupcheck == 1) {
      // 공개그룹
      this.check = true;
    } else {
      // 비공개그룹
      this.check = false;
    }
  },
  methods: {
    //해당 그룹 멤버조회
    memberList: function() {
      axios
        .get(`${SERVER_URL}/club/${this.$route.params.groupId}/member`)
        .then((res) => {
          
          this.members = res.data;
        })
        .catch((err) => {
          console.log(err);
          alert("서버에 오류발생하였습니다.");
        });
    },
    // 승인 대기목록 
     memberWatingList: function() {
      axios
        .get(`${SERVER_URL}/club/${this.$route.params.groupId}/waiting`)
        .then((res) => {
          
          this.membersWating = res.data;
        })
        .catch((err) => {
          console.log(err);
          alert("서버에 오류발생하였습니다.");
        });
    },
    // 멤버 탈퇴시키기
    deleteMember(data) {
      if (this.userId === this.group.userId) {
        alert("본인을 삭제할 수는 없습니다.")
      } else {
        axios
        .delete(
          `${SERVER_URL}/club/member?clubId=${this.$route.params.groupId}&&userId=${data}&&type=1&&contents=aaa`
        )
        .then(() => {
          
          this.$router.push({
            name: "GroupProfile",
            params: {
              address: this.$route.params.address,
              groupId: this.$route.params.groupId,
              groupcheck: 2,
            },
          });
        })
        .catch((err) => {
          console.log(err);
          alert("서버에 오류발생하였습니다.");
        });
      }
      
    },
    //가입신청 멤버승인하기 
    createMember(data){
      console.log(data)
      axios
        .post(
          `${SERVER_URL}/club/member/waiting`,data)
        .then(() => {
      
          this.memberWatingList();
          this.memberList();
        })
        .catch((err) => {
          console.log(err);
          alert("서버에 오류발생하였습니다.");
        });
    }
  },
};
</script>

<style>
body {
  margin-top: 20px;
}

/* USER LIST TABLE */
.user-list tbody td > img {
  position: relative;
  max-width: 50px;
  float: left;
  margin-right: 15px;
}
.user-list tbody td .user-link {
  display: block;
  font-size: 1.25em;
  padding-top: 3px;
  margin-left: 60px;
}
.user-list tbody td .user-subhead {
  font-size: 0.875em;
  font-style: italic;
}

/* TABLES */
.table {
  border-collapse: separate;
}
.table-hover > tbody > tr:hover > td,
.table-hover > tbody > tr:hover > th {
  background-color: #eee;
}
.table thead > tr > th {
  border-bottom: 1px solid #c2c2c2;
  padding-bottom: 0;
}
.table tbody > tr > td {
  font-size: 0.875em;
  background: #f5f5f5;
  border-top: 10px solid #fff;
  vertical-align: middle;
  padding: 12px 8px;
}
.table tbody > tr > td:first-child,
.table thead > tr > th:first-child {
  padding-left: 20px;
}
.table thead > tr > th span {
  border-bottom: 2px solid #c2c2c2;
  display: inline-block;
  padding: 0 5px;
  padding-bottom: 5px;
  font-weight: normal;
}
.table thead > tr > th > a span {
  color: #344644;
}
.table thead > tr > th > a span:after {
  content: "\f0dc";
  font-family: FontAwesome;
  font-style: normal;
  font-weight: normal;
  text-decoration: inherit;
  margin-left: 5px;
  font-size: 0.75em;
}
.table thead > tr > th > a.asc span:after {
  content: "\f0dd";
}
.table thead > tr > th > a.desc span:after {
  content: "\f0de";
}
.table thead > tr > th > a:hover span {
  text-decoration: none;
  color: #2bb6a3;
  border-color: #2bb6a3;
}
.table.table-hover tbody > tr > td {
  -webkit-transition: background-color 0.15s ease-in-out 0s;
  transition: background-color 0.15s ease-in-out 0s;
}
.table tbody tr td .call-type {
  display: block;
  font-size: 0.75em;
  text-align: center;
}
.table tbody tr td .first-line {
  line-height: 1.5;
  font-weight: 400;
  font-size: 1.125em;
}
.table tbody tr td .first-line span {
  font-size: 0.875em;
  color: #969696;
  font-weight: 300;
}
.table tbody tr td .second-line {
  font-size: 0.875em;
  line-height: 1.2;
}
.table a.table-link {
  margin: 0 5px;
  font-size: 1.125em;
}
.table a.table-link:hover {
  text-decoration: none;
  color: #2aa493;
}
.table a.table-link.danger {
  color: #fe635f;
}
.table a.table-link.danger:hover {
  color: #dd504c;
}

.table-products tbody > tr > td {
  background: none;
  border: none;
  border-bottom: 1px solid #ebebeb;
  -webkit-transition: background-color 0.15s ease-in-out 0s;
  transition: background-color 0.15s ease-in-out 0s;
  position: relative;
}
.table-products tbody > tr:hover > td {
  text-decoration: none;
  background-color: #f6f6f6;
}
.table-products .name {
  display: block;
  font-weight: 600;
  padding-bottom: 7px;
}
.table-products .price {
  display: block;
  text-decoration: none;
  width: 50%;
  float: left;
  font-size: 0.875em;
}
.table-products .price > i {
  color: #8dc859;
}
.table-products .warranty {
  display: block;
  text-decoration: none;
  width: 50%;
  float: left;
  font-size: 0.875em;
}
.table-products .warranty > i {
  color: #f1c40f;
}
.table tbody > tr.table-line-fb > td {
  background-color: #9daccb;
  color: #262525;
}
.table tbody > tr.table-line-twitter > td {
  background-color: #9fccff;
  color: #262525;
}
.table tbody > tr.table-line-plus > td {
  background-color: #eea59c;
  color: #262525;
}
.table-stats .status-social-icon {
  font-size: 1.9em;
  vertical-align: bottom;
}
.table-stats .table-line-fb .status-social-icon {
  color: #556484;
}
.table-stats .table-line-twitter .status-social-icon {
  color: #5885b8;
}
.table-stats .table-line-plus .status-social-icon {
  color: #a75d54;
}
</style>
