import http from '../utils/http';
import api from '../api';

export function loginSerice(data) {
    return http.post(api.login, data);
}