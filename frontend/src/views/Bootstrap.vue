<template>
  <div class="bootstrap">
    <h1>{{ msg }}</h1>
    <h5>REST service call are easy to do with Vue.js, if you know how to do it.</h5>
    <p></p>
    <h6><span class="badge bg-primary"> Let´s go!</span> Call a Spring Boot REST backend service, by clicking a button:</h6>
    <p></p>
      <button class="btn btn-success" @click="callHelloApi()" id="btnCallHello">/hello (GET)</button>
      <p></p>
    <h4>Backend response: <span class="alert alert-primary" role="alert" :show="showResponse" dismissible @dismissed="showResponse=false">{{ backendResponse }}</span></h4>

    <button class="btn btn-secondary" data-bs-toggle="collapse" data-bs-target="#collapseOuter">Show Response details</button>
    <p></p>
    <div class="collapse" id="collapseOuter">
      <div class="card card-body">
        The Response hat this details
        <button class="btn btn-primary" data-bs-toggle="collapse" data-bs-target="#collapseInnerStatusCode">HTTP Status</button>

        <div class="collapse" id="collapseInnerStatusCode">
          <div class="card card-body">Status: {{ httpStatusCode }}</div>
          <div class="card card-body">Statustext: {{ httpStatusText }}</div>
        </div>

        <button class="btn btn-warning" data-bs-toggle="collapse" data-bs-target="#collapseInnerHeaders">HTTP Headers</button>
        <div class="collapse" id="collapseInnerHeaders">
          <p v-if="headers && headers.length">
          <li v-for="header of headers">
            <div class="card card-body">Header: {{ header.valueOf() }}</div>
          </li>
          </p>
        </div>

        <button class="btn btn-danger" data-bs-toggle="collapse" data-bs-target="#collapseInnerResponseConfig">Full Request configuration</button>
        <div class="collapse" id="collapseInnerResponseConfig">
          <div class="card card-body">Config: {{ responseConfig }} </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import api from '../api/backend-api'
import {AxiosError, AxiosRequestConfig} from "axios";

interface State {
  msg: string;
  showResponse: boolean;
  backendResponse: string;
  responseConfig: any;
  httpStatusCode: number;
  httpStatusText: string;
  headers: string[];
  errors: AxiosError[]
}

export default defineComponent({
  name: 'Bootstrap',

  data: (): State => {
    return {
      msg: 'Nice Bootstrap candy!',
      showResponse: false,
      backendResponse: '',
      responseConfig: '',
      httpStatusCode: 0,
      httpStatusText: '',
      headers: ['Noting here atm. Did you call the Service?'],
      errors: []
    }
  },
  methods: {
    callHelloApi (): any {
        api.hello().then(response => {
          this.backendResponse = response.data;
          this.httpStatusCode = response.status;
          this.httpStatusText = response.statusText;
          this.headers = response.headers;
          this.responseConfig = response.config;
          this.showResponse=true
        })
        .catch((error: AxiosError) => {
          this.errors.push(error)
        })
    }
  }
});

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
