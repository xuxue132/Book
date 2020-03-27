<template>
  <div class="PageOne">
    <el-button  @click="drawer = true" type="primary" style="float: right" class="el-icon-search">搜索</el-button>
    <el-drawer
      style="color: red"
      :visible.sync="drawer"
      direction="ltr"
      :before-close="handleClose">
      <span style="color: black">书籍查询</span>
      <div style="height: 10px"></div>
      <div style="width: 418px;height: 40px">
        <el-row class="demo-autocomplete" style="float: left">
          <el-col :span="12">
            <el-autocomplete
              style="width: 300px"
              class="inline-input"
              v-model="state1"
              :fetch-suggestions="querySearch"
              placeholder="请输入内容"
            ></el-autocomplete>
          </el-col>
        </el-row>
        <el-button type="primary" icon="el-icon-search" style="float: left" @click="serach()">搜索</el-button>
      </div>
      <div style="height: 10px"></div>
      <div v-if="bool">
        <span>编号：{{serachbook.id}}</span><br>
        <span>书名：{{serachbook.name}}</span><br>
        <span>作者：{{serachbook.author}}</span><br>
        <span>出版社：{{serachbook.publish}}</span><br>
        <span>页数：{{serachbook.pages}}</span><br>
        <span>价格：{{serachbook.price}}</span>
      </div>
      <div v-if="notbook">
        <span>没有此书籍!!</span><br>
      </div>

    </el-drawer>

    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        fixed
        prop="id"
        label="编号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="name"
        label="图书名"
        width="190">
      </el-table-column>
      <el-table-column
        prop="author"
        label="作者"
        width="190">
      </el-table-column>
      <el-table-column
        prop="publish"
        label="出版社"
        width="190">
      </el-table-column>
      <el-table-column
        prop="pages"
        label="页数"
        width="100">
      </el-table-column>
      <el-table-column
        prop="price"
        label="价格"
        width="110">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row)" type="text" size="small"><i class="el-icon-edit"></i></el-button>

          <el-popconfirm
            placement="top"
            width="160"
            title="确定删除吗？"
            confirmButtonText='删除'
            @onConfirm="deletebook(scope.row)"
          >
            <el-button slot="reference"  type="text" size="small"><i class="el-icon-delete"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      layout="prev, pager, next"
      :page-size="10"
      :total="book.length"
      @current-change="page">
    </el-pagination>

  </div>

</template>
<script>
  export default {
    name: "PageOne",
    methods: {
      serach(){
        var _this = this;
        _this.notbook=false;
        _this.bool=false;

        this.$axios.get('http://localhost:8181/serach/'+this.state1).then(function (resp) {

          try {
            _this.serachbook=resp.data
            if(_this.serachbook.name===undefined){
              _this.notbook=true;
            }else {
              _this.bool=true;
            }
          }catch (e) {
            _this.$router.push("/land")
          }

        })
      },

      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
            this.state1="";
            this.serachbook="";
            this.notbook=false;
            this.bool=false;
          })
          .catch(_ => {});
      },
      edit(row) {
        this.$router.push({
          path: '/update',
          query:{
            id: row.id
          }
        })
      },
      nonething(){

      },
      deletebook(row){
        const _this=this;
        this.$axios.get('http://localhost:8181/delete/'+row.id).then(function (re) {
          _this.$alert('《'+row.name+'》删除成功', '图书删除', {
            confirmButtonText: '确定',
            callback: action => {
              window.location.reload()
            }
          });
        })
      },
      page(currentPage){
        this.page1=currentPage
        this.url= "http://localhost:8181/select/all/"+this.page1;
        const _this=this;
        this.$axios.get(_this.url).then(function (resp) {
          _this.tableData = resp.data;
        });
      },


      querySearch(queryString, cb) {
        var restaurants = this.restaurants;
        var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      // handleSelect(item) {
      //   console.log(item);
      // }
    },


    data() {
      return {
        visible: false,
        book: [],
        tableData: [],
        page1:1,
        url:"",
        drawer: false,
        input:" ",
        restaurants: [],
        state1: '',
        serachbook:'',
        bool: false,
        notbook: false
      }
    },
    created() {
      this.url= "http://localhost:8181/select/all/"+this.page1;
      const _this=this;
      this.$axios.get(_this.url).then(function (resp) {
        // if(resp.data.code === "500"){
        //   alert("1 "+resp.data.code)
        //   _this.$router.push("/land")
        // }else {
          _this.tableData = resp.data;
        // }

      });
      this.$axios.get("http://localhost:8181/select/all").then(function (resp) {
        // if(resp.data.code === "500"){
        //   _this.$router.push("/land")
        // }
        _this.book = resp.data;
        _this.restaurants = resp.data;
        for(var i=0; i<_this.restaurants.length;i++){
          _this.restaurants[i].value=_this.restaurants[i].name
        }
      });
      // if(localStorage.getItem('Authorization')===null){
      //   _this.$router.push("/land")
      // }
    }
  }
</script>

<style scoped>
.PageOne{
  margin: 0 auto;
}
</style>
