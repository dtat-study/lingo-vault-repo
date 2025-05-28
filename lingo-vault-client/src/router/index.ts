import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import ErrorComponent from '../components/common/ErrorComponent.vue'
import HomeComponent from '../components/home/HomeComponent.vue'
import  LoginComponent from '../components/login/LoginComponent.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeComponent,
  },
  {
    path: '/login',
    name: 'login',
    component: LoginComponent,
  },
    {
    path: '/error',
    name: 'error',
    component: ErrorComponent,
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'error',
    component: ErrorComponent,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
