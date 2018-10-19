<template>
  <div id="app">
    <container>
      <br>
      <p v-if="isConnected">{{ statusBroker }}</p>
      <btn color="default" v-on:click.native="connect" :disabled="!isDisable">Connect to Broker</btn>
      <btn color="danger" v-on:click.native="disconnect" :disabled="isDisable">Disconnect from Broker</btn>
    </container>
    <br>
    <container>
      <row>
        <column sm="3">
          <card>
            <card-body>
              <card-title>Total Key</card-title>
              <card-text>
                <h2>{{ totalKey }}</h2>
              </card-text>
            </card-body>
          </card>
        </column>
        <column sm="3">
          <card>
            <card-body>
              <card-title>Memory Usage</card-title>
              <card-text>
                <h2>{{ ramUsage }}</h2>
              </card-text>
            </card-body>
          </card>
        </column>
        <column sm="3">
          <card>
            <card-body>
              <card-title>Client Connect</card-title>
              <card-text>
                <h2>{{ clientConnect }}</h2>
              </card-text>
            </card-body>
          </card>
        </column>
        <column sm="3">
          <card>
            <card-body>
              <card-title>Uptime</card-title>
              <card-text>
                <h2>{{ upTime }}</h2>
              </card-text>
            </card-body>
          </card>
        </column>
      </row>
    </container>
  </div>
</template>

<script>
  import SockJS from 'sockjs-client'
  import Stomp from 'webstomp-client'
  import alert from '../utils/alert-utils'
  import timeUtils from '../utils/time-utils'
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
  } from 'mdbvue'

  export default {
    name: 'RedisDashboard',
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
        isConnected: false,
        msg: 'Redis Monitoring',
        items: [],
        socketUrl: process.env.SOCKET_URL,
        totalKey: 0,
        ramUsage: 0,
        clientConnect: 0,
        upTime: 0,
        statusBroker: ''
      }
    },
    created() {
      // TODO set placeholder ui
      console.log(this.socketUrl)
    },
    computed: {
      isDisable() {
        return !this.isConnected;
      }
    },
    methods: {
      socketConnect() {
        this.socket = new SockJS(this.socketUrl);
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.hasDebug = false;
        this.isConnected = true;
        this.statusBroker = 'Connecting';
        this.stompClient.connect({}, (frame) => {
          this.stompClient.subscribe('/redis', (message) => {
            let body = JSON.parse(message.body);
            this.items.push(body);
            let total = 0;
            for (let key in body.data.Keyspace) {
              total += parseInt(body.data.Keyspace[key].keys);
            }
            this.totalKey = total;
            this.ramUsage = body.data.Memory.used_memory_human;
            this.clientConnect = body.data.Clients.connected_clients;
            this.upTime = timeUtils.fancyTimeFormat(body.data.Server.uptime_in_seconds);
            this.statusBroker = 'We\'re connected to the server!';
          })
        }, (error) => {
          alert('danger', error.reason);
          this.isConnected = false;
          this.totalKey = 0;
          this.ramUsage = 0;
          this.clientConnect = 0;
          this.upTime = 0;
        })
      },
      socketDisconnect() {
        if (this.stompClient) {
          this.stompClient.disconnect()
        }
        this.isConnected = false;
        this.items = [];
        this.totalKey = 0;
        this.ramUsage = 0;
        this.clientConnect = 0;
        this.upTime = 0;
      },
      connect() {
        if (!this.isConnected) {
          this.socketConnect();
        }
      },
      disconnect() {
        if (this.isConnected) {
          this.socketDisconnect();
        }
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
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
