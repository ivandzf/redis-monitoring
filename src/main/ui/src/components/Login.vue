<template>
  <container>
    <div class="d-flex justify-content-center">
      <card class="w-50">
        <card-body>
          <card-title class="text-center">Login Monitoring</card-title>
          <hr/>
          <form class="need-validation" novalidate="true" @submit.prevent="login">
              <div class="form-group" :class="{'has-error': errors.has('username') }" >
                <label class="control-label" for="username">Username</label>
                <input v-model="username" name="username" v-validate="'required'" class="form-control" type="text" required>
                <p class="text-danger text-validate" v-if="errors.has('username')">Username cannot be empty.</p>
              </div>
              <div class="form-group" :class="{'has-error': errors.has('password') }" >
                <label class="control-label" for="password">Password</label>
                <input v-model="password" name="password" v-validate="'required'" class="form-control" type="password" required>
                <p class="text-danger text-validate" v-if="errors.has('password')">Password cannot be empty.</p>
              </div>
            <div class="text-center">
              <btn color="blue">Login</btn>
            </div>
          </form>
        </card-body>
      </card>
    </div>
  </container>
</template>

<script>
  import {Btn, Card, CardBody, CardTitle, Container} from 'mdbvue';

  export default {
    name: 'Login',
    data() {
      return {
      username: '',
      password: ''
      }
    },
    components: {
      Container,
      Card,
      CardBody,
      CardTitle,
      Btn
    },
    methods: {
      login(event) {
        this.$validator.validate().then(result => {
          event.target.classList.add('was-validated');
          if (result) {
            this.$store.dispatch('login', {
                username: this.username,
                password: this.password
            })
            .then(response => {
              this.$router.push({name: 'RedisDashboard'})
            })
          }
        });
      }
    }
  };
</script>

<style scoped>
.d-flex {
  margin-top: 50px;
}

.text-validate {
  margin-top: 2px;
  font-size: 13px;
}
</style>
