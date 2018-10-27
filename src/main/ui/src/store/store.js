import Vue from "vue";
import Vuex from "vuex";
import Dashboard from "./modules/dashboard"
import Login from "./modules/login"

Vue.use(Vuex);

export const store = new Vuex.Store({
    state: {
        // main state
    },
    getters: {
        // main getters
    },
    mutations: {
        // main mutations
    },
    actions: {
        // main actions
    },
    modules: {
        dashboard: Dashboard,
        login: Login
    }
});