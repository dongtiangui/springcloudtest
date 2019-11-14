import Vue from 'vue'
import Router from 'vue-router'
import login from '../views/login'
import home from '../views/home'

import basic from '../components/welcometab'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/index',
      name: 'home',
      component: home
    },
    {
      path: "/basic",
      name: 'basic',
      component: basic
    }
  ]
})
