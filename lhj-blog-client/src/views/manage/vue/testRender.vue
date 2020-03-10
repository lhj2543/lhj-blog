<template>
    <div id="testRender">

        11
      <!-- <rederDemo /> -->

     

    </div>
</template>

<script>

import rederDemo from '@/views/manage/vue/rederDemo'

export default {
    name:'testRender',
    data(){
        return{
            msg:''
        }
    },
    components:{rederDemo}
}
</script>