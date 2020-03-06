<template>
    <footer id="index-footer" :class="contentActiveTheme" >
      <div class="left-contact">
        <div class="contact-d">
          <Icon type="ios-chatbubbles" />
        </div>
        <div class="contact-d">
          <Icon type="logo-twitch" />
        </div>
        <div class="contact-d">
          <Icon type="logo-github" />
        </div>
        <div class="contact-d">
          <Icon type="ios-thumbs-up-outline" />
        </div>
       
      </div>
      <span class="copyright">Copyright © 2020 Liuhaijiang </span>
    </footer>
</template>


<script>


export default {
  name: 'index-footer',
  data(){
    return {
    }
  },
  props:{
      contentActiveTheme:String,
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
  
  .copyright{
    position: absolute;
    bottom: 0;
    right: 0;
    padding: 2rem;
    padding-bottom: 1rem;
  }
  .left-contact{
    position: absolute;
    left: 0;
    padding: 2rem;
    bottom: 2rem;
  }
  .left-contact i{
    font-size: 1.6rem;
  }
  .contact-d{
    padding-top: 2rem;
    cursor: pointer;

  }
</style>
