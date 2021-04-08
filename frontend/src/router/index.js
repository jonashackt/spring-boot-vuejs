import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home'
import Service from '../views/Service'
import Bootstrap from '../views/Bootstrap'
import User from '../views/User'
import Login from '../views/Login'
import Protected from '../views/Protected'

import store from '../store'

const routes = [
    { path: '/', component: Home },
    { path: '/callservice', component: Service },
    { path: '/bootstrap', component: Bootstrap },
    { path: '/user', component: User },
    { path: '/login', component: Login },
    {
        path: '/protected',
        component: Protected,
        meta: {
            requiresAuth: true
        }
    },

    // otherwise redirect to home
    { path: '/:pathMatch(.*)*', redirect: '/' }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL), // uris without hashes #, see https://router.vuejs.org/guide/essentials/history-mode.html#html5-history-mode
    routes
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // this route requires auth, check if logged in
        // if not, redirect to login page.
        if (!store.getters.isLoggedIn) {
            next({
                path: '/login'
            })
        } else {
            next();
        }
    } else {
        next(); // make sure to always call next()!
    }
});

export default router;