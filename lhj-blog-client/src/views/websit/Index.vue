<template>

  <div id="index-body" class="index-body"  :class="{'mnue-more-open':mnueMoreOpenFlag}" >

    <div class="mnue-more-panel">
      <div class="mnue-more-top">
        <span><Icon class="" type="md-list" style="vertical-align:middle;" /> 菜单</span>
        <Icon type="ios-close"  class="mnue-close cursor-pointer" @click="closeMnue" />
      </div>

      <Row class="mnue-more-row">
        <Col span="12" class="mnue-more-item" >
          <div class="mnue-more-icon-div"><Icon type="ios-home-outline" class="mnue-more-icon" /></div>
          <div class="mnue-more-title">首页</div>
        </Col>
        <Col span="12" class="mnue-more-item" style="border-right:none;" >
          <div class="mnue-more-icon-div"><Icon type="ios-bulb-outline" class="mnue-more-icon" /></div>
          <div class="mnue-more-title">个人简历</div>
        </Col>
      </Row>

      <Row class="mnue-more-row">
        <Col span="12" class="mnue-more-item" >
          <div class="mnue-more-icon-div"><Icon type="ios-cafe-outline" class="mnue-more-icon" /></div>
          <div class="mnue-more-title">作品展示</div>
        </Col>
        <Col span="12" class="mnue-more-item" style="border-right:none;" >
          <div class="mnue-more-icon-div"> <Icon type="ios-mail-outline" class="mnue-more-icon" /></div>
          <div class="mnue-more-title">联系我们</div>
        </Col>
      </Row>

    </div>

    <!-- 遮罩层 -->
    <div class="shade" :class="[mnueMoreOpenFlag?'display-block':'display-none']"  @click="closeMnue" ></div>

    <div id="index" class="index" >
      <!-- 头部 -->
      <!--或者这种写法也行<AppHeader/> -->
      <app-header v-on:openMnue="openMnue" :contentActiveTheme="contentActiveTheme" />

      <!-- ======================右侧切换条=============== -->
      <div class="right-tab-panel plan-right-centre theme-0" >
        <div :key="index" v-for="(row,index) in contentActives" :class="[row.className=='active'?'active':'']" @click="rightTabClick(index)" class="right-tab"></div>
      </div>
      <!-- ======================右侧切换条=============== -->

      <!-- ===============首页内容-1============== -->
      <div class="index-content index-content2 theme-1"  :class="contentActives[0].className" >
        
        <div class="content2-main-panel plan-centre">
          <div style="font-size:3rem;padding:2rem 0;">——欢迎进入个人主页</div>
          <div style="font-size:1.8rem;">所谓天才，只不过是把别人喝咖啡的功夫都用在工作上罢了。</div>
          <div class="resume-deail cursor-pointer" style="margin-top: 9.5rem;color: #F4CD29;" >个人详情</div>
        </div>

      </div>
      <!-- ===============首页内容-1============== -->

      <!-- ===============首页内容-2============== -->
      <div class="index-content index-content3 theme-1"  :class="contentActives[1].className"  >
        
        <div class="content3-topbg">
          
        </div>

        <div class="plan-centre content3-main-panel">

          <div class="card-item cursor-pointer"  >
            <div class="content3-title ">——项目作品展览</div>
            <div class="card-item-panel">
                <div class="card-img"><img width="100%" height="100%" src="/static/index/featured35.jpg" /></div>
                  <div class="card-detail">
                    <div class="card-detail-desc">Top landmarks & amazing</div>
                    <div class="card-detail-name">Yacht Tour</div>
                  </div>
                  <div class="card-info">
                    Right to find fault with a man who chooses to enjoy a pleasure that has annoying 
                  </div>
            </div>
          </div>

          <div class="card-item cursor-pointer"  >

            <div class="card-item-panel">
              <div class="card-img"><img width="100%" height="100%" src="/static/index/featured36.jpg" /></div>
                <div class="card-detail">
                  <div class="card-detail-desc">Top landmarks & amazing</div>
                  <div class="card-detail-name">Yacht Tour</div>
                </div>
                <div class="card-info">
                  Right to find fault with a man who chooses to enjoy a pleasure that has annoying 
                </div>
            </div>

          </div>
          
          <div class="card-item cursor-pointer"  >
            <div class="content3-more hove-color">查看更多</div>
            <div class="card-item-panel">
              <div class="card-img"><img width="100%" height="100%" src="/static/index/featured37.jpg" /></div>
              <div class="card-detail">
                <div class="card-detail-desc">Top landmarks & amazing</div>
                <div class="card-detail-name">Yacht Tour</div>
              </div>
              <div class="card-info">
                Right to find fault with a man who chooses to enjoy a pleasure that has annoying 
              </div>
            </div>
          </div>

        </div>

      </div>
      <!-- ===============首页内容-2============== -->

       <!-- ===============首页内容-4============== -->
      <div class="index-content index-content4"  :class="contentActives[2].className"  >
        
        <div class="plan-centre content4-main-panel">

          <div class="card-item" v-for="(row,index) in indexData.skill" :key="row.id" >
              <div class="cnt4-icon-d"><Icon :type="row.icon" class="cnt4-icon" /></div>
              <div class="cnt4-title">
                <div class="cnt4-title-name">{{row.name}}</div>
                <div class="cnt4-title-desc">{{row.desc}}</div>
              </div>
              <div class="cnt4-button-d">
                <span class="content4-button cursor-pointer">查看详细</span>
              </div>
          </div>
          
        </div>

      </div>
      <!-- ===============首页内容-4============== -->

      <!-- ===============首页内容-5============== -->
      <div class="index-content index-content5"  :class="contentActives[3].className"  >
        <div class="content5-title plan-centre">
          实用工具
        </div>

        <div class="plan-centre content5-main-panel">

          <div class="content5-card-panel">
            
            <div class="content5-card-item">
              <div class="content5-card-item-left" >
                <img src="/static/index/featured-image-4.jpg" width="100%" height="100%"> 
              </div>
              <div class="content5-card-item-right">
                <div class="info title">
                    Office 在线转换工具
                    <div class="title-icon" style="float:right">
                      <Icon type="md-heart-outline" />
                    </div>
                </div>
                <div class="info descs">
                    1.支持 Word / Excel / PPt 转Pdf<br/>
                    2.支持 image 转Pdf<br/>
                    3.支持 Pdf 转 Word 等

                </div>
                <div class="info " style="padding-top:1.8rem;">
                  <span class="button cursor-pointer">欢 迎 使 用</span>
                  <span style="float:right;margin-top:-0.5rem"><span style="color:#7f8a99;">Create Date</span><br>2019-02-16</span>
                </div>
              </div>
            </div>

            <div class="content5-card-item">
              <div class="content5-card-item-left" >
                <img src="/static/index/featured-image-5.jpg" width="100%" height="100%"> 
              </div>
              <div class="content5-card-item-right">
                <div class="info title">
                    Office 在线转换工具
                    <div class="title-icon" style="float:right">
                      <Icon type="md-heart-outline" />
                    </div>
                </div>
                <div class="info descs">
                    1.支持 Word / Excel / PPt 转Pdf<br/>
                    2.支持 image 转Pdf<br/>
                    3.支持 Pdf 转 Word 等

                </div>
                <div class="info " style="padding-top:1.8rem;">
                  <span class="button cursor-pointer">欢 迎 使 用</span>
                  <span style="float:right;margin-top:-0.5rem"><span style="color:#7f8a99;">Create Date</span><br>2019-02-16</span>
                </div>
              </div>
            </div>

            <div class="content5-card-item">
              <div class="content5-card-item-left" >
                <img src="/static/index/featured-image-4.jpg" width="100%" height="100%"> 
              </div>
              <div class="content5-card-item-right">
                <div class="info title">
                    Office 在线转换工具
                    <div class="title-icon" style="float:right">
                      <Icon type="md-heart-outline" />
                    </div>
                </div>
                <div class="info descs">
                    1.支持 Word / Excel / PPt 转Pdf<br/>
                    2.支持 image 转Pdf<br/>
                    3.支持 Pdf 转 Word 等

                </div>
                <div class="info " style="padding-top:1.8rem;">
                  <span class="button cursor-pointer">欢 迎 使 用</span>
                  <span style="float:right;margin-top:-0.5rem"><span style="color:#7f8a99;">Create Date</span><br> 2019-02-16</span>
                </div>
              </div>
            </div>

            <div class="content5-card-item">
              <div class="content5-card-item-left" >
                <img src="/static/index/featured-image-5.jpg" width="100%" height="100%"> 
              </div>
              <div class="content5-card-item-right">
                <div class="info title">
                    Office 在线转换工具
                    <div class="title-icon" style="float:right">
                      <Icon type="md-heart-outline" />
                    </div>
                </div>
                <div class="info descs">
                    1.支持 Word / Excel / PPt 转Pdf<br/>
                    2.支持 image 转Pdf<br/>
                    3.支持 Pdf 转 Word 等

                </div>
                <div class="info " style="padding-top:1.8rem;">
                  <span class="button cursor-pointer">欢 迎 使 用</span>
                  <span style="float:right;margin-top:-0.5rem"><span style="color:#7f8a99;">Created Date</span><br>2019 - 02 - 16</span>
                </div>
              </div>
            </div>
            

          </div>

        </div>

        <div class="content5-bottom-panel">
          <div class="content5-bottom-card">

            <div class="content5-bottom-card-item">

              <div class="icon-box">
                <diV class="octagonWrap">
                    <div class="octagon">
                    </div>
                    <Icon type="ios-cafe-outline" slot="prepend" class="octagonWrap-icon"></Icon>
                </diV>
              </div>
              
              <div class="content">
                <div class="content-title">Best Equipments</div>
                <div class="content-desc">Perfectly simple & easy to in our hour, when our power.</div>
                <div class="content-more"><Icon type="ios-arrow-round-forward" />More About Surfing</div>
              </div>

            </div>
            
            <div class="content5-bottom-card-item">

              <div class="icon-box">
                <diV class="octagonWrap">
                    <div class="octagon">
                    </div>
                    <Icon type="ios-bulb-outline" slot="prepend" class="octagonWrap-icon"></Icon>
                </diV>
              </div>
              
              <div class="content">
                <div class="content-title">Best Equipments</div>
                <div class="content-desc">Perfectly simple & easy to in our hour, when our power.</div>
                <div class="content-more"><Icon type="ios-arrow-round-forward" />More About Surfing</div>
              </div>

            </div>

            <div class="content5-bottom-card-item">

              <div class="icon-box">
                <diV class="octagonWrap">
                    <div class="octagon">
                    </div>
                    <Icon type="ios-mail-outline" slot="prepend" class="octagonWrap-icon"></Icon>
                </diV>
              </div>
              
              <div class="content">
                <div class="content-title">Best Equipments</div>
                <div class="content-desc">Perfectly simple & easy to in our hour, when our power.</div>
                <div class="content-more"><Icon type="ios-arrow-round-forward" />More About Surfing</div>
              </div>

            </div>
            
          </div>
        </div>

      </div>
      <!-- ===============首页内容-5============== -->

      <!-- ===============首页内容-6============== -->
      <div class="index-content index-content6 theme-1"  :class="contentActives[4].className"  >
        
        <div class="content6-topbg">
          <div class="" style="width:80%;height:10rem;border:1px solid red1;margin:0 auto;position: relative;top:35%;">
            <div class="" style="font-size:3rem;">鲁迅诗集</div>
            <div class="" style="font-size:1.5rem;">所谓天才，只不过是把别人喝咖啡的功夫都用在工作上罢了。</div>
          </div>
        </div>

        <div class="content6-main-panel">
          <div class="content6-left">
            <table width="100%" height="100%" >
              <tr>
                <td height='100%' width="60%" >
                  <div class="content6-left-right"></div>
                </td>
                <td width="3%" ></td>
                <td width="40%" >
                  <div class="content6-left-title">
                    <div>留言</div>
                    <span style="font-size:1.5rem;">您有何见解，不吝赐教！</span>
                  </div>
                </td>
              </tr>
            </table>
          </div>
          <div class="content6-right">
              <div class="leaving-panel">
                <Form  ref="formInline" >
                    <div class="leavin-div">
                       <Icon type="ios-person-outline" slot="prepend" class="leavingName-icon"></Icon>
                      <input class="leavingName" name="leavingName " placeholder="留言人" value="游客" />
                    </div>
                    <div class="leavin-div">
                      <textarea class="leavingContent" name="leavingContent " placeholder="请输入留言内容.." />
                    </div>
                    <div class="leavin-div leavingBut">
                      <div style="float:right">
                      <span class="content4-button cursor-pointer" style="background:#D5AE82;">留 言</span>
                      </div>
                    </div>
                </Form>
              </div>

          </div>
        </div>
          

      </div>
      <!-- ===============首页内容-6============== -->
      

      <!-- ===============首页内容-6============== -->
      <!-- <div class="index-content"  :class="contentActives[4].className">
        <div  class="index-content1 plan-centre">
          <div class="left-bg-img" >
            <img src="/static/index/graffiti-5.jpg" width="100%" height="100%" />
          </div>

          <div class="business-card cursor-pointer">
            <Row class="personal-plan">
              <Col span="15" class="personal-info">
                <div class="personal-info-detial plan-right-centre">
                  <div class="user-name"> 刘海江 </div>
                  <div class="welcome-speech">欢迎进入我的个人主页</div>
                  <div class="resume-deail">个人详情</div>
                </div>
              </Col>
              <Col span="9" class="head-portrait" >
                <Avatar class="head-portrait-avatar plan-left-centre" src="/static/index/header-2.jpg" />
              </Col>
            </Row>
          </div>
        </div>
      </div> -->
      <!-- ===============首页内容-6============== -->


      <!-- 尾部 -->
      <app-footer :contentActiveTheme="contentActiveTheme" />
    </div>

  </div>

</template>

<script>
import '@/assets/css/index.css'//引入首页样式
import AppHeader from './Header.vue'//引入头部
import AppFooter from './Footer.vue'//引入头部


export default {
  name: 'Index',
  data () {
          return {
             mnueMoreOpenFlag:false,
             contentActiveIndex:0,//当前选中内容标识
             contentActiveTheme:'theme-1',//主题
             contentActives:this.initContentActives(),
             indexData:{
               skill:[//专业技能
                {id:'skill1',name:'jQery',desc:'jQuery 是一个JS库，极大地简化了JS编程',icon:'ios-home-outline',url:''},
                {id:'skill2',name:'Vue.js',desc:'jQuery 是一个JS库，极大地简化了JS编程',icon:'ios-bulb-outline',url:''},
                {id:'skill3',name:'Html',desc:'jQuery 是一个JS库，极大地简化了JS编程',icon:'ios-cafe-outline',url:''},
                {id:'skill4',name:'Css3',desc:'jQuery 是一个JS库，极大地简化了JS编程',icon:'ios-mail-outline',url:''},
                {id:'skill5',name:'JavaScript',desc:'jQuery 是一个JS库，极大地简化了JS编程',icon:'ios-home-outline',url:''},
                {id:'skill6',name:'Java',desc:'jQuery 是一个JS库，极大地简化了JS编程',icon:'ios-bulb-outline',url:''},
                {id:'skill7',name:'MySql',desc:'jQuery 是一个JS库，极大地简化了JS编程',icon:'ios-cafe-outline',url:''},
                {id:'skill8',name:'Spring Boot',desc:'jQuery 是一个JS库，极大地简化了JS编程',icon:'ios-mail-outline',url:''},
               ]
             }
          }
        },
  components:{//注册组件
    AppHeader,
    AppFooter
  },
  beforeCreate(){//拿不到任何信息，无法篡改数据，一般做loding，这个时候的vue实例还什么都没有，但是$route对象是存在的，可以根据路由信息进行重定向之类的操作
    
  },
  created() {//el 没有初始化，数据已加载完成，阔以篡改数据，并更新，不会触发，，在这结束，还做一些初始化，实现函数自执行，ref属性内容为空数组
    
  },
  beforeMount(){//$el已被初始化,，数据已加载完成，阔以篡改数据，并更新，不会触发beforeUpdate，updated，在挂载开始之前被调用，beforeMount之前，会找到对应的template，并编译成render函数
  },
  methods:{//el 已被初始化，数据已加载完成，阔以篡改数据，并更新，并且触发，，在这发起后端请求，拿回数据，配合路由钩子做一些事情，ref属性可以访问
    
    initContentActives(){//数据初始化
      var result = new Array(5);
      for(var i=0;i<result.length;i++){
        var className = i==0?'active':'activeUp';
        var theme = 'theme-1';
        if(i==1){
          theme='theme-0  color-white';
        }
        if(i==2){
          theme='theme-0  top-fied';
        }else if(i==4 || i==3){
          theme='theme-1  top-fied';
        }

        result[i] = {className:className,theme:theme};
      }
      return result;
    },
    pageResize(event){ //计算窗口大小，自适应布局

      var e = {
                current: window.innerHeight,
                /* limit: window.innerWidth < 768 ? window.innerWidth < window.innerHeight ? 1280 : 602 : 1600, */
                limit: window.innerWidth < 768 ? window.innerWidth < window.innerHeight ? 1600 : 1600 : 1600,
                /* limit: 1280, */
                scale: 0
              },
          t = {
          current: window.innerWidth,
          limit: window.innerWidth < 768 ? window.innerWidth < window.innerHeight ? 2560 : 2560 : 2560,
          /* limit: 2560, */
          scale: 0
          };
          /* alert(e.limit);
          alert(t.limit); */
          e.scale = e.current / e.limit;
          t.scale = t.current / t.limit;
          var n = 22.5 * Math.min(e.scale, t.scale);
          /* var n = 32 * Math.min(e.scale, t.scale); */
          document.documentElement.style.fontSize = n + "px";
    },
    closeMnue(ev){//关闭右侧菜单
      ev.stopPropagation();
			ev.preventDefault();
      this.mnueMoreOpenFlag = false;
    },
    openMnue(ev){//打开右侧菜单
      ev.stopPropagation();
			ev.preventDefault();
      this.mnueMoreOpenFlag = true;
    },
    rightTabClick(index,type){
      if(index>this.contentActiveIndex || type=='down'){
        //改变数组数据立即dom立即生效 两种方式
        /* this.contentActives.splice(index, 1, {className:'activeUp'}); */
        this.$set(this.contentActives, index, {className:'activeUp',theme:this.contentActives[index].theme});

        setTimeout(()=>{
          this.contentActives[this.contentActiveIndex].className='activeDown delay';
          this.contentActives[index].className='active';
          this.contentActiveIndex = index;
          this.contentActiveTheme=this.contentActives[index].theme;
        },100);

      }else if(index<this.contentActiveIndex){
        //改变数组数据立即dom立即生效 两种方式
        /* this.contentActives.splice(index, 1, {className:'activeUp'}); */
        this.$set(this.contentActives, index, {className:'activeDown',theme:this.contentActives[index].theme});
        setTimeout(()=>{
          this.contentActives[this.contentActiveIndex].className='activeUp delay';
          this.contentActives[index].className='active';
          this.contentActiveIndex = index;
          this.contentActiveTheme=this.contentActives[index].theme;
        },100);
      }
    },
    scrollUp(e,this_){
     //向上滚动
     if(this.contentActiveIndex-1 < 0){
       return;
     }
     var nexIndex = this.contentActiveIndex-1 < 0 ? this.contentActives.length-1 : this.contentActiveIndex-1;
     this.rightTabClick(nexIndex);
    },
    scrollDown(e,this_){
     //向下滚动
     var nexIndex = this.contentActiveIndex+1 >= this.contentActives.length? 0 : this.contentActiveIndex+1;
     this.rightTabClick(nexIndex,'down');
    },
    scrollListener(){
      //滚轮滚动事件监听
      var this_ = this;
      var scrollFunc=function(e){

        e = e || window.event;
        if (e.wheelDelta) {  //判断浏览器IE，谷歌滑轮事件
            if (e.wheelDelta > 0) { 
              this_.scrollDown(e,this_);
            }
            if (e.wheelDelta < 0) { 
                this_.scrollUp(e,this_);
            }
        } else if (e.detail) {  //Firefox滑轮事件
            if (e.detail> 0) { 
                this_.scrollDown(e,this_);
            }
            if (e.detail< 0) { 
                this_.scrollUp(e,this_);
            }
        }
        
        /*解决滚动时候多次调用问题 */
        document.removeEventListener('DOMMouseScroll',scrollFunc, false);
        window.onmousewheel = document.onmousewheel = null;
        setTimeout(()=>{
          this_.scrollListener();
        },800);

      };
      //给页面绑定滑轮滚动事件
      if (document.addEventListener) {
          document.addEventListener('DOMMouseScroll',scrollFunc, false);
      }
      //滚动滑轮触发scrollFunc方法
      window.onmousewheel = document.onmousewheel = scrollFunc;
      
    }
  },
  mounted(){

    this.pageResize();
    let _this = this;//赋值vue的this
    window.onresize = (e)=>{
　　　　//调用methods中的事件
      _this.pageResize(e);
    };

    this.scrollListener();//滚轮滚动监听

  },
  destroyed(){
    window.onresize = null;

    document.removeEventListener('DOMMouseScroll',scrollFunc, false);
    window.onmousewheel = document.onmousewheel = null;
  }

}
</script>

<style scoped>

</style>
