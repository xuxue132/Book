<template>
  <div class="PageTwo">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

      <el-form-item label="图书名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>

      <el-form-item label="作者" prop="author">
        <el-input v-model="ruleForm.author"></el-input>
      </el-form-item>

      <el-form-item label="出版社" prop="publish">
        <el-input v-model="ruleForm.publish"></el-input>
      </el-form-item>

      <el-form-item label="页数" prop="pages">
        <el-input v-model.number="ruleForm.pages"></el-input>
      </el-form-item>

      <el-form-item label="价钱" prop="price">
        <el-input v-model.number="ruleForm.price"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        ruleForm: {
          name: '',
          author: '',
          publish: '',
          pages: '',
          price: ''
        },
        rules: {
          name: [
            { required: true, message: '图书名不能为空', trigger: 'blur' },
          ],
          author:[
            {required: true, message: '作者不能为空', trigger:'blur'}
          ],
          publish:[
            {required: true, message: '出版社不能为空', trigger:'blur'}
          ],
          pages:[
            {required: true, message: '页数不能为空', trigger: 'blur' },
            {type:'number',message: '输入错误', trigger: 'blur' },
          ],
          price:[
            {required: true, message: '价钱不能为空', trigger:'blur'},
            {type:'number', message: '输入错误', trigger: 'blur' },
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        const _this=this;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$axios.post("http://localhost:8181/select/add",_this.ruleForm).then(function (resp) {
              _this.$alert('《'+_this.ruleForm.name+'》添加成功', '图书添加', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/pageOne')
                }
              });

            })
          } else {
            _this.$message.error('输入错误，请重新输入!!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    },
    created() {
      // if(localStorage.getItem('Authorization')===null){
      //   _this.$router.push("/land")
      // }
    }
  }

</script>

<style scoped>
.PageTwo{
  margin: 0 auto;
  width: 500px;
}
</style>
