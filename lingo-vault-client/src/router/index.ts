import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import EnglishVocabComponent from '../components/EnglishVocabComponent.vue'
import  LoginComponent from '../components/login/LoginComponent.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: EnglishVocabComponent,
  },
  {
    path: '/login',
    name: 'login',
    component: LoginComponent,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
