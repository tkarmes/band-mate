<template>
    <div v-if="isLoading">
        <loading-spinner id="spinner" :spin="true"/>
    </div>
    <div id="talents-view" v-else>
      <h2>Talents</h2>
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
        <li v-for="talent in filteredTalents" :key="talent.id" class="talent-item">
          <p>{{ talent.name }}</p>
        </li>
      </ul>
    </div>
</template>

<script>
import LoadingSpinner from '../components/LoadingSpinner.vue';
import { resourceService } from "../services/ResourceService.js";

export default {
    components: { LoadingSpinner },
    data() {
        return {
            isLoading: false,
            filter: "", // Filter text bound to the input field
        };
    },
    computed: {
        talents() {
            return this.$store.state.talents;
        },
        filteredTalents() {
            // Filter the talents array based on the filter input
            return this.talents.filter(talent =>
                talent.name.toLowerCase().includes(this.filter.toLowerCase())
            );
        },
    },
    created() {
        this.isLoading = true;
        resourceService.getTalent()
            .then((response) => {
                this.$store.commit("SET_TALENTS", response.data);
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
#talents-view {
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

.talent-item {
    margin-bottom: 10px;
}

.talent-item p {
    font-size: 1.2rem;
    color: #333;
}
</style>
