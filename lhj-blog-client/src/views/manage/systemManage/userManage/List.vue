
<style scoped>
a{border-bottom: }
</style>

<template>
  <div class="content-panel" >
    <!-- <router-link target="" :to="{name:'manage-user-modify'}" >
      修改
    </router-link>

    <router-link target="" :to="{name:'manage-user-detail'}" >
      详情
    </router-link> -->

    <div class="content-card">
      <div class="header" >
        <span class="title">
          <Icon type="ios-list-box-outline" />
          用户列表
        </span>
        
        <span class="right-tool" style="float:right">
          <Icon title="刷新"  size="18" @click="loadListData" class="cursor-pointer"  type="md-sync" />
        </span>
      </div>
      <div class="card-body">
        
        <Form ref="searchForm" :model="searchForm" :label-width="180" style="padding:20px 15px 10px 15px;">
          <Row>
            <Col span="8">
                <FormItem label="用户cd" prop="userCd">
                  <Input type="text" v-model="searchForm.userCd" placeholder="请输入用户cd...">
                  </Input>
              </FormItem>
            </Col>
            <Col span="8">
              <FormItem label="用户名称"  prop="userName">
                  <Input type="text"  v-model="searchForm.userName" placeholder="请输入用户名称...">
                  </Input>
              </FormItem>
            </Col>
            <Col span="8" style="text-align:right">
                    <Button @click="search('searchForm')" type="info" ghost icon="ios-search" >检索</Button>
                    <Button @click="formReset('searchForm')" type="info" ghost icon="ios-refresh" >清空</Button>
                    <Button type="info" ghost icon="ios-add" @click="login('loginForm')">新增</Button>
                    <Button type="error" ghost icon="ios-trash-outline" @click="login('loginForm')">批量删除</Button>
            </Col>
            
          </Row>
        </Form>

        
        <Table :loading="loading" class="content-table" height="519" border :columns="columns" :data="row.rows"></Table>
        <Page class="content-page" :total="row.total" :page-size="searchForm.pageSize" :page-size-opts="pageSizeOpts"
        @on-change="pageChange" @on-page-size-change="pageSizeChange" 
        show-total show-sizer show-elevator :transfer="true" /><!-- 分页 -->
        
      </div>
        
    </div>
    
  </div>

</template>

<script>
  
  export default {
    name: 'userList',
    data () {
      return {
        loading:false,
        searchForm:{
          userCd:'',
          userName:'',
          page:1,
          pageSize:10,
        },
        pageSizeOpts:[5,10, 20, 50, 100],
        row:{
          total:0,
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
                                    this.show(params.index)
                                }
                            }
                        }, '详情'),
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
                                    this.show(params.index)
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
                                    this.remove(params.index)
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
      
    },
    beforeCreate(){//拿不到任何信息，无法篡改数据，一般做loding，这个时候的vue实例还什么都没有，但是$route对象是存在的，可以根据路由信息进行重定向之类的操作
      
    },
    created() {//el 没有初始化，数据已加载完成，阔以篡改数据，并更新，不会触发，，在这结束，还做一些初始化，实现函数自执行，ref属性内容为空数组
      
      //加载列表数据
      this.loadListData();

    },
    beforeMount(){//$el已被初始化,，数据已加载完成，阔以篡改数据，并更新，不会触发beforeUpdate，updated，在挂载开始之前被调用，beforeMount之前，会找到对应的template，并编译成render函数
    },
    methods:{//el 已被初始化，数据已加载完成，阔以篡改数据，并更新，并且触发，，在这发起后端请求，拿回数据，配合路由钩子做一些事情，ref属性可以访问
      search(params){//检索
        this.searchForm.page = 1;
        this.loadListData();
      },
      formReset (refName) {//表单重置
        this.$refs[refName].resetFields();
      },
      loadListData(params){/* 加载列表数据 */
        let _this = this;

        _this.loading=true;

        let result = [];

        this.axios.get('/sysUser/query',{params:_this.searchForm})
        .then((response)=>{
          let row = response.data;
          if(row.success){
            let rows = row.rows;
            _this.row=row;
            _this.loading=false;
          }else{
            _this.loading=false;
            _this.$Message.info(row.message);
          }
        })
        .catch((err)=>{
          _this.loading=false;
          _this.$Message.error(error);
        });

        return result;
      },
      pageChange(page){
        this.searchForm.page = page;
        this.loadListData();
      },
      pageSizeChange(pageSize){
        this.searchForm.pageSize = pageSize;
        this.loadListData();
      },
      show (index) {
          /* this.$Modal.info({
              title: 'User Info',
              content: `Name：${this.row.rows[index].userCd}`
          }) */
          this.$router.push({name:'manage-user-detail'});
      },
      remove (index) {
        let _this = this;
        _this.$Modal.confirm({title: '友情提示',content: '你确定要删除操作？'
        ,onOk:()=>{

          let sid = this.row.rows[index].sid;
          this.axios.post('/sysUser/delete',JSON.stringify({sid:sid}))
          .then((response)=>{
            let row = response.data;
            if(row.success){
              _this.$Message.info(row.message);
              _this.loadListData(row.message);
              //this.row.rows.splice(index, 1);
            }else{
              _this.$Message.info(row.message);
            }
          })
          .catch((err)=>{
            _this.$Message.error(error);
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