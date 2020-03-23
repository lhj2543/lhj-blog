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
  
  <div class="content-panel" >

    <div class="content-card">
      <div class="header" >
        <span class="title" v-if="thisRow.pageType == 'modify'">
          <Icon :type="$common.icon.modify" />
          修改角色
        </span>
        <span class="title" v-else-if="thisRow.pageType == 'add'">
          <Icon :type="$common.icon.add" />
          新增角色
        </span>
        <span class="title" v-else >
          <Icon :type="$common.icon.detail" />
          角色详情
        </span>
        
        <span class="right-tool" style="float:right">
          <Button v-if="!isDisabled"  type="info" ghost @click="save"  :icon="$common.icon.save" size="small" >保存</Button>
          <Button  type="info" ghost @click="cancel"  :icon="$common.icon.cancel"  size="small" ><span v-if="isDisabled" >返回</span><span v-else >取消</span></Button>
          <!-- <Button  type="info" ghost @click="formReset('dataForm1')"  :icon="$common.icon.clean" size="small" >重置</Button> -->
          <Button  type="info" ghost @click="init"  :icon="$common.icon.refresh" size="small" >刷新</Button>
        </span>
      </div>
      <div class="card-body" style="padding:25px 25px;">

        <Form ref="dataForm1" :model="row" :rules="form1Validate" :disabled="isDisabled" :label-width="120" style="padding:20px;">
          <Row>
            <Col span="6">
              <FormItem label="角色code" prop="roleCode">
                <Input type="text" :disabled="isDisabled || roleCodeIsDisabled" v-model="row.roleCode" placeholder="请输入角色cd...">
                </Input>
              </FormItem>
            </Col>
            <Col span="6">
              <FormItem label="角色名称"  prop="roleName">
                  <Input type="text" :disabled="isDisabled" v-model="row.roleName" placeholder="请输入角色名称...">
                  </Input>
              </FormItem>
            </Col>
            <Col span="6">
              <FormItem label="角色分类"  prop="category">
                  <Input type="text" :disabled="isDisabled" v-model="row.category" placeholder="请输入角色分类...">
                  </Input>
              </FormItem>
            </Col>
            <Col span="6" >
              <FormItem label="状态"  prop="status">
                <RadioGroup v-model="row.status" >
                  <Radio v-for="(value,key,index) in items['isActive']" :key="key+'-'+value" :label="key">{{value}}</Radio>
                </RadioGroup>
              </FormItem>
            </Col>
          </Row>

          <Row v-if="thisRow.pageType=='detail'">
            <Col span="6" >
              <FormItem label="创建人"  prop="createdBy">
                <Input type="text" :disabled="isDisabled" v-model="row.createdBy" placeholder="">
                </Input>
              </FormItem>
            </Col>
            <Col span="6">
              <FormItem label="创建时间"  prop="createdDate">
                  <Input type="text" :disabled="isDisabled" v-model="row.createdDate" placeholder="">
                  </Input>
              </FormItem>
            </Col>
            <Col span="6">
              <FormItem label="修改人" prop="updateBy">
                <Input type="text" :disabled="isDisabled" v-model="row.updateBy" placeholder="">
                </Input>
              </FormItem>
            </Col>
            <Col span="6">
              <FormItem label="修改时间"  prop="updateDate">
                  <Input type="text" :disabled="isDisabled" v-model="row.updateDate" placeholder="">
                  </Input>
              </FormItem>
            </Col>
          </Row>

          <Row>
            <Col span="24">
              <FormItem label="备注" prop="notes">
                <Input type="textarea" maxlength="500" :disabled="isDisabled" show-word-limit v-model="row.notes" placeholder="请输备注..." :rows="5" >
                </Input>
              </FormItem>
            </Col>
            
          </Row>
        </Form>

      </div>
        
    </div>
    
    <!-- loadding 防止多次点击 -->
    <Spin fix v-if="loading">
        <Icon type="ios-loading" size=18 class="spin-icon-load"></Icon>
        <div>数据保存中请稍后</div>
    </Spin>
    
  </div>

</template>


<script>

  export default {
    name: 'roleModify',
    data () {
      return {
        detailUrl:'/sysRole/detail',
        saveUrl:'/sysRole/modify',
        isDisabled:this.thisRow.pageType=='detail'?true:false,
        roleCodeIsDisabled:this.thisRow.pageType=='modify'?true:false,
        loading:false,//laoding 标识
        items:{},//字典数据
        row:{},
        form1Validate:{//表单验证
          roleCode: [
              { required: true,message:'请输入角色cd',validator: '', trigger: 'blur' }
          ],
          roleName: [
              { required: true,message:'请输入角色名称',validator: '', trigger: 'blur' }
          ],
        },
        
      }
    },
    props:{
      thisRow:Object,//父页面传过来的参数
    },
    components:{//注册组件
      
    },
    beforeCreate(){//拿不到任何信息，无法篡改数据，一般做loding，这个时候的vue实例还什么都没有，但是$route对象是存在的，可以根据路由信息进行重定向之类的操作
      
    },
    created() {//el 没有初始化，数据已加载完成，阔以篡改数据，并更新，不会触发，，在这结束，还做一些初始化，实现函数自执行，ref属性内容为空数组
       /* 加载页面上所有用到的字典 */
      let params = {categoryNames:['isActive']};
      this.getSysItems(params,(data)=>{
        this.items = data.itemMap?data.itemMap:{};
      });
    },
    beforeMount(){//$el已被初始化,，数据已加载完成，阔以篡改数据，并更新，不会触发beforeUpdate，updated，在挂载开始之前被调用，beforeMount之前，会找到对应的template，并编译成render函数
    },
    methods:{//el 已被初始化，数据已加载完成，阔以篡改数据，并更新，并且触发，，在这发起后端请求，拿回数据，配合路由钩子做一些事情，ref属性可以访问
      init(){//表单初始化
        let _this = this;
        this.axios.get(this.detailUrl,{params:{
          sid:_this.thisRow.sid
        }})
        .then((response)=>{
          _this.loading=false;
          let row = response.data;
          if(row.success){
            _this.row = row;
          }else{
            _this.$Message.info(row.message);
          }
        })
        .catch((error)=>{
          _this.loading=false;
          _this.$Modal.error({title: '异常',content: error});
        });
      },
      save(params){//保存
        let _this=this;

        this.$refs.dataForm1.validate((vali1)=>{//表单验证

          if(vali1){

            _this.loading=true;
            _this.axios.post(_this.saveUrl,JSON.stringify(_this.row))
            .then((response)=>{
              let row = response.data;
              _this.loading=false;
              _this.$Message.info(row.message);
              if(row.success){
                let isReFresh = _this.thisRow.pageType=='add'?true:false;
                let updateData = _this.thisRow.pageType=='modify'?{row:row}:false;
                _this.$emit('chindrenChangeData',{showPage:"list",updateData:{row:row},isReFresh:isReFresh});//调用父级方法
              }
            })
            .catch((error)=>{
              _this.loading=false;
              _this.$Modal.error({title: '异常',content: error});
            });

          }

        });
        
      },
      cancel(){//取消
        this.$emit('chindrenChangeData',{showPage:"list"});//调用父级方法
      },
    },
    mounted(){
      this.init();
    },
    destroyed(){
    
    }

  }

</script>
