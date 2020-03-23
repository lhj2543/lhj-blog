<template>

    <div class="content-panel" style="height:100%;">

        <div class="content-card" style="height:100%;">
        <div class="header" >
            <span class="title" >
            <Icon :type="$common.icon.detail" />
                笔记分类管理
            </span>
            
            <span class="right-tool" style="float:right">
                <Icon title="刷新"  size="18" @click="refresh" class="cursor-pointer"  :type="$common.icon.refresh" />
            </span>
        </div>
        <div class="card-body" style="height:calc(100vh - 60px);max-height:999999999px">

            <Split v-model="split1" min="250px" max="900px" >
                <div slot="left" class="trees-split-pane" >
                    
                    <Tree :data="trees" :render="renderContent" class="trees-tree" @on-select-change="menuOnSelectChange" ref="treesTree"  ></Tree>

                </div>
                <div slot="right"   class="trees-split-pane" :style="{padding:'40px'}">
                    
                    <Form ref="treeForm" :model="treeForm" :rules="ruleValidate" :label-width="100" :disabled="isDisabled">

                        <Row>
                            <Col span="12">
                                <FormItem label="分类名称" prop="displayName">
                                    <Input v-model="treeForm.displayName" placeholder="请输入分类名称..."></Input>
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem label="分类Code" prop="categoryName">
                                    <Input v-model="treeForm.categoryName" placeholder="请输入分类Code..."></Input>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem label="图标" prop="icon">
                                    <Input v-model="treeForm.icon" placeholder="请输入图标..."></Input>
                                </FormItem>
                            </Col>
                            <Col span="12">
                                <FormItem label="排序" prop="sortKey">
                                    <Input v-model="treeForm.sortKey" placeholder="请输入排序..."></Input>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row>
                            <Col span="12">
                                <FormItem label="状态" prop="status">
                                    <RadioGroup v-model="treeForm.status" >
                                        <Radio v-for="(value,key,index) in items['isActive']" :key="key+'-'+value" :label="key">{{value}}</Radio>
                                    </RadioGroup>
                                </FormItem>
                            </Col>
                            
                        </Row>

                        <FormItem label="描述" prop="notes">
                            <Input v-model="treeForm.notes"   type="textarea"  :autosize="{minRows: 2,maxRows: 10}" placeholder="请输入描述..."></Input>
                        </FormItem>

                        <FormItem v-if="isAdd" :style="{padding:'20px 0','text-align':'center'}">
                            <Button type="primary" @click="handleSubmit('treeForm')"  >保存</Button>
                            <Button @click="handleReset('treeForm')" style="margin-left: 8px"  >重置</Button>
                        </FormItem>
                    </Form>

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

    let menuObj= {
                sid:'',
                parentId:'',
                displayName: '',
                categoryName: '',
                status: '',
                icon: '',
                sortKey: '',
                notes: '',
            };


export default {
    name:'NoteCategoryManage',
    data(){
        return{
            split1:0.2,
            loading:false,
            isAdd:false,
            items:{},//字典数据
            trees:[],
            buttonProps: {
                type: 'text',
                /* size: 'small', */
            },
            treeForm:Object.assign({},menuObj,{}),
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

        this.loadtrees();//加载后台菜单
    },
    methods: {
        refresh(){//刷新
            this.loadtrees();
            this.treeForm=Object.assign({},menuObj,{});
        },
        openAll(){//展开所有菜单
            
        },
        closeAll(){//收缩所有菜单

        },
        loadtrees(){//加载后台菜单
            let _this = this;
            this.loading = true;
            this.axios.get('/noteBookCategory/query',{params:{expand:true}})
            .then((response)=>{
                _this.loading=false;
                let row = response.data;
                if(row.success){
                    this.trees = row.rows;
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
        append (root, node, data) {//添加菜单
            //判断父节点是否为空
            if(!data.sid || data.sid==''){
                this.$Message.info('请先保存该节点数据！');
                return;
            }
            /* console.log("============添加菜单=========");
            console.log(data);
            console.log(node); */
            var selectedNodes=this.$refs.treesTree.getSelectedNodes();
            //console.log(selectedNodes);
            selectedNodes.forEach(node => {
                node.selected=false;
            });

            const children = data.children || [];
            var newMenu=Object.assign({},menuObj,{
                sid:'',
                displayName:'新建分类',
                parentId:data.sid,
                status:'1',
                selected:true,
                expand:true
            });
            //this.treeForm=Object.assign({},newMenu,{});
            this.treeForm=newMenu;
            children.push(newMenu);
            this.isAdd=true;
            this.$set(data, 'children', children);
        },
        remove (root, node, data) {//移除菜单
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
                    content: '<p>删除该菜单同时会删除该分类下所有子分类！</p>',
                    onOk: () => {
                        
                        //后台删除数据
                        this.axios.post('/noteBookCategory/deletes',JSON.stringify(data))
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
                        //this.$Message.info('取消删除菜单');
                    }
                });

            }else{
                refreshFormData(index);
            }

            function refreshFormData(index){
                parent.children.splice(index, 1);
                _this.isAdd=false;
                _this.treeForm = menuObj;
            }

        },
        menuOnSelectChange(selectNodes,node){//菜单选中事件
            console.log("============菜单选中=========");
            console.log(node);
            //表单赋值
            //var k=Object.assign({},node,{});
            this.treeForm=node;
            this.isAdd=true;
            //this.isDisabled=this.treeForm.parentId=='-1'?true:false;//根节点不能修改
            
        },
        handleSubmit (name) {//表单提交
            let _this = this;
            this.$refs[name].validate((valid) => {

                if (valid) {
                    
                    _this.loading=true;
                    _this.axios.post('/noteBookCategory/save',JSON.stringify(_this.treeForm))
                    .then((response)=>{
                        let row = response.data;
                        _this.loading=false;
                        _this.$Message.info(row.message);
                        if(row.success){
                            _this.treeForm.sid = row.sid;
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
        }
    },
    mounted(){
        
    }
}
</script>

<style scoped>
    #treesManage{
        height: 100%;
    }

    .trees-split-pane{
        height:calc(100vh - 180px);
        padding: 10px;
        overflow: auto;
    }

</style>