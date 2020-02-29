<style scoped>
.welcome-content{
    width: 100%px;
    margin: 0 auto;
    text-align: center;
    padding: 150px 0;
    font-size: 30px;
}
</style>

<template>
  <div id="welcome-content" class="welcome-content">
    <h1>Welcome To Manage Home</h1>
  </div>
</template>


<script>
  export default {
    name: 'Welcome',
    data () {
      return {
        
      }
    },
    computed:{//是在HTML DOM加载后马上执行的，如赋值；

    },
    methods:{//方法

    },
    watch:{//它用于观察Vue实例上的数据变动。对应一个对象，键是观察表达式，值是对应回调。值也可以是方法名，或者是对象，包含选项。所以他们的执行顺序为：默认加载的时候先computed再watch，不执行methods；等触发某一事件后，则是：先methods再watch。

    },
    mounted(){//生命周期

    }

  }
</script>
