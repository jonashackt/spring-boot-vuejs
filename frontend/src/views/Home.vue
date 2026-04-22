<template>
  <div class="home">
    <img alt="Vue with Spring logo" src="../assets/spring-boot-vuejs-logo.png">
    <HelloSpringWorld/>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import HelloSpringWorld from '@/components/HelloSpringWorld.vue';

export default defineComponent({
  name: 'Home',
  components: {
    HelloSpringWorld,
  },
});
</script>
