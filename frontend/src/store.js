import Vue from 'vue'
import Vuex from 'vuex'
import api from './components/backend-api'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        loginSuccess: false,
        loginError: false,
        userName: null
    },
    mutations: {
        login_success(state, name){
            state.loginSuccess = true;
            state.userName = name

        },
        login_error(state, name){
            state.loginError = true;
            state.userName = name
        }
    },
    actions: {
        async login({commit}, {user, password}) {
            console.log("Accessing backend with user: '" + user + "' and password '" + password + "'");
            api.getSecured(user, password)
                .then(response => {
                    console.log("Response: '" + response.data + "' with Statuscode " + response.status);
                    if(response.status == 200) {
                        console.log("Login successful");
                        // place the loginSuccess state into our vuex store
                        return commit('login_success', name);
                    }
                }).catch(error => {
                    console.log("Error: " + error);
                    // place the loginError state into our vuex store
                    commit('login_error', name);
                    return Promise.reject("Invalid credentials!")
                })
        }
    },
    getters: {
        isLoggedIn: state => state.loginSuccess,
        hasLoginErrored: state => state.loginError
    }
})