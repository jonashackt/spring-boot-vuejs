<template>
  <div class="home">
    <img alt="Vue with Spring logo" src="../assets/spring-boot-vuejs-logo.png">
    <HelloSpringWorld hellomsg="Welcome to your Vue.js powered Spring Boot App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloSpringWorld from '@/components/HelloSpringWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloSpringWorld
  }
}
</script>
