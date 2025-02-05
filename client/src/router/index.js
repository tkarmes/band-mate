import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import RegisterView from '../views/RegisterView.vue'
import ArtistsView from '../views/ArtistsView.vue'
import TalentsView from '../views/TalentsView.vue'
import AdminView from '../views/AdminView.vue'



/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: LogoutView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/artists",
      name: "artists",
      component: ArtistsView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/talents",
      name: "talents",
      component: TalentsView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/admin",
      name: "admin",
      component: AdminView,
      meta: {
        requiresAuth: true,
        role: "ROLE_ADMIN" // Ensure this route is only accessible by admin users
      }
    }
  ];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

// router.beforeEach((to) => {

//   // Get the Vuex store
//   const store = useStore();

//   // Determine if the route requires Authentication
//   const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

//   // If it does and they are not logged in, send the user to "/login"
//   if (requiresAuth && store.state.token === '') {
//     return {name: "login"};
//   }
//   // Otherwise, do nothing and they'll go to their next destination
// });

router.beforeEach((to, from, next) => {
  const store = useStore();
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
  const role = to.meta.role;

  if (requiresAuth && store.state.token === '') {
    next({ name: "login" });
  } else if (role && store.state.user.role !== role) {
    next({ name: "home" }); // Redirect non-admins to home
  } else {
    next();
  }
});

export default router;
