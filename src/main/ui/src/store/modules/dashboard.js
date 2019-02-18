import fromEventSource from "../../api/dashboard";

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
  connect(context) {
    context.commit("changeBrokerStatus");
    const eventSource = new EventSource(process.env.SSE_URL);
    eventSource.onopen
    // fromEventSource().subscribe(val => console.log(val));
  },
  disconnect(context) {
    if (context.getters.isBrokerConnected) {
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
