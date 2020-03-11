<template>
    <div id="error-404" class="error-404 plan-centre">
      <h1>404抱歉页面找不到！</h1>
      
    </div>
</template>


<script>
export default {
  name: '404',
  data(){
    return {
    }
  },
  created() {
    
  },
  mounted () {//钩子
    this.$Modal.error({title: '404异常',content: "error"});
  },
  methods: {
  },
  destroyed () {
  },

}
</script>

<style scoped>
  .error-404{
    text-align: center;
    font-size: 30px;
    
  }
</style>