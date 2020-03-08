import Vue from 'vue'
import Router from 'vue-router'

import common from '@/components/common.vue' // 导入共用组件
import axios from '../config/axiosConfig';


Vue.use(Router)

let router = new Router({
  mode: 'history', //（使用history模式） 去掉访问路径里面加#
  scrollBehavior: () => ({ y: 0 }), // 解决vue框架页面跳转有白色不可追踪色块的bug
  isLoadSiteRouter:false,
  routes: [
    {
      path: '/login',
      name: 'login',
      mate:{},
      // 模块使用异步加载
      component:(resolve)=> require(['@/views/websit/Login'], resolve),
      //component:()=>import ('@/views/websit/Login'),
    },
    {
      path: '/',
      name: 'index',
      component:(resolve)=> require(['@/views/websit/Index'], resolve),
    },
    {
      path: '/404',
      name:'404',
      mate:{},
      component:(resolve)=> require(['@/views/errors/404.vue'], resolve),
    },
    {
      path:'/manage',
      name:'manage',
      //redirect:'welcome',
      // 模块使用异步加载
      component:(resolve)=> require(['@/views/manage/Home.vue'], resolve),
      children:[
        {
          path: '/welcome',
          name:'welcome',
          component:(resolve)=> require(['@/views/manage/Welcome.vue'], resolve),
        },
        {
          path: '/content/:mnuesName',
          name:'content',
          component: ()=>import('@/views/manage/Content.vue'),
          children:[
            {
              path: '/content/systemManage/menusManage',
              name:'menusManage',
              component: ()=>import('@/views/manage/systemManage/MenusManage.vue')
            },
            {
              path: '/content/systemManage/userRoles',
              name:'userRoles',
              component: ()=>import('@/views/manage/systemManage/UserRoles.vue')
            },
            {
              path: '/content/vue/myRouter',
              name:'myRouter',
              component: ()=>import('@/views/manage/vue/testRender.vue')
            }
            ,
            {
              path: '/content/vue/other',
              name:'other',
              component: ()=>import('@/views/manage/vue/rederDemo.vue')
            }
          ]
        },
        
      ]
    },

  ]
});


//路由拦截
router.beforeEach((to, from, next) => {
  //console.log(to);
  let toPath = to.path;
  //判断是否访问后台页面,并且未登录，跳转到登录页面
  if(toPath.slice(0,7)=='/manage' && common.getToken()==''){
    next('login');
  }else if(router.options.isLoadSiteRouter){
    next();
  }else if(toPath.slice(0,7)!='/manage'){
    /* 加载站点数据库路由数据 */
    var webSitRoules= [
      /*  {
        path: '/welcome',
        name:'welcome',
        mate:{sid:'001',displayName:'首页',icon:'',status:'1'},
        component:(resolve)=> require(['@/views/manage/Welcome.vue'], resolve),
      }, */
    ];

    axios.post('/site/index/loadTopMenus', '{}',{headers: {'Content-Type': 'application/json;charset=UTF-8'}})
    .then((response)=>{
      if(response.data.success){
        let rows = response.data.rows;
        for(let i in rows){
          let row = rows[i];
          let roule = {
            path:row.url,
            name:row.menuName,
            mate:row,
            component:(resolve)=> require([`@/views/${row.pagePath}`], resolve),
          };
          webSitRoules.push(roule);
        }

        webSitRoules.push(
          {//找不到的路径跳转到异常页面
            path:'*',
            mate:{status:'0'},
            redirect:{name:'404'}
          }
        );

        router.addRoutes(webSitRoules);
        router.options.isLoadSiteRouter=true;
        common.webSitRoules=webSitRoules;
        next({...to, replace: true});

      }else{
        alert(response.data.message);
      }
    })
    .catch((err)=>{
      alert(err);
    });
    
  }else{
    next();
  }
  
});



export default router;