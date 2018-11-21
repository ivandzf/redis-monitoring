import Vue from "vue";
import axios from "axios";
import Cookies from "js-cookie";

let axiosInstance = axios.create({
  baseURL: process.env.API_URL,
  timeout: 120000
});

axiosInstance.interceptors.request.use(
  function(config) {
    if (process.env.NODE_ENV === "production")
      config.headers["XSRF-TOKEN"] = Cookies.get("XSRF-TOKEN");
    return config;
  },
  function(error) {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  function(response) {
    return response;
  },
  function(error) {
    if (error.response) {
      return Promise.reject(error.response);
    } else {
      return Promise.reject(error.response.status=500)
    }
  }
);

class HttpRequest {
  jsonContentHeader() {
    return {
      "Content-Type": "application/json"
    };
  }

  setHeader() {
    let headers = {};
    for (let i = 0; i < arguments.length; i++)
      headers[arguments[i].key] = headers[arguments[i].value];

    return {
      header: headers
    };
  }

  get(methodName, data) {
    return axiosInstance.get(methodName, {
      params: data
    });
  }

  post(methodName, data, config) {
    return axiosInstance.post(methodName, data, config);
  }

  update(methodName, data) {
    return axiosInstance.put(methodName, data, config);
  }

  delete(methodName, id) {
    return axiosInstance.delete(methodName, { params: { id: id } });
  }
}

export default HttpRequest;
