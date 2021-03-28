import axios from 'axios';
import queryString from 'query-string';
import CONFIG from '../config'

const http = axios.create({
  baseURL: CONFIG.http.baseURL,
  headers: {
    'content-type': 'application/json',
  },
  paramsSerializer: params => queryString.stringify(params),
});

http.interceptors.request.use(async (config) => {
  // Handle token here ...
  const token = localStorage.getItem('token');
  config.headers.Authorization = token ? `Bearer ${token}` : '';
  return config;
});

http.interceptors.response.use((response) => {
  if (response && response.data) {
    return response.data;
  }
  return response;
}, (error) => {
  // TODO: handle error here
  throw error;
});


export default http;

