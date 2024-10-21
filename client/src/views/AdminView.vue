<template>
    <div v-if="isLoading">
      <loading-spinner id="spinner" :spin="true" />
    </div>
    <div id="admin-talents-view" v-else>
      <h2>Manage Talents</h2>
  
      <!-- Form to add new talent -->
      <form @submit.prevent="addTalent">
        <input
          type="text"
          placeholder="Enter talent name"
          v-model="newTalent"
          required
        />
        <button type="submit">Add Talent</button>
      </form>
  
      <!-- Display existing talents with delete option -->
      <ul>
        <li v-for="talent in talents" :key="talent.id">
          {{ talent.name }}
          <button @click="deleteTalent(talent.id)">Delete</button>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import { resourceService } from "../services/ResourceService.js";
  import LoadingSpinner from "../components/LoadingSpinner.vue";
  
  export default {
    components: { LoadingSpinner },
    data() {
      return {
        isLoading: false,
        talents: [],
        newTalent: ""
      };
    },
    methods: {
      addTalent() {
        // Call the API to add a new talent
        resourceService.createTalent(this.newTalent)
          .then(() => {
            this.loadTalents(); // Reload talents after adding
          })
          .catch((error) => {
            console.error(error);
          });
      },
      deleteTalent(talentId) {
        // Call the API to delete a talent
        resourceService.deleteTalent(talentId)
          .then(() => {
            this.loadTalents(); // Reload talents after deletion
          })
          .catch((error) => {
            console.error(error);
          });
      },
      loadTalents() {
        this.isLoading = true;
        resourceService.getTalent()
          .then((response) => {
            this.talents = response.data;
          })
          .finally(() => {
            this.isLoading = false;
          });
      }
    },
    created() {
      this.loadTalents(); // Load talents when the component is created
    }
  };
  </script>
  
  <style scoped>
  #admin-talents-view {
    padding: 20px;
  }
  
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  button {
    margin-left: 10px;
  }
  </style>
  