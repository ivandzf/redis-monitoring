<template>
  <div id="app">
    <container>
      <p v-if="isConnected">{{ message }}</p>
      <btn color="blue" v-on:click.native="connect" :disabled="isConnected">Connect to Broker</btn>
      <btn
        color="red"
        v-on:click.native="disconnect"
        :disabled="!isConnected"
      >Disconnect from Broker</btn>
    </container>
    <br>
    <container>
      <row>
        <column sm="3">
          <card>
            <card-body>
              <card-title>Total Key</card-title>
              <card-text>
                <h3>{{ info.totalKey }}</h3>
              </card-text>
            </card-body>
          </card>
        </column>
        <column sm="3">
          <card>
            <card-body>
              <card-title>Memory Usage</card-title>
              <card-text>
                <h3>{{ info.ramUsage }}</h3>
              </card-text>
            </card-body>
          </card>
        </column>
        <column sm="3">
          <card>
            <card-body>
              <card-title>Client Connect</card-title>
              <card-text>
                <h3>{{ info.clientConnected }}</h3>
              </card-text>
            </card-body>
          </card>
        </column>
        <column sm="3">
          <card>
            <card-body>
              <card-title>Redis Uptime</card-title>
              <card-text>
                <h3>{{ info.upTime }}</h3>
              </card-text>
            </card-body>
          </card>
        </column>
      </row>
    </container>
  </div>
</template>

<script>
import timeUtils from "../utils/time-utils";
import {
  Btn,
  Card,
  CardBody,
  CardImg,
  CardText,
  CardTitle,
  Column,
  Container,
  Fa,
  MdMask,
  Row,
  ViewWrapper
} from "mdbvue";

export default {
  name: "RedisDashboard",
  components: {
    Btn,
    Row,
    Column,
    ViewWrapper,
    Card,
    MdMask,
    Container,
    CardImg,
    CardBody,
    CardText,
    CardTitle,
    Fa
  },
  data() {
    return {
      info: {
        totalKey: 0,
        ramUsage: 0,
        clientConnected: 0,
        upTime: 0
      },
      message: String
    };
  },
  computed: {
    isConnected() {
      return this.$store.getters.isBrokerConnected;
    }
  },
  methods: {
    connect() {
      this.message = "Connecting";
      this.$store.dispatch("connect");
    },
    disconnect() {
      return this.$store.dispatch("disconnect");
    }
  }
};
</script>

<style scoped>
.card-title {
  font-size: 16px;
}

h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

* {
  text-align: center;
}
</style>
