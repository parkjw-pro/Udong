import Vue from 'vue'

import App from './App.vue'
import store from './store'
import router from './router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueGlide from 'vue-glide-js'
import 'vue-glide-js/dist/vue-glide.css'
import ToggleButton from 'vue-js-toggle-button'
import { extend, localize } from 'vee-validate';
import { required, email, min, confirmed } from 'vee-validate/dist/rules';
import ko from 'vee-validate/dist/locale/ko.json'
import vSelect from 'vue-select'  // vue-select
import VuePackeryPlugin from 'vue-packery-plugin'
import VueDraggabillyPlugin from 'vue-packery-draggabilly-plugin'
import Mint from 'mint-ui';
import coverflow from 'vue-coverflow'



localize('ko', ko)
extend('email', email)
extend('required', required)
extend('min', min)
extend('confirmed', confirmed)
// Vue.use(VeeValidate)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(VueGlide)
Vue.use(ToggleButton)
Vue.use(VuePackeryPlugin)
Vue.use(VueDraggabillyPlugin) // Packery Draggabilly
Vue.use(Mint);  // Mint UI
Vue.use(coverflow)
Vue.component('v-select', vSelect)  // vue-select
Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')