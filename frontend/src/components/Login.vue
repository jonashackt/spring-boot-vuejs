<template>
  <div class="protected" v-if="loginSuccess">
    <h1><b-badge variant="success">Access to protected sites granted!</b-badge></h1>
    <h5>If you're able to read this, you've successfully logged in.</h5>
  </div>
  <div class="unprotected" v-else-if="loginError">
    <h1><b-badge variant="danger">You don't have rights here, mate :D</b-badge></h1>
    <h5>Seams that you don't have access rights... </h5>
  </div>
  <div class="unprotected" v-else>
    <h1><b-badge variant="info">Please login to get access!</b-badge></h1>
    <h5>You're not logged in - so you don't see much here. Try to log in:</h5>

    <form @submit.prevent="callLogin()">
      <input type="text" placeholder="username" v-model="user">
      <input type="password" placeholder="password" v-model="password">
      <b-btn variant="success" type="submit">Login</b-btn>
      <p v-if="error" class="error">Bad login information</p>
    </form>
  </div>

</template>

<script>
import api from './backend-api'

export default {
  name: 'login',

  data () {
    return {
      loginSuccess: false,
      loginError: false,
      user: '',
      password: '',
      error: false
    }
  },
  methods: {
    callLogin() {
      api.getSecured(this.user, this.password).then(response => {
        console.log("Response: '" + response.data + "' with Statuscode " + response.status);
        if(response.status == 200) {
          this.loginSuccess = true
        }
      }).catch(error => {
        console.log("Error: " + error);
        this.loginError = true
      })
    }
  }
}

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
