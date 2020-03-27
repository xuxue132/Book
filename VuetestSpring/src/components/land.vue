<template style="background-color: #409EFF">
  <div class="land">
    <header>
      <el-row>
        <el-col :span="8" class='title'>
          <i class="el-icon-view"></i> &nbsp;&nbsp;图书管理系统
        </el-col>
      </el-row>
    </header>
    <div style="padding-top: 30px;margin: auto">
<!--      <div class="title">-->
<!--        <el-menu router default-active="/land" class="el-menu-demo" mode="horizontal">-->
<!--          <el-menu-item index="/land" class="is-active">登陆</el-menu-item>-->
<!--          <el-menu-item index="/insert">注册</el-menu-item>-->
<!--        </el-menu>-->
<!--      </div>-->
      <router-view/>
    </div>

  </div>

</template>

<script>
    export default {
        name: "land",
        data(){
          return {
            activeIndex: '1',
            activeIndex2: '1'
          };
        }
    }
</script>

<style scoped>
  .title{
    margin:0 auto;
    width: 300px;
  }
  .land{
    width: 100%;
    height: 700px;
    /*background-color: red;*/
    margin: 0 auto;
  }

  header {
    background: #418cd3;
  }
  header .title {
    height: 58px;
    line-height: 58px;
    text-align: center;
    font-size: 20px;
    color: #fff;
  }
</style>
