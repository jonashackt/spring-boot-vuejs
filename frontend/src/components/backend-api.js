import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeou: 1000
});

export function hello() {
    return AXIOS.get(`/hello`);
}

export function getUser(userId) {
  return AXIOS.get(`/user/` + userId);
}

export function createUser(firstName, lastName) {
    return AXIOS.post(`/user/` + firstName + '/' + lastName);
}
