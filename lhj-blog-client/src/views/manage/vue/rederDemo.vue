<template>
    <div>拉开圣诞节flak第三方</div>
</template>

<script>
export default {
    name:'renderDemo',
    render(h){
        
        return h('div',
            {
                attrs:{
                    id:'reder-div'
                },
                style:{
                    background:'red'
                },
            },
            [
              h('span','啦啦啦'),
              h('div','232')
            ]
        );
    },
    data(){
        return{
            msg:'哈哈哈哈哈'
        }
    },
}
</script>