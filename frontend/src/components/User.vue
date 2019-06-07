<template>
  <div class="user">
    <h1>Create User</h1>

    <h3>Just some database interaction...</h3>

    <input type="text" v-model="user.firstName" placeholder="first name">
    <input type="text" v-model="user.lastName" placeholder="last name">

    <button @click="createNewUser()">Create User</button>

    <div v-if="showResponse"><h6>User created with Id: {{ response }}</h6></div>

    <button v-if="showResponse" @click="retrieveUser()">Retrieve user {{user.id}} data from database</button>

    <h4 v-if="showRetrievedUser">Retrieved User {{retrievedUser.firstName}} {{retrievedUser.lastName}}</h4>

  </div>
</template>

<script>
  import api from "./backend-api";

  export default {
    name: 'user',

    data () {
      return {
        response: [],
        errors: [],
        user: {
          lastName: '',
          firstName: '',
          id: 0
        },
        showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createNewUser () {

        api.createUser(this.user.firstName, this.user.lastName).then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data;
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
          .catch(e => {
            this.errors.push(e)
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
