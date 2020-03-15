let obj ={

    data:{
        queryUrl:'',//列表后台url
        delUrl:'',//删除后台url
        showPage:'list',//显示页面，默认显示列表
        loading:false,//列表laoding 标识
        searchForm:{},//检索表单
        pageSizeOpts:[5,10, 20, 50, 100],//列表每页显示多少条数据下拉框
        thisRow:{//修改，详情等当前选择的数据，传入子页面
          sid:''
        },
        checkedRows:[],//列表选中数据
        row:{//列表数据
          total:0,
          page:1,
          pageSize:10,
          rows: []
        },
        columns: [],
    },
    handle:{//列表按钮
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
    },
    methods:{
      chindrenChangeData(params){/* 子类修改父级数据调用方法 */
        this.showPage=params.showPage;
        if(params.updateData){
          this.row.rows[this.thisRow.index]=params.updateData.row;
        }
        if(params.isReFresh){
          this.search();
        }
      },
      getSearchForm(){//检索表单
        let result ={}
        return result;
      },
      refresh(params){//检索
        this.formReset();
        this.row.page = 1;
        this.loadListData();
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
        this.axios.get(this.queryUrl,{params:searchForm})
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
      listSelectChange(rows){//列表checked 事件
        this.checkedRows = rows;
      },
      listSort(params){//列表排序 事件
        let column = params.column;
        console.log(column);
        let key = column.dbKey;
        let order = params.order;
        if(order != 'normal'){
            this.searchForm.orderby = key + ' ' +order;
        }
        this.loadListData();
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
          this.thisRow.pageType="add";
      },
      toModify (params) {
          let index = params.index;
          this.showPage='modify';
          this.thisRow=params.row;
          this.thisRow.pageType="modify";
          this.thisRow.index=index;
      },
      toDetail (params) {
          this.showPage='modify';
          this.thisRow=params.row;
          this.thisRow.pageType="detail";
      },
      deletes (params) {//删除
        /* alert(params.length);
        alert(JSON.stringify(params)); */
        if(params.length<=0){
            this.$Modal.info({title: '提示',content: '请选择要删除的数据！'});
            return;
        }
        let _this = this;
        _this.$Modal.confirm({title: '友情提示',content: '你确定要删除操作？'
        ,onOk:()=>{

          this.axios.post(this.delUrl,JSON.stringify(params))
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
    
  }

  export default obj;