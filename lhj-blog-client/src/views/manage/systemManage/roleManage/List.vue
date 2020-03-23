
<template>
  <div class="content-panel" >

    <div class="content-card" v-if="showPage=='list'">
      <div class="header" v-if="!isModalOpen" >
        <span class="title">
          <Icon :type="$common.icon.list" />
          角色列表
        </span>
        
        <span class="right-tool" style="float:right">
          <Icon title="刷新"  size="18" @click="refresh" class="cursor-pointer"  :type="$common.icon.refresh" />
        </span>
      </div>
      <div class="card-body" style="padding:0 25px;">
        
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
                    <span v-if="!isModalOpen">
                      <Button type="info" ghost :icon="$common.icon.add" @click="toAdd">新增</Button>
                      <Button type="error" ghost :icon="$common.icon.delete" @click="deletes(checkedRows)">批量删除</Button>
                    </span>
            </Col>
            
          </Row>
        </Form>

        <!-- 列表 -->
        <Table :loading="loading" class="content-table" height="519" border :columns="columns" :data="row.rows" 
        @on-selection-change = "listSelectChange" @on-sort-change="listSort" ></Table>

        <!-- 分页 -->
        <Page class="content-page" :total="row.total" :current="row.page" :page-size="row.pageSize" :page-size-opts="pageSizeOpts"
        @on-change="pageChange" @on-page-size-change="pageSizeChange" 
        show-total show-sizer show-elevator :transfer="true" />
        
      </div>
        
    </div>

    <!-- 新增/修改/详情 共同页面 -->
    <modify v-if="showPage == 'modify'" v-bind:thisRow="thisRow" v-on:chindrenChangeData="chindrenChangeData"   />

  </div>

</template>

<script>

  import modify from './Modify.vue'

  import table from '@/components/Table.js'
  
  let methods_ = {
    getSearchForm(){//检索表单
      let result ={
        roleCode:'',
        roleName:'',
      }
      return result;
    },
    
  };
  let methods = Object.assign({},table.methods,methods_);

  //let tableHandle = Object.assign({},table.handle,{});

  

  export default {
    name: 'userList',
    data () {
      
      //列表按钮
      let listButton = {title: '',width:'1'};

      //是否modal窗口 模式开的
      let isModalOpen = false;
      if( this.modalParams ){
        isModalOpen = true ;
      }else{
        listButton={
                title: '操作',
                key: 'action',
                width: 280,
                align: 'center',
                render: (h, params) => {
                    return h('div', [
                        h('Button', {
                            props: {
                                type: 'primary',
                                size: 'small'
                            },
                            style: {
                                marginRight: '5px'
                            },
                            on: {
                                click: () => {
                                    this.toDetail(params)
                                }
                            }
                        }, '详情'),
                        h('Button', {
                            props: {
                                type: 'primary',
                                size: 'small',
                                //icon: this.$common.icon.delete
                            },
                            style: {
                                marginRight: '5px'
                            },
                            on: {
                                click: () => {
                                    this.toModify(params)
                                }
                            }
                        }, '修改'),
                        h('Button', {
                            props: {
                                type: 'error',
                                size: 'small'
                            },
                            on: {
                                click: () => {
                                    this.deletes([params.row])
                                }
                            }
                        }, '删除')
                    ]);
                }
            };
      }
      
      let data_ = {
        isModalOpen:isModalOpen,//是否窗口modal 模式打开
        items:{},//字典数据
        statusFilters:[],//状态过滤数组
        queryUrl:'/sysRole/query',//列表后台url
        delUrl:'/sysRole/deletes',//删除后台url
        searchForm:this.getSearchForm(),//检索表单
        columns: [
              {
                  type: 'selection',/* 复选框 */
                  width: 60,
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
                  render:(h,params)=>{
                    return h('span',{

                    },this.items? this.items[params.row.status] || params.row.status : params.row.status);
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
                  /* filterMethod (value, row) {//过滤器触发函数
                      console.log(row);
                      console.log(value);
                      return true;
                  }, */
                  filteredValue:[],//在初始化时使用过滤，数组，值为需要过滤的 value 集合
                  filterRemote (value, row,c) {//后台过滤
                      /* console.log(row);
                      console.log(value); */
                      if(value.length>0){
                        this.searchForm.status = value[0];
                      }else{
                        this.searchForm.status = '';
                      }
                      //this.statusFilteredValue=value;
                      this.loadListData();
                  }
              },
              {
                  title: '更新者',
                  key: 'updateBy',
                  dbKey:'update_by',
                  sortable: true, /* 是否排序 */
                  resizable: true,/* 是否可拖拽宽度 */
              },
              {
                  title: '更新时间',
                  key: 'updateDate',
                  dbKey:'update_date',
                  sortable: true, /* 是否排序 */
                  resizable: true,/* 是否可拖拽宽度 */
              },
              listButton,//列表按钮
          ],
        
      
      }
      
      let data = Object.assign({},table.data,data_);//继承 自定义table组件属性 
      return data;
    },
    components:{//注册组件
      modify
    },
    props:{
      modalParams:Object,//父页面传过来的参数 窗口引用该页面
    },
    watch:{
      modalParams:{  //监听父页面传过来的参数
          handler:function(val,oldval){ 
              if(val && val.modalIsOkClose){//窗口点击确定时，传数据到父页面
                this.$emit('modalCheckData',this.checkedRows);
              }
          },  
          immediate:true,//关键
          deep:true
      }
    },
    beforeCreate(){//拿不到任何信息，无法篡改数据，一般做loding，这个时候的vue实例还什么都没有，但是$route对象是存在的，可以根据路由信息进行重定向之类的操作
      
    },
    created() {//el 没有初始化，数据已加载完成，阔以篡改数据，并更新，不会触发，，在这结束，还做一些初始化，实现函数自执行，ref属性内容为空数组
      
      /* 加载页面上所有用到的字典 */
      let params = {categoryName:'isActive'};
      this.getSysItems(params,(data)=>{
        this.items = data.itemMap?data.itemMap:{};
      });
      
      //加载列表数据
      this.loadListData();

    },
    beforeMount(){//$el已被初始化,，数据已加载完成，阔以篡改数据，并更新，不会触发beforeUpdate，updated，在挂载开始之前被调用，beforeMount之前，会找到对应的template，并编译成render函数
    },
    //el 已被初始化，数据已加载完成，阔以篡改数据，并更新，并且触发，，在这发起后端请求，拿回数据，配合路由钩子做一些事情，ref属性可以访问
    methods:methods,

    mounted(){
      
    },
    destroyed(){
    
    }

  }

</script>