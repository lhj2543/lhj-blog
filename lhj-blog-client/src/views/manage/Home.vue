
<template>
  <div class="layout">
      <Layout :style="{height: '100%'}">
           <!-- =====================头部开始===================== -->
          <Header class="manage-header">
              <div class="layout-logo theme-1">
                  <a target="_self" href="/" >
                    <!-- <Icon type="logo-windows" color="#fff" /> 后台管理 -->
                    <span class="cursor-pointer" title="liuhaijiang.top">
                        <Icon id="logo" class="logo"  color="" custom="iconfont icon-icon-logo" />
                        <span class="log-name">RIVER</span>
                    </span>
                  </a>
              </div>
            <div class="layout-nav">
                <div class="top-menus" :style="{float:'right'}" >
                    <Menu mode="horizontal"   @on-select="topSelectMenu" :active-name="topMenuActiveName">
                        <MenuItem :url="menu.url" :name="menu.id" :id="menu.id" v-for="menu in menus" :key="menu.id"  >
                            <Icon :type="menu.icon" />
                            {{menu.name}}
                        </MenuItem>

                        <!-- 用户信息 -->
                        <span v-if="sysAccountInfo.token!='' && sysAccountInfo.token!=undefined">
                            <Icon type="ios-person-outline" />
                            <span>{{sysAccountInfo.userCd}}</span>
                            &nbsp; | &nbsp; 
                            <span class="cursor-pointer logout" @click="logout">注销</span>
                        </span>
                        
                        
                    </Menu>
                    
                   
                    
                </div>
            </div>
          </Header>
          <!-- =====================头部结束===================== -->

          <Layout :style="{padding: '0 50px'}">
              <!-- =====================导航栏开始===================== -->
              <Breadcrumb :style="{margin: '16px 0'}">
                  <BreadcrumbItem>Home</BreadcrumbItem>
                  <BreadcrumbItem>Components</BreadcrumbItem>
                  <BreadcrumbItem>Layout</BreadcrumbItem>
              </Breadcrumb>
              <!-- =====================导航栏结束===================== -->

              <Content :style="{minHeight: '280px',height:'100%', background: '#fff'}" >
                  <router-view :menuActiveName="menuActiveName" :openMenus="openMenus" :leftMenus="leftMenus" ref="ref_coontent"  />
              </Content>
          </Layout>
          <Footer class="layout-footer-center">Copyright &copy; 2020 vue-demo</Footer>
      </Layout>
  </div>
</template>


<script>

  import '@/assets/css/manage/home.css'//引入后台管理首页样式

  export default {
    name: 'Home',
    data () {
      
      return {
        sysAccountInfo:this.$common.getAccountInfo(),
        topMenuActiveName:'',
        menuActiveName:'',
        openMenus:[],
        leftMenus:[],
        menus:this.$common.manageMenus,
        /* menus:{
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
        }, */
       
      }
      
    },
    computed:{
    
    },
    created(){
        //展开所以菜单节点
        //this.openMenus=this.getOpenMenus();

        //设置默认菜单
        this.setDefaultMenu();

        //页面刷新时自动定位到菜单栏
        this.autoSelectMenu();
        
    },
    methods:{
        
        logout(){//用户注销
            this.$common.logout({_this:this});//公共注销方法
        },
        
        getOpenMenus(){//展开所以菜单节点
            var result=[];
            this.leftMenus.forEach((menu) => {
                if(menu.nextMenus!=undefined && menu.nextMenus!=null && menu.nextMenus.length>0){
                    result.push(menu.id);
                }
            });
            return result;
        },
        autoSelectMenu(){//页面刷新时自动定位到菜单栏
            var routePath=this.$route.path;
            var routeName=this.$route.name;
            if(routePath.lastIndexOf('/')==routePath.length-1){
                routePath=routePath.substring(0,routePath.lastIndexOf('/'));
            }
            var paths=routePath.split('/');
            var flag=true;
            topBreak:
            for(var i in this.menus){
                var menu=this.menus[i];
                if(paths.length<=3 ){
                    if(routePath==menu.url){
                        this.topMenuActiveName=menu.id;
                        this.leftMenus=menu.leftMenus;
                        this.setLeftDefaultMenu();
                        flag=false;
                        break;
                    }
                }else{
                    var parentPath='/'+paths[1]+'/'+paths[2];
                    var childrenPath=routePath;
                    for(var j in menu.leftMenus){
                        var leftMenu=menu.leftMenus[j];
                        var nextMenus=leftMenu.nextMenus;
                        if(nextMenus!=undefined && nextMenus!=null && nextMenus.length>0){
                            for(var k in nextMenus){
                                var nextMenu=nextMenus[k];
                                 if(childrenPath==nextMenu.url){ 
                                    this.topMenuActiveName=menu.id;
                                    this.openMenus=[leftMenu.id];
                                    this.menuActiveName=nextMenu.url;
                                    this.leftMenus=menu.leftMenus;
                                    flag=false;
                                    break topBreak;
                                }  
                            }
                        }else if(childrenPath==leftMenu.url){ 
                            this.topMenuActiveName=menu.id;
                            this.menuActiveName=leftMenu.url;
                            this.openMenus=[];
                            this.leftMenus=menu.leftMenus;
                            flag=false;
                            break topBreak;
                        }
                        
                    }
                }
            }
            /* alert(flag);
            if(flag){//菜单页面没找到跳转到首页
                this.$router.push({path:'/welcome'});
            } */

        },
        topSelectMenu(name){//顶部菜单选中事件
            var this_element=document.getElementById(name);
            var this_url=this_element.getAttribute('url');
            
            if(this.$route.path.indexOf(this_url)==-1){
                this.$router.push({path:this_url});
                this.leftMenus=this.menus[name].leftMenus==undefined || null?[]:this.menus[name].leftMenus;
                this.setLeftDefaultMenu();
            }

        },
        setDefaultMenu(){//设置默认菜单
            for(var i in this.menus){
                var menu=this.menus[i];
                this.topMenuActiveName=menu.id;
                this.leftMenus=menu.leftMenus;
                break;
            }
        },
        setLeftDefaultMenu(){//设置默认左菜单第一个
            
            if(this.leftMenus!=undefined && this.leftMenus!=null && this.leftMenus.length>0){
                var leftMenu=this.leftMenus[0];
                var nextMenus=leftMenu.nextMenus;
                if(nextMenus!=undefined && nextMenus!=null && nextMenus.length>0){
                    var nextMenu=nextMenus[0];
                    this.openMenus=[leftMenu.id];
                    this.menuActiveName=nextMenu.url;
                    this.$router.push({path:nextMenu.url});
                }else{
                    this.openMenus=[];
                    this.menuActiveName=leftMenu.url;
                    this.$router.push({path:leftMenu.url});
                }

                this.$nextTick(() => {//手动更新菜单选择，展开
                    this.$refs.ref_coontent.$refs.side_menu.updateOpened();
                    this.$refs.ref_coontent.$refs.side_menu.updateActiveName();
                })
            }
        }
    },
    mounted(){
       
    },  
    watch: {//监听
            '$route' (to, from) {//路径发生变化是监听
               //console.log(to);
               this.autoSelectMenu();
            }
        }
  }
</script>
