<template>
    <el-header>
        <el-row>
            <el-col :span="22">
                <div style="float: right">
                    <el-tag type="danger">系统状态:</el-tag>
                    <el-switch
                            v-model="logout_value"
                            active-color="#13ce66"
                            inactive-color="#ff4949">
                    </el-switch>
                </div>
            </el-col>
        </el-row>
    </el-header>
</template>

<script>
    export default {
        name: "headed",
        data(){
            return{
                logout_value: true, // 系统状态值
            }
        },
        methods:{

        },
    }
</script>

<style scoped>

</style>
