import Vue from 'vue'
import VueRouter from 'vue-router'

// 서비스별 abc순으로 정리되어 있다.

//유저
import AccountDetail from '@/views/account/AccountDetail.vue'
import AccountUpdate from '@/views/account/AccountUpdate.vue'
import FindPassword from '@/views/account/FindPassword.vue'
import Login from '@/views/account/Login.vue'
import Signup from '@/views/account/Signup.vue'

//관리자
import AdminDetail from '@/views/admin/AdminDetail.vue'
import AdminHome from '@/views/admin/Admin.vue'
import AdminUpdate from '@/views/admin/AdminUpdate.vue'

//앱
import Badge from '@/views/app/Badge.vue'
import Blank from '@/views/app/Blank.vue' // 처음의 빈 페이지
import Developers from '@/views/app/Developers.vue'
import FindLocation from '@/views/app/FindLocation.vue'
import GetLocation from '@/views/app/GetLocation.vue'
import Home from '@/views/app/Home'

//소식
import NewsHome from '@/views/news/NewsHome'

//리뷰
import FindStore from '@/views/review/FindStore.vue'
import GetStore from '@/views/review/GetStore.vue'

import ReviewCreate from '@/views/review/ReviewCreate.vue'
import ReviewDetail from '@/views/review/ReviewDetail.vue'
import ReviewHome from '@/views/review/ReviewHome.vue'
import ReviewList from '@/views/review/ReviewList.vue'
//스토리

import ArticleCreate from '@/views/story/ArticleCreate.vue'
import ArticleDetail from '@/views/story/ArticleDetail.vue'
import ArticleUpdate from '@/views/story/ArticleUpdate.vue'

import GroupCreate from '@/views/story/GroupCreate.vue'
import GroupList from '@/views/story/GroupList.vue'
import GroupMemberList from '@/views/story/GroupMemberList.vue'
import GroupPage from '@/views/story/GroupPage.vue'
import GroupProfile from '@/views/story/GroupProfile.vue'
import GroupUpdate from '@/views/story/GroupUpdate.vue'

import MyFeed from '@/views/story/MyFeed.vue'
import NewsFeed from '@/views/story/NewsFeed.vue'




Vue.use(VueRouter)

//https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
// const requireAuth = () => (to, from, next) => {
//   const nextRoute = to.path;

//   if (store.getters.getAccessToken | localStorage.getItem('Login-token') != undefined) {
//     return next();
//   } else next("/login" + nextRoute);
// };

const routes = [
  //유저
  {
    path: '/account/detail',
    name: 'AccountDetail',
    component: AccountDetail,
  },
  {
    path: '/account/update',
    name: 'AccountUpdate',
    component: AccountUpdate,
  },
  {
    path: '/account/find',
    name: 'FindPassword',
    component: FindPassword,
  },
  {
    path: '/account',
    name: 'Login',
    component: Login,
  },
  {
    path: '/account/signup',
    name: 'Signup',
    component: Signup,
  },

  //관리자
  {
    path: '/admin/:class',
    name: 'AdminDetail',
    component: AdminDetail,
  },
  {
    path: '/admin',
    name: 'AdminHome',
    component: AdminHome,
  },
  {
    path: '/admin/:class/:pk',
    name: 'AdminUpdate',
    component: AdminUpdate,
  },

  //앱
  {
    path: '/badge/:pk',
    name: 'Badge',
    component: Badge,
  },
  {
    path: '',
    name: 'Blank',
    component: Blank,
  },
  {
    path: '/developers',
    name: 'Developers',
    component: Developers,
  },
  {
    path: '/location/first',
    name: 'FindLocation',
    component: FindLocation,
  },
  {
    path: '/location',
    name: 'GetLocation',
    component: GetLocation,
  }, 
  {
    path: '/home',
    name: 'Home',
    component: Home,
  },

  // //소식
  {
    path: '/news',
    name: 'NewsHome',
    component: NewsHome,
  },

  //리뷰
  {
    path: '/store/find/:address/:keyword',
    name: 'FindStore',
    component: FindStore,
  },
  {
    path: '/store/get/:address',
    name: 'GetStore',
    component: GetStore,
  },

  {
    path: '/review/create/:storeId',
    name: 'ReviewCreate',
    component: ReviewCreate,
  },
  {
    path: '/review/detail/:storeId',
    name: 'ReviewDetail',
    component: ReviewDetail,
  },
  {
    path: '/review',
    name: 'ReviewHome',
    component: ReviewHome,
  },
  {
    path: '/review/:category',
    name: 'ReviewList',
    component: ReviewList,
  },

  //스토리
  {
    path: '/story/:address/article',
    name: 'ArticleCreate',
    component: ArticleCreate,
  },
  {
    path: '/story/:address/article/:userId/:groupId',
    name: 'ArticleDetail',
    component: ArticleDetail,
  },
  {
    path: '/story/:address/article/:userId/:groupId/update',
    name: 'ArticleUpdate',
    component: ArticleUpdate,
  },

  {
    path: '/story/:address/group/create',
    name: 'GroupCreate',
    component: GroupCreate,
  },
  {
    path: '/story/:address/group',
    name: 'GroupList',
    component: GroupList,
  },
  {
    path: '/story/:address/group/:groupId/member',
    name: 'GroupMemberList',
    component: GroupMemberList,
  },
  {
    path: '/story/:address/group/:groupId',
    name: 'GroupPage',
    component: GroupPage,
  },
  {
    path: '/story/:address/group/:groupId/profile',
    name: 'GroupProfile',
    component: GroupProfile,
  },
  {
    path: '/story/:address/group/:groupId/update',
    name: 'GroupUpdate',
    component: GroupUpdate,
  },

  {
    path: '/story/:userId',
    name: 'MyFeed',
    component: MyFeed,
  },
  {
    path: '/story/:address/:userId',
    name: 'NewsFeed',
    component: NewsFeed,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router