import Vue from 'vue'
import Router from 'vue-router'
import RedisDashboard from '@/components/RedisDashboard2'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'RedisDashboard',
      component: RedisDashboard
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
