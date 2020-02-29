<template>
  <div id="index">
    <!--===================================图片滚动 开始===================================-->
    <div class="index-carousel">
      <Carousel
          v-model="carouselIndex"
          :autoplay="setting.autoplay"
          :autoplay-speed="setting.autoplaySpeed"
          :dots="setting.dots"
          :radius-dot="setting.radiusDot"
          :trigger="setting.trigger"
          :arrow="setting.arrow">

          <CarouselItem>
              <div class="carouse-item" style="background-image: url(/static/index/carousel-0.jpg)">
                <!--<img width="100%" height="" src="/static/index/carousel-0.jpg" />-->
              </div>
          </CarouselItem>
          <CarouselItem>
              <div class="carouse-item" style="background-image: url(/static/index/carousel-0.jpg)"></div>
          </CarouselItem>
          
      </Carousel>

      <div class="carousel-title" >
        <div class="motto">所谓天才，只不过是把别人喝咖啡的功夫都用在工作上了</div>
        <div class="source">Welcome to my personal blog system.</div>
      </div>
    </div>
    <!--===================================图片滚动 结束===================================-->

   <!--===================================主体内容 开始===================================-->
   <div class="index-content">
    <div class="content-width content-card">

      <Card class="card-item cursor-pointer">
        <div><img class="card-item-img" src="/static/index/card-1.jpg"/></div>
        <div>科技阿斯蒂风口浪尖拉菲</div>
      </Card>
      <Card class="card-item cursor-pointer">
        <div><img class="card-item-img" src="/static/index/card-2.jpg"/></div>
        <div>卡戴假发沙士大夫</div>
      </Card>
      <Card class="card-item cursor-pointer">
        <div><img class="card-item-img" src="/static/index/card-3.jpg"/></div>
        <div>卡戴假发沙士大夫</div>
      </Card>
      <Card class="card-item cursor-pointer">
        <div><img class="card-item-img" src="/static/index/card-4.jpg"/></div>
        <div>卡戴假发沙士大夫</div>
      </Card>

      <Card class="card-item cursor-pointer">
        <div><img class="card-item-img" src="/static/index/card-1.jpg"/></div>
        <div>科技阿斯蒂风口浪尖拉菲</div>
      </Card>
      <Card class="card-item cursor-pointer">
        <div><img class="card-item-img" src="/static/index/card-2.jpg"/></div>
        <div>卡戴假发沙士大夫</div>
      </Card>
      <Card class="card-item cursor-pointer">
        <div><img class="card-item-img" src="/static/index/card-3.jpg"/></div>
        <div>卡戴假发沙士大夫</div>
      </Card>
      <Card class="card-item cursor-pointer">
        <div><img class="card-item-img" src="/static/index/card-4.jpg"/></div>
        <div>卡戴假发沙士大夫</div>
      </Card>
      
      <Row></Row>

    </div>
   </div>
   <!--===================================主体内容 结束===================================-->


    <!-- 返回顶面-->
    <BackTop ></BackTop>
  </div>
</template>

<script>
import '@/assets/css/index.css'//引入首页样式

export default {
  name: 'Index',
  data () {
            return {
                carouselIndex: 0,
                setting: {
                    autoplay: false,
                    autoplaySpeed: 4000,
                    dots: 'inside',
                    radiusDot: false,
                    trigger: 'hover',
                    arrow: 'never'
                }
            }
        },
}
</script>

