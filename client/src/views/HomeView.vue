<template>
  <div id="main-div">
    <welcome-page />
  </div>
</template>

<script>
import WelcomePage from '../components/WelcomePage.vue';

export default {
  components: { WelcomePage },
};
</script>

<style>
/* Main Content */
#main-div {
  display: flex;
  justify-content: center; /* Centers the WelcomePage component */
  margin: 20px;
}

/* Responsive Design for Tablets and Mobile */
@media (max-width: 768px) {
  #main-div {
    flex-direction: column;
    margin: 10px;
  }
}
</style>
