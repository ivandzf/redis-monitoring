import Vue from "vue";
import Snotify from "vue-snotify";
import "vue-snotify/styles/material.css";

const Notification = {
    install(Vue, options) {
        Vue.use(Snotify);

        Vue.prototype.$notification = (type, message) => {
            if (type === "success") {
                Vue.prototype.$snotify.success(message);
            } else if (type === "warning") {
                Vue.prototype.$snotify.warning(message);
            } else if (type === "danger") {
                Vue.prototype.$snotify.error(message);
            }
        }
    }
}

export default Notification