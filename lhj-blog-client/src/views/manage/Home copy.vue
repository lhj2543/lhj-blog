<style scoped>
.layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
    height: 100%;
}
.layout-logo{
    width: 100px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
    color: #fff;
    font-size: 16px;
}
.layout-nav{
   
}
.layout-footer-center{
    text-align: right;
    padding: 15px 50px;
}
.top-menus *{
    color: #fff !important;
}
.top-menus li:hover,.top-menus li:hover *,.top-menus .ivu-menu-item-active,.top-menus .ivu-menu-item-active *{
    color: #f60 !important;
}

</style>

<template>
  <div class="layout">
      <Layout :style="{height: '100%'}">
           <!-- =====================头部开始===================== -->
          <Header>
              <!-- <div class="layout-logo">vue-demo</div> -->
            <div class="layout-nav">
                <div class="top-menus" :style="{float:'right'}" >
                    <Menu mode="horizontal" theme="dark" @on-select="topSelectMenu" :active-name="topMenuActiveName">
                        <MenuItem :name="menu.id" v-for="menu in menus" :key="menu.id" >
                            <Icon :type="menu.icon" />
                            {{menu.name}}
                        </MenuItem>
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

              <Content :style="{minHeight: '280px',height:'100%', background: '#fff'}">
                  <Layout :style="{height:'100%'}">
                      <!-- =====================左侧菜单开始===================== -->
                      <Sider hide-trigger :style="{background: '#fff',height:'100%'}">
                          <Menu :active-name="menuActiveName" theme="light"  :open-names="openMenus" :style="{height:'100%'}"  width="auto" >
                              <template v-for="(menu,index) in leftMenus">
                                <Submenu v-if="menu.nextMenus && menu.nextMenus.length>0" :name="menu.id"  :key="index+'-'+menu.id">
                                    <template slot="title">
                                        <Icon :type="menu.icon"></Icon>
                                        {{menu.name}}
                                    </template>
                                    <MenuItem :to="{path:nextMenu.url}"  v-for="(nextMenu,nextIndex) in menu.nextMenus" :key="index+'-'+nextMenu.id" :name="nextMenu.urlName">
                                        {{nextMenu.name}}
                                    </MenuItem>
                                </Submenu>

                                <MenuItem v-else :to="{path:menu.url}" :key="index+'-'+menu.id" :name="menu.urlName">
                                    <Icon :type="menu.icon"></Icon>
                                    {{menu.name}}
                                </MenuItem>

                              </template>
                          </Menu>
                      </Sider>
                      <!-- =====================左侧菜单结束===================== -->

                       <!-- =====================右侧主体内容开始===================== -->
                      <Content :style="{padding: '24px', minHeight: '280px', background: '#fff'}">
                        <router-view/>
                      </Content>
                      <!-- =====================右侧主体内容结束===================== -->
                      
                  </Layout>
              </Content>
          </Layout>
          <Footer class="layout-footer-center">Copyright &copy; 2020 vue-demo</Footer>
      </Layout>
  </div>
</template>


<script>
  export default {
    name: 'Home',
    data () {
      return {
        topMenuActiveName:'',
        menuActiveName:'',
        openMenus:[],
        leftMenus:[],
        menus:{
            top0:{id:'top0',name:'首页',icon:'ios-home-outline',leftMenus:[
                 {id:'0',name:'welcome',url:'/welcome',urlName:'welcome',icon:'ios-home-outline',nextMenus:[]},
                 {id:'1',name:'iview-demo',url:'',urlName:'',icon:'ios-laptop',nextMenus:[
                    {id:'1-1',name:'mnuesDemo1',url:'/mnuesDemo1',urlName:'mnuesDemo1',icon:''},
                 ]},
                 {id:'2',name:'item2',url:'',urlName:'',icon:'ios-mail-outline',nextMenus:[
                    {id:'2-1',name:'op2',url:'/',urlName:'',icon:''},
                    {id:'2-2',name:'op2',url:'/',urlName:'',icon:''},
                 ]},
                 {id:'3',name:'item3',url:'/afaf',urlName:'afaf',icon:'ios-photos-outline'},
                 ],
            },
            top1:{id:'top1',name:'系统管理',icon:'ios-laptop',leftMenus:[
                 {id:'top1-0',name:'菜单管理',url:'/welcome',urlName:'welcome',icon:'ios-home-outline',nextMenus:[]},
                 ]
            },
            top2:{id:'top2',name:'vue',icon:'ios-mail-outline',leftMenus:[]},
            top3:{id:'top3',name:'spring boot',icon:'ios-photos-outline',leftMenus:[]},
        },
       
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
        getOpenMenus(){//展开所以菜单节点
            var result=[];
            this.leftMenus.forEach((menu) => {
                if(menu.nextMenus!=undefined && menu.nextMenus!=null && menu.nextMenus.length>0){
                    result.push(menu.id);
                }
            });
            return result;
        },
        autoSelectMenu(){////页面刷新时自动定位到菜单栏
            var routePath=this.$route.path;
            var routeName=this.$route.name;
            this.leftMenus.some((menu) => {

                if(menu.nextMenus!=undefined && menu.nextMenus!=null && menu.nextMenus.length>0){
                    menu.nextMenus.some((nextMenu)=>{
                        if(routeName==nextMenu.urlName){ 
                            this.openMenus=[menu.id];
                            this.menuActiveName=nextMenu.urlName;
                            return true;
                        }    
                    });
                }else if(routeName==menu.urlName){ 
                    this.menuActiveName=menu.urlName;
                    return true;
                }
            });
        },
        topSelectMenu(name){//顶部菜单选中事件
           this.leftMenus=this.menus[name].leftMenus==undefined || null?[]:this.menus[name].leftMenus;
        },
        setDefaultMenu(){//设置默认菜单
            for(var i in this.menus){
                var menu=this.menus[i];
                this.topMenuActiveName=menu.id;
                this.leftMenus=menu.leftMenus;
                break;
            }
        }
    },
    mounted(){
       
    }

  }
</script>
