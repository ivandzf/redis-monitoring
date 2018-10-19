import Vue from 'vue'
import Router from 'vue-router'
import RedisDashboard from '@/components/RedisDashboard'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'RedisDashboard',
      component: RedisDashboard
    }
  ]
})
