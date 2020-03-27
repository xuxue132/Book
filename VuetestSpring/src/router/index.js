import Vue from 'vue'
import Router from 'vue-router'
import BookMange from '../components/BookMange'
import AddBook from '../components/AddBook'
import index from '../components/index'
import BookUpdate from '../components/BookUpdate'
import land from '../components/land'
import landsys from '../components/landsys'
import insert from '../components/insert'
Vue.use(Router);

export default new Router({
  mode:'history',
  routes: [
    {
      path: "/one",
      name: "图书管理",
      component: index,
      redirect: "/pageOne",
      show: true,
      children:[
        {
          path:"/pageOne",
          name: "查询图书",
          show:true,
          component: BookMange
        },
        {
          path:"/pageTwo",
          name: "添加图书",
          show:true,
          component: AddBook
        },
        {
          path: '/update',
          show:false,
          component: BookUpdate,
        },
      ]
    },
    {
      path: "/",
      component: land,
      show: false,
      redirect: '/land',
      children: [
        {
          path: '/land',
          name: '登陆',
          component: landsys
        },
        {
          path: '/insert',
          name:'注册',
          component:insert
        }
      ]
    }
    // {
    //   path:"/navigation",
    //   name:"导航二",
    //   component:index,
    //   children: [
    //     {
    //       path:"/pageThree",
    //       name: "页面3",
    //       component: PageThree
    //     },
    //     {
    //       path:"/pageFour",
    //       name: "页面4",
    //       component: PageFour
    //     }
    //   ]
    // },
  ]
})
