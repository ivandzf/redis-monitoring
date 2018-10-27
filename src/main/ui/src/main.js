import Vue from "vue";
import App from "./App";
import router from "./router";
import "bootstrap-css-only/css/bootstrap.min.css";
import "../build/css/mdb.css";
import { store } from "./store/store";
import displayNotification from "./utils/notification-utils";

Vue.config.productionTip = false;

Vue.mixin({
  methods: {
    displayNotification
  }
})

new Vue({
  el: "#app",
  router: router,
  store: store,
  components: { App },
  template: "<App/>"
});
