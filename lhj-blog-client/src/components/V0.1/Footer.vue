<template>
    <footer id="index-footer">
      <div  class="footer-comtent" >
          <div class="content-width">
            <span class="copyright">Copyright © 2020 Liuhaijiang </span>
          </div>
      </div>
    </footer>
</template>


<script>


export default {
  name: 'index-footer',
  data(){
    return {
    }
  },
  mounted () {
  },
  methods: {
  },
  destroyed () {
  },

}
</script>


<style scoped>
  .footer-comtent{
    height: 70px;
    line-height: 70px;
    background-color: #333;
  }

  .copyright {
    float:right;
    color:#fff;
  }
</style>
