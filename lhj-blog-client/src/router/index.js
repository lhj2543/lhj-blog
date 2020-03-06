import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

export default new Router({
  mode: 'history', //（使用history模式） 去掉访问路径里面加#
  routes: [
    {
      path: '/login',
      name: 'login',
      component:()=>import ('@/views/websit/Login'),
    },
    {
      path: '/',
      name: 'index',
      component:()=>import ('@/views/websit/Index'),
    },
    {
      path:'/manage',
      name:'manage',
      component:()=>import ('@/views/manage/Home.vue'),
      children:[
        {
          path: '/',
          name:'welcome',
          component: ()=>import('@/views/manage/Welcome.vue'),
          /*  components:{
              page1:()=>import('./views/Page1.vue'),
              page2:()=>import('./views/Page2.vue')
          } */
        },
        {
          path: '/welcome',
          name:'welcome',
          component: ()=>import('@/views/manage/Welcome.vue')
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
    {
      path: '/error',
      name:'error',
      component: ()=>import('@//views/errors/404.vue'),
    },
		{//其他路径跳转到首页
			path:'*',
			redirect:{name:'error'}
		}
  ]
})
