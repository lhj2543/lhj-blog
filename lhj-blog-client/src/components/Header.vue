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
              {{row.name}}
              <!-- <router-link target="_blank" :to="{path:'/welcome'}" :style="{color:'#000'}">后台管理</router-link> -->
            </span>

            <span class="top-mnues ">|</span>

            <span class="top-mnues cursor-pointer hove-color" >
              <!-- <Icon type="ios-person-outline" /> -->
              登录
              <!-- <router-link target="_blank" :to="{path:'/welcome'}" :style="{color:'#000'}">后台管理</router-link> -->
            </span>
            <!-- <span class="top-mnues cursor-pointer hove-color" >
              注册
            </span> -->
            

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
      headerMenus:[
        {id:'sy_1',name:'首页',url:''},
        {id:'xmzp_2',name:'项目作品',url:''},
        {id:'zyjn_3',name:'专业技能',url:''},
        {id:'sygj_4',name:'实用工具',url:''},
        {id:'lyyj_5',name:'留言意见',url:''},
      ]
    }
  },
  props:{
      contentActiveTheme:String,
  },
  mounted () {//钩子
  },
  methods: {
    moreMnueOpen(ev){
      this.$emit('openMnue',ev);//调用父级openMnue
    }
  },
  destroyed () {
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
