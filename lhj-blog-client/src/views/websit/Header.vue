<template>
    <header id="index-header" :class="contentActiveTheme" >

      <Row class="header-row" >
        <Col span="4">
          <router-link target="" :to="{path:'/'}" >
            <span class="cursor-pointer" title="liuhaijiang.top">
              <Icon id="logo" class="logo"  color="" custom="iconfont icon-icon-logo" />
              <span class="log-name">RIVER</span>
            </span>
          </router-link>
        </Col>
        <Col span="20">
          <div class="mnue-more-r">
            
            <span class="top-mnues cursor-pointer hove-color" v-for="(row,index) in headerMenus" :key="row.id">
              <router-link target="_self" :to="{path:row.path}">{{row.displayName}}</router-link>
            </span>

            <span class="top-mnues ">|</span>

            <!-- 用户信息 -->
            <span v-if="sysAccountInfo.token!='' && sysAccountInfo.token!=undefined" >
                <span class="top-mnues " style="padding:0;">
                  <Icon type="ios-person-outline" />
                  {{sysAccountInfo.userCd}}
                </span>
                <span class="top-mnues cursor-pointer hove-color logout" @click="logout">注销</span>
            </span>
            <span v-else class="top-mnues cursor-pointer hove-color" >
              <router-link target="_self" :to="{path:'/login'}" >登录</router-link>
            </span>

            <span class="cursor-pointer" @click="moreMnueOpen"><Icon title="菜单" class="mnue-more " type="md-list" color=""  /></span>

          </div>
        </Col>
      </Row>
    </header>
</template>


<script>


export default {
  name: 'index-header',
  data(){
    return {
      sysAccountInfo:this.$common.getAccountInfo(),
      headerMenus:[
       /*  {id:'sy_1',name:'首页',url:''},
        {id:'xmzp_2',name:'项目作品',url:''},
        {id:'zyjn_3',name:'专业技能',url:''},
        {id:'sygj_4',name:'实用工具',url:''},
        {id:'lyyj_5',name:'留言意见',url:''}, */
      ]
    }
  },
  props:{
      contentActiveTheme:String,
  },
  beforeCreate(){//拿不到任何信息，无法篡改数据，一般做loding，这个时候的vue实例还什么都没有，但是$route对象是存在的，可以根据路由信息进行重定向之类的操作
    
  },
  created() {//el 没有初始化，数据已加载完成，阔以篡改数据，并更新，不会触发，，在这结束，还做一些初始化，实现函数自执行，ref属性内容为空数组
    //菜单生成
    let webSitRoules =this.$common.webSitRoules;
    let headerMenus = [];
    for(let i in webSitRoules){
      let roule = webSitRoules[i];
      let mate = roule.mate;
      if(mate && roule.mate.status != '0'){
        let menu = {
          id:mate.sid,
          displayName:mate.displayName,
          path:roule.path
        }
        headerMenus.push(menu);
      }
    }
    this.headerMenus = headerMenus;
  },
  beforeMount(){//$el已被初始化,，数据已加载完成，阔以篡改数据，并更新，不会触发beforeUpdate，updated，在挂载开始之前被调用，beforeMount之前，会找到对应的template，并编译成render函数
 
  },
  mounted () {//钩子

  },
  methods: {
    moreMnueOpen(ev){
      this.$emit('openMnue',ev);//调用父级openMnue
    },
    logout(){//用户注销
        this.$common.logout({_this:this});//公共注销方法
    },
  },
  destroyed () {//销毁

  },

}
</script>


<style scoped>

  .top-fied{
    background:#fff;
    border-bottom: 0.1rem solid #f0f0f0;
    -moz-box-shadow: 0 0 1rem #CBCBCB;
    -ms-box-shadow: 0 0 1rem #CBCBCB;
    -o-box-shadow: 0 0 1rem #CBCBCB;
    box-shadow: 0 0 1rem #CBCBCB;

    -webkit-transition: background 1s;
    -moz-transition: background 1s;
    -ms-transition: background 1s;
    -o-transition: background 1s;
    transition:  background 1s;
  }
  .theme-1.top-fied *{
    color: #000;
  }
  .theme-0.color-white *{
    color: #fff;
  }

  #index-header{
    padding: 0.5rem 2rem;
    position: absolute;
    width: 100%;
    top: 0;
    z-index: 100;
  }
  .header-row{
    line-height: 4rem;height:4rem;
  }
  .logo{
    font-size: 2.5rem;
    vertical-align: middle;
  }
  .log-name{
    font-size: 1.5rem;
    font-family: Bradley Hand ITC,High Tower Text;
    font-weight: bold;
  }
  .mnue-more-r{
    float: right;
  }
  .mnue-more{
    font-size: 1.5rem;
    vertical-align:middle;
    padding-left:0.5rem ;
  }
  .top-mnues{
    font-size: 1.1rem;
    font-weight: bold;
    padding: 0 1.5rem;
  }
  .top-mnues i{
    font-size: 1.5rem;
    vertical-align: middle;
  }


  
</style>
