import HttpRequest from "./index";
import { rejects } from "assert";

class Login extends HttpRequest {
  doLogin(data) {
    return new Promise((resolve, rejects) => {
      this.post("/login", data, this.setJsonContentType)
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
