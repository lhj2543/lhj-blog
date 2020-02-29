// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ViewUI from 'view-design'//引入iview 4.0.0后版本
import 'view-design/dist/styles/iview.css'//引入 iview 样式
import '@/assets/css/common.css'//引入自定义公共样式
import '@/assets/icon_custom/iconfont.css'//引入定义图标

Vue.config.productionTip = false
Vue.use(ViewUI)// 使用ivew

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
