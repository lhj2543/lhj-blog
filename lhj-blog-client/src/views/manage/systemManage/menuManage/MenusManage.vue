<template>
    <div id="menusManage" class="menusManage">

        <Split v-model="split1" min="200px" max="900px" >
            <div slot="left" class="menus-split-pane" >
                
                <Tree :data="menus" :render="renderContent" class="menus-tree" @on-select-change="menuOnSelectChange" ref="menusTree"  ></Tree>

            </div>
            <div slot="right"   class="menus-split-pane" :style="{padding:'40px'}">
                
                <Form ref="menuForm" :model="menuForm" :rules="ruleValidate" :label-width="100" :disabled="isDisabled">
                    <input type="hidden" name="id" v-model="menuForm.id"/>
                    <input type="hidden" name="parentId" v-model="menuForm.parentId" />

                    <Row>
                        <Col span="12">
                             <FormItem label="菜单名称" prop="displayName">
                                <Input v-model="menuForm.displayName" placeholder="请输入菜单名称..."></Input>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem label="菜单URL" prop="url">
                                <Input v-model="menuForm.url" placeholder="请输入菜单URL..."></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                             <FormItem label="页面路径" prop="pagePath">
                                <Input v-model="menuForm.pagePath" placeholder="请输入页面路径..."></Input>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem label="分类" prop="category">
                                <Input v-model="menuForm.category" placeholder="请输入分类..."></Input>
                            </FormItem>
                        </Col>
                    </Row>

                    <Row>
                        <Col span="12">
                             <FormItem label="URL名称" prop="menuName">
                                <Input v-model="menuForm.menuName" placeholder="请输入URL名称..."></Input>
                            </FormItem>
                        </Col>
                        <Col span="12">
                             <FormItem label="是否有效" prop="status">
                                <Select v-model="menuForm.status" placeholder="请选中是否有效...">
                                    <Option value="1">是</Option>
                                    <Option value="0">否</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>

                    <Row>
                        <Col span="12">
                            <FormItem label="菜单图标" prop="icon">
                                <Input v-model="menuForm.icon" placeholder="请输入菜单图标..."></Input>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem label="排序" prop="sortKey">
                                <Input v-model="menuForm.sortKey" placeholder="请输入排序号..."></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    
                    <FormItem label="菜单描述" prop="notes">
                        <Input v-model="menuForm.notes"   type="textarea"  :autosize="{minRows: 2,maxRows: 10}" placeholder="请输入菜单描述..."></Input>
                    </FormItem>

                    <FormItem :style="{padding:'20px 0','text-align':'center'}">
                        <Button type="primary" @click="handleSubmit('menuForm')"  >保存</Button>
                        <Button @click="handleReset('menuForm')" style="margin-left: 8px"  >重置</Button>
                    </FormItem>
                </Form>

            </div>
        </Split>

    </div>
</template>

<script>

    let menuObj= {
                sid:'',
                parentId:'',
                displayName: '',
                url: '',
                pagePath:'',
                menuName: '',
                status: '',
                icon: '',
                sortKey: '',
                notes: '',
                category:'',
            };


export default {
    name:'MenusManage',
    data(){
        return{
            split1:0.2,
            menus:[],
            /* menus: [
                    {
                    sid:'1',
                    parentId:'-1',
                    displayName: '后台管理',
                    url:'',
                    menuName:'',
                    status:'1',
                    icon:'',
                    sortKey:'0',
                    notes:'',
                    expand: true,
                    children: [
                        {
                            id:'1-1',
                            parentId:'1',
                            name: '首页',
                            url:'/welcom',
                            menuName:'welcom',
                            status:'1',
                            icon:'ios-home-outline',
                            sortKey:'1',
                            notes:'测试',
                            expand: true,
                            children:[]
                        },
                        {   
                            id:'1-2',
                            parentId:'1',
                            name: '系统管理',
                            url:'',
                            menuName:'',
                            status:'',
                            icon:'',
                            sortKey:'',
                            notes:'',
                            expand: true,
                            children: [
                                {
                                    id:'1-2-1',
                                    parentId:'1-2',
                                    name: '菜单管理',
                                    url:'',
                                    menuName:'',
                                    status:'',
                                    icon:'',
                                    sortKey:'',
                                    notes:'',
                                    expand: true,
                                    children:[
                                        {
                                            id:'1-2-1-1',
                                            parentId:'1-2-1',
                                            name: '菜单管理',
                                            url:'',
                                            menuName:'',
                                            status:'',
                                            icon:'',
                                            sortKey:'',
                                            notes:'',
                                            expand: true,
                                            children:[]
                                        },
                                        {
                                            id:'1-2-1-2',
                                            parentId:'1-2-1',
                                            name: '菜单授权',
                                            url:'',
                                            menuName:'',
                                            status:'',
                                            icon:'',
                                            sortKey:'',
                                            notes:'',
                                            expand: true,
                                            children:[]
                                        }
                                    ]
                                },
                                {
                                    id:'1-2-2',
                                    parentId:'1-2',
                                    name: '用户管理',
                                    url:'',
                                    menuName:'',
                                    status:'',
                                    icon:'',
                                    sortKey:'',
                                    notes:'',
                                    expand: true,
                                    children:[]
                                }
                            ]
                        }
                    ]
                }
            ], */
            buttonProps: {
                type: 'text',
                /* size: 'small', */
            },
            menuForm:Object.assign({},menuObj,{}),
            isDisabled:false,//表单按钮是否不可点击
            ruleValidate: {
                displayName: [
                    { required: true, message: '菜单名称必填!', trigger: 'blur' }
                ],
                url: [
                    /* { required: true, message: '菜单URL必填!', trigger: 'blur' }, */
                ],
                menuName: [
                   /*  { required: true, message: 'Please select the city', trigger: 'change' } */
                ],
                status: [
                    /* { required: true, message: '请选中是否有效！', trigger: 'change' } */
                ],
                
                notes: [
                    /* { required: true, message: 'Please enter a personal introduction', trigger: 'blur' }, */
                    { type: 'string', max: 200, message: '最大不能超过200个字！', trigger: 'blur' }
                ]
            }

        }
    },
    created() {
        this.loadMenus();//加载后台菜单
    },
    methods: {
        loadMenus(){//加载后台菜单
            let _this = this;
            this.axios.get('/sysMenu/query',{params:{expand:true}})
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
        append (root, node, data) {//添加菜单
            /* console.log("============添加菜单=========");
            console.log(data);
            console.log(node); */
            var selectedNodes=this.$refs.menusTree.getSelectedNodes();
            //console.log(selectedNodes);
            selectedNodes.forEach(node => {
                node.selected=false;
            });

            const children = data.children || [];
            var newMenu=Object.assign({},menuObj,{
                sid:'',
                displayName:'新建菜单',
                parentId:data.sid,
                status:'1',
                selected:true,
                expand:true
            });
            //this.menuForm=Object.assign({},newMenu,{});
            this.menuForm=newMenu;
            children.push(newMenu);
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
                    title: '您确定要删除菜单？',
                    content: '<p>删除该菜单同时会删除该菜单下所有子菜单！</p>',
                    onOk: () => {
                        
                        //后台删除数据
                        this.axios.post('/sysMenu/deletes',JSON.stringify(data))
                        .then((response)=>{
                            let row = response.data;
                            _this.$Message.info(row.message);
                            if(row.success){
                                    parent.children.splice(index, 1);
                            }
                        })
                        .catch((error)=>{
                            _this.$Modal.error({title: '异常',content: error});
                        });
                    },
                    onCancel: () => {
                        this.$Message.info('取消删除菜单');
                    }
                });

            }else{
                parent.children.splice(index, 1);
            }

        },
        menuOnSelectChange(selectNodes,node){//菜单选中事件
            console.log("============菜单选中=========");
            console.log(node);
            //表单赋值
            //var k=Object.assign({},node,{});
            this.menuForm=node;
            this.isDisabled=this.menuForm.parentId=='-1'?true:false;//根节点不能修改
            
        },
        handleSubmit (name) {//表单提交
            let _this = this;
            this.$refs[name].validate((valid) => {

                if (valid) {
                    
                    _this.loading=true;
                    _this.axios.post('/sysMenu/save',JSON.stringify(_this.menuForm))
                    .then((response)=>{
                        let row = response.data;
                        _this.loading=false;
                        _this.$Message.info(row.message);
                        if(row.success){
                            _this.menuForm.sid = row.sid;
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
    #menusManage{
        height: 100%;
    }

    .menus-split-pane{
        height: 100%;
        padding: 10px;
        overflow: auto;
    }

</style>