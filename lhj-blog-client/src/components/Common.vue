

<script type="text/javascript">
    
    var commomObj= {
        
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
                    window.localStorage.removeItem('sysAccountInfo');
                    _this.$router.push({path:'/login'});
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
        getAccountInfo:(params)=>{//获取账户信息
            let result = {};
            if(window.localStorage && window.localStorage.getItem(this.a.tokenKey)!=undefined){
                result = JSON.parse(window.localStorage.getItem(this.a.tokenKey));
            }
            return result;
        }

    }

    // 暴露出这些属性和方法
    export default commomObj;

</script>