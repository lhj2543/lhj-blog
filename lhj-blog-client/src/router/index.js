import Vue from 'vue'
import Router from 'vue-router'

import common from '@/components/common.vue' // 导入共用组件
import axios from '../config/axiosConfig';


Vue.use(Router)

let router = new Router({
  mode: 'history', //（使用history模式） 去掉访问路径里面加#
  scrollBehavior: () => ({ y: 0 }), // 解决vue框架页面跳转有白色不可追踪色块的bug
  isLoadSiteRouter:false,
  isLoadManageRouter:false,
  routes: [
    {
      path: '/river/login',
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
      path: '/river/404',
      name:'404',
      mate:{},
      component:(resolve)=> require(['@/views/errors/404.vue'], resolve),
    },
    /* {
      path:'/manage',
      name:'manage',
      //redirect:'welcome',
      // 模块使用异步加载
      component:(resolve)=> require(['@/views/manage/Home.vue'], resolve),
    }, */

  ]
});

//路由拦截
router.beforeEach((to, from, next) => {
  //console.log(to);
  debugger
  let toPath = to.path;
  //判断是否访问后台页面,并且未登录，跳转到登录页面
  if(toPath!='/' && toPath.slice(0,6) !='/river' && common.getToken()==''){
    next('/river/login');
  }else if(router.options.isLoadSiteRouter){
    next();
  }else if(router.options.isLoadManageRouter){
    next();
  }else if(toPath!='/' && toPath.slice(0,6) !='/river'){
     /* 加载后台管理路由，及菜单 */

      axios.post('/home/loadMenus', '{}',{headers: {'Content-Type': 'application/json;charset=UTF-8'}})
      .then((response)=>{
          let manageRoules = [];//后台管理路由数据
          let manageMenus = {};//后台管理菜单数据
          

          let data = response.data;
          if(data.success){
            let rows = data.rows;
            /* ===============路由数据组装=============== */
            let manageRoule = getRole(data);
            let contentRoule = {
              path: '/content/:mnuesName',
              name:'content',
              mate:{},
              component: ()=>import('@/views/manage/Content.vue'),
              children:[]
            };

            
            for(let i in rows){
              let row = rows[i];
              if(row.parentId == data.sid && row.url && row.pagePath ){
                manageRoule.children.push(getRole(row));
              }else if(row.url && row.pagePath && row.parentId!='-1'){
                contentRoule.children.push(getRole(row));
              }

              /* ===============菜单数据组装=============== */
              if(row.parentId == data.sid){
                let menu = getMenu(row,rows,true);
                manageMenus[row.sid] = menu;
              }
             /* ===============菜单数据组装=============== */

            }
            manageRoule.children.push(contentRoule);
            manageRoules.push(manageRoule);
          
            
             /* ===============路由数据组装=============== */


            common.manageRoules = manageRoules;
            common.manageMenus = manageMenus;
          
          }else{
              alert(data.message);
          }

          manageRoules.push(
            {//找不到的路径跳转到异常页面
              path:'*',
              mate:{status:'0'},
              redirect:{name:'404'}
            }
          );
          router.addRoutes(manageRoules);
          router.options.isLoadManageRouter=true;
          next({...to, replace: false});

      })
      .catch((err)=>{
         alert(err);
      });

  }else if(toPath == '/' || toPath.slice(0,6) =='/river'){
    /* 加载站点数据库路由数据 */
    var webSitRoules= [
      /*  {
        path: '/welcome',
        name:'welcome',
        mate:{sid:'001',displayName:'首页',icon:'',status:'1'},
        component:(resolve)=> require(['@/views/manage/Welcome.vue'], resolve),
      }, */
    ];

    var webSitMenus = [];

    axios.post('/site/index/loadTopMenus', '{}',{headers: {'Content-Type': 'application/json;charset=UTF-8'}})
    .then((response)=>{
      if(response.data.success){
        let rows = response.data.rows;
        for(let i in rows){
          let row = rows[i];
          if(row.pagePath){
            let roule = getRole(row);
            webSitRoules.push(roule);
          }

          let menu = {
            id:row.sid,
            displayName:row.displayName,
            url:row.url
          }
          webSitMenus.push(menu);
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
        common.webSitMenus=webSitMenus;
        next({...to, replace: false});

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

/* 组装后台管理路由 */
function getRole(data) {
  
  return {
    path:data.url,
    name:data.menuName,
    //redirect:'/welcome',
    //mate:data,
    // 模块使用异步加载
    component:(resolve)=> require([`@/views/${data.pagePath}`], resolve),
    children:[]
  }

}


/* 
  top0:{id:'top0',name:'首页',url:'/welcome',urlName:'welcome',icon:'ios-home-outline',leftMenus:[]},
  top1:{id:'top1',name:'系统管理',url:'/content/systemManage',urlName:'content',icon:'ios-laptop',leftMenus:
          [
          {id:'top1-20',name:'菜单管理',url:'',urlName:'',icon:'ios-home-outline',nextMenus:[
              {id:'top1-20-0',name:'菜单管理',url:'/content/systemManage/menusManage',urlName:'menusManage',icon:'ios-home-outline',nextMenus:[]},
              {id:'top1-20-1',name:'菜单授权',url:'/content/systemManage/userRoles',urlName:'userRoles',icon:'ios-home-outline',nextMenus:[]}
          ]},
          {id:'top1-0',name:'菜单管理',url:'/content/systemManage/menusManage2',urlName:'welcome',icon:'ios-home-outline',nextMenus:[]},
          {id:'top1-1',name:'用户管理',url:'',urlName:'',icon:'ios-home-outline',nextMenus:[
              {id:'top1-1-0',name:'用户授权',url:'/content/systemManage/userRoles1',urlName:'userROles1',icon:'ios-home-outline',nextMenus:[]}
          ]},
          {id:'top1-2',name:'授权管理',url:'',urlName:'',icon:'ios-home-outline',nextMenus:[
              {id:'top1-2-0',name:'用户授权',url:'/content/systemManage/userRoles',urlName:'userROles',icon:'ios-home-outline',nextMenus:[]},
              {id:'top1-2-1',name:'菜单授权',url:'/content/systemManage/userRoles2',urlName:'userROles2',icon:'ios-home-outline',nextMenus:[]}
          ]},
          ]
  },
  top2:{id:'top2',name:'vue',url:'/content/vue',urlName:'content',icon:'ios-mail-outline',leftMenus:
      [
          {id:'top1-0',name:'render测试',url:'/content/vue/myRouter',urlName:'myRouter',icon:'ios-home-outline',nextMenus:[]}, 
          {id:'top1-0',name:'render demo',url:'/content/vue/other',urlName:'other',icon:'ios-home-outline',nextMenus:[]}, 
      ]
  },
  top3:{id:'top3',name:'spring-boot',url:'/content/spring-boot',urlName:'content',icon:'ios-photos-outline',leftMenus:[]}, 
*/

/*迭代 组装后台管理菜单 */
function getMenu(data,rows,isLeftMenu) {

  let menu = {
    id:data.sid,
    name:data.displayName,
    url:data.url,
    urlName:data.menuName,
    icon:data.icon,
  };

  if(isLeftMenu){
    menu.leftMenus=[];
  }else{
    menu.nextMenus=[];
  }

  for(let i in rows){
    let row = rows[i];
    if(row.parentId == data.sid){
      if(isLeftMenu){
        menu.leftMenus.push(getMenu(row,rows,false));
      }else{
        menu.nextMenus.push(getMenu(row,rows,false));
      }
    }
  }

  return menu;

}

export default router;