<template>
  <div class="service">
    <h1>{{ msg }}</h1>
    <h2>REST service call results</h2>

    <button @click="callHelloApi()">CALL Spring Boot REST backend service</button>

    <h4>Backend response: {{ backendResponse }}</h4>

  </div>
</template>

<script>
  import api from "./backend-api";

  export default {
    name: 'service',

    data () {
      return {
        msg: 'HowTo call REST-Services:',
        backendResponse: [],
        errors: []
      }
    },
    methods: {
      // Fetches posts when the component is created.
      callHelloApi () {
        api.hello().then(response => {
            this.backendResponse = response.data;
            console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        })
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
