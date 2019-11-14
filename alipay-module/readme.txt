本demo为小程序云应用示例程序todo-list的后端服务，需配合小程序开发者工具提供的前端demo使用。
该应用演示了以下3个功能：
1. 调用小程序服务端SDK【UserController】
2. 数据库增删改查【TaskController】
3. 文件上传下载【UploadController】

应用部署运行：
1、配置resource/application.properties文件中的相关参数
2、在根目录下,执行: mvn clean package 打包命令
3、在根目录下,启动alipayDemo-todo系统命令：java -jar target/alipaydemo-todo-1.0.0.jar com.alipay.demo.Application.class
4、使用IDEA Alipay DevTools将应用部署到云服务器

具体的demo上手文档请参看https://docs.alipay.com/mini/cloud-service/case9x