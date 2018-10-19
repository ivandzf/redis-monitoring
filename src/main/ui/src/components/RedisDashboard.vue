<template>
  <div id="app">
    <container>
      <br>
      <p v-if="isConnected">We're connected to the server!</p>
      <btn color="default" v-on:click.native="connect">Connect to Broker</btn>
      <btn color="danger" v-on:click.native="disconnect">Disconnect from Broker</btn>
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
        upTime: 0
      }
    },
    created() {
      // TODO set placeholder ui
      console.log(this.socketUrl)
    },
    methods: {
      displayNotification(type, message) {
        if (type === 'success') {
          this.$snotify.success(message);
        } else if (type === 'warning') {
          this.$snotify.warning(message);
        } else if (type === 'danger') {
          this.$snotify.error(message);
        }
      },
      connect() {
        this.socket = new SockJS(this.socketUrl);
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.hasDebug = false;
        this.stompClient.connect({}, (frame) => {
          this.isConnected = true;
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
          })
        }, (error) => {
          this.displayNotification('danger', error.reason);
          this.isConnected = false;
          this.totalKey = 0;
          this.ramUsage = 0;
          this.clientConnect = 0;
          this.upTime = 0;
        })
      },
      disconnect() {
        if (this.stompClient) {
          this.stompClient.disconnect()
        }
        this.isConnected = false;
        this.items = [];
        this.totalKey = 0;
        this.ramUsage = 0;
        this.clientConnect = 0;
        this.upTime = 0;
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
