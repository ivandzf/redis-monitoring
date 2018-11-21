import Vue from "vue";
import Vuex from "vuex";
import Dashboard from "./modules/dashboard";
import Login from "./modules/login";
import Cookies from "js-cookie";

Vue.use(Vuex);

const state = {
    user: Cookies.get('secret')
};

const getters = {
  isLoggedIn(state) {
    return state.user !== null;
  }
};

const mutations = {
  retrieveUser(state, user) {
    state.user = user;
  }
};

const actions = {
  
};

const modules = {
  dashboard: Dashboard,
  login: Login
};

export const store = new Vuex.Store({
  state,
  getters,
  mutations,
  actions,
  modules
});
