<template>
  <el-container  v-bind:style="{height:(screenHeight)+'px'}">
    <el-aside :style="{width:'200px'}" v-bind:width="leftwidth">
      <navMenu></navMenu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <navHeader></navHeader>
      </el-header>
      <el-main>
        <navMain></navMain>
      </el-main>
      <el-footer>
          <footer></footer>
      </el-footer>
    </el-container>
  </el-container>
</template>
<script>

  import navMenu from '@/layout/navmenu/navmenu'
  import navHeader from '@/layout/header/headed'
  import navMain from '@/layout/navmain/main'
  import footer from '@/layout/footer/footer'
  export default {
    name: 'home',
    data(){
      return{
        screenHeight:'',
        leftwidth: '200',
      }
    },
    components: {
      navMenu,
      navHeader,
      navMain,
      footer
    },
    watch: {
      'screenHeight': function(el) {
        let main = document.getElementsByClassName('.el-container')
        main.style.height = (Number(el)) + 'px'
      }

    },
    //改变窗口大小
    mounted () {
      let _this = this;
      window.onresize = function () {
        _this.screenHeight = document.documentElement.clientHeight
      }
    },

  }
</script>

<style scoped>

  .el-menu-horizontal {
    width: auto !important;
  }

  .el-disabled-item {
    background-color: #ff2f0d !important;
  }

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }

  .el-header, .el-footer {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
</style>
