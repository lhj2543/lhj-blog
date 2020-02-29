

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