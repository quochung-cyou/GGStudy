import Axios from "axios";

import {API_URL} from "../config";
import {useNotificationStore} from "../stores/notifications";
import storage from "../utils/storage";
import {useAuth0} from "@auth0/auth0-react";



export const axios = Axios.create({
    baseURL: API_URL,
    headers: {
        "Content-Type": "application/json",
    },
    withCredentials: true,
});

axios.interceptors.request.use(
    function (config) {
        const token = storage.get("token");
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    function (error) {
        return Promise.reject(error);
    }
);

axios.interceptors.response.use(
    (response) => {
        return response.data;
    },
    (error) => {
        const { loginWithRedirect } = useAuth0();
        if (error.response?.status === 401) {
            loginWithRedirect();
        } else {
            const message = error.response?.data?.message || error.message;
            useNotificationStore.getState().addNotification({
                type: "error",
                title: "Error",
                message,
            });
        }
        return Promise.reject(error);
    }
);