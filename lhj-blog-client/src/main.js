// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ViewUI from 'view-design'//引入iview 4.0.0后版本
import 'view-design/dist/styles/iview.css'//引入 iview 样式
import '@/assets/css/common.css'//引入自定义公共样式
import '@/assets/icon_custom/iconfont.css'//引入定义图标

import axios from './config/axiosConfig' //引入axios 全局配置
import VueAxios from 'vue-axios' //vue-axios是将axios集成到Vue.js的小包装器
Vue.use(VueAxios,axios);

Vue.config.productionTip = false
Vue.use(ViewUI)// 使用ivew

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
