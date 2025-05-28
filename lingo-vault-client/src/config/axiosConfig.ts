import axios from "axios";

const axiosClient = axios.create({
  baseURL: process.env.LINGO_VAULT_API,
  headers: {
    "Content-Type": "application/json",
  },
});

// Request Interceptor: Gắn token vào Header
axiosClient.interceptors.request.use(
  (config) => {
    config.withCredentials = true;
    return config;
  },
  (error) => Promise.reject(error)
);

// Response Interceptor: Xử lý lỗi chung
axiosClient.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response) {
      if (error.response.status === 500) {
        window.location.href = "/error";
      } else if (error.response.status === 403) {
        window.location.href = "/login";
      }
    }
    return Promise.reject(error);
  }
);

export default axiosClient;
