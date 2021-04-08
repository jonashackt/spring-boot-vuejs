import { createApp } from 'vue';
import App from './App.vue'
import router from './router'
import store from './store'
//import 'bootstrap-vue/dist/bootstrap-vue.css'
//import 'bootstrap/dist/css/bootstrap.css'
//import BootstrapVue from 'bootstrap-vue'

createApp(App).use(router).use(store).mount('#app');

// .use(BootstrapVue)
