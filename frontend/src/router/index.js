import Vue from 'vue'
import VueRouter from 'vue-router'
// 추가한 router(기능별 정렬)
//유저
import Admin from '@/views/account/Admin.vue'
import AdminDetail from '@/views/account/AdminDetail.vue'
import AdminUpdate from '@/views/account/AdminUpdate.vue'
import Signup from '@/views/account/Signup.vue'
import Login from '@/views/account/Login.vue'
import FindPassword from '@/views/account/FindPassword.vue'
import AccountDetail from '@/views/account/AccountDetail.vue'
import AccountUpdate from '@/views/account/AccountUpdate.vue'

//앱
import Badge from '@/views/app/Badge.vue'
import Blank from '@/views/app/Blank.vue'
import Developers from '@/views/app/Developers.vue'
import FindLocation from '@/views/app/FindLocation.vue'
import GetLocation from '@/views/app/GetLocation.vue'
import Home from '@/views/app/Home'

//소식
import NewsHome from '@/views/news/NewsHome'

//리뷰
import ReviewHome from '@/views/review/ReviewHome.vue'
import FindStore from '@/views/review/FindStore.vue'
import GetStore from '@/views/review/GetStore.vue'

import ReviewCreate from '@/views/review/ReviewCreate.vue'
import ReviewList from '@/views/review/ReviewList.vue'
import ReviewDetail from '@/views/review/ReviewDetail.vue'
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
    path: '/admin',
    name: 'Admin',
    component: Admin,
    props:true,
  },
  {
    path: '/admin/detail',
    name: 'AdminDetail',
    component: AdminDetail,
    props:true,
  },
  {
    path: '/admin/detail/update',
    name: 'AdminUpdate',
    component: AdminUpdate,
  },
  {
    path: '/account/signup',
    name: 'Signup',
    component: Signup,
  },
  {
    path: '/account',
    name: 'Login',
    component: Login,
  },
  {
    path: '/accountFind',
    name: 'FindPassword',
    component: FindPassword,
  },
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
  //앱
  {
    path: '/badge',
    name: 'Badge',
    component: Badge,
  },
  {
    path: '/',
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
    path: '/review',
    name: 'ReviewHome',
    component: ReviewHome,
  },
  {
    path: '/review/findstore',
    name: 'FindStore',
    component: FindStore,
  },
  {
    path: '/review/getstore',
    name: 'GetStore',
    component: GetStore,
  },
  {
    path: '/review/article/create',
    name: 'ReviewCreate',
    component: ReviewCreate,
  },
  {
    path: '/review/article',
    name: 'ReviewList',
    component: ReviewList,
  },
  {
    path: '/review/article/detail',
    name: 'ReviewDetail',
    component: ReviewDetail,
  },
  //스토리
  {
    path: '/story/article',
    name: 'ArticleCreate',
    component: ArticleCreate,
  },
  {
    path: '/story/article/detail',
    name: 'ArticleDetail',
    component: ArticleDetail,
  },
  {
    path: '/story/article/update',
    name: 'ArticleUpdate',
    component: ArticleUpdate,
  },
  {
    path: '/story/group',
    name: 'GroupCreate',
    component: GroupCreate,
  },
  {
    path: '/story/group/list',
    name: 'GroupList',
    component: GroupList,
  },
  {
    path: '/story/group/member',
    name: 'GroupMemberList',
    component: GroupMemberList,
  },
  {
    path: '/story/group/main',
    name: 'GroupPage',
    component: GroupPage,
  },
  {
    path: '/story/group/profile',
    name: 'GroupProfile',
    component: GroupProfile,
  },
  {
    path: '/story/group/update',
    name: 'GroupUpdate',
    component: GroupUpdate,
  },
  {
    path: '/story/myfeed',
    name: 'MyFeed',
    component: MyFeed,
  },
  {
    path: '/story/newsfeed',
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