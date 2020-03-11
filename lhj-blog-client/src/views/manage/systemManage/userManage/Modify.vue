<style>
  .user-modify-panel .ivu-tabs-tabpane{
    max-height:590px;
    overflow-X: auto;
  }
  .user-modify-panel .ivu-tabs-nav{
    height:60px;
    line-height:40px;
  }
</style>
<template>
  
  <div class="content-panel" >

    <div class="content-card">
      <div class="header" >
        <span class="title">
          <Icon :type="$common.icon.modify" />
          修改用户
        </span>
        
        <span class="right-tool" style="float:right">
          <Button  type="info" ghost @click="save"  :icon="$common.icon.save" size="small" >保存</Button>
          <Button  type="info" ghost @click="cancel"  :icon="$common.icon.cancel"  size="small" >取消</Button>
          <Button  type="info" ghost @click="formReset('dataForm')"  :icon="$common.icon.clean" size="small" >重置</Button>
          <Button  type="info" ghost @click="formReset"  :icon="$common.icon.refresh" size="small" >刷新</Button>
        </span>
      </div>
      <div class="card-body" style="padding:0 25px;">

      <div class="user-modify-panel">
        <Tabs style="border:1px solid #dcdee2;margin-top:25px;" >
            <TabPane label="基本信息" icon="logo-windows">
              
              <Form ref="dataForm" :model="row" :label-width="120" style="padding:20px;">
                <Row>
                  <Col span="6">
                    <FormItem label="用户cd" prop="userCd">
                      <Input type="text" v-model="row.userCd" placeholder="请输入用户cd...">
                      </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="用户名称"  prop="userName">
                        <Input type="text"  v-model="row.userName" placeholder="请输入用户名称...">
                        </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="手机号码"  prop="phoneNumber">
                        <Input type="text"  v-model="row.phoneNumber" placeholder="请输入手机号码...">
                        </Input>
                    </FormItem>
                  </Col>
                  <Col span="6" style="text-align:right">
                    <FormItem label="邮箱"  prop="email">
                      <Input type="text"  v-model="row.email" placeholder="请输入邮箱...">
                      </Input>
                    </FormItem>
                  </Col>
                </Row>

                <Row>
                  <Col span="6" style="text-align:right">
                    <FormItem label="性别"  prop="sex">
                      <Input type="text"  v-model="thisRow.sex" placeholder="请输入性别...">
                      </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="状态"  prop="status">
                        <Input type="text"  v-model="row.status" placeholder="请输入状态...">
                        </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="国家" prop="countryCd">
                      <Input type="text" v-model="row.countryCd" placeholder="请输入国家...">
                      </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="地址"  prop="address">
                        <Input type="text"  v-model="row.address" placeholder="请输入地址...">
                        </Input>
                    </FormItem>
                  </Col>
                  
                </Row>
                <Row>
                  <Col span="24">
                    <FormItem label="备注" prop="notes">
                      <Input type="textarea" maxlength="500" show-word-limit v-model="row.notes" placeholder="请输备注..." rows='5'>
                      </Input>
                    </FormItem>
                  </Col>
                  
                </Row>
              </Form>

            </TabPane>
            <TabPane label="账户信息" icon="logo-apple" >
              账户信息
            </TabPane>
            <TabPane label="角色" icon="logo-tux">
              角色信息
            </TabPane>
             <TabPane label="机构" icon="logo-tux">
              机构
            </TabPane>
        </Tabs>
      </div>
        

      </div>
        
    </div>
    
    <!-- loadding 防止多次点击 -->
    <Spin fix v-if="loading">
        <Icon type="ios-loading" size=18 class="spin-icon-load"></Icon>
        <div>数据保存中请稍后</div>
    </Spin>
    
  </div>

</template>

</template>

<script>

  export default {
    name: 'userModify',
    data () {
      return {
        loading:false,//laoding 标识
        row:{
          sid:'',
          userCd:'',
          userName:'',
          sex:'',
          countryCd:'',
          address:'',
          phoneNumber:'',
          email:'',
          status:'',
          notes:'',
        }
      }
    },
    props:{
      thisRow:Object,//父页面传过来的参数
    },
    components:{//注册组件
      
    },
    beforeCreate(){//拿不到任何信息，无法篡改数据，一般做loding，这个时候的vue实例还什么都没有，但是$route对象是存在的，可以根据路由信息进行重定向之类的操作
      
    },
    created() {//el 没有初始化，数据已加载完成，阔以篡改数据，并更新，不会触发，，在这结束，还做一些初始化，实现函数自执行，ref属性内容为空数组
      //alert(JSON.stringify());
    },
    beforeMount(){//$el已被初始化,，数据已加载完成，阔以篡改数据，并更新，不会触发beforeUpdate，updated，在挂载开始之前被调用，beforeMount之前，会找到对应的template，并编译成render函数
    },
    methods:{//el 已被初始化，数据已加载完成，阔以篡改数据，并更新，并且触发，，在这发起后端请求，拿回数据，配合路由钩子做一些事情，ref属性可以访问
      save(){//保存
        let _this=this;
        this.loading=true;

        this.axios.post('/sysUser/save',JSON.stringify({}))
        .then((response)=>{
          this.loading=false;
          let row = response.data;
          if(row.success){
           
          }else{
            _this.$Message.info(row.message);
          }
        })
        .catch((error)=>{
          this.loading=false;
          _this.$Modal.error({title: '异常',content: error});
        });

      },
      cancel(){//取消
        this.$emit('chindrenChangeData',{showPage:"list"});//调用父级方法
      },
      formReset(refName){//重置
        //alert(refName);
        this.$refs[refName].resetFields();
      },
      /* cancel(){//刷新
        this.$emit('chindrenChangeData',{showPage:"list"});//调用父级方法
      } */
    },
    mounted(){
      this.row = this.thisRow;
    },
    destroyed(){
    
    }

  }

</script>
