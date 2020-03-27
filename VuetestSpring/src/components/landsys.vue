<template>
  <div class="hello">
    <h2>用户登录</h2>
    <el-input v-model="land.name" placeholder="请输入用户名"></el-input>
    <div style="height: 10px"></div>
    <el-input placeholder="请输入密码" v-model="land.password" show-password></el-input>
    <div style="height: 10px"></div>
    <el-button type="primary" @click="onSubmit" style="width: 300px">登陆</el-button>
    <div style="height: 10px"></div>
    <el-link type="primary" @click="insert()" style="float: right">注册</el-link>
  </div>
</template>

<script>
import {mapMutations} from "vuex";

export default {
  name: 'HelloWorld',
  data () {
    return {
      land:{
        name:'',
        password:''
      }
    }
  },
  methods:{
    ...mapMutations(['changeLogin']),
    onSubmit(){
      const _this=this;
      this.$axios.post('http://localhost:8181/land',_this.land).then(function (res) {
        // alert(re.data)
        // if(re.data==="登陆成功")
        // _this.$router.push('/one')
        if(res.data.code === 200){    //成功返回200
          _this.userToken = res.data.token; //拿到token
          _this.changeLogin({Authorization: _this.userToken});
          _this.$router.push('/one')
        }else {
          alert("密码错误")
        }
      })
    },
    insert(){
      this.$router.push('/insert')
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello{
  margin: 0 auto;
  width: 300px;
}
</style>
