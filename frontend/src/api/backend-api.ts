import axios, {AxiosResponse} from 'axios'

const axiosApi = axios.create({
    baseURL: `/api`,
    timeout: 1000,
    headers: { 'Content-Type': 'application/json' }
});

interface User {
    id: number;
    firstName: string;
    lastName: string;
}

export default {
    hello(): Promise<AxiosResponse<string>> {
        return axiosApi.get(`/hello`);
    },
    getUser(userId: number): Promise<AxiosResponse<User>> {
        return axiosApi.get(`/user/` + userId);
    },
    createUser(firstName: string, lastName: string): Promise<AxiosResponse<number>> {
        return axiosApi.post(`/user/` + firstName + '/' + lastName);
    },
    getSecured(user: string, password: string): Promise<AxiosResponse<string>> {
        return axiosApi.get(`/secured/`,{
            auth: {
                username: user,
                password: password
            }});
    }
}


