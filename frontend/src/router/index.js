import Vue from 'vue'
import VueRouter from 'vue-router'
// 추가한 router(ABC순 정렬)
import Home from '@/views/app/Home'
import Login from '@/views/account/Login.vue'
import NewsFeed from '@/views/story/NewsFeed.vue'
import Signup from '@/views/account/Signup.vue'
import FindPassword from '@/views/account/FindPassword.vue'
import FindLocation from '@/views/app/FindLocation.vue'
import Admin from '@/views/account/Admin.vue'
Vue.use(VueRouter)

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;

  if (store.getters.getAccessToken | localStorage.getItem('Login-token') != undefined) {
    return next();
  } else next("/login" + nextRoute);
};

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
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router