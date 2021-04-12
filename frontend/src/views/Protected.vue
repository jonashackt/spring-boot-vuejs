<template>
  <div>
    <h1><span class="badge bg-success">YEAH you made it!</span></h1>
    <h5>If you're able to read this, you've successfully logged in and redirected to this protected site :)</h5>

    <button class="btn btn-primary" @click="getSecuredTextFromBackend()">Call the secured API</button>
    <p></p>

    <div v-if="securedApiCallSuccess">
      <span class="badge bg-success">API call</span> Full response: {{ backendResponse }} <span class="badge bg-success">successful</span>
    </div>
    <div v-if="errors">
      <span class="badge bg-warning">API call</span> {{ errors }} <span class="badge bg-warning">NOT successful</span>
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

