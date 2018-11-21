import Login from "../../api/login";
import Vue from "vue";
import Cookies from "js-cookie";

const state = {};

const getters = {};

const mutations = {};

const actions = {
  login(context, data) {
    return new Promise((resolve, reject) => {
      Login.doLogin(data)
        .then(response => {
          Cookies.set('secret', response.data);
          context.commit('retrieveUser', response.data)
          resolve(response.data)
        })
        .catch(error => {
          error.status === 401
            ? Vue.prototype.$notification("danger", "Username or Password wrong.")
            : Vue.prototype.$notification("danger", "Oppsss can't login");
          reject(error)
        });
    })
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
