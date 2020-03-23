<template>

    <div class="content-panel" style="height:100%;">

        <div class="content-card" style="height:100%;">
        <div class="header" >
            <span class="title" >
            <Icon :type="$common.icon.detail" />
                字典管理
            </span>
            
            <span class="right-tool" style="float:right">
                <Button  type="info" ghost @click="save('formRef')" v-if="checkSid!='' || isAdd"  :icon="$common.icon.save" size="small" >保存</Button>
                <!-- <Icon title="刷新"  size="18" @click="refresh" class="cursor-pointer"  :type="$common.icon.refresh" /> -->
            </span>
        </div>
        <div class="card-body" style="height:calc(100vh - 60px);max-height:999999999px">

            <Split v-model="split1" min="250px" max="900px" >
                <div slot="left" class="menus-split-pane" >
                    
                    <Tree :data="menus" :render="renderContent" class="menus-tree" @on-select-change="menuOnSelectChange" ref="menusTree"  ></Tree>

                </div>
                <div slot="right"   class="menus-split-pane" :style="{padding:'40px'}">
                    
                    <Form ref="formRef" :model="row" :rules="ruleValidate" :label-width="100" :disabled="isDisabled">

                        <Row>
                            <Col span="12">
                                <FormItem label="分类名称" prop="displayName">
                                    <Input v-model="row.displayName" placeholder="请输入分类名称..."></Input>
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem label="分类Code" prop="categoryName">
                                    <Input v-model="row.categoryName" placeholder="请输入分类Code..."></Input>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem label="状态" prop="status">
                                    <RadioGroup v-model="row.status" >
                                        <Radio v-for="(value,key,index) in items['isActive']" :key="key+'-'+value" :label="key">{{value}}</Radio>
                                    </RadioGroup>
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem label="排序" prop="sortKey">
                                    <Input v-model="row.sortKey" placeholder="请输入排序..."></Input>
                                </FormItem>
                            </Col>
                        </Row>

                        <FormItem label="描述" prop="notes">
                            <Input v-model="row.notes"   type="textarea"  :autosize="{minRows: 2,maxRows: 10}" placeholder="请输入描述..."></Input>
                        </FormItem>

                        <!-- <FormItem :style="{padding:'20px 0','text-align':'center'}">
                            <Button type="primary" @click="save('formRef')"  >保存</Button>
                            <Button @click="handleReset('formRef')" style="margin-left: 8px"  >重置</Button>
                        </FormItem> -->
                    </Form>


                    <!-- ============字典列表================== -->

                    <!-- 列表 -->
                    <Table style="padding-bottom:90px;" class="content-table" border :columns="itemColumns" :data="row.sysItems" ></Table>

                    <!-- ============字典列表================== -->

                </div>
            </Split>
            

        </div>
            
        </div>
        
        <!-- loadding 防止多次点击 -->
        <Spin fix v-if="loading">
            <Icon type="ios-loading" size=18 class="spin-icon-load"></Icon>
            <div>数据加载中请稍后</div>
        </Spin>
        
    </div>
    
</template>

<script>

    let formObj= {
                sid:'',
                parentId:'',
                displayName: '',
                categoryName: '',
                status: '',
                icon: '',
                sortKey: '',
                notes: '',
                sysItems:[]
            };


export default {
    name:'ItemManage',
    data(){

        let itemListButton={width:1};
        if(true){
            
            //用户角色列表按钮
            itemListButton= {//列表按钮
                    title: '',
                    key: 'action',
                    width: 250,
                    align: 'center',
                    renderHeader:(h, params) => {//标题自定义
                        if(this.checkSid ||  this.isAdd){
                            
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'info',
                                        ghost:true,
                                        size: 'small',
                                        title:'添加',
                                        //icon:this.$common.icon.add
                                    },
                                    on: {
                                        click: () => {
                                            this.row.sysItems.push({
                                                status:'1',
                                            });
                                            //this.$set(this.row,'sysItems',sysItems);
                                        }
                                    }
                                }, '添加')
                            ]);

                        }

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
                                        this.deletesItem(params)
                                        //this.row.sysItems.splice(params.index, 1);
                                    }
                                }
                            }, '删除')
                        ]);
                    },
                };

        }

        return{
            split1:0.2,
            loading:false,
            checkSid:'',//选择的分类
            isAdd:false,
            items:{},//字典数据
            menus:[],//分类数据
            row:{//字典数据
                sysItems:[]
            },
            item:{
                option:[
                    {
                    value: '1',
                    label: '有效'
                    },
                    {
                    value: '0',
                    label: '无效'
                    }
                ]
            },
            itemColumns:[
                {
                    type: 'index',
                    title: '序号',
                    width: 65,
                    align: 'center'
                },
                {
                    title: '字典key', 
                    key: 'itemKey', 
                    dbKey:'item_key',
                    sortable: true, /* 是否排序 */
                    resizable: true,/* 是否可拖拽宽度 */
                    render:(h,params)=>{
                        let _this =  this;
                        return h('Input',{
                            props:{
                                value:params.row.itemKey
                            },
                            on:{
                                'on-change':function(enevt){
                                    _this.row.sysItems[params.index].itemKey = enevt.target.value;
                                }
                            }
                        });
                    },
                },
                {
                    title: '字典值',
                    key: 'itemValue', 
                    dbKey:'item_value',
                    sortable: true, /* 是否排序 */
                    resizable: true,// 是否可拖拽宽度 
                    render:(h,params)=>{
                        let _this = this;
                        return h('Input',{
                            props:{
                                value:params.row.itemValue
                            },
                            on:{
                                'on-change':function(enevt){
                                    _this.row.sysItems[params.index].itemValue=enevt.target.value;
                                }
                            }
                        });
                        
                    },
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
                                        _this.row.sysItems[params.index].status = value;
                                        //this.$set(currentRow, params.column.key, value)
                                    }
                                }
                            }, 
                            !this.items || !this.items['isActive']?params.row.status:Object.keys(this.items['isActive']).map(function(key) {
                                let label = _this.items['isActive'][key];
                                
                                return h('Option', {
                                    props: {
                                        value: key,
                                        label: label || key
                                    }
                                },  label || key);
                            }));

                        } else  {
                            return h('div', params.row.status);
                        }
                    },
                    
                },
                {
                    title: '排序',
                    key: 'sortKey', 
                    dbKey:'sort_key',
                    sortable: true, /* 是否排序 */
                    resizable: true,// 是否可拖拽宽度
                    render:(h,params)=>{
                        let _this =  this;
                        return h('Input',{
                            props:{
                                value:params.row.sortKey
                            },
                            on:{
                                'on-change':function(enevt){
                                    _this.row.sysItems[params.index].sortKey=enevt.target.value;
                                }
                            }
                        });
                    }
                },
                itemListButton,//用户角色列表按钮

            ],
            buttonProps: {
                type: 'text',
                /* size: 'small', */
            },
            //FormData:Object.assign({},formObj,{}),
            isDisabled:false,//表单按钮是否不可点击
            ruleValidate: {
                categoryName: [
                    { required: true, message: '分类Code必填!', trigger: 'blur' }
                ],
                displayName: [
                    { required: true, message: '分类名称必填!', trigger: 'blur' }
                ],
                notes: [
                    { type: 'string', max: 200, message: '最大不能超过200个字！', trigger: 'blur' }
                ]
            }

        }
    },
    created() {

        /* 加载页面上所有用到的字典 */
        let params = {categoryNames:['isActive']};
        this.getSysItems(params,(data)=>{
            this.items = data.itemMap?data.itemMap:{};
        });

        this.loadMenus();//加载后台分类
    },
    methods: {
        refresh(){//刷新
            this.loadMenus();
            this.row=Object.assign({},formObj,{});
        },
        openAll(){//展开所有分类
            
        },
        closeAll(){//收缩所有分类

        },
        loadMenus(){//加载后台数据
            let _this = this;
            this.loading = true;
            this.axios.get('/sysItemCategory/query',{params:{expand:true}})
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
        renderContent (h, { root, node, data }) {//自定义树样式
            /* console.log(root);
            console.log(node);
            console.log(data); */
            var menuIcon='ios-paper-outline';
            if(data.children!=undefined && data.children.length>0){
                menuIcon='ios-folder-outline';
            }

            var removeButton=h('Button', 
                {
                props: Object.assign({}, this.buttonProps, {
                    icon: 'ios-remove'
                }),
                on: {
                    click: (e) => { 
                        e.stopPropagation();//阻止事件冒泡
                        this.remove(root, node, data) 
                    }
                }
            });

            if(data.parentId=='-1'){
                removeButton=h();
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
                                type: menuIcon
                            },
                            style: {
                                marginRight: '8px'
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
                    [
                        h('Button', {
                            props: Object.assign({}, this.buttonProps, {
                                icon: 'ios-add'
                                /* ,ghost:'' */
                            }),
                            style: {
                                marginRight: '2x'
                            },
                            on: {
                                click: (e) => { 
                                    e.stopPropagation();//阻止事件冒泡
                                    this.append(root, node, data); 
                                }
                            }
                        }),
                        removeButton
                    ])
                ]
            );
        },
        append (root, node, data) {//添加分类

            //判断父节点是否为空
            if(!data.sid || data.sid==''){
                this.$Message.info('请先保存数据！');
                return;
            }

            /* console.log("============添加分类=========");
            console.log(data);
            console.log(node); */
            var selectedNodes=this.$refs.menusTree.getSelectedNodes();
            //console.log(selectedNodes);
            selectedNodes.forEach(node => {
                node.selected=false;
            });

            const children = data.children || [];
            var newMenu=Object.assign({},formObj,{
                sid:'',
                displayName:'新建分类',
                parentId:data.sid,
                status:'1',
                selected:true,
                expand:true,
                sysItems:[],
            });
            //this.row=Object.assign({},newMenu,{});
            this.row=newMenu;
            this.isAdd = true;
            this.checkSid='';
            children.push(newMenu);
            this.$set(data, 'children', children);
        },
        remove (root, node, data) {//移除分类
            /* console.log(root);
            console.log(node);
            console.log(data); */
            
            let sid = data.sid;
            let _this = this;
            //alert(sid);

            const parentKey = root.find(el => el === node).parent;
            const parent = root.find(el => el.nodeKey === parentKey).node;
            const index = parent.children.indexOf(data);

            if(sid){

                this.$Modal.confirm({
                    title: '您确定要删除分类？',
                    content: '<p>删除该分类同时会删除该分类下所有子分类及分类下所有字典！</p>',
                    onOk: () => {
                        
                        //后台删除数据
                        this.axios.post('/sysItemCategory/deletes',JSON.stringify(data))
                        .then((response)=>{
                            let row = response.data;
                            _this.$Message.info(row.message);
                            if(row.success){
                               refreshFormData(index);
                            }
                        })
                        .catch((error)=>{
                            _this.$Modal.error({title: '异常',content: error});
                        });
                    },
                    onCancel: () => {
                        //this.$Message.info('取消删除分类');
                    }
                });

            }else{
                refreshFormData(index);
            }

            function refreshFormData(index){
                parent.children.splice(index, 1);
                _this.row = formObj;
                _this.isAdd = false;
                _this.checkSid='';
            }

        },
        menuOnSelectChange(selectNodes,node){//分类选中事件
            console.log("============分类选中=========");
            console.log(node);
            //表单赋值
            //var k=Object.assign({},node,{});
            this.checkSid = node.sid;
            this.isAdd = true;
            this.row=node;
            this.isDisabled=this.row.parentId=='-1'?true:false;//根节点不能修改

            this.loadItems(this.row);//加载分类下所有字典
            
        },
        loadItems(params){
            let _this = this;
            if(!params.sid || params.sid==''){
                return; 
            }
            this.axios.get('/sysItem/query',{params:{categoryId:params.sid}})
            .then((response)=>{
                let row = response.data;
                if(row.success){
                    _this.row.sysItems = row.rows;
                }else{
                    _this.$Message.info(row.message);
                }
            })
            .catch((error)=>{
                _this.$Modal.error({title: '异常',content: error});
            });
        },
        save (name) {//表单提交
            let _this = this;

            //判断父节点是否为空
            if(!this.row.parentId || this.row.parentId==''){
                _this.$Message.info('请先保存父节点数据！');
                return;
            }

            this.$refs[name].validate((valid) => {

                if (valid) {
                    
                    _this.loading=true;
                    _this.axios.post('/sysItemCategory/save',JSON.stringify(_this.row))
                    .then((response)=>{
                        let row = response.data;
                        _this.loading=false;
                        _this.$Message.info(row.message);
                        if(row.success){
                            _this.row.sid = row.sid;
                        }
                    })
                    .catch((error)=>{
                        _this.loading=false;
                        _this.$Modal.error({title: '异常',content: error});
                    });
                    
                }

            })
        },
        handleReset (name) {//表单重置
            this.$refs[name].resetFields();
        },
        deletesItem(params){//删除字典
            let _this = this;
            let index = params.index;
            let row = params.row;
            if(row.sid){
            
                _this.$Modal.confirm({title: '友情提示',content: '你确定要删除操作？'
                ,onOk:()=>{

                    this.axios.post('/sysItem/deletes',JSON.stringify(row))
                    .then((response)=>{
                    let row = response.data;
                    if(row.success){
                        _this.$Message.info(row.message);
                        _this.row.sysItems.splice(params.index, 1);
                    }else{
                        _this.$Message.info(row.message);
                    }
                    })
                    .catch((error)=>{
                    _this.$Modal.error({title: '异常',content: error});
                    });


                }});
                
            }else{
                this.row.sysItems.splice(params.index, 1);
            }

        
      },

    },
    mounted(){
        
    }
}
</script>

<style scoped>
    #menusManage{
        height: 100%;
    }

    .menus-split-pane{
        height:calc(100vh - 180px);
        padding: 10px;
        overflow: auto;
    }

</style>