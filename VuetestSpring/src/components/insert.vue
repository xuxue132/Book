<template>
    <div class="insert">
      <div style="margin-left: 70px">
        <h2>用户注册</h2>
      </div>

      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="ruleForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
      <el-link type="primary" @click="landBook()" style="float: right">返回登录</el-link>
    </div>
</template>

<script>
    export default {
        name: "insert",
      data() {
        var checkAge = (rule, value, callback) => {
          if (!value) {
            return callback(new Error('年龄不能为空'));
          }
          setTimeout(() => {
            if (!Number.isInteger(value)) {
              callback(new Error('请输入数字值'));
            } else {
              if (value < 18) {
                callback(new Error('必须年满18岁'));
              } else {
                callback();
              }
            }
          }, 1000);
        };
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'));
          } else {
            if (this.ruleForm.checkPass !== '') {
              this.$refs.ruleForm.validateField('checkPass');
            }
            callback();
          }
        };
        var validatePass2 = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请再次输入密码'));
          } else if (value !== this.ruleForm.pass) {
            callback(new Error('两次输入密码不一致!'));
          } else {
            callback();
          }
        };
        return {
          ruleForm: {
            name: '',
            pass: '',
            checkPass: '',
          },
          user:{
            name:'',
            password:''
          },
          rules: {
            pass: [
              { validator: validatePass, trigger: 'blur' }
            ],
            checkPass: [
              { validator: validatePass2, trigger: 'blur' }
            ],
            name:[
              { required: true, message: '请输入用户名', trigger: 'blur' },
            ]

          }
        };
      },
      methods: {
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              const _this=this;
              var params = new URLSearchParams();
              params.append('name',_this.ruleForm.name);
              params.append('password',_this.ruleForm.pass);
              this.$axios.post('http://localhost:8181/add',params).then(function (resp) {
                alert(resp.data);
                if(resp.data === "创建成功"){
                  _this.$router.push('/land')
                }
              })
            } else {
              alert('输入错误!!');
              return false;
            }
          });
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        },
        landBook(){
          this.$router.push('/land')
        }
      }
    }
</script>

<style scoped>
.insert{
  margin: 0 auto;
  width: 400px;
}
</style>
