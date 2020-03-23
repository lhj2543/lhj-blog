
import Vue from 'vue'

import axios from '@/config/axiosConfig' //引入axios 全局配置
import VueAxios from 'vue-axios' //vue-axios是将axios集成到Vue.js的小包装器
Vue.use(VueAxios,axios);



var commomObj= {
    
    
    install(v, options) {
        Vue.prototype.getSysItems = (params,callback) => {//加载字典数据 
            
            let _this = Vue;
                
            _this.axios.post('/sysItem/getSysItemByCategoryName',JSON.stringify(params))
            .then((response)=>{
                let row = response.data;
                if(row.success){
                    callback(row);
                }else{
                    alert(row.message);
                    //Vue.$Message.info(row.message);
                }
            })
            .catch((error)=>{
                alert(error);
                //Vue.$Modal.error({title: '异常',content: error});
            });

        }
    },



    icon:{//全局图标定义
        logo:'iconfont icon-icon-logo',//logo
        add:'ios-add',//新增
        modify:'ios-create-outline',//修改
        detail:'ios-browsers',//详情
        delete:'ios-trash-outline',//删除
        remover:'',//移除
        search:'ios-search',//搜索
        list:'ios-list-box-outline',//列表
        refresh:'md-sync',//刷新
        back:'ios-undo-outline',//返回
        save:'ios-albums-outline',//保存
        cancel:'ios-undo-outline',//取消
        clean:'ios-refresh',//清除 重置
        close:'',//关闭
        full:'',//全屏
        
    },
    tokenKey:'sysAccountInfo',//localStorage 存储用户信息key
    webSitRoules:[],//站点路由数据
    webSitMenus:[],//站点菜单数据
    manageRoules:[],//后台管理路由数据
    manageMenus:{},//后台管理菜单数据
    
    logout:(params)=>{//登出
        let _this = params._this;
        _this.axios.get('/logout')
        .then((response)=>{
            let data = response.data;
            if(data.success){
                _this.$common.cleanToken();
                //_this.$router.push({path:'/login'});
                location.href='/river/login';
            }else{
                    _this.$Message.error(data.message);
            }
        })
        .catch((error)=>{
            alert(error);
        });
    },
    setToken:(value)=>{//设置token
        if(window.localStorage){
            window.localStorage.setItem(this.a.tokenKey,JSON.stringify(value));
        }
    },
    getToken:(params)=>{//获取token
        let result = '';
        if(window.localStorage && window.localStorage.getItem(this.a.tokenKey)!=undefined){
            let sysAccountInfo = JSON.parse(window.localStorage.getItem(this.a.tokenKey));
            result =  sysAccountInfo.token;
        }
        return result;
    },
    cleanToken:()=>{//清除token
        if(window.localStorage){
            window.localStorage.removeItem(this.a.tokenKey);
        }
    },
    getAccountInfo:(params)=>{//获取账户信息
        let result = {};
        if(window.localStorage && window.localStorage.getItem(this.a.tokenKey)!=undefined){
            result = JSON.parse(window.localStorage.getItem(this.a.tokenKey));
        }
        return result;
    },

}

// 暴露出这些属性和方法
export default commomObj;
