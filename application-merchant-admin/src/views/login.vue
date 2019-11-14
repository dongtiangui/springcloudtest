<template>
  <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px"
           class="demo-ruleForm login-container">
    <h3 class="title">商家系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="账号">
        <template slot="prepend"><i class="fa fa-1x fa-user"></i></template>
      </el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="密码">
        <template slot="prepend"><i class="fa fa-1x fa-lock"></i></template>
      </el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit" :loading="logining">登录
      </el-button>
    </el-form-item>
    <el-form-item>
      <el-link type="success"><router-link to="index" >home</router-link></el-link>
    </el-form-item>
  </el-form>

</template>

<script>
  import {requestLogin} from '../api/api'
  export default {
    data () {
      return {
        logining: false,
        ruleForm2: {
          account: '',
          checkPass: ''
        },
        rules2: {
          account: [
            {required: true, message: '请输入账号', trigger: 'blur'},
          ],
          checkPass: [
            {required: true, message: '请输入密码', trigger: 'blur'},
          ]
        },
        checked: true
      }
    },
    methods: {
      handleSubmit () {
        this.$refs.ruleForm2.validate((valid) => {
          if (valid) {
            this.logining = true
            let loginParams = {username: this.ruleForm2.account, password: this.ruleForm2.checkPass}
            //引用全局变量
            // this.$post(this.$base+'/login',loginParams).then(data => {
            //   this.logining = false
            //   let {msg, code, user} = data
            //   if (code !== 200) {
            //     this.$message({
            //       message: msg,
            //       type: 'error'
            //     })
            //   } else {
            //     // 本地存储
            //     sessionStorage.setItem('user', JSON.stringify(user))
            //     this.$router.push({path: '/home'})
            //   }
            // })
            this.$router.push({path: '/index'})
          } else {
            console.log('error submit!!')
            return false
          }
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
  .login-container {
    box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0 0 rgba(0, 0, 0, 0.02);
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;

    .title {
      margin: 0 auto 40px auto;
      text-align: center;
      color: #505458;
    }

    .remember {
      margin: 0 0 35px 0;
    }
  }
</style>
