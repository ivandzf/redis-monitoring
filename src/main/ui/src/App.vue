<template>
  <div id="app" class="flyout">
    <navbar dark position="top" class="default-color" scrolling>
      <mdb-navbar-brand href="#/" style="font-weight: bolder;">
        Redis Monitoring
      </mdb-navbar-brand>
      <navbar-collapse>
        <navbar-nav right>
          <navbar-item router exact href="/" waves-fixed>Home</navbar-item>
        </navbar-nav>
      </navbar-collapse>
    </navbar>
    <main :style="{marginTop: '60px'}">
      <vue-snotify></vue-snotify>
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
  import {Footer, mdbNavbarBrand, Navbar, NavbarCollapse, NavbarItem, NavbarNav} from 'mdbvue';

  export default {
    name: 'App',
    components: {
      Navbar,
      NavbarItem,
      NavbarNav,
      NavbarCollapse,
      mdbNavbarBrand,
      Footer
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  .flyout {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    justify-content: space-between;
  }

  .active {
    background-color: rgba(255, 255, 255, 0.1);
  }
</style>

