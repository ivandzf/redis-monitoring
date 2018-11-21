import HttpRequest from "./index";

class Login extends HttpRequest {
  doLogin(data) {
    return new Promise((resolve, rejects) => {
      this.post("/login", data, this.setHeader(this.jsonContentHeader))
        .then(response => {
          resolve(response);
        })
        .catch(error => {
          rejects(error);
        });
    });
  }
}

export default new Login();
