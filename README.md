# perfect-blog项目

#### 介绍
perfect-blog项目使用的主要技术：后台使用SpringBoot+SpringSecurity+MyBatis+MySql+Swagger，前端使用Vue+Axios+vue-router+vue-echarts，属于前后端分离项目，主要功能是用户管理和用户博客管理。

#### 项目功能概况图
![输入图片说明](https://images.gitee.com/uploads/images/2020/0811/132033_f3c435f6_6533994.jpeg "博客管理系统.jpeg")

#### 项目其余功能

除了以上功能之外，项目在登录模块使用了Spring的安全框架——SpringSecurity，对不同角色用户的访问权限进行认证和授权，使得项目在安全性能方面有了一定的提升；项目通过视图和定时任务进行文章访问量的计算，最后通过echarts统计图查看文章最近访问流量；项目使用Swagger编写了接口文档，项目运行以来之后可以通过[http://localhost:8081/swagger-ui.html#/](http://localhost:8081/swagger-ui.html#/)查看接口文档，进而对项目的功能和接口的功能有一个大致的了解。

#### Wiki帮助文档

1：[项目数据库表设计](https://gitee.com/wei_zhong_liu/vueblog/wikis/%E9%A1%B9%E7%9B%AE%E6%95%B0%E6%8D%AE%E5%BA%93%E8%A1%A8%E8%AE%BE%E8%AE%A1?sort_id=2467437)

2：[项目数据库视图设计](https://gitee.com/wei_zhong_liu/vueblog/wikis/%E9%A1%B9%E7%9B%AE%E6%95%B0%E6%8D%AE%E5%BA%93%E8%A7%86%E5%9B%BE%E8%AE%BE%E8%AE%A1?sort_id=2893744)

3：[Swagger接口文档说明](https://gitee.com/wei_zhong_liu/vueblog/wikis/Swagger%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3%E8%AF%B4%E6%98%8E?sort_id=2899399)

#### 项目架构

根据项目架构设计，项目结构可以划分为以下几个层次

**1：后台** 
- 持久对象层：该层由实体类组成
- 数据访问层：该层由DAO接口和MyBatis映射文件组成
- 业务逻辑层：该层由Service接口组成
- 控制层：该层由Controller控制类组成

 **2：前端**
- 路由层：路由文件，定义了各个页面对应的url
- 视图层：存储视图和组件 

#### 项目开发及运行环境

- 操作系统：Windows
- Java开发包：JDK
- 开发工具：IDEA、postman、git、navicat、processon、vue-ui、node、vscode
- 项目构建工具：Maven
- 数据库：MySQL
- 浏览器：谷歌

#### 项目截图

![输入图片说明](https://images.gitee.com/uploads/images/2020/0811/140832_aad3372b_6533994.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0811/140850_0309f399_6533994.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0811/140904_5dc4246b_6533994.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0811/140914_1e483ff3_6533994.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0811/140925_1c49d7ca_6533994.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0811/141057_c655b6c4_6533994.png "屏幕截图.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0923/165923_5ae0b2b5_6533994.png "屏幕截图.png")

#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
