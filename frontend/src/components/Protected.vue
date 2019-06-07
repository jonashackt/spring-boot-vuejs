<template>
  <div class="protected" v-if="loginSuccess">
    <h1><b-badge variant="success">Access to protected site granted!</b-badge></h1>
    <h5>If you're able to read this, you've successfully logged in.</h5>
  </div>
  <div class="unprotected" v-else>
    <h1><b-badge variant="info">Please login to get access!</b-badge></h1>
    <h5>You're not logged in - so you don't see much here. Try to log in:</h5>
    <router-link :to="{ name: 'Login' }" exact target="_blank">Login</router-link>
  </div>

</template>

<script>
import api from './backend-api'

export default {
  name: 'protected',

  data () {
    return {
      loginSuccess: false,
      error: false
    }
  },
  methods: {
    //
  }
}

</script>

