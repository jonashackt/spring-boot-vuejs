import axios from 'axios'

export const AXIOS = axios.create({
  baseURL: `http://localhost:` + process.env.API_PORT
})
