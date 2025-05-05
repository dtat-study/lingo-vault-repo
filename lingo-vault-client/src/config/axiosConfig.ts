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
    return config;
  },
  (error) => Promise.reject(error)
);

// Response Interceptor: Xử lý lỗi chung
axiosClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
    }
    return Promise.reject(error);
  }
);

export default axiosClient;
