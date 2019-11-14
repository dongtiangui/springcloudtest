import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import BootstrapVue from 'bootstrap-vue' // bootstrap
import 'bootstrap/dist/css/bootstrap.css'
import 'element-ui/lib/theme-chalk/index.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueResource from 'vue-resource'
import fontawesome from '@fortawesome/fontawesome'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import solid from '@fortawesome/fontawesome-free-solid'
import regular from '@fortawesome/fontawesome-free-regular'
import brands from '@fortawesome/fontawesome-free-brands'
import {put,post,get,patch} from './util/http'

import echarts from 'echarts'
import store from './store'
fontawesome.library.add(solid)
fontawesome.library.add(regular)
fontawesome.library.add(brands)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.config.productionTip = false
Vue.use(BootstrapVue)
Vue.use(VueResource)
Vue.use(ElementUI)

Vue.prototype.$echarts = echarts
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

Vue.prototype.$base = '/api'

// Vue.prototype.name 定义vue 全局变量
Vue.prototype.$post = post

Vue.prototype.$put = put

Vue.prototype.$get = get

Vue.prototype.$patch = patch


