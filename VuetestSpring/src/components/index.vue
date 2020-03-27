<template>
  <div class="Total">
    <div class="title">
      <div style="height: 65px;width: 190px;margin-left: 1180px;overflow:hidden">
        <el-dropdown>
            <span class="el-dropdown-link">
              <el-avatar :src="circleUrl"></el-avatar><i class="el-icon-arrow-down el-icon--right" ></i>
            </span>
            <el-dropdown-menu slot="dropdown" style="width: 140px">
              <div style="height: 50px;line-height: 50px;text-align: center">你好{{name}}</div>
              <div style="text-align: center">
                <el-button type="danger" plain @click="outLand">退出登录</el-button>
              </div>

            </el-dropdown-menu>
        </el-dropdown>


        <div class="username">
          {{name}}
        </div>
      </div>

    </div>
    <el-container style="height: 500px; border: 1px solid #eee;width: 1365px">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu router :default-openeds="['1', '0']">
          <el-submenu :key="index" :index="index+''" v-for="(item,index) in $router.options.routes" v-if="item.show">
            <template slot="title"><i class="el-icon-setting"></i>{{item.name}}</template>
            <el-menu-item :key="index2" v-for="(item2,index2) in item.children" :index="item2.path" v-if="item2.show"
                          :class="$route.path===item2.path ? 'is-active':''">{{item2.name}}
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
        name: ""
      }
    },
    methods:{
      outLand(){
        localStorage.removeItem('Authorization');
        this.$router.push('/land');
      }
    },
    created() {
      const _this = this;
      this.$axios.get("http://localhost:8181/get/token").then(function (resp) {

          _this.name = resp.data;


      })
    }

  }
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }

  .title {
    width: 1365px;
    height: 65px;
    margin-top: 20px;
  }

  .Total {
    width: 1365px;
    /*height: 560px;*/
    margin: 0 auto;
    /*background-color: #eeeeee;*/
  }

  .username {
    height: 30px;
    text-align: center;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  }
  .el-dropdown-link {
    margin-top: 10px;
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    margin-top: 10px;
    font-size: 12px;
  }
</style>
