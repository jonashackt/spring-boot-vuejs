<template>
  <div class="service">
    <h1>{{ msg }}</h1>
    <h2>REST service call results</h2>

    <button class=”Search__button” @click="callRestService()">CALL Spring Boot REST backend service</button>

    <h3>{{ response }}</h3>

    <button class=”Search__button” @click="callRestServiceAsync()">Make an ASYNC Call to Spring Boot REST backend service</button>

    <h3>{{ asyncresponse }}</h3>

  </div>
</template>

<script>
// import axios from 'axios'
import {AXIOS} from './http-common'

export default {
  name: 'service',

  data () {
    return {
      msg: 'HowTo call REST-Services:',
      response: [],
      errors: [],
      asyncresponse: []
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
    },
    // async / await version (created() becomes async created())
    callRestServiceAsync: async function () {
      // JSON responses are automatically parsed.
      try {
        const response = await AXIOS.get(`api/hello`)
        this.asyncresponse = response.data
      } catch (e) {
        this.errors.push(e)
      }
    }
  }
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
