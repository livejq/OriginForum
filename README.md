<h1 align="center">JavaWeb Forum</h1>
<br>

<p align="center">
  	<a href="http://hits.dwyl.io/livejq/java_web_forum"><img alt="HitCount" src="http://hits.dwyl.io/livejq/java_web_forum.svg"></a>
  	<a href="https://github.com/livejq/java_web_forum/issues"><img alt="GitHub issues" src="https://img.shields.io/github/issues/livejq/java_web_forum.svg"></a>
	<a href="https://github.com/livejq/java_web_forum/blob/master/LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/license/livejq/java_web_forum.svg"></a>
</p>

<br>

<p align="center">
<span>一个以🚩班级为单位的论坛，方便学生和老师的问题交流🥳</span>
</p>

<hr>

从大二下学期开始接触到了JavaWeb技术，借助Tomcat😸的power，从最原始的JSP和Servlet搞起。虽然耦合度较高，现在也基本没人用，但也算是第一次见识到了“古人”的Web技术。又到了一年两度的期末课设作业，每次到了交作业的时候，不是学委替老师收集，就是等到上课的时候将作业从自己的U盘拷到老师那里去，十分的不方便。因此，基于这些“简单”的🌲需求，我就想用这学期所学的JavaWeb技术设计一个有学生和老师两种身份的登录模式，有根据科目或问题类型的论坛答疑社区并且学生登录后可发布问题。💡在最后还附加了以班级为单位，学生可以查看并提交作业，而老师可以布置和收集作业的功能。



>*以需求为前提和基础，来导出界面的整体规划和结构设计；以用户的感受为最优考虑，来实现更加人性化的功能操作和数据显示。*




## 内容列表

- [实现效果](#实现效果)
- [安装配置](#安装配置)
- [注意事项](#注意事项)
- [致谢](#致谢)
- [参与贡献方式](#参与贡献方式)
- [许可证](#许可证)



## 实现效果

![login](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/login.png "登录")

![Student首页](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/student.png "Student首页")

![Teacher首页](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/teacher.png "Teacher首页")

![forum](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/forum.png "论坛")

![community](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/community.png "查看问题")

![solution](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/solution.png "问题详情")

![comments](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/comments.png "评论问题")

<br>

评论样式参考了cnblog（有没有觉得有点熟悉😬），按钮参考了CSDN，下同~

<br>

![task](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/task.png "老师布置作业")

![show](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/show.png "学生查看作业")

![sent](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/sent.png "学生上交作业")

![gather](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/gather.png "老师收集作业")



## 安装配置

<br>

> 我的环境

- Java8
- Eclipse Java EE IDE for Web Developers
- 57.0.3098.106 Opera
- apache-tomcat-8.5.34
- MySQL 5.7.21

<br>

> 导入到Eclipse

新建Dynamic Web Project，将src和WebContent下的内容复制到对应的目录下即可。

<br>

> 导入数据库

将WebContent/database目录下的 [livejq.sql](https://github.com/livejq/java_web_forum/blob/master/WebContent/database/livejq.sql) 文件导入到MySQL即可。

![mysql](https://raw.githubusercontent.com/livejq/blog-comment-repo/master/java_web_forum/mysql.png "数据表")


##  注意事项

- JRE目录下的lib/ext扩展库中别忘了添加servlet-api.jar



## 致谢

<br>

感谢秦老师的耐心指导~



## 参与贡献方式

<br>

还有很多小功能未来得及实现，欢迎[PR](https://github.com/livejq/java_web_forum/pulls)和[issuess](https://github.com/livejq/java_web_forum/issues)



## 许可证

<br>

@[MIT](https://github.com/livejq/java_web_forum/blob/master/LICENSE) LICENSE