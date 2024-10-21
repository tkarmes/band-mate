<template>
  <div v-if="isLoading">
      <loading-spinner id="spinner" :spin="true"/>
  </div>
  <div id="artists-view" v-else>
    <h2>Artists</h2>
    <div>
      <header>
          <form>
              <input 
                  type="text" 
                  placeholder="Filter by name" 
                  id="filter-input" 
                  v-model="filter" 
              />
          </form>
      </header>
    </div>
    <ul>
      <li v-for="artist in filteredArtists" :key="artist.id" class="artist-item">
        <h3>{{ artist.name }}</h3>
        <p>{{ artist.bio }}</p>
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
          filter: "", // Filter text bound to the input field
      };
  },
  computed: {
      artists() {
          return this.$store.state.artists;
      },
      filteredArtists() {
          // Filter the artists array based on the filter input
          return this.artists.filter(artist =>
              artist.name.toLowerCase().includes(this.filter.toLowerCase())
          );
      },
  },
  created() {
      this.isLoading = true;
      resourceService.getArtist()
          .then((response) => {
              this.$store.commit("SET_ARTISTS", response.data);
              console.log(response.data);
          })
          .catch((error) => {
              console.log(error);
          })
          .finally(() => {
              this.isLoading = false;
          });
  },
};
</script>

<style scoped>
#artists-view {
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
}

h2 {
  font-size: 2rem;
  margin-bottom: 20px;
}

form {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

#filter-input {
  padding: 10px;
  font-size: 1rem;
  border: 2px solid #ccc;
  border-radius: 5px;
  width: 100%;
  max-width: 400px;
  transition: border-color 0.3s;
}

#filter-input:focus {
  border-color: #007bff;
  outline: none;
}

ul {
  list-style-type: none;
  padding: 0;
}

.artist-item {
  margin-bottom: 20px;
}

.artist-item h3 {
  font-size: 1.5rem;
  margin-bottom: 10px;
}

.artist-item p {
  font-size: 1rem;
  color: #555;
}
</style>
