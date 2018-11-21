import Vue from "vue";
import App from "./App";
import router from "./router";
import "bootstrap-css-only/css/bootstrap.min.css";
import "../build/css/mdb.css";
import { store } from "./store/store";
import Notification from "./plugin/notification";
import VeeValidate from "vee-validate";

Vue.config.productionTip = false;

Vue.use(VeeValidate);
Vue.use(Notification);

new Vue({
  el: "#app",
  router: router,
  store: store,
  components: { App },
  template: "<App/>"
});
