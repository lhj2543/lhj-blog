
<template>
  <div class="content-panel" >

    <div class="content-card" v-if="showPage=='list'">
      <div class="header" >
        <span class="title">
          <Icon :type="$common.icon.list" />
          用户列表
        </span>
        
        <span class="right-tool" style="float:right">
          <Icon title="刷新"  size="18" @click="loadListData" class="cursor-pointer"  :type="$common.icon.refresh" />
        </span>
      </div>
      <div class="card-body" style="padding:0 25px;">
        
        <Form ref="searchForm" :model="searchForm" :label-width="120" style="padding:25px 0px 10px 0px;">
          <Row>
            <Col span="6">
              <FormItem label="用户cd" prop="userCd">
                <Input type="text" v-model="searchForm.userCd" placeholder="请输入用户cd...">
                </Input>
              </FormItem>
            </Col>
            <Col span="6">
              <FormItem label="用户名称"  prop="userName">
                  <Input type="text"  v-model="searchForm.userName" placeholder="请输入用户名称...">
                  </Input>
              </FormItem>
            </Col>
            <Col span="6">
              <FormItem label="手机号码"  prop="phoneNumber">
                  <Input type="text"  v-model="searchForm.phoneNumber" placeholder="请输入手机号码...">
                  </Input>
              </FormItem>
            </Col>
            <Col span="6" style="text-align:right">
                    <Button @click="search('searchForm')" type="info" ghost :icon="$common.icon.search" >检索</Button>
                    <Button @click="formReset('searchForm')" type="info" ghost :icon="$common.icon.clean" >清空</Button>
                    <Button type="info" ghost :icon="$common.icon.add" @click="toAdd">新增</Button>
                    <Button type="error" ghost :icon="$common.icon.delete" @click="deletes('loginForm')">批量删除</Button>
            </Col>
            
          </Row>
        </Form>

        
        <Table :loading="loading" class="content-table" height="519" border :columns="columns" :data="row.rows"></Table>
        <Page class="content-page" :total="row.total" :current="row.page" :page-size="row.pageSize" :page-size-opts="pageSizeOpts"
        @on-change="pageChange" @on-page-size-change="pageSizeChange" 
        show-total show-sizer show-elevator :transfer="true" /><!-- 分页 -->
        
      </div>
        
    </div>

    <modify v-if="showPage == 'modify'" v-bind:thisRow="thisRow" v-on:chindrenChangeData="chindrenChangeData"   />

    <Detail v-if="showPage == 'detail'" v-bind:thisRow="thisRow" v-on:chindrenChangeData="chindrenChangeData" />
    
  </div>

</template>

<script>

  import modify from './Modify.vue'
  import Detail from './Detail.vue'
  
  export default {
    name: 'userList',
    data () {
      return {
        showPage:'list',//显示页面，默认显示列表
        loading:false,//列表laoding 标识
        searchForm:this.getSearchForm(),//检索表单
        pageSizeOpts:[5,10, 20, 50, 100],//列表每页显示多少条数据下拉框
        thisRow:{//修改，详情等当前选择的数据，传入子页面
          sid:''
        },
        row:{//列表数据
          total:0,
          page:1,
          pageSize:10,
          rows: []
        },
        columns: [
              {
                  type: 'selection',/* 复选框 */
                  width: 60,
                  align: 'center'
              },
              {
                  title: '用户Cd', 
                  key: 'userCd', 
                  sortable: true, /* 是否排序 */
                  resizable: true,/* 是否可拖拽宽度 */
              },
              {
                  title: '用户名称',
                  key: 'userName', 
                  sortable: true, /* 是否排序 */
                  resizable: true,/* 是否可拖拽宽度 */
              },
              {
                  title: '手机号码',
                  key: 'phoneNumber',
                  sortable: true, /* 是否排序 */
                  resizable: true,/* 是否可拖拽宽度 */
              },
              {
                  title: '邮箱',
                  key: 'email',
                  sortable: true, /* 是否排序 */
                  resizable: true,/* 是否可拖拽宽度 */
              },
              {
                  title: '账户状态',
                  key: 'accountStatus',
                  resizable: true,/* 是否可拖拽宽度 */
              },
              {
                  title: '更新者',
                  key: 'updateBy',
                  sortable: true, /* 是否排序 */
                  resizable: true,/* 是否可拖拽宽度 */
              },
              {
                  title: '更新时间',
                  key: 'updateDate',
                  sortable: true, /* 是否排序 */
                  resizable: true,/* 是否可拖拽宽度 */
              },
              {
                title: '操作',
                key: 'action',
                width: 250,
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
                                    this.toDetail(params.index)
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
                                    this.toModify(params.index)
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
                                    this.deletes(params.index)
                                }
                            }
                        }, '删除')
                    ]);
                }
            }
              
          ],
        
      
      }
    },
    components:{//注册组件
      modify,Detail
    },
    beforeCreate(){//拿不到任何信息，无法篡改数据，一般做loding，这个时候的vue实例还什么都没有，但是$route对象是存在的，可以根据路由信息进行重定向之类的操作
      
    },
    created() {//el 没有初始化，数据已加载完成，阔以篡改数据，并更新，不会触发，，在这结束，还做一些初始化，实现函数自执行，ref属性内容为空数组
      
      /* let params = this.$route.query;//获取上个页面传递参数
      alert(JSON.stringify(params)); */

      //加载列表数据
      this.loadListData();

    },
    beforeMount(){//$el已被初始化,，数据已加载完成，阔以篡改数据，并更新，不会触发beforeUpdate，updated，在挂载开始之前被调用，beforeMount之前，会找到对应的template，并编译成render函数
    },
    methods:{//el 已被初始化，数据已加载完成，阔以篡改数据，并更新，并且触发，，在这发起后端请求，拿回数据，配合路由钩子做一些事情，ref属性可以访问
      chindrenChangeData(params){/* 子类修改父级数据调用方法 */
        this.showPage=params.showPage;
      },
      getSearchForm(){//检索表单
        let result ={
          userCd:'',
          userName:'',
          phoneNumber:'',
        }
        return result;
      },
      search(params){//检索
        this.row.page = 1;
        this.loadListData();
      },
      formReset (refName) {//表单重置
        
        //this.$refs[refName].resetFields();
        this.searchForm = this.getSearchForm();
      },
      loadListData(params){/* 加载列表数据 */
        let _this = this;

        _this.loading=true;

        let result = [];
        let searchForm=_this.searchForm;
        searchForm.page=_this.row.page;
        searchForm.pageSize=_this.row.pageSize;
        this.axios.get('/sysUser/query',{params:searchForm})
        .then((response)=>{
          _this.loading=false;
          let row = response.data;
          if(row.success){
            let rows = row.rows;
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
      pageChange(page){
        this.row.page = page;
        this.loadListData();
      },
      pageSizeChange(pageSize){
        this.row.pageSize = pageSize;
        this.loadListData();
      },
      toAdd () {
          this.showPage='modify';
          this.thisRow={sid:''}
      },
      toModify (index) {
          this.showPage='modify';
          this.thisRow=this.row.rows[index];
      },
      toDetail (index) {
          this.showPage='detail';
          this.thisRow=this.row.rows[index];
      },
      deletes (index) {
        let _this = this;
        _this.$Modal.confirm({title: '友情提示',content: '你确定要删除操作？'
        ,onOk:()=>{

          let sid = this.row.rows[index].sid;
          this.axios.post('/sysUser/delete',JSON.stringify({sid:sid}))
          .then((response)=>{
            let row = response.data;
            if(row.success){
              _this.$Message.info(row.message);
              _this.loadListData();
              //this.row.rows.splice(index, 1);
            }else{
              _this.$Message.info(row.message);
            }
          })
          .catch((error)=>{
            _this.$Modal.error({title: '异常',content: error});
          });


        }});
          
      }

    },
    mounted(){
      
    },
    destroyed(){
    
    }

  }

</script>