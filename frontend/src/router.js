import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Service from '@/components/Service'
import Bootstrap from '@/components/Bootstrap'
import User from '@/components/User'
import Login from '@/components/Login'

Vue.use(Router);

export default new Router({
    mode: 'history', // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes: [
    { path: '/', component: Hello },
    { path: '/callservice', name: 'Service', component: Service },
    { path: '/bootstrap', name: 'Bootstrap', component: Bootstrap },
    { path: '/user', name: 'User', component: User },
    { path: '/login', name: 'Login', component: Login },

    // otherwise redirect to home
    { path: '*', redirect: '/' }
    ]
})
