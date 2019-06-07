<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Hello</router-link> |
      <router-link to="/callservice">Service</router-link> |
      <router-link to="/bootstrap">Bootstrap</router-link> |
      <router-link to="/user">User</router-link> |
      <router-link to="/login">Login</router-link> |
      <router-link to="/protected">Protected</router-link>
    </div>
    <router-view :hellomsg="msg"></router-view>
  </div>
</template>

<script>

export default {
  name: 'app',
  data () {
    return {
      msg: 'Welcome to your Vue.js powered Spring Boot App'
    }
  }
}
</script>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 20px;
}

#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
     color: #42b983;
    }
  }
}
</style>
