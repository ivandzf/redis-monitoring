import axios from 'axios'

let axiosInstance = axios.create({
    baseURL: process.env.API_URL,
    timeout: 120000
})

axiosInstance.interceptors.request.use(function (config) {
    return config
}, function (error) {
    return Promise.reject(error)
})

axiosInstance.interceptors.response.use(function (response) {
    return response
}, function (error) {
    return Promise.reject(error)
})

class HttpRequest {
    constructor() {
        this.axios = axios
    }

    setHeader(header) {
        axiosInstance.defaults.headers.common[header.key] = header.value
    }

    get(methodName, data) {
        return axiosInstance.get(methodName, {
            params: data
        })
    }

    post(methodName, data) {
        return axiosInstance.post(methodName, data)
    }

    update(methodName, data) {
        return axiosInstance.put(methodName, data)
    }

    delete(methodName, id) {
        return axiosInstance.delete(methodName, { params: { id: id } })
    }

    request(type, url, data) {
        let promise = null
        switch (type) {
            case 'GET': promise = axios.get(url, { params: data }); break
            case 'POST': promise = axios.post(url, data); break
            case 'PUT': promise = axios.put(url, data); break
            case 'DELETE': promise = axios.delete(url, data); break
            default: promise = axios.get(url, { params: data }); break
        }
        return promise
    }
}

export default HttpRequest