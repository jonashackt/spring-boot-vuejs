<template>
  <div class="user">
    <h1>Create User</h1>

    <h3>Just some database interaction...</h3>

    <input type="text" v-model="user.firstName" placeholder="first name">
    <input type="text" v-model="user.lastName" placeholder="last name">

    <button @click="createNewUser()">Create User</button>

    <div v-if="showResponse"><h6>User created with Id: {{ user.id }}</h6></div>

    <button v-if="showResponse" @click="retrieveUser()">Retrieve user {{user.id}} data from database</button>

    <h4 v-if="showRetrievedUser">Retrieved User {{retrievedUser.firstName}} {{retrievedUser.lastName}}</h4>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import api from "../api/backend-api";
import {AxiosError} from "axios";

interface State {
  user: {
    id: number
    firstName: string,
    lastName: string;
  };
  retrievedUser: {
    id: number
    firstName: string,
    lastName: string;
  };
  showResponse: boolean;
  showRetrievedUser: boolean;
  errors: AxiosError[]
}

export default defineComponent({
  name: 'User',

  data: (): State => {
    return {
      errors: [],
      user: {
        id: 0,
        firstName: '',
        lastName: ''
      },
      showResponse: false,
      retrievedUser: {
        id: 0,
        firstName: '',
        lastName: ''
      },
      showRetrievedUser: false
    }
  },
  methods: {
    // Fetches posts when the view is created.
    createNewUser () {
      api.createUser(this.user.firstName, this.user.lastName).then(response => {
          // JSON responses are automatically parsed.
          this.user.id = response.data;
          console.log('Created new User with Id ' + response.data);
          this.showResponse = true
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    retrieveUser () {
      api.getUser(this.user.id).then(response => {
          // JSON responses are automatically parsed.
          this.retrievedUser = response.data;
          this.showRetrievedUser = true
        })
        .catch((error: AxiosError):void => {
          this.errors.push(error)
        })
    }
  }
});
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
