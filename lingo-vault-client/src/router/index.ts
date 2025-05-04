import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import EnglishVocab from '../components/EnglishVocab.vue'
import Tulip from '../components/Tulip.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: EnglishVocab,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
