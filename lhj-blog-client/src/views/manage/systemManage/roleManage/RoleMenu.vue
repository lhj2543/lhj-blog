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
  
  <div class="content-panel" style="height:100%;">

    <div class="content-card" style="height:100%;">
      <div class="header" >
        <span class="title" >
          <Icon :type="$common.icon.detail" />
          菜单授权
        </span>
        
        <span class="right-tool" style="float:right">
          <Button  type="info" ghost @click="save" v-if="checkRoleId!=''"  :icon="$common.icon.save" size="small" >保存</Button>
          <!-- <Button  type="info" ghost @click="cancel"  :icon="$common.icon.cancel"  size="small" ><span >取消</span></Button> -->
          <!-- <Button  type="info" ghost @click="formReset('dataForm1')"  :icon="$common.icon.clean" size="small" >重置</Button> -->
          <!-- <Button  type="info" ghost @click="init"  :icon="$common.icon.refresh" size="small" >刷新</Button> -->
        </span>
      </div>
      <div class="card-body" style="height:calc(100vh - 60px);max-height:999999999px">

        <Split v-model="split1" min="700px"  >
            <div slot="left" class="menus-split-pane" style="padding:0 25px;" >
                
                <Form ref="searchForm" :model="searchForm" :label-width="120" style="padding:25px 0px 10px 0px;">
                  <Row>
                    <Col span="6">
                      <FormItem label="角色cd" prop="roleCode">
                        <Input type="text" v-model="searchForm.roleCode" placeholder="请输入角色cd...">
                        </Input>
                      </FormItem>
                    </Col>
                    <Col span="6">
                      <FormItem label="角色名称"  prop="roleName">
                          <Input type="text"  v-model="searchForm.roleName" placeholder="请输入角色名称...">
                          </Input>
                      </FormItem>
                    </Col>
                    <!-- <Col span="6">
                      &nbsp;
                    </Col> -->
                    <Col span="12" style="text-align:right">
                            <Button @click="search('searchForm')" type="info" ghost :icon="$common.icon.search" >检索</Button>
                            <Button @click="formReset('searchForm')" type="info" ghost :icon="$common.icon.clean" >清空</Button>
                            
                    </Col>
                    
                  </Row>
                </Form>

                <!-- 列表 -->
                <Table :loading="loading" class="content-table" highlight-row  @on-current-change="listCurrentChange" height="519" border :columns="columns" :data="row.rows" 
                @on-selection-change = "listSelectChange" @on-sort-change="listSort" ></Table>

                <!-- 分页 -->
                <Page class="content-page" :total="row.total" :current="row.page" :page-size="row.pageSize" :page-size-opts="pageSizeOpts"
                @on-change="pageChange" @on-page-size-change="pageSizeChange" 
                show-total show-sizer show-elevator :transfer="true" />
              

            </div>
            <div slot="right"   class="menus-split-pane" style="height:calc(100vh - 60px);overflow:auto;padding:10px 0 0 20px;">
                
                <Tree :data="menus" :render="renderContent" class="menus-tree" show-checkbox ref="menusTree"  ></Tree>

            </div>
        </Split>

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
  import table from '@/components/Table.js'

  let methods_ = {
    getSearchForm(){//检索表单
      let result ={
        roleCode:'',
        roleName:'',
      }
      return result;
    },
    loadListData(params){/* 加载列表数据 */
      let _this = this;

      _this.loading=true;

      let result = [];
      let searchForm=_this.searchForm;
      searchForm.page=_this.row.page;
      searchForm.pageSize=_this.row.pageSize;
      this.axios.get(this.queryUrl,{params:searchForm})
      .then((response)=>{
        _this.loading=false;
        let row = response.data;
        if(row.success){
          let rows = row.rows;
          _this.checkRoleId = '';
          _this.menus = [];
          _this.row=row;
        }else{
          _this.$Message.info(row.message);
        }
      })
      .catch((error)=>{
        _this.loading=false;
        _this.$Modal.error({title: '异常',content: error});
      });

      return result;
    },
    listCurrentChange(row,oldRow){//选择角色触发事件
      this.checkRoleId = row.sid;
      this.loadMenus({roleId:row.sid});
    },
    renderContent (h, { root, node, data }) {//自定义树样式
      /* console.log(root);
      console.log(node);
      console.log(data); */
      var menuIcon='ios-paper-outline';
      if(data.children!=undefined && data.children.length>0){
          menuIcon='ios-folder-outline';
      }

      return h('span', 
          {
              style: {
                  display: 'inline-block',
                  width: '100%'
              }
          }, 
          [
              h('span', [
                  h('Icon', {
                      props: {
                          //type: menuIcon
                      },
                      style: {
                          marginRight: '-7px'
                      }
                  }),
                  h('span', data.displayName)
              ]),
              h('span', {
                  style: {
                      display: 'inline-block',
                      float: 'right',
                      marginRight: '2px',
                      marginLeft:'5px'
                  }
              }, 
            )
          ]
      );
  },
    loadMenus(params){//加载后台菜单
        let _this = this;
        this.axios.get('/sysRoleMenu/query',{params:{roleId:params.roleId,expand:true}})
        .then((response)=>{
            _this.loading=false;
            let row = response.data;
            if(row.success){
                this.menus = row.rows;
            }else{
                _this.$Message.info(row.message);
            }
        })
        .catch((error)=>{
            _this.loading=false;
            _this.$Modal.error({title: '异常',content: error});
        });
    },
    save(){
      let _this = this;
      _this.loading=true;

      let rows = this.$refs.menusTree.getCheckedAndIndeterminateNodes();
      let row ={
        rows:rows,
        roleId:this.checkRoleId,
      }

      _this.axios.post("/sysRoleMenu/save",JSON.stringify(row))
      .then((response)=>{
        let row = response.data;
        _this.loading=false;
        _this.$Message.info(row.message);
        if(row.success){
        }
      })
      .catch((error)=>{
        _this.loading=false;
        _this.$Modal.error({title: '异常',content: error});
      });

    }
  };
  let methods = Object.assign({},table.methods,methods_);

  export default {
    name: 'roleMenu',
    data () {
      let _this = this;
      let data_ = {
        split1:0.8,
        checkRoleId:'',//选中的角色
        menus:[],//菜单数据
        loading:false,//laoding 标识
        queryUrl:'/sysRole/query',//列表后台url
        searchForm:this.getSearchForm(),//检索表单
        columns: [
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
                  title: '角色分类',
                  key: 'category', 
                  dbKey:'category',
                  sortable: true, /* 是否排序 */
                  resizable: true,// 是否可拖拽宽度 
              },
              {
                  title: '状态',
                  key: 'status',
                  resizable: true,// 是否可拖拽宽度 
                  filters: [// 过滤器 
                            
                            {
                                label: '有效',
                                value: '1'
                            },
                            {
                                label: '无效',
                                value: '0'
                            },
                        ],
                  filterMultiple: false,//过滤器是否可多选
                  /* filterMethod (value, row) {//过滤器触发函数
                      console.log(row);
                      console.log(value);
                      return true;
                  }, */
                  filteredValue:[],//在初始化时使用过滤，数组，值为需要过滤的 value 集合
                  filterRemote (value, row,c) {//后台过滤
                      /* console.log(row); */
                      if(value.length>0){
                        _this.searchForm.status = value[0];
                      }else{
                        _this.searchForm.status = '';
                      }
                      //this.statusFilteredValue=value;
                      _this.loadListData();
                  }
              },
              
          ],
        
      
      }
      
      let data = Object.assign({},table.data,data_);//继承 自定义table组件属性 
      return data;

    },
    props:{
      
    },
    components:{//注册组件
      
    },
    beforeCreate(){//拿不到任何信息，无法篡改数据，一般做loding，这个时候的vue实例还什么都没有，但是$route对象是存在的，可以根据路由信息进行重定向之类的操作
      
    },
    created() {//el 没有初始化，数据已加载完成，阔以篡改数据，并更新，不会触发，，在这结束，还做一些初始化，实现函数自执行，ref属性内容为空数组
      //加载列表数据
      this.loadListData();
    },
    beforeMount(){//$el已被初始化,，数据已加载完成，阔以篡改数据，并更新，不会触发beforeUpdate，updated，在挂载开始之前被调用，beforeMount之前，会找到对应的template，并编译成render函数
    },
    methods:methods, //el 已被初始化，数据已加载完成，阔以篡改数据，并更新，并且触发，，在这发起后端请求，拿回数据，配合路由钩子做一些事情，ref属性可以访问
      
    mounted(){
      //this.init();
    },
    destroyed(){
    
    }

  }

</script>
