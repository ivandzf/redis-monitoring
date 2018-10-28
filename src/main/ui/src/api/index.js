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

    setJsonContentType() {
        return {
            headers: {
                "Content-Type": "application/json"
            }
        }
    }

    setHeader(header) {
        axiosInstance.defaults.headers.common[header.key] = header.value
    }

    get(methodName, data) {
        return axiosInstance.get(methodName, {
            params: data
        })
    }

    post(methodName, data, config) {
        return axiosInstance.post(methodName, data, config)
    }

    update(methodName, data) {
        return axiosInstance.put(methodName, data)
    }

    delete(methodName, id) {
        return axiosInstance.delete(methodName, { params: { id: id } })
    }

}

export default HttpRequest