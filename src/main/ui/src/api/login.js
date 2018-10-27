import HttpRequest from './index'

class Login extends HttpRequest {
    doLogin(data) {
        this.setHeader({
            key: 'ContentType',
            value: 'application/xxx-formurl-encode'
        })
        return this.post('/login');
    }
}

export default new Login()