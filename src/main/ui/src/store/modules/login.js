import Login from "../../api/login";

const state = {};

const getters = {};

const mutations = {};

const actions = {
  login(context, data) {
    Login.doLogin(data)
      .then(response => {
        console.log(response);
      })
      .catch(error => {
        console.log(error);
      });
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
