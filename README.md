# ***student-manager***
### 学生后台管理系统（仅供学习使用）
本项目使用*Spring + Spring MVC + MyBatis* 编写后端数据；前端使用 *js、jQuery、CSS、HTML、AJAX*等编写。<br>
本人负责编写后端**数据库交互、业务代码编写、测试代码编写**以及**与前端交互的API编写。** 前端页面来源于网络。<br><br>
项目主要功能：<br>
* 1、制作登录验证、非登录的非法请求拦截。
* 2、对学生注册信息进行**查询、修改、增加以及删除**；按照输入内容对学生进行**信息模糊搜索。**
* 3、对学生分数进行**查询、修改、增加以及删除**操作。
* 4、按照对学生注册信息进行**查询、修改、增加、删除**以及**按照注册时间统计**。具体可按照**最近注册时间**统计、按照指定时间段分组查询出每个时间段注册的人数
* 5、对学生成绩按照分数段分段**统计、整理、传递**数据。 具体可按照指定分数段查询出某个分数段具体有多少人，该分数段中具体有哪些人。
* 6、管理员对权限的控制。主要为对**敏感操作进行权限拦截**；管理员在其相应权限内进行操作；具有权限的管理员可以**对其他管理员、用户进行权限操作**，例如：增加权限、修改管理员信息、限制用户权限等