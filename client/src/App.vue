<template>
  <div id="bandmate-app">
    <header>
      <div id="header-top">
        <div id="logo-container">
          <img src="./assets/simpleBandIcon.webp" alt="BANDMATE" />
          <h1>BANDMATE</h1>
        </div>
        <nav>
          <router-link v-bind:to="{ name: 'home' }">
            <button>Home</button>
          </router-link>

          <router-link v-bind:to="{ name: 'artists' }">
            <button>Artists</button>
          </router-link>

          <router-link v-bind:to="{ name: 'talents' }">
            <button>Talents</button>
          </router-link>

          <!-- Admin link visible only to admin users -->
          <router-link v-if="$store.state.user.role === 'ROLE_ADMIN'" v-bind:to="{ name: 'admin' }">
            <button>Admin</button>
          </router-link>

          <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">
            <button>Logout</button>
          </router-link>
          <router-link v-bind:to="{ name: 'login' }" v-else>
            <button>Login</button>
          </router-link>
        </nav>
      </div>

    
    </header>

    <main>
      <router-view />
    </main>

    <footer>&copy; 2024 Bandmate. All Rights Reserved.</footer>
  </div>
</template>

<script>
export default {
  computed: {
    isAdmin() {
      // Check if the user role is ROLE_ADMIN
      return this.$store.state.user.role === 'ROLE_ADMIN'; 
    }
  }
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Arial', sans-serif;
  background-color: #f4f4f4;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

#bandmate-app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

/* Header Styling */
header {
  background-color: #222;
  color: white;
  padding: 20px 0;
}

#header-top {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

#logo-container {
  display: flex;
  align-items: center;
}

#logo-container img {
  width: 50px;
  margin-right: 15px;
}

#logo-container h1 {
  font-size: 2rem;
}

nav {
  display: flex;
  gap: 20px;
}

nav button {
  background-color: #333;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

nav button:hover {
  background-color: #f39c12;
}

form {
  margin-top: 10px;
  text-align: center;
}

#filter-input {
  width: 250px;
  padding: 8px;
  border: 2px solid #333;
  border-radius: 5px;
}

/* Main Content */
main {
  flex-grow: 1;
  padding: 20px;
  background-color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* Footer Styling */
footer {
  background-color: #222;
  color: white;
  text-align: center;
  padding: 10px 0;
  width: 100%;
}
</style>
