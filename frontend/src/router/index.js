import Vue from 'vue'
import VueRouter from 'vue-router'
// 추가한 router(ABC순 정렬)
import Home from '@/views/app/Home'
import Login from '@/views/account/Login.vue'
import NewsFeed from '@/views/story/NewsFeed.vue'
import Signup from '@/views/account/Signup.vue'
import FindPassword from '@/views/account/FindPassword.vue'
import FindLocation from '@/views/app/FindLocation.vue'
import GroupPage from '@/views/story/GroupPage'

Vue.use(VueRouter)

const routes = [
  // {
  //   path: '/',
  //   name: 'Home',
  //   component: Home
  // },
  // {
  //   path: '/about',
  //   name: 'About',
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // },
  {
    path: '',
    name: 'Home',
    component: Home,
  },
  {
    path: '/user/login',
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
    path: '/club',
    name: 'GroupPage',
    component: GroupPage,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router