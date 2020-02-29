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
                             <FormItem label="菜单名称" prop="name">
                                <Input v-model="menuForm.name" placeholder="请输入菜单名称..."></Input>
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
                             <FormItem label="URL名称" prop="urlName">
                                <Input v-model="menuForm.urlName" placeholder="请输入URL名称..."></Input>
                            </FormItem>
                        </Col>
                        <Col span="12">
                             <FormItem label="是否有效" prop="isActive">
                                <Select v-model="menuForm.isActive" placeholder="请选中是否有效...">
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
                            <FormItem label="排序" prop="order">
                                <Input v-model="menuForm.order" placeholder="请输入序号..."></Input>
                            </FormItem>
                        </Col>
                    </Row>
                    
                    <FormItem label="菜单描述" prop="desc">
                        <Input v-model="menuForm.desc"   type="textarea"  :autosize="{minRows: 2,maxRows: 10}" placeholder="请输入菜单描述..."></Input>
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
var menuObj= {
                id:'',
                parentId:'',
                name: '',
                url: '',
                urlName: '',
                isActive: '',
                icon: '',
                order: '',
                desc: ''
            };


export default {
    name:'MenusManage',
    data(){
        return{
            split1:0.2,
            menus: [
                    {
                    id:'1',
                    parentId:'-1',
                    name: '后台管理',
                    url:'',
                    urlName:'',
                    isActive:'1',
                    icon:'',
                    order:'0',
                    desc:'',
                    expand: true,
                    children: [
                        {
                            id:'1-1',
                            parentId:'1',
                            name: '首页',
                            url:'/welcom',
                            urlName:'welcom',
                            isActive:'1',
                            icon:'ios-home-outline',
                            order:'1',
                            desc:'测试',
                            expand: true,
                            children:[]
                        },
                        {   
                            id:'1-2',
                            parentId:'1',
                            name: '系统管理',
                            url:'',
                            urlName:'',
                            isActive:'',
                            icon:'',
                            order:'',
                            desc:'',
                            expand: true,
                            children: [
                                {
                                    id:'1-2-1',
                                    parentId:'1-2',
                                    name: '菜单管理',
                                    url:'',
                                    urlName:'',
                                    isActive:'',
                                    icon:'',
                                    order:'',
                                    desc:'',
                                    expand: true,
                                    children:[
                                        {
                                            id:'1-2-1-1',
                                            parentId:'1-2-1',
                                            name: '菜单管理',
                                            url:'',
                                            urlName:'',
                                            isActive:'',
                                            icon:'',
                                            order:'',
                                            desc:'',
                                            expand: true,
                                            children:[]
                                        },
                                        {
                                            id:'1-2-1-2',
                                            parentId:'1-2-1',
                                            name: '菜单授权',
                                            url:'',
                                            urlName:'',
                                            isActive:'',
                                            icon:'',
                                            order:'',
                                            desc:'',
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
                                    urlName:'',
                                    isActive:'',
                                    icon:'',
                                    order:'',
                                    desc:'',
                                    expand: true,
                                    children:[]
                                }
                            ]
                        }
                    ]
                }
            ],
            buttonProps: {
                type: 'text',
                /* size: 'small', */
            },
            menuForm:Object.assign({},menuObj,{}),
            isDisabled:false,//表单按钮是否不可点击
            ruleValidate: {
                name: [
                    { required: true, message: '菜单名称必填!', trigger: 'blur' }
                ],
                url: [
                    /* { required: true, message: '菜单URL必填!', trigger: 'blur' }, */
                ],
                urlName: [
                   /*  { required: true, message: 'Please select the city', trigger: 'change' } */
                ],
                isActive: [
                    /* { required: true, message: '请选中是否有效！', trigger: 'change' } */
                ],
                
                desc: [
                    /* { required: true, message: 'Please enter a personal introduction', trigger: 'blur' }, */
                    { type: 'string', max: 200, message: '最大不能超过200个字！', trigger: 'blur' }
                ]
            }

        }
    },
    methods: {
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
                        h('span', data.name)
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
                name:'新建菜单',
                parentId:data.id,
                isActive:'1',
                selected:true,
                expand:true
            });
            //this.menuForm=Object.assign({},newMenu,{});
            this.menuForm=newMenu;
            children.push(newMenu);
            this.$set(data, 'children', children);
        },
        remove (root, node, data) {//移除菜单

            this.$Modal.confirm({
                title: '您确定要删除菜单？',
                content: '<p>删除该菜单同时会删除该菜单下所有子菜单！</p>',
                onOk: () => {
                    const parentKey = root.find(el => el === node).parent;
                    const parent = root.find(el => el.nodeKey === parentKey).node;
                    const index = parent.children.indexOf(data);
                    parent.children.splice(index, 1);
                },
                onCancel: () => {
                    this.$Message.info('取消删除菜单');
                }
            });
            
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
            this.$refs[name].validate((valid) => {
                if (valid) {
                    this.$Message.success('保存成功!');
                } else {
                    this.$Message.error('保存失败!');
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
        /* border: 1px solid #dcdee2; */
    }

    .menus-split-pane{
        height: 100%;
        padding: 10px;
        overflow: auto;
    }

</style>