<template>
  <div class="home">
    <img alt="Vue with Spring logo" src="../assets/spring-boot-vuejs-logo.png">
    <HelloSpringWorld :hellomsg="hellomsg"/>
    <div class="refresh-section">
      <button class="btn btn-primary" @click="refreshData" :disabled="loading">
        {{ loading ? '加载中...' : '刷新数据' }}
      </button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import HelloSpringWorld from '@/components/HelloSpringWorld.vue'; // @ is an alias to /src
import api from '@/api/backend-api';

export default defineComponent({
  name: 'Home',
  components: {
    HelloSpringWorld,
  },
  data() {
    return {
      hellomsg: 'Welcome to your Vue.js (+ TypeScript) powered Spring Boot App',
      loading: false
    };
  },
  mounted() {
    this.refreshData();
  },
  methods: {
    async refreshData() {
      if (this.loading) return;
      
      this.loading = true;
      try {
        const response = await api.hello();
        this.hellomsg = response.data;
      } catch (error) {
        console.error('Failed to refresh data:', error);
        this.hellomsg = '获取数据失败，请稍后重试';
      } finally {
        this.loading = false;
      }
    }
  }
});
</script>

<style scoped>
.refresh-section {
  margin-top: 20px;
}
</style>
