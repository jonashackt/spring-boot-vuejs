<template>
  <div class="bootstrap">
    <h1>{{ msg }}</h1>
    <h5>REST service call are easy to do with Vue.js, if you know how to do it.</h5>
    <p></p>
    <h6><b-badge variant="primary"> Let´s go!</b-badge> Call a Spring Boot REST backend service, by clicking a button:</h6>
    <p></p>
    <b-btn variant="success" @click="callRestService(); showResponse=true" id="btnCallHello">/hello (GET)</b-btn>
    <p></p>
    <h4>Backend response: <b-alert :show="showResponse" dismissible @dismissed="showResponse=false">{{ response }}</b-alert></h4>

    <b-btn v-b-toggle.collapse1>Show Response details</b-btn>
    <p></p>
    <b-collapse id="collapse1" class="mt-2">
      <b-card>
        <p class="card-text">The Response hat this details</p>
        <b-btn v-b-toggle.collapse1_inner size="sm" variant="primary">HTTP Status</b-btn>
        <b-collapse id=collapse1_inner class="mt-2">
          <b-card>Status: {{ httpStatusCode }}</b-card>
          <b-card>Statustext: {{ httpStatusText }}</b-card>
        </b-collapse>

        <b-btn v-b-toggle.collapse2_inner size="sm" id="btnHttpHeaders" variant="warning">HTTP Headers</b-btn>
        <b-collapse id=collapse2_inner class="mt-2">

          <p v-if="headers && headers.length">
            <li v-for="header of headers">
            <b-card>Header: {{ header.valueOf() }}</b-card>
            </li>
          </p>
        </b-collapse>

        <b-btn v-b-toggle.collapse3_inner size="sm" variant="danger">Full Request configuration</b-btn>
        <b-collapse id=collapse3_inner class="mt-2">
          <p class="card-text">Config: {{ fullResponse.config }} </p>
        </b-collapse>
      </b-card>
    </b-collapse>


    <b-tooltip target="btnHttpHeaders" title="You should always know your HTTP Headers!"></b-tooltip>

  </div>
</template>

<script>
// import axios from 'axios'
import {AXIOS} from './http-common'

export default {
  name: 'bootstrap',

  data () {
    return {
      msg: 'HowTo call REST-Services:',
      showResponse: false,
      response: '',
      fullResponse: {
        config: {
          foo: '',
          bar: ''
        }
      },
      httpStatusCode: '',
      httpStatusText: '',
      headers: ['Noting here atm. Did you call the Service?'],
      errors: []
    }
  },
  methods: {
    // Fetches posts when the component is created.
    callRestService () {
      AXIOS.get(`/hello`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data
          console.log(response.data)
          this.httpStatusCode = response.status
          this.httpStatusText = response.statusText
          this.headers = response.headers
          this.fullResponse = response
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
}

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
p {
  margin-bottom: 20px;
}

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
