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
        <span class="title" v-if="thisRow.pageType == 'modify'">
          <Icon :type="$common.icon.modify" />
          修改用户
        </span>
        <span class="title" v-else-if="thisRow.pageType == 'add'">
          <Icon :type="$common.icon.add" />
          新增用户
        </span>
        <span class="title" v-else >
          <Icon :type="$common.icon.detail" />
          用户详情
        </span>
        
        <span class="right-tool" style="float:right">
          <Button v-if="!isDisabled"  type="info" ghost @click="save"  :icon="$common.icon.save" size="small" >保存</Button>
          <Button  type="info" ghost @click="cancel"  :icon="$common.icon.cancel"  size="small" ><span v-if="isDisabled" >返回</span><span v-else >取消</span></Button>
          <!-- <Button  type="info" ghost @click="formReset('dataForm1')"  :icon="$common.icon.clean" size="small" >重置</Button> -->
          <Button  type="info" ghost @click="init"  :icon="$common.icon.refresh" size="small" >刷新</Button>
        </span>
      </div>
      <div class="card-body" style="padding:0 25px;">

      <div class="user-modify-panel">
        <Tabs style="border:1px solid #dcdee2;margin-top:25px;" v-model:value="tabsCheckValue" >
            <TabPane label="基本信息" name="userInfo"  icon="logo-windows">
              
              <Form ref="dataForm1" :model="row" :rules="form1Validate" :disabled="isDisabled" :label-width="120" style="padding:20px;">
                <Row>
                  <Col span="6">
                    <FormItem label="用户cd" prop="userCd">
                      <Input type="text" :disabled="isDisabled || userCdIsDisabled" v-model="row.userCd" placeholder="请输入用户cd...">
                      </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="用户名称"  prop="userName">
                        <Input type="text" :disabled="isDisabled" v-model="row.userName" placeholder="请输入用户名称...">
                        </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="手机号码"  prop="phoneNumber">
                        <Input type="text" :disabled="isDisabled" v-model="row.phoneNumber" placeholder="请输入手机号码...">
                        </Input>
                    </FormItem>
                  </Col>
                  <Col span="6" >
                    <FormItem label="邮箱"  prop="email">
                      <Input type="text" :disabled="isDisabled" v-model="row.email" placeholder="请输入邮箱...">
                      </Input>
                    </FormItem>
                  </Col>
                </Row>

                <Row>
                  <Col span="6" >
                    <FormItem label="性别"  prop="sex">
                      <RadioGroup v-model="row.sex" >
                        <Radio v-for="(value,key,index) in items['sex']" :key="key+'-'+value" :label="key">{{value}}</Radio>
                      </RadioGroup>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="状态"  prop="status">
                      <Select :disabled="isDisabled"  v-model="row.status" placeholder="请选择用户状态..." >
                        <Option  v-for="(value,key,index) in items['userType']" :key="key+'-'+value" :value="key" >{{value}}</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="国家" prop="countryCd">
                      <Input type="text" :disabled="isDisabled" v-model="row.countryCd" placeholder="请输入国家...">
                      </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="地址"  prop="address">
                        <Input type="text" :disabled="isDisabled" v-model="row.address" placeholder="请输入地址...">
                        </Input>
                    </FormItem>
                  </Col>
                  
                </Row>

                <Row v-if="thisRow.pageType=='detail'">
                  <Col span="6" >
                    <FormItem label="创建人"  prop="createdBy">
                      <Input type="text" :disabled="isDisabled" v-model="row.createdBy" placeholder="">
                      </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="创建时间"  prop="createdDate">
                        <Input type="text" :disabled="isDisabled" v-model="row.createdDate" placeholder="">
                        </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="修改人" prop="updateBy">
                      <Input type="text" :disabled="isDisabled" v-model="row.updateBy" placeholder="">
                      </Input>
                    </FormItem>
                  </Col>
                  <Col span="6">
                    <FormItem label="修改时间"  prop="updateDate">
                        <Input type="text" :disabled="isDisabled" v-model="row.updateDate" placeholder="">
                        </Input>
                    </FormItem>
                  </Col>
                </Row>

                <Row>
                  <Col span="24">
                    <FormItem label="备注" prop="notes">
                      <Input type="textarea" maxlength="500" :disabled="isDisabled" show-word-limit v-model="row.notes" placeholder="请输备注..." :rows="5" >
                      </Input>
                    </FormItem>
                  </Col>
                  
                </Row>
              </Form>

            </TabPane>
            <TabPane label="账户信息" name="accountInfo"  icon="logo-apple" >

              <Form ref="dataForm2" :model="row.sysAccount"  :rules="form2Validate" :disabled="isDisabled"  :label-width="220" style="padding:20px;">
                <Row>
                  <Col span="10">
                    <FormItem label="用户cd" prop="userCd">
                      <Input type="text" :disabled="isDisabled || userCdIsDisabled" v-model="row.userCd" placeholder="请输入用户cd...">
                      </Input>
                    </FormItem>
                  </Col>
                  <Col span="10">
                    <FormItem label="密码"  prop="password">
                        <Input type="password" :disabled="isDisabled"  v-model="row.sysAccount.password" placeholder="请输入密码...">
                        </Input>
                    </FormItem>
                  </Col>
                  <Col span="4"></Col>
                </Row>

                <Row>
                  <Col span="10">
                    <FormItem label="账户状态"  prop="licence">
                      <RadioGroup v-model="row.sysAccount.licence" >
                        <Radio v-for="(value,key,index) in items['isActive']" :key="key+'-'+value" :label="key">{{value}}</Radio>
                      </RadioGroup>
                    </FormItem>
                  </Col>
                  <Col span="10" >
                    <FormItem label="账户类型"  prop="type">
                      <Select :disabled="isDisabled"  v-model="row.sysAccount.type" placeholder="请选择账户类型..." >
                          <Option  v-for="(value,key,index) in items['accountType']" :key="key+'-'+value" :value="key" >{{value}}</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="4"></Col>
                </Row>

                <Row>
                  <Col span="10" >
                    <FormItem label="账户有效开始日期"  prop="start_date">
                      <!--  @on-change=”row.sysAccount.startDate=$event”  解决日期传后台格式化问题
                      日期在双向绑定之后格式为：2017-07-03T16:00:00.000Z  -->
                      <DatePicker type="date" format = "yyyy-MM-dd"  @on-change="row.sysAccount.startDate=$event"  placeholder="请选择日期..." :disabled="isDisabled" v-model="row.sysAccount.startDate"  style="width: 100%"></DatePicker>
                    </FormItem>
                  </Col>
                  <Col span="10">
                    <FormItem label="账户有效结束日期"  prop="end_date">
                        <!--  @on-change=”row.sysAccount.startDate=$event”  解决日期传后台格式化问题
                      日期在双向绑定之后格式为：2017-07-03T16:00:00.000Z  -->
                        <DatePicker type="date" format = "yyyy-MM-dd"  @on-change="row.sysAccount.endDate=$event" placeholder="请选择日期..." :disabled="isDisabled" v-model="row.sysAccount.endDate"  style="width: 100%"></DatePicker>
                    </FormItem>
                  </Col>
                  <Col span="4"></Col>
                </Row>

              </Form>

            </TabPane>
            <TabPane label="用户角色" name="userRoles" icon="logo-tux" style="padding:25px;">
              
              <!-- 列表 -->
              <Table style="padding-bottom:90px;" class="content-table" border :columns="userRolesColumns" :data="row.userRoles" ></Table>

            </TabPane>
            <!-- <TabPane label="机构" icon="logo-tux">
              机构
            </TabPane> -->
        </Tabs>
      </div>
        

      </div>
        
    </div>
    
    <!-- loadding 防止多次点击 -->
    <Spin fix v-if="loading">
        <Icon type="ios-loading" size=18 class="spin-icon-load"></Icon>
        <div>数据保存中请稍后</div>
    </Spin>

    <!-- 添加用户角色窗口 -->
    <Modal  v-model="isShowUserRoleModel"  @on-ok="modalParams.modalIsOkClose=true" title="角色列表" :scrollable="true" width="70%" >
        <roleList v-bind:modalParams="modalParams" v-on:modalCheckData="modalCheckData" /> 
    </Modal>

  </div>

</template>


<script>

  import roleList from '@/views/manage/systemManage/roleManage/List.vue'

  export default {
    name: 'userModify',
    data () {

      let userRolesListButton={width:1};
      if(this.thisRow.pageType !='detail'){
        
        //用户角色列表按钮
        userRolesListButton= {//列表按钮
                title: '',
                key: 'action',
                width: 250,
                align: 'center',
                renderHeader:(h, params) => {//标题自定义
                
                  return h('div', [
                        h('Button', {
                            props: {
                                type: 'info',
                                ghost:true,
                                size: 'small',
                                title:'添加角色',
                                //icon:this.$common.icon.add
                            },
                            on: {
                                click: () => {
                                    //this.row.userRoles.push({});
                                    this.isShowUserRoleModel = true;
                                    this.modalParams.modalIsOkClose = false;
                                }
                            }
                        }, '添加')
                    ]);

                },
                render: (h, params) => {//操作按钮
                    return h('div', [
                        h('Button', {
                            props: {
                                type: 'error',
                                size: 'small'
                            },
                            on: {
                                click: () => {
                                    this.deletesRole(params)
                                    //this.row.userRoles.splice(params.index, 1);
                                }
                            }
                        }, '删除')
                    ]);
                },
            };

      }

      return {
        modalParams:{//用户角色窗口参数传递
          modalIsOkClose:false,//窗口是否关闭
        },
        items:{},//字典数据
        tabsCheckValue:'',//当前tab 选中的name
        isShowUserRoleModel:false,//是否显示添加用户角色窗口
        isDisabled:this.thisRow.pageType=='detail'?true:false,
        userCdIsDisabled:this.thisRow.pageType=='modify'?true:false,
        loading:false,//laoding 标识
        row:{
          sysAccount:{},//账户信息
          userRoles:[],//用户角色
        },
        userRolesColumns:[
              {
                  type: 'index',
                  title: '序号',
                  width: 65,
                  align: 'center'
              },
              {
                  title: '角色code', 
                  key: 'roleCode', 
                  dbKey:'role_code',
                  sortable: true, /* 是否排序 */
                  resizable: true,/* 是否可拖拽宽度 */
              },
              {
                  title: '角色名称',
                  key: 'roleName', 
                  dbKey:'role_name',
                  sortable: true, /* 是否排序 */
                  resizable: true,// 是否可拖拽宽度 
              },
              {
                  title: '状态',
                  key: 'status',
                  resizable: true,// 是否可拖拽宽度 

                  render: (h, params) => {//表格编辑
                      let _this =  this;
                      if (!this.isDisabled) {
                      
                        return h('Select', {
                          props: {
                            // ***重点***:  这里要写currentRow[params.column.key],绑定的是cloneDataList里的数据
                            value: params.row.status
                          },
                          on: {
                            'on-change': function(value) {
                              _this.row.userRoles[params.index].status = value;
                              //this.$set(currentRow, params.column.key, value)
                            }
                          }
                        }, !this.items || !this.items['isActive']?params.row.status:Object.keys(this.items['isActive']).map(function(key) {
                          let label = _this.items['isActive'][key];
                         
                          return h('Option', {
                            props: {
                              value: key,
                              label: label || key
                            }
                          },  label || key);
                        }));

                      } else  {
                        return h('div', !this.items || !this.items['isActive']?params.row.status:this.items['isActive'][params.row.status] || params.row.status);
                      }
                  },

                  filters: [// 过滤器 
                            {
                                label: '有效',
                                value: '1'
                            },
                            {
                                label: '无效',
                                value: '2'
                            },
                        ],
                  filterMultiple: false,//过滤器是否可多选
                  filterMethod (value, row) {//过滤器触发函数
                      /* console.log(row);
                      console.log(value); */
                      return row.status==value?true:false;
                  },
              },
              userRolesListButton,//用户角色列表按钮

        ],
        form1Validate:{//表单验证
          userCd: [
              { required: true,message:'请输入用户cd',validator: '', trigger: 'blur' }
          ],
          userName: [
              { required: true,message:'请输入用户名称',validator: '', trigger: 'blur' }
          ],
        },
        form2Validate:{//表单验证
          password: [
              { required: true,message:'请输入用户密码',validator: '', trigger: 'blur' }
          ],
          licence: [
              { required: true,message:'请选择账户状态',validator: '', trigger: 'blur' }
          ],
          type: [
              { required: true,message:'请选择账户类型',validator: '', trigger: 'blur' }
          ],
          
        },
      }
    },
    props:{
      thisRow:Object,//父页面传过来的参数
    },
    components:{//注册组件
      roleList
    },
    beforeCreate(){//拿不到任何信息，无法篡改数据，一般做loding，这个时候的vue实例还什么都没有，但是$route对象是存在的，可以根据路由信息进行重定向之类的操作
      
    },
    created() {//el 没有初始化，数据已加载完成，阔以篡改数据，并更新，不会触发，，在这结束，还做一些初始化，实现函数自执行，ref属性内容为空数组
      
      /* 加载页面上所有用到的字典 */
      let params = {categoryNames:['sex','isActive','accountType','userType']};
      this.getSysItems(params,(data)=>{
        this.items = data.itemMap?data.itemMap:{};
      });
      
    },
    beforeMount(){//$el已被初始化,，数据已加载完成，阔以篡改数据，并更新，不会触发beforeUpdate，updated，在挂载开始之前被调用，beforeMount之前，会找到对应的template，并编译成render函数
    },
    methods:{//el 已被初始化，数据已加载完成，阔以篡改数据，并更新，并且触发，，在这发起后端请求，拿回数据，配合路由钩子做一些事情，ref属性可以访问
      init(){
        let _this = this;
        this.axios.get('/sysUser/detail',{params:{
          sid:_this.thisRow.sid
        }})
        .then((response)=>{
          _this.loading=false;
          let row = response.data;
          if(row.success){
            _this.row = row;
          }else{
            _this.$Message.info(row.message);
          }
        })
        .catch((error)=>{
          _this.loading=false;
          _this.$Modal.error({title: '异常',content: error});
        });
      },
      save(params){//保存
        let _this=this;

        this.$refs.dataForm1.validate((vali1)=>{//表单验证
          if(vali1){

            _this.$refs.dataForm2.validate((vali2)=>{//表单验证
              if(vali2){

                _this.loading=true;
                _this.axios.post('/sysUser/modify',JSON.stringify(_this.row))
                .then((response)=>{
                  let row = response.data;
                  _this.loading=false;
                  _this.$Message.info(row.message);
                  if(row.success){
                    let isReFresh = _this.thisRow.pageType=='add'?true:false;
                    let updateData = _this.thisRow.pageType=='modify'?{row:row}:false;
                    _this.$emit('chindrenChangeData',{showPage:"list",updateData:{row:row},isReFresh:isReFresh});//调用父级方法
                  }
                })
                .catch((error)=>{
                  _this.loading=false;
                  _this.$Modal.error({title: '异常',content: error});
                });

              }else{
                _this.tabsCheckValue="accountInfo";
              }
            });

          }else{
            _this.tabsCheckValue="userInfo";
          }
        });
        
      },
      cancel(){//取消
        this.$emit('chindrenChangeData',{showPage:"list"});//调用父级方法
      },
      deletesRole(params){//删除角色
        let _this = this;
        let index = params.index;
        let row = params.row;
        if(row.sid){
          
          _this.$Modal.confirm({title: '友情提示',content: '你确定要删除操作？'
          ,onOk:()=>{

            this.axios.post('/sysUser/delRole',JSON.stringify(row))
            .then((response)=>{
              let row = response.data;
              if(row.success){
                _this.$Message.info(row.message);
                _this.row.userRoles.splice(params.index, 1);
              }else{
                _this.$Message.info(row.message);
              }
            })
            .catch((error)=>{
              _this.$Modal.error({title: '异常',content: error});
            });


          }});
            
        }else{
          this.row.userRoles.splice(params.index, 1);
        }

        
      },
      modalCheckData(rows){//角色窗口选中的数据
        for(let j in rows){
          let row = rows[j];
          let obj ={
              roleId:row.sid,
              roleCode:row.roleCode,
              roleName:row.roleName,
              status:row.status,
          };

          let isExits = false;
          for(let i in this.row.userRoles){
            let ur = this.row.userRoles[i];
            if(ur.roleId == row.sid ){
              isExits =true;
            }
          }

          if(!isExits){//不存在才添加
            this.row.userRoles.push(obj);
          }

        }
        
        
      }

    },
    mounted(){
      this.init();
    },
    destroyed(){
    
    }

  }

</script>
