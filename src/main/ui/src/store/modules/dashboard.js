import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

const state = {
  isBrokerConnected: false,
  info: {}
};

const getters = {
  isBrokerConnected(state) {
    return state.isBrokerConnected;
  },
  info(state) {
    return state.info;
  }
};

const mutations = {
  changeBrokerStatus(state) {
    state.isBrokerConnected = state.isBrokerConnected == true ? false : true;
  },
  info(state, payload) {
    state.info = payload;
  }
};

const actions = {
  connectMessageBroker(context) {
    this.socket = new SockJS(process.env.SOCKET_URL);
    this.stompClient = Stomp.over(this.socket);
    this.stompClient.hasDebug = false;
    return new Promise((resolve, reject) => {
      this.stompClient.connect(
        {},
        frame => {
          context.commit("changeBrokerStatus");
          this.stompClient.subscribe("/info", message => {
              console.log(JSON.parse(message.body));
          });
          resolve();
        },
        error => {
          reject(error);
        }
      );
    });
  },
  disconnectMessageBroker(context) {
    if (context.getters.isBrokerConnected) {
      this.stompClient.disconnect();
      context.commit("changeBrokerStatus");
    }
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
