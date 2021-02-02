import Vue from 'vue'
import VueRouter from 'vue-router'
<<<<<<< HEAD
// 추가한 router(기능별 정렬)
import Admin from '@/views/account/Admin.vue'
=======
// 추가한 router(ABC순 정렬)
import Home from '@/views/app/Home'
import Login from '@/views/account/Login.vue'
import NewsFeed from '@/views/story/NewsFeed.vue'
import GroupUpdate from '@/views/story/GroupUpdate.vue'
>>>>>>> community
import Signup from '@/views/account/Signup.vue'
import Login from '@/views/account/Login.vue'
import FindPassword from '@/views/account/FindPassword.vue'
import Home from '@/views/app/Home'

import NewsFeed from '@/views/story/NewsFeed.vue'


import FindLocation from '@/views/app/FindLocation.vue'
<<<<<<< HEAD

import GroupPage from '@/views/story/GroupPage'
=======
import Admin from '@/views/account/Admin.vue'
import ReviewHome from '@/views/review/ReviewHome'

>>>>>>> community

Vue.use(VueRouter)

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
// const requireAuth = () => (to, from, next) => {
//   const nextRoute = to.path;

//   if (store.getters.getAccessToken | localStorage.getItem('Login-token') != undefined) {
//     return next();
//   } else next("/login" + nextRoute);
// };

const routes = [
  // {
  //   path: '/about',
  //   name: 'About',
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // },
  {
    path: '/home',
    name: 'Home',
    component: Home,
  },
  {
    path: '/account/admin',
    name: 'Admin',
    component: Admin,
  },
  {
    path: '/',
    name: 'Login',
    component: Login,
  },
  {
    path: '/user/signup',
    name: 'Signup',
    component: Signup,
  },
  {
    path: '/user/findpassword',
    name: 'FindPassword',
    component: FindPassword,
  },
  {
    path: '/story',
    name: 'NewsFeed',
    component: NewsFeed,
  },
  {
    path: '/location',
    name: 'FindLocation',
    component: FindLocation,
  },
  {
    path: '/group/update',
    name: 'GroupUpdate',
    component: GroupUpdate,
  },
  {
    path: '/review',
    name: 'ReviewHome',
    component: ReviewHome,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router