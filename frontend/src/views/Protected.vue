<template>
  <div>
    <h1><b-badge variant="success">YEAH you made it!</b-badge></h1>
    <h5>If you're able to read this, you've successfully logged in and redirected to this protected site :)</h5>

    <b-btn variant="primary" @click="getSecuredTextFromBackend()">Call the secured API</b-btn>
    <p></p>

    <div v-if="securedApiCallSuccess">
      <b-badge variant="success">API call</b-badge> Full response: {{ backendResponse }} <b-badge variant="success">successful</b-badge>
    </div>
    <div v-if="errors">
      <b-badge variant="warning">API call</b-badge> {{ errors }} <b-badge variant="warning">NOT successful</b-badge>
    </div>
  </div>

</template>

<script lang="ts">
import { defineComponent } from 'vue';
import api from '../api/backend-api'
import store from '../store'
import {AxiosError} from "axios";

interface State {
  backendResponse: string;
  securedApiCallSuccess: boolean,
  errors: AxiosError[]
}

export default defineComponent({
  name: 'Protected',

  data: (): State => {
    return {
      backendResponse: '',
      securedApiCallSuccess: false,
      errors: []
    }
  },
  methods: {
    getSecuredTextFromBackend() {
      api.getSecured(store.getters.getUserName, store.getters.getUserPass)
          .then(response => {
            console.log("Response: '" + response.data + "' with Statuscode " + response.status);
            this.securedApiCallSuccess = true;
            this.backendResponse = response.data;
          })
          .catch((error: AxiosError) => {
            console.log("Error: " + error);
            this.errors.push(error);
          })
    }
  }
});
</script>

