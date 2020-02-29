<template>
    <header id="index-header">
      <Affix :offset-top="0" >
          <div  class="row-header " :class="{ header_offset: header_offset_active }" >
              <Row class="content-width">
                <Col span="4" class="cursor-pointer">
                  <Avatar  size="45" icon="md-bonfire" class="logo"></Avatar>
                  <span class="logo-title ">Blog</span>
                </Col>
                <Col span="20" class="header-menu-itme">
                  <a v-for="headerMenu in headerMenus" :id="headerMenu.id" v-html="headerMenu.name"  :style="{cursor:headerMenu.cursor}" ></a>
                </Col>
              </Row>
          </div>
      </Affix>
    </header>
</template>


<script>


export default {
  name: 'index-header',
  data(){
    return {
      header_offset_active:false,//滚动时顶部栏样式标识
      headerMenus:[
        {id:1,name:'首 页',url:''},
        {id:2,name:'后台管理',url:''},
        {id:3,name:"帮 助",url:""},
        {id:4,name:'关 于',url:''},
        {id:5,name:'<span class="split-line" style="padding:1px 0px;border-left: 1px solid #fff;"></span>',url:'',cursor:'default'},
        {id:6,name:'登 录',url:''},
        {id:7,name:'注 册',url:""},
      ]
    }
  },
  mounted () {//钩子
    window.addEventListener('scroll', this.handleScroll)
  },
  methods: {
    handleScroll (){
        var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
        scrollTop>0?this.header_offset_active=true:this.header_offset_active=false;
    }
  },
  destroyed () {
    window.removeEventListener('scroll', this.handleScroll);
  },

}
</script>


<style scoped>
  #index-header{
    width: 100%;
    position: fixed;
    z-index: 99999999;
  }
  .row-header{
    height:80px;
    line-height: 80px;
  }
  .row-header *{
    color: #fff;
  }
  .header_offset{
    background-color: rgba(0, 0, 0, 0.5)
  }
  .logo{
    background-color:#fff;color:#000;margin-top:-8px;
  }
  .logo-title{
    font-size:25px;
    font-family: Lucida Calligraphy, Helvetica, Arial, sans-serif;
    font-weight: bold;
  }
  .header-menu-itme{
    text-align: right;
  }
  .header-menu-itme a{
    padding:5px 15px;
    font-size:16px;
    /* font-family: Circular, Helvetica, Arial, sans-serif; */    
    /* color: #d9dadc; */
    font-weight: bold;
  }
  .header-menu-itme a:hover{
    color: #1DB954;
  }
  .split-line{
    padding:1px 0px;
    border-left: 1px solid #fff;
  }
</style>
