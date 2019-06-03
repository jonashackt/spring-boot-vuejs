<template>
  <div class="protected">
    <h1><b-badge variant="danger">This Site is protected!</b-badge></h1>
    <h5>If you're able to read this, you've successfully logged in.</h5>

  </div>
</template>

<script>
import {hello} from './backend-api'

export default {
  name: 'protected',

  data () {
    return {
      showResponse: false,
      backendResponse: '',
      errors: []
    }
  },
  methods: {

  }
}

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
