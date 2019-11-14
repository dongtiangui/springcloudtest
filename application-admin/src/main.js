import Vue from 'vue'
// import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
// import './styles/element-variables.scss'
//
// import './styles/index.scss' // global css

import App from './App'
import router from './router'

import './icons' // icon

Vue.use(Element)

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});
