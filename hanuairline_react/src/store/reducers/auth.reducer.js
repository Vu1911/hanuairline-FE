import {
    USER_LOADED,
    AUTH_ERROR,
    LOGIN_FAIL,
    LOGIN_SUCCESS,
    LOGOUT,
  } from '../constants';

const initialState = {
    token: localStorage.getItem('token'),
    isAuthenticated: null,
    loading: true,
    user: null,
}

const authReducer = (state = initialState, action) => {
    const { type, payload } = action;
    switch (type) {
        case USER_LOADED:
          return {
            ...state,
            isAuthenticated: true,
            loading: false,
            user: payload,
          };
        case LOGIN_SUCCESS:
          localStorage.setItem("token", payload);
          return {
            ...state,
            token: payload,
            user: payload,
            isAuthenticated: true,
            loading: false,
          };
        case AUTH_ERROR:
        case LOGIN_FAIL:
        case LOGOUT:
          localStorage.removeItem("token");
          return {
            ...state,
            user: null,
            token: null,
            isAuthenticated: false,
            loading: false,
          };
        default:
          return state;
      }
}

export default authReducer;