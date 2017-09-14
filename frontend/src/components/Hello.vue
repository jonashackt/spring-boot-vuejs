<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Sources</h2>
    <ul>
      <li><a href="https://github.com/jonashackt/spring-boot-vuejs" target="_blank">github.com/jonashackt/spring-boot-vuejs</a></li>
    </ul>
    <h2>REST service call results</h2>

    <button class=”Search__button” @click="callRestService()">CALL Spring Boot REST backend service</button>

    <h3>{{ response }}</h3>

  </div>
</template>

<script>
// import axios from 'axios'
import {AXIOS} from './http-common'

export default {
  name: 'hello',

  data () {
    return {
      msg: 'Welcome to your Vue.js powered Spring Boot App',
      response: [],
      errors: []
    }
  },
  methods: {
    // Fetches posts when the component is created.
    callRestService () {
      AXIOS.get(`api/hello`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
      // async / await version (created() becomes async created())
//       try {
//         const response = await axios.get(`http://localhost:8088/hello`)
//         this.posts = response.data
//       } catch (e) {
//         this.errors.push(e)
//       }
    // }
  // }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
