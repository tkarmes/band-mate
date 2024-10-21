<template>
  <section>
    <ul id="sidebar">
          <li><a href="#">Login</a></li>
          <li><a href="#">Create Profile</a></li>
 
        </ul>
  </section>
</template>

<script>

export default {

}
</script>

<style>

#sidebar {
    background-color: #333;
    color: white;
    padding: 20px;
    width: 250px;
    float: left;
    height: calc(100vh - 100px); /* Full height minus header and footer */
}

#sidebar ul {
    list-style: none;
    padding: 0;
}

#sidebar ul li {
    margin-bottom: 15px;
}

#sidebar ul li a {
    color: white;
    text-decoration: none;
}

#sidebar ul li a:hover {
    color: #f39c12;
}

</style>