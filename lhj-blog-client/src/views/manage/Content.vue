<style scoped>
</style>

<template>
  <div class="layout">
      <Layout :style="{height:'100%'}">
        <!-- =====================左侧菜单开始===================== -->
        <Sider hide-trigger :style="{background: '#fff',height:'100%'}">
            <Menu :active-name="menuActiveName" theme="light"  :open-names="openMenus" ref="side_menu" :style="{height:'100%'}"  width="auto" >
                <template v-for="(menu,index) in leftMenus">
                <Submenu v-if="menu.nextMenus && menu.nextMenus.length>0" :name="menu.id"  :key="index+'-'+menu.id">
                    <template slot="title">
                        <Icon :type="menu.icon"></Icon>
                        {{menu.name}}
                    </template>
                    <MenuItem :to="{path:nextMenu.url}"  v-for="(nextMenu) in menu.nextMenus" :key="index+'-'+nextMenu.id" :name="nextMenu.url">
                        {{nextMenu.name}}
                    </MenuItem>
                </Submenu>

                <MenuItem v-else :to="{path:menu.url}" :key="index+'-'+menu.id" :name="menu.url">
                    <Icon :type="menu.icon"></Icon>
                    {{menu.name}}
                </MenuItem>

                </template>
            </Menu>
        </Sider>
        <!-- =====================左侧菜单结束===================== -->

        <!-- =====================右侧主体内容开始===================== -->
        <Content :style="{ minHeight: '280px', background: '#fff'}">
            <router-view/>
        </Content>
        <!-- =====================右侧主体内容结束===================== -->
        
    </Layout>
  </div>
</template>


<script>
  export default {
    name: 'myContent',
    props:{
        menuActiveName:String,
        openMenus:Array,
        leftMenus:Array,
    },
    data () {
      return {
      }
    },
    computed:{
    },
    created(){
        
    },
    methods:{
        
    },
    mounted(){
       
    }

  }
</script>
