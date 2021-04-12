<template>
  <div class="unprotected" v-if="loginError">
    <h1><span class="badge bg-danger">You don't have rights here, mate :D</span></h1>
    <h5>Seems that you don't have access rights... </h5>
  </div>
  <div class="unprotected" v-else>
    <h1><span class="badge bg-warning text-dark">Please login to get access!</span></h1>
    <h5>You're not logged in - so you don't see much here. Try to log in:</h5>

    <form @submit.prevent="callLogin()">
      <input type="text" placeholder="username" v-model="user">
      <input type="password" placeholder="password" v-model="password">
      <button type="submit" class="btn btn-primary">Login</button>
      <p v-if="error" class="error">Bad login information</p>
    </form>
  </div>

</template>

<script lang="ts">
import { defineComponent } from 'vue';
import {AxiosError} from "axios";

interface State {
  loginError: boolean;
  user: string;
  password: string;
  error: boolean;
  errors: AxiosError[]
}

export default defineComponent({
  name: 'Login',

  data: (): State => {
    return {
      loginError: false,
      user: '',
      password: '',
      error: false,
      errors: []
    }
  },
  methods: {
    callLogin() {
      this.errors = [];
      this.$store.dispatch("login", { user: this.user, password: this.password})
        .then(() => {
          this.$router.push('/Protected')
        })
        .catch((error: AxiosError) => {
          this.loginError = true;
          this.errors.push(error);
          this.error = true
        })
    }
  }
});
</script>