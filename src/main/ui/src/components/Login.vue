<template>
  <container>
    <div class="d-flex justify-content-center">
      <card class="w-50">
        <card-body>
          <card-title class="text-center">Login</card-title>
          <form>
            <div class="grey-text">
              <mdb-input label="Username" icon="user" type="text"/>
              <mdb-input label="Password" icon="lock" type="password"/>
            </div>
            <div class="text-center">
              <btn v-on:click.native="login">Login</btn>
            </div>
          </form>
        </card-body>
      </card>
    </div>
  </container>
</template>

<script>
  import {Btn, Card, CardBody, CardTitle, Container, mdbInput} from 'mdbvue';

  export default {
    name: 'Login',
    components: {
      Container,
      Card,
      CardBody,
      CardTitle,
      Btn,
      mdbInput
    },
    methods: {
      login() {
        console.log('aa')
        this.$store.dispatch('login')
      }
    }
  };
</script>

<style scoped>
.d-flex {
  margin-top: 50px;
}
</style>
