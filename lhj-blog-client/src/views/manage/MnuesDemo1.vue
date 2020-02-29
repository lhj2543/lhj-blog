  <template>
  <div class="hello">
        <div class="icondiv"></div>
        <Menu :active-name="selected" @on-select="menuselect" theme="light" ref="child" style="width:200px" :open-names="openname" >
            <template  v-for="(item,index) in listdata" > 
                <template v-if="item.child&&item.child.length>0">
                    <Submenu :name="item.name" :key="index">
                        <template slot="title">
                            <Icon :class="item.icon"></Icon>
                            {{item.name}}
                        </template>
                        <template v-for="(sub,index) in item.child">
                            <MenuItem :name="sub.href" :key="index" >{{sub.name}}</MenuItem>
                        </template>
                    </Submenu>
                </template>
                <template v-else>
                <MenuItem :name="item.href" :key="index" >
                    <div :class="item.icon"></div>
                    <div class="layout-text">{{item.name}}</div>
                </MenuItem>
              </template>
            </template>
        </Menu>
  </div>
</template>
<script>
export default {
      name: 'MnuesDemo1',
      data () {
        return {
            listdata:[
                {
                    'name':'公告',
                    'icon':['ixitong','cipp'],
                    'href':'#/menu1',
                },
                {
                    'name':'解惑',
                    'icon':['ianswer','cipp'],
                    'href':'#/menu2',
                },
                {
                    'name':'设置',
                    'icon':['im-extension','cipp'],
                    'child':[
                        {
                            'name':'审核',
                            'href':'#/submenu1',
                        },
                        {
                            'name':'托管',
                            'href':'#/submenu2',
                        },
                    ]
                }
            ],
            selected:"#/submenu1",
            openname:[],
        }
    },
      methods: {
            menuselect (a) {
                this.$router.push({path:a.split('#')[1]});
            },
        watchRoute(){
            if(this.$refs.child&&this.$route.name!="submenu1"&&this.$route.name!="submenu2"){
                this.openname = [];
                    this.$refs.child.$children[2].opened = false;
            }else{
                this.openname = ['设置'];
            }
            this.$nextTick(()=> {
                    this.$refs.child.updateOpened();
                });
        }
      },
      watch:{
            $route(){
                this.watchRoute();
            }
      },
      mounted(){
          this.watchRoute();
      }
}
</script>
