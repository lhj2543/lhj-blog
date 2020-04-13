<style scoped>
  .login{
    background: url('/static/index/login_bg.png');
    background-size: cover;
    background-position: center;
  }
  .login *{
    color: #3498db;
  }
  .login-title{
    text-align:center;padding:10px 0;
  }
  .login-button-panel{
    margin-top: 60px;
  }
  .login-button{
    font-size: 16px;
    padding: 10px 30px;
    border:1px solid #3498db;
    margin-right: 20px;
    cursor: pointer;
  }
  .login-input-panel{
    padding: 20px 0 ;
  }
  .login-input-label{
    
  }
  .login-input{
    background:none;border-top:none;border-left:none;border-right:none;border-bottom:2px solid #3498db;width:100%;height:40px;
  }
  .login-input.error{
    border-bottom:2px solid red !important; 
  }
</style>
<template>

  <div id="login" class="index-body login"  >

    <!-- 头部 -->
    <!--或者这种写法也行<AppHeader/> -->
    <app-header :contentActiveTheme="contentActiveTheme" />

    <!-- =============登录=============== -->
    <div class="login-panel">
      <h2 class="login-title">系统登录</h2>
      
      <div class="login-input-panel">
        <span class="login-input-label" >账户：</span>
        <input :class="{'login-input':true,'error':usercdError}" ref="usercd" v-model="loginForm.userCd"  /> 
      </div>

      <div class="login-input-panel">
        <span class="login-input-label" >密码：</span>
        <input type="password" ref="password" v-model="loginForm.password" :class="{'login-input':true,'error':passwordError}" /> 
      </div>

      <div class="login-button-panel">
        <span class="login-button" @click="login('loginForm')">
          登 录
        </span>

        <span class="login-button">
          注 册
        </span>

      </div>
    
      <!-- <Form ref="loginForm" :model="loginForm" :rules="loginFormValidate" :label-width="0">
          <FormItem label="" prop="userCd" >
              <Input type="text" v-model="loginForm.userCd" placeholder="请输入用户名...">
                <Icon type="ios-person-outline" slot="prepend"></Icon>
              </Input>
          </FormItem>
         
          <FormItem label=""  prop="password">
              <Input type="password" v-model="loginForm.password" placeholder="请输入密码...">
                <Icon type="ios-lock-outline" slot="prepend"></Icon>
              </Input>
          </FormItem>
          <FormItem style="text-align:center;">
              <Button type="primary" @click="login('loginForm')">登录</Button>
              <Button @click="loginReset('loginForm')" style="margin-left: 8px">重置</Button>
          </FormItem>
      </Form> -->

    </div>
    <!-- =============登录=============== -->


    <!-- 尾部 -->
    <app-footer :contentActiveTheme="contentActiveTheme" />

  </div>

</template>

<script>
import '@/assets/css/index.css'//引入首页样式
import AppHeader from './Header.vue'//引入头部
import AppFooter from './Footer.vue'//引入头部


export default {
  name: 'Login',
  data () {
          return {
             contentActiveTheme:'theme-1',//主题
             loginForm:{//登录表单
                userCd:'',//用户名
                password:'',//密码
                captcha:'',//验证码
                isForceLogin:'0' //是否强制登录 0=否 1=是
              },
              usercdError:false,
              passwordError:false,
              loginFormValidate:{//登录表单验证
                userCd: [
                    { required: true,message:'请输入用户名',validator: '', trigger: 'blur' }
                ],
                password: [
                    { required: true,message:'请输入密码',validator: '', trigger: 'blur' }
                ],
              },
              
             loginData:{} 
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
    
    login (refName) {//登录方法

        let usercd = this.$refs.usercd;
        let password = this.$refs.password;

        if(!usercd.value){
          this.usercdError = true;
        }else{
          this.usercdError = false;
        }
        if(!password.value){
          this.passwordError = true;
        }else{
          this.passwordError = false;
        }
        if(this.usercdError || this.passwordError){
          return;
        }


        //this.$refs[refName].validate((valid) => {

            let _this = this;
            
            //if (valid) {
              this.axios.post('/login', JSON.stringify(_this.loginForm),{headers: {'Content-Type': 'application/json;charset=UTF-8'}})
              .then(function (response) {
                let data = response.data;
                if(data.success && data.token){
                  _this.$common.setToken(data)//存储token 及用户信息
                  //登录成功跳转后台管理
                  //_this.$router.push({path:'/welcome'});
                  location.href='/welcome';
                }else{
                  if(data.message == '412'){

                    _this.$Modal.confirm({title: '登录警告',content: '该帐号已登录或已在其他设备登录，是否继续登录！'
                    ,onOk:()=>{
                      _this.loginForm.isForceLogin = '1';
                      _this.login('loginForm');
                    }});

                  }else{
                    //_this.$Message.error(data.message);
                    _this.$Modal.error({title: '登录失败',content: data.message});
                  }
                }
              })
              .catch(function (error) {
                _this.$Modal.error({title: '登录异常',content: error});
                //_this.$Message.error(error);
              });
              
            //} 
        //})
    },
    loginReset (refName) {//登录表单重置
        this.$refs[refName].resetFields();
    }

  },
  mounted(){

    let _this = this;

  },
  destroyed(){
   
  }

}
</script>

<style scoped>

</style>
