import axios from 'axios';
const api=axios.create({baseURL:'/api'});api.interceptors.request.use(c=>{const token=localStorage.getItem('sw_token');if(token)c.headers.Authorization=`Bearer ${token}`;return c;});
export default api;
