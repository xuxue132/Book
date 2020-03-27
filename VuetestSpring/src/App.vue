<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      // tableData: Array(20).fill(item)
    }
  }
}
</script>

<style>
#app {
   font-family: 'Avenir', Helvetica, Arial, sans-serif;
   -webkit-font-smoothing: antialiased;
   -moz-osx-font-smoothing: grayscale;
   text-align: center;
   color: #2c3e50;
   /*margin-top: 60px;*/
  /*height: 100%;*/
  /*width: 100%;*/
  margin: 0 auto;
  /*height: 600px;*/
  /*width: 1307px;*/
 }

body{
  margin: 0;
  padding:0;
  background: #dfe7e948;

}

</style>
