<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand">MAIN</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/list-note" class="nav-link">NOTE LIST</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add-basic-note" class="nav-link">CREATE BASIC NOTE</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add-image-note" class="nav-link">CREATE IMAGE NOTE</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add-checkbox-note" class="nav-link">CREATE CHECKBOX NOTE</router-link>
        </li>
      </div>
    </nav>
    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>
<script>
export default {
  name: "app"
};
</script>