<template>

    <div class="content-panel" style="height:100%;">

        <div class="content-card" style="height:100%;">
            <div class="header" >
                <span class="title" >
                <Icon :type="$common.icon.detail" />
                    笔记管理
                </span>
                
                <span class="right-tool" style="float:right">
                    <span v-if="showPage == 'list'" >
                        <span style="float:left;" title="检索"><Input @on-search="search" v-model="searchForm.searchValue" search enter-button style="width:300px;"  /></span> &nbsp;
                        <!-- <Button  type="info" ghost @click="toModify({type:'add'})"   :icon="$common.icon.search" size="small" >检索</Button> -->
                        <Button  type="info" ghost @click="toAdd"  v-if="checkSid" :icon="$common.icon.add"  >新增</Button>
                    </span>

                    <span v-if="showPage == 'modify' && (thisRow.pageType=='modify' || thisRow.pageType=='add' )" >
                        <Button  type="info" ghost @click="$refs.modifyPage.save()"   :icon="$common.icon.save" size="small" >保存</Button>
                        <Button  type="info" ghost @click="showPage='list'"   :icon="$common.icon.cancel" size="small" >取消</Button>
                    </span>

                    <Button v-if="thisRow.pageType=='detail' && showPage == 'modify'" type="info" ghost @click="showPage='list'"   :icon="$common.icon.cancel" size="small" >返回</Button>
                    <!-- <Icon title="刷新"  size="18" @click="refresh" class="cursor-pointer"  :type="$common.icon.refresh" /> -->
                </span>
            </div>
            <div class="card-body" style="height:calc(100vh - 60px);max-height:999999999px">

                <Split v-model="split1" min="250px" max="900px" >
                    <div slot="left" class="menus-split-pane" >
                        
                        <Tree :data="menus" :render="renderContent" class="menus-tree" @on-select-change="menuOnSelectChange" ref="menusTree"  ></Tree>

                    </div>
                    
                    <div slot="right" v-if="showPage == 'list'"  class="menus-split-pane" :style="{padding:'40px 120px'}">
                        
                        <div style="width:200px;margin:0 auto;padding-top:100px;" v-if="row.rows.length <=0 ">
                            <h3>暂无数据</h3>
                        </div>



                        <!-- ============列表================== -->
                        <div  class="custom-list" v-for="(item,index) in row.rows" :index="index" :key="item.sid" >

                            <div>
                                <span class="title">{{item.title}}</span>
                                <span class="right-tool">
                                    <Icon title="修改"  size="18" @click="toModify(index)" class="cursor-pointer" color="#57c5f7"  :type="$common.icon.modify" />
                                    <Icon title="删除"  size="18" @click="deletes(index)" class="cursor-pointer" color="#57c5f7" :type="$common.icon.delete" />
                                </span>
                            </div>
                            <div class="content" @click="toDetail(index)"  v-html="item.bodys"></div>
                            <div class="buttom">
                               <span> 
                                    <Icon type="ios-star-outline" color="#f60" /> 123   &nbsp;   &nbsp; 
                                    <Icon type="ios-thumbs-up-outline" color="#f60" /> 234  &nbsp;   &nbsp; 
                                    <Icon type="ios-chatbubbles-outline" color="#f60" /> 345  &nbsp;   &nbsp; 
                                </span>
                               <span class="right-tool"> 
                                   <Icon type="ios-contact-outline" size="17" /> {{item.createdBy}}  &nbsp; 
                                   <Icon type="ios-time-outline" size="17" /> {{item.createdDate}}
                                </span>
                            </div>
                        </div>
               
                        <!-- ============列表================== -->

                    </div>

                    <div slot="right" v-if="showPage == 'modify'"  class="menus-split-pane" style="padding:0px;padding-left:6px;">
                        <!-- 新增/修改/详情 共同页面 -->
                        <modify v-if="showPage == 'modify'" ref="modifyPage" v-bind:thisRow="thisRow" v-on:chindrenChangeData="chindrenChangeData" />
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
import modify from './Modify.vue'

export default {
    name:'NotebookList',
    data(){

        return{
            split1:0.1,
            loading:false,
            checkSid:'',//选择的分类
            isAdd:false,
            items:{},//字典数据
            menus:[],//分类数据
            row:{
                rows:[]
            },
            isDisabled:false,//表单按钮是否不可点击
            showPage:'list',//显示页面，默认显示列表
            searchForm:{searchValue:''},//检索表单
            thisRow:{//修改，详情等当前选择的数据，传入子页面
                sid:''
            },

        }
    },
    created() {
        this.loadTrees();//加载后台分类
        this.search();//加载所有笔记列表
    },
    methods: {
        refresh(){//刷新
            this.loadTrees();
        },
        openAll(){//展开所有分类
            
        },
        closeAll(){//收缩所有分类

        },
        loadTrees(){//加载后台数据
            let _this = this;
            this.loading = true;
            this.axios.get('/noteBookCategory/query',{params:{expand:true}})
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
                    }, )       
                ]
            );
        },
        
        menuOnSelectChange(selectNodes,node){//分类选中事件
            console.log("============分类选中=========");
            console.log(selectNodes);
            console.log(node);
            if(selectNodes.length>0){
                this.checkSid = node.sid;
                this.loadNoteBooks({categoryId:node.sid});//加载分类下所有笔记
            }else{
                this.checkSid = '';
                this.search();
            }

            this.showPage='list';
            
        },
        search(params){//检索
            this.loadNoteBooks({categoryId:this.checkSid,searchValue:this.searchForm.searchValue});
        },
        loadNoteBooks(params){
            let _this = this;
            
            this.axios.get('/noteBook/query',{params:params})
            .then((response)=>{
                let row = response.data;
                if(row.success){
                    _this.row = row;
                }else{
                    _this.$Message.info(row.message);
                }
            })
            .catch((error)=>{
                _this.$Modal.error({title: '异常',content: error});
            });
        },
        toAdd(params){
            if(!this.checkSid){
                this.$Message.info('请先选择所属分类！');
                return;
            }

            this.showPage='modify';
            this.thisRow.pageType="add";
            this.thisRow.sid='';
            this.thisRow.categoryId=this.checkSid;
        },
        toModify(index){
            
            this.thisRow=this.row.rows[index];

            this.showPage='modify';
            this.thisRow.pageType="modify";
            this.thisRow.index = index;
            
        },
        toDetail(index){
            
            this.thisRow=this.row.rows[index];

            this.showPage='modify';
            this.thisRow.pageType="detail";
            this.thisRow.index = index;
            
        },
        deletes(index){
            let _this = this;
            let row = this.row.rows[index];
            let params = {rows:[row]};

            _this.$Modal.confirm({title: '友情提示',content: '你确定要删除操作？'
            ,onOk:()=>{

                this.axios.post('/noteBook/deletes',JSON.stringify(params))
                .then((response)=>{
                    let row = response.data;
                    _this.$Message.info(row.message);

                    if(row.success){
                        _this.search();
                        //this.row.rows.splice(index, 1);
                    }

                })
                .catch((error)=>{
                    _this.$Modal.error({title: '异常',content: error});
                });


            }});
        },
        chindrenChangeData(params){/* 子类修改父级数据调用方法 */
            this.showPage=params.showPage;
            if(params.updateData){
                this.row.rows[this.thisRow.index]=params.updateData.row;
            }
            if(params.isReFresh){
                this.search();
            }
        },
        

    },
    mounted(){
        
    },
    components:{//注册组件
      modify
    },
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